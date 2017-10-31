package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.OrderDAO;
import models.DBmodels.UserDAO;
import models.entities.Order;
import models.entities.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// check if user exists in db
		try {
			boolean exists = UserDAO.getInstance().userExists(username, password);
			if (exists) {
				// update session
				User user = UserDAO.getInstance().getUser(username);
				request.getSession().setAttribute("user", user);
				ServletContext application = getServletConfig().getServletContext();
				synchronized (application) {
					if (application.getAttribute("orders") == null) {
						HashSet<Order> orders = OrderDAO.getInstance().getAllOrders();
						application.setAttribute("orders", orders);
					}
				}
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "user does not exist");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException | ValidationException | UnexistingException e) {
			request.setAttribute("error", "database problem : " + e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
	}
}