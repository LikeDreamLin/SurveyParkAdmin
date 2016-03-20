package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.i.BaseDao;

public interface ResourceDao extends BaseDao<Resource>{

	Integer getMaxResPos();

	Long getCurrentMaxResCode(Integer maxResPos);

	boolean getResourceByActionName(String actionName);

	int getTotalCount();

	List<Resource> getLimitedList(Integer pageNo, int pageSize);

	void batchDelete(List<Integer> resIdList);

	List<Resource> getAllResList();

	Resource getResourceByTargetName(String actionName);

}
