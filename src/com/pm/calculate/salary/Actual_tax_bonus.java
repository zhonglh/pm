package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;


/**
 * 计算实发奖金(税前)
 * @author zhonglihong
 * @date 2016年4月10日 下午7:42:42
 */
public class Actual_tax_bonus extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;

		double should_work_days = salary.getShould_work_days();
		double tax_bonus = salary.getTax_bonus();
		double work_days = salary.getWork_days();
		double paid_leave_days = salary.getPaid_leave_days();
		if(should_work_days != 0){

			result = tax_bonus/should_work_days;
			if(result != 0){
				result = result * (work_days+paid_leave_days) ;
			}
			result = NumberKit.getNumberFormatByDouble(result);					
		}
		salary.setActual_tax_bonus(result);	

	}

}
