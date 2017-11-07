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
import models.DBmodels.CommentDAO;
import models.entities.User;
import models.entities.comments.Comment;

@WebServlet("/getComments")
public class GetCommentService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(!Validators.isAuthenticated(request, response)){
			return;
		}
		
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");

		String bookId = request.getParameter("bookId");
		try {

			response.getWriter()
					.print(new Gson().toJson(CommentDAO.getInstance().getAllComments(Long.parseLong(bookId))));

		} catch (SQLException | ValidationException | UnexistingException e) {
			e.printStackTrace();
			response.sendRedirect("./error404.html");
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!Validators.isAuthenticated(request, response)) {
			return;
		}
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			
			java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			
			String data = request.getParameter("data");

			Gson gson = new GsonBuilder().create();
			JsonObject jobj = gson.fromJson(data, JsonObject.class);
			String bookId =jobj.get("commentId").getAsString();
			
	//		String content1 = jobj.get("name").getAsString();
			
			String content2 = jobj.get("name1").getAsString();
			
	//		String content = content1.equals("")?content2:content1;
			
			
			Comment comment = new Comment(content2, date);

			long bookId1 = Long.parseLong(bookId);

			CommentDAO.getInstance().addComment(comment, user.getId(), bookId1);

			

		} catch (ValidationException | SQLException e) {
			response.sendRedirect("./error404.html");
		}

	}

}
