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

import models.Validators;
import models.DBmodels.CategoryDAO;
import models.entities.User;

@WebServlet("/getCategory")
public class GetCategoryService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
				
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			Map<String, Long> cat = CategoryDAO.getInstance().getAllFavourites(user.getId());
			
			Set<String> categories = new HashSet<>();
			categories.addAll(cat.keySet());
			response.getWriter()
			.print(new Gson().toJson(categories));
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
			response.sendRedirect("./error404.html");
		}
	}


}
