package com.pm.excel.persoonnelmonthly;

import java.util.Map;

import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyOfficial;
import com.pm.domain.business.StaffCost;
import com.pm.service.IApplyApproveService;
import com.pm.service.IPersonnelMonthlyOfficialService;
import com.pm.util.PersonnelMonthlyUtil;

public class ExcelProcessOfficial extends AbstractPMExcel<PersonnelMonthlyOfficial>{
	

	IPersonnelMonthlyOfficialService service = null;

	public ExcelProcessOfficial(IApplyApproveService applyApproveService , IPersonnelMonthlyOfficialService service) {
		super(applyApproveService);
		this.service = service;
	}
	

	@Override
	public int addPersonnelMonthly(PersonnelMonthlyOfficial base) {		
		return service.addPersonnelMonthlyOfficial(base);
	}

	@Override
	public boolean checkPersonnelMonthly(PersonnelMonthlyOfficial base, StaffCost staffCost, Map<String,InsuranceGrade> insuranceGrades) {		

		if(base.getCurr_salary() == 0){
			base.setErrorInfo(base.getErrorInfo() + "没有设置转正当月工资;");
		}
		
		if(staffCost != null){
			base.setJoin_datetime(staffCost.getJoin_datetime());
			if(staffCost.getConfirmation_date() != null) {
				base.setConfirmation_date(staffCost.getConfirmation_date());
			}
			base.setTryout_salary(staffCost.getTryout_salary());
			base.setOfficial_salary(staffCost.getOfficial_salary());
			if(base.getCurr_salary() > staffCost.getOfficial_salary() || base.getCurr_salary() < staffCost.getTryout_salary()){
				base.setErrorInfo(base.getErrorInfo() + "转正当月工资设置错误;");
			}
		}
		
		boolean b = PersonnelMonthlyUtil.validate(base);
		if(!b)  base.setErrorInfo(base.getErrorInfo() + "转正日期和月报月份不符;");
		return b;
	}
}
