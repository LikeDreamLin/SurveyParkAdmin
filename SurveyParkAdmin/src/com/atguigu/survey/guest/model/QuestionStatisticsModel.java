package com.atguigu.survey.guest.model;

import java.util.List;

public class QuestionStatisticsModel {
	
	private String questionName;
	private int totalCount;
	private List<OptionStatisticsModel> osmList;
	
	public QuestionStatisticsModel() {
		
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<OptionStatisticsModel> getOsmList() {
		return osmList;
	}

	public void setOsmList(List<OptionStatisticsModel> osmList) {
		this.osmList = osmList;
	}

	@Override
	public String toString() {
		return "QuestionStatisticsModel [questionName=" + questionName
				+ ", totalCount=" + totalCount + ", osmList=" + osmList + "]";
	}

}
