package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.BookDAO;
import models.DBmodels.CommentDAO;
import models.entities.User;
import models.entities.comments.Comment;

@WebServlet("/getReplies")
public class getRepliesService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");

		String commentId = request.getParameter("commentId");
		
		try {

			
			response.getWriter()
					.print(new Gson().toJson(CommentDAO.getInstance().getAllReplies(Long.parseLong(commentId))));

		} catch (SQLException | ValidationException | UnexistingException e) {
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
	}
	
	@Override
	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Validators.isAuthenticated(request, response)){
			System.out.println("tuka li sym1111");
			return;
		}
		System.out.println("tuka li smeeeeeeeeeeeeeeeeeeeeeeee");
		User user = (User) request.getSession().getAttribute("user");

		
		String data = request.getParameter("data");

		Gson gson = new GsonBuilder().create();
		JsonObject jobj = gson.fromJson(data, JsonObject.class);
		System.out.println(jobj.toString());
		String commentId =jobj.get("commentId").getAsString();
		
		String content1 = jobj.get("name").getAsString();
		
		String content2 = jobj.get("name1").getAsString();
		
		String content = content1.equals("")?content2:content1;
		
		System.out.println(commentId);
		System.out.println(content);
		
		long commentId1 = Long.parseLong(commentId);
		java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		try {
			long bookId = BookDAO.getInstance().getBookIdByCommentId(commentId1);
			
			Comment comment = new Comment(content, date);
			
			CommentDAO.getInstance().addReply(comment, user.getId(), bookId, commentId1);
			
		} catch (SQLException | ValidationException e) {
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}
		
	}


}
