package models.DBInterfaces;

import java.sql.SQLException;
import java.util.Set;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Book;

public interface IBookDAO {

	void addBook(Book book, long authorId, long categoryId) throws SQLException, AlreadyExistException, ValidationException ;
	
	void removeBook(long bookId) throws SQLException;
	
	Book getBook(long bookId) throws SQLException, UnexistingException, ValidationException;
	
	Set<Book> getAll() throws SQLException, UnexistingException, ValidationException;
	
	boolean exist(String title, long authorId) throws SQLException, ValidationException;
}
