package com.atguigu.survey.guest.model;

public class OptionStatisticsModel {
	
	private String label;
	private int count;
	
	public OptionStatisticsModel() {
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OptionStatisticsModel [label=" + label + ", count=" + count
				+ "]";
	}

}
