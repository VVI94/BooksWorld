package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UnexistingException;
import models.DBInterfaces.ICategoryDAO;

public class CategoryDAO extends DAO implements ICategoryDAO {

	private static ICategoryDAO instance;

	private CategoryDAO() {
	}

	public static ICategoryDAO getInstance() {

		if (instance == null) {
			instance = new CategoryDAO();
		}

		return instance;
	}

	@Override
	public long addCategory(String category) throws SQLException {
		
		if (this.exist(category)) {
			return this.getId(category);
		}

		PreparedStatement ps = this.getCon().prepareStatement("INSERT INTO categories(category_name) VALUES(?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, category);
		ps.executeUpdate();

		ResultSet id = ps.getGeneratedKeys();
		id.next();
		return id.getLong(1);
	}

	@Override
	public long getId(String category) throws SQLException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT category_id FROM categories " + "WHERE category_name = ?");

		ps.setString(1, category);

		ResultSet result = ps.executeQuery();

		result.next();

		return result.getLong(1);
	}

	@Override
	public String getCategory(long id) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT category_name FROM categories " + "WHERE category_id = ?");

		ps.setLong(1, id);
		ResultSet result = ps.executeQuery();
		if (!result.next()) {
			throw new UnexistingException("Category with id: " + id + " doesn't exist!");
		}

		return result.getString(1);
	}

	@Override
	public void removeCategory(long id) throws SQLException {

		boolean isInTransaction = false;

		if (!this.getCon().getAutoCommit()) {
			isInTransaction = true;
		}

		try {

			this.getCon().setAutoCommit(false);

			for (Long bookId : BookDAO.getInstance().getBookIDsByCategory(id)) {
				BookDAO.getInstance().removeBook(bookId);
			}

			PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM categories WHERE category_id =?");
			ps1.setLong(1, id);
			ps1.executeUpdate();

		} catch (SQLException e) {
			this.getCon().rollback();
			throw new SQLException("Can't remove this category", e);
		} finally {
			if (!isInTransaction) {
				this.getCon().commit();
				this.getCon().setAutoCommit(true);
			}
		}

	}

	@Override
	public boolean exist(String category) throws SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM categories WHERE category_name = ?");

		ps.setString(1, category);

		ResultSet result = ps.executeQuery();

		return result.next();
	}

}
