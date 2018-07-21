package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.DepartObjective;
import com.pm.domain.system.Dept;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IDepartObjectiveService;
import com.pm.service.IDeptService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class DepartObjectiveLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IDepartObjectiveService departObjectiveService = SpringContextUtil.getApplicationContext().getBean(IDepartObjectiveService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			DepartObjective departObjective = (DepartObjective)invocation.getArguments()[0];
			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				DepartObjective preDepartObjective = new DepartObjective();				
				log.setEntity_id(departObjective.getId());
				log.setEntity_name(
							departObjective.getDepart_name()==null?
							(preDepartObjective.getDepart_name()+" "+preDepartObjective.getYears()):
							departObjective.getDepart_name()+" "+departObjective.getYears()
				);
				
				details = PubMethod.getLogDetails(log,DepartObjective.class, preDepartObjective,departObjective);	
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				DepartObjective preDepartObjective = departObjectiveService.getDepartObjective(departObjective.getId());	
				log.setEntity_id(departObjective.getId());
				log.setEntity_name(
						departObjective.getDepart_name()==null?
						(preDepartObjective.getDepart_name()+" "+preDepartObjective.getYears()):
						departObjective.getDepart_name()+" "+departObjective.getYears()
				);
				
				details = PubMethod.getLogDetails(log,DepartObjective.class, preDepartObjective,departObjective);	
				log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	
	
	
	

}
