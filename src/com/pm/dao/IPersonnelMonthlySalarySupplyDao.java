package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlySalarySupply;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPersonnelMonthlySalarySupplyDao {

	public int addPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ;

	public int updatePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ; 

	public void deletePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ;

	public void verifyPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ;	

	public void unVerifyPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply) ;

	public PersonnelMonthlySalarySupply getPersonnelMonthlySalarySupply(String id) ;	

	public Pager<PersonnelMonthlySalarySupply> queryPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply personnelMonthlySalarySupply,  UserPermit userPermit,Pager<PersonnelMonthlySalarySupply> pager);

}