package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.ILogDao;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.ILogService;
import com.pm.vo.UserPermit;

@Component
public class LogServiceImpl implements ILogService {
	

	@Autowired ILogDao logDao;

	

	@Override
	public void insertLog(Log log, List<LogDetail> details) {
		logDao.insertLog(log, details);
	}

	@Override
	public Pager<Log> queryLog(Log log, UserPermit userPermit, Pager<Log> pager) {		
		return logDao.queryLog(log, userPermit, pager);
	}
	

	public List<LogDetail> getDetails(Log log){
		return logDao.getDetails(log);
	}
	


	public void deleteLog(Log log){
		logDao.deleteLog(log);
	}

}
