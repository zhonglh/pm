package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.ProjectExpend;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IProjectExpendService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ProjectExpendLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IProjectExpendService projectExpendService = SpringContextUtil.getApplicationContext().getBean(IProjectExpendService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			ProjectExpend[] projectExpends = (ProjectExpend[])invocation.getArguments()[0];
			if(projectExpends == null || projectExpends.length == 0) return null;
			for(ProjectExpend projectExpend : projectExpends){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				ProjectExpend preProjectExpend = projectExpendService.getProjectExpend(projectExpend.getProject_expend_id());
				if(preProjectExpend == null) preProjectExpend = new ProjectExpend();
				log.setEntity_id(projectExpend.getProject_expend_id());
				String entity_name = projectExpend.getProject_name() == null ?
						(preProjectExpend.getUse_month() + ":" +preProjectExpend.getProject_name()  + ":" + preProjectExpend.getSub_contractor_name()):
						(projectExpend.getUse_month() + ":" +projectExpend.getProject_name()  + ":" + projectExpend.getSub_contractor_name());
				log.setEntity_name(entity_name);
				log.setProject_id(projectExpend.getProject_id()==null?preProjectExpend.getProject_id() : projectExpend.getProject_id());
				List<LogDetail>  details = PubMethod.getLogDetails(log,ProjectExpend.class, preProjectExpend,projectExpend);	
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				ProjectExpend projectExpend = (ProjectExpend)invocation.getArguments()[0];
				ProjectExpend preProjectExpend = new ProjectExpend();				
				log.setEntity_id(projectExpend.getProject_expend_id());
				String entity_name = projectExpend.getProject_name() == null ?
						(preProjectExpend.getUse_month() + ":" +preProjectExpend.getProject_name()  + ":" + preProjectExpend.getSub_contractor_name()):
						(projectExpend.getUse_month() + ":" +projectExpend.getProject_name()  + ":" + projectExpend.getSub_contractor_name());
				log.setEntity_name(entity_name);
				log.setProject_id(projectExpend.getProject_id()==null?preProjectExpend.getProject_id() : projectExpend.getProject_id());
				details = PubMethod.getLogDetails(log,ProjectExpend.class, preProjectExpend,projectExpend);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				ProjectExpend projectExpend = (ProjectExpend)invocation.getArguments()[0];
				ProjectExpend preProjectExpend = projectExpendService.getProjectExpend(projectExpend.getProject_expend_id());				
				log.setEntity_id(projectExpend.getProject_expend_id());
				String entity_name = projectExpend.getProject_name() == null ?
						(preProjectExpend.getUse_month() + ":" +preProjectExpend.getProject_name()  + ":" + preProjectExpend.getSub_contractor_name()):
						(projectExpend.getUse_month() + ":" +projectExpend.getProject_name()  + ":" + projectExpend.getSub_contractor_name());
				log.setEntity_name(entity_name);
				log.setProject_id(projectExpend.getProject_id()==null?preProjectExpend.getProject_id() : projectExpend.getProject_id());
				details = PubMethod.getLogDetails(log,ProjectExpend.class, preProjectExpend,projectExpend);				
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				ProjectExpend projectExpend = (ProjectExpend)invocation.getArguments()[0];
				ProjectExpend preProjectExpend = projectExpendService.getProjectExpend(projectExpend.getProject_expend_id());				
				log.setEntity_id(projectExpend.getProject_expend_id());
				String entity_name = projectExpend.getProject_name() == null ?
						(preProjectExpend.getUse_month() + ":" +preProjectExpend.getProject_name()  + ":" + preProjectExpend.getSub_contractor_name()):
						(projectExpend.getUse_month() + ":" +projectExpend.getProject_name()  + ":" + projectExpend.getSub_contractor_name());
				log.setEntity_name(entity_name);
				log.setProject_id(projectExpend.getProject_id()==null?preProjectExpend.getProject_id() : projectExpend.getProject_id());
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	
	
	
	

}