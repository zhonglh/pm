package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ProjectStaffCostLog  extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		List<Log> logs = new ArrayList<Log>();
		
		List<LogDetail> details = null;
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
			List<ProjectStaffCost> projectStaffCosts = (List<ProjectStaffCost>)invocation.getArguments()[0];
			for(ProjectStaffCost projectStaffCost : projectStaffCosts){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				ProjectStaffCost preProjectStaffCost = new ProjectStaffCost();				
				log.setEntity_id(projectStaffCost.getProject_staffcost_id());
				String entity_name = projectStaffCost.getStaff_name()==null?
						preProjectStaffCost.getStaff_name()+"  "+ preProjectStaffCost.getWork_month() + "  "+preProjectStaffCost.getProject_name():
						projectStaffCost.getStaff_name()+" "+ projectStaffCost.getWork_month() + "  "+projectStaffCost.getProject_name();
				log.setEntity_name(entity_name);
				log.setProject_id(projectStaffCost.getProject_id()==null?preProjectStaffCost.getProject_id():projectStaffCost.getProject_id());
				details = PubMethod.getLogDetails(log,ProjectStaffCost.class, preProjectStaffCost,projectStaffCost);	
				
				log.setDetails(details);
				logs.add(log);
			}
		}
		
		return logs;
	}

}
