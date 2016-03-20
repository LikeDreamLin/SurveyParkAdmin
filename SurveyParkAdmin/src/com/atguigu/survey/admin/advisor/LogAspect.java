package com.atguigu.survey.admin.advisor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.GlobalNames;
import com.opensymphony.xwork2.ActionContext;

/**
 * 日志记录仪
 * @author Creathin
 *
 */
public class LogAspect {
	
	private LogService logService;
	
	public Object record(ProceedingJoinPoint joinPoint) {
		
		String operator = getOperator();
		String operateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		
		//声明目标方法的类型
		String methodType = joinPoint.getSignature().getDeclaringTypeName();
		
		//目标方法的方法名
		String methodName = joinPoint.getSignature().getName();
		
		//传递给目标方法的参数列表
		Object[] args = joinPoint.getArgs();
		
		String methodArgs = Arrays.asList(args).toString();//[1,2,3]
		
		String methodReturnValue = null;
		String methodResultMsg = null;
		
		Object result = null;
		
		
		//执行被代理的目标方法
		try {
			result = joinPoint.proceed(args);
			
			methodReturnValue = (result == null)?"无":result.toString();
			methodResultMsg = "成功";
			
		} catch (Throwable e) {
			
			methodResultMsg = "失败"+e.getMessage();
			
		} finally {
			Log log = new Log(operator, operateTime, methodType, methodName, methodArgs, methodReturnValue, methodResultMsg);
			logService.saveLog(log);
		}
		
		return result;
	}
	
	public String getOperator() {
		
		Admin admin = (Admin) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_USER);
		
		String adminPart = (admin == null)?"[]":"["+admin.getAdminName()+"]";
		String userPart = (user == null)?"[]":"["+user.getUserName()+"]";
		
		return adminPart+userPart;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
