package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IOtherPersonnelMonthlySalaryDao {

	public int addPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ;

	public int updatePersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ; 

	public void deletePersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ;

	public void verifyPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ;	

	public void unVerifyPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary) ;

	public PersonnelMonthlySalary getPersonnelMonthlySalary(String id) ;	

	public Pager<PersonnelMonthlySalary> queryPersonnelMonthlySalary(PersonnelMonthlySalary personnelMonthlySalary,  UserPermit userPermit,Pager<PersonnelMonthlySalary> pager);

}