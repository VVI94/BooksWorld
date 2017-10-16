package models.entities;

public class Role {
	private long id;
	private String role;
	
	
	public Role(String role) {
		this.role = role;
	}
	public Role(long id, String role) {
		this(role);
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getRoleName() {
		return role;
	}
	

}
