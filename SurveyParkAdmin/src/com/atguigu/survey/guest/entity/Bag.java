package com.atguigu.survey.guest.entity;

import java.io.Serializable;
import java.util.Set;

public class Bag implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer bagId;
	private String bagName;
	
	private transient Survey survey;
	private Set<Question> questions;
	private int bagOrder;
	
	public Bag() {
		
	}

	@Override
	public String toString() {
		return "Bag [bagId=" + bagId + ", bagName=" + bagName + ", bagOrder="
				+ bagOrder + "]";
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	public int getBagOrder() {
		return bagOrder;
	}
	
	public void setBagOrder(int bagOrder) {
		this.bagOrder = bagOrder;
	}
}
