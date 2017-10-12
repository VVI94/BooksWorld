package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.UnexistingException;
import models.entities.Author;

public interface IAuthorDAO {

	long addAuthor(Author author) throws SQLException;

	void removeAuthor(long authorId) throws SQLException;

	long getAuthorId(String firstName, String lastName) throws SQLException, UnexistingException;

	Author getAuthor(long authorId) throws SQLException, UnexistingException;

}