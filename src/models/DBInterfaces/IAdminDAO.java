package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.User;

public class IAdminDAO implements IUserDAO {

	@Override
	public long addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeUser(long userId) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getUserID(String username, String email, String fisrtname, String lastname)
			throws ValidationException, SQLException, UnexistingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUser(long userId) throws ValidationException, SQLException, UnexistingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userExists(String username, String password) throws UnexistingException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String username) throws ValidationException, SQLException, UnexistingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editUser(long userID, User user) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
