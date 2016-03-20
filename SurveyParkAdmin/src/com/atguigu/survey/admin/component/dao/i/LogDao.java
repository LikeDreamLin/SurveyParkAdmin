package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.i.BaseDao;

public interface LogDao extends BaseDao<Log>{

	void createTable(String tableName);

	void saveLog(Log log);
	
	List<String> getAllTableNames();
	
	int getTotalCount();
	
	List<Log> getLimitedLogList(int pageNo,int pageSize);


}
