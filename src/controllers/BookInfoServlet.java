package controllers;

import java.io.IOException;
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

@WebServlet("/BookInfo")
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("book");
		Book book = null;
		try {
			book = BookDAO.getInstance().getBook(Long.parseLong(id));
			
			request.setAttribute("book", book);
			request.setAttribute("view", "BookDescription.jsp");
			request.getRequestDispatcher("base-layout.jsp").forward(request, response);
			
		} catch (NumberFormatException | SQLException | UnexistingException | ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}


}
