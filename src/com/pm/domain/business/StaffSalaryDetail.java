package com.pm.domain.business;


/**
 * 人员工资明细项
 * @author zhonglihong
 * @date 2016年12月12日 下午2:37:57
 */
@SuppressWarnings("serial")
public class StaffSalaryDetail implements java.io.Serializable {
	
	//人员ID
	private String staff_id;
	
	
	//用于记录Item类型 ts_item_define的ID
	private String item_id;
	
	//项目名称， 如ts_item_define中的name, 或者上下级的姓名等
	private String item_name;
	
	//金额
	private double bal;
	
	//发生方向	0:支出  1:收入
	private String item_direction;

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getBal() {
		return bal;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}

	public String getItem_direction() {
		return item_direction;
	}

	public void setItem_direction(String item_direction) {
		this.item_direction = item_direction;
	}
	
	
	

	
	

}
