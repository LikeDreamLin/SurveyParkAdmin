package com.atguigu.survey.guest.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.SurveyDao;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;

@Repository
public class SurveyDaoImpl extends BaseDaoImpl<Survey> implements SurveyDao{

	@Override
	public int getUncompletedCount(User user) {
		
		String hql = "select count(*) from Survey s where s.completed=false and s.user=?";
		
		long totalRecord = (long) this.getSession().createQuery(hql).setEntity(0, user).uniqueResult();
		
		return (int) totalRecord;
	}

	@Override
	public List<Survey> getUncompletedList(int pageNo, int pageSize, User user) {
		
		String hql = "from Survey s where s.completed=false and s.user=?";
		
		int index = (pageNo - 1)*pageSize;
		
		return this.getSession()
				   .createQuery(hql)
				   .setEntity(0, user)
				   .setFirstResult(index)
				   .setMaxResults(pageSize).list();
	}

	@Override
	public int getCompletedCount(User user) {
		String hql = "select count(*) from Survey s where s.completed=true and s.user=?";
		
		long totalRecord = (long) this.getSession().createQuery(hql).setEntity(0, user).uniqueResult();
		
		return (int) totalRecord;
	}

	@Override
	public List<Survey> getCompletedList(int pageNo, int pageSize, User user) {
		String hql = "from Survey s where s.completed=true and s.user=?";
		
		int index = (pageNo - 1)*pageSize;
		
		return this.getSession()
				   .createQuery(hql)
				   .setEntity(0, user)
				   .setFirstResult(index)
				   .setMaxResults(pageSize).list();
	}

	@Override
	public int getCompletedCount() {
		String hql = "select count(*) from Survey s where s.completed=true";
		
		long totalRecord = (long) this.getSession().createQuery(hql).uniqueResult();
		
		return (int) totalRecord;
	}

	@Override
	public List<Survey> getCompletedList(int pageNo, int pageSize) {
		String hql = "from Survey s where s.completed=true";
		
		int index = (pageNo - 1)*pageSize;
		
		return this.getSession()
				   .createQuery(hql)
				   .setFirstResult(index)
				   .setMaxResults(pageSize).list();
	}

	@Override
	public List<Survey> findNewestTenSurvey() {
		
		String hql = "From Survey s where s.completed=true order by completedTime desc";
		
		return this.getSession().createQuery(hql).setMaxResults(10).list();
	}

	@Override
	public List<Survey> findHotestTenSurvey() {
		return null;
	}

	@Override
	public void saveEngageMsg(Integer userId, Integer surveyId) {
		
		String sql = "INSERT INTO engage(user_id,survey_id) VALUES(?,?)";
		
		this.getSession().createSQLQuery(sql).setInteger(0, userId).setInteger(1, surveyId).executeUpdate();
		
	}

	@Override
	public int getMyEngagedCount(Integer userId) {
		
		String sql = "select count(user_id) from engage where user_id=?";
		
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, userId).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Survey> getMyEngagedList(Integer userId, Integer pageNo,
			int pageSize) {
		
		String sql = "SELECT survey_id,TITLE,LOGO_PATH,COMPLETED_TIME FROM surveys "
				+ "WHERE survey_id IN "
				+ "(SELECT engage.survey_id FROM engage WHERE engage.user_id=?) limit ?,?";
		
		List<Object[]> list = this.getSession().createSQLQuery(sql).setInteger(0, userId).setInteger(1, (pageNo - 1)*pageSize).setInteger(2, pageSize).list();
		
		List<Survey> surveyList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] objects = list.get(i);
			
			Integer surveyId = (Integer) objects[0];
			String title = (String) objects[1];
			String logoPath = (String) objects[2];
			Date comTime = (Date) objects[3];
			
			Survey survey = new Survey(surveyId, title, logoPath, comTime);
			surveyList.add(survey);
		}
		
		return surveyList;
	}

	@Override
	public int getTotalEngagedCount(Integer surveyId) {
		
		String sql = "SELECT COUNT(DISTINCT UUID) FROM answers WHERE survey_id=?";
		
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, surveyId).uniqueResult();
		
		return result.intValue();
	}

}
