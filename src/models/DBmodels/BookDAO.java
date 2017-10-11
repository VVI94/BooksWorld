package models.DBmodels;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public void addBook(Book book, int authorId, int categoryId) throws SQLException{
		
		PreparedStatement ps = this.getCon().prepareStatement("INSERT INTO "
					+ "books(title, description, year, publisher, price, authors_author_id, categories_category_id)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getDescription());
		ps.setInt(3, book.getYear());
		ps.setString(4, book.getPublisher());
		ps.setDouble(5, book.getPrice());
		ps.setInt(6, authorId);
		ps.setInt(7, categoryId);
		
		ps.executeUpdate();
		
	}
	
	public void removeBook(int bookId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM books WHERE book_id = ?");
		ps.setInt(1, bookId);
		ps.executeUpdate();
	}
	
	public Book getBook(int bookId) throws SQLException, UnexistingException, ValidationException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM books WHERE book_id = ?");
		ps.setInt(1, bookId);
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("Book with id: " + bookId + " doesn't exists");
		}
		
		int id = result.getInt("book_id");
		String title = result.getString("title");
		//TODO add getAuthor method
		Author author = new Author("Mitko", "Ivanov");
		String description = result.getString("description");
		String publisher = result.getString("publisher");
		int year = result.getInt("year");
		double price = result.getDouble("price");
		//TODO add getAuthor method
		String category = "SCI-FI";
				
		return new Book(title, author, description, year, publisher, price, category);
	}


}
