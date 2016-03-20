package com.atguigu.survey.guest.component.action;

import java.util.List;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.QuestionService;
import com.atguigu.survey.guest.component.service.i.StatisticsService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class StatisticsAction extends BaseAction<Survey>{

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	
	private JFreeChart chart;
	private Integer questionId;
	
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private QuestionService questionService;
	
	private int rowNumber;
	private int colNumber;
	
	@Autowired
	private StatisticsService statisticsService;
	
	public String showOptionMatrixChart() {
		
		List<OptionStatisticsModel> osmList = statisticsService.getOptionOsmList(questionId, rowNumber, colNumber);
		
		this.chart = DataProcessUtils.generateChartByOsmList(osmList);
		
		this.width = 400;
		this.height = 300;
		
		return "toChartResult";
	}
	
	public String showOptionMatrixPage() {
		
		Question question = questionService.getEntityById(questionId);
		reqMap.put(GlobalNames.QUESTION, question);
		
		return "toOptionMatrixPage";
	}
	
	public String showNormalMatrixChart() {
		
		List<OptionStatisticsModel> osmList = statisticsService.getOsmList(questionId, rowNumber);
		
		this.chart = DataProcessUtils.generateChartByOsmList(osmList);
		
		this.width = 300;
		this.height = 200;
		
		return "toChartResult";
	}
	
	public String showNormalMatrixPage() {
		
		Question question = questionService.getEntityById(questionId);
		reqMap.put(GlobalNames.QUESTION, question);
		
		return "toNormalMatrixPage";
	}
	
	public String showTextList() {
		
		List<String> textList = statisticsService.getTextList(questionId);
		reqMap.put(GlobalNames.TEXT_LIST, textList);
		
		return "toTextListPage";
	}
	
	public String showOtherTextList() {
		
		List<String> textList = statisticsService.getOtherTextList(questionId);
		reqMap.put(GlobalNames.TEXT_LIST, textList);
		
		return "toTextListPage";
	}
	
	public String showNormalChart() {
		
		//1.生成Chart对象赋值给this.chart
		QuestionStatisticsModel qsm = statisticsService.getQsm(questionId);
		
		this.chart = DataProcessUtils.generateChartByQsm(qsm);
		
		//2.设置宽高
		this.width = 1024;
		this.height = 768;
		
		return "toChartResult";
	}
	
	public void prepareShowSummary() {
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String showSummary() {
		
		return "toSummaryPage";
	}
	
	public JFreeChart getChart() {
		return chart;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}
}
