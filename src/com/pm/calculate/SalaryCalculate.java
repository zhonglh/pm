package com.pm.calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pm.calculate.salary.Actual_computer_allowance;
import com.pm.calculate.salary.Actual_salary;
import com.pm.calculate.salary.Actual_tax_bonus;
import com.pm.calculate.salary.Actual_travel_allowance;
import com.pm.calculate.salary.Deductions_cost;
import com.pm.calculate.salary.Late_salary;
import com.pm.calculate.salary.Meals_allowance;
import com.pm.calculate.salary.Neglect_work_salary;
import com.pm.calculate.salary.Overtime_allowance;
import com.pm.calculate.salary.Personal_income_tax;
import com.pm.calculate.salary.Should_salary;
import com.pm.calculate.salary.Sick_leave_salary;
import com.pm.calculate.salary.Taxable_income;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

public class SalaryCalculate {

	private static class LazyHolder {
		private static final SalaryCalculate INSTANCE = new SalaryCalculate();
	}

	private List<ISalaryCalculate> calculates;

	private SalaryCalculate() {
		calculates = new ArrayList<ISalaryCalculate>();
		calculates.add(new Sick_leave_salary());
		calculates.add(new Neglect_work_salary());
		calculates.add(new Late_salary());
		calculates.add(new Actual_travel_allowance());
		calculates.add(new Actual_computer_allowance());
		calculates.add(new Overtime_allowance());
		calculates.add(new Meals_allowance());
		calculates.add(new Actual_tax_bonus());
		calculates.add(new Should_salary());
		calculates.add(new Deductions_cost());
		calculates.add(new Taxable_income());
		calculates.add(new Personal_income_tax());
		calculates.add(new Actual_salary());
	}

	public static SalaryCalculate getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
		try{
			for (ISalaryCalculate calculate : calculates) {
				calculate.calculate(salary, paramMap, paramExtMap);
			}
		}catch(Exception e){
			
		}
	}

}
