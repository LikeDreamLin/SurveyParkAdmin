package com.atguigu.survey.guest.component.service.m;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServiceImpl;
import com.atguigu.survey.guest.component.dao.i.BagDao;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.utils.DataProcessUtils;

@Service
public class BagServiceImpl extends BaseServiceImpl<Bag> implements BagService{

	@Autowired
	private BagDao bagDao;
	@Autowired
	private QuestionDao questionDao;

	@Override
	public void batchUpdateBagOrder(List<Bag> bagList) {
		
		bagDao.batchUpdateBagOrder(bagList);
		
	}

	@Override
	public void moveToThisSurvey(Integer bagId, Integer surveyId) {
		
		bagDao.moveToThisSurvey(bagId, surveyId);
		
	}

	@Override
	public void copyToThisSurvey(Integer bagId, Integer surveyId) {
		
		//1.根据bagId查询得到Bag对象
		Bag sourceBag = bagDao.getEntityById(bagId);
		
		//2.将Bag对象深度复制后得到新的对象
		Bag targetBag = (Bag) DataProcessUtils.deeplyCopy(sourceBag);
		
		//3.将新的对象保存到数据库中
		//设置SurveyId关联关系
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		targetBag.setSurvey(survey);
		
		bagDao.saveEntity(targetBag);
		
		Set<Question> questions = targetBag.getQuestions();
		questionDao.batchSave(questions);
		
	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		return bagDao.getFirstBag(surveyId);
	}

	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {
		return bagDao.getPrevBag(surveyId,bagId);
	}

	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		
		return bagDao.getNextBag(surveyId,bagId);
	}
	
}
