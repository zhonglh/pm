package com.pm.service.impl;

import com.pm.service.IOtherPersonnelMonthlySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.dao.IOtherPersonnelMonthlyBaseDao;
import com.pm.dao.IOtherPersonnelMonthlySalaryDao;
import com.pm.service.IOtherPersonnelMonthlySalaryService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;

@Component
public class OtherPersonnelMonthlySalaryServiceImpl implements IOtherPersonnelMonthlySalaryService {

	@Autowired IOtherPersonnelMonthlyBaseDao personnelMonthlyBaseDao;
	@Autowired IOtherPersonnelMonthlySalaryDao personnelMonthlySalaryDao;
	@Override
	public int addPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		int size = personnelMonthlyBaseDao.addPersonnelMonthlyBase(personnelMonthlySalary);
		if(size == 0) return size;
		
		if(personnelMonthlySalary.getDescription() != null && personnelMonthlySalary.getDescription().isEmpty())
			personnelMonthlySalary.setDescription(null);
		return personnelMonthlySalaryDao.addPersonnelMonthlySalary(personnelMonthlySalary);
	}

	@Override
	public int updatePersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) {
		int size = personnelMonthlyBaseDao.updatePersonnelMonthlyBase(personnelMonthlySalary);
		if(size == 0) return size;
		
		if(personnelMonthlySalary.getDescription() != null && personnelMonthlySalary.getDescription().isEmpty())
			personnelMonthlySalary.setDescription(null);
		return personnelMonthlySalaryDao.updatePersonnelMonthlySalary(personnelMonthlySalary);
	}

	@Override
	public void deletePersonnelMonthlySalary(PersonnelMonthlySalary[] personnelMonthlySalarys) {
		for(PersonnelMonthlySalary personnelMonthlySalary : personnelMonthlySalarys){
			int size = personnelMonthlyBaseDao.deletePersonnelMonthlyBase(personnelMonthlySalary);
			if(size == 0) throw new PMException(CommonErrorConstants.e029901);
			personnelMonthlySalaryDao.deletePersonnelMonthlySalary(personnelMonthlySalary);
		}
	}


	@Override
	public PersonnelMonthlySalary getPersonnelMonthlySalary(String id) {
		PersonnelMonthlySalary obj = personnelMonthlySalaryDao.getPersonnelMonthlySalary(id);
		obj.getMonthly_type_name();
		return obj;
	}

	@Override
	public Pager<PersonnelMonthlySalary> queryPersonnelMonthlySalary(
		PersonnelMonthlySalary personnelMonthlySalary,
		UserPermit userPermit,
		Pager<PersonnelMonthlySalary> pager){
		return personnelMonthlySalaryDao.queryPersonnelMonthlySalary(personnelMonthlySalary, userPermit, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String id) {
		return (T)getPersonnelMonthlySalary(id);
	}

}