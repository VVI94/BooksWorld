package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IAdminDAO;
import models.entities.Book;
import models.entities.User;
import models.entities.comments.Comment;
import models.entities.comments.Reply;

public class AdminDAO extends DAO implements IAdminDAO {

	private static IAdminDAO instance;

	private AdminDAO() {
	}

	public static synchronized IAdminDAO getInstance() {
		if (instance == null) {
			instance = new AdminDAO();
		}

		return instance;
	}

	@Override
	public void addAdmin(String username) throws SQLException, ValidationException, UnexistingException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT * FROM users" + "	WHERE username = ?");

		ps.setString(1, username);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("User with username: " + username + " doesn't exists");
		}
		long role = result.getLong("roles_role_id");
		User user = new User(result.getLong(""), username ,result.getString("password"), result.getString("email"), result.getString("first_name"), result.getString("last_name"), result.getString("address"),  result.getString("telephone"), result.getString("userAvatar"), role );
		user.setRoleID(2L);

		
	}

	@Override
	public void removeUser(long userId) throws SQLException {
		UserDAO.getInstance().removeUser(userId);
	}

	@Override
	public void addBook(Book book) throws SQLException, AlreadyExistException, ValidationException {
		BookDAO.getInstance().addBook(book);
	}

	@Override
	public void editBook(long bookId, Book book) throws SQLException {
		BookDAO.getInstance().editBook(bookId, book);
	}

	@Override
	public void removeBook(long bookId) throws SQLException, UnexistingException, ValidationException {
		BookDAO.getInstance().removeBook(bookId);
		
	}

	@Override
	public void addCategory(String category) throws SQLException {
		CategoryDAO.getInstance().addCategory(category);
		
	}

	@Override
	public void removeCategory(long id) throws SQLException {
		CategoryDAO.getInstance().removeCategory(id);
		
	}

	@Override
	public void addComment(Comment comment, long userId, long bookId) throws SQLException {
		CommentDAO.getInstance().addComment(comment, userId, bookId);
		
	}

	@Override
	public void addReply(Reply comment, long userId, long bookId, long commentId) throws SQLException {
		CommentDAO.getInstance().addReply(comment, userId, bookId, commentId);
	}

	@Override
	public void deleteComment(long id) throws SQLException {
		CommentDAO.getInstance().delete(id);
		
	}

}
