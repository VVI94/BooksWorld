package models.entities;

import exceptions.ValidationException;
import models.Validators;

public class Author implements Comparable<Author>{

	private long id;

	private String firstName;

	private String lastName;

	public Author(String firstName, String laststName) throws ValidationException {

		this.setFirstName(firstName);
		this.setLastName(laststName);
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

	public String getLastName() {
		return this.lastName;
	}

	private void setLastName(String laststName) throws ValidationException {

		Validators.vlidateInputForNull("last name", laststName);

		this.lastName = laststName;
	}

	public long getId() {
		return this.id;
	}

	@Override
	public int compareTo(Author o) {
		if(this.firstName.equals(o.firstName)){
			return this.lastName.compareTo(o.lastName);
		}
		return this.firstName.compareTo(o.firstName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	

}
