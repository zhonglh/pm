package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 准许扣除的费用
 * @author zhonglihong
 * @date 2016年4月10日 下午7:47:15
 */
public class Deductions_cost extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;
		
		double pension_insurance = salary.getPension_insurance();
		double unemployment_insurance = salary.getUnemployment_insurance();
		double medical_Insurance = salary.getMedical_Insurance();
		double accumulation_fund = salary.getAccumulation_fund();
		
		
		result = pension_insurance + unemployment_insurance;
		result = result + medical_Insurance + accumulation_fund;


		result = NumberKit.getNumberFormatByDouble(result);		
		salary.setDeductions_cost(result);

	}

}
