package com.pm.util.charts;

import com.pm.domain.business.StaffCost;
import com.pm.util.PubMethod;


/**
 * 图表计算工具类
 * @author zhonglihong
 * @date 2016年12月14日 上午12:44:43
 */
public class ChartsUtil {
	
	public static double getSocialInsurancePersonal(StaffCost staffCost ){
		double social_insurance_personal = staffCost.getEndowment_insurance_bypersonal() +
				staffCost.getMedical_insurance_bypersonal() +
				staffCost.getLosejob_insurance_bypersonal() +
				staffCost.getReservefund_bypersonal() ;		
		social_insurance_personal = PubMethod.getNumberFormatByDouble(social_insurance_personal);
		return social_insurance_personal;
		
	}
	
	public static double getsocialInsuranceCompany(StaffCost staffCost ){
		double social_insurance_company = staffCost.getEndowment_insurance_bycompany() +
				staffCost.getMedical_insurance_bycompany() +
				staffCost.getLosejob_insurance_bycompany() +
				staffCost.getMaternity_insurance_bycompany() +
				staffCost.getJobharm_insurance_bycompany() +
				staffCost.getReservefund_bypcompany() ;
		social_insurance_company = PubMethod.getNumberFormatByDouble(social_insurance_company);
		return social_insurance_company;
	}

}
