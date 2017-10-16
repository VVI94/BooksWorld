package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import models.entities.Role;

public interface IRoleDAO {

	long addRole(Role role) throws SQLException, UnexistingException;

	long getRoleId(String roleName) throws SQLException;

	Role getRole(long id) throws SQLException, UnexistingException;

	void removeRole(long id) throws SQLException;
}
