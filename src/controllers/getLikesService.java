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
import models.DBmodels.CommentDAO;
import models.DBmodels.LikesDAO;
import models.entities.User;
import models.enums.LikeType;

@WebServlet("/getLikes")
public class getLikesService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Validators.isAuthenticated(request, response)) {
			return;
		}

		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");

		long commentId = Long.parseLong(request.getParameter("commentId"));
		String likeType = request.getParameter("type");

		// User currentUser = (User) request.getSession().getAttribute("user");
		try {

			response.getWriter().print(new Gson().toJson(CommentDAO.getInstance().getLikes(commentId, likeType)));

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
		long userId = user.getId();
		
		String data = request.getParameter("data");

		Gson gson = new GsonBuilder().create();
		JsonObject jobj = gson.fromJson(data, JsonObject.class);
		
		String input = jobj.get("input").getAsString();

		
		long commentId = Long.parseLong(input.split("Count")[1]);
		LikeType type = this.getType(input.split("Count")[0]);
		
		
		try {
			long authorId = CommentDAO.getInstance().getAuthorId(commentId);
			
			if(authorId == userId){				
				return;
			}
			
			if(LikesDAO.getInstance().exists(commentId, type, userId)){
				LikesDAO.getInstance().remove(commentId, type, userId);
			}else{
				LikeType type1 = type.equals(LikeType.LIKES)? LikeType.DISLIKES:LikeType.LIKES;
				if(LikesDAO.getInstance().exists(commentId, type1, userId)){
					LikesDAO.getInstance().remove(commentId, type1, userId);
				}
				LikesDAO.getInstance().add(commentId, type, userId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
	}
	
	private LikeType getType(String type){
		
		switch (type) {
		case "LIKES":			
			return LikeType.LIKES;

		case "DISLIKES":			
			return LikeType.DISLIKES;
		}
		
		return null;
	}
}
