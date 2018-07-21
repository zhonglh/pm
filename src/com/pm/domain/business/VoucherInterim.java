package com.pm.domain.business;


import java.io.Serializable;
import java.util.Date;

import com.pm.util.log.EntityAnnotation;

/**
 * 凭证临时表实体类
 * 
 * @author zhonglh
 * @date 2017-10-8 20:39:03
 */
public class VoucherInterim extends IdEntity  implements Serializable{


	private static final long serialVersionUID = 1L;




	//数据类型   1: 月度结算单  2: 发票  3: 回款  
	private String data_type;




    //数据ID , 如发票号ID
	private String data_id;




    //数据历史ID , 如发票号历史ID
	private String data_hisid;




    //凭证日期
	private Date voucher_date;




    //总金额
	private double total_money;




    //是否已导出
	private String exported;




    //导出人
	private String exported_userid;




   //导出人姓名
	private String exported_username;




    //导出时间
	private Date exported_time;




    @EntityAnnotation(item_name="凭证ID",item_sort=10    )
	private String voucher_id;




    @EntityAnnotation(item_name="会计年",item_sort=11 )
	private String years;




    @EntityAnnotation(item_name="会计期间",item_sort=12   )
	private String months;




    @EntityAnnotation(item_name="制单日期",item_sort=13    )
	private Date build_time;




    @EntityAnnotation(item_name="凭证类别",item_sort=14    )
	private String voucher_type;




    @EntityAnnotation(item_name="凭证号",item_sort=15    )
	private String voucher_no;




    @EntityAnnotation(item_name="制单人",item_sort=16      )
	private String build_username;




    @EntityAnnotation(item_name="所附单据数",item_sort=17      )
	private String attached_number;




    @EntityAnnotation(item_name="备注1",item_sort=18      )
	private String description1;




    @EntityAnnotation(item_name="备注2",item_sort=19      )
	private String description2;




    @EntityAnnotation(item_name="科目编码",item_sort=20     )
	private String course_code;




    @EntityAnnotation(item_name="摘要",item_sort=21    )
	private String summary;




    @EntityAnnotation(item_name="结算方式编码",item_sort=22    )
	private String settlement_code;




    @EntityAnnotation(item_name="票据号",item_sort=23  )
	private String bill_number;




    @EntityAnnotation(item_name="票据日期",item_sort=24    )
	private Date bill_date;




    @EntityAnnotation(item_name="币种名称",item_sort=25    )
	private String currency_name;




    @EntityAnnotation(item_name="汇率",item_sort=26      )
	private String exchange_rate;




    @EntityAnnotation(item_name="单价",item_sort=27      )
	private String price;




    @EntityAnnotation(item_name="借方数量",item_sort=28      )
	private String debit_number;




    @EntityAnnotation(item_name="贷方数量",item_sort=29      )
	private String loan_number;




    @EntityAnnotation(item_name="原币借方",item_sort=30      )
	private String original_currency_debit;




    @EntityAnnotation(item_name="原币贷方",item_sort=31      )
	private String original_currency_loan;




    @EntityAnnotation(item_name="借方金额",item_sort=32      )
	private double debit_amount;




    @EntityAnnotation(item_name="贷方金额",item_sort=33      )
	private double loan_amount;




    @EntityAnnotation(item_name="部门编码",item_sort=34      )
	private String depart_code;




    @EntityAnnotation(item_name="职员编码",item_sort=35    )
	private String staff_code;




    @EntityAnnotation(item_name="客户编码",item_sort=36    )
	private String client_code;




    @EntityAnnotation(item_name="供应商编码",item_sort=37    )
	private String vendor_code;




    @EntityAnnotation(item_name="项目大类编码",item_sort=38   )
	private String project_class_code;




    @EntityAnnotation(item_name="项目编码",item_sort=39    )
	private String project_code;




    @EntityAnnotation(item_name="业务员",item_sort=40    )
	private String salesman;




    @EntityAnnotation(item_name="自定义项1",item_sort=41    )
	private String custom_item1;




    @EntityAnnotation(item_name="自定义项2",item_sort=42    )
	private String custom_item2;




    @EntityAnnotation(item_name="自定义项3",item_sort=43    )
	private String custom_item3;




    @EntityAnnotation(item_name="自定义项4",item_sort=44    )
	private String custom_item4;




    @EntityAnnotation(item_name="自定义项5",item_sort=45    )
	private String custom_item5;




    @EntityAnnotation(item_name="自定义项6",item_sort=46    )
	private String custom_item6;




    @EntityAnnotation(item_name="自定义项7",item_sort=47    )
	private String custom_item7;




    @EntityAnnotation(item_name="自定义项8",item_sort=48    )
	private String custom_item8;




    @EntityAnnotation(item_name="自定义项9",item_sort=49    )
	private String custom_item9;




    @EntityAnnotation(item_name="自定义项10",item_sort=50    )
	private String custom_item10;




    @EntityAnnotation(item_name="自定义项11",item_sort=51    )
	private String custom_item11;




    @EntityAnnotation(item_name="自定义项12",item_sort=52    )
	private String custom_item12;




    @EntityAnnotation(item_name="自定义项13",item_sort=53    )
	private String custom_item13;




    @EntityAnnotation(item_name="自定义项14",item_sort=54    )
	private String custom_item14;




    @EntityAnnotation(item_name="自定义项15",item_sort=55    )
	private String custom_item15;




    @EntityAnnotation(item_name="自定义项16",item_sort=56    )
	private String custom_item16;




    @EntityAnnotation(item_name="现金流量项目",item_sort=57    )
	private String cash_flow_item;




    @EntityAnnotation(item_name="现金流量借方金额",item_sort=58      )
	private double cash_flow_debit_amount;




    @EntityAnnotation(item_name="现金流量贷方金额",item_sort=59      )
	private double cash_flow_loan_amount;

    
    
    ///////////////////////////
    
    private String client_name;
    
    private String months1;
    private String months2;
    
    
    

	/** 
	 * 数据类型
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}
	
	/** 
	 * 数据类型
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	/** 
	 * 数据ID , 如发票号ID
	 * @return the data_id
	 */
	public String getData_id() {
		return data_id;
	}
	
	/** 
	 * 数据ID , 如发票号ID
	 */
	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
	/** 
	 * 数据历史ID , 如发票号历史ID
	 * @return the data_hisid
	 */
	public String getData_hisid() {
		return data_hisid;
	}
	
	/** 
	 * 数据历史ID , 如发票号历史ID
	 */
	public void setData_hisid(String data_hisid) {
		this.data_hisid = data_hisid;
	}
	/** 
	 * 凭证日期
	 * @return the voucher_date
	 */
	public Date getVoucher_date() {
		return voucher_date;
	}
	
	/** 
	 * 凭证日期
	 */
	public void setVoucher_date(Date voucher_date) {
		this.voucher_date = voucher_date;
	}
	/** 
	 * 总金额
	 * @return the total_money
	 */
	public double getTotal_money() {
		return total_money;
	}
	
	/** 
	 * 总金额
	 */
	public void setTotal_money(double total_money) {
		this.total_money = total_money;
	}
	/** 
	 * 是否已导出
	 * @return the exported
	 */
	public String getExported() {
		return exported;
	}
	
	/** 
	 * 是否已导出
	 */
	public void setExported(String exported) {
		this.exported = exported;
	}
	/** 
	 * 导出人
	 * @return the exported_userid
	 */
	public String getExported_userid() {
		return exported_userid;
	}
	
	/** 
	 * 导出人
	 */
	public void setExported_userid(String exported_userid) {
		this.exported_userid = exported_userid;
	}
	/** 
	 * 导出人姓名
	 * @return the exported_username
	 */
	public String getExported_username() {
		return exported_username;
	}
	
	/** 
	 * 导出人姓名
	 */
	public void setExported_username(String exported_username) {
		this.exported_username = exported_username;
	}
	/** 
	 * 导出时间
	 * @return the exported_time
	 */
	public Date getExported_time() {
		return exported_time;
	}
	
	/** 
	 * 导出时间
	 */
	public void setExported_time(Date exported_time) {
		this.exported_time = exported_time;
	}
	/** 
	 * 凭证ID
	 * @return the voucher_id
	 */
	public String getVoucher_id() {
		return voucher_id;
	}
	
	/** 
	 * 凭证ID
	 */
	public void setVoucher_id(String voucher_id) {
		this.voucher_id = voucher_id;
	}
	/** 
	 * 会计年
	 * @return the years
	 */
	public String getYears() {
		return years;
	}
	
	/** 
	 * 会计年
	 */
	public void setYears(String years) {
		this.years = years;
	}
	/** 
	 * 会计期间
	 * @return the months
	 */
	public String getMonths() {
		return months;
	}
	
	/** 
	 * 会计期间
	 */
	public void setMonths(String months) {
		this.months = months;
	}
	/** 
	 * 制单日期
	 * @return the build_time
	 */
	public Date getBuild_time() {
		return build_time;
	}
	
	/** 
	 * 制单日期
	 */
	public void setBuild_time(Date build_time) {
		this.build_time = build_time;
	}
	/** 
	 * 凭证类别
	 * @return the voucher_type
	 */
	public String getVoucher_type() {
		return voucher_type;
	}
	
	/** 
	 * 凭证类别
	 */
	public void setVoucher_type(String voucher_type) {
		this.voucher_type = voucher_type;
	}
	/** 
	 * 凭证号
	 * @return the voucher_no
	 */
	public String getVoucher_no() {
		return voucher_no;
	}
	
	/** 
	 * 凭证号
	 */
	public void setVoucher_no(String voucher_no) {
		this.voucher_no = voucher_no;
	}
	/** 
	 * 制单人
	 * @return the build_username
	 */
	public String getBuild_username() {
		return build_username;
	}
	
	/** 
	 * 制单人
	 */
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	/** 
	 * 所附单据数
	 * @return the attached_number
	 */
	public String getAttached_number() {
		return attached_number;
	}
	
	/** 
	 * 所附单据数
	 */
	public void setAttached_number(String attached_number) {
		this.attached_number = attached_number;
	}
	/** 
	 * 备注1
	 * @return the description1
	 */
	public String getDescription1() {
		return description1;
	}
	
	/** 
	 * 备注1
	 */
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	/** 
	 * 备注2
	 * @return the description2
	 */
	public String getDescription2() {
		return description2;
	}
	
	/** 
	 * 备注2
	 */
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	/** 
	 * 科目编码
	 * @return the course_code
	 */
	public String getCourse_code() {
		return course_code;
	}
	
	/** 
	 * 科目编码
	 */
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	/** 
	 * 摘要
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	
	/** 
	 * 摘要
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/** 
	 * 结算方式编码
	 * @return the settlement_code
	 */
	public String getSettlement_code() {
		return settlement_code;
	}
	
	/** 
	 * 结算方式编码
	 */
	public void setSettlement_code(String settlement_code) {
		this.settlement_code = settlement_code;
	}
	/** 
	 * 票据号
	 * @return the bill_number
	 */
	public String getBill_number() {
		return bill_number;
	}
	
	/** 
	 * 票据号
	 */
	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}
	/** 
	 * 票据日期
	 * @return the bill_date
	 */
	public Date getBill_date() {
		return bill_date;
	}
	
	/** 
	 * 票据日期
	 */
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	/** 
	 * 币种名称
	 * @return the currency_name
	 */
	public String getCurrency_name() {
		return currency_name;
	}
	
	/** 
	 * 币种名称
	 */
	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}
	/** 
	 * 汇率
	 * @return the exchange_rate
	 */
	public String getExchange_rate() {
		return exchange_rate;
	}
	
	/** 
	 * 汇率
	 */
	public void setExchange_rate(String exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
	/** 
	 * 单价
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	
	/** 
	 * 单价
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/** 
	 * 借方数量
	 * @return the debit_number
	 */
	public String getDebit_number() {
		return debit_number;
	}
	
	/** 
	 * 借方数量
	 */
	public void setDebit_number(String debit_number) {
		this.debit_number = debit_number;
	}
	/** 
	 * 贷方数量
	 * @return the loan_number
	 */
	public String getLoan_number() {
		return loan_number;
	}
	
	/** 
	 * 贷方数量
	 */
	public void setLoan_number(String loan_number) {
		this.loan_number = loan_number;
	}
	/** 
	 * 原币借方
	 * @return the original_currency_debit
	 */
	public String getOriginal_currency_debit() {
		return original_currency_debit;
	}
	
	/** 
	 * 原币借方
	 */
	public void setOriginal_currency_debit(String original_currency_debit) {
		this.original_currency_debit = original_currency_debit;
	}
	/** 
	 * 原币贷方
	 * @return the original_currency_loan
	 */
	public String getOriginal_currency_loan() {
		return original_currency_loan;
	}
	
	/** 
	 * 原币贷方
	 */
	public void setOriginal_currency_loan(String original_currency_loan) {
		this.original_currency_loan = original_currency_loan;
	}
	/** 
	 * 借方金额
	 * @return the debit_amount
	 */
	public double getDebit_amount() {
		return debit_amount;
	}
	
	/** 
	 * 借方金额
	 */
	public void setDebit_amount(double debit_amount) {
		this.debit_amount = debit_amount;
	}
	/** 
	 * 贷方金额
	 * @return the loan_amount
	 */
	public double getLoan_amount() {
		return loan_amount;
	}
	
	/** 
	 * 贷方金额
	 */
	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}
	/** 
	 * 部门编码
	 * @return the depart_code
	 */
	public String getDepart_code() {
		return depart_code;
	}
	
	/** 
	 * 部门编码
	 */
	public void setDepart_code(String depart_code) {
		this.depart_code = depart_code;
	}
	/** 
	 * 职员编码
	 * @return the staff_code
	 */
	public String getStaff_code() {
		return staff_code;
	}
	
	/** 
	 * 职员编码
	 */
	public void setStaff_code(String staff_code) {
		this.staff_code = staff_code;
	}
	/** 
	 * 客户编码
	 * @return the client_code
	 */
	public String getClient_code() {
		return client_code;
	}
	
	/** 
	 * 客户编码
	 */
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	/** 
	 * 供应商编码
	 * @return the vendor_code
	 */
	public String getVendor_code() {
		return vendor_code;
	}
	
	/** 
	 * 供应商编码
	 */
	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}
	/** 
	 * 项目大类编码
	 * @return the project_class_code
	 */
	public String getProject_class_code() {
		return project_class_code;
	}
	
	/** 
	 * 项目大类编码
	 */
	public void setProject_class_code(String project_class_code) {
		this.project_class_code = project_class_code;
	}
	/** 
	 * 项目编码
	 * @return the project_code
	 */
	public String getProject_code() {
		return project_code;
	}
	
	/** 
	 * 项目编码
	 */
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	/** 
	 * 业务员
	 * @return the salesman
	 */
	public String getSalesman() {
		return salesman;
	}
	
	/** 
	 * 业务员
	 */
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	/** 
	 * 自定义项1
	 * @return the custom_item1
	 */
	public String getCustom_item1() {
		return custom_item1;
	}
	
	/** 
	 * 自定义项1
	 */
	public void setCustom_item1(String custom_item1) {
		this.custom_item1 = custom_item1;
	}
	/** 
	 * 自定义项2
	 * @return the custom_item2
	 */
	public String getCustom_item2() {
		return custom_item2;
	}
	
	/** 
	 * 自定义项2
	 */
	public void setCustom_item2(String custom_item2) {
		this.custom_item2 = custom_item2;
	}
	/** 
	 * 自定义项3
	 * @return the custom_item3
	 */
	public String getCustom_item3() {
		return custom_item3;
	}
	
	/** 
	 * 自定义项3
	 */
	public void setCustom_item3(String custom_item3) {
		this.custom_item3 = custom_item3;
	}
	/** 
	 * 自定义项4
	 * @return the custom_item4
	 */
	public String getCustom_item4() {
		return custom_item4;
	}
	
	/** 
	 * 自定义项4
	 */
	public void setCustom_item4(String custom_item4) {
		this.custom_item4 = custom_item4;
	}
	/** 
	 * 自定义项5
	 * @return the custom_item5
	 */
	public String getCustom_item5() {
		return custom_item5;
	}
	
	/** 
	 * 自定义项5
	 */
	public void setCustom_item5(String custom_item5) {
		this.custom_item5 = custom_item5;
	}
	/** 
	 * 自定义项6
	 * @return the custom_item6
	 */
	public String getCustom_item6() {
		return custom_item6;
	}
	
	/** 
	 * 自定义项6
	 */
	public void setCustom_item6(String custom_item6) {
		this.custom_item6 = custom_item6;
	}
	/** 
	 * 自定义项7
	 * @return the custom_item7
	 */
	public String getCustom_item7() {
		return custom_item7;
	}
	
	/** 
	 * 自定义项7
	 */
	public void setCustom_item7(String custom_item7) {
		this.custom_item7 = custom_item7;
	}
	/** 
	 * 自定义项8
	 * @return the custom_item8
	 */
	public String getCustom_item8() {
		return custom_item8;
	}
	
	/** 
	 * 自定义项8
	 */
	public void setCustom_item8(String custom_item8) {
		this.custom_item8 = custom_item8;
	}
	/** 
	 * 自定义项9
	 * @return the custom_item9
	 */
	public String getCustom_item9() {
		return custom_item9;
	}
	
	/** 
	 * 自定义项9
	 */
	public void setCustom_item9(String custom_item9) {
		this.custom_item9 = custom_item9;
	}
	/** 
	 * 自定义项10
	 * @return the custom_item10
	 */
	public String getCustom_item10() {
		return custom_item10;
	}
	
	/** 
	 * 自定义项10
	 */
	public void setCustom_item10(String custom_item10) {
		this.custom_item10 = custom_item10;
	}
	/** 
	 * 自定义项11
	 * @return the custom_item11
	 */
	public String getCustom_item11() {
		return custom_item11;
	}
	
	/** 
	 * 自定义项11
	 */
	public void setCustom_item11(String custom_item11) {
		this.custom_item11 = custom_item11;
	}
	/** 
	 * 自定义项12
	 * @return the custom_item12
	 */
	public String getCustom_item12() {
		return custom_item12;
	}
	
	/** 
	 * 自定义项12
	 */
	public void setCustom_item12(String custom_item12) {
		this.custom_item12 = custom_item12;
	}
	/** 
	 * 自定义项13
	 * @return the custom_item13
	 */
	public String getCustom_item13() {
		return custom_item13;
	}
	
	/** 
	 * 自定义项13
	 */
	public void setCustom_item13(String custom_item13) {
		this.custom_item13 = custom_item13;
	}
	/** 
	 * 自定义项14
	 * @return the custom_item14
	 */
	public String getCustom_item14() {
		return custom_item14;
	}
	
	/** 
	 * 自定义项14
	 */
	public void setCustom_item14(String custom_item14) {
		this.custom_item14 = custom_item14;
	}
	/** 
	 * 自定义项15
	 * @return the custom_item15
	 */
	public String getCustom_item15() {
		return custom_item15;
	}
	
	/** 
	 * 自定义项15
	 */
	public void setCustom_item15(String custom_item15) {
		this.custom_item15 = custom_item15;
	}
	/** 
	 * 自定义项16
	 * @return the custom_item16
	 */
	public String getCustom_item16() {
		return custom_item16;
	}
	
	/** 
	 * 自定义项16
	 */
	public void setCustom_item16(String custom_item16) {
		this.custom_item16 = custom_item16;
	}
	/** 
	 * 现金流量项目
	 * @return the cash_flow_item
	 */
	public String getCash_flow_item() {
		return cash_flow_item;
	}
	
	/** 
	 * 现金流量项目
	 */
	public void setCash_flow_item(String cash_flow_item) {
		this.cash_flow_item = cash_flow_item;
	}
	/** 
	 * 现金流量借方金额
	 * @return the cash_flow_debit_amount
	 */
	public double getCash_flow_debit_amount() {
		return cash_flow_debit_amount;
	}
	
	/** 
	 * 现金流量借方金额
	 */
	public void setCash_flow_debit_amount(double cash_flow_debit_amount) {
		this.cash_flow_debit_amount = cash_flow_debit_amount;
	}
	/** 
	 * 现金流量贷方金额
	 * @return the cash_flow_loan_amount
	 */
	public double getCash_flow_loan_amount() {
		return cash_flow_loan_amount;
	}
	
	/** 
	 * 现金流量贷方金额
	 */
	public void setCash_flow_loan_amount(double cash_flow_loan_amount) {
		this.cash_flow_loan_amount = cash_flow_loan_amount;
	}

	public String getMonths1() {
		return months1;
	}

	public void setMonths1(String months1) {
		this.months1 = months1;
	}

	public String getMonths2() {
		return months2;
	}

	public void setMonths2(String months2) {
		this.months2 = months2;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	
	
	
}