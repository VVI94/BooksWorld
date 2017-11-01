package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnknownSortException;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Book;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder sb = new StringBuilder();
		try {
			String sortBy = request.getParameter("sortBy");

			Map<String, Long> categories = CategoryDAO.getInstance().getAllCategories();
			Set<Book> books = new HashSet<>();
			Set<String> markCategories = new HashSet<>();
			for (Entry<String, Long> entry : categories.entrySet()) {
				String isCheck = request.getParameter(entry.getKey());
				if (isCheck != null) {
					books.addAll(BookDAO.getInstance().getAllBooksByCategoryId(entry.getValue()));
					markCategories.add(entry.getKey());
					sb.append(entry.getKey()).append("=true&");
				}
			}

			if (books.isEmpty()) {
				if (sortBy == null) {
					response.sendRedirect("./");
					return;
				}
				response.sendRedirect("./?sortBy=" + sortBy);
				return;

			} else {

				if (sortBy != null) {

					sortAndSet(request, sortBy, books, "sortBooks");
					
				} else {
					request.setAttribute("sortBooks", books);
				}

				request.setAttribute("url", sb.toString());
				request.setAttribute("markCategories", markCategories);
				request.setAttribute("sortBy", sortBy);
				request.getRequestDispatcher("/").forward(request, response);
			}

		} catch (SQLException | UnknownSortException e) {
			response.sendRedirect("./error404.html");
		}

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	private static Set<Book> getSortSetByDesc(String sortBy) throws UnknownSortException {
		switch (sortBy.substring(0, sortBy.length()-4)) {
		case "title":
			return new TreeSet<Book>((b, a) -> {
				if (a.getTitle().equals(b.getTitle())) {
					return a.getAuthor().compareTo(b.getAuthor());
				}
				return a.getTitle().compareTo(b.getTitle());
			});
		case "price":
			return new TreeSet<Book>((b, a) -> {

				if (a.getPrice() == b.getPrice()) {
					if (a.getTitle().equals(b.getTitle())) {
						return a.getAuthor().compareTo(b.getAuthor());
					}
					return a.getTitle().compareTo(b.getTitle());
				}
				return (int) (a.getPrice() * 200 - b.getPrice() * 200);
			});
		case "year":
			return new TreeSet<Book>((b, a) -> {

				if (a.getYear() == b.getYear()) {
					if (a.getTitle().equals(b.getTitle())) {
						return a.getAuthor().compareTo(b.getAuthor());
					}
					return a.getTitle().compareTo(b.getTitle());
				}
				return a.getYear() - b.getYear();

			});
		case "publisher":
			return new TreeSet<Book>((b, a) -> {

				if (a.getPublisher().equals(b.getPublisher())) {
					if (a.getTitle().equals(b.getTitle())) {
						return a.getAuthor().compareTo(b.getAuthor());
					}
					return a.getTitle().compareTo(b.getTitle());
				}

				return a.getPublisher().compareTo(b.getPublisher());

			});
		}

		throw new UnknownSortException("Can't sort by " + sortBy);
	}

	private static Set<Book> getSortSetBy(String sortBy) throws UnknownSortException {
		switch (sortBy) {
		case "title":
			return new TreeSet<Book>((a, b) -> {
				if (a.getTitle().equals(b.getTitle())) {
					return a.getAuthor().compareTo(b.getAuthor());
				}
				return a.getTitle().compareTo(b.getTitle());
			});
		case "price":
			return new TreeSet<Book>((a, b) -> {

				if (a.getPrice() == b.getPrice()) {
					if (a.getTitle().equals(b.getTitle())) {
						return a.getAuthor().compareTo(b.getAuthor());
					}
					return a.getTitle().compareTo(b.getTitle());
				}
				return (int) (a.getPrice() * 200 - b.getPrice() * 200);
			});
		case "year":
			return new TreeSet<Book>((a, b) -> {

				if (a.getYear() == b.getYear()) {
					if (a.getTitle().equals(b.getTitle())) {
						return a.getAuthor().compareTo(b.getAuthor());
					}
					return a.getTitle().compareTo(b.getTitle());
				}
				return a.getYear() - b.getYear();

			});
		case "publisher":
			return new TreeSet<Book>((a, b) -> {

				if (a.getPublisher().equals(b.getPublisher())) {
					if (a.getTitle().equals(b.getTitle())) {
						return a.getAuthor().compareTo(b.getAuthor());
					}
					return a.getTitle().compareTo(b.getTitle());
				}

				return a.getPublisher().compareTo(b.getPublisher());

			});
		}

		throw new UnknownSortException("Can't sort by " + sortBy);

	}
	
	public static void sortAndSet(HttpServletRequest request, String sortBy, Set<Book> books, String attrName) throws UnknownSortException {
		if (sortBy.endsWith("Desc")) {
			Set<Book> sortedBooks = getSortSetByDesc(sortBy);
			sortedBooks.addAll(books);
			request.setAttribute(attrName, sortedBooks);
		} else {
			Set<Book> sortedBooks = getSortSetBy(sortBy);
			sortedBooks.addAll(books);
			request.setAttribute(attrName, sortedBooks);
		}
	}

}
