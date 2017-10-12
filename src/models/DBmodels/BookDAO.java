package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IBookDAO;
import models.entities.Author;
import models.entities.Book;

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

	public void addBook(Book book, long authorId, long categoryId) throws SQLException, AlreadyExistException {
		
		if(this.isExist(book.getTitle(), authorId)){
			throw new AlreadyExistException("This book already exist");
		}

		PreparedStatement ps = this.getCon()
				.prepareStatement("INSERT INTO "
						+ "books(title, description, year, publisher, price, authors_author_id, categories_category_id)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?)");

		ps.setString(1, book.getTitle());
		ps.setString(2, book.getDescription());
		ps.setInt(3, book.getYear());
		ps.setString(4, book.getPublisher());
		ps.setDouble(5, book.getPrice());
		ps.setLong(6, authorId);
		ps.setLong(7, categoryId);

		ps.executeUpdate();

	}

	public void removeBook(long bookId) throws SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM books WHERE book_id = ?");
		ps.setLong(1, bookId);
		ps.executeUpdate();
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
		double price = result.getDouble("price");
		String category = CategoryDAO.getInstance().getCategory(result.getLong("categories_category_id"));

		return new Book(id, title, author, description, year, publisher, price, category);
	}
	
	public Set<Book> getAll() throws SQLException, UnexistingException, ValidationException{
		Set<Book> books = new HashSet<>();
		
		PreparedStatement ps = this.getCon().prepareStatement("SELECT book_id FROM books");
		ResultSet restult = ps.executeQuery();
		
		while (restult.next()) {
			
			long bookId = restult.getLong(1);
			
			books.add(this.getBook(bookId));		
		}
		
		return books;
	}
	
	public boolean isExist(String title, long authorId) throws SQLException{
		
		try {
			Author author = AuthorDAO.getInstance().getAuthor(authorId);
			
			PreparedStatement ps = this.getCon().prepareStatement("SELECT b.title, a.first_name, a.last_name "
					+ "FROM books as b JOIN authors as a ON (b.authors_author_id = a.author_id) "
					+ "WHERE b.title = ? AND a.first_name = ? AND a.last_name = ?;");
			
			ps.setString(1, title);
			ps.setString(2, author.getFirstName());
			ps.setString(3, author.getLaststName());
			
			ResultSet result = ps.executeQuery();
			
			return result.next();
			
		} catch (UnexistingException e) {
			return false;
		}		
	}

}
