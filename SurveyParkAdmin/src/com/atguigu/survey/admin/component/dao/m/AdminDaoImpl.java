package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.AdminDao;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseDaoImpl;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public void batchSaveAdminList(List<Admin> adminList) {
		String sql = "insert into admins(admin_name,admin_pwd) values(?,?)";
		List<Object[]> params = new ArrayList<>();
		for (Admin admin : adminList) {
			Object [] param = new Object[2];
			param[0] = admin.getAdminName();
			param[1] = admin.getAdminPwd();
			params.add(param);
		}
		
		doBatchWork(sql, params);
	}

	@Override
	public int getTotalCount() {
		
		String sql = "select count(*) from admins";
		
		BigInteger count = (BigInteger)this.getSession().createSQLQuery(sql).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Admin> getLimitedList(Integer pageNo, int i) {
		
		String hql = "From Admin";
		
		return this.getSession().createQuery(hql).setFirstResult((pageNo-1)*i).setMaxResults(i).list();
	}

	@Override
	public List<Integer> getCurrentRoleIdList(Integer adminId) {
		String sql = "select role_id from admin_role_inner where admin_id=?";
		return this.getSession().createSQLQuery(sql).setInteger(0, adminId).list();
	}

	@Override
	public void deleteOldRole(Integer adminId) {
		String sql = "delete from admin_role_inner where admin_id=?";
		this.getSession().createSQLQuery(sql).setInteger(0, adminId).executeUpdate();
	}

	@Override
	public void saveNewRole(Integer adminId, List<Integer> roleIdList) {
		String sql = "insert into admin_role_inner(admin_id,role_id) values(?,?)";
		List<Object[]> params = new ArrayList<>();
		for (Integer roleId : roleIdList) {
			Object[] param = new Object[2];
			param[0] = adminId;
			param[1] = roleId;
			params.add(param);
		}
		doBatchWork(sql, params);
	}

	@Override
	public Admin checkAdminNameAndPwd(String adminName, String adminPwd) {
		
		String hql = "From Admin a where a.adminName=? and a.adminPwd=?";
		
		return (Admin) this.getSession().createQuery(hql).setString(0, adminName).setString(1, adminPwd).uniqueResult();
	}

}
