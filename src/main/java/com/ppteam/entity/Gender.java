package com.ppteam.entity;

public enum Gender {
	MALE, FEMALE,UNKNOWN;
	
	public static String enumToString(Gender g){
		switch(g){
		case MALE:return "male";
		case FEMALE:return "female";
		default:return "unknown";
		}
	}
	
	public static Gender stringToEnum(String s){
		if(s.equals("male")){
			return Gender.MALE;
		}
		else if(s.equals("female")){
			return Gender.FEMALE;
		}
		else{
			return Gender.UNKNOWN;
		}
	}
}
