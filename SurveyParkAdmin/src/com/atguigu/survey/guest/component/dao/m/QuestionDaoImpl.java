package com.atguigu.survey.guest.component.dao.m;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.entity.Question;

@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao{

	@Override
	public void batchSave(Set<Question> questions) {
		String sql = "INSERT INTO questions("
				+ "`BAG_ID`,"
				+ "`QUESTION_NAME`,"
				+ "`QUESTION_TYPE`,"
				+ "`OPTIONS`,"
				+ "`BR`,"
				+ "`HAS_OTHER`,"
				+ "`OTHER_TYPE`,"
				+ "`MATRIX_ROW_TITLES`,"
				+ "`MATRIX_COL_TITLES`,"
				+ "`MATRIX_OPTIONS`) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		List<Object[]> params = new ArrayList<>();
		for (Question question : questions) {
			
			Object[] param = new Object[10];
			param[0] = question.getBag().getBagId();
			param[1] = question.getQuestionName();
			param[2] = question.getQuestionType();
			param[3] = question.getOptions();
			param[4] = question.isBr();
			param[5] = question.isHasOther();
			param[6] = question.getOtherType();
			param[7] = question.getMatrixRowTitles();
			param[8] = question.getMatrixColTitles();
			param[9] = question.getMatrixOptions();
			
			params.add(param);
			
		}
		
		this.doBatchWork(sql, params);
	}

	@Override
	public List<Question> getQuestionListBySurveyId(Integer surveyId) {
		
		String hql = "From Question q where q.bag.survey.surveyId=?";
		
		return this.getSession().createQuery(hql).setInteger(0, surveyId).list();
	}

}












