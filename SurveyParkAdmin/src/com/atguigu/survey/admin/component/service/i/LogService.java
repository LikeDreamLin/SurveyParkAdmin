package com.atguigu.survey.admin.component.service.i;

import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

public interface LogService extends BaseService<Log>{

	void createTable(String tableName);

	void saveLog(Log log);
	
	Page<Log> getPage(String pageNoStr, int pageSize);

}
