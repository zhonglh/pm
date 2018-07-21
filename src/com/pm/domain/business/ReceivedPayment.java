package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.PubMethod;
import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivedPayment implements Serializable {


	private String receive_payment_id	;
	private String project_id		;

	
	private String invoice_id;

	@EntityAnnotation(item_name="付款单位" ,item_sort=2,length=150) 
	private String payment_unit;

	@EntityAnnotation(item_name="到款所属月份" ) 
	private int receive_payment_month;

	@EntityAnnotation(item_name="到款金额"  ,item_sort=4,length=15) 
	private double receive_payment_amount;	

	@EntityAnnotation(item_name="到款日期"  ,item_sort=5,length=10) 
	private Timestamp receive_payment_datetime;
	
	

	private String receivable_accounts_item_id;	

	

	@EntityAnnotation(item_name="备注",item_sort=10,length=200) 
	private String description;

	private Timestamp build_datetime;	
	private String build_username;	
	private String build_userid;	
	private String verify_username;	
	private String verify_userid;	
	private Timestamp verify_datetime;	

	//////////////////////////////////////////////////
	////////////////////扩展//////////////////////////
	//////////////////////////////////////////////////
	
	

	@EntityAnnotation(item_name="项目名称" ,item_sort=1,length=100) 
	private String project_name;
	
	@EntityAnnotation(item_name="项目编号")
	private String project_no;	

	@EntityAnnotation(item_name="对应发票号" ,item_sort=7,length=100) 
	private String invoice_no;	
	

	@EntityAnnotation(item_name="发票金额" ,item_sort=8) 
	private double invoice_amount;	
	

	private String contract_id;
	@EntityAnnotation(item_name="合同编号" ,item_sort=9) 
	private String contract_no;

	@EntityAnnotation(item_name="收款方式" ,item_sort=6,length=100) 
	private String receivable_accounts_item_name;		
	

	@EntityAnnotation(item_name="到款所属月份"  ,item_sort=3,length=10) 
	private String str_month;
	
	//@EntityAnnotation(item_name="是否到款")
	//0:未到款， 1：已到款， 2：部分到款
	private String is_received_payment;
	
	private String errorInfo = "";
	

	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	

	//查询时间段
	private Timestamp date1;	
	private Timestamp date2;
	

	private int receive_payment_month1;
	private int receive_payment_month2;

	private String dept_id;
	private String dept_name;


	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	

	public String getReceive_payment_id() {
		return receive_payment_id;
	}

	public void setReceive_payment_id(String receive_payment_id) {
		this.receive_payment_id = receive_payment_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getPayment_unit() {
		return payment_unit;
	}

	public void setPayment_unit(String payment_unit) {
		this.payment_unit = payment_unit;
	}

	public int getReceive_payment_month() {
		return receive_payment_month;
	}

	public void setReceive_payment_month(int receive_payment_month) {
		this.receive_payment_month = receive_payment_month;
	}

	public double getReceive_payment_amount() {
		return receive_payment_amount;
	}

	public void setReceive_payment_amount(double receive_payment_amount) {
		this.receive_payment_amount = receive_payment_amount;
	}

	public Timestamp getReceive_payment_datetime() {
		return receive_payment_datetime;
	}

	public void setReceive_payment_datetime(Timestamp receive_payment_datetime) {
		this.receive_payment_datetime = receive_payment_datetime;
	}

	public Timestamp getBuild_datetime() {
		return build_datetime;
	}

	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
	}

	public String getBuild_username() {
		return build_username;
	}

	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}

	public String getBuild_userid() {
		return build_userid;
	}

	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}

	public String getVerify_username() {
		return verify_username;
	}

	public void setVerify_username(String verify_username) {
		this.verify_username = verify_username;
	}

	public String getVerify_userid() {
		return verify_userid;
	}

	public void setVerify_userid(String verify_userid) {
		this.verify_userid = verify_userid;
	}

	public Timestamp getVerify_datetime() {
		return verify_datetime;
	}

	public void setVerify_datetime(Timestamp verify_datetime) {
		this.verify_datetime = verify_datetime;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_no() {
		return project_no;
	}

	public void setProject_no(String project_no) {
		this.project_no = project_no;
	}

	public String getVerify_flag() {
		return verify_flag;
	}

	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getReceivable_accounts_item_id() {
		return receivable_accounts_item_id;
	}

	public void setReceivable_accounts_item_id(String receivable_accounts_item_id) {
		this.receivable_accounts_item_id = receivable_accounts_item_id;
	}

	public String getReceivable_accounts_item_name() {
		return receivable_accounts_item_name;
	}

	public void setReceivable_accounts_item_name(
			String receivable_accounts_item_name) {
		this.receivable_accounts_item_name = receivable_accounts_item_name;
	}

	public String getStr_month() {
		if(str_month == null && receive_payment_month != 0) return String.valueOf(receive_payment_month);
		else return PubMethod.fromatExcelDecimal(str_month);
	}

	public void setStr_month(String str_month) {
		this.str_month = str_month;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getNeed_approve() {
		return need_approve;
	}

	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}

	public double getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(double invoice_amount) {
		this.invoice_amount = invoice_amount;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getIs_received_payment() {
		return is_received_payment;
	}

	public void setIs_received_payment(String is_received_payment) {
		this.is_received_payment = is_received_payment;
	}

	public int getReceive_payment_month1() {
		return receive_payment_month1;
	}

	public void setReceive_payment_month1(int receive_payment_month1) {
		this.receive_payment_month1 = receive_payment_month1;
	}

	public int getReceive_payment_month2() {
		return receive_payment_month2;
	}

	public void setReceive_payment_month2(int receive_payment_month2) {
		this.receive_payment_month2 = receive_payment_month2;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Timestamp getDate1() {
		return date1;
	}

	public void setDate1(Timestamp date1) {
		this.date1 = date1;
	}

	public Timestamp getDate2() {
		return date2;
	}

	public void setDate2(Timestamp date2) {
		this.date2 = date2;
	}



}
