package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IInsuranceGradeService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class InsuranceGradeLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IInsuranceGradeService insuranceGradeService = SpringContextUtil.getApplicationContext().getBean(IInsuranceGradeService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			InsuranceGrade[] insuranceGrades = (InsuranceGrade[])invocation.getArguments()[0];
			if(insuranceGrades == null || insuranceGrades.length == 0) return null;
			for(InsuranceGrade insuranceGrade : insuranceGrades){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				InsuranceGrade preInsuranceGrade = insuranceGradeService.getInsuranceGrade(insuranceGrade.getInsurance_grade_id());
				if(preInsuranceGrade == null) preInsuranceGrade = new InsuranceGrade();
				log.setEntity_id(insuranceGrade.getInsurance_grade_id());
				log.setEntity_name(String.valueOf(insuranceGrade.getInsurance_radix()==null?preInsuranceGrade.getInsurance_radix():insuranceGrade.getInsurance_radix()));
				List<LogDetail>  details = PubMethod.getLogDetails(log,InsuranceGrade.class, preInsuranceGrade,insuranceGrade);	
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				InsuranceGrade insuranceGrade = (InsuranceGrade)invocation.getArguments()[0];
				InsuranceGrade preInsuranceGrade = new InsuranceGrade();				
				log.setEntity_id(insuranceGrade.getInsurance_grade_id());
				log.setEntity_name(String.valueOf(insuranceGrade.getInsurance_radix()==null?preInsuranceGrade.getInsurance_radix():insuranceGrade.getInsurance_radix()));
				details = PubMethod.getLogDetails(log,InsuranceGrade.class, preInsuranceGrade,insuranceGrade);				
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				InsuranceGrade insuranceGrade = (InsuranceGrade)invocation.getArguments()[0];
				InsuranceGrade preInsuranceGrade = insuranceGradeService.getInsuranceGrade(insuranceGrade.getInsurance_grade_id());				
				log.setEntity_id(insuranceGrade.getInsurance_grade_id());
				log.setEntity_name(String.valueOf(insuranceGrade.getInsurance_radix()==null?preInsuranceGrade.getInsurance_radix():insuranceGrade.getInsurance_radix()));
				details = PubMethod.getLogDetails(log,InsuranceGrade.class, preInsuranceGrade,insuranceGrade);				
				log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	
	
	
	

}