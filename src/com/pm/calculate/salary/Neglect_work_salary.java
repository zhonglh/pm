package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

/**
 * 旷工工资
 * @author zhonglihong
 * @date 2016年4月10日 下午3:51:19
 */
public class Neglect_work_salary extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(AbstractSalary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;

		double should_work_days = salary.getShould_work_days();
		double neglect_work_days = salary.getNeglect_work_days();
		if(should_work_days != 0 && neglect_work_days!=0){
			result = getCountSalary(salary)/should_work_days*neglect_work_days*2;
			result = NumberKit.getNumberFormatByDouble(result);					
		}
		salary.setNeglect_work_salary(result);	

	}

}
