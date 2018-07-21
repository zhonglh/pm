package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.beans.Pager;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.Salary;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.ISalaryService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;

public class SalaryLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation,
			MethodInvocation invocation, User sessionUser) {
		
		ISalaryService salaryService = SpringContextUtil.getApplicationContext().getBean(ISalaryService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){

			UserPermit userPermit = new UserPermit();
			userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
			Salary[] salarys = (Salary[])invocation.getArguments()[0];
			if(salarys == null || salarys.length == 0) return null;
			
			for(Salary salarySearch : salarys){
				
				Pager<Salary> pager = salaryService.querySalary(salarySearch, userPermit, PubMethod.getPagerByAll(null, Salary.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				
				for(Salary salary1 : pager.getResultList()){	
					
					if(salary1.getVerify_userid() != null && !salary1.getVerify_userid().isEmpty()){
						//已经审核的不会删除
						continue;
					}
					
					Salary salary = new Salary();
					salary.setSalary_id(salary1.getSalary_id());
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					Salary preSalary = salaryService.getSalary(salary.getSalary_id());
					if(preSalary == null) preSalary = new Salary();
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(salary1.getProject_id()==null?preSalary.getProject_id():salary1.getProject_id());
					List<LogDetail>  details = PubMethod.getLogDetails(log,Salary.class, preSalary,salary);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}
			
		}else {
			

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				List<Salary> salarys = (List<Salary>)invocation.getArguments()[0];
				for(Salary salary : salarys){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					Salary preSalary = new Salary();				
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(salary.getProject_id()==null?preSalary.getProject_id():salary.getProject_id());
					details = PubMethod.getLogDetails(log,Salary.class, preSalary,salary);	
					
					log.setDetails(details);
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				List<Salary> salarys = (List<Salary>)invocation.getArguments()[0];
				for(Salary salary : salarys){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					Salary preSalary = salaryService.getSalary(salary.getSalary_id());				
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);
					log.setProject_id(salary.getProject_id()==null?preSalary.getProject_id():salary.getProject_id());
					details = PubMethod.getLogDetails(log,Salary.class, preSalary,salary);		
					
					log.setDetails(details);
					logs.add(log);
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){

				Salary[] salarys = (Salary[])invocation.getArguments()[0];
				for(Salary salary : salarys){
					Log log = super.getLog(methodAnnotation, invocation,sessionUser );
					Salary preSalary = salaryService.getSalary(salary.getSalary_id());				
					log.setEntity_id(salary.getSalary_id());
					String entity_name = salary.getStaff_name()==null?
							preSalary.getStaff_name()+"  "+ preSalary.getSalary_month():
							salary.getStaff_name()+" "+ salary.getSalary_month() ;
					log.setEntity_name(entity_name);		
					log.setProject_id(salary.getProject_id()==null?preSalary.getProject_id():salary.getProject_id());			
					logs.add(log);
				}
			}
			
			
		}
		
		return logs;
	}
}
