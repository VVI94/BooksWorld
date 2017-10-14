package models.entities;

import exceptions.ValidationException;
import models.Validators;

public class Author {

	private long id;

	private String firstName;

	private String lastName;

	public Author(String firstName, String laststName) throws ValidationException {

		this.setFirstName(firstName);
		this.setLaststName(laststName);
	}

	public Author(long id, String firstName, String laststName) throws ValidationException {
		this(firstName, laststName);
		this.id = id;
		
	}

	public String getFirstName() {
		return this.firstName;
	}

	private void setFirstName(String firstName) throws ValidationException {

		Validators.vlidateInputForNull("first name", firstName);

		this.firstName = firstName;
	}

	public String getLaststName() {
		return this.lastName;
	}

	private void setLaststName(String laststName) throws ValidationException {

		Validators.vlidateInputForNull("last name", laststName);

		this.lastName = laststName;
	}

	public long getId() {
		return this.id;
	}

}
