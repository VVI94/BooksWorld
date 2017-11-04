package models.DBmodels;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controllers.UploadBookServlet;
import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IBookDAO;
import models.entities.Author;
import models.entities.Book;
import models.entities.comments.Comment;

public class BookDAO extends DAO implements IBookDAO {

	private static BookDAO instance;

	private BookDAO() {
	}

	public static BookDAO getInstance() {
		if (instance == null) {
			instance = new BookDAO();
		}

		return instance;
	}

	public void addBook(Book book) throws SQLException, AlreadyExistException, ValidationException {

		boolean isInTransaction = false;

		if (!this.getCon().getAutoCommit()) {
			isInTransaction = true;
		}

		try {
			this.getCon().setAutoCommit(false);

			long categoryId = CategoryDAO.getInstance().addCategory(book.getCategory());

			long authorId = AuthorDAO.getInstance().addAuthor(book.getAuthor());

			if (this.exist(book.getTitle(), authorId)) {
				this.getCon().rollback();
				throw new AlreadyExistException("This book already exist");
			}

			PreparedStatement ps = this.getCon()
					.prepareStatement("INSERT INTO "
							+ "books(title, description, year, publisher, price, authors_author_id, categories_category_id, photo)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getDescription());
			ps.setInt(3, book.getYear());
			ps.setString(4, book.getPublisher());
			ps.setDouble(5, book.getPrice());
			ps.setLong(6, authorId);
			ps.setLong(7, categoryId);
			ps.setString(8, book.getPhoto());

			ps.executeUpdate();

		} catch (SQLException e) {
			this.getCon().rollback();
			throw new SQLException("Can't add this book", e);
		} finally {
			if (!isInTransaction) {
				this.getCon().commit();
				this.getCon().setAutoCommit(true);
			}
		}

	}

	public void removeBook(long bookId) throws SQLException {

		boolean isInTransaction = false;

		if (!this.getCon().getAutoCommit()) {
			isInTransaction = true;
		}

		try {
			this.getCon().setAutoCommit(false);

			this.removeComments(bookId);

			this.deleteAvatar(bookId);

			PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM books WHERE book_id = ?");
			ps.setLong(1, bookId);
			ps.executeUpdate();

		} catch (SQLException | UnexistingException | ValidationException e) {
			this.getCon().rollback();
			throw new SQLException("Can't remove this book", e);
		} finally {
			if (!isInTransaction) {
				this.getCon().commit();
				this.getCon().setAutoCommit(true);
			}
		}

	}

	private void removeComments(long bookId) throws SQLException, UnexistingException, ValidationException {
		Book book = this.getBook(bookId);

		for (Comment comment : book.getComments()) {
			CommentDAO.getInstance().delete(comment.getId());
		}

	}

	public Book getBook(long bookId) throws SQLException, UnexistingException, ValidationException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM books WHERE book_id = ?");
		ps.setLong(1, bookId);
		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("Book with id: " + bookId + " doesn't exists");
		}

		long id = result.getInt("book_id");
		String title = result.getString("title");
		Author author = AuthorDAO.getInstance().getAuthor(result.getLong("authors_author_id"));
		String description = result.getString("description");
		String publisher = result.getString("publisher");
		int year = result.getInt("year");
		String photo = result.getString("photo");
		double price = result.getDouble("price");
		String category = CategoryDAO.getInstance().getCategory(result.getLong("categories_category_id"));
		List<Comment> comments = CommentDAO.getInstance().getAllComments(id);

		return new Book(id, title, author, description, year, publisher, price, category, comments, photo);
	}

	public Set<Book> getAll() throws SQLException, UnexistingException, ValidationException {

		PreparedStatement ps = this.getCon().prepareStatement("SELECT book_id FROM books");
		ResultSet restult = ps.executeQuery();

		return getBooksFromResult(restult);
	}

	private Set<Book> getBooksFromResult(ResultSet restult)
			throws SQLException, UnexistingException, ValidationException {

		Set<Book> books = new HashSet<>();

		while (restult.next()) {

			long bookId = restult.getLong(1);

			books.add(this.getBook(bookId));
		}

		return books;
	}

	public boolean exist(String title, long authorId) throws SQLException, ValidationException {

		try {
			Author author = AuthorDAO.getInstance().getAuthor(authorId);

			PreparedStatement ps = this.getCon()
					.prepareStatement("SELECT b.title, a.first_name, a.last_name "
							+ "FROM books as b JOIN authors as a ON (b.authors_author_id = a.author_id) "
							+ "WHERE b.title = ? AND a.first_name = ? AND a.last_name = ?;");

			ps.setString(1, title);
			ps.setString(2, author.getFirstName());
			ps.setString(3, author.getLastName());

			ResultSet result = ps.executeQuery();

			return result.next();

		} catch (UnexistingException e) {
			return false;
		}
	}

	public Set<Long> getBookIDsByCategory(long categoryId) throws SQLException {

		Set<Long> ids = new HashSet<>();

		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT book_id  FROM books WHERE categories_category_id = ?");
		ps.setLong(1, categoryId);
		ResultSet result = ps.executeQuery();

		while (result.next()) {
			ids.add(result.getLong(1));

		}

		return ids;
	}

	public Set<Long> getBookIDsByAuthor(long authorId) throws SQLException {

		Set<Long> ids = new HashSet<>();

		PreparedStatement ps = this.getCon().prepareStatement("SELECT book_id  FROM books WHERE authors_author_id = ?");
		ps.setLong(1, authorId);
		ResultSet result = ps.executeQuery();

		while (result.next()) {
			ids.add(result.getLong(1));

		}

		return ids;
	}

	private void deleteAvatar(long bookId) throws SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT photo FROM books WHERE book_id = ?");
		ps.setLong(1, bookId);
		ResultSet result = ps.executeQuery();

		result.next();
		String photoName = result.getString("photo");

		File avatar = new File(UploadBookServlet.BOOK_IMAGE_URL + photoName);
		avatar.delete();
	}

	public void editBook(long bookId, Book book) throws SQLException {

		boolean isInTransaction = false;

		if (!this.getCon().getAutoCommit()) {
			isInTransaction = true;
		}

		try {

			this.getCon().setAutoCommit(false);
			
			long categoryId = CategoryDAO.getInstance().addCategory(book.getCategory());

			long authorId = AuthorDAO.getInstance().addAuthor(book.getAuthor());

			
			PreparedStatement ps = this.getCon()
					.prepareStatement("UPDATE books "
							+ "SET title = ?, description = ?, year = ?, publisher = ?, price = ?, authors_author_id = ?,"
							+ " categories_category_id = ?, photo = ? WHERE book_id = ?");

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getDescription());
			ps.setInt(3, book.getYear());
			ps.setString(4, book.getPublisher());
			ps.setDouble(5, book.getPrice());
			ps.setLong(6, authorId);
			ps.setLong(7, categoryId);
			ps.setString(8, book.getPhoto());
			ps.setLong(9, bookId);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			this.getCon().rollback();
			throw new SQLException("Can't edit this book", e);
		} finally {
			if (!isInTransaction) {
				this.getCon().commit();
				this.getCon().setAutoCommit(true);
			}
		}

	}
	
	
	public Set<Book> search(String name) throws SQLException, UnexistingException, ValidationException{
		
		PreparedStatement ps = this.getCon().prepareStatement("SELECT b.book_id FROM books as b JOIN authors as a "
				+ "ON(a.author_id = b.authors_author_id) "
				+ "WHERE(b.title LIKE ? OR a.first_name LIKE ? OR a.last_name LIKE ? "
				+ "OR (SELECT CONCAT(a.first_name,' ', a.last_name) as full_name) LIKE ?)");
		
			
		ps.setString(1, "%"+ name + "%");
		ps.setString(2, "%"+ name + "%");
		ps.setString(3, "%"+ name + "%");
		ps.setString(4, "%"+ name + "%");
		
		ResultSet result = ps.executeQuery();
		
		return this.getBooksFromResult(result);
	}
	
	public Set<Book> getAllBooksByCategoryId(long id, String author, int minYear,
		int maxYear, int minPrice, int maxPrice) throws SQLException, UnexistingException, ValidationException{
		
		StringBuilder sb =new StringBuilder();
		
		sb.append("SELECT b.book_id FROM books as b JOIN authors as a ON(a.author_id = b.authors_author_id) "
				+ "WHERE ");
		
		if(id != 0){
			sb.append("categories_category_id = ? AND ");
		}
		
		sb.append("price BETWEEN ? AND ? AND year BETWEEN ? AND ?");
		
		if(author!=null && !author.equals("")){
			sb.append(" AND(a.first_name LIKE ? OR a.last_name LIKE ? OR (SELECT CONCAT(a.first_name,' ',a.last_name) LIKE ?))");
		}
				
		PreparedStatement ps = this.getCon().prepareStatement(sb.toString());
		
		int num = 1;
		if(id != 0){
			ps.setLong(num++, id);
		}
		ps.setInt(num++, Math.min(minPrice, maxPrice));
		ps.setInt(num++, Math.max(minPrice, maxPrice));
		ps.setInt(num++, Math.min(minYear, maxYear));
		ps.setInt(num++, Math.max(minYear, maxYear));
		
		if(author!=null && !author.equals("")){
			ps.setString(num++, "%"+ author + "%");
			ps.setString(num++, "%"+ author + "%");
			ps.setString(num++, "%"+ author + "%");
		}
		
		ResultSet result = ps.executeQuery();
				
		Set<Book> books = new HashSet<>();
		
		while (result.next()) {
			books.add(this.getBook(result.getLong("book_id")));			
		}
//		for (Long bookId : this.getBookIDsByCategory(id)) {
//			try {
//				books.add(this.getBook(bookId));
//			} catch (UnexistingException | ValidationException e) {
//				System.out.println(e.getMessage());
//			}
//		}
		
		return books;
	}

	public long getBookIdByCommentId(long CommentId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT books_book_id FROM comments WHERE comment_id = ?");
		
		ps.setLong(1, CommentId);
		ResultSet result = ps.executeQuery();
		result.next();
		
		return result.getLong(1);
				
	}
}
