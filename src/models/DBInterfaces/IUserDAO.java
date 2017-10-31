package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Role;
import models.entities.User;

public interface IUserDAO {
	
long addUser(User user) throws SQLException;

void removeUser(long userId)throws SQLException;

long getUserID(String username, String email, String fisrtname, String lastname) throws ValidationException, SQLException, UnexistingException;

User getUser(String username) throws ValidationException, SQLException, UnexistingException;

User getUser(Long userId) throws ValidationException, SQLException, UnexistingException;

boolean userExists (String username, String password) throws UnexistingException, SQLException;

public void editUser(long userID, User user) throws SQLException;
}
