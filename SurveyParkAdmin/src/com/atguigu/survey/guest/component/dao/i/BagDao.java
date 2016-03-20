package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Bag;

public interface BagDao extends BaseDao<Bag>{

	void batchUpdateBagOrder(List<Bag> bagList);

	void moveToThisSurvey(Integer bagId, Integer surveyId);

	Bag getFirstBag(Integer surveyId);

	Bag getPrevBag(Integer surveyId, Integer bagId);

	Bag getNextBag(Integer surveyId, Integer bagId);

}
