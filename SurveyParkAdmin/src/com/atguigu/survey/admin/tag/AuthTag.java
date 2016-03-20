package com.atguigu.survey.admin.tag;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;
import com.opensymphony.xwork2.ActionContext;

/**
<atguigu:auth targetRes="ExcelAction_showAllSurvey">
	[<s:a namespace="/Admin" action="ExcelAction_showAllSurvey">Excel</s:a>]
</atguigu:auth>
 * @author Creathin
 *
 */
public class AuthTag extends SimpleTagSupport{
	
	private String targetRes;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		//getJspBody().invoke(null);
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		
		Admin admin = (Admin) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_USER);
		
		//超级管理员
		if(admin != null && "SuperAdmin".equals(admin.getAdminName())) {
			getJspBody().invoke(null);
			return ;
		}
		
		Set<String> visitorAllowedAction = new HashSet<>();
		visitorAllowedAction.add("UserAction_register");
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("UserAction_logout");
		visitorAllowedAction.add("SurveyAction_top10");
		visitorAllowedAction.add("AdminAction_toAdminMain");
		visitorAllowedAction.add("AdminAction_login");
		visitorAllowedAction.add("AdminAction_logout");
		
		if(visitorAllowedAction.contains(targetRes)) {
			getJspBody().invoke(null);
			return ;
		}
		
		if(user != null) {
			String resCode = user.getResCode();
			ResourceService resourceService = ioc.getBean(ResourceService.class);
			Resource resource = resourceService.getResourceByTargetName(resCode);
			
			boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
			if(checkResult) {
				getJspBody().invoke(null);
				return ;
			}
		}
		
		if(admin != null) {
			String resCode = admin.getResCode();
			ResourceService resourceService = ioc.getBean(ResourceService.class);
			Resource resource = resourceService.getResourceByTargetName(targetRes);
			
			boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
			if(checkResult) {
				getJspBody().invoke(null);
				return ;
			}
		}
		
	}
	
	public void setTargetRes(String targetRes) {
		this.targetRes = targetRes;
	}

}
