package com.atguigu.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.LogDao;
import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.m.BaseServiceImpl;
import com.atguigu.survey.guest.model.Page;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{
	
	@Autowired
	private LogDao logDao;

	@Override
	public void createTable(String tableName) {
		logDao.createTable(tableName);
	}

	@Override
	public void saveLog(Log log) {
		logDao.saveLog(log);
	}

	@Override
	public Page<Log> getPage(String pageNoStr, int pageSize) {
		
		int totalRecordNo = logDao.getTotalCount();
		
		Page<Log> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		List<Log> logList = logDao.getLimitedLogList(page.getPageNo(), pageSize);
		
		page.setList(logList);
		
		return page;
	}

}
