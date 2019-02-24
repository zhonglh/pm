package com.pm.calculate.salary;

import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.AbstractSalary;

public abstract class AbstractSalaryClculate implements ISalaryCalculate{
	
	public double getCountSalary(AbstractSalary salary){

		double basic_salary = salary.getBasic_salary();
		double post_salary = salary.getPost_salary();
		double performance_allowances = salary.getPerformance_allowances();
		
		return basic_salary + post_salary + performance_allowances;
	}
}
