package models.DBInterfaces;

import java.sql.SQLException;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Book;
import models.entities.comments.Comment;
import models.entities.comments.Reply;

public interface IAdminDAO  {
	
	public void addAdmin(String username) throws SQLException, ValidationException, UnexistingException;

	public void removeUser(long userId) throws SQLException;
	
	public void addBook(Book book) throws SQLException, AlreadyExistException, ValidationException;
	
	public void editBook(long bookId, Book book) throws SQLException;
	
	public void removeBook(long bookId) throws SQLException, UnexistingException, ValidationException;
	
	public void addCategory(String category) throws SQLException;
	
	public void removeCategory(long id) throws SQLException;
	
	public void addComment(Comment comment, long userId, long bookId) throws SQLException;
	
	public void addReply(Reply comment, long userId, long bookId, long commentId) throws SQLException;
	
	public void deleteComment(long id) throws SQLException;

}
