package com.pm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IInsuranceGradeDao;
import com.pm.domain.business.InsuranceGrade;

@Component
public class InsuranceGradeDaoImpl extends BatisDao implements
		IInsuranceGradeDao {

	@Override
	public int addInsuranceGrade(InsuranceGrade insuranceGrade) {
		String sql = "InsuranceGradeMapping.addInsuranceGrade";
		return this.insert(sql, insuranceGrade);
	}

	@Override
	public int updateInsuranceGrade(InsuranceGrade insuranceGrade) {
		String sql = "InsuranceGradeMapping.updateInsuranceGrade";
		return this.update(sql, insuranceGrade);
	}

	@Override
	public void deleteInsuranceGrade(InsuranceGrade insuranceGrade) {
		String sql = "InsuranceGradeMapping.deleteInsuranceGrade";
		this.update(sql, insuranceGrade);
	}

	@Override
	public InsuranceGrade getInsuranceGrade(String insurance_grade_id) {
		InsuranceGrade insuranceGrade = new InsuranceGrade();
		insuranceGrade.setInsurance_grade_id(insurance_grade_id);
		List<InsuranceGrade> list = this.queryInsuranceGrade(insuranceGrade);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}

	@Override
	public boolean isExist(InsuranceGrade insuranceGrade) {

		String sql = "InsuranceGradeMapping.isExist";
		List<InsuranceGrade> list =  this.query(sql, InsuranceGrade.class, insuranceGrade);
		if(list == null || list.isEmpty()) return false;
		else return true;
	}

	@Override
	public List<InsuranceGrade> queryInsuranceGrade( InsuranceGrade insuranceGrade) {

		String sql = "InsuranceGradeMapping.queryInsuranceGrade";
		return this.query(sql, InsuranceGrade.class, insuranceGrade);
	}

}
