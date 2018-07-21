package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.PersonnelMonthlySalarySupply;
import com.pm.dao.IPersonnelMonthlyBaseDao;
import com.pm.dao.IPersonnelMonthlySalarySupplyDao;
import com.pm.service.IPersonnelMonthlySalarySupplyService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;

@Component
public class PersonnelMonthlySalarySupplyServiceImpl implements  IPersonnelMonthlySalarySupplyService {

	@Autowired IPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	@Autowired IPersonnelMonthlySalarySupplyDao personnelMonthlySalarySupplyDao;
	@Override
	public int addPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		int size = personnelMonthlyBaseDao.addPersonnelMonthlyBase(personnelMonthlySalarySupply);
		if(size == 0) return size;

		if(personnelMonthlySalarySupply.getDescription() != null && personnelMonthlySalarySupply.getDescription().isEmpty())
			personnelMonthlySalarySupply.setDescription(null);
		return personnelMonthlySalarySupplyDao.addPersonnelMonthlySalarySupply(personnelMonthlySalarySupply);
	}

	@Override
	public int updatePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) {
		int size = personnelMonthlyBaseDao.updatePersonnelMonthlyBase(personnelMonthlySalarySupply);
		if(size == 0) return size;

		if(personnelMonthlySalarySupply.getDescription() != null && personnelMonthlySalarySupply.getDescription().isEmpty())
			personnelMonthlySalarySupply.setDescription(null);
		return personnelMonthlySalarySupplyDao.updatePersonnelMonthlySalarySupply(personnelMonthlySalarySupply);
	}

	@Override
	public void deletePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply[] personnelMonthlySalarySupplys) {
		for(PersonnelMonthlySalarySupply personnelMonthlySalarySupply : personnelMonthlySalarySupplys){
			int size = personnelMonthlyBaseDao.deletePersonnelMonthlyBase(personnelMonthlySalarySupply);
			if(size == 0) throw new PMException(CommonErrorConstants.e029901);
			personnelMonthlySalarySupplyDao.deletePersonnelMonthlySalarySupply(personnelMonthlySalarySupply);
		}
	}


	@Override
	public PersonnelMonthlySalarySupply getPersonnelMonthlySalarySupply(String id) {
		PersonnelMonthlySalarySupply obj = personnelMonthlySalarySupplyDao.getPersonnelMonthlySalarySupply(id);
		obj.getMonthly_type_name();
		return obj;
	}

	@Override
	public Pager<PersonnelMonthlySalarySupply> queryPersonnelMonthlySalarySupply(
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply,
		UserPermit userPermit,
		Pager<PersonnelMonthlySalarySupply> pager){

		return personnelMonthlySalarySupplyDao.queryPersonnelMonthlySalarySupply(personnelMonthlySalarySupply, userPermit, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String id) {
		return (T)getPersonnelMonthlySalarySupply(id);
	}


}