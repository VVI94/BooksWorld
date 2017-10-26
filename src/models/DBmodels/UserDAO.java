package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IUserDAO;
import models.entities.Author;
import models.entities.Role;
import models.entities.User;

public class UserDAO extends DAO implements IUserDAO {

	private static IUserDAO instance;

	private UserDAO() {
	}

	public static IUserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}

		return instance;
	}

	@Override
	public long addUser(User user, long roleId) throws SQLException {

		try {
			return this.getUserID(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname());
		} catch (UnexistingException e) {

			PreparedStatement ps = this.getCon().prepareStatement(
					"INSERT INTO users(username, address, password, first_name, last_name, email, telephone, roles_role_id)"
							+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getAddress());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getLastname());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getTelephone());
			ps.setLong(8, roleId);
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
	public User getUser(long userId) throws ValidationException, SQLException, UnexistingException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM users WHERE user_id = ?");
		ps.setLong(1, userId);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("User with id: " + userId + " doesn't exist!");
		}
		Role role = RoleDAO.getInstance().getRole(result.getLong("roles_role_id"));
		
		return new User(userId, result.getString("username"), result.getString("address"),result.getString("password"), result.getString("first_name"), result.getString("last_name"), result.getString("email"),  result.getString("telephone"),role );
	}

	@Override
	public boolean userExists(String username, String password) throws UnexistingException, SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
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

}
