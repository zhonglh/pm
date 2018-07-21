package com.pm.util.log;

import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.IDKit;
import com.pm.domain.system.Log;
import com.pm.domain.system.User;
import com.pm.service.ILogService;
import com.pm.util.PubMethod;

public abstract class BasicLog {

	public abstract List<Log> calculateLog(LogAnnotation methodAnnotation,
			MethodInvocation invocation,User seesionUser ) ;
	

	public void insertLog(List<Log> logs,ILogService logService) {
		if(logs == null) return ;
		for(Log log : logs){
			logService.insertLog(log, log.getDetails());
		}
	}
	
	
	
	public Log getLog(LogAnnotation methodAnnotation,MethodInvocation invocation,User sessionUser){
		Log log = new Log();
		log.setLog_id(IDKit.generateId());
		log.setEntity_type(methodAnnotation.entity_type());
		log.setOperation_type(methodAnnotation.operation_type());
		

		log.setOperation_userid(sessionUser.getUser_id());		
		log.setOperation_username(sessionUser.getUser_name());	
		log.setOperation_deptid(sessionUser.getUser_deptid());
		log.setOperation_time(PubMethod.getCurrentDate());	
		
		return log;
	}
	

	

}
