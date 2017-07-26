package com.ppteam.entity;

import java.util.*;

public class UserSecurity {
	private Integer id;
	private String password;
	private Map<String,String> questionsAndAnswers=new HashMap<String,String>();
	
	public UserSecurity() {}

	public UserSecurity(Integer id, String password, Map<String, String> questionsAndAnswers) {
		super();
		this.id = id;
		this.password = password;
		this.questionsAndAnswers = questionsAndAnswers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getQuestionsAndAnswers() {
		return questionsAndAnswers;
	}

	public void setQuestionsAndAnswers(Map<String, String> questionsAndAnswers) {
		this.questionsAndAnswers = questionsAndAnswers;
	}
	
	

}
