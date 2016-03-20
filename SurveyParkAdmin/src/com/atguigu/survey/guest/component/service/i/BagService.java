package com.atguigu.survey.guest.component.service.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Bag;

public interface BagService extends BaseService<Bag>{

	void batchUpdateBagOrder(List<Bag> bagList);

	void moveToThisSurvey(Integer bagId, Integer surveyId);

	void copyToThisSurvey(Integer bagId, Integer surveyId);

	Bag getFirstBag(Integer surveyId);

	Bag getPrevBag(Integer surveyId, Integer bagId);

	Bag getNextBag(Integer surveyId, Integer bagId);

}
