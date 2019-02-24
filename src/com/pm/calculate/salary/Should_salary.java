package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

/**
 * 应发工资
 * @author zhonglihong
 * @date 2016年4月10日 下午7:45:21
 */
public class Should_salary extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(AbstractSalary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;

		double should_work_days = salary.getShould_work_days();
		double work_days = salary.getWork_days();
		double paid_leave_days = salary.getPaid_leave_days();

		if(should_work_days != 0){	
			result = getCountSalary(salary)/should_work_days*(work_days+paid_leave_days);
		}
		
		double sick_leave_salary = salary.getSick_leave_salary();


		double waiting_post_salary = salary.getWaiting_post_salary();
		double maternity_leave_salary = salary.getMaternity_leave_salary();

		double neglect_work_salary = salary.getNeglect_work_salary();
		double late_salary = salary.getLate_salary();
		double actual_travel_allowance = salary.getActual_travel_allowance();
		double actual_computer_allowance = salary.getActual_computer_allowance();

		double actual_extra_allowance = salary.getActual_extra_allowance();
		double overtime_allowance = salary.getOvertime_allowance();
		double meals_allowance = salary.getMeals_allowance();
		double actual_tax_bonus = salary.getActual_tax_bonus();

		result = result + sick_leave_salary + waiting_post_salary + maternity_leave_salary - neglect_work_salary - late_salary;
		result = result + actual_travel_allowance + actual_computer_allowance;
		result = result + actual_extra_allowance + overtime_allowance;
		result = result + meals_allowance + actual_tax_bonus;		
		result = NumberKit.getNumberFormatByDouble(result);		
		salary.setShould_salary(result);	

	}

}
