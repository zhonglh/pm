package com.common.utils;

import org.apache.commons.io.monitor.FileAlterationListener;

import com.pm.util.PubMethod;

/**
 * 个税计算器
 * @author zhonglihong
 * @date 2016年12月6日 下午2:44:53
 */
public class PersonalTaxComputer {
	
	//免征额
	private static final double exemption_amount = 3500.00;
	
	/**
	 * 计算速算扣除数
	 * @param d
	 * @return
	 */
	private static double getQuickDeduction(double d){
		
		if	   (d > 83500)	return 13505;
		else if(d > 58500)	return 5505;
		else if(d > 38500) return 2755;
		else if(d > 12500) return 1005;
		else if(d > 8000) return 555;
		else if(d > 5000) return 105;
		else if(d > 3500) return 0;
		else return 0;
	}
	
	/**
	 * 计算税率
	 * @param d
	 * @return
	 */
	private static double getTaxRate(double d){
		if	   (d > 83500) return 0.45;
		else if(d > 58500) return 0.35;
		else if(d > 38500) return 0.30;
		else if(d > 12500) return 0.25;
		else if(d > 8000)  return 0.20;
		else if(d > 5000)  return 0.10;
		else if(d > 3500)  return 0.03;
		else return 0;
	}
	
	/**
	 * 获取个人所得税
	 * @param d	应发工资-准许扣除的费用
	 * @return
	 */
	public static double getIncomeTax(double d){
		double tax =  (d-exemption_amount) * getTaxRate(d) - getQuickDeduction(d);
		return PubMethod.getNumberFormatByDouble(Math.abs(tax));
	}

}
