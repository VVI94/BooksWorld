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
import models.DBmodels.FavouriteDAO;
import models.entities.User;

@WebServlet("/favourite")
public class FavouriteService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");
		
		long bookId = Long.parseLong(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			String isIn = "false";
			if(FavouriteDAO.getInstance().exists(user.getId(), bookId)){
				isIn = "true";
			}
			response.getWriter().print(new Gson().toJson(isIn));

			
		} catch (SQLException e) {
			
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		String data = request.getParameter("data");
		Gson gson = new GsonBuilder().create();
		JsonObject jobj = gson.fromJson(data, JsonObject.class);
		
		long bookId = jobj.get("id").getAsLong();
		
		try {
			
			if(!FavouriteDAO.getInstance().exists(user.getId(), bookId)){			
				FavouriteDAO.getInstance().add(user.getId(), bookId);
			}else{
				FavouriteDAO.getInstance().remove(user.getId(), bookId);	
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
	}


}
