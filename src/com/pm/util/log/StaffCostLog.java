package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.StaffCost;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IStaffCostService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class StaffCostLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation,
			MethodInvocation invocation, User sessionUser) {
		
		IStaffCostService staffCostService = SpringContextUtil.getApplicationContext().getBean(IStaffCostService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			StaffCost[] staffCosts = (StaffCost[])invocation.getArguments()[0];
			if(staffCosts == null || staffCosts.length == 0) return null;
			for(StaffCost staffCost : staffCosts){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				StaffCost preStaffCost = staffCostService.getStaffCost(staffCost.getStaff_id());
				if(preStaffCost == null) preStaffCost = new StaffCost();
				log.setEntity_id(staffCost.getStaff_id());
				log.setEntity_name(staffCost.getStaff_name()==null?preStaffCost.getStaff_name():staffCost.getStaff_name());
				List<LogDetail>  details = PubMethod.getLogDetails(log,StaffCost.class, preStaffCost,staffCost);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				StaffCost staffCost = (StaffCost)invocation.getArguments()[0];
				StaffCost preStaffCost = new StaffCost();				
				log.setEntity_id(staffCost.getStaff_id());
				log.setEntity_name(staffCost.getStaff_name()==null?preStaffCost.getStaff_name():staffCost.getStaff_name());
				details = PubMethod.getLogDetails(log,StaffCost.class, preStaffCost,staffCost);
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				StaffCost staffCost = (StaffCost)invocation.getArguments()[0];
				StaffCost preStaffCost = staffCostService.getStaffCost(staffCost.getStaff_id());				
				log.setEntity_id(staffCost.getStaff_id());
				log.setEntity_name(staffCost.getStaff_name()==null?preStaffCost.getStaff_name():staffCost.getStaff_name());
				details = PubMethod.getLogDetails(log,StaffCost.class, preStaffCost,staffCost);	
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
			if(detail.getData_item_code().equals("outsource_staff") || detail.getData_item_code().equals("can_send_info")){
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
