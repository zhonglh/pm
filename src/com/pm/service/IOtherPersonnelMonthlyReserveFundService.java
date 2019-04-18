package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IOtherPersonnelMonthlyReserveFundService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int addPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int updatePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void deletePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund[] personnelMonthlyReserveFunds) ;


	public PersonnelMonthlyReserveFund getPersonnelMonthlyReserveFund(String id) ;	

	public Pager<PersonnelMonthlyReserveFund> queryPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund,  UserPermit userPermit,Pager<PersonnelMonthlyReserveFund> pager);

}