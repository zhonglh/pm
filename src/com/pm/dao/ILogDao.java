package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.vo.UserPermit;

public interface ILogDao {
	

	public void insertLog(Log log, List<LogDetail> details) ;
	
	public Pager<Log> queryLog(Log log, UserPermit userPermit , Pager<Log> pager) ;	

	public List<LogDetail> getDetails(Log log) ;
	
	public void deleteLog(Log log);

}
