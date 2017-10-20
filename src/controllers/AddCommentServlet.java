package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.CommentDAO;
import models.DBmodels.UserDAO;
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
		// TODO isLogIn
		// TODO getUser

		User user = null;
		try {
			user = UserDAO.getInstance().getUser(1);
		} catch (ValidationException | SQLException | UnexistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String content = request.getParameter("content");

		long bookId = Long.parseLong(request.getParameter("book"));
		java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		Comment comment = null;
		try {
			comment = new Comment(content, date);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getParameter("comment") == null) {

			try {
				CommentDAO.getInstance().addComment(comment, user.getId(), bookId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			long commentId = Long.parseLong(request.getParameter("comment"));
			try {
				CommentDAO.getInstance().addReply(comment, user.getId(), bookId, commentId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.sendRedirect("./BookInfo?book=" + bookId);
	}

}
