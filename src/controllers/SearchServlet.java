package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.BookDAO;
import models.entities.Book;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		if (!name.trim().isEmpty()) {
			try {
				Set<Book> books = BookDAO.getInstance().search(name);
				response.getWriter().print(new Gson().toJson(books));

			} catch (SQLException | UnexistingException | ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			response.getWriter().print("[]");
		}

	}

}
