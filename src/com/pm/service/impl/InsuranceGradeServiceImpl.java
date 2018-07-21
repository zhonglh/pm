package com.pm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IInsuranceGradeDao;
import com.pm.domain.business.InsuranceGrade;
import com.pm.service.IInsuranceGradeService;

@Component
public class InsuranceGradeServiceImpl implements IInsuranceGradeService {


	@Autowired IInsuranceGradeDao insuranceGradeDao;
	
	@Override
	public int  addInsuranceGrade(InsuranceGrade insuranceGrade) {
		return insuranceGradeDao.addInsuranceGrade(insuranceGrade);
	}
	

	@Override
	public int  updateInsuranceGrade(InsuranceGrade insuranceGrade) {
		return insuranceGradeDao.updateInsuranceGrade(insuranceGrade);
	}

	@Override
	public void deleteInsuranceGrade(InsuranceGrade[] insuranceGrades) {
		for(InsuranceGrade insuranceGrade : insuranceGrades){
			insuranceGradeDao.deleteInsuranceGrade(insuranceGrade);
		}

	}

	@Override
	public InsuranceGrade getInsuranceGrade(String insurance_grade_id) {		
		return insuranceGradeDao.getInsuranceGrade(insurance_grade_id);
	}

	@Override
	public boolean isExist(InsuranceGrade insuranceGrade) {
		return insuranceGradeDao.isExist(insuranceGrade);
	}

	@Override
	public List<InsuranceGrade> queryInsuranceGrade(
			InsuranceGrade insuranceGrade) {		
		return insuranceGradeDao.queryInsuranceGrade(insuranceGrade);
	}

}
