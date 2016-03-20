package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

@Service
public interface RoleService extends BaseService<Role>{

	Page<Role> getPage(String pageNoStr, int pageSize);

	void batchDelete(List<Integer> roleIdList);

	List<Integer> getCurrentAuthIdList(Integer roleId);

	void authMng(Integer roleId, List<Integer> authIdList);

	List<Role> getAllRoleList();

	Role getRoleByName(String roleName);

}
