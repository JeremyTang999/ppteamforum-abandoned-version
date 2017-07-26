package com.ppteam.entity;

import java.util.*;

public class UserSecurity {
	private Integer id;
	private String password;
	private List<QuestionAndAnswer> questionsAndAnswers=
			new ArrayList<QuestionAndAnswer>();
	
	public UserSecurity() {}

	public UserSecurity(Integer id, String password, List<QuestionAndAnswer> questionsAndAnswers) {
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

	public List<QuestionAndAnswer> getQuestionsAndAnswers() {
		return questionsAndAnswers;
	}

	public void setQuestionsAndAnswers(List<QuestionAndAnswer> questionsAndAnswers) {
		this.questionsAndAnswers = questionsAndAnswers;
	}
	
	

}
