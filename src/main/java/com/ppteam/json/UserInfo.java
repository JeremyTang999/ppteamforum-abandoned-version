package com.ppteam.json;

public class UserInfo {
	
	private String gender;
	private String personalSignature;
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(String gender, String personalSignature) {
		super();
		this.gender = gender;
		this.personalSignature = personalSignature;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPersonalSignature() {
		return personalSignature;
	}
	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}
	
}
