package com.atguigu.survey.guest.component.service.i;

import java.util.Map;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Answer;

public interface AnswerService extends BaseService<Answer>{

	void parseAndSave(Map<Integer, Map<String, String[]>> allBagMap,
			Integer surveyId);

}
