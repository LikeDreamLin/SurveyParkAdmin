package com.atguigu.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.e.CascadDelBagException;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class BagAction extends BaseAction<Bag> implements UserAware{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BagService bagService;
	
	@Autowired
	private SurveyService surveyService;
	
	private String pageNoStr;
	
	private Integer bagId;

	private User user;
	
	public String copyToThisSurvey() {
		
		//将深度复制的全部工作都交给Service方法完成
		bagService.copyToThisSurvey(bagId,surveyId);
		
		return "toSurveyDesignAction";
	}
	
	public String moveToThisSurvey() {
		
		//将bagId代表的包裹移动到surveyId代表的调查中
		bagService.moveToThisSurvey(bagId,surveyId);
		
		return "toSurveyDesignAction";
	}
	
	public String toMoveCopyPage() {
		
		Page<Survey> page = surveyService.getUncompletedPage(pageNoStr, user, 5);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toMoveCopyPage";
	}
	
	public String update() {
		
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		t.setSurvey(survey);
		
		bagService.updateEntity(t);
		return "toSurveyDesignAction";
	}
	
	public void prepareEdit() {
		this.t = bagService.getEntityById(bagId);
	}
	
	public String edit() {
		return "toEditPage";
	}
	
	public String remove() {
		try {
			bagService.deleteEntity(t);
		} catch (Exception e) {
			
			throw new CascadDelBagException();
			
		}
		
		return "toSurveyDesignAction";
	}
	
	public String save() {
		
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		t.setSurvey(survey);
		
		bagService.saveEntity(t);
		
		return "toSurveyDesignAction";
	}
	
	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}
	
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
