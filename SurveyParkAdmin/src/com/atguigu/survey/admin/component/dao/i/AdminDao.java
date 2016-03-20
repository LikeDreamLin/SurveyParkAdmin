package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.base.i.BaseDao;

public interface AdminDao extends BaseDao<Admin>{

	void batchSaveAdminList(List<Admin> adminList);

	int getTotalCount();

	List<Admin> getLimitedList(Integer pageNo, int i);

	List<Integer> getCurrentRoleIdList(Integer adminId);

	void deleteOldRole(Integer adminId);

	void saveNewRole(Integer adminId, List<Integer> roleIdList);

	Admin checkAdminNameAndPwd(String adminName, String adminPwd);

}
