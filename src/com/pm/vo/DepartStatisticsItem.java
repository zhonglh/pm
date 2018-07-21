
package com.pm.vo;

import java.io.Serializable;

/**
 * 部门统计信息
 * @author zhonglihong
 * @date 2016年6月2日 下午11:30:41
 */
@SuppressWarnings("serial")
public class DepartStatisticsItem implements Serializable {
	
	/**
	 * 部门ID
	 */
	private String deptId;
	
	/**
	 * 元素ID
	 */
	private String itemId;

	/**
	 * 元素名称
	 */
	private String itemName;
	
	/**
	 * 元素格式化
	 */
	private String itemFormatter;
	
	

	/**
	 * 统计值
	 */
	private double val;
	
	/**
	 * 格式化
	 */
	private String formatter;
	
	/**
	 * 格式化后的值
	 */
	private String showVal;
	
	/**
	 * URL
	 */
	private String url;

	
	
	
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShowVal() {
		return showVal;
	}

	public void setShowVal(String showVal) {
		this.showVal = showVal;
	}

	public String getItemFormatter() {
		return itemFormatter;
	}

	public void setItemFormatter(String itemFormatter) {
		this.itemFormatter = itemFormatter;
	}
	
	
	public DepartStatisticsItem copy(String itemId, String itemName){
		DepartStatisticsItem departStatisticsItem = new DepartStatisticsItem();
		departStatisticsItem.setDeptId(this.getDeptId());
		departStatisticsItem.setFormatter(this.getFormatter());
		departStatisticsItem.setVal(this.getVal());
		departStatisticsItem.setShowVal(this.getShowVal());
		
		departStatisticsItem.setItemFormatter(this.getItemFormatter());
		departStatisticsItem.setItemId(itemId);
		departStatisticsItem.setItemName(itemName);		
		return departStatisticsItem;
	}
	

}
