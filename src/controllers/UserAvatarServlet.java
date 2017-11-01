package controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.UserDAO;
import models.entities.User;

@WebServlet("/profilePicture")
public class UserAvatarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("user");
		User user = null;
		try {
			user = UserDAO.getInstance().getUser(Long.parseLong(id));
		} catch (NumberFormatException | SQLException | UnexistingException | ValidationException e) {
			e.printStackTrace();
		}

		String profilePicture = user.getUserAvatar();
		File file = new File(UserRegisterServlet.USER_AVATAR_URL + profilePicture);

		try (OutputStream out = response.getOutputStream()) {
			Path path = file.toPath();
			Files.copy(path, out);
			out.flush();
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}
	}
}
