package com.pm.util.log;

import com.common.beans.Pager;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.OtherSalary;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IOtherSalaryService;
import com.pm.service.ISalaryService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;
import org.aopalliance.intercept.MethodInvocation;

import java.util.ArrayList;
import java.util.List;

public class OtherSalaryLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation,
			MethodInvocation invocation, User sessionUser) {

		IOtherSalaryService salaryService = SpringContextUtil.getApplicationContext().getBean(IOtherSalaryService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){

			UserPermit userPermit = new UserPermit();
			userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
			OtherSalary[] salarys = (OtherSalary[])invocation.getArguments()[0];
			if(salarys == null || salarys.length == 0) {
				return null;
			}
			
			for(OtherSalary salarySearch : salarys){
				
				Pager<OtherSalary> pager = salaryService.querySalary(salarySearch, userPermit, PubMethod.getPagerByAll(null, OtherSalary.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				
				for(OtherSalary salary1 : pager.getResultList()){	
					
					if(salary1.getVerify_userid() != null && !salary1.getVerify_userid().isEmpty()){
						//已经审核的不会删除
						continue;
					}
					
					OtherSalary salary = new OtherSalary();
					salary.setSalary_id(salary1.getSalary_id());
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherSalary preSalary = salaryService.getSalary(salary.getSalary_id());
					if(preSalary == null) {
						preSalary = new OtherSalary();
					}
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					List<LogDetail>  details = PubMethod.getLogDetails(log,OtherSalary.class, preSalary,salary);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}
			
		}else {
			

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				List<OtherSalary> salarys = (List<OtherSalary>)invocation.getArguments()[0];
				for(OtherSalary salary : salarys){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherSalary preSalary = new OtherSalary();				
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					details = PubMethod.getLogDetails(log,OtherSalary.class, preSalary,salary);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				List<OtherSalary> salarys = (List<OtherSalary>)invocation.getArguments()[0];
				for(OtherSalary salary : salarys){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherSalary preSalary = salaryService.getSalary(salary.getSalary_id());				
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					details = PubMethod.getLogDetails(log,OtherSalary.class, preSalary,salary);		
					
					log.setDetails(details);
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){

				OtherSalary[] salarys = (OtherSalary[])invocation.getArguments()[0];
				for(OtherSalary salary : salarys){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					OtherSalary preSalary = salaryService.getSalary(salary.getSalary_id());				
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					logs.add(log);
				}
			}
			
			
		}
		
		return logs;
	}
}
