package com.pm.dao.impl;

import java.util.List;

import com.pm.dao.IOtherPersonnelMonthlyCalculationDao;
import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IPersonnelMonthlyCalculationDao;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.Salary;

@Component
public class OtherPersonnelMonthlyCalculationDaoImpl extends BatisDao implements IOtherPersonnelMonthlyCalculationDao {

	@Override
	public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(Salary search) {
		String sql = "OtherPersonnelMonthlyCalculationMapping.getCurrSalaryByWorkAttendance";
		return this.query(sql, PersonnelMonthlySalary.class,search);
	}

}
