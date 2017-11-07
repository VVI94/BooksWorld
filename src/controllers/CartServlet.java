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

import com.google.gson.Gson;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.BookDAO;
import models.entities.Book;
import models.entities.Stock;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		request.setAttribute("view", "cart.jsp");
		request.getRequestDispatcher("base-layout.jsp").forward(request, response);
		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		Map<Book, Double> books = (Map<Book, Double>) request.getSession().getAttribute("shopCart");
		Set<Stock> stocks = new HashSet<>();
		for (Entry<Book, Double> entry : books.entrySet()) {
			stocks.add(new Stock(entry.getKey(), entry.getValue()));
		}
		
		
		
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");	
		response.getWriter().print(new Gson().toJson(stocks));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		
		
		long bookId = Long.parseLong(request.getParameter("bookId"));
		
		try {
			
			
			Map<Book, Double> cart = (Map<Book, Double>) request.getSession().getAttribute("shopCart");
			cart.remove(BookDAO.getInstance().getBook(bookId));
			
		} catch (SQLException | UnexistingException | ValidationException e) {
			
			e.printStackTrace();
			
			response.sendRedirect("./error404.html");
		}
	}

}
