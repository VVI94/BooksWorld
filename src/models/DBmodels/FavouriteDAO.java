package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import models.DBInterfaces.IFavouriteDAO;


public class FavouriteDAO extends DAO implements IFavouriteDAO {

	private static IFavouriteDAO instance;

	private FavouriteDAO() {
	}

	public static synchronized IFavouriteDAO getInstance() {
		if (instance == null) {
			instance = new FavouriteDAO();
		}

		return instance;
	}
	@Override
	public void add(long userId, long bookId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("INSERT INTO favourite_books(users_user_id, books_book_id) "
																+ "VALUES(?,?)");
		
		ps.setLong(1, userId);
		ps.setLong(2, bookId);
		ps.executeUpdate();
		
	}
	
	@Override
	public void remove(long userId, long bookId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM favourite_books WHERE (users_user_id =? AND books_book_id = ?)"); 
					
		ps.setLong(1, userId);
		ps.setLong(2, bookId);
		ps.executeUpdate();
	}
	
	@Override
	public Set<Long> getAll(long userId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT books_book_id FROM favourite_books "
				+ "WHERE users_user_id =? "); 
		ps.setLong(1, userId);			;
		ResultSet result = ps.executeQuery();
		Set<Long> ids = new HashSet<>();
		while (result.next()) {
			ids.add(result.getLong("books_book_id"));
			
		}
		
		return ids;
	}
	
	@Override
	public boolean exists(long userId, long bookId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT books_book_id FROM favourite_books "
				+ "WHERE users_user_id =? AND books_book_id =? "); 
		
		ps.setLong(1, userId);			
		ps.setLong(2, bookId);
		ResultSet result = ps.executeQuery();
	
		return result.next();
	}
}
