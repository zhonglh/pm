package com.pm.excel.otherpersoonnelmonthly;

import java.util.Map;

import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.OtherStaff;
import com.pm.service.IApplyApproveService;
import com.pm.service.IOtherPersonnelMonthlySalaryService;
import com.pm.service.IPersonnelMonthlySalaryService;
import com.pm.util.PersonnelMonthlyUtil;

public class OtherExcelProcessSalary extends OtherAbstractPMExcel<PersonnelMonthlySalary> {
	

	IOtherPersonnelMonthlySalaryService service = null;

	public OtherExcelProcessSalary(IApplyApproveService applyApproveService , IOtherPersonnelMonthlySalaryService service) {
		super(applyApproveService);
		this.service = service;
	}
	

	@Override
	public int addPersonnelMonthly(PersonnelMonthlySalary base) {		
		return service.addPersonnelMonthlySalary(base);
	}


	@Override
	public boolean checkPersonnelMonthly(PersonnelMonthlySalary base, OtherStaff staffCost, Map<String,InsuranceGrade> insuranceGrades) {
		
		if(staffCost != null ){
			base.setJoin_datetime(staffCost.getJoin_datetime());		
			if(staffCost.getConfirmation_date() == null){
				base.setOld_salary(staffCost.getOfficial_salary());
			}else {
				if(staffCost.getConfirmation_date().compareTo(base.getChange_time()) > 0){
					base.setOld_salary(staffCost.getTryout_salary());
				}else {
					base.setOld_salary(staffCost.getOfficial_salary());
				}
			}
		}
		

		if(base.getChange_time() == null) base.setErrorInfo(base.getErrorInfo() + "调薪时间没有设置;");
		if(base.getNew_salary() == 0) base.setErrorInfo(base.getErrorInfo() + "调薪后薪资没有设置;");
		if(base.getCurr_salary() == 0) base.setErrorInfo(base.getErrorInfo() + "调薪当月工资没有设置;");
		
		boolean b = PersonnelMonthlyUtil.validate(base);
		if(!b)  base.setErrorInfo(base.getErrorInfo() + "调薪日期和月报月份不符;");
		
		return false;
	}

}
