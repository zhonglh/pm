
package com.pm.calculate.salary;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 病假工资计算
 * @author zhonglihong
 * @date 2016年4月10日 下午3:45:24
 */
public class Sick_leave_salary extends AbstractSalaryClculate implements ISalaryCalculate{

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
		double result = 0.0;
		double should_work_days = salary.getShould_work_days();
		double sick_leave_days = salary.getSick_leave_days();
		
		if(should_work_days != 0){
			try{
				ParamExtend paramExtend = null;
				if(paramExtMap != null) {
					paramExtend = paramExtMap.get("sick_leave_salary");
				}
				if(paramExtend != null && "1".equals(paramExtend.getType1()) && StringUtils.isNotEmpty(paramExtend.getExpression()) ){
					result = this.getCountSalary(salary)/should_work_days*sick_leave_days*Double.parseDouble(paramExtend.getProcessor());
				}else if(paramExtend != null && "2".equals(paramExtend.getType1()) && StringUtils.isNotEmpty(paramExtend.getExpression()) ){
					result = Double.parseDouble(paramExtend.getRealVal())/should_work_days*sick_leave_days*Double.parseDouble(paramExtend.getProcessor());
				}else {
					result = this.getCountSalary(salary)/should_work_days*sick_leave_days*0.5;
				}
			}catch(Exception e){
				result = this.getCountSalary(salary)/should_work_days*sick_leave_days*0.5;
			}
			result = NumberKit.getNumberFormatByDouble(result);		
			salary.setSick_leave_salary(result);
		}
	}

}
