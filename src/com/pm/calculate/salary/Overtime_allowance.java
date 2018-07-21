package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 计算加班补助
 * @author zhonglihong
 * @date 2016年4月10日 下午7:38:03
 */
public class Overtime_allowance extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		
		double result = salary.getWeekend_overtime_days() * 100;
		result = NumberKit.getNumberFormatByDouble(result);		
		salary.setOvertime_allowance(result);

	}

}
