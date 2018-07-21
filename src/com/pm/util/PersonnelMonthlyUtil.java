package com.pm.util;

import java.util.Date;

import com.common.utils.DateKit;
import com.pm.domain.business.PersonnelMonthlyBase;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.PersonnelMonthlySalarySupply;

public class PersonnelMonthlyUtil {
	

	public static boolean validate(PersonnelMonthlyBase personnelMonthlyBase){
		boolean b = true;
		if(personnelMonthlyBase.getClass() == PersonnelMonthlyBonus.class){
			b = monthEquals(personnelMonthlyBase.getThe_month(), ((PersonnelMonthlyBonus)personnelMonthlyBase).getChange_time());
		}else if(personnelMonthlyBase.getClass() == PersonnelMonthlyOfficial.class){
			b = monthEquals(personnelMonthlyBase.getThe_month(), ((PersonnelMonthlyOfficial)personnelMonthlyBase).getConfirmation_date());
		}else if(personnelMonthlyBase.getClass() == PersonnelMonthlySalary.class){
			b = monthEquals(personnelMonthlyBase.getThe_month(), ((PersonnelMonthlySalary)personnelMonthlyBase).getChange_time());
		}else if(personnelMonthlyBase.getClass() == PersonnelMonthlySalarySupply.class){
			b = monthEquals(personnelMonthlyBase.getThe_month(), ((PersonnelMonthlySalarySupply)personnelMonthlyBase).getSupply_time());
		}		
		return b;
	}
	
	
	private static boolean monthEquals(int the_month , Date date){
		if(date == null) return false;
		String month = DateKit.fmtDateToStr(date, "yyyyMM");
		if(month.equals(String.valueOf(the_month))){
			return true;
		}else {
			return false;
		}
	}

}
