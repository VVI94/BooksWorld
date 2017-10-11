package models.entities;

import exceptions.ValidationException;
import models.Validators;

public class Author {
	
	private int id;
	
	private String firstName;
	
	private String laststName;

	public Author(String firstName, String laststName) {
		
		this.firstName = firstName;
		this.laststName = laststName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) throws ValidationException {
		
		Validators.vlidateInputForNull("first name", firstName);
		
		this.firstName = firstName;
	}

	public String getLaststName() {
		return this.laststName;
	}

	public void setLaststName(String laststName) throws ValidationException {
		
		Validators.vlidateInputForNull("last name", laststName);
		
		this.laststName = laststName;
	}

	public int getId() {
		return this.id;
	}
	
	
	
	
}
