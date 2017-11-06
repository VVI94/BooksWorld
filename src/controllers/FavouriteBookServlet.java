package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.BookDAO;
import models.DBmodels.CategoryDAO;
import models.entities.Book;
import models.entities.User;

@WebServlet("/favouriteBooks")
public class FavouriteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
			
		try {
			Set<Book> favourites = BookDAO.getInstance().getAllFavourite(user.getId());
			Map<String, Long> categories =CategoryDAO.getInstance().getAllFavourites(user.getId());
			
			request.setAttribute("categories", categories);
			request.setAttribute("books", favourites);
			request.setAttribute("view", "favourite.jsp");
			request.getRequestDispatcher("base-layout.jsp").forward(request, response);
			
		} catch (SQLException | UnexistingException | ValidationException e) {			
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
