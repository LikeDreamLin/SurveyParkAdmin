package com.atguigu.survey.admin.entity;

/**
 * 代表权限管理机制中的“资源”
 * @author Creathin
 *
 */
public class Resource {
	
	//OID
	private Integer resourceId;
	
	//目标资源的URL地址，在Struts2环境下就是目标Action的名字
	//AdminAction_login
	private String actionName;
	
	//将actionName转换后得到的友好名称
	private String resourceName;
	
	//权限位
	private int resPos;
	
	//权限码
	private long resCode;
	
	public Resource() {
		
	}

	public Resource(String actionName, String resourceName, int resPos,
			long resCode) {
		super();
		this.actionName = actionName;
		this.resourceName = resourceName;
		this.resPos = resPos;
		this.resCode = resCode;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getResPos() {
		return resPos;
	}

	public void setResPos(int resPos) {
		this.resPos = resPos;
	}

	public long getResCode() {
		return resCode;
	}

	public void setResCode(long resCode) {
		this.resCode = resCode;
	}

}
