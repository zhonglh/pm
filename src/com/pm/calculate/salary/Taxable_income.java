package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

/**
 * 计算应纳税所得额
 * @author zhonglihong
 * @date 2016年4月10日 下午7:49:04
 */
public class Taxable_income extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(AbstractSalary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;

		double should_salary = salary.getShould_salary();
		double deductions_cost = salary.getDeductions_cost();


		result = should_salary - deductions_cost - 5000;

		//增加 专项附加扣除
		result -= salary.getChildren_education();
		result -= salary.getContinuing_education();
		result -= salary.getSupport_elderly();
		if(salary.getHousing_loans() > 0){
			result -= salary.getHousing_loans();
		}else {
			result -= salary.getHousing_rent();
		}

		if(result < 0) {
			result = 0;
		}
		result = NumberKit.getNumberFormatByDouble(result);	
		salary.setTaxable_income(result);

	}

}
