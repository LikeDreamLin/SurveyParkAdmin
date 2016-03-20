package com.atguigu.survey.guest.component.service.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;

public interface StatisticsService extends BaseService<Survey>{

	QuestionStatisticsModel getQsm(Integer questionId);

	List<String> getOtherTextList(Integer questionId);

	List<String> getTextList(Integer questionId);

	List<OptionStatisticsModel> getOsmList(Integer questionId, int rowNumber);

	List<OptionStatisticsModel> getOptionOsmList(Integer questionId,
			int rowNumber, int colNumber);

}
