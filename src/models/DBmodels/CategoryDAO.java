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

	/* (non-Javadoc)
	 * @see models.DBmodels.ICategoryDAO#addCategory(java.lang.String)
	 */
	@Override
	public long addCategory(String category) throws SQLException{
		if(this.isExist(category)){
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

	/* (non-Javadoc)
	 * @see models.DBmodels.ICategoryDAO#getId(java.lang.String)
	 */
	@Override
	public long getId(String category) throws SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT category_id FROM categories "
																+ "WHERE category_name = ?");

		ps.setString(1, category);

		ResultSet result = ps.executeQuery();

		result.next();
		
		return result.getLong(1);
	}

	/* (non-Javadoc)
	 * @see models.DBmodels.ICategoryDAO#getCategory(long)
	 */
	@Override
	public String getCategory(long id) throws SQLException, UnexistingException{
		PreparedStatement ps = this.getCon().prepareStatement("SELECT category_name FROM categories "
															 + "WHERE category_id = ?");
		
		ps.setLong(1, id);
		ResultSet result = ps.executeQuery();
		if(!result.next()){
			throw new UnexistingException("Category with id: " + id + " doesn't exist!");
		}
		
		return result.getString(1);
	}
	
	/* (non-Javadoc)
	 * @see models.DBmodels.ICategoryDAO#removeCategory(long)
	 */
	@Override
	public void removeCategory(long id) throws SQLException{
		
		this.getCon().setAutoCommit(false);
		
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM books WHERE categories_category_id = ?");
		ps.setLong(1, id);
		ps.executeUpdate();
		
		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM categories WHERE category_id =?");
		ps1.setLong(1, id);
		ps1.executeUpdate();
		
		this.getCon().commit();
		
		this.getCon().setAutoCommit(true);
		
	}
	
	/* (non-Javadoc)
	 * @see models.DBmodels.ICategoryDAO#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String category) throws SQLException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM categories WHERE category_name = ?");

		ps.setString(1, category);

		ResultSet result = ps.executeQuery();

		return result.next();
	}
	
}
