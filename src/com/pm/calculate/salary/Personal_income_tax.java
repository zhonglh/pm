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
		double result = 0.0;


		double taxableIncome = salary.getTaxable_income();
		double paySalary = taxableIncome;
		
		


		if(paySalary > 80000){
			result = taxableIncome * 0.45 - 15160;
		}else if(paySalary > 55000){
			result = taxableIncome * 0.35 - 7160;
		}else if(paySalary > 35000){
			result = taxableIncome * 0.30 - 4410;
		}else if(paySalary > 25000){
			result = taxableIncome * 0.25 - 2660;
		}else if(paySalary > 12000){
			result = taxableIncome * 0.20 - 1410;
		}else if(paySalary > 3000){
			result = taxableIncome * 0.10 - 210;
		}else if(paySalary > 0){
			result = taxableIncome * 0.03;
		}else {
			result = 0;
		}

		if(result < 0) {result = 0;}
		result = NumberKit.getNumberFormatByDouble(result);	
		salary.setPersonal_income_tax(result);

	}




	public static void main(String[] args) {


		System.out.println(NumberKit.getNumberFormatByDouble(45.166));
		System.out.println(NumberKit.getNumberFormatByDouble(45.117));
		System.out.println(NumberKit.getNumberFormatByDouble(45.121));
		System.out.println(NumberKit.getNumberFormatByDouble(45.2));

	}

}