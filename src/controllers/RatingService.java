package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import models.Validators;
import models.DBmodels.RatingDAO;
import models.entities.User;

@WebServlet("/rating")
public class RatingService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Validators.isAuthenticated(request, response)) {
			return;
		}

		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");

		User user = (User) request.getSession().getAttribute("user");
		long bookId = Long.parseLong(request.getParameter("bookId"));

		try {
			int rating = 0;
			if (request.getParameter("avg") != null) {
				rating = RatingDAO.getInstance().getAverage(bookId);
			} else {
				rating = RatingDAO.getInstance().get(user.getId(), bookId);

			}
			response.getWriter().print(new Gson().toJson(rating));

		} catch (SQLException e) {

			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!Validators.isAuthenticated(request, response)) {
			return;
		}
		User user = (User) request.getSession().getAttribute("user");

		String data = request.getParameter("data");
		Gson gson = new GsonBuilder().create();
		JsonObject jobj = gson.fromJson(data, JsonObject.class);

		long bookId = jobj.get("bookId").getAsLong();
		int rating = jobj.get("rating").getAsInt();

		try {

			if (RatingDAO.getInstance().exicts(user.getId(), bookId)) {
				RatingDAO.getInstance().edit(user.getId(), bookId, rating);
			} else {
				RatingDAO.getInstance().add(user.getId(), bookId, rating);
			}

		} catch (SQLException e) {

			e.printStackTrace();

			response.sendRedirect("./error404.html");
		}

	}

}
