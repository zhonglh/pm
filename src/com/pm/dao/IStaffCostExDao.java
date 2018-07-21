package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.PersonnelMonthlySalary;

public interface IStaffCostExDao {
	
	
	public int checkAddSalary1(PersonnelMonthlySalary personnelMonthlySalary) ;
	public int checkAddSalary2(PersonnelMonthlySalary personnelMonthlySalary) ;
	public int unCheckAddSalary1(PersonnelMonthlySalary personnelMonthlySalary) ;	
	public int unCheckAddSalary2(PersonnelMonthlySalary personnelMonthlySalary) ;
	public int checkDecrSalary1(PersonnelMonthlySalary personnelMonthlySalary) ;
	public int checkDecrSalary2(PersonnelMonthlySalary personnelMonthlySalary) ;
	public int unCheckDecrSalary1(PersonnelMonthlySalary personnelMonthlySalary) ;
	public int unCheckDecrSalary2(PersonnelMonthlySalary personnelMonthlySalary) ;
	
	
	public void checkInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;
	public void unCheckInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;
	

	public void checkReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;
	public void unCheckReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund);
	

	public int checkBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;
	public int unCheckBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;
	
	

}
