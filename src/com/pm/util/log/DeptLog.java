package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.utils.IDKit;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.Permit;
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.service.IDeptService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class DeptLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IDeptService deptService = SpringContextUtil.getApplicationContext().getBean(IDeptService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			Dept[] depts = (Dept[])invocation.getArguments()[0];
			if(depts == null || depts.length == 0) return null;
			for(Dept dept : depts){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				Dept preDept = deptService.getDept(dept.getDept_id());
				if(preDept == null) preDept = new Dept();
				log.setEntity_id(dept.getDept_id());
				log.setEntity_name(dept.getDept_name()==null?preDept.getDept_name():dept.getDept_name());
				List<LogDetail>  details = PubMethod.getLogDetails(log,Dept.class, preDept,dept);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				Dept dept = (Dept)invocation.getArguments()[0];
				Dept preDept = new Dept();				
				log.setEntity_id(dept.getDept_id());
				log.setEntity_name(dept.getDept_name()==null?preDept.getDept_name():dept.getDept_name());
				details = PubMethod.getLogDetails(log,Dept.class, preDept,dept);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				Dept dept = (Dept)invocation.getArguments()[0];
				Dept preDept = deptService.getDept(dept.getDept_id());				
				log.setEntity_id(dept.getDept_id());
				log.setEntity_name(dept.getDept_name()==null?preDept.getDept_name():dept.getDept_name());
				details = PubMethod.getLogDetails(log,Dept.class, preDept,dept);				
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	


	private void special(List<LogDetail> details){
		if(details == null || details.isEmpty()) return ;
		for(LogDetail detail : details){
			if(detail.getData_item_code().equals("statisticsed")){
				if(detail.getOperation_after() != null && !detail.getOperation_after().isEmpty()){
					detail.setOperation_after(SpringContextUtil.getApplicationContext ().getMessage ("boolean."+detail.getOperation_after(), null, Locale.CHINA));
				}
				if(detail.getOperation_before() != null && !detail.getOperation_before().isEmpty()){
					detail.setOperation_before(SpringContextUtil.getApplicationContext ().getMessage ("boolean."+detail.getOperation_before(), null, Locale.CHINA));
				}
				
				
			}
			
					
		}
	}
	
	

}
