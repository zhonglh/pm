package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 计算电脑补助
 * @author zhonglihong
 * @date 2016年4月10日 下午7:35:10
 */
public class Actual_computer_allowance  extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {

		double result = 0.0;

		double should_work_days = salary.getShould_work_days();
		double computer_allowance = salary.getComputer_allowance();
		double work_days = salary.getWork_days();
		if(should_work_days != 0 && work_days != 0){
			result = computer_allowance/should_work_days*work_days;
			result = NumberKit.getNumberFormatByDouble(result);					
		}
		salary.setActual_computer_allowance(result);
	}

}
