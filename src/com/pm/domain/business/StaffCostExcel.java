package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class StaffCostExcel implements Serializable {
	

	@EntityAnnotation(item_name="招聘专员名称", item_sort=1)
	private String recruiter_name;	

	@EntityAnnotation(item_name="员工名称", item_sort=2)
	private String staff_name;	
	

	@EntityAnnotation(item_name="联系电话", item_sort=3)
	private String tel;
	

	@EntityAnnotation(item_name="入职时间", item_sort=4)
	private Timestamp join_datetime;	
	

	@EntityAnnotation(item_name="离职时间", item_sort=5)
	private Timestamp leave_job_datetime;
	
	@EntityAnnotation(item_name="所在项目", item_sort=6)
	private String project_name;
	

	@EntityAnnotation(item_name="人员总成本", item_sort=7)
	private double totalcost ;
	

	@EntityAnnotation(item_name="客户最初报价", item_sort=8)
	private double firstquotes;	
	


	@EntityAnnotation(item_name="差额", item_sort=9)
	private double diff ;



	public String getRecruiter_name() {
		return recruiter_name;
	}



	public void setRecruiter_name(String recruiter_name) {
		this.recruiter_name = recruiter_name;
	}



	public String getStaff_name() {
		return staff_name;
	}



	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}






	public Timestamp getJoin_datetime() {
		return join_datetime;
	}



	public void setJoin_datetime(Timestamp join_datetime) {
		this.join_datetime = join_datetime;
	}



	public Timestamp getLeave_job_datetime() {
		return leave_job_datetime;
	}



	public void setLeave_job_datetime(Timestamp leave_job_datetime) {
		this.leave_job_datetime = leave_job_datetime;
	}



	public String getProject_name() {
		return project_name;
	}



	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}



	public double getTotalcost() {
		return totalcost;
	}



	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}



	



	public double getDiff() {
		return diff;
	}



	public void setDiff(double diff) {
		this.diff = diff;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public double getFirstquotes() {
		return firstquotes;
	}



	public void setFirstquotes(double firstquotes) {
		this.firstquotes = firstquotes;
	}
	

}
