package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 计算个人所得税
 * @author zhonglihong
 * @date 2016年4月10日 下午7:50:32
 */
public class Personal_income_tax extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;
		

		double paySalary = salary.getShould_salary() - salary.getDeductions_cost();
		double taxableIncome = salary.getTaxable_income();
		
		


		if(paySalary > 83500){
			result = taxableIncome * 0.45 - 13505;
		}else if(paySalary > 58500){
			result = taxableIncome * 0.35 - 5505;
		}else if(paySalary > 38500){
			result = taxableIncome * 0.30 - 2755;
		}else if(paySalary > 12500){
			result = taxableIncome * 0.25 - 1005;
		}else if(paySalary > 8000){
			result = taxableIncome * 0.20 - 555;
		}else if(paySalary > 5000){
			result = taxableIncome * 0.10 - 105;
		}else if(paySalary > 3500){
			result = taxableIncome * 0.03;
		}

		if(result < 0) result = 0;
		result = NumberKit.getNumberFormatByDouble(result);	
		salary.setPersonal_income_tax(result);

	}

}
