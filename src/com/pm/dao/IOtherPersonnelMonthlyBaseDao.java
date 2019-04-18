package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlyBase;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IOtherPersonnelMonthlyBaseDao {

	public int addPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) ;

	public int updatePersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) ; 

	public int deletePersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) ;

	public int verifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) ;	

	public int unVerifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) ;

	public PersonnelMonthlyBase getPersonnelMonthlyBase(String id) ;	

	public int getNotCheckNumByWorkAttendance(PersonnelMonthlyBase personnelMonthlyBase) ;
	

	public Pager<PersonnelMonthlyBase> queryPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase,  UserPermit userPermit,Pager<PersonnelMonthlyBase> pager);

}