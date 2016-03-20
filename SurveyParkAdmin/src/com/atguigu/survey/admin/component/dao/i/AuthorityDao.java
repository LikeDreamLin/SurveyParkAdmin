package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.i.BaseDao;

public interface AuthorityDao extends BaseDao<Authority>{

	void batchDelete(List<Integer> authIdList);

	int getTotalCount();

	List<Authority> getLimitedList(Integer pageNo, int pageSize);

	List<Integer> getCurrentResIdList(Integer authorityId);

	void deleteOldRes(Integer authorityId);

	void saveNewRes(Integer authorityId, List<Integer> resIdList);

	List<Authority> getAllAuthList();

}
