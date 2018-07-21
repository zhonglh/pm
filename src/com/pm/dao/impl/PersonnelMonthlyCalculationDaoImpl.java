package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IPersonnelMonthlyCalculationDao;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.Salary;

@Component
public class PersonnelMonthlyCalculationDaoImpl extends BatisDao implements IPersonnelMonthlyCalculationDao {

	@Override
	public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(Salary search) {
		String sql = "PersonnelMonthlyCalculationMapping.getCurrSalaryByWorkAttendance";
		return this.query(sql, PersonnelMonthlySalary.class,search);
	}

}
