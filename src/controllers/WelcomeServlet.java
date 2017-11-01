package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.UnknownSortException;
import exceptions.ValidationException;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Book;

@WebServlet("")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String sortBy = request.getParameter("sortBy");
			if (request.getAttribute("sortBooks") == null) {
				Set<Book> books = BookDAO.getInstance().getAll();
				if (sortBy == null) {
					request.setAttribute("books", books);
				} else {
					Set<Book> sortedBooks = SortServlet.getSortSetBy(sortBy);
					sortedBooks.addAll(books);
					request.setAttribute("books", sortedBooks);					
				}
			} else {
				request.setAttribute("books", request.getAttribute("sortBooks"));
			}

			if (request.getAttribute("markCategories") != null) {
				request.setAttribute("markCategories", request.getAttribute("markCategories"));
			} else {
				request.setAttribute("markCategories", new HashSet<>());
			}

			Map<String, Long> categories = CategoryDAO.getInstance().getAllCategories();
			request.setAttribute("categories", categories);

			request.setAttribute("view", "index.jsp");
			request.getRequestDispatcher("base-layout.jsp").forward(request, response);

		} catch (SQLException | UnexistingException | ValidationException | UnknownSortException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			response.sendRedirect("./error404.html");
		}

	}

}
