package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Answer;
import com.atguigu.survey.guest.model.OptionStatisticsModel;

public interface AnswerDao extends BaseDao<Answer>{

	void batchSaveAnswerList(List<Answer> answerList);

	int getTotalEngagedCount(Integer questionId);

	List<OptionStatisticsModel> getOsmList(Integer questionId, String[] optionsArray);

	int getOtherCount(Integer questionId);

	List<String> getOtherTextList(Integer questionId);

	List<String> getTextList(Integer questionId);

	int getOptionEnagedCount(Integer questionId, String currentValue);

	List<Answer> getEngagedListBySurveyId(Integer surveyId);

}
