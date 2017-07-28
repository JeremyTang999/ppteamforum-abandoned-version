package com.ppteam.json;

public class BasicInfo {
	
	private Integer id;
	private String username;
	private String role;
	public BasicInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BasicInfo(Integer id,String username, String role) {
		super();
		this.id=id;
		this.username = username;
		this.role = role;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id=id;
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
