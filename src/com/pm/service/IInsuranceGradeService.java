package com.pm.service;

import java.util.List;

import com.pm.domain.business.InsuranceGrade;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IInsuranceGradeService {
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.INSURANCE_GRADE)
	public int addInsuranceGrade(InsuranceGrade insuranceGrade) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.INSURANCE_GRADE)
	public int updateInsuranceGrade(InsuranceGrade insuranceGrade) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.INSURANCE_GRADE)
	public void deleteInsuranceGrade(InsuranceGrade[] insuranceGrades) ;
	
	
	public InsuranceGrade getInsuranceGrade(String insurance_grade_id) ;
	
	
	public boolean isExist(InsuranceGrade insuranceGrade);
	

	public List<InsuranceGrade> queryInsuranceGrade(InsuranceGrade insuranceGrade);
	

}
