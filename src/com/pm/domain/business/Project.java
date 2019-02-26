package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;


/**
 * 项目基础信息
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class Project implements Serializable {

	private String project_id;
	
	@EntityAnnotation(item_name="项目名称",item_sort=1)
	private String project_name;

	@EntityAnnotation(item_name="项目编号" ,item_sort=2)
	private String project_no;
	private String project_client_id;	

	@EntityAnnotation(item_name="项目联系人名称" , item_sort=12)
	private String project_contacts_name;

	@EntityAnnotation(item_name="项目联系人电话")
	private String project_contacts_tel;
	private String recruitment_userid;
	private String sales_userid;
	private String info_sources_userid;
	private String manage_userid;
	private String sales_before_userid;
	private String sales_after_userid;	
	private String sales_amount_deptid;	
	private String exec_amount_deptid;	
	private String deptid;	


	@EntityAnnotation(item_name="项目类型" ,item_sort=7)
	private String project_type;		
	

	@EntityAnnotation(item_name="项目金额" ,item_sort=3)
	private double project_amount;	
	
	@EntityAnnotation(item_name="预估成本", item_sort=10)
	private double estimate_costs	;	

	@EntityAnnotation(item_name="开始时间")
	private Timestamp start_date;		
	private String sign_contract_flag;		

	@EntityAnnotation(item_name="合同编号", item_sort=11)
	private String contract_no;	
	
	
	//用于处理 月度结算单 处理取消核单申请
	//批复人名称
	private String approve_user_id;

	@EntityAnnotation(item_name="描述")
	private String description;	
	private Timestamp build_datetime;	
	private String build_userid;	
	private String build_username;	
	private String delete_flag;	
	private Timestamp delete_datetime;	
	private String delete_userid;
	private String delete_username;
	
	
	
	///////////////////////////////////////
	/////////////////扩展//////////////////
	//////////////////////////////////////

	@EntityAnnotation(item_name="客户名称" ,item_sort=4)
	private String project_client_name;
	
	//客户编号
	private String project_client_no;
	
	@EntityAnnotation(item_name="招聘专员")
	private String recruitment_username;

	@EntityAnnotation(item_name="销售负责人" ,item_sort=5)
	private String sales_username;
	
	@EntityAnnotation(item_name="信息来源"  ,item_sort=8)
	private String info_sources_username;

	@EntityAnnotation(item_name="项目管理人"  ,item_sort=6)
	private String manage_username;

	@EntityAnnotation(item_name="售前支持" , item_sort=13)
	private String sales_before_username;
	
	@EntityAnnotation(item_name="售后支持", item_sort=14)
	private String sales_after_username;	

	@EntityAnnotation(item_name="销售额归属" , item_sort=15)
	private String sales_amount_deptname;	
	
	@EntityAnnotation(item_name="执行额归属" , item_sort=16)
	private String exec_amount_deptname;
	

	@EntityAnnotation(item_name="所属部门", item_sort=9)
	private String deptname;	
	
	@EntityAnnotation(item_name="批复人")
	private String approve_user_name;

	//查询条件  1:有 0:无
	private String haveContract ;
	
	/**
	 * 项目已用成本
	 */
	private double amount;
	
	ProjectStaff[] projectStaffs ;
		

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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

	public String getProject_client_id() {
		return project_client_id;
	}

	public void setProject_client_id(String project_client_id) {
		this.project_client_id = project_client_id;
	}

	public String getProject_contacts_name() {
		return project_contacts_name;
	}

	public void setProject_contacts_name(String project_contacts_name) {
		this.project_contacts_name = project_contacts_name;
	}

	public String getProject_contacts_tel() {
		return project_contacts_tel;
	}

	public void setProject_contacts_tel(String project_contacts_tel) {
		this.project_contacts_tel = project_contacts_tel;
	}

	public String getRecruitment_userid() {
		return recruitment_userid;
	}

	public void setRecruitment_userid(String recruitment_userid) {
		this.recruitment_userid = recruitment_userid;
	}

	public String getSales_userid() {
		return sales_userid;
	}

	public void setSales_userid(String sales_userid) {
		this.sales_userid = sales_userid;
	}

	public String getInfo_sources_userid() {
		return info_sources_userid;
	}

	public void setInfo_sources_userid(String info_sources_userid) {
		this.info_sources_userid = info_sources_userid;
	}

	public String getManage_userid() {
		return manage_userid;
	}

	public void setManage_userid(String manage_userid) {
		this.manage_userid = manage_userid;
	}

	public String getSales_before_userid() {
		return sales_before_userid;
	}

	public void setSales_before_userid(String sales_before_userid) {
		this.sales_before_userid = sales_before_userid;
	}

	public String getSales_after_userid() {
		return sales_after_userid;
	}

	public void setSales_after_userid(String sales_after_userid) {
		this.sales_after_userid = sales_after_userid;
	}

	public String getSales_amount_deptid() {
		return sales_amount_deptid;
	}

	public void setSales_amount_deptid(String sales_amount_deptid) {
		this.sales_amount_deptid = sales_amount_deptid;
	}

	public String getExec_amount_deptid() {
		return exec_amount_deptid;
	}

	public void setExec_amount_deptid(String exec_amount_deptid) {
		this.exec_amount_deptid = exec_amount_deptid;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public double getProject_amount() {
		return project_amount;
	}

	public void setProject_amount(double project_amount) {
		this.project_amount = project_amount;
	}

	public double getEstimate_costs() {
		return estimate_costs;
	}

	public void setEstimate_costs(double estimate_costs) {
		this.estimate_costs = estimate_costs;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public String getSign_contract_flag() {
		return sign_contract_flag;
	}

	public void setSign_contract_flag(String sign_contract_flag) {
		this.sign_contract_flag = sign_contract_flag;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
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

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public Timestamp getDelete_datetime() {
		return delete_datetime;
	}

	public void setDelete_datetime(Timestamp delete_datetime) {
		this.delete_datetime = delete_datetime;
	}

	public String getDelete_userid() {
		return delete_userid;
	}

	public void setDelete_userid(String delete_userid) {
		this.delete_userid = delete_userid;
	}

	public String getDelete_username() {
		return delete_username;
	}

	public void setDelete_username(String delete_username) {
		this.delete_username = delete_username;
	}

	public String getProject_client_name() {
		return project_client_name;
	}

	public void setProject_client_name(String project_client_name) {
		this.project_client_name = project_client_name;
	}

	public String getRecruitment_username() {
		return recruitment_username;
	}

	public void setRecruitment_username(String recruitment_username) {
		this.recruitment_username = recruitment_username;
	}

	public String getSales_username() {
		return sales_username;
	}

	public void setSales_username(String sales_username) {
		this.sales_username = sales_username;
	}

	public String getInfo_sources_username() {
		return info_sources_username;
	}

	public void setInfo_sources_username(String info_sources_username) {
		this.info_sources_username = info_sources_username;
	}

	public String getManage_username() {
		return manage_username;
	}

	public void setManage_username(String manage_username) {
		this.manage_username = manage_username;
	}

	public String getSales_before_username() {
		return sales_before_username;
	}

	public void setSales_before_username(String sales_before_username) {
		this.sales_before_username = sales_before_username;
	}

	public String getSales_after_username() {
		return sales_after_username;
	}

	public void setSales_after_username(String sales_after_username) {
		this.sales_after_username = sales_after_username;
	}

	public String getSales_amount_deptname() {
		return sales_amount_deptname;
	}

	public void setSales_amount_deptname(String sales_amount_deptname) {
		this.sales_amount_deptname = sales_amount_deptname;
	}

	public String getExec_amount_deptname() {
		return exec_amount_deptname;
	}

	public void setExec_amount_deptname(String exec_amount_deptname) {
		this.exec_amount_deptname = exec_amount_deptname;
	}

	public ProjectStaff[] getProjectStaffs() {
		return projectStaffs;
	}

	public void setProjectStaffs(ProjectStaff[] projectStaffs) {
		this.projectStaffs = projectStaffs;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 是否超过预算成本
	 */
	public boolean getExceed_budget() {
		if(estimate_costs > 0){
			if(amount > estimate_costs) {return true;}
		}
		return false;
	}

	public String getApprove_user_id() {
		return approve_user_id;
	}

	public void setApprove_user_id(String approve_user_id) {
		this.approve_user_id = approve_user_id;
	}

	public String getApprove_user_name() {
		return approve_user_name;
	}

	public void setApprove_user_name(String approve_user_name) {
		this.approve_user_name = approve_user_name;
	}

	public String getProject_client_no() {
		return project_client_no;
	}

	public void setProject_client_no(String project_client_no) {
		this.project_client_no = project_client_no;
	}


	public String getHaveContract() {
		return haveContract;
	}

	public void setHaveContract(String haveContract) {
		this.haveContract = haveContract;
	}
}
