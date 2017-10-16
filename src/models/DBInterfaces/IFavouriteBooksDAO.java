package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import models.entities.FavouriteBooks;

public interface IFavouriteBooksDAO {
	long addFavouriteBook(FavouriteBooks favourite) throws SQLException;

	void removeFavouriteBook(long favouriteBookId) throws SQLException;

	long getFavouriteBookId(long users_user_id, long user_id) throws SQLException, UnexistingException;

	FavouriteBooks getFavouriteBook(long favouriteBookId) throws SQLException, UnexistingException;
}
