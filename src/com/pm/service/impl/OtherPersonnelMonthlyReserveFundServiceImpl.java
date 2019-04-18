package com.pm.service.impl;

import com.pm.service.IOtherPersonnelMonthlyReserveFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.dao.IOtherPersonnelMonthlyBaseDao;
import com.pm.dao.IOtherPersonnelMonthlyInsuranceDao;
import com.pm.dao.IOtherPersonnelMonthlyReserveFundDao;
import com.pm.service.IOtherPersonnelMonthlyReserveFundService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;

@Component
public class OtherPersonnelMonthlyReserveFundServiceImpl implements IOtherPersonnelMonthlyReserveFundService {

	@Autowired IOtherPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	@Autowired IOtherPersonnelMonthlyReserveFundDao personnelMonthlyReserveFundDao;
	@Override
	public int addPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		int size = personnelMonthlyBaseDao.addPersonnelMonthlyBase(personnelMonthlyReserveFund);
		if(size == 0) return size;

		if(personnelMonthlyReserveFund.getDescription() != null && personnelMonthlyReserveFund.getDescription().isEmpty())
			personnelMonthlyReserveFund.setDescription(null);
		return personnelMonthlyReserveFundDao.addPersonnelMonthlyReserveFund(personnelMonthlyReserveFund);
	}

	@Override
	public int updatePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		int size = personnelMonthlyBaseDao.updatePersonnelMonthlyBase(personnelMonthlyReserveFund);
		if(size == 0) return size;

		if(personnelMonthlyReserveFund.getDescription() != null && personnelMonthlyReserveFund.getDescription().isEmpty())
			personnelMonthlyReserveFund.setDescription(null);
		return personnelMonthlyReserveFundDao.updatePersonnelMonthlyReserveFund(personnelMonthlyReserveFund);
	}

	@Override
	public void deletePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund[] personnelMonthlyReserveFunds) {
		for(PersonnelMonthlyReserveFund personnelMonthlyReserveFund : personnelMonthlyReserveFunds){
			int size = personnelMonthlyBaseDao.deletePersonnelMonthlyBase(personnelMonthlyReserveFund);
			if(size == 0) throw new PMException(CommonErrorConstants.e029901);
			personnelMonthlyReserveFundDao.deletePersonnelMonthlyReserveFund(personnelMonthlyReserveFund);
		}
	}
 

	@Override
	public PersonnelMonthlyReserveFund getPersonnelMonthlyReserveFund(String id) {
		PersonnelMonthlyReserveFund obj = personnelMonthlyReserveFundDao.getPersonnelMonthlyReserveFund(id);
		obj.getMonthly_type_name();
		return obj;
	}

	@Override
	public Pager<PersonnelMonthlyReserveFund> queryPersonnelMonthlyReserveFund(
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund,
		UserPermit userPermit,
		Pager<PersonnelMonthlyReserveFund> pager){

		return personnelMonthlyReserveFundDao.queryPersonnelMonthlyReserveFund(personnelMonthlyReserveFund, userPermit, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String id) {
		return (T)getPersonnelMonthlyReserveFund(id);
	}


}