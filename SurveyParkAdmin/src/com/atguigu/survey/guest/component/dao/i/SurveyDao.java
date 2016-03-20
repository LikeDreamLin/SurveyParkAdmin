package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;

public interface SurveyDao extends BaseDao<Survey>{
	
	//查询未完成的调查的总记录数
	int getUncompletedCount(User user);
	
	//查询未完成的调查的集合
	List<Survey> getUncompletedList(int pageNo,int pageSize,User user);
	
	//查询已完成的调查的总记录数
	int getCompletedCount(User user);
	
	//查询已完成的调查的集合
	List<Survey> getCompletedList(int pageNo,int pageSize,User user);
	
	//查询已完成的调查的总记录数
	int getCompletedCount();
	
	//查询已完成的调查的集合
	List<Survey> getCompletedList(int pageNo,int pageSize);

	List<Survey> findNewestTenSurvey();

	List<Survey> findHotestTenSurvey();

	void saveEngageMsg(Integer userId, Integer surveyId);

	int getMyEngagedCount(Integer userId);

	List<Survey> getMyEngagedList(Integer userId, Integer pageNo, int pageSize);

	int getTotalEngagedCount(Integer surveyId);

}
