import java.sql.SQLException;
import java.util.Set;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.AuthorDAO;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Author;
import models.entities.Book;

public class Demo {

	public static void main(String[] args) throws SQLException, UnexistingException, ValidationException, AlreadyExistException {
		
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
		

	}

}
