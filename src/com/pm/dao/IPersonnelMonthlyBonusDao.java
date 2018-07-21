package com.pm.dao;

import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPersonnelMonthlyBonusDao {

	public int addPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;

	public int updatePersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ; 

	public void deletePersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;

	public void verifyPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;	

	public void unVerifyPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus) ;

	public PersonnelMonthlyBonus getPersonnelMonthlyBonus(String id) ;	

	public Pager<PersonnelMonthlyBonus> queryPersonnelMonthlyBonus(PersonnelMonthlyBonus personnelMonthlyBonus,  UserPermit userPermit,Pager<PersonnelMonthlyBonus> pager);

}