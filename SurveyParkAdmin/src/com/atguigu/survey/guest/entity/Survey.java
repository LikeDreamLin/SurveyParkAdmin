package com.atguigu.survey.guest.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Survey {
	
	private Integer surveyId;
	private String title;
	private String logoPath = "/resources_static/logo.gif";
	private User user;
	private Set<Bag> bagSet;
	
	private boolean completed;
	private Date completedTime;
	
	private Integer minOrder;
	private Integer maxOrder;

	public Survey() {
		
	}

	/*@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", title=" + title
				+ ", logoPath=" + logoPath + ", user=" + user + "]";
	}*/
	
	public Survey(Integer surveyId, String title, String logoPath,
			Date completedTime) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.logoPath = logoPath;
		this.completedTime = completedTime;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", title=" + title
				+ ", logoPath=" + logoPath + ", completed=" + completed
				+ ", completedTime=" + completedTime + ", minOrder=" + minOrder
				+ ", maxOrder=" + maxOrder + "]";
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public Set<Bag> getBagSet() {
		return bagSet;
	}
	
	public void setBagSet(Set<Bag> bagSet) {
		this.bagSet = bagSet;
	}
	
	public Date getCompletedTime() {
		return completedTime;
	}
	
	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Integer getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(Integer minOrder) {
		this.minOrder = minOrder;
	}

	public Integer getMaxOrder() {
		return maxOrder;
	}

	public void setMaxOrder(Integer maxOrder) {
		this.maxOrder = maxOrder;
	}
	
	public String getFormatedTime() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		
		return format.format(completedTime);
	}
	
}
