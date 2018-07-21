package com.pm.util.constant;


/**
 * 营销模式工资计算原始项
 * @author zhonglihong
 * @date 2016年12月16日 上午9:00:15
 */
public enum EnumSalaryItem {
	

	platform_ratio("platform_ratio", "平台收费比例" , "营销模式全局设置中的平台收费比例" ,10),
	
	totalcost("totalcost", "人员总成本" ,  "人员成本中设置的人员总成本" ,21),
	qustomerquotes("qustomerquotes", "技术费用" ,"人员成本中设置的客户最新报价" ,22),
	ss_bypersonal("ss_bypersonal", "社保个人部分" ,"人员成本中个人缴纳的社保" ,23),
	ss_bycompany("ss_bycompany", "社保公司部分" ,"人员成本中公司缴纳的社保" ,24),
	extra_item("extra_item","额外项","人员成本中的额外",25),
	extra_expend("extra_expend", "额外支出","人员成本中的额外支出",26),
	
	guarantee_amount("guarantee_amount", "垫资金额" ,"薪酬模式中设置的垫资金额",31),
	other_expenditures("other_expenditures", "其他固定支出" ,"薪酬模式中设置的其他固定支出",32),
	other_ratio("other_ratio", "其他支出比例" ,"薪酬模式中设置的其他支出比例",32),
	
	exchange_taxrate("exchange_taxrate", "交易税率" ,"参数设置中的税率", 40),
	;
	
	String key;
	String showName;
	String explain;
	int ordernum;
	
	
	private EnumSalaryItem(String key, String showName, String explain, int ordernum) {
		this.key = key;
		this.showName = showName;
		this.explain = explain;
		this.ordernum = ordernum;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}

	
	
	

}
