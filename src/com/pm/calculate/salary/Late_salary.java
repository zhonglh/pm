package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 计算迟到日期
 * @author zhonglihong
 * @date 2016年4月10日 下午7:29:49
 */
public class Late_salary extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		
		double result = salary.getLate_days() * 50;
		result = NumberKit.getNumberFormatByDouble(result);		
		salary.setLate_salary(result);

	}

}
