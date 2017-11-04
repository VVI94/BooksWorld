package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.entities.User;

@WebServlet("/getSession")
public class getSessionUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text.json");
		response.setCharacterEncoding("UTF-8");
		
		User user = (User) request.getSession().getAttribute("user");
		
		response.getWriter().print(new Gson().toJson(user));
	}


}
