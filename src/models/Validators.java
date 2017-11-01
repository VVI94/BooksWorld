package models;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ValidationException;

public class Validators {

	public static void vlidateInputForNull(String type, Object object) throws ValidationException{		
			if(object == null){
				throw new ValidationException("Invalid " + type + "!");
			}		
	}
	
	public static boolean validateStrings(String str, int length, String message) throws ValidationException{
		if(str != null && str.trim().length() > length){
			return true;
		}
		else{
			throw new ValidationException("Invalid " + message);
		}
	}
	
	public static boolean validateEmail(String email, int length) throws ValidationException{
		if(validateStrings(email, length, "email!") && email.contains("@")){
			return true;
		}
		else{
			throw new ValidationException("Invalid e-mail!");
		}
	}
	
	public static boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(request.getSession().getAttribute("user") == null){
			response.sendRedirect("./login");
			return false;
		}
		
		return true;
	}
		
}
