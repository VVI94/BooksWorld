package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
				if (request.getParameter("empty") == null) {
					Set<Book> books = BookDAO.getInstance().getAll();
					if (sortBy == null) {
						request.setAttribute("books", books);
					} else {
						SortServlet.sortAndSet(request, sortBy, books, "books");
					}
				} else {
					request.setAttribute("books", new HashSet<Book>());
					request.setAttribute("author", request.getParameter("author"));
					request.setAttribute("minYear", request.getParameter("minYear"));
					request.setAttribute("maxYear", request.getParameter("maxYear"));
					request.setAttribute("minPrice", request.getParameter("minPrice"));
					request.setAttribute("maxPrice", request.getParameter("maxPrice"));

					
				}
			} else {
				request.setAttribute("books", request.getAttribute("sortBooks"));
			}

			if (request.getAttribute("markCategories") != null) {
				request.setAttribute("markCategories", request.getAttribute("markCategories"));
			} else {
				if (request.getParameter("empty") == null) {
					request.setAttribute("markCategories", new HashSet<>());
				}else{
					Map<String, Long> categories1 = CategoryDAO.getInstance().getAllCategories();
					Set<String> markCategories = new HashSet<>();
					for (Entry<String, Long> entry : categories1.entrySet()) {
						if(request.getParameter(entry.getKey())!=null){
							markCategories.add(entry.getKey());
						}
					}

					request.setAttribute("markCategories", markCategories);
				}
			}

			Map<String, Long> categories = CategoryDAO.getInstance().getAllCategories();
			request.setAttribute("categories", categories);

			request.setAttribute("sortBy", sortBy);
			request.setAttribute("view", "index.jsp");
			request.getRequestDispatcher("base-layout.jsp").forward(request, response);

		} catch (SQLException | UnexistingException | ValidationException | UnknownSortException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			response.sendRedirect("./error404.html");
		}

	}

}
