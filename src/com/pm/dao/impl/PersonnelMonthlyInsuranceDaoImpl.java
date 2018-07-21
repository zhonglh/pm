package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IPersonnelMonthlyInsuranceDao;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.vo.UserPermit;

@Component
public class PersonnelMonthlyInsuranceDaoImpl extends BatisDao implements IPersonnelMonthlyInsuranceDao  {

	@Override
	public int addPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		String sql = "PersonnelMonthlyInsuranceMapping.addPersonnelMonthlyInsurance";
		return this.insert(sql, personnelMonthlyInsurance);
	}

	@Override
	public int updatePersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		String sql = "PersonnelMonthlyInsuranceMapping.updatePersonnelMonthlyInsurance";
		return this.update(sql, personnelMonthlyInsurance);
	}

	@Override
	public void deletePersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		String sql = "PersonnelMonthlyInsuranceMapping.deletePersonnelMonthlyInsurance";
		this.delete(sql, personnelMonthlyInsurance);
	}

	@Override
	public void verifyPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		String sql = "PersonnelMonthlyInsuranceMapping.verifyPersonnelMonthlyInsurance";
		this.update(sql, personnelMonthlyInsurance);
	}

	@Override
	public void unVerifyPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		String sql = "PersonnelMonthlyInsuranceMapping.unVerifyPersonnelMonthlyInsurance";
		this.update(sql, personnelMonthlyInsurance);
	}

	@Override
	public PersonnelMonthlyInsurance getPersonnelMonthlyInsurance(String id) {

		String sql = "PersonnelMonthlyInsuranceMapping.getPersonnelMonthlyInsurance"; 
		PersonnelMonthlyInsurance personnelMonthlyInsurance = new PersonnelMonthlyInsurance(); 
		personnelMonthlyInsurance.setId(id); 
		List<PersonnelMonthlyInsurance> list = this.query(sql, PersonnelMonthlyInsurance.class, personnelMonthlyInsurance); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PersonnelMonthlyInsurance> queryPersonnelMonthlyInsurance(
		PersonnelMonthlyInsurance personnelMonthlyInsurance,
		UserPermit userPermit,
		Pager<PersonnelMonthlyInsurance> pager){

		String sql = "PersonnelMonthlyInsuranceMapping.queryPersonnelMonthlyInsurance"; 
		Pager<PersonnelMonthlyInsurance> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PersonnelMonthlyInsurance.class, personnelMonthlyInsurance,userPermit); 
		/*sql = "PersonnelMonthlyInsuranceMapping.queryPersonnelMonthlyInsuranceTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(personnelMonthlyInsurance != null) map.put(personnelMonthlyInsurance.getClass().getSimpleName(), personnelMonthlyInsurance);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}*/
		return pager1;
	}


}