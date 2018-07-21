package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IPersonnelMonthlySalarySupplyDao;
import com.pm.domain.business.PersonnelMonthlySalarySupply;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class PersonnelMonthlySalarySupplyDaoImpl extends BatisDao implements IPersonnelMonthlySalarySupplyDao  {

	@Override
	public int addPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		String sql = "PersonnelMonthlySalarySupplyMapping.addPersonnelMonthlySalarySupply";
		return this.insert(sql, personnelMonthlySalarySupply);
	}

	@Override
	public int updatePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		String sql = "PersonnelMonthlySalarySupplyMapping.updatePersonnelMonthlySalarySupply";
		return this.update(sql, personnelMonthlySalarySupply);
	}

	@Override
	public void deletePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		String sql = "PersonnelMonthlySalarySupplyMapping.deletePersonnelMonthlySalarySupply";
		this.delete(sql, personnelMonthlySalarySupply);
	}

	@Override
	public void verifyPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		String sql = "PersonnelMonthlySalarySupplyMapping.verifyPersonnelMonthlySalarySupply";
		this.update(sql, personnelMonthlySalarySupply);
	}

	@Override
	public void unVerifyPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		String sql = "PersonnelMonthlySalarySupplyMapping.unVerifyPersonnelMonthlySalarySupply";
		this.update(sql, personnelMonthlySalarySupply);
	}

	@Override
	public PersonnelMonthlySalarySupply getPersonnelMonthlySalarySupply(String id) {

		String sql = "PersonnelMonthlySalarySupplyMapping.getPersonnelMonthlySalarySupply"; 
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply = new PersonnelMonthlySalarySupply(); 
		personnelMonthlySalarySupply.setId(id); 
		List<PersonnelMonthlySalarySupply> list = this.query(sql, PersonnelMonthlySalarySupply.class, personnelMonthlySalarySupply); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PersonnelMonthlySalarySupply> queryPersonnelMonthlySalarySupply(
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply,
		UserPermit userPermit,
		Pager<PersonnelMonthlySalarySupply> pager){

		String sql = "PersonnelMonthlySalarySupplyMapping.queryPersonnelMonthlySalarySupply"; 
		Pager<PersonnelMonthlySalarySupply> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlySalarySupply.class, personnelMonthlySalarySupply,userPermit); 
		/*sql = "PersonnelMonthlySalarySupplyMapping.queryPersonnelMonthlySalarySupplyTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(personnelMonthlySalarySupply != null) map.put(personnelMonthlySalarySupply.getClass().getSimpleName(), personnelMonthlySalarySupply);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}*/
		return pager1;
	}


}