package com.ppteam.entity;

public enum Gender {
	MALE("male"),
	FEMALE("female"),
	OTHER("other"),
	UNKNOWN("unknown");
	
	private String value;
	
	private Gender(String value){
		this.value=value;
	}
	
	public String toString(){
		return this.value;
	}
	
	public static Gender fromString(String s){
		if(s.equals("male")){
			return MALE;
		}
		else if(s.equals("female")){
			return FEMALE;
		}
		else if(s.endsWith("other")){
			return OTHER;
		}
		else{
			return UNKNOWN;
		}
	}
}
