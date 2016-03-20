package com.atguigu.survey.guest.component.service.i;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;

public interface SurveyService extends BaseService<Survey>{
	
	Page<Survey> getUncompletedPage(String pageNoStr,User user,int pageSize);
	Page<Survey> getCompletedPage(String pageNoStr,User user,int pageSize);
	boolean complete(Integer surveyId);
	List<Survey> findNewestTenSurvey();
	List<Survey> findHotestTenSurvey();
	Page<Survey> findAllAvailableSurvey(String pageNoStr,int pageSize);
	void saveEngageMsg(Integer userId, Integer surveyId);
	Page<Survey> getMyEngagedSurvey(User user,String pageNoStr,int pageSize);
	Page<Survey> getCompletedPage(String pageNoStr, int pageSize);
	HSSFWorkbook generateWorkBook(Integer surveyId);

}
