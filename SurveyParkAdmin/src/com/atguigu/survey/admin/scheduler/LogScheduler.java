package com.atguigu.survey.admin.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.utils.DataProcessUtils;

public class LogScheduler extends QuartzJobBean{
	
	private LogService logService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		//自动创建三个月的日志表
		String tableName = DataProcessUtils.generateTableName(1);
		logService.createTable(tableName);
		
		tableName = DataProcessUtils.generateTableName(2);
		logService.createTable(tableName);
		
		tableName = DataProcessUtils.generateTableName(3);
		logService.createTable(tableName);
		
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
