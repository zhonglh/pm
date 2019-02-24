package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.pm.util.log.EntityAnnotation;


/**
 * 工资表
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class Salary extends AbstractSalary implements Serializable {


	private String project_id;


	////////////////////////////////////////////////////
	////////////////////////扩展////////////////////////
	////////////////////////////////////////////////////

	@EntityAnnotation(item_name="项目名称")
	private String project_name;



	@EntityAnnotation(item_name="项目编号")
	private String project_no;



	private String dept_id;
	private String dept_name;

	//项目工资人数
	private int project_salary_number;


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

	public int getProject_salary_number() {
		return project_salary_number;
	}

	public void setProject_salary_number(int project_salary_number) {
		this.project_salary_number = project_salary_number;
	}


}
