package com.pm.service.impl;

import com.pm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pm.util.constant.EnumEntityType;


/**
 * @author Administrator
 */
@Component
public class CommonServiceImpl implements ICommonService {


	@Autowired
	private ISalaryService salaryService;	

	@Autowired
	private IProjectExpendService projectExpendService;

	@Autowired
	private IProjectExpendPayService projectExpendPayService;

	@Autowired
	private IReimburseCostsService reimburseCostsService;

	@Autowired
	private IMonthlyStatementService monthlyStatementService;

	@Autowired
	private IInvoiceService invoiceService;

	@Autowired
	private IReceivedPaymentService receivedPaymentService;

	@Autowired
	private IDepartCostsService departCostsService;

	@Autowired
	private IPersonnelMonthlyBaseService personnelMonthlyBaseService;
	@Autowired
	private IOtherPersonnelMonthlyBaseService otherPersonnelMonthlyBaseService;

	@Autowired
	@Qualifier("otherInsuranceServiceImpl")
	private IInsuranceService otherInsuranceService;

	@Autowired
	@Qualifier("insuranceServiceImpl")
	private IInsuranceService insuranceService;

	@Autowired
	private IOtherSalaryService otherSalaryService;

	@Autowired
	private IStaffPerformanceService staffPerformanceService;

	@Autowired
	private ICommonCostService commonCostService;
	

	@Override
	public IBaseService getBusinessService(String type) {
		if(EnumEntityType.SALARY.name().equals(type)){
			return salaryService;
		}else if(EnumEntityType.PROJECT_EXPEND.name().equals(type)){
			return projectExpendService;
		}else if(EnumEntityType.PROJECT_EXPEND_PAY.name().equals(type)){
			return projectExpendPayService;
		}else if(EnumEntityType.REIMBURSE_COSTS.name().equals(type)){
			return reimburseCostsService;
		}else if(EnumEntityType.MONTHLY_STATEMENT.name().equals(type)){
			return monthlyStatementService;
		}else if(EnumEntityType.INVOICE.name().equals(type)){
			return invoiceService;
		}else if(EnumEntityType.RECEIVED_PAYMENT.name().equals(type)){
			return receivedPaymentService;
		}else if(EnumEntityType.PERSONNELMONTHLYBASE.name().equals(type)){
			return personnelMonthlyBaseService;
		}else if(EnumEntityType.OTHERPERSONNELMONTHLYBASE.name().equals(type)){
			return otherPersonnelMonthlyBaseService;
		}else if(EnumEntityType.DEPART_COSTS.name().equals(type)){
			return departCostsService;
		}else if(EnumEntityType.INSURANCE.name().equals(type)){
			return insuranceService;
		}else if(EnumEntityType.OTHER_INSURANCE.name().equals(type)){
			return otherInsuranceService;
		}else if(EnumEntityType.OTHER_SALARY.name().equals(type)){
			return otherSalaryService;
		}else if(EnumEntityType.STAFF_PERFORMANCE.name().equals(type)){
			return staffPerformanceService;
		}else if(EnumEntityType.COMMONCOST.name().equals(type)){
			return commonCostService;
		}
		
		return null;
	}

}
