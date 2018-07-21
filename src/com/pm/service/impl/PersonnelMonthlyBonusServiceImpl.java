package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IPersonnelMonthlyBaseDao;
import com.pm.dao.IPersonnelMonthlyBonusDao;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.service.IPersonnelMonthlyBonusService;
import com.pm.vo.UserPermit;

@Component
public class PersonnelMonthlyBonusServiceImpl implements  IPersonnelMonthlyBonusService {

	@Autowired IPersonnelMonthlyBonusDao personnelMonthlyBonusDao;
	@Autowired IPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	
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