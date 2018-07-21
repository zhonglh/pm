package com.pm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.dao.IInvoiceDao;
import com.pm.dao.IParamsDao;
import com.pm.dao.IProjectDao;
import com.pm.dao.IReceivedPaymentDao;
import com.pm.dao.IThInvoiceDao;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.Params;
import com.pm.domain.business.Project;
import com.pm.domain.business.ThInvoice;
import com.pm.domain.business.VoucherInterim;
import com.pm.domain.system.User;
import com.pm.service.IInvoiceService;
import com.pm.service.IVoucherInterimService;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.EnumDataType;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;

@Component
public class InvoiceServiceImpl implements IInvoiceService {

	

	@Autowired private IThInvoiceDao thInvoiceDao;
	
	@Autowired private IInvoiceDao invoiceDao;
	@Autowired private IReceivedPaymentDao receivedPaymentDao;
	@Autowired private IProjectDao projectDao;
	@Autowired private IParamsDao paramsDao;
	
	@Autowired private IVoucherInterimService voucherInterimService;

	@Override
	public int addInvoice(Invoice invoice) {
		int size = invoiceDao.addInvoice(invoice);
		
		try{
			ThInvoice thInvoice = new ThInvoice();
			BeanUtils.copyProperties(invoice, thInvoice);
			thInvoice.setHis_operation_type(LogConstant.OPERATION_INSERT);
			thInvoiceDao.addThInvoice(thInvoice);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return size;
	}

	@Override
	public int updateInvoice(Invoice invoice) {
		int size =  invoiceDao.updateInvoice(invoice);

		try{
			ThInvoice thInvoice = new ThInvoice();
			BeanUtils.copyProperties(invoice, thInvoice);
			thInvoice.setHis_operation_type(LogConstant.OPERATION_UPDATE);
			thInvoiceDao.addThInvoice(thInvoice);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return size;
	}

	@Override
	public void deleteInvoice(Invoice[] invoices) {
		for(Invoice invoice : invoices){
			invoiceDao.deleteInvoice(invoice);
			receivedPaymentDao.deleteInvoice(invoice);
		}

	}

	@Override
	public void verifyInvoice(Invoice invoice) {
		int size = invoiceDao.verifyInvoice(invoice);
		if(size == 0) throw new PMException (CommonErrorConstants.e029901);
		
		/*
		try{
			Invoice invoice1 = this.getInvoice(invoice.getInvoice_id());
			ThInvoice thInvoice = new ThInvoice();
			BeanUtils.copyProperties(invoice1, thInvoice);
			thInvoice.setHis_operation_type(LogConstant.OPERATION_CHECK);
			thInvoiceDao.addThInvoice(thInvoice);
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		
		handleVoucherInterim(invoice , false);
	}
	
	
	

	/**
	 * 将发票数据插入到凭证信息表中
	 * @param invoice	 
	 * @param isnegative		是否为负数, 撤销复核为负数，  复核为正数
	 */
	private void handleVoucherInterim(Invoice invoice , boolean isnegative) {
		User user = ThreadLocalUser.getUser();
		Params params = new Params();
		double rate = 0.06 ;
		params.setParam_key("invoice.default.rate");
		List<Params> paramList = paramsDao.queryAllParams(params);
		try{
		if(paramList != null && paramList.size() > 0){
			rate = Double.parseDouble(paramList.get(0).getParam_value());
		}
		}catch(Exception e){}
		Project project = projectDao.getProject(invoice.getProject_id());
		Date date = invoice.getInvoice_date();
		
		VoucherInterim voucherInterim1 = new VoucherInterim(); 
		voucherInterim1.setId(IDKit.generateId());
		voucherInterim1.setData_id(invoice.getInvoice_id());
		voucherInterim1.setData_type(EnumDataType.invoice.getId());
		voucherInterim1.setVoucher_date(date);
		voucherInterim1.setTotal_money(invoice.getInvoiceno_amount());
		voucherInterim1.setExported("0");		
		voucherInterim1.setMonths(DateKit.fmtDateToStr(date,"yyyyMM"));
		voucherInterim1.setYears(DateKit.fmtDateToStr(date,"yyyy"));
		voucherInterim1.setBuild_time(DateKit.getLastDayOfMonth(date));
		voucherInterim1.setVoucher_type("记");
		voucherInterim1.setBuild_username(user.getUser_name());
		//voucherInterim1.setDescription1("一张发票的导入");
		//voucherInterim1.setDescription2("借方：应收账款-已开票");
		voucherInterim1.setCourse_code("1122001");
		voucherInterim1.setSummary(project.getProject_client_name()+"技术服务费收入");
		voucherInterim1.setCurrency_name("人民币");
		voucherInterim1.setDebit_amount(invoice.getInvoiceno_amount());
		voucherInterim1.setClient_code(project.getProject_client_no	());
		
		
		VoucherInterim voucherInterim2 = new VoucherInterim();
		BeanUtils.copyProperties(voucherInterim1, voucherInterim2);
		voucherInterim2.setId(IDKit.generateId());
		//voucherInterim2.setDescription2("贷方：应收账款-未开票");
		voucherInterim2.setCourse_code("1122002");
		voucherInterim2.setDebit_amount(0);
		voucherInterim2.setLoan_amount(invoice.getInvoiceno_amount());
		
		
		

		VoucherInterim voucherInterim3 = new VoucherInterim();
		BeanUtils.copyProperties(voucherInterim2, voucherInterim3);
		double amount = voucherInterim3.getTotal_money()/(1+rate)*rate;
		amount = PubMethod.getNumberFormatByDouble(amount);
		voucherInterim3.setId(IDKit.generateId());
		voucherInterim3.setTotal_money(amount);
		//voucherInterim3.setDescription2("借方：应交税金-应交税费");
		voucherInterim3.setCourse_code("2221001001");
		voucherInterim3.setLoan_amount(amount);
		


		VoucherInterim voucherInterim4 = new VoucherInterim();
		BeanUtils.copyProperties(voucherInterim3, voucherInterim4);		
		voucherInterim4.setId(IDKit.generateId());
		//voucherInterim4.setDescription2("贷方：应交税金-待转销项税");
		voucherInterim4.setCourse_code("2221001005");
		voucherInterim4.setLoan_amount(0);
		voucherInterim4.setDebit_amount(amount);
		
		if(isnegative){
			if(voucherInterim1.getTotal_money()!=0) voucherInterim1.setTotal_money(voucherInterim1.getTotal_money()*-1);
			if(voucherInterim1.getDebit_amount()!=0) voucherInterim1.setDebit_amount(voucherInterim1.getDebit_amount()*-1);
			if(voucherInterim1.getLoan_amount()!=0) voucherInterim1.setLoan_amount(voucherInterim1.getLoan_amount()*-1);
			
			if(voucherInterim2.getTotal_money()!=0) voucherInterim2.setTotal_money(voucherInterim2.getTotal_money()*-1);
			if(voucherInterim2.getDebit_amount()!=0) voucherInterim2.setDebit_amount(voucherInterim2.getDebit_amount()*-1);
			if(voucherInterim2.getLoan_amount()!=0) voucherInterim2.setLoan_amount(voucherInterim2.getLoan_amount()*-1);
		

			if(voucherInterim3.getTotal_money()!=0) voucherInterim3.setTotal_money(voucherInterim3.getTotal_money()*-1);
			if(voucherInterim3.getDebit_amount()!=0) voucherInterim3.setDebit_amount(voucherInterim3.getDebit_amount()*-1);
			if(voucherInterim3.getLoan_amount()!=0) voucherInterim3.setLoan_amount(voucherInterim3.getLoan_amount()*-1);

			if(voucherInterim4.getTotal_money()!=0) voucherInterim4.setTotal_money(voucherInterim4.getTotal_money()*-1);
			if(voucherInterim4.getDebit_amount()!=0) voucherInterim4.setDebit_amount(voucherInterim4.getDebit_amount()*-1);
			if(voucherInterim4.getLoan_amount()!=0) voucherInterim4.setLoan_amount(voucherInterim4.getLoan_amount()*-1);
		
		}
		
		voucherInterimService.batchHandleVoucherInterim(new VoucherInterim[]{voucherInterim1 , voucherInterim2, voucherInterim3 ,voucherInterim4});
	}
	
	
	
	
	
	

	@Override
	public void unVerify(String id) {
		Invoice invoice = this.getInvoice(id);
		int size = invoiceDao.unVerifyInvoice(invoice);
		if(size == 0) throw new PMException (CommonErrorConstants.e029902);
		
		/*
		try{
			Invoice invoice1 = this.getInvoice(invoice.getInvoice_id());
			ThInvoice thInvoice = new ThInvoice();
			BeanUtils.copyProperties(invoice1, thInvoice);
			thInvoice.setHis_operation_type(LogConstant.OPERATION_UNCHECK);
			thInvoiceDao.addThInvoice(thInvoice);
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		
		handleVoucherInterim(invoice , true);
		
	}

	@Override
	public Invoice getInvoice(String invoice_id) {
		return invoiceDao.getInvoice(invoice_id);
	}
	
	public List<Invoice> getInvoiceByMonthly(String monthly_statement_id){
		return invoiceDao.getInvoiceByMonthly(monthly_statement_id);
	}

	@Override
	public Pager<Invoice> queryInvoice(Invoice invoice,
			UserPermit userPermit, Pager<Invoice> pager) {
		return invoiceDao.queryInvoice(invoice, userPermit, pager);
	}


}
