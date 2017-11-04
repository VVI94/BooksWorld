package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IUserDAO;
import models.entities.User;

public class UserDAO extends DAO implements IUserDAO {

	private static IUserDAO instance;

	private UserDAO() {
	}

	public static synchronized IUserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}

		return instance;
	}

	@Override
	public long addUser(User user) throws SQLException {

		try {
			return this.getUserID(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname());
		} catch (UnexistingException e) {

			PreparedStatement ps = this.getCon().prepareStatement(
					"INSERT INTO users(username, password,email, first_name, last_name, address, telephone, roles_role_id, userAvatar)"
							+ " VALUES(?, sha1(?), ?, ?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			ps.setString(1, user.getUsername());			
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getLastname());
			ps.setString(6, user.getAddress());			
			ps.setString(7, user.getTelephone());
			ps.setLong(8, user.getRoleID());
			ps.setString(9, user.getUserAvatar());
			ps.executeUpdate();

			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getLong(1);
		}
	}

	@Override
	public void removeUser(long userId) throws SQLException {
		this.getCon().setAutoCommit(false);
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM favourite_books WHERE users_user_id = ?");
		ps.setLong(1, userId);
		ps.executeUpdate();

		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM orders WHERE users_user_id = ?");
		ps1.setLong(1, userId);
		ps1.executeUpdate();

		PreparedStatement ps2 = this.getCon().prepareStatement("DELETE FROM users WHERE user_id = ?");
		ps2.setLong(1, userId);
		ps2.executeUpdate();

		this.getCon().commit();

		this.getCon().setAutoCommit(true);

	}

	@Override
	public long getUserID(String username, String email, String firstname, String lastname) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT user_id FROM users" + "	WHERE (username = ? AND email = ? AND first_name = ? AND last_name = ?)");

		ps.setString(1, username);
		ps.setString(2, email);
		ps.setString(3, firstname);
		ps.setString(4, lastname);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("User " + firstname + " " + lastname + " doesn't exists");
		}

		return result.getLong("user_id");

	}

	@Override
	public User getUser(String username) throws ValidationException, SQLException, UnexistingException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM users WHERE username = ?");
		ps.setString(1, username);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("User with username: " + username + " doesn't exist!");
		}
		long userId = UserDAO.getInstance().getUserID(username, result.getString("email"), result.getString("first_name"),result.getString("last_name"));
		//Role role = RoleDAO.getInstance().getRole(result.getLong("roles_role_id"));
		
		return new User(userId, result.getString("username"),result.getString("password"), result.getString("email"), result.getString("first_name"), result.getString("last_name"), result.getString("address"),  result.getString("telephone"), result.getString("userAvatar") );
	}

	@Override
	public boolean userExists(String username, String password) throws UnexistingException, SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM users WHERE username = ? AND password = sha1(?)");
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet result = ps.executeQuery();
		boolean exists = true;
		
		if(!result.next()){
			exists = false;
			throw new UnexistingException("User with " + username + " or password doesn't exist!");
		}
		return exists;
	}
	
	public void editUser(long userID, User user) throws SQLException {

		boolean isInTransaction = false;

		if (!this.getCon().getAutoCommit()) {
			isInTransaction = true;
		}

		try {

			this.getCon().setAutoCommit(false);
			
			PreparedStatement ps = this.getCon()
					.prepareStatement("UPDATE users "
							+ "SET password = sha1(?), email = ?,first_name = ?, last_name = ?, address=?, telephone = ?, roles_role_id = ?, userAvatar = ?"
							+ " WHERE user_id = ?");

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getTelephone());
			ps.setLong(7, user.getRoleID());
			ps.setString(8, user.getUserAvatar());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException("Can't edit this user!", e);
		} finally {
			if (!isInTransaction) {
				this.getCon().commit();
				this.getCon().setAutoCommit(true);
			}
		}

	}

	@Override
	public User getUser(long userId) throws ValidationException, SQLException, UnexistingException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM users WHERE user_id = ?");
		ps.setLong(1, userId);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("User with user id: " + userId + " doesn't exist!");
		}
		
		return new User(userId, result.getString("username"),result.getString("password"), result.getString("email"), result.getString("first_name"), result.getString("last_name"), result.getString("address"),  result.getString("telephone"), result.getString("userAvatar") );
	}

}
