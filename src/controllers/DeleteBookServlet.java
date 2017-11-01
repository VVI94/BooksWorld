package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Validators;
import models.DBmodels.BookDAO;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		long bookId = Long.parseLong(request.getParameter("bookId"));
		
		try {
			BookDAO.getInstance().removeBook(bookId);
						
			response.sendRedirect("./");
			
		} catch (SQLException e) {
			response.sendRedirect("./error404.html");
		}
	}

}
