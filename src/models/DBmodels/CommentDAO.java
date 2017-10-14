package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import exceptions.ValidationException;
import models.entities.User;
import models.entities.comments.Comment;
import models.entities.comments.Reply;

public class CommentDAO extends DAO {

	private static CommentDAO instance;

	private CommentDAO() {
	}

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}

		return instance;
	}
	
	public void addComment(Comment comment, long userId, long bookId) throws SQLException{
		
		PreparedStatement ps = this.getCon()
						.prepareStatement("INSERT INTO comments(content, date, users_user_id, books_book_id) "
						+ "VALUES(?, ?, ?, ?)");
		
		ps.setString(1, comment.getContent());
		ps.setTimestamp(2, comment.getDate());
		ps.setLong(3, userId);
		ps.setLong(4, bookId);
		
		ps.executeUpdate();
	}
	
	public void addReply(Reply comment, long userId, long bookId, long commentId) throws SQLException{
		
		PreparedStatement ps = this.getCon()
				.prepareStatement("INSERT INTO comments(content, date, users_user_id, "
									+ "books_book_id, comments_comment_id )"
									+ "VALUES(?, ?, ?, ?, ?)");
		
		ps.setString(1, comment.getContent());
		ps.setTimestamp(2, comment.getDate());
		ps.setLong(3, userId);
		ps.setLong(4, bookId);
		ps.setLong(5, commentId);
		
		ps.executeUpdate();
	}
	
	
	
	public List<Comment> getAllComments(long bookId) throws SQLException, ValidationException{
		List<Comment> comments = new LinkedList<>();
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM comments "
								+ "WHERE books_book_id = ? AND comments_comment_id IS NULL ORDER BY date");
		ps.setLong(1, bookId);
		
		ResultSet result = ps.executeQuery();
		
		while (result.next()) {
			Comment comment = this.getComment(result);
			comments.add(comment);
		}
		
		return comments;
	}

	private Comment getComment(ResultSet result) throws SQLException, ValidationException {
		long id = result.getLong("comment_id");
		String content = result.getString("content");
		Timestamp date = result.getTimestamp("date");
		List<Reply> replies = this.getAllReplies(id);
		//TODO add user
		User user = new User();
		
		return new Comment(id, content, date, user, replies);
	}
	
	private List<Reply> getAllReplies(long commentId) throws SQLException, ValidationException{
		List<Reply> replies = new LinkedList<>();
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM comments "
								+ "WHERE comments_comment_id = ? ORDER BY date");
		ps.setLong(1, commentId);
		
		ResultSet result = ps.executeQuery();
		
		while (result.next()) {
			Reply comment = this.getReply(result);
			replies.add(comment);
		}
		
		return replies;
	}

	private Reply getReply(ResultSet result) throws SQLException, ValidationException {
		long id = result.getLong("comment_id");
		String content = result.getString("content");
		Timestamp date = result.getTimestamp("date");
		//TODO add user
		User user = new User();
		return new Reply(id, content, date, user);
	}
	
	public void delete(long id) throws SQLException{
		this.getCon().setAutoCommit(false);
		
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM comments WHERE comments_comment_id = ?");
		ps.setLong(1, id);
		ps.executeUpdate();
		
		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM comments WHERE comment_id = ?");
		ps1.setLong(1, id);
		ps1.executeUpdate();
		
		this.getCon().commit();
		this.getCon().setAutoCommit(true);
	}

}