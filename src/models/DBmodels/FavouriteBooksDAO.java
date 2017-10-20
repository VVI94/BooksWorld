package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UnexistingException;
import models.DBInterfaces.IFavouriteBooksDAO;
import models.entities.FavouriteBooks;

public class FavouriteBooksDAO extends DAO implements IFavouriteBooksDAO {

	private static IFavouriteBooksDAO instance;

	private FavouriteBooksDAO() {
	}

	public static IFavouriteBooksDAO getInstance() {
		if (instance == null) {
			instance = new FavouriteBooksDAO();
		}

		return instance;
	}
	@Override
	public long addFavouriteBook(FavouriteBooks favourite) throws SQLException {
		try {
			return this.getFavouriteBookId(favourite.getUsers_user_id(), favourite.getUsers_id());
		} catch (UnexistingException e) {

			PreparedStatement ps = this.getCon().prepareStatement(
					"INSERT INTO favourite_books(users_user_id, users_id)" + " VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setLong(1, favourite.getUsers_user_id());
			ps.setLong(2, favourite.getUsers_id());
			ps.executeUpdate();

			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getLong(1);
		}
	}

	@Override
	public void removeFavouriteBook(long favouriteBookId) throws SQLException {
		this.getCon().setAutoCommit(false);
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM favourite_books_has_books WHERE favourite_books_favourite_book_id = ?");
		ps.setLong(1, favouriteBookId);
		ps.executeUpdate();

		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM favourite_books WHERE favourite_book_id = ?");
		ps1.setLong(1, favouriteBookId);
		ps1.executeUpdate();

		this.getCon().commit();

		this.getCon().setAutoCommit(true);

	}

	@Override
	public long getFavouriteBookId(long users_user_id, long user_id) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT favourite_book_id FROM favourite_books" + "	WHERE (users_user_id = ? AND users_id = ?)");

		ps.setLong(1, users_user_id);
		ps.setLong(2, user_id);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("Favourite book with usersId " + users_user_id + " and user " + user_id + " doesn't exists");
		}

		return result.getLong("favourite_book_id");
	}

	@Override
	public FavouriteBooks getFavouriteBook(long favouriteBookId) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM favourite_books WHERE favourite_book_id = ?");
		ps.setLong(1, favouriteBookId);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("Favourite book with id: " + favouriteBookId + " doesn't exist!");
		}
		
		return new FavouriteBooks(favouriteBookId, result.getLong("users_user_id"), result.getLong("users_id"));
	}

}
