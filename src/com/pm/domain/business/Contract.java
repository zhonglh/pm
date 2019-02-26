package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.pm.util.PubMethod;
import com.pm.util.log.EntityAnnotation;

/**
 * 合同信息
 * @author zhonglihong
 * @date 2016年5月11日 下午5:28:37
 */
@SuppressWarnings("serial")
public class Contract extends IdEntity implements Serializable {
	 
	//合同存放地ID
	private String storage_addr;
	
	private String project_id;

	@EntityAnnotation(item_name="合同编号" ,item_sort=5,length=30) 
	private String contract_no   ;    

	@EntityAnnotation(item_name="执行合同",item_sort=6,length=50) 
	private String exec_contract ;    

	//人月费用
	private Double monthly_expenses ; 
	private Double monthly_expenses2 ; 

	@EntityAnnotation(item_name="合同签订日期" ,item_sort=8,length=10) 
	private Date signing_date ;     

	private Date validity_date1 ;  
	
	private Date validity_date2 ;   

	private String sales_userid ;     

	@EntityAnnotation(item_name="客户联系人"  ,item_sort=11,length=30) 
	private String client_linkman ;

	@EntityAnnotation(item_name="法定代表人"  ,item_sort=12,length=30) 
	private String corporation  ;    

	@EntityAnnotation(item_name="提交日期" ,item_sort=13,length=10) 
	private Date submit_date    ;   

	@EntityAnnotation(item_name="合同份数" ,item_sort=14,length=9) 
	private int contract_number ;


	@EntityAnnotation(item_name="付款方式" ,item_sort=15,length=100)
	private String paymen_mode;

	@EntityAnnotation(item_name="备注" ,item_sort=16,length=150)
	private String description   ;   
	
	private Date build_datetime   ; 
	private String build_userid   ;   
	private String build_username ;
	
	
	///////////////////////////////////////////////
	////////扩展
	///////////////////////////////////////////////
	

	@EntityAnnotation(item_name="合同存放地",item_sort=3,length=100) 
	private String storage_addr_name ;    
	
	@EntityAnnotation(item_name="立项名称" ,item_sort=2,length=150) 
	private String project_name;
	
	@EntityAnnotation(item_name="立项编号" ,item_sort=1) 
	private String project_no;
	
	@EntityAnnotation(item_name="客户名称",item_sort=4) 
	private String project_client_name;

	@EntityAnnotation(item_name="销售负责人" ,item_sort=10,length=20) 
	private String sales_username;

	@EntityAnnotation(item_name="合同有效期" ,item_sort=9,length=23) 
	private String effectivedate;
	
	
	

	@EntityAnnotation(item_name="人月费用" ,item_sort=7,length=10) 
	private String monthly_expenses_str;

	private String errorInfo = "";


	//提醒标志
	private int remind;

	//查询条件
	private Timestamp date1;
	private Timestamp date2;


	@Override
	public String toString() {
		return contract_no==null?"":contract_no ;
	}
	
	
	public String getStorage_addr() {
		return storage_addr;
	}
	public void setStorage_addr(String storage_addr) {
		this.storage_addr = storage_addr;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getExec_contract() {
		return exec_contract;
	}
	public void setExec_contract(String exec_contract) {
		this.exec_contract = exec_contract;
	}
	public Double getMonthly_expenses() {
		return monthly_expenses;
	}
	public void setMonthly_expenses(Double monthly_expenses) {
		this.monthly_expenses = monthly_expenses;
	}
	public Date getSigning_date() {
		return signing_date;
	}
	public void setSigning_date(Date signing_date) {
		this.signing_date = signing_date;
	}
	public Date getValidity_date1() {
		return validity_date1;
	}
	public void setValidity_date1(Date validity_date1) {
		this.validity_date1 = validity_date1;
	}
	public Date getValidity_date2() {
		return validity_date2;
	}
	public void setValidity_date2(Date validity_date2) {
		this.validity_date2 = validity_date2;
	}
	public String getSales_userid() {
		return sales_userid;
	}
	public void setSales_userid(String sales_userid) {
		this.sales_userid = sales_userid;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public Date getSubmit_date() {
		return submit_date;
	}
	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}
	public int getContract_number() {
		return contract_number;
	}
	public void setContract_number(int contract_number) {
		this.contract_number = contract_number;
	}

	public String getPaymen_mode() {
		return paymen_mode;
	}

	public void setPaymen_mode(String paymen_mode) {
		this.paymen_mode = paymen_mode;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Date build_datetime) {
		this.build_datetime = build_datetime;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
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
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getSales_username() {
		return sales_username;
	}
	public void setSales_username(String sales_username) {
		this.sales_username = sales_username;
	}

	public String getProject_client_name() {
		return project_client_name;
	}
	public void setProject_client_name(String project_client_name) {
		this.project_client_name = project_client_name;
	}
	public String getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getClient_linkman() {
		return client_linkman;
	}
	public void setClient_linkman(String client_linkman) {
		this.client_linkman = client_linkman;
	}
	public String getStorage_addr_name() {
		return storage_addr_name;
	}
	public void setStorage_addr_name(String storage_addr_name) {
		this.storage_addr_name = storage_addr_name;
	}
	public Double getMonthly_expenses2() {
		return monthly_expenses2;
	}
	public void setMonthly_expenses2(Double monthly_expenses2) {
		this.monthly_expenses2 = monthly_expenses2;
	}
	public String getMonthly_expenses_str() {
		if(monthly_expenses_str == null){
			monthly_expenses_str = PubMethod.twoNumber2Str(this.getMonthly_expenses(), this.getMonthly_expenses2());
		}
		return monthly_expenses_str;
	}
	public void setMonthly_expenses_str(String monthly_expenses_str) {
		this.monthly_expenses_str = monthly_expenses_str;
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

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}
}
