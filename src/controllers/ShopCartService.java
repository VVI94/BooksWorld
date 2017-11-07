package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.BookDAO;
import models.entities.Book;

@WebServlet("/shopCart")
public class ShopCartService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Validators.isAuthenticated(request, response)) {
			return;
		}
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");
		
		String data = request.getParameter("data");

		Gson gson = new GsonBuilder().create();
		JsonObject jObj = gson.fromJson(data, JsonObject.class);
		if (jObj.get("forLoad").getAsBoolean()) {

			int all = ((Map<Book, Double>) request.getSession().getAttribute("shopCart")).size();
			response.getWriter().print(new Gson().toJson(all));
			
		} else {

			long bookId = jObj.get("bookId").getAsLong();
			int quantity = jObj.get("quantity").getAsInt();
			
			System.out.println(quantity);

			try {
				Book book = BookDAO.getInstance().getBook(bookId);

				addToSession(request, quantity, book);

				int all = ((Map<Book, Double>) request.getSession().getAttribute("shopCart")).size();
				response.getWriter().print(new Gson().toJson(all));

			} catch (SQLException | UnexistingException | ValidationException e) {

				e.printStackTrace();
				response.sendRedirect("./error404.html");
			}

		}

	}

	@SuppressWarnings("unchecked")
	private void addToSession(HttpServletRequest request, double quantity, Book book) {
		Map<Book, Double> shopCart = (Map<Book, Double>) request.getSession().getAttribute("shopCart");

		if (!shopCart.containsKey(book)) {
			shopCart.put(book, 0d);
		}

		shopCart.put(book, shopCart.get(book) + quantity/2);
		
		for (Book a : shopCart.keySet()) {
			System.out.println("kolich"+ shopCart.get(a));
		}
	}

}
