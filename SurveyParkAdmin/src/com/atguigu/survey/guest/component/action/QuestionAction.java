package com.atguigu.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.QuestionService;
import com.atguigu.survey.guest.entity.Question;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question>{

	private static final long serialVersionUID = 4132876302395483796L;

	@Autowired
	private QuestionService questionService;
	
	private Integer bagId;
	private Integer questionId;
	
	public String remove() {
		
		questionService.deleteEntity(t);
		
		return "toSurveyDesignAction";
	}
	
	public String saveOrUpdate() {
		
		questionService.saveOrUpdateEntity(t);
		
		return "toSurveyDesignAction";
	}
	
	public void prepareToQuestionDesign() {
		if(questionId != null) {
			this.t = questionService.getEntityById(questionId);
		}
	}
	
	public String toQuestionDesign() {
		return "toQuestionDesignPage";
	}
	
	public void prepareToTypeChoosen() {
		//只在questionId存在的时候才到数据库中查询
		if(questionId != null) {
			this.t = questionService.getEntityById(questionId);
		}
	}
	
	public String toTypeChoosen() {
		return "toTypeChoosenPage";
	}
	
	public Integer getBagId() {
		return bagId;
	}
	
	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}
	
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
}
