package com.pm.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.service.ILogService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;

@Controller
@RequestMapping(value = "LogAction.do")
public class LogAction extends BaseAction {


	private static final String rel = "rel53";

	@Autowired
	private ILogService logService;
	
	@Autowired
	private IRoleService roleService;
	

	@RequestMapping(params = "method=list")
	public String list(Log serachLog,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.LOGVIEW.getId());
		
		if(serachLog != null && serachLog.getOperation_time2() != null){
			serachLog.getOperation_time2().setTime(serachLog.getOperation_time2().getTime()+1000*60*60*24-1);
		}
		
		Pager<Log> pager = logService.queryLog(serachLog, userPermit, PubMethod.getPager(request, Log.class));
		PubMethod.setRequestPager(request, pager,Log.class);
		

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.LOGDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		
		request.setAttribute("enumEntityTypes", EnumEntityType.values());
		
		return "system/log_list";
		
	}

	@RequestMapping(params = "method=toDetail")
	public String toDetail(Log serachLog,HttpServletResponse res,HttpServletRequest request){		
		List<LogDetail> details = logService.getDetails(serachLog);
		request.setAttribute("details", details);
		return "system/log_detail";
	}

	@RequestMapping(params = "method=deleteLog")
	public String deleteLog(HttpServletResponse res,HttpServletRequest request){
		
		Log log = new Log();
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());     
		ca.add(Calendar.YEAR, -1); 
		java.sql.Timestamp timestamp = new java.sql.Timestamp(ca.getTimeInMillis());
		log.setOperation_time(timestamp);
		
		logService.deleteLog(log);
		return this.ajaxForwardSuccess(request,false);
	}
	

}
