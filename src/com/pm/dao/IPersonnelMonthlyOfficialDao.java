package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPersonnelMonthlyOfficialDao {

	public int addPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ;

	public int updatePersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ; 

	public void deletePersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ;

	public void verifyPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ;	

	public void unVerifyPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial) ;

	public PersonnelMonthlyOfficial getPersonnelMonthlyOfficial(String id) ;	

	public Pager<PersonnelMonthlyOfficial> queryPersonnelMonthlyOfficial(PersonnelMonthlyOfficial personnelMonthlyOfficial,  UserPermit userPermit,Pager<PersonnelMonthlyOfficial> pager);

}