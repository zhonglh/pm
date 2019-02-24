package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

/**
 * 计算餐补
 * @author zhonglihong
 * @date 2016年4月10日 下午7:40:47
 */
public class Meals_allowance  extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(AbstractSalary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		
		double result = salary.getMeal_allowance() * salary.getWork_days();
		result = NumberKit.getNumberFormatByDouble(result);		
		salary.setMeals_allowance(result);

	}

}
