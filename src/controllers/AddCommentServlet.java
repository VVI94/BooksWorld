package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ValidationException;
import models.Validators;
import models.DBmodels.CommentDAO;
import models.entities.User;
import models.entities.comments.Comment;

@WebServlet("/AddComment")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		if (!Validators.isAuthenticated(request, response)) {
			return;
		}
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			String content = request.getParameter("content");
			long bookId = Long.parseLong(request.getParameter("book"));
			java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			Comment comment = new Comment(content, date);

			if (request.getParameter("comment") == null) {

				CommentDAO.getInstance().addComment(comment, user.getId(), bookId);

			} else {
				long commentId = Long.parseLong(request.getParameter("comment"));

				CommentDAO.getInstance().addReply(comment, user.getId(), bookId, commentId);
			}

			response.sendRedirect("./BookInfo?book=" + bookId);

		} catch (ValidationException | SQLException e) {
			response.sendRedirect("./error404.html");
		}

	}

}
