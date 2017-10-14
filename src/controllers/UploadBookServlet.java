package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.AlreadyExistException;
import exceptions.ValidationException;
import models.DBmodels.AuthorDAO;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Author;
import models.entities.Book;

@WebServlet("/UploadBook")
public class UploadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public UploadBookServlet() {
	        super();
	    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int year = Integer.parseInt(request.getParameter("year"));
		String publisher = request.getParameter("publisher");
		double price = Double.parseDouble(request.getParameter("price"));
		
		
		Book book = null;
		try {
			book = new Book(title, description, year, publisher, price);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String firstName = request.getParameter("firstName");
		String lastName =  request.getParameter("lastName");
		
		Author author = null;
		try {
			author = new Author(firstName, lastName);
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		long authorId = 0;
		try {
			authorId = AuthorDAO.getInstance().addAuthor(author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String category =request.getParameter("category");
		
		long categoryId = 0;
		try {
			categoryId = CategoryDAO.getInstance().addCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BookDAO.getInstance().addBook(book, authorId, categoryId);
		} catch (SQLException | AlreadyExistException | ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("a.jsp");
		
	}

}
