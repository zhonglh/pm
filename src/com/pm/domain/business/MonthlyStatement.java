package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.pm.util.log.EntityAnnotation;

/**
 * 月度结算单
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class MonthlyStatement implements Serializable  {
	
	private String monthly_statement_id;
	private String project_id	;
	

	@EntityAnnotation(item_name="结算单月份",item_sort=5)
	private int statement_month;
	

	@EntityAnnotation(item_name="结算单类型",item_sort=4)
	private String statement_type	;

	@EntityAnnotation(item_name="结算单总金额",item_sort=6)
	private double amount		;
	
	@EntityAnnotation(item_name="备注")
	private String description	;
	
	private Timestamp build_datetime;
	private String build_username	;
	private String build_userid	;
	private String verify_username;
	private String verify_userid	;
	private Timestamp verify_datetime;
	
	/////////////////////////////////////////////
	//////////////////扩展///////////////////////
	////////////////////////////////////////////	

	@EntityAnnotation(item_name="项目编号",item_sort=2)
	private String project_no;

	@EntityAnnotation(item_name="项目名称" ,item_sort=1)
	private String project_name;
	
	//项目类型
	@EntityAnnotation(item_name="项目类型" ,item_sort=3)
	private String project_type;
	

	@EntityAnnotation(item_name="发票总金额" ,item_sort=7)
	private double invoice_amount;
	

	@EntityAnnotation(item_name="到款总金额" ,item_sort=8)
	private double receive_amount;
	
	
	
	

	@EntityAnnotation(item_name="未开发票金额" ,item_sort=9)
	private double not_open_invoice_amount;
	
	@EntityAnnotation(item_name="结算单未回款金额" ,item_sort=10)
	private double not_receive_amount;
	
	@EntityAnnotation(item_name="开发票未回款金额" ,item_sort=11)
	private double invoice_not_receive_amount;
	
	


	//项目所属的销售负责人ID,  用于查询
	private String sales_userid;
	
	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	
	
	private String dept_id;
	private String dept_name;
	private int statement_month1;
	private int statement_month2;
	
	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	
	private List<MonthlyStatementDetail> details;
	
	
	public String getMonthly_statement_id() {
		return monthly_statement_id;
	}


	public void setMonthly_statement_id(String monthly_statement_id) {
		this.monthly_statement_id = monthly_statement_id;
	}


	public String getProject_id() {
		return project_id;
	}


	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}


	public int getStatement_month() {
		return statement_month;
	}


	public void setStatement_month(int statement_month) {
		this.statement_month = statement_month;
	}


	public String getStatement_type() {
		return statement_type;
	}


	public void setStatement_type(String statement_type) {
		this.statement_type = statement_type;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
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


	public String getProject_no() {
		return project_no;
	}


	public void setProject_no(String project_no) {
		this.project_no = project_no;
	}


	public String getProject_name() {
		return project_name;
	}


	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}



	public String getVerify_flag() {
		return verify_flag;
	}


	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}


	public String getProject_type() {
		return project_type;
	}


	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}


	public List<MonthlyStatementDetail> getDetails() {
		return details;
	}


	public void setDetails(List<MonthlyStatementDetail> details) {
		this.details = details;
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


	public double getReceive_amount() {
		return receive_amount;
	}


	public void setReceive_amount(double receive_amount) {
		this.receive_amount = receive_amount;
	}


	public String getSales_userid() {
		return sales_userid;
	}


	public void setSales_userid(String sales_userid) {
		this.sales_userid = sales_userid;
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


	public double getNot_open_invoice_amount() {
		return not_open_invoice_amount;
	}


	public void setNot_open_invoice_amount(double not_open_invoice_amount) {
		this.not_open_invoice_amount = not_open_invoice_amount;
	}


	public double getNot_receive_amount() {
		return not_receive_amount;
	}


	public void setNot_receive_amount(double not_receive_amount) {
		this.not_receive_amount = not_receive_amount;
	}


	public double getInvoice_not_receive_amount() {
		return invoice_not_receive_amount;
	}


	public void setInvoice_not_receive_amount(double invoice_not_receive_amount) {
		this.invoice_not_receive_amount = invoice_not_receive_amount;
	}


	
	
	

}
