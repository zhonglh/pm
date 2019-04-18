package com.pm.service.impl;

import com.pm.service.IOtherPersonnelMonthlyBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IOtherPersonnelMonthlyBaseDao;
import com.pm.dao.IOtherPersonnelMonthlyBonusDao;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.service.IOtherPersonnelMonthlyBonusService;
import com.pm.vo.UserPermit;

@Component
public class OtherPersonnelMonthlyBonusServiceImpl implements IOtherPersonnelMonthlyBonusService {

	@Autowired IOtherPersonnelMonthlyBonusDao personnelMonthlyBonusDao;
	@Autowired IOtherPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	
	@Override
	public int addPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		int size = personnelMonthlyBaseDao.addPersonnelMonthlyBase(personnelMonthlyBonus);
		if(size == 0) return size;

		if(personnelMonthlyBonus.getDescription() != null && personnelMonthlyBonus.getDescription().isEmpty())
			personnelMonthlyBonus.setDescription(null);
		return personnelMonthlyBonusDao.addPersonnelMonthlyBonus(personnelMonthlyBonus);
	}

	@Override
	public int updatePersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		int size = personnelMonthlyBaseDao.updatePersonnelMonthlyBase(personnelMonthlyBonus);
		if(size == 0) return size;

		if(personnelMonthlyBonus.getDescription() != null && personnelMonthlyBonus.getDescription().isEmpty())
			personnelMonthlyBonus.setDescription(null);
		return personnelMonthlyBonusDao.updatePersonnelMonthlyBonus(personnelMonthlyBonus);
	}

	@Override
	public void deletePersonnelMonthlyBonus(PersonnelMonthlyBonus[] personnelMonthlyBonuss) {
		for(PersonnelMonthlyBonus personnelMonthlyBonus : personnelMonthlyBonuss){
			int size = personnelMonthlyBaseDao.deletePersonnelMonthlyBase(personnelMonthlyBonus);
			if(size == 0) throw new PMException(CommonErrorConstants.e029901);
			personnelMonthlyBonusDao.deletePersonnelMonthlyBonus(personnelMonthlyBonus);
		}
	}


	@Override
	public PersonnelMonthlyBonus getPersonnelMonthlyBonus(String id) {
		PersonnelMonthlyBonus obj = personnelMonthlyBonusDao.getPersonnelMonthlyBonus(id);
		obj.getMonthly_type_name();
		return obj;
	}

	@Override
	public Pager<PersonnelMonthlyBonus> queryPersonnelMonthlyBonus(
		PersonnelMonthlyBonus personnelMonthlyBonus,
		UserPermit userPermit,
		Pager<PersonnelMonthlyBonus> pager){

		return personnelMonthlyBonusDao.queryPersonnelMonthlyBonus(personnelMonthlyBonus, userPermit, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String id) {
		return (T) getPersonnelMonthlyBonus(id);
	}


}