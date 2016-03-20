package com.atguigu.survey.guest.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServiceImpl;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.component.service.i.QuestionService;
import com.atguigu.survey.guest.entity.Question;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService{

	@Autowired
	private QuestionDao questionDao;
	
}
