package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Role;
import models.entities.User;

public interface IUserDAO {
	
long addUser(User user, long roleId) throws SQLException;

void removeUser(long userId)throws SQLException;

long getUserID(String username, String email, String fisrtname, String lastname) throws ValidationException, SQLException, UnexistingException;

User getUser(long userId) throws ValidationException, SQLException, UnexistingException;

boolean userExists (String username, String password) throws UnexistingException, SQLException;
}
