package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.UserDAO;
import models.entities.Book;
import models.entities.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("error", request.getParameter("error"));
		request.setAttribute("view", "login.jsp");
		request.getRequestDispatcher("base-layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// check if user exists in db
		try {
			UserDAO.getInstance().userExists(username, password);
			
				// update session
				User user = UserDAO.getInstance().getUser(username);
				
				HttpSession session = request.getSession();
				
				
					session.setAttribute("shopCart", new HashMap<Book, Double>());
					session.setAttribute("user", user);
				
			
//				ServletContext application = getServletConfig().getServletContext();
//				synchronized (application) {
//					if (application.getAttribute("orders") == null) {
//						HashSet<Order> orders = OrderDAO.getInstance().getAllOrders();
//						application.setAttribute("orders", orders);
//					}
//				}
//				request.getRequestDispatcher("/").forward(request, response);
			
//				request.setAttribute("error", "user does not exist");
//				request.getRequestDispatcher("login").forward(request, response);
				
				response.sendRedirect("./");
			
		} catch (SQLException | ValidationException e) {
			request.setAttribute("error", "database problem : " + e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} catch (UnexistingException e) {
			response.sendRedirect("./login?error=user does not exist");
		}
	}
}