package com.ppteam.entity;

public enum Role {
	USER("user"), ADMIN("admin"), IN_AUDIT("in_audit");
	
	private String value;
	
	private Role(String value){
		this.value=value;
	}
	public String toString(){
		return this.value;
	}
	
	public static Role fromString(String s){
		if(s.equals("user"))
			return USER;
		else if(s.equals("admin"))
			return ADMIN;
		else
			return IN_AUDIT;
	}
}
