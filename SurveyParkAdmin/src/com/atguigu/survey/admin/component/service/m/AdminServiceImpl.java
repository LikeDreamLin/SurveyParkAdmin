package com.atguigu.survey.admin.component.service.m;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.AdminDao;
import com.atguigu.survey.admin.component.dao.i.ResourceDao;
import com.atguigu.survey.admin.component.service.i.AdminService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseServiceImpl;
import com.atguigu.survey.guest.component.dao.i.UserDao;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.ValidateUtils;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Admin login(Admin t) {
		
		if(t == null) return null;
		
		String adminName = t.getAdminName();
		String adminPwd = t.getAdminPwd();
		
		if("SuperAdmin".equals(adminName) && "atguigu".equals(adminPwd)) {
			
			return t;
			
		}
		
		return adminDao.checkAdminNameAndPwd(adminName, adminPwd);
	}

	@Override
	public List<Admin> generateAdminList() {
		
		List<Admin> adminList = new ArrayList<>();
		
		for(int i = 0; i < 20; i++) {
			Admin admin = new Admin();
			admin.setAdminName(DataProcessUtils.generateRandomString(6));
			admin.setAdminPwd(DataProcessUtils.generateRandomString(8));
			
			adminList.add(admin);
		}
		
		batchSaveAdminList(adminList);
		
		return adminList;
	}
	
	public void batchSaveAdminList(List<Admin> adminList) {
		
		adminDao.batchSaveAdminList(adminList);
		
	}

	@Override
	public Page<Admin> getPage(String pageNoStr, int i) {
		
		int totalRecordNo = adminDao.getTotalCount();
		
		Page<Admin> page = new Page<>(pageNoStr, totalRecordNo, i);
		
		List<Admin> list = adminDao.getLimitedList(page.getPageNo(),i);
		
		page.setList(list);
		
		return page;
	}

	@Override
	public List<Integer> getCurrentRoleIdList(Integer adminId) {
		
		return adminDao.getCurrentRoleIdList(adminId);
	}

	@Override
	public void roleMng(Integer adminId, List<Integer> roleIdList) {
		adminDao.deleteOldRole(adminId);
		adminDao.saveNewRole(adminId,roleIdList);
	}

	@Override
	public void calculationCode() {
		
		//一、Admin
		//1.获取系统中所有的Admin
		List<Admin> adminList = adminDao.getEntityList();
		
		//2.重新计算每一个Admin的权限码
		//①遍历AdminList
		for (Admin admin : adminList) {
			//②获取每一个Admin中的Role集合
			Set<Role> roles = admin.getRoles();
			
			//③查询当前系统中最大的权限位的值
			Integer maxPox = resourceDao.getMaxResPos();
			
			String resCode = DataProcessUtils.calculatCodeByRoles(roles, maxPox);
			
			admin.setResCode(resCode);
			
			adminDao.updateEntity(admin);
		}
		
		
		//二、User
		//1.获取系统中所有的User
		List<User> userList = userDao.getEntityList();
		
		//2.重新计算每一个User的权限码
		for (User user : userList) {
			Set<Role> roles = user.getRoles();
			
			Integer maxPos = resourceDao.getMaxResPos();
			
			String resCode = DataProcessUtils.calculatCodeByRoles(roles, maxPos);
			
			user.setResCode(resCode);
			
			userDao.updateEntity(user);
		}
		
	}
	
}
