package com.pm.util.log;

import com.pm.domain.business.OtherStaffCost;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;
import org.aopalliance.intercept.MethodInvocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class OtherStaffCostLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation, MethodInvocation invocation, User sessionUser) {
		
		List<Log> logs = new ArrayList<Log>();
		
		List<LogDetail> details = null;
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
			List<OtherStaffCost> ptherStaffCosts = (List<OtherStaffCost>)invocation.getArguments()[0];
			for(OtherStaffCost ptherStaffCost : ptherStaffCosts){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				OtherStaffCost preOtherStaffCost = new OtherStaffCost();				
				log.setEntity_id(ptherStaffCost.getOther_staff_cost_id());
				String entity_name = ptherStaffCost.getStaff_name()==null?
						preOtherStaffCost.getDept_name()+"  "+ preOtherStaffCost.getWork_month() + "  "+preOtherStaffCost.getDept_name():
						ptherStaffCost.getStaff_name()+" "+ ptherStaffCost.getWork_month() + "  "+ptherStaffCost.getDept_name();
				log.setEntity_name(entity_name);
				//log.setProject_id(ptherStaffCost.getProject_id()==null?preOtherStaffCost.getProject_id():ptherStaffCost.getProject_id());
				details = PubMethod.getLogDetails(log,OtherStaffCost.class, preOtherStaffCost,ptherStaffCost);	
				
				log.setDetails(details);
				logs.add(log);
			}
		}
		
		return logs;
	}

}
