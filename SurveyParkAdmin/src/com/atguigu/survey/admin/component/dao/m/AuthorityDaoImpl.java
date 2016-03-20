package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.AuthorityDao;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.m.BaseDaoImpl;

@Repository
public class AuthorityDaoImpl extends BaseDaoImpl<Authority> implements AuthorityDao{

	@Override
	public void batchDelete(List<Integer> authIdList) {
		
		String sql = "delete from AUTHORITIES where AUTHORITY_ID=?";
		
		List<Object[]> params = new ArrayList<>();
		for(int i = 0; i < authIdList.size(); i++) {
			Integer authId = authIdList.get(i);
			Object[] param = new Object[1];
			param[0] = authId;
			params.add(param);
		}
		
		doBatchWork(sql, params);
		
	}

	@Override
	public int getTotalCount() {
		
		String sql = "select count(*) from AUTHORITIES";
		
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Authority> getLimitedList(Integer pageNo, int pageSize) {
		
		String hql = "From Authority";
		
		return this.getSession().createQuery(hql).setFirstResult((pageNo - 1)*pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List<Integer> getCurrentResIdList(Integer authorityId) {
		
		String sql = "SELECT res_id FROM auth_res_inner WHERE auth_id=?";
		
		List<Integer> resIdList = this.getSession().createSQLQuery(sql).setInteger(0, authorityId).list();
		
		return resIdList;
	}

	@Override
	public void deleteOldRes(Integer authorityId) {
		
		String sql = "delete from auth_res_inner where auth_id=?";
		this.getSession().createSQLQuery(sql).setInteger(0, authorityId).executeUpdate();
		
	}

	@Override
	public void saveNewRes(Integer authorityId, List<Integer> resIdList) {
		
		String sql = "insert into auth_res_inner(auth_id,res_id) values(?,?)";
		List<Object[]> params = new ArrayList<>();
		for (Integer resId : resIdList) {
			Object[] param = new Object[2];
			param[0] = authorityId;
			param[1] = resId;
			params.add(param);
		}
		
		doBatchWork(sql, params);
		
	}

	@Override
	public List<Authority> getAllAuthList() {
		
		String hql = "From Authority";
		
		return this.getSession().createQuery(hql).list();
	}

}
