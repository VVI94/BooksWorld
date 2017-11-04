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

import exceptions.IntegerException;
import exceptions.UnexistingException;
import exceptions.UnknownSortException;
import exceptions.ValidationException;
import models.Validators;
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
			boolean haveSort = false;
			String sortBy = request.getParameter("sortBy");
			String author = request.getParameter("author");
			String minYear = request.getParameter("minYear");
			String maxYear = request.getParameter("maxYear");
			String minPrice = request.getParameter("minPrice");
			String maxPrice = request.getParameter("maxPrice");
			
			String emptySearchBy ="&author=" + author +"&minYear="+minYear+"&maxYear="
											+maxYear+"&minPrice="+minPrice+"&maxPrice="+ maxPrice;
			
			
			if(sortBy!=null){
				author = author == null?"":author;
				minYear = minYear == null?"":minYear;
				maxYear = maxYear == null?"":maxYear;
				minPrice = minPrice == null?"":minPrice;
				maxPrice = maxPrice == null?"":maxPrice;
			}

			if (author != null && (!"".equals(author) || !"".equals(minYear) || !"".equals(maxYear) || !"".equals(minPrice)
					|| !"".equals(maxPrice))) {
				haveSort = true;
			}

			int minYearInt = 0;
			int maxYearInt = 9999;
			int minPriceInt = 0;
			int maxPriceInt = 9999;

			if (author != null) {
				Validators.valideteInteger(minYear, maxYear, minPrice, maxPrice);
					
				minYearInt = "".equals(minYear) ? 0 :minYear==null?0 : Integer.parseInt(minYear);
				maxYearInt = "".equals(maxYear) ? 9999 :maxYear==null?9999 : Integer.parseInt(maxYear);
				minPriceInt = "".equals(minPrice) ? 0 :minPrice==null?0 : Integer.parseInt(minPrice);
				maxPriceInt = "".equals(maxPrice) ? 9999 :maxPrice==null?9999 : Integer.parseInt(maxPrice);
			}
			Map<String, Long> categories = CategoryDAO.getInstance().getAllCategories();
			Set<Book> books = new HashSet<>();
			Set<String> markCategories = new HashSet<>();

			boolean haveCheck = false;
			for (Entry<String, Long> entry : categories.entrySet()) {
				String isCheck = request.getParameter(entry.getKey());
				if (isCheck != null) {
					books.addAll(BookDAO.getInstance().getAllBooksByCategoryId(entry.getValue(), author, minYearInt,
							maxYearInt, minPriceInt, maxPriceInt));
					markCategories.add(entry.getKey());
					emptySearchBy +="&"+entry.getKey()+"=true";
					sb.append(entry.getKey()).append("=true&");
					haveCheck = true;
					haveSort = true;
				}
			}

			if (!haveCheck && haveSort) {
				books.addAll(BookDAO.getInstance().getAllBooksByCategoryId(0, author, minYearInt, maxYearInt,
						minPriceInt, maxPriceInt));

			}

			if (author != null) {

				sb = this.apendIfNotNull(sb, author, minYear, maxYear, minPrice, maxPrice, request);
			}

			if (books.isEmpty()) {
				if (!haveSort) {
					if (sortBy == null) {
						response.sendRedirect("./");
						return;
					}

					response.sendRedirect("./?sortBy=" + sortBy);
					return;
				}

				response.sendRedirect("./?empty= true" + emptySearchBy);
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

		} catch (SQLException | UnknownSortException | IntegerException | UnexistingException | ValidationException e) {
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}

	}

	private StringBuilder apendIfNotNull(StringBuilder sb, String author, String minYear, String maxYear,
			String minPrice, String maxPrice, HttpServletRequest request) {
		if (author!=null && !author.equals("")) {
			sb.append("author=" + author + "&");
			request.setAttribute("author", author);
		}
		if (minYear!=null && !minYear.equals("")) {
			sb.append("minYear=" + minYear + "&");
			request.setAttribute("minYear", minYear);
		}
		if (maxYear!=null && !maxYear.equals("")) {
			sb.append("maxYear=" + maxYear + "&");
			request.setAttribute("maxYear", maxYear);
		}
		if (minPrice!=null && !minPrice.equals("")) {
			sb.append("minPrice=" + minPrice + "&");
			request.setAttribute("minPrice", minPrice);
		}
		if (maxPrice!=null && !maxPrice.equals("")) {
			sb.append("maxPrice=" + maxPrice + "&");
			request.setAttribute("maxPrice", maxPrice);
		}

		return sb;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	private static Set<Book> getSortSetByDesc(String sortBy) throws UnknownSortException {
		switch (sortBy.substring(0, sortBy.length() - 4)) {
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

	public static void sortAndSet(HttpServletRequest request, String sortBy, Set<Book> books, String attrName)
			throws UnknownSortException {
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
