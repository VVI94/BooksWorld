package models.entities;

public class Role {
	private long id;
	private UserRole role;
	
	
	public Role(UserRole role) {
		this.role = role;
	}
	public Role(long id, UserRole role) {
		this(role);
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public UserRole getRoleName() {
		return role;
	}
	

}
