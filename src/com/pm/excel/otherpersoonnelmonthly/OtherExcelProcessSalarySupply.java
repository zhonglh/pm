package com.pm.excel.otherpersoonnelmonthly;

import java.util.Map;

import com.pm.service.IOtherPersonnelMonthlySalarySupplyService;
import org.apache.commons.lang.StringUtils;

import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlySalarySupply;
import com.pm.domain.business.OtherStaff;
import com.pm.service.IApplyApproveService;
import com.pm.service.IPersonnelMonthlySalarySupplyService;
import com.pm.util.PersonnelMonthlyUtil;

public class OtherExcelProcessSalarySupply extends OtherAbstractPMExcel<PersonnelMonthlySalarySupply> {
	

	IOtherPersonnelMonthlySalarySupplyService service = null;

	public OtherExcelProcessSalarySupply(IApplyApproveService applyApproveService , IOtherPersonnelMonthlySalarySupplyService service) {
		super(applyApproveService);
		this.service = service;
	}
	

	@Override
	public int addPersonnelMonthly(PersonnelMonthlySalarySupply base) {		
		return service.addPersonnelMonthlySalarySupply(base);
	}


	@Override
	public boolean checkPersonnelMonthly(
			PersonnelMonthlySalarySupply base, 
			OtherStaff staffCost,
			Map<String, InsuranceGrade> insuranceGrades) {
		

		if(StringUtils.isEmpty(base.getSupply_type())) base.setErrorInfo(base.getErrorInfo() + "工资补充类型没有设置;");
		if(base.getSupply_time() == null) base.setErrorInfo(base.getErrorInfo() + "工资补充时间没有设置;");
		if(base.getAmount() == 0) base.setErrorInfo(base.getErrorInfo() + "补贴扣除金额没有设置;");
		
		boolean b = PersonnelMonthlyUtil.validate(base);
		if(!b)  base.setErrorInfo(base.getErrorInfo() + "工资补充日期和月报月份不符;");
		return b;
	}

}
