package com.ppteam.json;

import java.util.*;

import com.ppteam.entity.QuestionAndAnswer;
public class SecurityInfo {
	private int id;
	private List<QuestionAndAnswer> oriQnA= new ArrayList<QuestionAndAnswer>();
	private List<QuestionAndAnswer> newQna= new ArrayList<QuestionAndAnswer>();
	
	public SecurityInfo(){
	}

	public SecurityInfo(int id, List<QuestionAndAnswer> oriQnA, List<QuestionAndAnswer> newQna) {
		super();
		this.id = id;
		this.oriQnA = oriQnA;
		this.newQna = newQna;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<QuestionAndAnswer> getOriQnA() {
		return oriQnA;
	}

	public void setOriQnA(List<QuestionAndAnswer> oriQnA) {
		this.oriQnA = oriQnA;
	}

	public List<QuestionAndAnswer> getNewQna() {
		return newQna;
	}

	public void setNewQna(List<QuestionAndAnswer> newQna) {
		this.newQna = newQna;
	}
	

}
