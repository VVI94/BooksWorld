package models.DBInterfaces;

import java.sql.SQLException;
import java.util.Map;

import exceptions.UnexistingException;

public interface ICategoryDAO {

	long addCategory(String category) throws SQLException;

	long getId(String category) throws SQLException;

	String getCategory(long id) throws SQLException, UnexistingException;

	void removeCategory(long id) throws SQLException;

	boolean exist(String category) throws SQLException;
	
	Map<String, Long> getAllCategories() throws SQLException;
	
	public Map<String, Long> getAllFavourites(long userId) throws SQLException;
}