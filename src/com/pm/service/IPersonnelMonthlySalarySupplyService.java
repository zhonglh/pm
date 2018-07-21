package com.pm.service;

import com.pm.domain.business.PersonnelMonthlySalarySupply;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IPersonnelMonthlySalarySupplyService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int addPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int updatePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void deletePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply[] personnelMonthlySalarySupplys) ;

	
	public PersonnelMonthlySalarySupply getPersonnelMonthlySalarySupply(String id) ;	

	public Pager<PersonnelMonthlySalarySupply> queryPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply,  UserPermit userPermit,Pager<PersonnelMonthlySalarySupply> pager);

}