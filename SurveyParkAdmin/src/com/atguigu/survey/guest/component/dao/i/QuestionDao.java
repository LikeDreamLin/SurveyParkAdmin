package com.atguigu.survey.guest.component.dao.i;

import java.util.List;
import java.util.Set;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Question;

public interface QuestionDao extends BaseDao<Question>{

	void batchSave(Set<Question> questions);

	List<Question> getQuestionListBySurveyId(Integer surveyId);

}
