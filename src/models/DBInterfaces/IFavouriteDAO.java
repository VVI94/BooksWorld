package models.DBInterfaces;

import java.sql.SQLException;
import java.util.Set;


public interface IFavouriteDAO {
	
	public void add(long userId, long bookId) throws SQLException;
	
	public void remove(long userId, long bookId) throws SQLException;
	
	public Set<Long> getAll(long userId) throws SQLException;

	boolean exists(long userId, long bookId) throws SQLException;
}
