package models.DBInterfaces;

import java.sql.SQLException;

public interface IRatingDAO {
	void edit(long userId, long bookId, int rating) throws SQLException;
	
	public int get (long userId, long bookId)throws SQLException;
	
	void add(long userId, long bookId, int rating) throws SQLException;
	
	boolean exicts(long userId, long bookId)throws SQLException;
	
	int getAverage(long bookId)throws SQLException;
}
