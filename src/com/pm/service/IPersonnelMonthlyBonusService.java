package com.pm.service;

import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IPersonnelMonthlyBonusService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int addPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int updatePersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void deletePersonnelMonthlyBonus(PersonnelMonthlyBonus[] personnelMonthlyBonuss) ;

	//@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	//public void verifyPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;	

	public PersonnelMonthlyBonus getPersonnelMonthlyBonus(String id) ;	

	public Pager<PersonnelMonthlyBonus> queryPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus,  UserPermit userPermit,Pager<PersonnelMonthlyBonus> pager);

}