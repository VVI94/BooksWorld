package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.BookDAO;
import models.entities.Book;

@WebServlet("")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		try {
			Set<Book> books = BookDAO.getInstance().getAll();
			request.setAttribute("books", books);
		} catch (SQLException | UnexistingException | ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		request.setAttribute("view", "index.jsp");
		request.getRequestDispatcher("base-layout.jsp").forward(request, response);
		
	}


}
