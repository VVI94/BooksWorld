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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Author;
import models.entities.Book;
import models.entities.User;

@WebServlet("/favBooks")
public class FovouriteBooksSevrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");
		
		try {
			Set<Book> favourites = BookDAO.getInstance().getAllFavourite(user.getId());
			
			response.getWriter()
			.print(new Gson().toJson(favourites));
			
			
			
		} catch (SQLException | UnexistingException | ValidationException e) {			
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");
		
		
		String data = request.getParameter("data");

		Gson gson = new GsonBuilder().create();
		JsonObject jobj = gson.fromJson(data, JsonObject.class);
		
		String author = jobj.get("author").getAsString();
		String minPrice = jobj.get("minPrice").getAsString();
		String maxPrice = jobj.get("maxPrice").getAsString();
		String minYear = jobj.get("minYear").getAsString();
		String maxYear = jobj.get("maxYear").getAsString();
		
		
		int minYearInt = "".equals(minYear) ? 0 :minYear==null?0 : Integer.parseInt(minYear);
		int maxYearInt = "".equals(maxYear) ? 9999 :maxYear==null?9999 : Integer.parseInt(maxYear);
		int minPriceInt = "".equals(minPrice) ? 0 :minPrice==null?0 : Integer.parseInt(minPrice);
		int maxPriceInt = "".equals(maxPrice) ? 9999 :maxPrice==null?9999 : Integer.parseInt(maxPrice);
		Set<Book> books = new HashSet<>();
		try {
			books.add(new Book("skip", "", 1, "", 1, "", new Author("", ""), ""));
		} catch (ValidationException e1) {			
			e1.printStackTrace();
			response.sendRedirect("./error404.html");
		}
		
		if(jobj.get("categories").getAsJsonArray().size()!=0){
			
			
			for (JsonElement element : jobj.get("categories").getAsJsonArray()) {
												
				String categoryName = element.getAsString();
				
				getSortBooks(response, user, author, minYearInt, maxYearInt, minPriceInt, maxPriceInt, books, categoryName);
				
				
			}
			
		}else{
			try {
				Map<String, Long> cat = CategoryDAO.getInstance().getAllCategories();
				for (String c : cat.keySet()) {
					getSortBooks(response, user, author, minYearInt, maxYearInt, minPriceInt, maxPriceInt, books, c);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("./error404.html");
			}
			
		}
		
	
		
		
		response.getWriter().print(new Gson().toJson(books));
		
	}


	private void getSortBooks(HttpServletResponse response, User user, String author, int minYearInt, int maxYearInt,
			int minPriceInt, int maxPriceInt, Set<Book> books, String categoryName) throws IOException {
		try {
			long id = CategoryDAO.getInstance().getId(categoryName);
			books.addAll(BookDAO.getInstance().getAllBooksByCategoryId(id, author, 
					minYearInt, maxYearInt, minPriceInt, maxPriceInt, true, user.getId()));
		} catch (SQLException | UnexistingException | ValidationException e) {
			response.sendRedirect("./error404.html");
		}
	}

}
