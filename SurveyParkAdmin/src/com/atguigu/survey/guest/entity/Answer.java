package com.atguigu.survey.guest.entity;

import java.util.Date;

public class Answer {
	
	private Integer answerId;
	private Integer questionId;
	private Integer surveyId;
	
	private Date answerTime;
	private String uuid;
	
	//保存答案数据的主要属性，包括“和主题型一致”的其他项数据
	private String mainAnswers;
	
	//保存文本框形式的其他项
	private String otherAnswers;
	
	public Answer() {
		
	}

	public Answer(Integer answerId, Integer questionId, Integer surveyId,
			Date answerTime, String uuid, String mainAnswers,
			String otherAnswers) {
		super();
		this.answerId = answerId;
		this.questionId = questionId;
		this.surveyId = surveyId;
		this.answerTime = answerTime;
		this.uuid = uuid;
		this.mainAnswers = mainAnswers;
		this.otherAnswers = otherAnswers;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + "@ questionId=" + questionId
				+ "@ surveyId=" + surveyId + "@ answerTime=" + answerTime
				+ "@ uuid=" + uuid + "@ mainAnswers=" + mainAnswers
				+ "@ otherAnswers=" + otherAnswers + "]";
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMainAnswers() {
		return mainAnswers;
	}

	public void setMainAnswers(String mainAnswers) {
		this.mainAnswers = mainAnswers;
	}

	public String getOtherAnswers() {
		return otherAnswers;
	}

	public void setOtherAnswers(String otherAnswers) {
		this.otherAnswers = otherAnswers;
	}
	
}
