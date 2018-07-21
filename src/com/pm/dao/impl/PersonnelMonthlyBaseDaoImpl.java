package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IPersonnelMonthlyBaseDao;
import com.pm.domain.business.PersonnelMonthlyBase;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class PersonnelMonthlyBaseDaoImpl extends BatisDao implements IPersonnelMonthlyBaseDao  {

	@Override
	public int addPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) {
		String sql = "PersonnelMonthlyBaseMapping.addPersonnelMonthlyBase";
		return this.insert(sql, personnelMonthlyBase);
	}

	@Override
	public int updatePersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) {
		String sql = "PersonnelMonthlyBaseMapping.updatePersonnelMonthlyBase";
		return this.update(sql, personnelMonthlyBase);
	}

	@Override
	public int deletePersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) {
		String sql = "PersonnelMonthlyBaseMapping.deletePersonnelMonthlyBase";
		return this.delete(sql, personnelMonthlyBase);
	}

	@Override
	public int verifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) {
		String sql = "PersonnelMonthlyBaseMapping.verifyPersonnelMonthlyBase";
		return this.update(sql, personnelMonthlyBase);
	}

	@Override
	public int unVerifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) {
		String sql = "PersonnelMonthlyBaseMapping.unVerifyPersonnelMonthlyBase";
		return this.update(sql, personnelMonthlyBase);
	}

	@Override
	public PersonnelMonthlyBase getPersonnelMonthlyBase(String id) {

		String sql = "PersonnelMonthlyBaseMapping.getPersonnelMonthlyBase"; 
		PersonnelMonthlyBase personnelMonthlyBase = new PersonnelMonthlyBase(); 
		personnelMonthlyBase.setId(id); 
		List<PersonnelMonthlyBase> list = this.query(sql, PersonnelMonthlyBase.class, personnelMonthlyBase); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}
	
	

	public int getNotCheckNumByWorkAttendance(PersonnelMonthlyBase personnelMonthlyBase) {
		String sql = "PersonnelMonthlyBaseMapping.getNotCheckNumByWorkAttendance"; 
		return this.query4Int(sql, personnelMonthlyBase);
	}

	@Override
	public Pager<PersonnelMonthlyBase> queryPersonnelMonthlyBase(
		PersonnelMonthlyBase personnelMonthlyBase,
		UserPermit userPermit,
		Pager<PersonnelMonthlyBase> pager){

		String sql = "PersonnelMonthlyBaseMapping.queryPersonnelMonthlyBase"; 
		Pager<PersonnelMonthlyBase> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlyBase.class, personnelMonthlyBase,userPermit); 
		//sql = "PersonnelMonthlyBaseMapping.queryPersonnelMonthlyBaseTotalAmount";
		//Map<String,Object> map = new HashMap<String,Object>();
		//if(personnelMonthlyBase != null) map.put(personnelMonthlyBase.getClass().getSimpleName(), personnelMonthlyBase);
		//if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		//Double amount = getSqlSession().selectOne(sql,map);
		//if(amount != null) {
		//	double total_amount = amount.doubleValue();
		//	pager1.setTotal_amount(total_amount);
		//}
		return pager1;
	}


}