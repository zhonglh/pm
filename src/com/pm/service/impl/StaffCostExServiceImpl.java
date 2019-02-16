package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.exceptions.PMException;
import com.pm.dao.IStaffCostDao;
import com.pm.dao.IStaffCostExDao;
import com.pm.dao.IStaffRaiseRecordDao;
import com.pm.dao.IStaffRewardPenaltyDao;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffRaiseRecord;
import com.pm.domain.business.StaffRewardPenalty;
import com.pm.service.IInsuranceGradeService;
import com.pm.service.IStaffCostExService;

@Component
public class StaffCostExServiceImpl implements IStaffCostExService{
	
	

	@Autowired IStaffRewardPenaltyDao staffRewardPenaltyDao;
	@Autowired IStaffRaiseRecordDao raiserecordDao;
	
	@Autowired  private IStaffCostDao staffCostDao;
	@Autowired  private IStaffCostExDao staffCostExDao;
	@Autowired  private IInsuranceGradeService insuranceGradeService;
	
	/**
	 * 组织加薪记录对象
	 * @param personnelMonthlySalary
	 * @return
	 */
	private StaffRaiseRecord buildStaffRaiseRecord(PersonnelMonthlySalary personnelMonthlySalary) {
		StaffRaiseRecord raiserecord = new StaffRaiseRecord();
		raiserecord.setId(personnelMonthlySalary.getId());
		raiserecord.setStaff_id(personnelMonthlySalary.getStaff_id());
		raiserecord.setRaise_time(personnelMonthlySalary.getChange_time());
		raiserecord.setAmount(personnelMonthlySalary.getNew_salary()-personnelMonthlySalary.getOld_salary());
		raiserecord.setDescription(personnelMonthlySalary.getDescription());
		raiserecord.setBuild_datetime(personnelMonthlySalary.getBuild_datetime());
		raiserecord.setBuild_userid(personnelMonthlySalary.getBuild_userid());
		raiserecord.setBuild_username(personnelMonthlySalary.getBuild_username());
		return raiserecord;
	}

	@Override
	public void checkAddSalary(PersonnelMonthlySalary personnelMonthlySalary) {
		int size= staffCostExDao.checkAddSalary1(personnelMonthlySalary);
		if(size == 0) {
			size = staffCostExDao.checkAddSalary2(personnelMonthlySalary);
		}
		if(size == 1){
			StaffRaiseRecord raiserecord = buildStaffRaiseRecord(personnelMonthlySalary);
			raiserecordDao.addRaiseRecord(raiserecord);
		}
	}
	@Override
	public void unCheckAddSalary(PersonnelMonthlySalary personnelMonthlySalary) {
		int size= staffCostExDao.unCheckAddSalary1(personnelMonthlySalary);
		if(size == 0) {
			size = staffCostExDao.unCheckAddSalary2(personnelMonthlySalary);
		}
		if(size == 1){
			StaffRaiseRecord raiserecord = buildStaffRaiseRecord(personnelMonthlySalary);
			raiserecordDao.deleteRaiseRecord(raiserecord);
		}
	}
	@Override
	public void checkDecrSalary(PersonnelMonthlySalary personnelMonthlySalary) {
		int size= staffCostExDao.checkDecrSalary1(personnelMonthlySalary);
		if(size == 0) {
			size = staffCostExDao.checkDecrSalary2(personnelMonthlySalary);
		}

		if(size == 1){
			StaffRaiseRecord raiserecord = buildStaffRaiseRecord(personnelMonthlySalary);
			raiserecordDao.addRaiseRecord(raiserecord);
		}
	}
	@Override
	public void unCheckDecrSalary(PersonnelMonthlySalary personnelMonthlySalary) {
		int size= staffCostExDao.unCheckDecrSalary1(personnelMonthlySalary);
		if(size == 0) {
			size = staffCostExDao.unCheckDecrSalary2(personnelMonthlySalary);
		}

		if(size == 1){
			StaffRaiseRecord raiserecord = buildStaffRaiseRecord(personnelMonthlySalary);
			raiserecordDao.deleteRaiseRecord(raiserecord);
		}
	}
	
	

	@Override
	public void checkReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {
		staffCostExDao.checkReserveFund(personnelMonthlyReserveFund);
		InsuranceGrade insuranceGrade =insuranceGradeService.getInsuranceGrade(personnelMonthlyReserveFund.getInsurance_grade_id());
		StaffCost staffCost1 = staffCostDao.getStaffCost(personnelMonthlyReserveFund.getStaff_id());
		if(
				insuranceGrade.getEndowment_insurance_bypersonal() != staffCost1.getEndowment_insurance_bypersonal() ||
				insuranceGrade.getMedical_insurance_bypersonal() != staffCost1.getMedical_insurance_bypersonal() ||
				insuranceGrade.getLosejob_insurance_bypersonal() != staffCost1.getLosejob_insurance_bypersonal() ||
				insuranceGrade.getReservefund_bypersonal() != staffCost1.getReservefund_bypersonal() ||
				
				insuranceGrade.getEndowment_insurance_bycompany() != staffCost1.getEndowment_insurance_bycompany() ||
				insuranceGrade.getMedical_insurance_bycompany() != staffCost1.getMedical_insurance_bycompany() ||
				insuranceGrade.getLosejob_insurance_bycompany() != staffCost1.getLosejob_insurance_bycompany() ||
				insuranceGrade.getMaternity_insurance_bycompany() != staffCost1.getMaternity_insurance_bycompany() ||
				insuranceGrade.getJobharm_insurance_bycompany() != staffCost1.getJobharm_insurance_bycompany() ||
				insuranceGrade.getReservefund_bypcompany() != staffCost1.getReservefund_bypcompany() 
			){
				staffCost1.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
				staffCost1.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
				staffCost1.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
				staffCost1.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());

				staffCost1.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
				staffCost1.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
				staffCost1.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
				staffCost1.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
				staffCost1.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
				staffCost1.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());
				

				int size = staffCostDao.updateStaffCost(staffCost1);
				//if(size == 0) throw new PMException("111111", "操作错误，已有其他人和你同时修改人员成本信息，请重新操作！");
			}
	}
	
	@Override
	public void unCheckReserveFund(PersonnelMonthlyReserveFund personnelMonthlyReserveFund) {

		staffCostExDao.unCheckReserveFund(personnelMonthlyReserveFund);
		InsuranceGrade insuranceGrade =insuranceGradeService.getInsuranceGrade(personnelMonthlyReserveFund.getOld_insurance_grade_id());
		StaffCost staffCost1 = staffCostDao.getStaffCost(personnelMonthlyReserveFund.getStaff_id());
		if(
				insuranceGrade.getEndowment_insurance_bypersonal() != staffCost1.getEndowment_insurance_bypersonal() ||
				insuranceGrade.getMedical_insurance_bypersonal() != staffCost1.getMedical_insurance_bypersonal() ||
				insuranceGrade.getLosejob_insurance_bypersonal() != staffCost1.getLosejob_insurance_bypersonal() ||
				insuranceGrade.getReservefund_bypersonal() != staffCost1.getReservefund_bypersonal() ||
				
				insuranceGrade.getEndowment_insurance_bycompany() != staffCost1.getEndowment_insurance_bycompany() ||
				insuranceGrade.getMedical_insurance_bycompany() != staffCost1.getMedical_insurance_bycompany() ||
				insuranceGrade.getLosejob_insurance_bycompany() != staffCost1.getLosejob_insurance_bycompany() ||
				insuranceGrade.getMaternity_insurance_bycompany() != staffCost1.getMaternity_insurance_bycompany() ||
				insuranceGrade.getJobharm_insurance_bycompany() != staffCost1.getJobharm_insurance_bycompany() ||
				insuranceGrade.getReservefund_bypcompany() != staffCost1.getReservefund_bypcompany() 
			){
				staffCost1.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
				staffCost1.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
				staffCost1.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
				staffCost1.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());

				staffCost1.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
				staffCost1.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
				staffCost1.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
				staffCost1.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
				staffCost1.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
				staffCost1.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());

				int size = staffCostDao.updateStaffCost(staffCost1);
				//if(size == 0) throw new PMException("111111", "操作错误，已有其他人和你同时修改人员成本信息，请重新操作！");
			}
	
	}	
	
	@Override
	public void checkInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		staffCostExDao.checkInsurance(personnelMonthlyInsurance);
		InsuranceGrade insuranceGrade =insuranceGradeService.getInsuranceGrade(personnelMonthlyInsurance.getInsurance_grade_id());
		StaffCost staffCost1 = staffCostDao.getStaffCost(personnelMonthlyInsurance.getStaff_id());
		if(
				insuranceGrade.getEndowment_insurance_bypersonal() != staffCost1.getEndowment_insurance_bypersonal() ||
				insuranceGrade.getMedical_insurance_bypersonal() != staffCost1.getMedical_insurance_bypersonal() ||
				insuranceGrade.getLosejob_insurance_bypersonal() != staffCost1.getLosejob_insurance_bypersonal() ||
				insuranceGrade.getReservefund_bypersonal() != staffCost1.getReservefund_bypersonal() ||
				
				insuranceGrade.getEndowment_insurance_bycompany() != staffCost1.getEndowment_insurance_bycompany() ||
				insuranceGrade.getMedical_insurance_bycompany() != staffCost1.getMedical_insurance_bycompany() ||
				insuranceGrade.getLosejob_insurance_bycompany() != staffCost1.getLosejob_insurance_bycompany() ||
				insuranceGrade.getMaternity_insurance_bycompany() != staffCost1.getMaternity_insurance_bycompany() ||
				insuranceGrade.getJobharm_insurance_bycompany() != staffCost1.getJobharm_insurance_bycompany() ||
				insuranceGrade.getReservefund_bypcompany() != staffCost1.getReservefund_bypcompany() 
			){
				staffCost1.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
				staffCost1.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
				staffCost1.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
				staffCost1.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());

				staffCost1.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
				staffCost1.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
				staffCost1.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
				staffCost1.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
				staffCost1.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
				staffCost1.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());

				int size = staffCostDao.updateStaffCost(staffCost1);
				//if(size == 0) throw new PMException("111111", "操作错误，已有其他人和你同时修改人员成本信息，请重新操作！");
			}
		
	}
	@Override
	public void unCheckInsurance(PersonnelMonthlyInsurance personnelMonthlyInsurance) {
		staffCostExDao.unCheckInsurance(personnelMonthlyInsurance);
		InsuranceGrade insuranceGrade =insuranceGradeService.getInsuranceGrade(personnelMonthlyInsurance.getOld_insurance_grade_id());
		StaffCost staffCost1 = staffCostDao.getStaffCost(personnelMonthlyInsurance.getStaff_id());
		if(
				insuranceGrade.getEndowment_insurance_bypersonal() != staffCost1.getEndowment_insurance_bypersonal() ||
				insuranceGrade.getMedical_insurance_bypersonal() != staffCost1.getMedical_insurance_bypersonal() ||
				insuranceGrade.getLosejob_insurance_bypersonal() != staffCost1.getLosejob_insurance_bypersonal() ||
				insuranceGrade.getReservefund_bypersonal() != staffCost1.getReservefund_bypersonal() ||
				
				insuranceGrade.getEndowment_insurance_bycompany() != staffCost1.getEndowment_insurance_bycompany() ||
				insuranceGrade.getMedical_insurance_bycompany() != staffCost1.getMedical_insurance_bycompany() ||
				insuranceGrade.getLosejob_insurance_bycompany() != staffCost1.getLosejob_insurance_bycompany() ||
				insuranceGrade.getMaternity_insurance_bycompany() != staffCost1.getMaternity_insurance_bycompany() ||
				insuranceGrade.getJobharm_insurance_bycompany() != staffCost1.getJobharm_insurance_bycompany() ||
				insuranceGrade.getReservefund_bypcompany() != staffCost1.getReservefund_bypcompany() 
			){
				staffCost1.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
				staffCost1.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
				staffCost1.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
				staffCost1.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());

				staffCost1.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
				staffCost1.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
				staffCost1.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
				staffCost1.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
				staffCost1.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
				staffCost1.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());

				int size = staffCostDao.updateStaffCost(staffCost1);
				//if(size == 0) throw new PMException("111111", "操作错误，已有其他人和你同时修改人员成本信息，请重新操作！");
			}
	}
	

	
	@Override
	public void checkBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		int size = staffCostExDao.checkBonus(personnelMonthlyBonus);
		
		if(size == 1){
			StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty();
			staffRewardPenalty.setId(personnelMonthlyBonus.getId());
			staffRewardPenalty.setStaff_id(personnelMonthlyBonus.getStaff_id());
			staffRewardPenalty.setRp_time(personnelMonthlyBonus.getChange_time());
			staffRewardPenalty.setAmount(personnelMonthlyBonus.getAmount());
			staffRewardPenalty.setDescription(personnelMonthlyBonus.getDescription());
			staffRewardPenalty.setBuild_datetime(personnelMonthlyBonus.getBuild_datetime());
			staffRewardPenalty.setBuild_userid(personnelMonthlyBonus.getBuild_userid());
			staffRewardPenalty.setBuild_username(personnelMonthlyBonus.getBuild_username());
			staffRewardPenaltyDao.addStaffRewardPenalty(staffRewardPenalty);
		}
		
	}
	@Override
	public void unCheckBonus(PersonnelMonthlyBonus personnelMonthlyBonus) {
		int size = staffCostExDao.unCheckBonus(personnelMonthlyBonus);
		
		if(size == 1){
			StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty();
			staffRewardPenalty.setId(personnelMonthlyBonus.getId());
			staffRewardPenaltyDao.deleteStaffRewardPenalty(staffRewardPenalty);
		}
	}

}
