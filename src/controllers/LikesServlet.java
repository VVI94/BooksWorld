package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Validators;
import models.DBmodels.CommentDAO;
import models.DBmodels.LikesDAO;
import models.entities.User;
import models.enums.LikeType;

@WebServlet("/likes")
public class LikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		long userId = user.getId();
		long commentId =Long.parseLong(request.getParameter("commentId"));
		long bookId = Long.parseLong(request.getParameter("bookId"));
		LikeType type = this.getType(request.getParameter("type"));
		
		try {
			long authorId = CommentDAO.getInstance().getAuthorId(commentId);
			
			if(authorId == userId){
				response.sendRedirect("BookInfo?book="+bookId);
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
			response.sendRedirect("BookInfo?book="+bookId);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private LikeType getType(String type){
		
		switch (type) {
		case "like":			
			return LikeType.LIKES;

		case "dislike":			
			return LikeType.DISLIKES;
		}
		
		return null;
	}

}
