package com.pm.service;

import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IOtherPersonnelMonthlyInsuranceService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int addPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int updatePersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void deletePersonnelMonthlyInsurance(PersonnelMonthlyInsurance[] personnelMonthlyInsurances) ;

	//@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYINSURANCE)
	//public void verifyPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;	

	public PersonnelMonthlyInsurance getPersonnelMonthlyInsurance(String id) ;	

	public Pager<PersonnelMonthlyInsurance> queryPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance,  UserPermit userPermit,Pager<PersonnelMonthlyInsurance> pager);

}