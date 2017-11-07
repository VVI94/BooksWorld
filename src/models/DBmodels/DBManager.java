package models.DBmodels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static DBManager instance;

	private Connection connection;

	public String getMAIL_USERNAME() {
		return MAIL_USERNAME;
	}

	public String getMAIL_PASSWORD() {
		return MAIL_PASSWORD;
	}

	public String getMAIL_SMTP_HOST() {
		return MAIL_SMTP_HOST;
	}

	public String getMAIL_REGISTRATION_SITE_LINK() {
		return MAIL_REGISTRATION_SITE_LINK;
	}

	final String MAIL_USERNAME = "books.world.pv@gmail.com"; // like
																// example@outlook.com
	final String MAIL_PASSWORD = "booksWorld"; // your mail password here
	final String MAIL_SMTP_HOST = "smtp.gmail.com"; // smtp.live.com
	final String MAIL_REGISTRATION_SITE_LINK = "http://localhost:8080/BooksWorld/VerifyRegisteredEmailHash";

	private DBManager() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found or falled to load. Check your libraries!");
		}

		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "book_world";
		final String DB_USER = "VVI";
		final String DB_PASSWORD = "VVI*";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME
					+ "?verifyServerCertificate=false" + "&useSSL=false" + "&requireSSL=false", DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Check DBManager");
			e.printStackTrace();
		}
	}

	public synchronized static DBManager getInstance() {

		if (instance == null) {
			instance = new DBManager();
		}

		return instance;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
