package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

public interface AdminService extends BaseService<Admin>{

	Admin login(Admin t);

	List<Admin> generateAdminList();

	Page<Admin> getPage(String pageNoStr, int i);

	List<Integer> getCurrentRoleIdList(Integer adminId);

	void roleMng(Integer adminId, List<Integer> roleIdList);

	void calculationCode();

}
