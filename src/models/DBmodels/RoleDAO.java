package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UnexistingException;
import models.DBInterfaces.IRoleDAO;
import models.entities.Role;

public class RoleDAO extends DAO implements IRoleDAO {

	private static IRoleDAO instance;

	private RoleDAO() {
	}

	public static IRoleDAO getInstance() {

		if (instance == null) {
			instance = new RoleDAO();
		}

		return instance;
	}

	@Override
	public long addRole(Role role) throws SQLException {
				
//		try {
//			return this.getRoleId(role.getRoleName());
//		} catch (UnexistingException e) {
			
			PreparedStatement ps = this.getCon().prepareStatement("INSERT INTO roles(role)"
												  + " VALUES(?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,role.getRoleName());
			ps.executeUpdate();
			
			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getLong(1);
		}
//	}

	@Override
	public long getRoleId(String roleName) throws SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT id FROM roles " + "WHERE role = ?");

		ps.setString(1, roleName);

		ResultSet result = ps.executeQuery();

		result.next();

		return result.getLong(1);
	}

	@Override
	public Role getRole(long id) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT role FROM roles " + "WHERE role_id = ?");

		ps.setLong(1, id);
		ResultSet result = ps.executeQuery();
		if (!result.next()) {
			throw new UnexistingException("Role with id: " + id + " doesn't exist!");
		}

		return new Role(id, result.getString(1));
	}

	@Override
	public void removeRole(long id) throws SQLException {
		this.getCon().setAutoCommit(false);

		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM users WHERE roles_id = ?");
		ps.setLong(1, id);
		ps.executeUpdate();

		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM roles WHERE id =?");
		ps1.setLong(1, id);
		ps1.executeUpdate();

		this.getCon().commit();

		this.getCon().setAutoCommit(true);

	}
}
