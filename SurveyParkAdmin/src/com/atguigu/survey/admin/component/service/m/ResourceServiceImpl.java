package com.atguigu.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.ResourceDao;
import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseServiceImpl;
import com.atguigu.survey.guest.model.Page;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Integer getMaxResPos() {
		return resourceDao.getMaxResPos();
	}

	@Override
	public Long getCurrentMaxResCode(Integer maxResPos) {
		return (maxResPos == null)?null:resourceDao.getCurrentMaxResCode(maxResPos);
	}

	@Override
	public boolean getResourceByActionName(String actionName) {
		return resourceDao.getResourceByActionName(actionName);
	}

	@Override
	public Page<Resource> getPage(String pageNoStr, int pageSize) {
		
		int totalRecordNo = resourceDao.getTotalCount();
		
		Page<Resource> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		List<Resource> list = resourceDao.getLimitedList(page.getPageNo(), pageSize);
		
		page.setList(list);
		
		return page;
	}

	@Override
	public void batchDelete(List<Integer> resIdList) {
		resourceDao.batchDelete(resIdList);
	}

	@Override
	public List<Resource> getAllResList() {
		return resourceDao.getAllResList();
	}

	@Override
	public Resource getResourceByTargetName(String actionName) {
		return resourceDao.getResourceByTargetName(actionName);
	}
	
}
