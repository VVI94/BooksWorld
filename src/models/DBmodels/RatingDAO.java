package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DBInterfaces.IRatingDAO;

public class RatingDAO extends DAO implements IRatingDAO {
	
	private static IRatingDAO instance;

	private RatingDAO() {
	}

	public static synchronized IRatingDAO getInstance() {
		if (instance == null) {
			instance = new RatingDAO();
		}

		return instance;
	}

	
	public void add(long userId, long bookId, int rating) throws SQLException{
		
		PreparedStatement ps = this.getCon().prepareStatement("INSERT INTO ratings(users_user_id, books_book_id, rating) "
															+ "VALUES(?, ?, ?)");
		ps.setLong(1, userId);
		ps.setLong(2, bookId);
		ps.setInt(3, rating);
		
		ps.executeUpdate();
	}
	
	public void edit(long userId, long bookId, int rating) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("UPDATE ratings SET rating = ? "
																+ "WHERE (users_user_id = ? AND books_book_id = ?)");
		
		ps.setInt(1, rating);
		ps.setLong(2, userId);
		ps.setLong(3, bookId);

		ps.executeUpdate();
		
	}
	
	public int get (long userId, long bookId)throws SQLException{
		
		PreparedStatement ps = this.getCon().prepareStatement("SELECT rating FROM ratings "
				+ "WHERE (users_user_id = ? AND books_book_id = ?)");

		
			ps.setLong(1, userId);
			ps.setLong(2, bookId);
			
			ResultSet result = ps.executeQuery();
			if(result.next()){
				return result.getInt("rating");
			}
		
			return 0;
		
		
	}
	
	public boolean exicts(long userId, long bookId)throws SQLException{
	
		PreparedStatement ps = this.getCon().prepareStatement("SELECT rating FROM ratings "
				+ "WHERE (users_user_id = ? AND books_book_id = ?)");

		
			ps.setLong(1, userId);
			ps.setLong(2, bookId);
			
			ResultSet result = ps.executeQuery();
			
				return result.next();
	
	}
	
	public int getAverage(long bookId)throws SQLException{
		
		PreparedStatement ps = this.getCon().prepareStatement("SELECT AVG(rating) FROM ratings "
				+ "WHERE books_book_id = ?");
		
			ps.setLong(1, bookId);
			
			ResultSet result = ps.executeQuery();
			
			result.next();
			
			return result.getInt(1);
		
	}
	
}
