package com.pm.calculate.salary;

import java.util.Map;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

/**
 * 计算个人所得税
 * @author zhonglihong
 * @date 2016年4月10日 下午7:50:32
 */
public class Personal_income_tax extends AbstractSalaryClculate implements ISalaryCalculate {

	@Override
	public void calculate(Salary salary,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {

		salary.setPersonal_income_tax(salary.getAccumulated_replenishment_tax());

	}




	public static void main(String[] args) {


		System.out.println(NumberKit.getNumberFormatByDouble(45.166));
		System.out.println(NumberKit.getNumberFormatByDouble(45.117));
		System.out.println(NumberKit.getNumberFormatByDouble(45.121));
		System.out.println(NumberKit.getNumberFormatByDouble(45.2));

	}

}