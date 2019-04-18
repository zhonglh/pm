package com.pm.dao.impl;

import com.common.daos.BatisDao;
import com.pm.dao.IOtherStaffExDao;
import com.pm.dao.IStaffCostExDao;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.PersonnelMonthlySalary;
import org.springframework.stereotype.Component;

@Component
public class OtherStaffExDaoImpl extends BatisDao implements IOtherStaffExDao {

	@Override
	public int checkAddSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.checkAddSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int checkAddSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.checkAddSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	@Override
	public int unCheckAddSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.unCheckAddSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int unCheckAddSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.unCheckAddSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	@Override
	public int checkDecrSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.checkDecrSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int checkDecrSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.checkDecrSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	@Override
	public int unCheckDecrSalary1(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.unCheckDecrSalary1";
		return this.update(sql, personnelMonthlySalary);
	}
	@Override
	public int unCheckDecrSalary2(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "OtherStaffExMapping.unCheckDecrSalary2";
		return this.update(sql, personnelMonthlySalary);
	}
	
	
	
	
	

	@Override
	public void checkInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {

		String sql = "OtherStaffExMapping.checkInsurance";
		this.update(sql, personnelMonthlyInsurance);
	}

	@Override
	public void unCheckInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {

		String sql = "OtherStaffExMapping.unCheckInsurance";
		this.update(sql, personnelMonthlyInsurance);

	}




	@Override
	public void checkReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "OtherStaffExMapping.checkReserveFund";
		this.update(sql, personnelMonthlyReserveFund);
	}
	@Override
	public void unCheckReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund){

		String sql = "OtherStaffExMapping.unCheckReserveFund";
		this.update(sql, personnelMonthlyReserveFund);
	}
	
	
	
	

	@Override
	public int checkBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {

		String sql = "OtherStaffExMapping.checkBonus";
		return this.update(sql, personnelMonthlyBonus);

	}

	@Override
	public int unCheckBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {


		String sql = "OtherStaffExMapping.unCheckBonus";
		return this.update(sql, personnelMonthlyBonus);

	}

}
