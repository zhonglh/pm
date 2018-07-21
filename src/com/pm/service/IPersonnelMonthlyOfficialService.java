package com.pm.service;

import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IPersonnelMonthlyOfficialService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int addPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public int updatePersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void deletePersonnelMonthlyOfficial(PersonnelMonthlyOfficial[] personnelMonthlyOfficials) ;


	public PersonnelMonthlyOfficial getPersonnelMonthlyOfficial(String id) ;	

	public Pager<PersonnelMonthlyOfficial> queryPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial,  UserPermit userPermit,Pager<PersonnelMonthlyOfficial> pager);

}