package models.entities;

import java.util.Set;

import exceptions.ValidationException;
import models.Validators;

public class User {

	private static final int USERNAME_MIN_LENGTH = 5;
	private static final int ADDRESS_MIN_LENGTH = 10;
	private static final int PASS_MIN_LENGTH = 6;
	private static final int NAME_MIN_LENGTH = 3;
	private static final int EMAIL_MIN_LENGTH = 10;
	private static final int TELEPHONE_MIN_LENGTH = 10;
	
	
	private long id;
	private String username;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String address;
	private String telephone;
	private Long roleID;
	private String userAvatar;
	private Set<Order> orders;
	
	
	
	public User(String username, String password, String email, String firstname, String lastname, String address,
			String telephone, String userAvatar) throws ValidationException {
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setFirstname(firstname);
		setLastname(lastname);
		setAddress(address);		
		setTelephone(telephone);
		setRoleID(1L); // means basic user, 2-> admin
		setUserAvatar(userAvatar);
		
	}
//	public User(long id, String username, String password, String email, String firstname, String lastname, String address,
//			String telephone, String userAvatar) throws ValidationException {
//		this(username, password,  email, firstname, lastname, address, telephone, userAvatar);
//		setUserAvatar(userAvatar);
//		this.id = id;
//	}

	public User(long id, String username, String password, String email, String firstname, String lastname, String address,
			String telephone, String userAvatar) throws ValidationException {
		this(username, password,  email, firstname, lastname, address, telephone, userAvatar);
		this.id = id;
	}
	
	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		if(userAvatar.length() > 0)
		this.userAvatar = userAvatar;
		else
			this.userAvatar = "C:/Users/Vasilena/Git_IT_Talents/pictures/default_user_avatar.jpg";
	}

	private void setRoleID(Long roleid) throws ValidationException{
		this.roleID = roleid;		
	}

	public long getId() {
		return id;
	}

	public String getUsername() {		
		return username;
	}

	public void setUsername(String username) throws ValidationException {
		String message = "username!";
		if(Validators.validateStrings(username, USERNAME_MIN_LENGTH, message))
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws ValidationException {
		String message = "address!";
		if(Validators.validateStrings(address, ADDRESS_MIN_LENGTH, message))
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws ValidationException {
		String message = "password!";
		if(Validators.validateStrings(password, PASS_MIN_LENGTH, message))
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) throws ValidationException {
		String message = "firstname!";
		if(Validators.validateStrings(firstname, NAME_MIN_LENGTH, message))
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) throws ValidationException {
		String message = "lastname!";
		if(Validators.validateStrings(lastname, NAME_MIN_LENGTH, message))
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws ValidationException {
		String message = "email!";
		if(Validators.validateStrings(email, EMAIL_MIN_LENGTH, message))
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) throws ValidationException {
		String message = "telephone!";
		if(Validators.validateStrings(telephone, TELEPHONE_MIN_LENGTH, message))
		this.telephone = telephone;
	}
	public long getRoleID() {
		return this.roleID;
	}
}
