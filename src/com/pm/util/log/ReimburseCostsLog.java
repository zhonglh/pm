package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.ReimburseCosts;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IReimburseCostsService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ReimburseCostsLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IReimburseCostsService reimburseCostsService = SpringContextUtil.getApplicationContext().getBean(IReimburseCostsService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			ReimburseCosts[] reimburseCostss = (ReimburseCosts[])invocation.getArguments()[0];
			if(reimburseCostss == null || reimburseCostss.length == 0) return null;
			for(ReimburseCosts reimburseCosts : reimburseCostss){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				ReimburseCosts preReimburseCosts = reimburseCostsService.getReimburseCosts(reimburseCosts.getReimburse_id());
				if(preReimburseCosts == null) preReimburseCosts = new ReimburseCosts();
				log.setEntity_id(reimburseCosts.getReimburse_id());
				
				String entity_name = reimburseCosts.getProject_name() == null ?
						(preReimburseCosts.getUse_month() + ":" +preReimburseCosts.getProject_name()  + ":" + preReimburseCosts.getStaff_name()):
						(reimburseCosts.getUse_month() + ":" +reimburseCosts.getProject_name()  + ":" + ( reimburseCosts.getStaff_name()==null?(reimburseCosts.getStaff_id()==null?"":reimburseCosts.getStaff_id()):reimburseCosts.getStaff_name()));
				
				log.setEntity_name(entity_name);
				log.setProject_id(reimburseCosts.getProject_id()==null?preReimburseCosts.getProject_id():reimburseCosts.getProject_id());
				List<LogDetail>  details = PubMethod.getLogDetails(log,ReimburseCosts.class, preReimburseCosts,reimburseCosts);	
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				ReimburseCosts reimburseCosts = (ReimburseCosts)invocation.getArguments()[0];
				ReimburseCosts preReimburseCosts = new ReimburseCosts();				
				log.setEntity_id(reimburseCosts.getReimburse_id());

				String entity_name = reimburseCosts.getProject_name() == null ?
						(preReimburseCosts.getUse_month() + ":" +preReimburseCosts.getProject_name()  + ":" + preReimburseCosts.getStaff_name()):
						(reimburseCosts.getUse_month() + ":" +reimburseCosts.getProject_name()  + ":" + ( reimburseCosts.getStaff_name()==null?(reimburseCosts.getStaff_id()==null?"":reimburseCosts.getStaff_id()):reimburseCosts.getStaff_name()));
				log.setEntity_name(entity_name);
				log.setProject_id(reimburseCosts.getProject_id()==null?preReimburseCosts.getProject_id():reimburseCosts.getProject_id());
				details = PubMethod.getLogDetails(log,ReimburseCosts.class, preReimburseCosts,reimburseCosts);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				ReimburseCosts reimburseCosts = (ReimburseCosts)invocation.getArguments()[0];
				ReimburseCosts preReimburseCosts = reimburseCostsService.getReimburseCosts(reimburseCosts.getReimburse_id());				
				log.setEntity_id(reimburseCosts.getReimburse_id());

				String entity_name = reimburseCosts.getProject_name() == null ?
						(preReimburseCosts.getUse_month() + ":" +preReimburseCosts.getProject_name()  + ":" + preReimburseCosts.getStaff_name()):
						(reimburseCosts.getUse_month() + ":" +reimburseCosts.getProject_name()  + ":" + ( reimburseCosts.getStaff_name()==null?(reimburseCosts.getStaff_id()==null?"":reimburseCosts.getStaff_id()):reimburseCosts.getStaff_name()));
						
				log.setEntity_name(entity_name);
				log.setProject_id(reimburseCosts.getProject_id()==null?preReimburseCosts.getProject_id():reimburseCosts.getProject_id());
				details = PubMethod.getLogDetails(log,ReimburseCosts.class, preReimburseCosts,reimburseCosts);				
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				ReimburseCosts reimburseCosts = (ReimburseCosts)invocation.getArguments()[0];
				ReimburseCosts preReimburseCosts = reimburseCostsService.getReimburseCosts(reimburseCosts.getReimburse_id());				
				log.setEntity_id(reimburseCosts.getReimburse_id());

				String entity_name = reimburseCosts.getProject_name() == null ?
						(preReimburseCosts.getUse_month() + ":" +preReimburseCosts.getProject_name()  + ":" + preReimburseCosts.getStaff_name()):
						(reimburseCosts.getUse_month() + ":" +reimburseCosts.getProject_name()  + ":" + ( reimburseCosts.getStaff_name()==null?(reimburseCosts.getStaff_id()==null?"":reimburseCosts.getStaff_id()):reimburseCosts.getStaff_name()));
						
				log.setEntity_name(entity_name);
				log.setProject_id(reimburseCosts.getProject_id()==null?preReimburseCosts.getProject_id():reimburseCosts.getProject_id());
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	

}
