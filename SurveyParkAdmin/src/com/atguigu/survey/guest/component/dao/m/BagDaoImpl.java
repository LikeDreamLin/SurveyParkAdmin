package com.atguigu.survey.guest.component.dao.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.BagDao;
import com.atguigu.survey.guest.entity.Bag;

@Repository
public class BagDaoImpl extends BaseDaoImpl<Bag> implements BagDao{

	@Override
	public void batchUpdateBagOrder(List<Bag> bagList) {
		//1.准备SQL语句
		String sql = "UPDATE bags SET bag_order=? WHERE BAG_ID=?";
		
		//2.将bagList转换成List<Object[]> params
		List<Object[]> params = new ArrayList<>();
		for(int i = 0; i < bagList.size(); i++) {
			Bag bag = bagList.get(i);
			Object[] param = new Object[2];
			param[0] = bag.getBagOrder();
			param[1] = bag.getBagId();
			params.add(param);
		}
		
		//3.执行批量更新
		this.doBatchWork(sql, params);
	}

	@Override
	public void moveToThisSurvey(Integer bagId, Integer surveyId) {
		//本质：将Bag的SurveyId外键修改为目标调查的OID值
		String hql = "update Bag b set b.survey.surveyId=? where b.bagId=?";
		this.getSession()
			.createQuery(hql)
			.setInteger(0, surveyId)
			.setInteger(1, bagId)
			.executeUpdate();
	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		
		String hql = "From Bag b left join fetch b.questions where b.survey.surveyId=? order by b.bagOrder asc";
		
		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId).setMaxResults(1).uniqueResult();
	}

	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {
		
		//要查询的目标包裹：bagOrder<bagId对应的包裹的bagOrder
		
		String hql = "From Bag b left join fetch b.questions where b.survey.surveyId=? and "
				+ "b.bagOrder<(select innerBag.bagOrder from Bag innerBag where innerBag.bagId=?) order by b.bagOrder desc";
		
		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId).setInteger(1, bagId).setMaxResults(1).uniqueResult();
	}

	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		String hql = "From Bag b left join fetch b.questions where b.survey.surveyId=? and "
				+ "b.bagOrder>(select innerBag.bagOrder from Bag innerBag where innerBag.bagId=?) order by b.bagOrder asc";
		
		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId).setInteger(1, bagId).setMaxResults(1).uniqueResult();
	}

}
