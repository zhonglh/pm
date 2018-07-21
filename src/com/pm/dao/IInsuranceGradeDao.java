package com.pm.dao;

import java.util.List;

import com.pm.domain.business.InsuranceGrade;

public interface IInsuranceGradeDao {


	public int addInsuranceGrade(InsuranceGrade insuranceGrade) ;
	

	public int updateInsuranceGrade(InsuranceGrade insuranceGrade) ; 
	

	public void deleteInsuranceGrade(InsuranceGrade insuranceGrades) ;
	
	
	public InsuranceGrade getInsuranceGrade(String insurance_grade_id) ;
	
	
	public boolean isExist(InsuranceGrade insuranceGrade);
	

	public List<InsuranceGrade> queryInsuranceGrade(InsuranceGrade insuranceGrade);
	
	
}
