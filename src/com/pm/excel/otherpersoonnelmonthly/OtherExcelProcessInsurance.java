package com.pm.excel.otherpersoonnelmonthly;

import java.util.Map;

import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyInsurance;
import com.pm.domain.business.OtherStaff;
import com.pm.service.IApplyApproveService;
import com.pm.service.IOtherPersonnelMonthlyInsuranceService;
import com.pm.service.IPersonnelMonthlyInsuranceService;

public class OtherExcelProcessInsurance extends OtherAbstractPMExcel<PersonnelMonthlyInsurance>{
	

	IOtherPersonnelMonthlyInsuranceService service = null;

	public OtherExcelProcessInsurance(IApplyApproveService applyApproveService , IOtherPersonnelMonthlyInsuranceService service) {
		super(applyApproveService);
		this.service = service;
	}
	

	@Override
	public int addPersonnelMonthly(PersonnelMonthlyInsurance base) {		
		return service.addPersonnelMonthlyInsurance(base);
	}

	@Override
	public boolean checkPersonnelMonthly(PersonnelMonthlyInsurance base, OtherStaff staffCost, Map<String,InsuranceGrade> insuranceGrades) {
		if(staffCost != null){
			base.setOld_insurance_grade_id(staffCost.getInsurance_grade_id());
			base.setOld_securty_unit(staffCost.getSecurty_unit());
		}
		

		if(base.getInsurance_radix() == null) base.setErrorInfo(base.getErrorInfo() + "社保档次没有设置;");
		else {
			if(insuranceGrades != null){
				InsuranceGrade insuranceGrade = insuranceGrades.get(base.getInsurance_radix());
				if(insuranceGrade != null){
					base.setInsurance_grade_id(insuranceGrade.getInsurance_grade_id());
					base.setInsurance_radix(insuranceGrade.getInsurance_radix());				
					base.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
					base.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
					base.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
					base.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
					base.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
					base.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
					base.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
					base.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());	
				}else {
					base.setErrorInfo(base.getErrorInfo() + "社保档次错误;");
				}
			}else {
				base.setErrorInfo(base.getErrorInfo() + "社保档次信息错误;");
			}
		}
		
		
		
		
		return true;
	}

}
