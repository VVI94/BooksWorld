package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exceptions.AlreadyExistException;
import exceptions.ValidationException;
import models.DBmodels.BookDAO;
import models.entities.Author;
import models.entities.Book;

@WebServlet("/UploadBook")
@MultipartConfig
public class UploadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public static final String BOOK_IMAGE_URL ="E:/Final Project Workspace/BooksWorld/WebContent/images/";

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int year = Integer.parseInt(request.getParameter("year"));
		String publisher = request.getParameter("publisher");
		double price = Double.parseDouble(request.getParameter("price"));
		String category =request.getParameter("category");		
		String firstName = request.getParameter("firstName");
		String lastName =  request.getParameter("lastName");		
		Part photo = request.getPart("image");
		
		String image = title +"_"+ firstName +"_"+ lastName + ".jpg";
		File myFile = new File(BOOK_IMAGE_URL + image);
		
		try (InputStream input = photo.getInputStream()) {
		    Files.copy(input, myFile.toPath());
		}
		
		Author author = null;
		try {
			author = new Author(firstName, lastName);
		} catch (ValidationException e1) {
			System.out.println(e1.getMessage());
		}
		
		
		Book book = null;
		try {
			book = new Book(title, description, year, publisher, price, category, author, image);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
							
		try {
			BookDAO.getInstance().addBook(book);
		} catch (SQLException | AlreadyExistException | ValidationException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect("./");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "bookRegister.html");
		request.getRequestDispatcher("base-layout.jsp").forward(request, response);
	}

}
