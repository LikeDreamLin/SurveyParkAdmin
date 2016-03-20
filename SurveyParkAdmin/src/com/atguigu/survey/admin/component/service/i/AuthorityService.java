package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

public interface AuthorityService extends BaseService<Authority>{

	void batchDelete(List<Integer> authIdList);

	Page<Authority> getPage(String pageNoStr, int i);

	List<Integer> getCurrentResIdList(Integer authorityId);

	void resMng(Integer authorityId,List<Integer> resIdList);

	List<Authority> getAllAuthList();

}
