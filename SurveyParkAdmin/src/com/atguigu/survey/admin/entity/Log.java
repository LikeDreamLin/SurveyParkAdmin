package com.atguigu.survey.admin.entity;

public class Log {
	
	private Integer logId;
	
	//执行当前操作的用户信息
	private String operator;
	
	//操作时间
	private String operateTime;
	
	//声明方法的类型
	private String methodType;
	
	//方法名
	private String methodName;
	
	//方法参数
	private String methodArgs;
	
	//方法返回值
	private String methodReturnValue;
	
	//方法执行结果：成功/失败，如果失败那么保存异常信息
	private String methodResultMsg;

	public Log() {
		
	}

	public Log(Integer logId, String operator, String operateTime,
			String methodType, String methodName, String methodArgs,
			String methodReturnValue, String methodResultMsg) {
		super();
		this.logId = logId;
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodType = methodType;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.methodReturnValue = methodReturnValue;
		this.methodResultMsg = methodResultMsg;
	}

	public Log(String operator, String operateTime, String methodType,
			String methodName, String methodArgs, String methodReturnValue,
			String methodResultMsg) {
		super();
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodType = methodType;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.methodReturnValue = methodReturnValue;
		this.methodResultMsg = methodResultMsg;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(String methodArgs) {
		this.methodArgs = methodArgs;
	}

	public String getMethodReturnValue() {
		return methodReturnValue;
	}

	public void setMethodReturnValue(String methodReturnValue) {
		this.methodReturnValue = methodReturnValue;
	}

	public String getMethodResultMsg() {
		return methodResultMsg;
	}

	public void setMethodResultMsg(String methodResultMsg) {
		this.methodResultMsg = methodResultMsg;
	}
	
}
