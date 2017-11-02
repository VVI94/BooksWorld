package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IAuthorDAO;
import models.entities.Author;

public class AuthorDAO extends DAO implements IAuthorDAO {

	private static IAuthorDAO instance;

	private AuthorDAO() {
	}

	public static synchronized IAuthorDAO getInstance() {
		if (instance == null) {
			instance = new AuthorDAO();
		}

		return instance;
	}
	
	@Override
	public long addAuthor(Author author) throws SQLException{
		
		try {
			return this.getAuthorId(author.getFirstName(), author.getLastName());
		} catch (UnexistingException e) {
			
			PreparedStatement ps = this.getCon().prepareStatement("INSERT INTO authors(first_name, last_name)"
												  + " VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, author.getFirstName());
			ps.setString(2, author.getLastName());
			ps.executeUpdate();
			
			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getLong(1);
		}
	}
	
	@Override
	public void removeAuthor(long authorId) throws SQLException{
		
		boolean isInTransaction = false;

		if (!this.getCon().getAutoCommit()) {
			isInTransaction = true;
		}
		
		try{
		this.getCon().setAutoCommit(false);
	
		for (long bookId : BookDAO.getInstance().getBookIDsByAuthor(authorId)) {
			BookDAO.getInstance().removeBook(bookId);
		}
		
		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM authors WHERE author_id = ?");
		ps1.setLong(1, authorId);
		ps1.executeUpdate();
		}catch (SQLException e) {
			this.getCon().rollback();
			throw new SQLException("Can't remove this author",e);
		}finally {			
			if(!isInTransaction){
				this.getCon().commit();				
				this.getCon().setAutoCommit(true);
			}
		}
		
	}
	
	@Override
	public long getAuthorId(String firstName, String lastName) throws SQLException, UnexistingException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT author_id FROM authors"
																+ "	WHERE (first_name = ? AND last_name = ?)");
		
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("Author " + firstName + " " + lastName + " doesn't exists");
		}
			
		return result.getLong("author_id");
	}
	
	@Override
	public Author getAuthor(long authorId) throws SQLException, UnexistingException, ValidationException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM authors WHERE author_id = ?");
		ps.setLong(1, authorId);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("Author with id: " + authorId + " doesn't exist!");
		}
		
		return new Author(authorId, result.getString("first_name"), result.getString("last_name"));
		
	}
}
