package models;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.IntegerException;
import exceptions.ValidationException;
import models.entities.User;

public class Validators {

	public static void vlidateInputForNull(String type, Object object) throws ValidationException {
		if (object == null) {
			throw new ValidationException("Invalid " + type + "!");
		}
	}

	public static boolean validateStrings(String str, int length, String message) throws ValidationException {
		if (str != null && str.trim().length() > length) {
			return true;
		} else {
			throw new ValidationException("Invalid " + message);
		}
	}

	public static boolean validateEmail(String email, int length) throws ValidationException {
		if (validateStrings(email, length, "email!") && email.contains("@")) {
			return true;
		} else {
			throw new ValidationException("Invalid e-mail!");
		}
	}

	public static boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("./login");
			return false;
		}

		return true;
	}

	public static void valideteInteger(String... args) throws IntegerException {

		for (String string : args) {
			if (string != null && !string.equals("")) {
				try {
					Integer.parseInt(string);
				} catch (Exception e) {
					throw new IntegerException("Invalid input!");
				}
			}
		}

	}
	
	
	public static boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{		
			User user =(User) (request.getSession().getAttribute("user"));

			if(!user.isAdmin()){
				System.out.println("ne sym admin");
				return false;
			}		
			return true;
		}catch (Exception e) {
			response.sendRedirect("./error404.html");
			return false;
		}
				
	}

}
