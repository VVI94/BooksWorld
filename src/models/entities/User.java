package models.entities;

<<<<<<< HEAD
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
	private String address;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String telephone;
	private Role role;
	
	public User(String username, String address, String password, String firstname, String lastname, String email,
			String telephone, Role role) throws ValidationException {
		setUsername(username);
		setAddress(address);
		setPassword(password);
		setFirstname(firstname);
		setLastname(lastname);
		setEmail(email);
		setTelephone(telephone);
		setRole(role);
	}

	private void setRole(Role role) throws ValidationException{
		Validators.vlidateInputForNull("role", role);
		this.role = role;
		
		
	}

	public User(long id, String username, String address, String password, String firstname, String lastname,
			String email, String telephone, Role role) throws ValidationException {
		this(username, address, password, firstname, lastname, email, telephone,role);
		this.id = id;
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
	
	
	
	
	
	
=======
public class User {

>>>>>>> b20dffc0791d264f32b9c36da1a1334f797cafc5
}
