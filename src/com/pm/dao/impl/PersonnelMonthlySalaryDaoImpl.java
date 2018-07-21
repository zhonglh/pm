package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IPersonnelMonthlySalaryDao;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class PersonnelMonthlySalaryDaoImpl extends BatisDao implements IPersonnelMonthlySalaryDao  {

	@Override
	public int addPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "PersonnelMonthlySalaryMapping.addPersonnelMonthlySalary";
		return this.insert(sql, personnelMonthlySalary);
	}

	@Override
	public int updatePersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "PersonnelMonthlySalaryMapping.updatePersonnelMonthlySalary";
		return this.update(sql, personnelMonthlySalary);
	}

	@Override
	public void deletePersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "PersonnelMonthlySalaryMapping.deletePersonnelMonthlySalary";
		this.delete(sql, personnelMonthlySalary);
	}

	@Override
	public void verifyPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "PersonnelMonthlySalaryMapping.verifyPersonnelMonthlySalary";
		this.update(sql, personnelMonthlySalary);
	}

	@Override
	public void unVerifyPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		String sql = "PersonnelMonthlySalaryMapping.unVerifyPersonnelMonthlySalary";
		this.update(sql, personnelMonthlySalary);
	}

	@Override
	public PersonnelMonthlySalary getPersonnelMonthlySalary(String id) {

		String sql = "PersonnelMonthlySalaryMapping.getPersonnelMonthlySalary"; 
		PersonnelMonthlySalary personnelMonthlySalary = new PersonnelMonthlySalary(); 
		personnelMonthlySalary.setId(id); 
		List<PersonnelMonthlySalary> list = this.query(sql, PersonnelMonthlySalary.class, personnelMonthlySalary); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PersonnelMonthlySalary> queryPersonnelMonthlySalary(
		PersonnelMonthlySalary personnelMonthlySalary,
		UserPermit userPermit,
		Pager<PersonnelMonthlySalary> pager){

		String sql = "PersonnelMonthlySalaryMapping.queryPersonnelMonthlySalary"; 
		Pager<PersonnelMonthlySalary> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlySalary.class, personnelMonthlySalary,userPermit); 
		/*sql = "PersonnelMonthlySalaryMapping.queryPersonnelMonthlySalaryTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(personnelMonthlySalary != null) map.put(personnelMonthlySalary.getClass().getSimpleName(), personnelMonthlySalary);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}*/
		return pager1;
	}


}