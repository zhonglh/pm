package com.pm.calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pm.calculate.salary.*;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * @author Administrator
 */
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

		calculates.add(new Accumulated_pretax_income());
		calculates.add(new Accumulated_tax_deduction());
		calculates.add(new Accumulated_children_education());
		calculates.add(new Accumulated_continuing_education());
		calculates.add(new Accumulated_housing_loans());
		calculates.add(new Accumulated_housing_rent());
		calculates.add(new Accumulated_support_elderly());
		calculates.add(new Accumulated_deductions_cost());
		calculates.add(new Accumulated_taxable_income());
		calculates.add(new Accumulated_deductible_taxpaid());

		calculates.add(new Accumulated_replenishment_tax());


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
