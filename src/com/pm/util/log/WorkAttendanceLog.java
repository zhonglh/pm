package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.beans.Pager;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.WorkAttendance;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IWorkAttendanceService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;

public class WorkAttendanceLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation,
			MethodInvocation invocation, User sessionUser) {
		
		IWorkAttendanceService workAttendanceService = SpringContextUtil.getApplicationContext().getBean(IWorkAttendanceService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){

			UserPermit userPermit = new UserPermit();
			userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
			WorkAttendance[] workAttendances = (WorkAttendance[])invocation.getArguments()[0];
			if(workAttendances == null || workAttendances.length == 0) return null;
			
			for(WorkAttendance workAttendanceSearch : workAttendances){
				
				Pager<WorkAttendance> pager = workAttendanceService.queryWorkAttendance(workAttendanceSearch, userPermit, PubMethod.getPagerByAll(null, WorkAttendance.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				
				for(WorkAttendance workAttendance1 : pager.getResultList()){	
					WorkAttendance workAttendance = new WorkAttendance();
					workAttendance.setAttendance_id(workAttendance1.getAttendance_id());
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					WorkAttendance preWorkAttendance = workAttendanceService.getWorkAttendance(workAttendance.getAttendance_id());
					if(preWorkAttendance == null) preWorkAttendance = new WorkAttendance();
					log.setEntity_id(workAttendance.getAttendance_id());
					String entity_name = workAttendance.getStaff_name()==null?
							preWorkAttendance.getStaff_name()+"  "+ preWorkAttendance.getAttendance_month():
							workAttendance.getStaff_name()+" "+ workAttendance.getAttendance_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(workAttendance1.getProject_id()==null?preWorkAttendance.getProject_id():workAttendance1.getProject_id());
					
					List<LogDetail>  details = PubMethod.getLogDetails(log,WorkAttendance.class, preWorkAttendance,workAttendance);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}
			
		}else {
			

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				List<WorkAttendance> workAttendances = null;
				
				Object obj = invocation.getArguments()[0];
				if(obj instanceof WorkAttendance){
					workAttendances = new ArrayList<WorkAttendance>();
					workAttendances.add((WorkAttendance)obj);
				}else {
					workAttendances = (List<WorkAttendance>)obj;
				}
				
				
				for(WorkAttendance workAttendance : workAttendances){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					WorkAttendance preWorkAttendance = new WorkAttendance();				
					log.setEntity_id(workAttendance.getAttendance_id());
					String entity_name = workAttendance.getStaff_name()==null?
							preWorkAttendance.getStaff_name()+"  "+ preWorkAttendance.getAttendance_month():
							workAttendance.getStaff_name()+" "+ workAttendance.getAttendance_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(workAttendance.getProject_id()==null?preWorkAttendance.getProject_id():workAttendance.getProject_id());
					details = PubMethod.getLogDetails(log,WorkAttendance.class, preWorkAttendance,workAttendance);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				List<WorkAttendance> workAttendances = (List<WorkAttendance>)invocation.getArguments()[0];
				for(WorkAttendance workAttendance : workAttendances){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					if(workAttendance.getAttendance_id() != null && workAttendance.getAttendance_id().length() > 0){
						WorkAttendance preWorkAttendance = workAttendanceService.getWorkAttendance(workAttendance.getAttendance_id());				
						log.setEntity_id(workAttendance.getAttendance_id());
						String entity_name = workAttendance.getStaff_name()==null?
								preWorkAttendance.getStaff_name()+"  "+ preWorkAttendance.getAttendance_month():
								workAttendance.getStaff_name()+" "+ workAttendance.getAttendance_month() ;
						log.setEntity_name(entity_name);
						log.setProject_id(workAttendance.getProject_id()==null?preWorkAttendance.getProject_id():workAttendance.getProject_id());
						details = PubMethod.getLogDetails(log,WorkAttendance.class, preWorkAttendance,workAttendance);					
						log.setDetails(details);
					}else {
						WorkAttendance preWorkAttendance = new WorkAttendance();				
						log.setEntity_id(workAttendance.getAttendance_id());
						String entity_name = workAttendance.getStaff_name()==null?
								preWorkAttendance.getStaff_name()+"  "+ preWorkAttendance.getAttendance_month():
								workAttendance.getStaff_name()+" "+ workAttendance.getAttendance_month() ;
						log.setEntity_name(entity_name);
						log.setProject_id(workAttendance.getProject_id()==null?preWorkAttendance.getProject_id():workAttendance.getProject_id());
						details = PubMethod.getLogDetails(log,WorkAttendance.class, preWorkAttendance,workAttendance);							
						log.setDetails(details);
					}
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				List<WorkAttendance> workAttendances = (List<WorkAttendance>)invocation.getArguments()[0];
				for(WorkAttendance workAttendance : workAttendances){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					WorkAttendance preWorkAttendance = workAttendanceService.getWorkAttendance(workAttendance.getAttendance_id());				
					log.setEntity_id(workAttendance.getAttendance_id());
					String entity_name = workAttendance.getStaff_name()==null?
							preWorkAttendance.getStaff_name()+"  "+ preWorkAttendance.getAttendance_month():
							workAttendance.getStaff_name()+" "+ workAttendance.getAttendance_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(workAttendance.getProject_id()==null?preWorkAttendance.getProject_id():workAttendance.getProject_id());
					
					logs.add(log);
				}
			}
			
			
		}
		
		return logs;
	}
}
