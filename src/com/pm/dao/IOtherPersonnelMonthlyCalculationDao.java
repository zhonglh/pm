package com.pm.dao;

import java.util.List;

import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.Salary;

public interface IOtherPersonnelMonthlyCalculationDao {
	

	/**
	 * 根据考勤的项目ID和月份 获取 转正和加减薪信息里的当月薪资和备注信息
	 * @param search
	 * @return
	 */
	public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(Salary search);

}
