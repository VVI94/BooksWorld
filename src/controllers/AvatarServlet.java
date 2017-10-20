package controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.BookDAO;
import models.entities.Book;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("book");
		Book book = null;
		try {
			book = BookDAO.getInstance().getBook(Long.parseLong(id));
		} catch (NumberFormatException | SQLException | UnexistingException | ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String avatar = book.getPhoto();

		File myFile = new File(UploadBookServlet.BOOK_IMAGE_URL+avatar);
		
		try (OutputStream out = response.getOutputStream()) {
		    Path path = myFile.toPath();
		    Files.copy(path, out);
		    out.flush();
		} catch (IOException e) {
		   System.out.println("ops");
		}
	}


}
