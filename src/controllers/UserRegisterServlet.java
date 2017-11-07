package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.dbcp.dbcp2.Utils;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.BCrypt;
import models.StatusPojo;
import models.DBmodels.UserDAO;
import models.entities.Book;
import models.entities.SendRegistrationEmail;
import models.entities.User;

@WebServlet("/Register")
@MultipartConfig
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String USER_AVATAR_URL = "E:/Final Project Workspace/BooksWorld/WebContent/images/";

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

		request.setAttribute("error", request.getParameter("error"));
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

		User user = null;
		try {
			user = new User(username, password, email, firstname, lastname, address, telephone, userAvatar);
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

			if (request.getParameter("userID") != null) {

				long userID = Long.parseLong(request.getParameter("userID"));
				UserDAO.getInstance().editUser(userID, user);
				request.getSession().invalidate();
				
				HttpSession session = request.getSession();
				
				
					session.setAttribute("shopCart", new HashMap<Book, Double>());
					session.setAttribute("user", user);
				

			} else {
				UserDAO.getInstance().addUser(user);
				user = UserDAO.getInstance().getUser(username);
				request.getSession().setAttribute("user", user);
			}

			response.sendRedirect("./");

		} catch (ValidationException | SQLException | UnexistingException e) {

			response.sendRedirect("./Register?error=" + e.getMessage());
		}

		String hash = models.Utils.prepareRandomString(30);

		// generate hash for password
		user.setEmailVerificationHash((BCrypt.hashpw(hash, "$2a$10$DOWSDz/CyKaJ40hslrk5fe")));
		StatusPojo sp = new StatusPojo();
		String output = "";

		try {
			SendRegistrationEmail.generateAndSendEmail(email, hash);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sp.setCode(0);
		sp.setMessage("Registation Link Was Sent To Your Mail Successfully. Please Verify Your Email");
		output = models.Utils.toJson(sp);

		// send output to user
		PrintWriter pw = response.getWriter();
		pw.write(output);
		pw.flush();
		pw.close();
	}

}
