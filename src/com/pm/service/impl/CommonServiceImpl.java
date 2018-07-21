package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.service.IBaseService;
import com.pm.service.ICommonService;
import com.pm.service.IDepartCostsService;
import com.pm.service.IInsuranceService;
import com.pm.service.IInvoiceService;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IPersonnelMonthlyBaseService;
import com.pm.service.IProjectExpendService;
import com.pm.service.IReceivedPaymentService;
import com.pm.service.IReimburseCostsService;
import com.pm.service.ISalaryService;
import com.pm.util.constant.EnumEntityType;


@Component
public class CommonServiceImpl implements ICommonService {


	@Autowired
	private ISalaryService salaryService;	

	@Autowired
	private IProjectExpendService projectExpendService;

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
	private IInsuranceService insuranceService;
	
	

	@Override
	public IBaseService getBusinessService(String type) {
		if(EnumEntityType.SALARY.name().equals(type)){
			return salaryService;
		}else if(EnumEntityType.PROJECT_EXPEND.name().equals(type)){
			return projectExpendService;
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
		}else if(EnumEntityType.DEPART_COSTS.name().equals(type)){
			return departCostsService;
		}else if(EnumEntityType.INSURANCE.name().equals(type)){
			return insuranceService;
		}
		
		return null;
	}

}
