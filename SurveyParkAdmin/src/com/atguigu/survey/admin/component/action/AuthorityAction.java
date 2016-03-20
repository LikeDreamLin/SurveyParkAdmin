package com.atguigu.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.AuthorityService;
import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class AuthorityAction extends BaseAction<Authority>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private ResourceService resourceService;
	
	private String pageNoStr;
	private List<Integer> authIdList;
	private Integer authorityId;
	
	private List<Integer> resIdList;
	
	public String resMng() {
		
		authorityService.resMng(authorityId, resIdList);
		
		return "toAllAuthorityAction";
	}
	
	public String toResMngPage() {
		
		List<Resource> allResList = resourceService.getAllResList();
		reqMap.put(GlobalNames.ALL_RES_LIST, allResList);
		
		List<Integer> currentResIdList = authorityService.getCurrentResIdList(authorityId);
		reqMap.put(GlobalNames.CURRENT_RES_ID_LIST, currentResIdList);
		
		return "toResMngPage";
	}
	
	public String save() {
		
		authorityService.saveEntity(t);
		
		return "toAllAuthorityAction";
	}
	
	public String toCreatePage() {
		
		return "toCreatePage";
	}
	
	public Map<String,String> getMessage() {
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");
		
		return map;
	}
	
	public void prepareUpdate() {
		this.t = authorityService.getEntityById(authorityId);
	}
	
	public String update() {
		
		authorityService.updateEntity(t);
		
		return "updateSuccess";
	}
	
	public String batchDelete() {
		
		authorityService.batchDelete(authIdList);
		
		return "toAllAuthorityAction";
	}
	
	public String showAuthorities() {
		
		Page<Authority> page = authorityService.getPage(pageNoStr, 40);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toAllAuthorityPage";
	}

	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
	
	public List<Integer> getAuthIdList() {
		return authIdList;
	}
	
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
	public List<Integer> getResIdList() {
		return resIdList;
	}
	
	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}
}
