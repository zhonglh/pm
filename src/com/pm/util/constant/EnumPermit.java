package com.pm.util.constant;

public enum EnumPermit {
	
	USERVIEW("0000","permit.user.view"),
	USERADD("0001","permit.user.add"),
	USERUPDATE("0002","permit.user.update"),
	USERDELETE("0003","permit.user.delete"),
	USERCHANGEPWD("0004","permit.user.changepwd"),
	
	DEPTVIEW("0100","permit.dept.view"),
	DEPTADD("0101","permit.dept.add"),
	DEPTUPDATE("0102","permit.dept.update"),
	DEPTDELETE("0103","permit.dept.delete"),
	
	ROLEVIEW("0200","permit.role.view"),
	ROLEADD("0201","permit.role.add"),
	ROLEUPDATE("0202","permit.role.update"),
	ROLEDELETE("0203","permit.role.delete"),
	

	LOGVIEW("0300","permit.log.view"),
	LOGDELETE("0303","permit.log.delete"),
	
	

	STAFFVIEW("0400","permit.staff.view"),
	STAFFADD("0401","permit.staff.add"),
	STAFFUPDATE("0402","permit.staff.update"),
	STAFFDELETE("0403","permit.staff.delete"),


	PROJECTVIEW("0500","permit.porject.view"),
	PROJECTADD("0501","permit.porject.add"),
	PROJECTUPDATE("0502","permit.porject.update"),
	PROJECTDELETE("0503","permit.porject.delete"),	
	
	

	
	

	WORKATTENDANCEVIEW("0600","permit.work.attendance.view"),
	WORKATTENDANCEADD("0601","permit.work.attendance.add"),
	WORKATTENDANCEUPDATE("0602","permit.work.attendance.update"),
	WORKATTENDANCEDELETE("0603","permit.work.attendance.delete"),
	WORKATTENDANCECHECK("0604","permit.work.attendance.check"),		

	SALARYVIEW("0700","permit.salary.view"),
	SALARYADD("0701","permit.salary.add"),
	SALARYUPDATE("0702","permit.salary.update"),
	SALARYDELETE("0703","permit.salary.delete"),	
	SALARYCHECK("0704","permit.salary.check"),
	SALARYUNCHECK("0709","permit.salary.uncheck"),
	
	SALARYMAIL("0705","permit.salary.mail"),
	
	

	PROJECTSTAFFCOSTVIEW("0800","permit.project.staff.cost.view"),
	PROJECTSTAFFCOSTCHECK("0801","permit.project.staff.cost.check"),
	

	REIMBURSEVIEW("0900","permit.reimburse.view"),
	REIMBURSEADD("0901","permit.reimburse.add"),
	REIMBURSEUPDATE("0902","permit.reimburse.update"),
	REIMBURSEDELETE("0903","permit.reimburse.delete"),	
	REIMBURSECHECK("0904","permit.reimburse.check"),	
	REIMBURSEUNCHECK("0905","permit.reimburse.uncheck"),

	PROJECTEXPENDVIEW("1000","permit.project.expend.view"),
	PROJECTEXPENDADD("1001","permit.project.expend.add"),
	PROJECTEXPENDUPDATE("1002","permit.project.expend.update"),
	PROJECTEXPENDDELETE("1003","permit.project.expend.delete"),	
	PROJECTEXPENDCHECK("1004","permit.project.expend.check"),
	PROJECTEXPENDUNCHECK("1005","permit.project.expend.uncheck"),
	
	

	MONTHLYSTATEMENTVIEW("1100","permit.monthly.statement.view"),
	MONTHLYSTATEMENTADD("1101","permit.monthly.statement.add"),
	MONTHLYSTATEMENTUPDATE("1102","permit.monthly.statement.update"),
	MONTHLYSTATEMENTDELETE("1103","permit.monthly.statement.delete"),	
	MONTHLYSTATEMENTCHECK("1104","permit.monthly.statement.check"),
	MONTHLYSTATEMENTUNCHECK("1105","permit.monthly.statement.uncheck"),
	
	INVOICEVIEW("1200","permit.invoice.view"),
	INVOICEADD("1201","permit.invoice.add"),
	INVOICEUPDATE("1202","permit.invoice.update"),
	INVOICEDELETE("1203","permit.invoice.delete"),	
	INVOICECHECK("1204","permit.invoice.check"),	
	INVOICEUNCHECK("1205","permit.invoice.uncheck"),	

	RECEIVEDPAYMENTVIEW("1300","permit.received.payment.view"),
	RECEIVEDPAYMENTADD("1301","permit.received.payment.add"),
	RECEIVEDPAYMENTUPDATE("1302","permit.received.payment.update"),
	RECEIVEDPAYMENTDELETE("1303","permit.received.payment.delete"),	
	RECEIVEDPAYMENTCHECK("1304","permit.received.payment.check"),
	RECEIVEDPAYMENTUNCHECK("1305","permit.received.payment.uncheck"),
	
	GROSSPROFITVIEW("1400","permit.gross.profit.view"),
	
	
	SALESVIEW("1500","permit.sales.view"),
	
	
	RECEIVABLESVIEW("1600","permit.receivables.view"),
	

	STAFFPROFITVIEW("2001","permit.staffprofit.view"),
	

	CLIENTVIEW("1700","permit.client.view"),
	CLIENTADD("1701","permit.client.add"),
	CLIENTUPDATE("1702","permit.client.update"),
	CLIENTDELETE("1703","permit.client.delete"),
	

	INSURANCEGRADEVIEW("1800","permit.insurance.grade.view"),
	INSURANCEGRADEADD("1801","permit.insurance.grade.add"),
	INSURANCEGRADEUPDATE("1802","permit.insurance.grade.update"),
	INSURANCEGRADEDELETE("1803","permit.insurance.grade.delete"),	
	

	OTHERSTAFFVIEW("1900","permit.other.staff.view"),
	OTHERSTAFFADD("1901","permit.other.staff.add"),
	OTHERSTAFFUPDATE("1902","permit.other.staff.update"),
	OTHERSTAFFDELETE("1903","permit.other.staff.delete"),
	
	
	
	CONTRACTVIEW("20000","permit.contract.view"),
	CONTRACTADD("20001","permit.contract.add"),
	CONTRACTUPDATE("20002","permit.contract.update"),
	CONTRACTDELETE("20003","permit.contract.delete"),
	
	
	
	POTENTIALCLIENTVIEW("21000","permit.potentialclient.view"),
	POTENTIALCLIENTADD("21001","permit.potentialclient.add"),
	POTENTIALCLIENTUPDATE("21002","permit.potentialclient.update"),
	POTENTIALCLIENTDELETE("21003","permit.potentialclient.delete"),
	POTENTIALCLIENTDALLOT("21004","permit.potentialclient.allot"),
	POTENTIALCLIENTFOLLWUPADD("21006","permit.potentialclient.follwup.add"),
	POTENTIALCLIENTFOLLWUPUPDATE("21007","permit.potentialclient.follwup.update"),
	POTENTIALCLIENTFOLLWUPDELETE("21008","permit.potentialclient.follwup.delete"),
	

	RECRUITINFOVIEW("22000","permit.recruitinfo.view"),
	RECRUITINFOREVIEW("22001","permit.recruitinfo.review"),
	
	

	STAFFCOSTJOINSTATISTICS("23001","permit.staffcost.joinstatistics.view"),
	STAFFCOSTLEAVESTATISTICS("24001","permit.staffcost.leavestatistics.view"),
	

	DEPARTCOSTVIEW("25000","permit.departcost.view"),
	DEPARTCOSTADD("25001","permit.departcost.add"),
	DEPARTCOSTUPDATE("25002","permit.departcost.update"),
	DEPARTCOSTDELETE("25003","permit.departcost.delete"),	
	DEPARTCOSTCHECK("25004","permit.departcost.check"),	
	DEPARTCOSTUNCHECK("25005","permit.departcost.uncheck"),
	

	DEPARTSTATISTICS("26001","permit.departstatistics.view"),
	
	RECRUITLIBRAR("27000","permit.recruit.library.view"),
	
	
	
	
	STAFFEXENTITYVIEW("31000","permit.staffex.view"),
	STAFFEXENTITYADD("31001","permit.staffex.add"),
	STAFFEXENTITYUPDATE("31002","permit.staffex.update"),
	STAFFEXENTITYDELETE("31003","permit.staffex.delete"),
	
	
	
	STAFFECOUNTVIEW("32000","permit.staffex.count.view"),
	
	
	

	FILEINFOMYFILE("40000","permit.fileinfo.myfile"),
	//FILEFOLDERADD("40001","permit.fileinfo.addfolder"),
	//FILEFOLDERDEL("40002","permit.fileinfo.delfolder"),	
	//FILEINFOADD("40003","permit.fileinfo.add"),
	//FILEINFODELETE("40004","permit.fileinfo.delete"),
	//FILEINFOSHARE("40005","permit.fileinfo.share"),	
	
	FILEINFOMYSHARE("41000","permit.fileinfo.myshare"),

	FILEINFOSHAREMY("42000","permit.fileinfo.sharemy"),
	
	

	PERSONNELMONTHLYBASEVIEW("50000","permit.personnelmonthly.view"),
	PERSONNELMONTHLYBASEADD("50001","permit.personnelmonthly.add"),
	PERSONNELMONTHLYBASEUPDATE("50002","permit.personnelmonthly.update"),
	PERSONNELMONTHLYBASEDELETE("50003","permit.personnelmonthly.delete"),	
	PERSONNELMONTHLYBASECHECK("50004","permit.personnelmonthly.check"),	
	PERSONNELMONTHLYBASEUNCHECK("50005","permit.personnelmonthly.uncheck"),
	
	
	
	INSURANCEVIEW("51000","permit.insurance.view"),
	INSURANCEADD("51001","permit.insurance.add"),
	INSURANCEUPDATE("51002","permit.insurance.update"),
	INSURANCEDELETE("51003","permit.insurance.delete"),
	INSURANCECHECK("51004","permit.insurance.check"),
	INSURANCEUNCHECK("51005","permit.insurance.uncheck"),
	
	;


	private String id;

	private String i18ncode;
	

	private EnumPermit(String id, String i18ncode) {
		this.i18ncode = i18ncode;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getI18ncode() {
		return i18ncode;
	}

	public void setI18ncode(String i18ncode) {
		this.i18ncode = i18ncode;
	}
	
}
