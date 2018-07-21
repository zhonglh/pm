package com.pm.domain.business;

@SuppressWarnings("serial")
public class Statistics extends Project {

	/**
	 * 金额
	 */
	private double statistics_amount;
	/**
	 * 利率
	 */
	private double statistics_rate;
	
	/**
	 * 统计类型 毛利，销售额， 应收
	 */
	private String statistics_type;
	
	/**
	 * 统计口径
	 */
	private String statistics_caliber;
	
	/**
	 * 查询起始月
	 */
	private int month1;

	/**
	 * 查询结束月
	 */
	private int month2;
	
	/**
	 * 年份
	 */
	private int year1;
	
	/**
	 * 季度
	 */
	private int quarter;

	/**
	 * 季度
	 */
	private String quarter_name;	
	
	/**
	 * 部门ID
	 */
	private String deptId;
	private String deptName;
	
	
	
	//小类ID,  报销类型等, 部门管理费用类型等
	private String type_id;
	
	
	//成本和收入类型 , 10,20,30 40  50,51,100,101 对应  收款 ，  结算单 ，  报销  ，支出费用  和项目人员成本 , 销售费用， 部门管理费用
	private String x;
	
	private String groupBy;
	private String groupSelect;
	
	
	private String staff_id;
	
	/**
	 * 税率， 用于计算到款扣税
	 */
	private double tax_rate = 0.0672;
	

	public double getStatistics_amount() {
		return statistics_amount;
	}

	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}

	public String getStatistics_type() {
		return statistics_type;
	}

	public void setStatistics_type(String statistics_type) {
		this.statistics_type = statistics_type;
	}

	public String getStatistics_caliber() {
		return statistics_caliber;
	}

	public void setStatistics_caliber(String statistics_caliber) {
		this.statistics_caliber = statistics_caliber;
	}


	public int getMonth1() {
		return month1;
	}

	public void setMonth1(int month1) {
		this.month1 = month1;
	}

	public int getMonth2() {
		return month2;
	}

	public void setMonth2(int month2) {
		this.month2 = month2;
	}

	public int getYear1() {
		return year1;
	}

	public void setYear1(int year1) {
		this.year1 = year1;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public String getQuarter_name() {
		return quarter_name;
	}

	public void setQuarter_name(String quarter_name) {
		this.quarter_name = quarter_name;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public double getStatistics_rate() {
		return statistics_rate;
	}

	public void setStatistics_rate(double statistics_rate) {
		this.statistics_rate = statistics_rate;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getGroupSelect() {
		return groupSelect;
	}

	public void setGroupSelect(String groupSelect) {
		this.groupSelect = groupSelect;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}


	

}
