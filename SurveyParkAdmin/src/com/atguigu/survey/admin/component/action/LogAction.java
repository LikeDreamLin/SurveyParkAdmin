package com.atguigu.survey.admin.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LogService logService;
	
	private String pageNoStr;
	
	public String showLogs() {
		
		Page<Log> page = logService.getPage(pageNoStr, 40);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toShowLogsPage";
	}
	
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

}
