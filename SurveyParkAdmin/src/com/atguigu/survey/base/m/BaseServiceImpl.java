package com.atguigu.survey.base.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.base.i.BaseService;

@Transactional
public class BaseServiceImpl<T> implements BaseService<T>{
	
	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	@Override
	public T getEntityById(Integer entityId) {
		return baseDao.getEntityById(entityId);
	}

	@Override
	public List<T> getEntityList() {
		return baseDao.getEntityList();
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

}
