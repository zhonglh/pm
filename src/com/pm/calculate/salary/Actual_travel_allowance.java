package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

/**
 * 计算出差补助
 * @author zhonglihong
 * @date 2016年4月10日 下午7:32:36
 */
public class Actual_travel_allowance  extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(AbstractSalary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
	
		double result = 0.0;

		double should_work_days = salary.getShould_work_days();
		double travel_allowance = salary.getTravel_allowance();
		double business_trip_days = salary.getBusiness_trip_days();
		if(should_work_days != 0){
			result = travel_allowance/should_work_days*business_trip_days;
			result = NumberKit.getNumberFormatByDouble(result);					
		}
		salary.setActual_travel_allowance(result);		

	}

}
