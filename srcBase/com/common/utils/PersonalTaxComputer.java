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
	private static final double exemption_amount = 5000.00;
	
	/**
	 * 计算速算扣除数
	 * @param d
	 * @return
	 */
	private static double getQuickDeduction(double d){


		if	   (d > 80000) return 15160;
		else if(d > 55000) return 7160;
		else if(d > 35000) return 4410;
		else if(d > 25000) return 2660;
		else if(d > 12000)  return 1410;
		else if(d > 3000)  return 210;
		else if(d > 0)  return 0;
		else return 0;
	}
	
	/**
	 * 计算税率
	 * @param d
	 * @return
	 */
	private static double getTaxRate(double d){
		if	   (d > 80000) return 0.45;
		else if(d > 55000) return 0.35;
		else if(d > 35000) return 0.30;
		else if(d > 25000) return 0.25;
		else if(d > 12000)  return 0.20;
		else if(d > 3000)  return 0.10;
		else if(d > 0)  return 0.03;
		else return 0;
	}
	
	/**
	 * 获取个人所得税
	 * @param d	应发工资-准许扣除的费用
	 * @return
	 */
	public static double getIncomeTax(double d){
		double ynsde = d-exemption_amount;
		double tax =  ynsde * getTaxRate(ynsde) - getQuickDeduction(ynsde);
		return PubMethod.getNumberFormatByDouble(Math.abs(tax));
	}

}
