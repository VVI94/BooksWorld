package models.DBmodels;

import java.sql.Connection;

public abstract class DAO {
	
	private Connection con = DBManager.getInstance().getConnection();

	protected Connection getCon() {
		return con;
	}
}
