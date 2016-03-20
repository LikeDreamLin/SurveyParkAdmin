package com.atguigu.survey.admin.entity;

import java.util.Set;

public class Authority {
	
	private Integer authorityId;
	private String authorityName;
	
	private Set<Resource> resources;
	
	public Authority() {
		
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	public Set<Resource> getResources() {
		return resources;
	}
}
