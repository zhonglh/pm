package com.pm.dao.impl;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IStaffCostExDao;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.PersonnelMonthlySalary;

@Component
public class StaffCostExDaoImpl extends BatisDao implements IStaffCostExDao {

	@Override
	public int checkAddSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.checkAddSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int checkAddSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.checkAddSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	@Override
	public int unCheckAddSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.unCheckAddSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int unCheckAddSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.unCheckAddSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	@Override
	public int checkDecrSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.checkDecrSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int checkDecrSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.checkDecrSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	@Override
	public int unCheckDecrSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.unCheckDecrSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int unCheckDecrSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "StaffCostExMapping.unCheckDecrSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	
	
	
	

	@Override
	public void checkInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {

		String sql = "StaffCostExMapping.checkInsurance";
		this.update(sql, personnelMonthlyInsurance);
	}

	@Override
	public void unCheckInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {

		String sql = "StaffCostExMapping.unCheckInsurance";
		this.update(sql, personnelMonthlyInsurance);

	}
	
	
	

	public void checkReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "StaffCostExMapping.checkReserveFund";
		this.update(sql, personnelMonthlyReserveFund);
	}
	public void unCheckReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund){

		String sql = "StaffCostExMapping.unCheckReserveFund";
		this.update(sql, personnelMonthlyReserveFund);
	}
	
	
	
	

	@Override
	public int checkBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {

		String sql = "StaffCostExMapping.checkBonus";
		return this.update(sql, personnelMonthlyBonus);

	}

	@Override
	public int unCheckBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {


		String sql = "StaffCostExMapping.unCheckBonus";
		return this.update(sql, personnelMonthlyBonus);

	}

}
