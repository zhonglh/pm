package com.pm.util.log;

import com.common.beans.Pager;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.OtherWorkAttendance;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IOtherWorkAttendanceService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;
import org.aopalliance.intercept.MethodInvocation;

import java.util.ArrayList;
import java.util.List;

public class OtherWorkAttendanceLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation,
			MethodInvocation invocation, User sessionUser) {
		
		IOtherWorkAttendanceService otherWorkAttendanceService = SpringContextUtil.getApplicationContext().getBean(IOtherWorkAttendanceService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){

			UserPermit userPermit = new UserPermit();
			userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
			OtherWorkAttendance[] otherWorkAttendances = (OtherWorkAttendance[])invocation.getArguments()[0];
			if(otherWorkAttendances == null || otherWorkAttendances.length == 0) {
				return null;
			}
			
			for(OtherWorkAttendance otherWorkAttendanceSearch : otherWorkAttendances){
				
				Pager<OtherWorkAttendance> pager = otherWorkAttendanceService.queryWorkAttendance(otherWorkAttendanceSearch, userPermit, PubMethod.getPagerByAll(null, OtherWorkAttendance.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				
				for(OtherWorkAttendance otherWorkAttendance1 : pager.getResultList()){	
					OtherWorkAttendance otherWorkAttendance = new OtherWorkAttendance();
					otherWorkAttendance.setAttendance_id(otherWorkAttendance1.getAttendance_id());
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherWorkAttendance preOtherWorkAttendance = otherWorkAttendanceService.getWorkAttendance(otherWorkAttendance.getAttendance_id());
					if(preOtherWorkAttendance == null) {
						preOtherWorkAttendance = new OtherWorkAttendance();
					}
					log.setEntity_id(otherWorkAttendance.getAttendance_id());
					String entity_name = otherWorkAttendance.getStaff_name()==null?
							preOtherWorkAttendance.getStaff_name()+"  "+ preOtherWorkAttendance.getAttendance_month():
							otherWorkAttendance.getStaff_name()+" "+ otherWorkAttendance.getAttendance_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(otherWorkAttendance1.getDept_id()==null?preOtherWorkAttendance.getDept_id():otherWorkAttendance1.getDept_id());
					
					List<LogDetail>  details = PubMethod.getLogDetails(log,OtherWorkAttendance.class, preOtherWorkAttendance,otherWorkAttendance);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}
			
		}else {
			

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				List<OtherWorkAttendance> otherWorkAttendances = null;
				
				Object obj = invocation.getArguments()[0];
				if(obj instanceof OtherWorkAttendance){
					otherWorkAttendances = new ArrayList<OtherWorkAttendance>();
					otherWorkAttendances.add((OtherWorkAttendance)obj);
				}else {
					otherWorkAttendances = (List<OtherWorkAttendance>)obj;
				}
				
				
				for(OtherWorkAttendance otherWorkAttendance : otherWorkAttendances){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherWorkAttendance preOtherWorkAttendance = new OtherWorkAttendance();				
					log.setEntity_id(otherWorkAttendance.getAttendance_id());
					String entity_name = otherWorkAttendance.getStaff_name()==null?
							preOtherWorkAttendance.getStaff_name()+"  "+ preOtherWorkAttendance.getAttendance_month():
							otherWorkAttendance.getStaff_name()+" "+ otherWorkAttendance.getAttendance_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(otherWorkAttendance.getDept_id()==null?preOtherWorkAttendance.getDept_id():otherWorkAttendance.getDept_id());
					details = PubMethod.getLogDetails(log,OtherWorkAttendance.class, preOtherWorkAttendance,otherWorkAttendance);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				List<OtherWorkAttendance> otherWorkAttendances = (List<OtherWorkAttendance>)invocation.getArguments()[0];
				for(OtherWorkAttendance otherWorkAttendance : otherWorkAttendances){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					if(otherWorkAttendance.getAttendance_id() != null && otherWorkAttendance.getAttendance_id().length() > 0){
						OtherWorkAttendance preOtherWorkAttendance = otherWorkAttendanceService.getWorkAttendance(otherWorkAttendance.getAttendance_id());				
						log.setEntity_id(otherWorkAttendance.getAttendance_id());
						String entity_name = otherWorkAttendance.getStaff_name()==null?
								preOtherWorkAttendance.getStaff_name()+"  "+ preOtherWorkAttendance.getAttendance_month():
								otherWorkAttendance.getStaff_name()+" "+ otherWorkAttendance.getAttendance_month() ;
						log.setEntity_name(entity_name);
						log.setProject_id(otherWorkAttendance.getDept_id()==null?preOtherWorkAttendance.getDept_id():otherWorkAttendance.getDept_id());
						details = PubMethod.getLogDetails(log,OtherWorkAttendance.class, preOtherWorkAttendance,otherWorkAttendance);					
						log.setDetails(details);
					}else {
						OtherWorkAttendance preOtherWorkAttendance = new OtherWorkAttendance();				
						log.setEntity_id(otherWorkAttendance.getAttendance_id());
						String entity_name = otherWorkAttendance.getStaff_name()==null?
								preOtherWorkAttendance.getStaff_name()+"  "+ preOtherWorkAttendance.getAttendance_month():
								otherWorkAttendance.getStaff_name()+" "+ otherWorkAttendance.getAttendance_month() ;
						log.setEntity_name(entity_name);
						log.setProject_id(otherWorkAttendance.getDept_id()==null?preOtherWorkAttendance.getDept_id():otherWorkAttendance.getDept_id());
						details = PubMethod.getLogDetails(log,OtherWorkAttendance.class, preOtherWorkAttendance,otherWorkAttendance);							
						log.setDetails(details);
					}
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				List<OtherWorkAttendance> otherWorkAttendances = (List<OtherWorkAttendance>)invocation.getArguments()[0];
				for(OtherWorkAttendance otherWorkAttendance : otherWorkAttendances){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherWorkAttendance preOtherWorkAttendance = otherWorkAttendanceService.getWorkAttendance(otherWorkAttendance.getAttendance_id());				
					log.setEntity_id(otherWorkAttendance.getAttendance_id());
					String entity_name = otherWorkAttendance.getStaff_name()==null?
							preOtherWorkAttendance.getStaff_name()+"  "+ preOtherWorkAttendance.getAttendance_month():
							otherWorkAttendance.getStaff_name()+" "+ otherWorkAttendance.getAttendance_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(otherWorkAttendance.getDept_id()==null?preOtherWorkAttendance.getDept_id():otherWorkAttendance.getDept_id());
					
					logs.add(log);
				}
			}
			
			
		}
		
		return logs;
	}
}
