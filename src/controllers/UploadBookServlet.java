package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Author;
import models.entities.Book;

@WebServlet("/UploadBook")
@MultipartConfig
public class UploadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String BOOK_IMAGE_URL = "E:/Final Project Workspace/BooksWorld/WebContent/images/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Validators.isAuthenticated(request, response)) {
			return;
		}

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int year = Integer.parseInt(request.getParameter("year"));
		String publisher = request.getParameter("publisher");
		double price = Double.parseDouble(request.getParameter("price"));
		String category = request.getParameter("category");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Part photo = request.getPart("dataFile");

		String image = title + "_" + firstName + "_" + lastName + ".jpg";
		File myFile = new File(BOOK_IMAGE_URL + image);

		try {

			Author author = new Author(firstName, lastName);

			Book book = new Book(title, description, year, publisher, price, category, author, image);

			if (photo.getSize() > 0) {
				
				validateMimeType(response, photo);
						
				try (InputStream input = photo.getInputStream()) {
					File prevPhoto = new File(BOOK_IMAGE_URL + request.getParameter("bookPhoto"));
					if (prevPhoto.exists()) {
						prevPhoto.delete();
					}
					Files.copy(input, myFile.toPath());
				}
			} else {
				File prevPhoto = new File(BOOK_IMAGE_URL + request.getParameter("bookPhoto"));
				prevPhoto.renameTo(myFile);
			}

			if (request.getParameter("bookId") != null) {

				long bookId = Long.parseLong(request.getParameter("bookId"));
				BookDAO.getInstance().editBook(bookId, book);

			} else {

				BookDAO.getInstance().addBook(book);
			}

			response.sendRedirect("./");

		} catch (ValidationException | SQLException | AlreadyExistException e1) {
			if (myFile.exists()) {
				myFile.delete();
			}
			e1.printStackTrace();
			System.out.println(e1.getMessage());
			response.sendRedirect("./error404.html");
		}
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Validators.isAuthenticated(request, response)) {
			return;
		}

		Book book = null;
		if (request.getParameter("bookId") != null) {
			try {
				book = BookDAO.getInstance().getBook(Long.parseLong(request.getParameter("bookId")));
			} catch (NumberFormatException | SQLException | UnexistingException | ValidationException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				response.sendRedirect("./error404.html");
			}
		}
		
		try {
			Map<String, Long> categories = CategoryDAO.getInstance().getAllCategories();
			
			
			request.setAttribute("categories", categories);
			request.setAttribute("book", book);
			request.setAttribute("view", "bookRegister.jsp");
			request.getRequestDispatcher("base-layout.jsp").forward(request, response);
						
		} catch (SQLException e) {
			response.sendRedirect("./error404.html");
		}


	}
	
	private void validateMimeType(HttpServletResponse response, Part photo) throws IOException {
		
		
		    
		 
		    
		
	}

}
