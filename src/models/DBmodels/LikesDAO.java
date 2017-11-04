package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.enums.LikeType;

public class LikesDAO extends DAO {

	private static LikesDAO instance;

	private LikesDAO() {
	}

	public static synchronized LikesDAO getInstance() {
		if (instance == null) {
			instance = new LikesDAO();
		}

		return instance;
	}

	public int get(long commentId, LikeType type) throws SQLException {

		PreparedStatement ps = this.getCon().prepareStatement("SELECT COUNT(*)FROM likes WHERE comments_comment_id = ? AND type = ?");

		ps.setLong(1, commentId);
		ps.setString(2, type.name());

		ResultSet result = ps.executeQuery();

		return result.next() ? result.getInt(1) : 0;

	}

	public void add(long commentId, LikeType type, long userId) throws SQLException {

		PreparedStatement ps = this.getCon().prepareStatement(
				"INSERT INTO likes" + "(type, comments_comment_id, users_user_id) " + "VALUES(?, ?, ?)");

		ps.setString(1, type.name());
		ps.setLong(2, commentId);		
		ps.setLong(3, userId);
		
		ps.executeUpdate();

	}
	
	public void remove(long commentId, LikeType type, long userId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM likes"
				+ " WHERE comments_comment_id = ? AND type = ? AND users_user_id = ?");
		
		ps.setLong(1, commentId);
		ps.setString(2, type.name());		
		ps.setLong(3, userId);
		
		ps.executeUpdate();
		
	}
	
	public boolean exists(long commentId, LikeType type, long userId) throws SQLException{
		PreparedStatement ps = this.getCon().prepareStatement(
				"SELECT id FROM likes "
				+ "WHERE comments_comment_id = ? AND type = ? AND users_user_id = ?");

		ps.setLong(1, commentId);
		ps.setString(2, type.name());
		ps.setLong(3, userId);
		
		ResultSet result = ps.executeQuery();
		return result.next();
	}
	
}
