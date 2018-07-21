package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 计算实发工资
 * @author zhonglihong
 * @date 2016年4月10日 下午7:53:20
 */
public class Actual_salary extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
	
		double result = 0.0;
		
		double should_salary = salary.getShould_salary();
		double deductions_cost = salary.getDeductions_cost();
		double personal_income_tax = salary.getPersonal_income_tax();
		double actual_bonus = salary.getActual_bonus();
		double overdue_tax_salary = salary.getOverdue_tax_salary();

		result = should_salary - deductions_cost - personal_income_tax;
		result = result + actual_bonus + overdue_tax_salary ;

		result = NumberKit.getNumberFormatByDouble(result);	
		salary.setActual_salary(result);

	}

}
