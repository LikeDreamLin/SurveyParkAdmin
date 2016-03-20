package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.i.BaseDao;

public interface RoleDao extends BaseDao<Role>{

	int getTotalCount();

	List<Role> getLimitedList(Integer pageNo, int pageSize);

	void batchDelete(List<Integer> roleIdList);

	List<Integer> getCurrentAuthIdList(Integer roleId);

	void deleteOldAuth(Integer roleId);

	void saveNewAuth(Integer roleId, List<Integer> authIdList);

	List<Role> getAllRoleList();

	Role getRoleByName(String roleName);

}
