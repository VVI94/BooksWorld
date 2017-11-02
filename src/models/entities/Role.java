package models.entities;

import exceptions.ValidationException;
import models.enums.UserRole;

public class Role {
	private long id;
	private UserRole role;
	private String name;
	
	
	public Role(String name) throws ValidationException {
		if(name.toUpperCase().equals("ADMINISTRATOR"))
			this.role = role.ADMINISTRATOR;
		if(name.toUpperCase().equals("BASIC"))
			this.role = role.BASIC;
		else
			throw new ValidationException("Invalid role!");
	}
	public Role(long id, String roleName) throws ValidationException {
		this(roleName);
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public UserRole getRoleName() {
		return role;
	}
	

}
