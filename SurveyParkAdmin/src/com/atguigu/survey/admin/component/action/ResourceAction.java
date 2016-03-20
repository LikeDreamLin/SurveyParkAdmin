package com.atguigu.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ResourceService resourceService;
	
	private String pageNoStr;
	private List<Integer> resIdList;
	private Integer resourceId;
	
	public Map<String,String> getMessage() {
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");
		
		return map;
	}
	
	public void prepareUpdate() {
		this.t = resourceService.getEntityById(resourceId);
	}
	
	public String update() {
		
		resourceService.updateEntity(t);
		
		return "updateSuccess";
	}
	
	public String batchDelete() {
		
		resourceService.batchDelete(resIdList);
		
		return "toAllResourcesAction";
	}
	
	public String showAllResources() {
		
		Page<Resource> page = resourceService.getPage(pageNoStr, 40);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toAllResourcesPage";
	}

	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}
	
	public List<Integer> getResIdList() {
		return resIdList;
	}
	
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
}
