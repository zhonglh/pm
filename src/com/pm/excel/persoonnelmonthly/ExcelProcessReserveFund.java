package com.pm.excel.persoonnelmonthly;

import java.util.Map;

import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
import com.pm.domain.business.StaffCost;
import com.pm.service.IApplyApproveService;
import com.pm.service.IPersonnelMonthlyOfficialService;
import com.pm.service.IPersonnelMonthlyReserveFundService;

public class ExcelProcessReserveFund extends AbstractPMExcel<PersonnelMonthlyReserveFund> {


	IPersonnelMonthlyReserveFundService service = null;

	public ExcelProcessReserveFund(IApplyApproveService applyApproveService , IPersonnelMonthlyReserveFundService service) {
		super(applyApproveService);
		this.service = service;
	}
	

	@Override
	public int addPersonnelMonthly(PersonnelMonthlyReserveFund base) {		
		return service.addPersonnelMonthlyReserveFund(base);
	}

	
	
	@Override
	public boolean checkPersonnelMonthly(
				PersonnelMonthlyReserveFund base, 
				StaffCost staffCost,
				Map<String, InsuranceGrade> insuranceGrades) {
		
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
					base.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());
					base.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());
					base.setBase_cardinal(insuranceGrade.getBase_cardinal());
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
