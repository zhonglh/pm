package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IPersonnelMonthlySalaryService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int addPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int updatePersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void deletePersonnelMonthlySalary(PersonnelMonthlySalary[] personnelMonthlySalarys) ;

	//@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYSALARY)
	//public void verifyPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ;	

	public PersonnelMonthlySalary getPersonnelMonthlySalary(String id) ;	

	public Pager<PersonnelMonthlySalary> queryPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary,  UserPermit userPermit,Pager<PersonnelMonthlySalary> pager);

}