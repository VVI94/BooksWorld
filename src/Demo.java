import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.AuthorDAO;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.DBmodels.CommentDAO;
import models.DBmodels.RoleDAO;
import models.DBmodels.UserDAO;
import models.entities.Author;
import models.entities.Book;
import models.entities.Role;
import models.entities.User;
import models.entities.comments.Comment;
import models.entities.comments.Reply;

public class Demo {

	public static void main(String[] args) throws SQLException, UnexistingException, ValidationException, AlreadyExistException {
		
		
		System.out.println("Работи ли?");
//		AuthorDAO.getInstance().removeAuthor(38);
		
//		java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
//		
//		Comment comment = new Comment("Super cool", date);
//		
//		CommentDAO.getInstance().addComment(comment, 1, 75);
//		
//		java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
//		
//		Reply reply = new Comment("My \"Me again\" reply", date);
//		
//		CommentDAO.getInstance().addReply(reply, 1, 75, 18);
		
		
//		BookDAO.getInstance().removeBook(37);
		
//		List<Comment> comments = CommentDAO.getInstance().getAllComments(10);
//		
//		for (Comment comment : comments) {
//			System.out.println(comment.getContent());
//			
//			System.out.println();
//		}
		
//		CommentDAO.getInstance().delete(10);
//		
//		
//		Book book = BookDAO.getInstance().getBook(1);
//		for (Comment comment : book.getComments()) {
//			System.out.println(comment.getContent());
//			for (Reply reply : comment.getReplyComments() ) {
//				System.out.println(reply.getContent());
//			}
//			System.out.println();
//		}
		
//		try {
//			
//			Author author = new Author("Gosho1", "Ivanov");
//			
//			Book book = new Book("Gladiator1", author ,
//								"Very good book", 1989, "Pegas", 34.23, "SCI-Fi");
//			
//			long id1 = CategoryDAO.getInstance().addCategory("Action");
//			long id = AuthorDAO.getInstance().addAuthor(author);
//			BookDAO.getInstance().addBook(book, id, id1);
//			
//		} catch (ValidationException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Set<Book> books = BookDAO.getInstance().getAll();
//		Book book = BookDAO.getInstance().getBook(7);
//		int a = 0;
		//System.out.println(AuthorDAO.getInstance().getAuthorId("Mitko", "Ivanov"));
		
//		CategoryDAO.getInstance().removeCategory(1);
		
//		try {
//			AuthorDAO.getInstance().removeAuthor(2);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		try {
//			BookDAO.getInstance().removeBook(2);
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		
//		
//		try {
//			Book book =BookDAO.getInstance().getBook(2);
//			int a = 0;
//		} catch (SQLException | UnexistingException | ValidationException e) {
//			System.out.println(e.getMessage());
//		}
		
//		BookDAO.getInstance().removeBook(18);
		
//		CategoryDAO.getInstance().removeCategory(4);
		
//		User user = UserDAO.getInstance().getUser(1);
//		
//		Book book = BookDAO.getInstance().getBook(18);
//		Role role = new Role("Admin");
////		
//		long id = RoleDAO.getInstance().addRole(role);
//		User user = new User("Vasilena", "sofia sofia", "1234561", "Vasilena", "Ilieva", "vasilena@abv.bg", "0886 111 2222", role);
//		UserDAO.getInstance().addUser(user, id);
		
		System.out.println( "aaaaaa");
	}

}
