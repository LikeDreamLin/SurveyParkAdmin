package com.atguigu.survey.admin.entity;

import java.util.Set;

public class Admin {
	
	private Integer adminId;
	private String adminName;
	private String adminPwd;
	private Set<Role> roles;
	private String resCode;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	
	public String getResCode() {
		return resCode;
	}
}
