package com.pm.util.constant;



/**
 * 实体类型
 * @author zhonglh
 *
 */
public enum EnumEntityType {
	
	USER("user.entity","user"),
	DEPT("dept.entity","dept"),
	DEPARTOBJECTIVE("dept.objective","dept.objective"),
	ROLE("role.entity","role"),
	LOG("log.entity","log"),
	

	CLIENT("client.entity","client"),
	INSURANCE_GRADE("insurance.grade.entity","insurance.grade"),
	STAFF_COST("staff.cost.entity","staff.cost"),
	PROJECT("project.entity","project"),
	CONTRACT("contract","contract"),
	PAYCONTRACT("paycontract","paycontract"),
	RECRUITINFO("recruitinfo","recruitinfo"),
	POTENTIALCLIENT("potentialclient","potentialclient"),
	POTENTIALCLIENT_FOLLWUP("potentialclient.followup","potentialclient.followup"),
	
	
	WORK_ATTENDANCE("work.attendance.entity","work.attendance"),
	SALARY("salary.entity","salary"),
	PROJECT_STAFF_COST("project.staff.cost.entity","project.staff.cost"),
	PROJECT_EXPEND("project.expend.entity","project.expend"),
	PROJECT_EXPEND_PAY("project.expend.pay.entity","project.expend.pay"),
	REIMBURSE_COSTS("reimburse.costs.entity","reimburse.costs"),
	COMMONCOST("common.cost.entity","common.cost"),


	OTHER_STAFF("other.staff.entity","other.staff"),
	OTHER_WORK_ATTENDANCE("other.work.attendance.entity","other.work.attendance"),
	OTHER_SALARY("other.salary.entity","other.salary"),


	MONTHLY_STATEMENT("monthly.statement.entity","monthly.statement"),
	INVOICE("invoice.entity","invoice"),
	RECEIVED_PAYMENT("received.payment.entity","received.payment"),
	
	REIMBURSE_ITEM("reimburse.item.entity","reimburse.item"),
	ACCOUNTS_TYPE("accounts.type.entity","accounts.type"),
	DICDATA("dicdata","dicdata"),


	COMMON_COST_ITEM("common.cost.item.entity","common.cost.item"),
	
	

	DEPART_COSTS("depart.costs.entity","depart.costs"),


	
	PERSONNELMONTHLYBASE("personnel.monthly.entity","personnel.monthly"),

	INSURANCE("insurance.entity","insurance"),
	

	PARAM("set.param","set.param"),
	PARAMEX("set.paramex","set.paramex"),
	
	
	

	;

	private String key;

	private String i18ncode;

	private EnumEntityType(String key, String i18ncode) {
		this.i18ncode = i18ncode;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getI18ncode() {
		return i18ncode;
	}

}
