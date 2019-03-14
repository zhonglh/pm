package com.pm.util.constant;

/**
 * @author Administrator
 */

public enum EnumStaticType {
	
	
	S10("10","回款"),
	S11("11","金额为回款， 时间为结算单"),
	S12("12","未核实回款"),
	S13("13","月份按照收款时间的回款"),
	S14("14","月份按照收款时间的未核实回款"),

	S20("20","月度结算单"),
	S21("21","月度结算单明细"),
	S22("22","未核实月度结算单"),
	S30("30","所有报销"),
	S31("31","人员报销"),
	S40("40","付款信息(实际付款或应付款)"),
	S41("41","付款信息(实际付款)"),
	S42("42","付款信息(按照支付信息记录)"),
	S50("50","项目人员成本(实发工资)"),
	S51("51","项目人员成本(所有成本)"),
	S60("60","工资"),
	S70("70","发票"),
	S80("80","公共费用"),

	S100("100","销售费用"),
	S101("101","部门管理费用"),
	S201("201","总部人员成本"),
	;
	
	private String code;
	private String name;

	private EnumStaticType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
