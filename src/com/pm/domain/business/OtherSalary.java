package com.pm.domain.business;

import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * 工资表
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class OtherSalary extends AbstractSalary implements Serializable {


	private String dept_id;


	@EntityAnnotation(item_name="部门名称")
	private String dept_name;


	//部门工资人数
	private int dept_salary_number;

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

	public int getDept_salary_number() {
		return dept_salary_number;
	}

	public void setDept_salary_number(int dept_salary_number) {
		this.dept_salary_number = dept_salary_number;
	}
}
