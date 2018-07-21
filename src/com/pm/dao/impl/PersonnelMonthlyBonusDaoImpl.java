package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IPersonnelMonthlyBonusDao;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.vo.UserPermit;

@Component
public class PersonnelMonthlyBonusDaoImpl extends BatisDao implements IPersonnelMonthlyBonusDao  {

	@Override
	public int addPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		String sql = "PersonnelMonthlyBonusMapping.addPersonnelMonthlyBonus";
		return this.insert(sql, personnelMonthlyBonus);
	}

	@Override
	public int updatePersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		String sql = "PersonnelMonthlyBonusMapping.updatePersonnelMonthlyBonus";
		return this.update(sql, personnelMonthlyBonus);
	}

	@Override
	public void deletePersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		String sql = "PersonnelMonthlyBonusMapping.deletePersonnelMonthlyBonus";
		this.delete(sql, personnelMonthlyBonus);
	}

	@Override
	public void verifyPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		String sql = "PersonnelMonthlyBonusMapping.verifyPersonnelMonthlyBonus";
		this.update(sql, personnelMonthlyBonus);
	}

	@Override
	public void unVerifyPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		String sql = "PersonnelMonthlyBonusMapping.unVerifyPersonnelMonthlyBonus";
		this.update(sql, personnelMonthlyBonus);
	}

	@Override
	public PersonnelMonthlyBonus getPersonnelMonthlyBonus(String id) {

		String sql = "PersonnelMonthlyBonusMapping.getPersonnelMonthlyBonus"; 
		PersonnelMonthlyBonus personnelMonthlyBonus = new PersonnelMonthlyBonus(); 
		personnelMonthlyBonus.setId(id); 
		List<PersonnelMonthlyBonus> list = this.query(sql, PersonnelMonthlyBonus.class, personnelMonthlyBonus); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PersonnelMonthlyBonus> queryPersonnelMonthlyBonus(
		PersonnelMonthlyBonus personnelMonthlyBonus,
		UserPermit userPermit,
		Pager<PersonnelMonthlyBonus> pager){

		String sql = "PersonnelMonthlyBonusMapping.queryPersonnelMonthlyBonus"; 
		Pager<PersonnelMonthlyBonus> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlyBonus.class, personnelMonthlyBonus,userPermit); 
		/*sql = "PersonnelMonthlyBonusMapping.queryPersonnelMonthlyBonusTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(personnelMonthlyBonus != null) map.put(personnelMonthlyBonus.getClass().getSimpleName(), personnelMonthlyBonus);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}*/
		return pager1;
	}


}