package models.entities;

import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.DBmodels.DBManager;

public class SendRegistrationEmail {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	// public static void main(String[] args) {

	// String host="smtp.gmail.com";
	// final String user="books.world.pv@gmail.com";//change accordingly
	// final String password="booksWorld";//change accordingly
	//
	// String to="vasilena_ilieva@abv.bg";//change accordingly
	//
	// //Get the session object
	// Properties props = new Properties();
	// props.put("mail.smtp.host",host);
	// props.put("mail.smtp.auth", "true");

	// Session session = Session.getDefaultInstance(props,
	// new javax.mail.Authenticator() {
	// protected PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication(user,password);
	// }
	// });
	//
	// String activationLink = unique();
	// //Compose the message
	// try {
	// MimeMessage message = new MimeMessage(session);
	// message.setFrom(new InternetAddress(user));
	// message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	// message.setSubject("Confirm registration in BooksWorld");
	// message.setText("This is an email to activate your account in BooksWorls,
	// please follow this link: "+ activationLink + " to confirm your
	// registration.");
	//
	// //send the message
	// Transport.send(message);
	//
	// System.out.println("message sent successfully...");
	//
	// } catch (MessagingException e) {
	// e.printStackTrace();
	// }
	// }
	public static void generateAndSendEmail(String id, String email, String hash)
			throws AddressException, MessagingException {

		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");

		String link = DBManager.getInstance().getMAIL_REGISTRATION_SITE_LINK() + "?scope=activation&userId=" + id
				+ "&hash=" + hash;

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("  Dear User<br/><br/>").append("  Thank you for your registration!")
				.append("  Please confirm your e-mail (" + email + ") by following this link <a href=\"" + link
						+ "\">here</a> or open below link in browser<br/>")
				.append("  <a href=\"" + link + "\">" + link + "</a>").append("  <br/><br/>")
				.append("  Kind regards,<br/>").append("  Books World").append("</div>");
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("books.world.pv@gmail.com"));

		generateMailMessage.setSubject("Registration confirmation");
		generateMailMessage.setContent(bodyText.toString(), "text/html; charset=utf-8");
		// Transport.send(generateMailMessage);

		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);

		// generateMailMessage.addRecipient(Message.RecipientType.TO, new
		// InternetAddress("vasilena_ilieva@abv.bg"));
		// generateMailMessage.addRecipient(Message.RecipientType.CC, new
		// InternetAddress("books.world.pv@gmail.com"));
		// generateMailMessage.setSubject("Registration confirmation");
		// String emailBody = "Hello, " + username + " please follow this link:
		// "+ activationLink +" to activate your account. " + "<br><br> Regards,
		// <br>Books World";
		// generateMailMessage.setContent(emailBody, "text/html");
		// System.out.println("Mail Session has been created successfully..");

		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "books.world.pv@gmail.com", "booksWorld");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}

	public static void sendResetPasswordLink(String id, String email, String hash)
			throws AddressException, MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", DBManager.getInstance().getMAIL_SMTP_HOST());
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(DBManager.getInstance().getMAIL_USERNAME(),
						DBManager.getInstance().getMAIL_PASSWORD());
			}
		});

		String link = DBManager.getInstance().getMAIL_REGISTRATION_SITE_LINK() + "?scope=resetPassword&userId=" + id
				+ "&hash=" + hash;

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("  Dear User<br/><br/>")
				.append("  We got your reset password request, Find below link to reset password <br/>")
				.append("  Please click <a href=\"" + link + "\">here</a> or open below link in browser<br/>")
				.append("  <a href=\"" + link + "\">" + link + "</a>").append("  <br/><br/>").append("  Thanks,<br/>")
				.append("  SodhanaLibrary Team").append("</div>");
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(DBManager.getInstance().getMAIL_USERNAME()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("Reset Password");
		message.setContent(bodyText.toString(), "text/html; charset=utf-8");
		Transport.send(message);
	}
}
