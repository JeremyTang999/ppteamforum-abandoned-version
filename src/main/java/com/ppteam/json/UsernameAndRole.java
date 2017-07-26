package com.ppteam.json;

public class UsernameAndRole {
	
	private String username;
	private String role;
	public UsernameAndRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsernameAndRole(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
