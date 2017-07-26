package com.ppteam.entity;

public enum Role {
	USER, ADMIN, IN_AUDIT;
	public static String enumToString(Role r){
		switch(r){
		case USER:return "user";
		case ADMIN:return "admin";
		case IN_AUDIT:return "in_audit";
		default: return "";
		}
	}
	
	public static Role stringToEnum(String s){
		if(s.equals("user"))
			return Role.USER;
		else if(s.equals("admin"))
			return Role.ADMIN;
		else
			return Role.IN_AUDIT;
	}
}
