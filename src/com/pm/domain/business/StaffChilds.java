package com.pm.domain.business;

/**
 * 记录人员上下级关系
 * @author zhonglihong
 * @date 2016年12月12日 下午2:37:42
 */
@SuppressWarnings("serial")
public class StaffChilds implements java.io.Serializable {
	
	private String staff_id;
	private String child_id;
	private int level_val ;

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getChild_id() {
		return child_id;
	}

	public void setChild_id(String child_id) {
		this.child_id = child_id;
	}

	public int getLevel_val() {
		return level_val;
	}

	public void setLevel_val(int level_val) {
		this.level_val = level_val;
	}

	
	

}
