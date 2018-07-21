package com.pm.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IPersonnelMonthlyBaseDao;
import com.pm.dao.IStaffCostDao;
import com.pm.domain.business.PersonnelMonthlyBase;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.service.IPersonnelMonthlyBaseService;
import com.pm.service.IPersonnelMonthlyBonusService;
import com.pm.service.IPersonnelMonthlyInsuranceService;
import com.pm.service.IPersonnelMonthlyOfficialService;
import com.pm.service.IPersonnelMonthlyReserveFundService;
import com.pm.service.IPersonnelMonthlySalaryService;
import com.pm.service.IPersonnelMonthlySalarySupplyService;
import com.pm.service.IStaffCostExService;
import com.pm.util.constant.EnumPersonnelMonthlyType;
import com.pm.vo.UserPermit;

@Component
public class PersonnelMonthlyBaseServiceImpl implements  IPersonnelMonthlyBaseService {

	@Autowired IPersonnelMonthlyBaseDao personnelMonthlyBaseDao;

	@Autowired IPersonnelMonthlyBonusService personnelMonthlyBonusService;
	@Autowired IPersonnelMonthlyInsuranceService personnelMonthlyInsuranceService;
	@Autowired IPersonnelMonthlyOfficialService personnelMonthlyOfficialService;
	@Autowired IPersonnelMonthlyReserveFundService personnelMonthlyReserveFundService;
	@Autowired IPersonnelMonthlySalaryService personnelMonthlySalaryService;
	@Autowired IPersonnelMonthlySalarySupplyService personnelMonthlySalarySupplyService;
	
	@Autowired IStaffCostDao staffCostDao;
	@Autowired IStaffCostExService staffCostExService;
	

	@Override
	public void verifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase) {
		PersonnelMonthlyBase base = personnelMonthlyBaseDao.getPersonnelMonthlyBase(personnelMonthlyBase.getId());
		if(!StringUtils.isEmpty(base.getVerify_userid()))  throw new PMException (CommonErrorConstants.e029901); 
		int size = personnelMonthlyBaseDao.verifyPersonnelMonthlyBase(personnelMonthlyBase);
		if(size == 1){
			if(EnumPersonnelMonthlyType.Bonus.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyBonus bonus = personnelMonthlyBonusService.getPersonnelMonthlyBonus(base.getId());
				staffCostExService.checkBonus(bonus);
			}else if(EnumPersonnelMonthlyType.AddInsurance.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyInsurance insurance = personnelMonthlyInsuranceService.getPersonnelMonthlyInsurance(base.getId());
				staffCostExService.checkInsurance(insurance);
			}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyInsurance insurance = personnelMonthlyInsuranceService.getPersonnelMonthlyInsurance(base.getId());
				staffCostExService.checkInsurance(insurance);
			}else if(EnumPersonnelMonthlyType.AddReserveFund.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyReserveFund reservefund = personnelMonthlyReserveFundService.getPersonnelMonthlyReserveFund(base.getId());
				staffCostExService.checkReserveFund(reservefund);
			}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyReserveFund reservefund = personnelMonthlyReserveFundService.getPersonnelMonthlyReserveFund(base.getId());
				staffCostExService.checkReserveFund(reservefund);
			}else if(EnumPersonnelMonthlyType.Official.getId().equals(base.getMonthly_type())){
				;
			}else if(EnumPersonnelMonthlyType.AddSalary.getId().equals(base.getMonthly_type())){
				PersonnelMonthlySalary salary = personnelMonthlySalaryService.getPersonnelMonthlySalary(base.getId());
				staffCostExService.checkAddSalary(salary);
			}else if(EnumPersonnelMonthlyType.DecrSalary.getId().equals(base.getMonthly_type())){
				PersonnelMonthlySalary salary = personnelMonthlySalaryService.getPersonnelMonthlySalary(base.getId());
				staffCostExService.checkDecrSalary(salary);
			}else if(EnumPersonnelMonthlyType.SalarySupply.getId().equals(base.getMonthly_type())){
				;
			}
		}else {
			throw new PMException (CommonErrorConstants.e029901);
		}
	}

	@Override
	public void unVerify(String id) {
		PersonnelMonthlyBase base = personnelMonthlyBaseDao.getPersonnelMonthlyBase(id);
		if(StringUtils.isEmpty(base.getVerify_userid()))  throw new PMException (CommonErrorConstants.e029902); 
		int size = personnelMonthlyBaseDao.unVerifyPersonnelMonthlyBase(base);
		if(size == 1){
			if(EnumPersonnelMonthlyType.Bonus.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyBonus bonus = personnelMonthlyBonusService.getPersonnelMonthlyBonus(base.getId());
				staffCostExService.unCheckBonus(bonus);
			}else if(EnumPersonnelMonthlyType.AddInsurance.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyInsurance insurance = personnelMonthlyInsuranceService.getPersonnelMonthlyInsurance(base.getId());
				staffCostExService.unCheckInsurance(insurance);
			}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyInsurance insurance = personnelMonthlyInsuranceService.getPersonnelMonthlyInsurance(base.getId());
				staffCostExService.unCheckInsurance(insurance);
			}else if(EnumPersonnelMonthlyType.AddReserveFund.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyReserveFund reservefund = personnelMonthlyReserveFundService.getPersonnelMonthlyReserveFund(base.getId());
				staffCostExService.unCheckReserveFund(reservefund);
			}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(base.getMonthly_type())){
				PersonnelMonthlyReserveFund reservefund = personnelMonthlyReserveFundService.getPersonnelMonthlyReserveFund(base.getId());
				staffCostExService.unCheckReserveFund(reservefund);
			}else if(EnumPersonnelMonthlyType.Official.getId().equals(base.getMonthly_type())){
				
			}else if(EnumPersonnelMonthlyType.AddSalary.getId().equals(base.getMonthly_type())){
				PersonnelMonthlySalary salary = personnelMonthlySalaryService.getPersonnelMonthlySalary(base.getId());
				staffCostExService.unCheckAddSalary(salary);
			}else if(EnumPersonnelMonthlyType.DecrSalary.getId().equals(base.getMonthly_type())){
				PersonnelMonthlySalary salary = personnelMonthlySalaryService.getPersonnelMonthlySalary(base.getId());
				staffCostExService.unCheckDecrSalary(salary);
			}else if(EnumPersonnelMonthlyType.SalarySupply.getId().equals(base.getMonthly_type())){
				;
			}
		}else {
			throw new PMException (CommonErrorConstants.e029902);
		}
	}

	@Override
	public PersonnelMonthlyBase getPersonnelMonthlyBase(String id) {
		return personnelMonthlyBaseDao.getPersonnelMonthlyBase(id);
	}
	

	@Override
	public boolean isExistNotCheckByWorkAttendance (String project_id , int the_month){
		PersonnelMonthlyBase personnelMonthlyBase = new PersonnelMonthlyBase();
		personnelMonthlyBase.setProject_id(project_id);
		personnelMonthlyBase.setThe_month(the_month);
		int size = personnelMonthlyBaseDao.getNotCheckNumByWorkAttendance(personnelMonthlyBase);
		return size != 0 ;
	}

	@Override
	public Pager<PersonnelMonthlyBase> queryPersonnelMonthlyBase(
		PersonnelMonthlyBase personnelMonthlyBase,
		UserPermit userPermit,
		Pager<PersonnelMonthlyBase> pager){

		return personnelMonthlyBaseDao.queryPersonnelMonthlyBase(personnelMonthlyBase, userPermit, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String id) {
		return (T)getPersonnelMonthlyBase(id);
	}



}