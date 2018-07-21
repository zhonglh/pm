package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.ILogDao;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.vo.UserPermit;

@Component
public class LogDaoImpl extends BatisDao  implements ILogDao {


	@Override
	public void insertLog(Log log, List<LogDetail> details) {

	      String sql = "LogMapping.insertLog";
	      this.insert(sql, log);
	      
	      if(details != null && !details.isEmpty()){
	      	String detailSql = "LogMapping.insertLogDetail";
	      	int index = 0;
		      for(LogDetail detail : details){
		      	detail.setOrder_no(++index);
			      this.insert(detailSql, detail);
		      }
	      }
	      

	}

	@Override

	public Pager<Log> queryLog(Log log, UserPermit userPermit , Pager<Log> pager) {

	      String sql = "LogMapping.queryLog";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Log.class, log,userPermit);
	}
	

	@Override
	public List<LogDetail> getDetails(Log log) {
	      String sql = "LogMapping.getDetails";
	      return this.query(sql, LogDetail.class, log);
	}
	


	@Override
	public void deleteLog(Log log){
		String sql = "LogMapping.deleteLogDetail";
	      this.delete(sql, log);
	      sql = "LogMapping.deleteLog";
	      this.delete(sql, log);
	      
	}

}
