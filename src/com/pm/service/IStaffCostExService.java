package com.pm.service;

import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.PersonnelMonthlySalary;

public interface IStaffCostExService {

	public void checkAddSalary(PersonnelMonthlySalary personnelMonthlySalary) ;
	public void unCheckAddSalary(PersonnelMonthlySalary personnelMonthlySalary) ;	
	public void checkDecrSalary(PersonnelMonthlySalary personnelMonthlySalary) ;
	public void unCheckDecrSalary(PersonnelMonthlySalary personnelMonthlySalary) ;
	
	
	public void checkInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;
	public void unCheckInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;
	

	public void checkReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;
	public void unCheckReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;
	
	

	public void checkBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;
	public void unCheckBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;
	
}
