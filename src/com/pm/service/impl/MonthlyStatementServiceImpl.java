package com.pm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.pm.dao.IMonthlyStatementDao;
import com.pm.dao.IParamsDao;
import com.pm.dao.IProjectDao;
import com.pm.dao.IThInvoiceDao;
import com.pm.dao.IThMonthlyStatementDao;
import com.pm.dao.IThMonthlyStatementDetailDao;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.MonthlyStatementDetail;
import com.pm.domain.business.Params;
import com.pm.domain.business.Project;
import com.pm.domain.business.ThInvoice;
import com.pm.domain.business.ThMonthlyStatement;
import com.pm.domain.business.ThMonthlyStatementDetail;
import com.pm.domain.business.VoucherInterim;
import com.pm.domain.system.User;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IVoucherInterimService;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumDataType;
import com.pm.util.constant.EnumProjectType;
import com.pm.util.constant.LogConstant;
import com.pm.vo.UserPermit;


@Component
public class MonthlyStatementServiceImpl implements IMonthlyStatementService {

	@Autowired  IThMonthlyStatementDao thMonthlyStatementDao;
	@Autowired  IThMonthlyStatementDetailDao thMonthlyStatementDetailDao;
	@Autowired  IMonthlyStatementDao monthlyStatementDao;

	@Autowired  IThInvoiceDao thInvoiceDao;
	@Autowired  IInvoiceDao invoiceDao;
	@Autowired private IProjectDao projectDao;
	@Autowired private IParamsDao paramsDao;
	
	@Autowired private IVoucherInterimService voucherInterimService;
	

	@Override
	public MonthlyStatement autoAddMonthlyStatement(String project_id, int use_month, User sessionUser){
		MonthlyStatement monthlyStatement = new MonthlyStatement();
		List<MonthlyStatementDetail> monthlyStatementDetails = null;		

		monthlyStatement.setProject_id(project_id);
		monthlyStatement.setStatement_month(use_month);
		monthlyStatement.setMonthly_statement_id(IDKit.generateId());
		monthlyStatement.setStatement_type(BusinessUtil.STATEMENT_TYPE_2);
		monthlyStatement.setAmount(0);
		monthlyStatement.setBuild_datetime(PubMethod.getCurrentDate());
		monthlyStatement.setBuild_userid(sessionUser.getUser_id());
		monthlyStatement.setBuild_username(sessionUser.getUser_name());	
		
		double amount = 0;
		
		monthlyStatementDetails = computeMonthlyStatementDetail(monthlyStatement);
		for(MonthlyStatementDetail monthlyStatementDetail : monthlyStatementDetails){
			monthlyStatementDetail.setProject_id(monthlyStatement.getProject_id());
			monthlyStatementDetail.setMonthly_statement_id(monthlyStatement.getMonthly_statement_id());
			monthlyStatementDetail.setMonthly_statement_detail_id(IDKit.generateId());
			monthlyStatementDetail.setBuild_datetime(PubMethod.getCurrentDate());
			monthlyStatementDetail.setBuild_userid(sessionUser.getUser_id());
			monthlyStatementDetail.setBuild_username(sessionUser.getUser_name());	
			amount += monthlyStatementDetail.getTotal_cost();
		}

		monthlyStatement.setAmount(amount);
		
		monthlyStatement.setDetails(monthlyStatementDetails);
		
		return monthlyStatement;
		
		
	}

	@Override
	public void addMonthlyStatement(MonthlyStatement monthlyStatement,List<MonthlyStatementDetail> monthlyStatementDetails) {
		monthlyStatementDao.addMonthlyStatement(monthlyStatement);
		insertMonthlyStatementDetail(monthlyStatement, monthlyStatementDetails);
	}

	private void insertMonthlyStatementDetail(MonthlyStatement monthlyStatement, List<MonthlyStatementDetail> monthlyStatementDetails) {
		if(monthlyStatementDetails != null){
			for(MonthlyStatementDetail monthlyStatementDetail :monthlyStatementDetails) {
				monthlyStatementDao.addMonthlyStatementDetail(monthlyStatementDetail);
			}
		}

		try{
			ThMonthlyStatement thMonthlyStatement = new ThMonthlyStatement();
			BeanUtils.copyProperties(monthlyStatement, thMonthlyStatement);
			thMonthlyStatement.setHis_operation_type(LogConstant.OPERATION_INSERT);
			thMonthlyStatementDao.addThMonthlyStatement(thMonthlyStatement);

			if(monthlyStatementDetails != null){
				for(MonthlyStatementDetail monthlyStatementDetail :monthlyStatementDetails){
					ThMonthlyStatementDetail thMonthlyStatementDetail = new ThMonthlyStatementDetail();
					BeanUtils.copyProperties(monthlyStatementDetail, thMonthlyStatementDetail);
					thMonthlyStatementDetail.setMonthly_statement_his_id(thMonthlyStatement.getId());
					thMonthlyStatementDetailDao.addThMonthlyStatementDetail(thMonthlyStatementDetail);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateMonthlyStatement(MonthlyStatement monthlyStatement,List<MonthlyStatementDetail> monthlyStatementDetails) {

		monthlyStatementDao.updateMonthlyStatement(monthlyStatement);

		List<MonthlyStatementDetail> insertList = new ArrayList<MonthlyStatementDetail>();
		List<MonthlyStatementDetail> updateList = new ArrayList<MonthlyStatementDetail>();



		if(monthlyStatementDetails != null){
			for (MonthlyStatementDetail monthlyStatementDetail : monthlyStatementDetails) {
				if(StringUtils.isNotEmpty(monthlyStatementDetail.getMonthly_statement_detail_id())){
					updateList.add(monthlyStatementDetail);
				}else {
					insertList.add(monthlyStatementDetail);
				}
			}
		}

		if(!insertList.isEmpty()){
			insertMonthlyStatementDetail(monthlyStatement,insertList);
		}


		if(!updateList.isEmpty()) {
			updateMonthlyStatementDetail(monthlyStatement, updateList);
		}
	}

	private void updateMonthlyStatementDetail(MonthlyStatement monthlyStatement, List<MonthlyStatementDetail> updateList) {

		for (MonthlyStatementDetail monthlyStatementDetail : updateList) {
			monthlyStatementDao.updateMonthlyStatementDetail(monthlyStatementDetail);
		}

		try{

			ThMonthlyStatement thMonthlyStatement = new ThMonthlyStatement();
			BeanUtils.copyProperties(monthlyStatement, thMonthlyStatement);
			thMonthlyStatement.setHis_operation_type(LogConstant.OPERATION_UPDATE);
			thMonthlyStatementDao.addThMonthlyStatement(thMonthlyStatement);

			for(MonthlyStatementDetail monthlyStatementDetail :updateList){
				ThMonthlyStatementDetail thMonthlyStatementDetail = new ThMonthlyStatementDetail();
				BeanUtils.copyProperties(monthlyStatementDetail, thMonthlyStatementDetail);
				thMonthlyStatementDetail.setMonthly_statement_his_id(thMonthlyStatement.getId());
				thMonthlyStatementDetailDao.addThMonthlyStatementDetail(thMonthlyStatementDetail);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMonthlyStatement(MonthlyStatement[] monthlyStatements) {
		List<Invoice> list = new ArrayList<Invoice>();
		
		for(MonthlyStatement monthlyStatement : monthlyStatements){
			monthlyStatementDao.deleteMonthlyStatementDetail(monthlyStatement);
			monthlyStatementDao.deleteMonthlyStatement(monthlyStatement);
			
			List<Invoice>  temps = invoiceDao.getInvoiceByMonthly(monthlyStatement.getMonthly_statement_id());
			if(temps != null && temps.size() > 0) {
				list.addAll(temps);
			}
			
			invoiceDao.deleteMonthlyStatement(monthlyStatement.getMonthly_statement_id());			
			
		}
		
		//增加发票历史记录
		try{
			for(Invoice invoice : list){
				ThInvoice thInvoice = new ThInvoice();
				BeanUtils.copyProperties(invoice, thInvoice);
				thInvoice.setMonthly_statement_id("");
				thInvoice.setMonthly_statement_name("");
				thInvoice.setStatement_type("");
				thInvoice.setHis_operation_type(LogConstant.OPERATION_UPDATE);
				thInvoiceDao.addThInvoice(thInvoice);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void verifyMonthlyStatement(MonthlyStatement monthlyStatement) {
		int size = monthlyStatementDao.verifyMonthlyStatement(monthlyStatement);
		if(size == 0) {
			throw new PMException (CommonErrorConstants.e029901);
		}
		
		/**
		try{
			ThMonthlyStatement thMonthlyStatement = new ThMonthlyStatement();
			monthlyStatement = this.getMonthlyStatement(monthlyStatement);
			BeanUtils.copyProperties(monthlyStatement, thMonthlyStatement);
			thMonthlyStatement.setHis_operation_type(LogConstant.OPERATION_CHECK);
			thMonthlyStatementDao.addThMonthlyStatement(thMonthlyStatement);
			
			List<MonthlyStatementDetail> monthlyStatementDetails = this.getMonthlyStatementDetail(monthlyStatement);			
			if(monthlyStatementDetails != null){
				for(MonthlyStatementDetail monthlyStatementDetail :monthlyStatementDetails){
					ThMonthlyStatementDetail thMonthlyStatementDetail = new ThMonthlyStatementDetail();
					BeanUtils.copyProperties(monthlyStatementDetail, thMonthlyStatementDetail);
					thMonthlyStatementDetail.setMonthlyStatementHisId(thMonthlyStatement.getId());
					thMonthlyStatementDetailDao.addThMonthlyStatementDetail(thMonthlyStatementDetail);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		
		handleVoucherInterim(monthlyStatement , false);
		
	}
	
	

	/**
	 * 将月度结算单数据插入到凭证信息表中
	 * @param monthlyStatement	月度结算单
	 * @param isnegative		是否为负数, 撤销复核为负数，  复核为正数
	 */
	private void handleVoucherInterim(MonthlyStatement monthlyStatement , boolean isnegative) {
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
		Project project = projectDao.getProject(monthlyStatement.getProject_id());
		Date date = DateKit.fmtStrToDate( String.valueOf(monthlyStatement.getStatement_month()) + "01" , "yyyyMMdd");
		
		VoucherInterim voucherInterim1 = new VoucherInterim(); 
		voucherInterim1.setId(IDKit.generateId());
		voucherInterim1.setData_id(monthlyStatement.getMonthly_statement_id());
		voucherInterim1.setData_type(EnumDataType.monthly_statement.getId());
		voucherInterim1.setVoucher_date(date);
		voucherInterim1.setTotal_money(monthlyStatement.getAmount());
		voucherInterim1.setExported("0");		
		
		
		voucherInterim1.setMonths(DateKit.fmtDateToStr(date,"yyyyMM"));
		voucherInterim1.setYears(DateKit.fmtDateToStr(date,"yyyy"));
		voucherInterim1.setBuild_time(DateKit.getLastDayOfMonth(date));
		voucherInterim1.setVoucher_type("记");
		voucherInterim1.setBuild_username(user.getUser_name());
		//voucherInterim1.setDescription1("一个已核单的结算单导入");
		//voucherInterim1.setDescription2("借方：应收账款-未开票");
		voucherInterim1.setCourse_code("1122002");
		voucherInterim1.setSummary(project.getProject_client_name()+"技术服务费收入");
		voucherInterim1.setCurrency_name("人民币");
		voucherInterim1.setDebit_amount(monthlyStatement.getAmount());
		voucherInterim1.setClient_code(project.getProject_client_no	());
		
		
		VoucherInterim voucherInterim2 = new VoucherInterim();
		BeanUtils.copyProperties(voucherInterim1, voucherInterim2);		
		double amount = voucherInterim2.getTotal_money()/(1+rate);
		amount = PubMethod.getNumberFormatByDouble(amount);		
		voucherInterim2.setId(IDKit.generateId());
		//voucherInterim2.setDescription2("贷方：技术服务收入");
		
		if(EnumProjectType.STAFF.getKey().equals(monthlyStatement.getProject_type())){
			voucherInterim2.setCourse_code("600100100101");
		}else {
			voucherInterim2.setCourse_code("600100100201");
		}
		  	
		voucherInterim2.setDebit_amount(0);
		voucherInterim2.setLoan_amount(amount	);
		voucherInterim2.setTotal_money(amount	);
		
		VoucherInterim voucherInterim3 = new VoucherInterim();
		BeanUtils.copyProperties(voucherInterim2, voucherInterim3);	
		voucherInterim3.setId(IDKit.generateId());
		//voucherInterim3.setDescription2("贷方：应交税金-待转销项税");
		voucherInterim3.setCourse_code("2221001005");
		voucherInterim3.setLoan_amount(PubMethod.getNumberFormatByDouble(voucherInterim1.getTotal_money() - voucherInterim2.getTotal_money())	);
		voucherInterim3.setTotal_money(voucherInterim3.getLoan_amount()	);
		
		
		voucherInterim2.setDepart_code("13");
		voucherInterim2.setProject_class_code("13");
		voucherInterim2.setProject_code("13");
		
		if(isnegative){
			if(voucherInterim1.getTotal_money()!=0) {
				voucherInterim1.setTotal_money(voucherInterim1.getTotal_money()*-1);
			}
			if(voucherInterim1.getDebit_amount()!=0) {
				voucherInterim1.setDebit_amount(voucherInterim1.getDebit_amount()*-1);
			}
			if(voucherInterim1.getLoan_amount()!=0) {
				voucherInterim1.setLoan_amount(voucherInterim1.getLoan_amount()*-1);
			}


			if(voucherInterim2.getTotal_money()!=0) {
				voucherInterim2.setTotal_money(voucherInterim2.getTotal_money()*-1);
			}
			if(voucherInterim2.getDebit_amount()!=0) {
				voucherInterim2.setDebit_amount(voucherInterim2.getDebit_amount()*-1);
			}
			if(voucherInterim2.getLoan_amount()!=0) {
				voucherInterim2.setLoan_amount(voucherInterim2.getLoan_amount()*-1);
			}

			if(voucherInterim3.getTotal_money()!=0) {
				voucherInterim3.setTotal_money(voucherInterim3.getTotal_money()*-1);
			}
			if(voucherInterim3.getDebit_amount()!=0) {
				voucherInterim3.setDebit_amount(voucherInterim3.getDebit_amount()*-1);
			}
			if(voucherInterim3.getLoan_amount()!=0) {
				voucherInterim3.setLoan_amount(voucherInterim3.getLoan_amount()*-1);
			}

			
			
		}
		
		voucherInterimService.batchHandleVoucherInterim(new VoucherInterim[]{voucherInterim1 , voucherInterim2 , voucherInterim3});
	}
	

	@Override
	public void unVerify(String id) {
		MonthlyStatement monthlyStatement = new MonthlyStatement();
		monthlyStatement.setMonthly_statement_id(id);
		int size = monthlyStatementDao.unVerifyMonthlyStatement(monthlyStatement);		
		if(size == 0) {
			throw new PMException (CommonErrorConstants.e029902);
		}
		
		monthlyStatement = this.monthlyStatementDao.getMonthlyStatement(monthlyStatement);
		
		/*
		try{
			ThMonthlyStatement thMonthlyStatement = new ThMonthlyStatement();
			monthlyStatement = this.getMonthlyStatement(monthlyStatement);
			BeanUtils.copyProperties(monthlyStatement, thMonthlyStatement);
			thMonthlyStatement.setHis_operation_type(LogConstant.OPERATION_UNCHECK);
			thMonthlyStatementDao.addThMonthlyStatement(thMonthlyStatement);
			
			List<MonthlyStatementDetail> monthlyStatementDetails = this.getMonthlyStatementDetail(monthlyStatement);			
			if(monthlyStatementDetails != null){
				for(MonthlyStatementDetail monthlyStatementDetail :monthlyStatementDetails){
					ThMonthlyStatementDetail thMonthlyStatementDetail = new ThMonthlyStatementDetail();
					BeanUtils.copyProperties(monthlyStatementDetail, thMonthlyStatementDetail);
					thMonthlyStatementDetail.setMonthlyStatementHisId(thMonthlyStatement.getId());
					thMonthlyStatementDetailDao.addThMonthlyStatementDetail(thMonthlyStatementDetail);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		

		handleVoucherInterim(monthlyStatement , true);
		
	}

	@Override
	public Pager<MonthlyStatement> queryMonthlyStatement(
			MonthlyStatement monthlyStatement, 
			UserPermit userPermit,
			Pager<MonthlyStatement> pager) {		
		Pager<MonthlyStatement> pager1 = monthlyStatementDao.queryMonthlyStatement(monthlyStatement, userPermit, pager);
		MonthlyStatement monthlyStatement1 = monthlyStatementDao.queryMonthlyStatementSum(monthlyStatement, userPermit);
		pager1.setResultObj(monthlyStatement1);
		return pager1;
	}

	@Override
	public MonthlyStatement getMonthlyStatement(MonthlyStatement monthlyStatement) {
		return monthlyStatementDao.getMonthlyStatement(monthlyStatement);
	}

	@Override
	public boolean isExist(MonthlyStatement monthlyStatement) {

		if(
				BusinessUtil.STATEMENT_TYPE_3.equals(monthlyStatement.getStatement_type()) ||
				BusinessUtil.STATEMENT_TYPE_4.equals(monthlyStatement.getStatement_type())
		)
			return false;
		
		return monthlyStatementDao.isExist(monthlyStatement);
	}

	
	
	
	/**
	
	@Override
	public void addMonthlyStatementDetail(MonthlyStatementDetail[] monthlyStatementDetails) {
		for(MonthlyStatementDetail monthlyStatementDetail :monthlyStatementDetails)
			monthlyStatementDao.addMonthlyStatementDetail(monthlyStatementDetail);
	}

	@Override
	public void updateMonthlyStatementDetail(MonthlyStatementDetail[] monthlyStatementDetails) {
		for(MonthlyStatementDetail monthlyStatementDetail :monthlyStatementDetails)
			monthlyStatementDao.updateMonthlyStatementDetail(monthlyStatementDetail);
	}

	@Override
	public void deleteMonthlyStatementDetail(MonthlyStatement monthlyStatement) {
		monthlyStatementDao.deleteMonthlyStatementDetail(monthlyStatement);
	}
	*/

	@Override
	public List<MonthlyStatementDetail> getMonthlyStatementDetail(MonthlyStatement monthlyStatement) {
		return monthlyStatementDao.getMonthlyStatementDetail(monthlyStatement);
	}

	@Override
	public List<MonthlyStatementDetail> computeMonthlyStatementDetail(MonthlyStatement monthlyStatement) {
		return monthlyStatementDao.computeMonthlyStatementDetail(monthlyStatement);
	}
	

	@Override
	public Pager<MonthlyStatementDetail> queryMonthlyStatementDetail(MonthlyStatement monthlyStatement, UserPermit userPermit,Pager<MonthlyStatementDetail> pager){
		return monthlyStatementDao.queryMonthlyStatementDetail(monthlyStatement, userPermit, pager);
	}

}
