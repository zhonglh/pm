package com.pm.service.impl;

import com.pm.service.IOtherPersonnelMonthlyOfficialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.dao.IOtherPersonnelMonthlyBaseDao;
import com.pm.dao.IOtherPersonnelMonthlyOfficialDao;
import com.pm.service.IOtherPersonnelMonthlyOfficialService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;

@Component
public class OtherPersonnelMonthlyOfficialServiceImpl implements IOtherPersonnelMonthlyOfficialService {

	@Autowired IOtherPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	@Autowired IOtherPersonnelMonthlyOfficialDao personnelMonthlyOfficialDao;
	
	@Override
	public int addPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		int size = personnelMonthlyBaseDao.addPersonnelMonthlyBase(personnelMonthlyOfficial);
		if(size == 0) return size;
		return personnelMonthlyOfficialDao.addPersonnelMonthlyOfficial(personnelMonthlyOfficial);
	}

	@Override
	public int updatePersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) {
		int size = personnelMonthlyBaseDao.updatePersonnelMonthlyBase(personnelMonthlyOfficial);
		if(size == 0) return size;
		return personnelMonthlyOfficialDao.updatePersonnelMonthlyOfficial(personnelMonthlyOfficial);
	}

	@Override
	public void deletePersonnelMonthlyOfficial(PersonnelMonthlyOfficial[] personnelMonthlyOfficials) {
		for(PersonnelMonthlyOfficial personnelMonthlyOfficial : personnelMonthlyOfficials){
			int size = personnelMonthlyBaseDao.deletePersonnelMonthlyBase(personnelMonthlyOfficial);
			if(size == 0) throw new PMException(CommonErrorConstants.e029901);
			personnelMonthlyOfficialDao.deletePersonnelMonthlyOfficial(personnelMonthlyOfficial);
		}
	}


	@Override
	public PersonnelMonthlyOfficial getPersonnelMonthlyOfficial(String id) {
		PersonnelMonthlyOfficial obj = personnelMonthlyOfficialDao.getPersonnelMonthlyOfficial(id);
		obj.getMonthly_type_name();
		return obj;
	}

	@Override
	public Pager<PersonnelMonthlyOfficial> queryPersonnelMonthlyOfficial(
		PersonnelMonthlyOfficial personnelMonthlyOfficial,
		UserPermit userPermit,
		Pager<PersonnelMonthlyOfficial> pager){

		return personnelMonthlyOfficialDao.queryPersonnelMonthlyOfficial(personnelMonthlyOfficial, userPermit, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String id) {
		return (T)getPersonnelMonthlyOfficial(id);
	}
}