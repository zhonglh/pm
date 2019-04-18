package com.pm.excel.otherpersoonnelmonthly;

import java.util.Map;

import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.OtherStaff;
import com.pm.service.IApplyApproveService;
import com.pm.service.IOtherPersonnelMonthlyBonusService;
import com.pm.service.IPersonnelMonthlyBonusService;
import com.pm.util.PersonnelMonthlyUtil;

/**
 * @author Administrator
 */
public class OtherExcelProcessBonus extends OtherAbstractPMExcel<PersonnelMonthlyBonus> {


	IOtherPersonnelMonthlyBonusService service = null;

	public OtherExcelProcessBonus(IApplyApproveService applyApproveService , IOtherPersonnelMonthlyBonusService service) {
		super(applyApproveService);
		this.service = service;
	}


	@Override
	public int addPersonnelMonthly(PersonnelMonthlyBonus base) {		
		return service.addPersonnelMonthlyBonus(base);
	}

	@Override
	public boolean checkPersonnelMonthly(PersonnelMonthlyBonus base, OtherStaff staffCost, Map<String,InsuranceGrade> insuranceGrades) {
		if(staffCost != null){
			base.setJoin_datetime(staffCost.getJoin_datetime());
		}
		if(base.getChange_time() == null) {
			base.setErrorInfo(base.getErrorInfo() + "奖惩时间没有设置;");
		}
		if(base.getAmount() == 0) {
			base.setErrorInfo(base.getErrorInfo() + "奖惩金额没有设置;");
		}
		

		boolean b = PersonnelMonthlyUtil.validate(base);
		if(!b)  {
			base.setErrorInfo(base.getErrorInfo() + "奖惩时间和月报月份不符;");
		}
		return b;
	}


}
