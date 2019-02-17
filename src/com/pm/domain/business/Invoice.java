package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;



@SuppressWarnings("serial")
public class Invoice implements Serializable {
	
	private String invoice_id;	
	
	private String project_id;
	
	@EntityAnnotation(item_name="发票号" ,item_sort=2,length=150) 
	private String invoice_no;	

	@EntityAnnotation(item_name="发票抬头" ,item_sort=3,length=150) 
	private String invoice_title;

	@EntityAnnotation(item_name="发票类型"  ,item_sort=4,length=15) 
	private String invoice_type;	

	//发票内容
	private String invoice_content;	
	

	@EntityAnnotation(item_name="开票日期"  ,item_sort=6,length=10) 
	private Timestamp invoice_date;

	@EntityAnnotation(item_name="是否免税"  ,item_sort=7,length=150) 
	private String is_exemption_tax;

	@EntityAnnotation(item_name="发票金额"  ,item_sort=8,length=15) 
	private double invoiceno_amount;

	@EntityAnnotation(item_name="税率(%)"  ,item_sort=9,length=6) 
	private double tax_rate;	
	

	//@EntityAnnotation(item_name="是否到款")
	//0:未到款， 1：已到款， 2：部分到款
	@EntityAnnotation(item_name="是否到款"  ,item_sort=15)
	private String is_received_payment;
	
	@EntityAnnotation(item_name="到款金额"  ,item_sort=16)
	private double receive_payment_amount;

	@EntityAnnotation(item_name="到款日期"  ,item_sort=17)
	private Timestamp received_payment_datetime;	

	private String invoice_staff_id;
	
	//发票接收人员
	private String invoice_receive;
	
	//月度考勤单ID
	private String monthly_statement_id ;
	
	//合同ID
	private String contract_id;

	@EntityAnnotation(item_name="备注" ,item_sort=14,length=300)
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

	@EntityAnnotation(item_name="项目名称" ,item_sort=1,length=150) 
	private String project_name;
	
	@EntityAnnotation(item_name="项目编号")
	private String project_no;
	

	@EntityAnnotation(item_name="发票内容"  ,item_sort=5,length=150) 
	private String invoice_content_name;	



	@EntityAnnotation(item_name="开票人员" ,item_sort=10,length=50) 
	private String invoice_staff_name;	

	@EntityAnnotation(item_name="发票接收人员" ,item_sort=11,length=50) 
	private String invoice_receive_name;	
	
	
	
	@EntityAnnotation(item_name="合同号" ,item_sort=12,length=50) 
	private String contract_no;	
	

	@EntityAnnotation(item_name="月度结算单" ,item_sort=13)
	private String monthly_statement_name ;
	

	@EntityAnnotation(item_name="月度结算单类型" ) 
	private String statement_type;
	
	//查询条件
	private Timestamp date1;
	private Timestamp date2;

	//销售负责人
	private String sales_username;
	
	//结算单月份
	private int statement_month1;
	private int statement_month2;
	
	private String dept_id;
	private String dept_name;

	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	

	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	

	private String errorInfo = "";

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
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

	public String getInvoice_title() {
		return invoice_title;
	}

	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public String getInvoice_content() {
		return invoice_content;
	}

	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}

	public String getIs_exemption_tax() {
		return is_exemption_tax;
	}

	public void setIs_exemption_tax(String is_exemption_tax) {
		this.is_exemption_tax = is_exemption_tax;
	}

	public double getInvoiceno_amount() {
		return invoiceno_amount;
	}

	public void setInvoiceno_amount(double invoiceno_amount) {
		this.invoiceno_amount = invoiceno_amount;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public String getInvoice_staff_id() {
		return invoice_staff_id;
	}

	public void setInvoice_staff_id(String invoice_staff_id) {
		this.invoice_staff_id = invoice_staff_id;
	}

	public String getInvoice_receive() {
		return invoice_receive;
	}

	public void setInvoice_receive(String invoice_receive) {
		this.invoice_receive = invoice_receive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getInvoice_staff_name() {
		return invoice_staff_name;
	}

	public void setInvoice_staff_name(String invoice_staff_name) {
		this.invoice_staff_name = invoice_staff_name;
	}

	public String getVerify_flag() {
		return verify_flag;
	}

	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}

	public String getIs_received_payment() {
		return is_received_payment;
	}

	public void setIs_received_payment(String is_received_payment) {
		this.is_received_payment = is_received_payment;
	}

	public Timestamp getReceived_payment_datetime() {
		return received_payment_datetime;
	}

	public void setReceived_payment_datetime(Timestamp received_payment_datetime) {
		this.received_payment_datetime = received_payment_datetime;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getMonthly_statement_id() {
		return monthly_statement_id;
	}

	public void setMonthly_statement_id(String monthly_statement_id) {
		this.monthly_statement_id = monthly_statement_id;
	}

	public String getMonthly_statement_name() {
		return monthly_statement_name;
	}

	public void setMonthly_statement_name(String monthly_statement_name) {
		this.monthly_statement_name = monthly_statement_name;
	}

	public String getStatement_type() {
		return statement_type;
	}

	public void setStatement_type(String statement_type) {
		this.statement_type = statement_type;
	}

	public double getReceive_payment_amount() {
		return receive_payment_amount;
	}

	public void setReceive_payment_amount(double receive_payment_amount) {
		this.receive_payment_amount = receive_payment_amount;
	}

	public Timestamp getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Timestamp invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getNeed_approve() {
		return need_approve;
	}

	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
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





	public String getInvoice_receive_name() {
		return invoice_receive_name;
	}

	public void setInvoice_receive_name(String invoice_receive_name) {
		this.invoice_receive_name = invoice_receive_name;
	}


	public String getInvoice_content_name() {
		return invoice_content_name;
	}

	public void setInvoice_content_name(String invoice_content_name) {
		this.invoice_content_name = invoice_content_name;
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

	public int getStatement_month1() {
		return statement_month1;
	}

	public void setStatement_month1(int statement_month1) {
		this.statement_month1 = statement_month1;
	}

	public int getStatement_month2() {
		return statement_month2;
	}

	public void setStatement_month2(int statement_month2) {
		this.statement_month2 = statement_month2;
	}

	public String getSales_username() {
		return sales_username;
	}

	public void setSales_username(String sales_username) {
		this.sales_username = sales_username;
	}
}
