package com.pm.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.dao.IInvoiceDao;
import com.pm.dao.IProjectDao;
import com.pm.dao.IReceivedPaymentDao;
import com.pm.dao.IThInvoiceDao;
import com.pm.dao.IThReceivedPaymentDao;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.Project;
import com.pm.domain.business.ReceivedPayment;
import com.pm.domain.business.ThInvoice;
import com.pm.domain.business.ThReceivedPayment;
import com.pm.domain.business.VoucherInterim;
import com.pm.domain.system.User;
import com.pm.service.IReceivedPaymentService;
import com.pm.service.IVoucherInterimService;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.EnumDataType;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;

@Component
public class ReceivedPaymentServiceImpl implements IReceivedPaymentService {

	@Autowired private IThReceivedPaymentDao thReceivedPaymentDao;
	@Autowired private IReceivedPaymentDao receivedPaymentDao;

	@Autowired private IThInvoiceDao thInvoiceDao;
	@Autowired private IInvoiceDao invoiceDao;

	@Autowired private IProjectDao projectDao;
	
	@Autowired private IVoucherInterimService voucherInterimService;
	

	@Override
	public int addReceivedPayment(ReceivedPayment receivedPayment) {
		int size = receivedPaymentDao.addReceivedPayment(receivedPayment);
		
		try{
			ThReceivedPayment thReceivedPayment = new ThReceivedPayment();
			BeanUtils.copyProperties(receivedPayment, thReceivedPayment);
			thReceivedPayment.setHis_operation_type(LogConstant.OPERATION_INSERT);
			thReceivedPaymentDao.addThReceivedPayment(thReceivedPayment);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return size;
	}

	@Override
	public int updateReceivedPayment(ReceivedPayment receivedPayment) {
		
		int size =  receivedPaymentDao.updateReceivedPayment(receivedPayment);

		try{
			ThReceivedPayment thReceivedPayment = new ThReceivedPayment();
			BeanUtils.copyProperties(receivedPayment, thReceivedPayment);
			thReceivedPayment.setHis_operation_type(LogConstant.OPERATION_UPDATE);
			thReceivedPaymentDao.addThReceivedPayment(thReceivedPayment);
		}catch(Exception e){
			e.printStackTrace();
		}
		return size;
	}

	@Override
	public void deleteReceivedPayment(ReceivedPayment[] receivedPayments) {
		for(ReceivedPayment receivedPayment : receivedPayments){
			receivedPaymentDao.deleteReceivedPayment(receivedPayment);
		}
	}

	@Override
	public void verifyReceivedPayment(ReceivedPayment receivedPayment) {
		int size = receivedPaymentDao.verifyReceivedPayment(receivedPayment);
		if(size == 0) throw new PMException (CommonErrorConstants.e029901);
		
		if(StringUtils.isNotEmpty(receivedPayment.getInvoice_id())){
			invoiceDao.updateInvoiceReceivedPayment(receivedPayment);
			invoiceDao.updateInvoiceIsReceivedPayment(receivedPayment);	
		}		

		/*
		try{
			ThReceivedPayment thReceivedPayment = new ThReceivedPayment();
			BeanUtils.copyProperties(receivedPayment, thReceivedPayment);
			thReceivedPayment.setHis_operation_type(LogConstant.OPERATION_CHECK);
			thReceivedPaymentDao.addThReceivedPayment(thReceivedPayment);
		}catch(Exception e){
			e.printStackTrace();
		}	
		*/	
		
		//插入发票历史信息表
		try{
			if(StringUtils.isNotEmpty(receivedPayment.getInvoice_id())){
				Invoice invoice = invoiceDao.getInvoice(receivedPayment.getInvoice_id());
				ThInvoice thInvoice = new ThInvoice();
				BeanUtils.copyProperties(invoice, thInvoice);
				thInvoice.setHis_operation_type(LogConstant.OPERATION_UPDATE);
				thInvoiceDao.addThInvoice(thInvoice);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

		//插入到凭证中
		handleVoucherInterim(receivedPayment , false);
	}

	/**
	 * 将收款数据插入到凭证信息表中
	 * @param receivedPayment	收款信息
	 * @param isnegative		是否为负数, 撤销复核为负数，  复核为正数
	 */
	private void handleVoucherInterim(ReceivedPayment receivedPayment , boolean isnegative) {
		User user = ThreadLocalUser.getUser();
		Project project = projectDao.getProject(receivedPayment.getProject_id());
		
		Date date = receivedPayment.getReceive_payment_datetime();
		VoucherInterim voucherInterim1 = new VoucherInterim(); 
		voucherInterim1.setId(IDKit.generateId());
		voucherInterim1.setData_id(receivedPayment.getReceive_payment_id());
		voucherInterim1.setData_type(EnumDataType.received_payment.getId());
		voucherInterim1.setVoucher_date(date);
		voucherInterim1.setTotal_money(receivedPayment.getReceive_payment_amount());
		voucherInterim1.setExported("0");		
		voucherInterim1.setMonths(DateKit.fmtDateToStr(date,"yyyyMM"));
		voucherInterim1.setYears(DateKit.fmtDateToStr(date,"yyyy"));
		voucherInterim1.setBuild_time(DateKit.getLastDayOfMonth(date));
		voucherInterim1.setVoucher_type("记");
		voucherInterim1.setBuild_username(user.getUser_name());
		//voucherInterim1.setDescription1("一个回款的导入");
		//voucherInterim1.setDescription2("借方：银行存款");
		voucherInterim1.setCourse_code("1002001");
		voucherInterim1.setSummary("收"+project.getProject_client_name()+"项目款");
		voucherInterim1.setCurrency_name("人民币");
		voucherInterim1.setDebit_amount(receivedPayment.getReceive_payment_amount());
		voucherInterim1.setClient_code(project.getProject_client_no	());
		voucherInterim1.setCash_flow_item("01");
		voucherInterim1.setCash_flow_debit_amount(receivedPayment.getReceive_payment_amount());
		
		
		VoucherInterim voucherInterim2 = new VoucherInterim();
		BeanUtils.copyProperties(voucherInterim1, voucherInterim2);
		voucherInterim2.setId(IDKit.generateId());
		//voucherInterim2.setDescription2("贷方：应收账款-已开票");
		voucherInterim2.setCourse_code("1122001");
		voucherInterim2.setCash_flow_debit_amount(0);
		voucherInterim2.setCash_flow_item(null);
		voucherInterim2.setDebit_amount(0);
		voucherInterim2.setLoan_amount(receivedPayment.getReceive_payment_amount());
		
		if(isnegative){
			if(voucherInterim1.getTotal_money()!=0) voucherInterim1.setTotal_money(voucherInterim1.getTotal_money()*-1);
			if(voucherInterim1.getDebit_amount()!=0) voucherInterim1.setDebit_amount(voucherInterim1.getDebit_amount()*-1);
			if(voucherInterim1.getLoan_amount()!=0) voucherInterim1.setLoan_amount(voucherInterim1.getLoan_amount()*-1);
			if(voucherInterim1.getCash_flow_debit_amount()!=0) voucherInterim1.setCash_flow_debit_amount(voucherInterim1.getCash_flow_debit_amount()*-1);
			if(voucherInterim1.getCash_flow_loan_amount()!=0) voucherInterim1.setCash_flow_loan_amount(voucherInterim1.getCash_flow_loan_amount()*-1);
			
			if(voucherInterim2.getTotal_money()!=0) voucherInterim2.setTotal_money(voucherInterim2.getTotal_money()*-1);
			if(voucherInterim2.getDebit_amount()!=0) voucherInterim2.setDebit_amount(voucherInterim2.getDebit_amount()*-1);
			if(voucherInterim2.getLoan_amount()!=0) voucherInterim2.setLoan_amount(voucherInterim2.getLoan_amount()*-1);
			if(voucherInterim2.getCash_flow_debit_amount()!=0) voucherInterim2.setCash_flow_debit_amount(voucherInterim2.getCash_flow_debit_amount()*-1);
			if(voucherInterim2.getCash_flow_loan_amount()!=0) voucherInterim2.setCash_flow_loan_amount(voucherInterim2.getCash_flow_loan_amount()*-1);
		}
		
		voucherInterimService.batchHandleVoucherInterim(new VoucherInterim[]{voucherInterim1 , voucherInterim2});
	}
	

	@Override
	public void unVerify(String id) {
		ReceivedPayment receivedPayment = receivedPaymentDao.getReceivedPayment(id);
		int size = receivedPaymentDao.unVerifyReceivedPayment(receivedPayment);
		if(size == 0) throw new PMException (CommonErrorConstants.e029902);	
		if(StringUtils.isNotEmpty(receivedPayment.getInvoice_id())) {
			invoiceDao.updateInvoiceReceivedPayment(receivedPayment);
			invoiceDao.updateInvoiceIsReceivedPayment(receivedPayment);
		}
				
		/*
		try{
			ThReceivedPayment thReceivedPayment = new ThReceivedPayment();
			BeanUtils.copyProperties(receivedPayment, thReceivedPayment);
			thReceivedPayment.setHis_operation_type(LogConstant.OPERATION_UNCHECK);
			thReceivedPaymentDao.addThReceivedPayment(thReceivedPayment);
		}catch(Exception e){
			e.printStackTrace();
		}	
		*/	
		
		try{
			if(StringUtils.isNotEmpty(receivedPayment.getInvoice_id())){
				Invoice invoice = invoiceDao.getInvoice(receivedPayment.getInvoice_id());
				ThInvoice thInvoice = new ThInvoice();
				BeanUtils.copyProperties(invoice, thInvoice);
				thInvoice.setHis_operation_type(LogConstant.OPERATION_UPDATE);
				thInvoiceDao.addThInvoice(thInvoice);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

		//插入到凭证中
		handleVoucherInterim(receivedPayment , true);
		
	}

	

	@Override
	public ReceivedPayment getReceivedPayment(String receivedPayment_id) {
		return receivedPaymentDao.getReceivedPayment(receivedPayment_id);
	}

	@Override
	public Pager<ReceivedPayment> queryReceivedPayment(ReceivedPayment receivedPayment,
			UserPermit userPermit, Pager<ReceivedPayment> pager) {
		return receivedPaymentDao.queryReceivedPayment(receivedPayment, userPermit, pager);
	}


	
}
