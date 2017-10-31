package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exceptions.AlreadyExistException;
import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBmodels.UserDAO;
import models.entities.User;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/Register")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String USER_AVATAR_URL = "C:/Users/Vasilena/Git_IT_Talents/pictures/";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = null;
		
		if (request.getParameter("userID") != null) {
			try {
				user = UserDAO.getInstance().getUser(Long.parseLong(request.getParameter("userID")));
			} catch (NumberFormatException | SQLException | UnexistingException | ValidationException e) {
				response.sendRedirect("./error404.html");
			}
		}

		request.setAttribute("user", user);
		request.setAttribute("view", "userRegister.jsp");
		request.getRequestDispatcher("base-layout.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		Part picture = request.getPart("picture");

		String userAvatar = username + "_" + firstname + "_" + lastname + ".jpg";
		File userFile = new File(USER_AVATAR_URL + userAvatar);

		if (picture.getSize() > 0) {
			try (InputStream input = picture.getInputStream()) {
				File prevPicture = new File(USER_AVATAR_URL + request.getParameter("picture"));
				if (prevPicture.exists()) {
					prevPicture.delete();
				}
				Files.copy(input, userFile.toPath());
			}
		} else {
			File prevPhoto = new File(USER_AVATAR_URL + request.getParameter("picture"));
			prevPhoto.renameTo(userFile);
		}

		try {
			User user = new User(username, password, email, firstname, lastname, address, telephone, userAvatar);
			if (request.getParameter("userID") != null) {

				long userID = Long.parseLong(request.getParameter("userID"));
				UserDAO.getInstance().editUser(userID, user);

			} else {
				UserDAO.getInstance().addUser(user);
			}

			response.sendRedirect("./");

		} catch (ValidationException | SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
