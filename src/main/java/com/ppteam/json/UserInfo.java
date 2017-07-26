package com.ppteam.json;

public class UserInfo {
	
	private int id;
	private String gender;
	private String photoPath;
	private String personalSignature;
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(int id,String gender,String photoPath, String personalSignature) {
		super();
		this.id=id;
		this.gender = gender;
		this.photoPath=photoPath;
		this.personalSignature = personalSignature;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getPersonalSignature() {
		return personalSignature;
	}
	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}
	
}
