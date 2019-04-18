package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pm.dao.IOtherPersonnelMonthlyReserveFundDao;
import org.springframework.stereotype.Component;

import com.pm.dao.IPersonnelMonthlyReserveFundDao;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class OtherPersonnelMonthlyReserveFundDaoImpl extends BatisDao implements IOtherPersonnelMonthlyReserveFundDao {

	@Override
	public int addPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "OtherPersonnelMonthlyReserveFundMapping.addPersonnelMonthlyReserveFund";
		return this.insert(sql, personnelMonthlyReserveFund);
	}

	@Override
	public int updatePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "OtherPersonnelMonthlyReserveFundMapping.updatePersonnelMonthlyReserveFund";
		return this.update(sql, personnelMonthlyReserveFund);
	}

	@Override
	public void deletePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "OtherPersonnelMonthlyReserveFundMapping.deletePersonnelMonthlyReserveFund";
		this.delete(sql, personnelMonthlyReserveFund);
	}

	@Override
	public void verifyPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "OtherPersonnelMonthlyReserveFundMapping.verifyPersonnelMonthlyReserveFund";
		this.update(sql, personnelMonthlyReserveFund);
	}

	@Override
	public void unVerifyPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		String sql = "OtherPersonnelMonthlyReserveFundMapping.unVerifyPersonnelMonthlyReserveFund";
		this.update(sql, personnelMonthlyReserveFund);
	}

	@Override
	public PersonnelMonthlyReserveFund getPersonnelMonthlyReserveFund(String id) {

		String sql = "OtherPersonnelMonthlyReserveFundMapping.getPersonnelMonthlyReserveFund"; 
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund = new PersonnelMonthlyReserveFund(); 
		personnelMonthlyReserveFund.setId(id); 
		List<PersonnelMonthlyReserveFund> list = this.query(sql, PersonnelMonthlyReserveFund.class, personnelMonthlyReserveFund); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PersonnelMonthlyReserveFund> queryPersonnelMonthlyReserveFund(
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund,
		UserPermit userPermit,
		Pager<PersonnelMonthlyReserveFund> pager){

		String sql = "OtherPersonnelMonthlyReserveFundMapping.queryPersonnelMonthlyReserveFund"; 
		Pager<PersonnelMonthlyReserveFund> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlyReserveFund.class, personnelMonthlyReserveFund,userPermit); 
		
		return pager1;
	}


}