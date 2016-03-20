package com.atguigu.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.AuthorityService;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	
	private String pageNoStr;
	private List<Integer> roleIdList;
	private Integer roleId;
	private List<Integer> authIdList;
	
	public String authMng() {
		
		roleService.authMng(roleId, authIdList);
		
		return "toShowRolesAction";
	}
	
	public String toAuthMngPage() {
		
		List<Authority> authList = authorityService.getAllAuthList();
		reqMap.put(GlobalNames.ALL_AUTH_LIST, authList);
		
		List<Integer> currentAuthList = roleService.getCurrentAuthIdList(roleId);
		reqMap.put(GlobalNames.CURRENT_AUTH_ID_LIST,currentAuthList);
		
		return "toAuthMngPage";
	}
	
	public Map<String,String> getMessage() {
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");
		
		return map;
	}
	
	public void prepareUpdate() {
		this.t = roleService.getEntityById(roleId);
	}
	
	public String update() {
		
		roleService.updateEntity(t);
		
		return "updateSuccess";
	}
	
	public String showRoles() {
		
		Page<Role> page = roleService.getPage(pageNoStr, 40);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toShowRolesPage";
	}
	
	public String batchDelete() {
		
		roleService.batchDelete(roleIdList);
		
		return "toShowRolesAction";
	}
	
	public String save() {
		
		roleService.saveEntity(t);
		
		return "toShowRolesAction";
	}
	
	public String toCreatePage() {
		return "toCreatePage";
	}
	
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
	
	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public List<Integer> getAuthIdList() {
		return authIdList;
	}
	
	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
}
