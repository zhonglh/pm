package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.dao.IPersonnelMonthlyBaseDao;
import com.pm.dao.IPersonnelMonthlyInsuranceDao;
import com.pm.service.IPersonnelMonthlyInsuranceService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;

@Component
public class PersonnelMonthlyInsuranceServiceImpl implements  IPersonnelMonthlyInsuranceService {

	@Autowired IPersonnelMonthlyInsuranceDao personnelMonthlyInsuranceDao;
	@Autowired IPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	
	@Override
	public int addPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		int size = personnelMonthlyBaseDao.addPersonnelMonthlyBase(personnelMonthlyInsurance);
		if(size == 0) return size;

		if(personnelMonthlyInsurance.getDescription() != null && personnelMonthlyInsurance.getDescription().isEmpty())
			personnelMonthlyInsurance.setDescription(null);
		return personnelMonthlyInsuranceDao.addPersonnelMonthlyInsurance(personnelMonthlyInsurance);
	}

	@Override
	public int updatePersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		int size = personnelMonthlyBaseDao.updatePersonnelMonthlyBase(personnelMonthlyInsurance);
		if(size == 0) return size;

		if(personnelMonthlyInsurance.getDescription() != null && personnelMonthlyInsurance.getDescription().isEmpty())
			personnelMonthlyInsurance.setDescription(null);
		return personnelMonthlyInsuranceDao.updatePersonnelMonthlyInsurance(personnelMonthlyInsurance);
	}

	@Override
	public void deletePersonnelMonthlyInsurance(PersonnelMonthlyInsurance[] personnelMonthlyInsurances) {
		for(PersonnelMonthlyInsurance personnelMonthlyInsurance : personnelMonthlyInsurances){
			int size = personnelMonthlyBaseDao.deletePersonnelMonthlyBase(personnelMonthlyInsurance);
			if(size == 0) throw new PMException(CommonErrorConstants.e029901);
			personnelMonthlyInsuranceDao.deletePersonnelMonthlyInsurance(personnelMonthlyInsurance);
		}
	}

	@Override
	public PersonnelMonthlyInsurance getPersonnelMonthlyInsurance(String id) {
		PersonnelMonthlyInsurance obj = personnelMonthlyInsuranceDao.getPersonnelMonthlyInsurance(id);
		obj.getMonthly_type_name();
		return obj;
	}

	@Override
	public Pager<PersonnelMonthlyInsurance> queryPersonnelMonthlyInsurance(
		PersonnelMonthlyInsurance personnelMonthlyInsurance,
		UserPermit userPermit,
		Pager<PersonnelMonthlyInsurance> pager){

		return personnelMonthlyInsuranceDao.queryPersonnelMonthlyInsurance(personnelMonthlyInsurance, userPermit, pager);
	}

	@Override
	public <T> T get(String id) {
		return (T)getPersonnelMonthlyInsurance(id);
	}


}