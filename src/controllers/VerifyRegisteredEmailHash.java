package controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BCrypt;
import models.Utils;
import models.DBmodels.UserDAO;

@WebServlet("/VerifyRegisteredEmailHash")
public class VerifyRegisteredEmailHash extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyRegisteredEmailHash() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // get user Id and email verification code Hash code  
        String userId = request.getParameter("userId");
        String hash = BCrypt.hashpw(request.getParameter("hash"), "$2a$10$DOWSDz/CyKaJ40hslrk5fe");
        String scope = request.getParameter("scope");
        String message = null;
        try {
            // verify with database
            if(UserDAO.verifyEmailHash(userId, hash) && scope.equals("active")) {
               //update status as active
               UserDAO.updateStatus(userId, "active");
               message = "Email verified successfully. Account was activated. Clic <a href=\"login.html\">here</a> to login";
            } else {
               //now increment verification attempts 
               int attempts = UserDAO.incrementVerificationAttempts(userId);
               if(attempts == 20) {
                   // reset verification code if attempts equal to 20 
                   String hashcode = Utils.prepareRandomString(30);
                   UserDAO.updateEmailVerificationHash(userId, BCrypt.hashpw(hashcode, "$2a$10$DOWSDz/CyKaJ40hslrk5fe"));
                   UserPojo up = UserDAO.getInstance().getUser(userId);
                   MailUtil.sendEmailRegistrationLink(userId, up.getEMAIL(), hashcode);
                   message = "20 times Wrong Email Validation Input Given. So we are sent new activation link to your Email";
               } else {
                   message = "Wrong Email Validation Input";   
               }
            }
        } catch (DException e) {
            e.printStackTrace();
            message = e.getMessage();
        } catch (AddressException e) {
            message = e.getMessage();
            e.printStackTrace();
        } catch (MessagingException e) {
            message = e.getMessage();
            e.printStackTrace();
        }
        if(message!=null) {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/messageToUser.jsp").forward(request, response);  
        } 
	}

}
