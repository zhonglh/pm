package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IPersonnelMonthlyOfficialDao;
import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class PersonnelMonthlyOfficialDaoImpl extends BatisDao implements IPersonnelMonthlyOfficialDao  {

	@Override
	public int addPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		String sql = "PersonnelMonthlyOfficialMapping.addPersonnelMonthlyOfficial";
		return this.insert(sql, personnelMonthlyOfficial);
	}

	@Override
	public int updatePersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		String sql = "PersonnelMonthlyOfficialMapping.updatePersonnelMonthlyOfficial";
		return this.update(sql, personnelMonthlyOfficial);
	}

	@Override
	public void deletePersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		String sql = "PersonnelMonthlyOfficialMapping.deletePersonnelMonthlyOfficial";
		this.delete(sql, personnelMonthlyOfficial);
	}

	@Override
	public void verifyPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		String sql = "PersonnelMonthlyOfficialMapping.verifyPersonnelMonthlyOfficial";
		this.update(sql, personnelMonthlyOfficial);
	}

	@Override
	public void unVerifyPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		String sql = "PersonnelMonthlyOfficialMapping.unVerifyPersonnelMonthlyOfficial";
		this.update(sql, personnelMonthlyOfficial);
	}

	@Override
	public PersonnelMonthlyOfficial getPersonnelMonthlyOfficial(String id) {

		String sql = "PersonnelMonthlyOfficialMapping.getPersonnelMonthlyOfficial"; 
		PersonnelMonthlyOfficial personnelMonthlyOfficial = new PersonnelMonthlyOfficial(); 
		personnelMonthlyOfficial.setId(id); 
		List<PersonnelMonthlyOfficial> list = this.query(sql, PersonnelMonthlyOfficial.class, personnelMonthlyOfficial); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PersonnelMonthlyOfficial> queryPersonnelMonthlyOfficial(
		PersonnelMonthlyOfficial personnelMonthlyOfficial,
		UserPermit userPermit,
		Pager<PersonnelMonthlyOfficial> pager){

		String sql = "PersonnelMonthlyOfficialMapping.queryPersonnelMonthlyOfficial"; 
		Pager<PersonnelMonthlyOfficial> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlyOfficial.class, personnelMonthlyOfficial,userPermit); 
		/*sql = "PersonnelMonthlyOfficialMapping.queryPersonnelMonthlyOfficialTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(personnelMonthlyOfficial != null) map.put(personnelMonthlyOfficial.getClass().getSimpleName(), personnelMonthlyOfficial);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}*/
		return pager1;
	}


}