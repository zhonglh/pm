package com.pm.service;

import com.pm.domain.business.PersonnelMonthlyBase;
import com.pm.util.constant.EnumStaffType;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IPersonnelMonthlyBaseService extends  IBaseService,IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PERSONNELMONTHLYBASE)
	public void verifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) ;	

	public PersonnelMonthlyBase getPersonnelMonthlyBase(String id) ;	
	


	public boolean isExistNotCheckByWorkAttendance (String project_id , int the_month) ;

	public Pager<PersonnelMonthlyBase> queryPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase,  UserPermit userPermit,Pager<PersonnelMonthlyBase> pager);

}