package com.atguigu.survey.admin.component.interceptor;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.GlobalNames;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAwareInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		
		User user = (User) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_USER);
		if(user != null && action instanceof UserAware) {
			UserAware ua = (UserAware) action;
			ua.setUser(user);
		}
		
		return invocation.invoke();
	}

}
