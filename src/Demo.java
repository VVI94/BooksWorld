import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.BookDAO;
import models.entities.Author;
import models.entities.Book;

public class Demo {

	public static void main(String[] args) {
		
//		try {
//			Book book = new Book("Gladiator", new Author("Mitko", "Ivanov"),
//								"Very good book", 1989, "Pegas", 34.23, "SCI-Fi");
//			
//			BookDAO.getInstance().addBook(book, 1, 1);
//			
//		} catch (ValidationException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		try {
			BookDAO.getInstance().removeBook(2);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		try {
			Book book =BookDAO.getInstance().getBook(2);
			int a = 0;
		} catch (SQLException | UnexistingException | ValidationException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
