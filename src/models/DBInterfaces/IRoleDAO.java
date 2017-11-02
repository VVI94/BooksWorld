package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Role;
import models.enums.UserRole;

public interface IRoleDAO {

	long addRole(Role role) throws SQLException, UnexistingException;

	long getRoleId(UserRole roleName) throws SQLException;

	Role getRole(long id) throws SQLException, UnexistingException, ValidationException;

	void removeRole(long id) throws SQLException;
}
