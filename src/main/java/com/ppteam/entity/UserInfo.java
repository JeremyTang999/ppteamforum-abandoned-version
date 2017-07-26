package com.ppteam.entity;

public class UserInfo {
	

	private Integer id;
	private Gender gender;
	private String photoPath;
	private String personalSignature;
	
	public UserInfo() {}

	public UserInfo(Integer id, Gender gender, String photoPath, String personalSignature) {
		super();
		this.id = id;
		this.gender = gender;
		this.photoPath = photoPath;
		this.personalSignature = personalSignature;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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
