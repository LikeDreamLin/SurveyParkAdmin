package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

public interface ResourceService extends BaseService<Resource>{

	Integer getMaxResPos();

	Long getCurrentMaxResCode(Integer maxResPos);

	boolean getResourceByActionName(String actionName);

	Page<Resource> getPage(String pageNoStr, int i);

	void batchDelete(List<Integer> resIdList);

	List<Resource> getAllResList();

	Resource getResourceByTargetName(String actionName);

}
