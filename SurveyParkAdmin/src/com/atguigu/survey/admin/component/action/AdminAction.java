package com.atguigu.survey.admin.component.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.AdminService;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class AdminAction extends BaseAction<Admin>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	
	private String pageNoStr;
	private List<Integer> roleIdList;
	private Integer adminId;
	
	public String logout() {
		this.sessionMap.remove(GlobalNames.LOGIN_ADMIN);
		return "toAdminLoginPage";
	}
	
	public String calculationCode() {
		
		adminService.calculationCode();
		
		return "toAdminMainPage";
	}
	
	public String roleMng() {
		
		adminService.roleMng(adminId,roleIdList);
		
		return "toAdminListAction";
	}
	
	public String toRoleMngPage() {
		
		List<Integer> currentRoleIdList = adminService.getCurrentRoleIdList(adminId);
		reqMap.put(GlobalNames.CURRENT_ROLE_ID_LIST, currentRoleIdList);
		
		List<Role> allRoleList = roleService.getAllRoleList();
		reqMap.put(GlobalNames.ALL_ROLE_LIST, allRoleList);
		
		return "toRoleMngPage";
	}
	
	public String showAdmins() {
		
		Page<Admin> page = adminService.getPage(pageNoStr,40);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toAdminsPage";
	}
	
	public String generateAdmin() {
		
		List<Admin> adminList = adminService.generateAdminList();
		reqMap.put(GlobalNames.NEW_ADMIN_LIST, adminList);
		
		return "toNewAdminsPage";
	}
	
	public String login() {
		
		//将根据用户名、密码查询得到的Admin对象返回，将来会作为判断是否具备权限的依据
		Admin admin = adminService.login(t);
		
		if(admin != null) {
			this.sessionMap.put(GlobalNames.LOGIN_ADMIN, admin);
			return "toAdminMainAction";
		}else{
			addActionError("用户名密码不正确！");
			return "toAdminLoginPage";
		}
		
	}
	
	public String toAdminMain() {
		
		//判断当前会话中是否有管理员登录
		Admin admin = (Admin) this.sessionMap.get(GlobalNames.LOGIN_ADMIN);
		
		if(admin == null) {
			
			addActionError("请登录后再操作！");
			return "toAdminLoginPage";
			
		}else{
			
			return "toAdminMainPage";
		}
		
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
}
