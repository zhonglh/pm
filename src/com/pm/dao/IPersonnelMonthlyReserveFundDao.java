package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPersonnelMonthlyReserveFundDao {

	public int addPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;

	public int updatePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ; 

	public void deletePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;

	public void verifyPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;	

	public void unVerifyPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) ;

	public PersonnelMonthlyReserveFund getPersonnelMonthlyReserveFund(String id) ;	

	public Pager<PersonnelMonthlyReserveFund> queryPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund,  UserPermit userPermit,Pager<PersonnelMonthlyReserveFund> pager);

}