package com.ppteam.json;

import java.util.*;

import com.ppteam.entity.QuestionAndAnswer;
public class SecurityInfo {
	private int id;
	private String oriPass;
	private List<QuestionAndAnswer> oriQA= new ArrayList<QuestionAndAnswer>();
	private String newPass;
	private List<QuestionAndAnswer> newQA= new ArrayList<QuestionAndAnswer>();
	
	public SecurityInfo(){
	}

	public SecurityInfo(int id, String oriPass, List<QuestionAndAnswer> oriQA, String newPass,
			List<QuestionAndAnswer> newQA) {
		super();
		this.id = id;
		this.oriPass = oriPass;
		this.oriQA = oriQA;
		this.newPass = newPass;
		this.newQA = newQA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriPass() {
		return oriPass;
	}

	public void setOriPass(String oriPass) {
		this.oriPass = oriPass;
	}

	public List<QuestionAndAnswer> getOriQA() {
		return oriQA;
	}

	public void setOriQA(List<QuestionAndAnswer> oriQA) {
		this.oriQA = oriQA;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public List<QuestionAndAnswer> getNewQA() {
		return newQA;
	}

	public void setNewQna(List<QuestionAndAnswer> newQA) {
		this.newQA = newQA;
	}

	
	

}
