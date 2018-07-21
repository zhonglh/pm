package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPersonnelMonthlyInsuranceDao {

	public int addPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;

	public int updatePersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ; 

	public void deletePersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;

	public void verifyPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;	

	public void unVerifyPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) ;

	public PersonnelMonthlyInsurance getPersonnelMonthlyInsurance(String id) ;	

	public Pager<PersonnelMonthlyInsurance> queryPersonnelMonthlyInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance,  UserPermit userPermit,Pager<PersonnelMonthlyInsurance> pager);

}