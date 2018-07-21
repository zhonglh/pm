package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IOtherStaffService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class OtherStaffLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IOtherStaffService otherStaffService = SpringContextUtil.getApplicationContext().getBean(IOtherStaffService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			OtherStaff[] otherStaffs = (OtherStaff[])invocation.getArguments()[0];
			if(otherStaffs == null || otherStaffs.length == 0) return null;
			for(OtherStaff otherStaff : otherStaffs){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				OtherStaff preOtherStaff = otherStaffService.getOtherStaff(otherStaff.getStaff_id());
				if(preOtherStaff == null) preOtherStaff = new OtherStaff();
				log.setEntity_id(otherStaff.getStaff_id());
				log.setEntity_name(otherStaff.getStaff_name()==null?preOtherStaff.getStaff_name():otherStaff.getStaff_name());
				List<LogDetail>  details = PubMethod.getLogDetails(log,OtherStaff.class, preOtherStaff,otherStaff);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				OtherStaff otherStaff = (OtherStaff)invocation.getArguments()[0];
				OtherStaff preOtherStaff = new OtherStaff();				
				log.setEntity_id(otherStaff.getStaff_id());
				log.setEntity_name(otherStaff.getStaff_name()==null?preOtherStaff.getStaff_name():otherStaff.getStaff_name());
				details = PubMethod.getLogDetails(log,OtherStaff.class, preOtherStaff,otherStaff);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				OtherStaff otherStaff = (OtherStaff)invocation.getArguments()[0];
				OtherStaff preOtherStaff = otherStaffService.getOtherStaff(otherStaff.getStaff_id());				
				log.setEntity_id(otherStaff.getStaff_id());
				log.setEntity_name(otherStaff.getStaff_name()==null?preOtherStaff.getStaff_name():otherStaff.getStaff_name());
				details = PubMethod.getLogDetails(log,OtherStaff.class, preOtherStaff,otherStaff);		
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
			if(detail.getData_item_code().equals("position_type")){
				if(detail.getOperation_after() != null && !detail.getOperation_after().isEmpty()){
					detail.setOperation_after(SpringContextUtil.getApplicationContext ().getMessage ("position.type."+detail.getOperation_after(), null, Locale.CHINA));
				}
				if(detail.getOperation_before() != null && !detail.getOperation_before().isEmpty()){
					detail.setOperation_before(SpringContextUtil.getApplicationContext ().getMessage ("position.type."+detail.getOperation_before(), null, Locale.CHINA));
				}
			}
		}
	}

}
