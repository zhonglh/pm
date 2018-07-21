package com.pm.vo.reports;

import com.pm.domain.business.StaffCost;

@SuppressWarnings("serial")
public class  StaffCharts extends StaffCost implements java.io.Serializable{
	
	private String id_ ;
	private String no_ ;
	private String name_ ;
	private String depth_;
	private double ratio_ ;
	
	private int month1;
	private double income_amount ;
	
	public String getId_() {
		return id_;
	}
	public void setId_(String id_) {
		this.id_ = id_;
	}
	public String getNo_() {
		return no_;
	}
	public void setNo_(String no_) {
		this.no_ = no_;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getDepth_() {
		return depth_;
	}
	public void setDepth_(String depth_) {
		this.depth_ = depth_;
	}
	public double getRatio_() {
		return ratio_;
	}
	public void setRatio_(double ratio_) {
		this.ratio_ = ratio_;
	}
	public int getMonth1() {
		return month1;
	}
	public void setMonth1(int month1) {
		this.month1 = month1;
	}
	public double getIncome_amount() {
		return income_amount;
	}
	public void setIncome_amount(double income_amount) {
		this.income_amount = income_amount;
	}
	
	
	

}
