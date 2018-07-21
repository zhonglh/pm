package com.pm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.DicData;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.Params;
import com.pm.domain.business.Project;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IDicDataService;
import com.pm.service.IInvoiceService;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IOtherStaffService;
import com.pm.service.IParamsService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.UserPermit;

/**
 * 发票管理
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "InvoiceManageAction.do")
public class InvoiceManageAction extends BaseAction {
	
	


	private static final String sessionAttr = "Invoices";

	private static final String rel = "rel31";
	

	@Autowired
	private IApplyApproveService applyApproveService;
	
	@Autowired
	private IInvoiceService invoiceService;
	
	@Autowired
	private IRoleService roleService;
	

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IOtherStaffService otherStaffService;
	

	@Autowired
	private IDicDataService dicDataService;
	

	@Autowired
	private IMonthlyStatementService monthlyStatementService;
	
	

	@Autowired
	private IParamsService paramsService;

	private static Map<String,String> isReceivedPaymentMap = new HashMap<String,String>();
	
	private static Map<String,String> invoiceTypeMap = new HashMap<String,String>();

	private static Map<String,String> isExemptionTaxMap = new HashMap<String,String>();
	
	static{
		invoiceTypeMap.put("增值税普通发票","1");
		invoiceTypeMap.put("增值税专用发票","2");
		invoiceTypeMap.put("1","增值税普通发票");
		invoiceTypeMap.put("2","增值税专用发票");
		

		isExemptionTaxMap.put("是","1");
		isExemptionTaxMap.put("否","0");
		isExemptionTaxMap.put("1","是");
		isExemptionTaxMap.put("0","否");
		
		isReceivedPaymentMap.put("0","未到款");
		isReceivedPaymentMap.put("1","已到款");
		isReceivedPaymentMap.put("2","部分到款");
	}
	
	
	


	@RequestMapping(params = "method=export")
	public void export(Invoice invoice,HttpServletResponse res,HttpServletRequest request){

		paramprocess(request,invoice);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.INVOICEVIEW.getId());
		
		Pager<Invoice> pager = invoiceService.queryInvoice(invoice, userPermit, PubMethod.getPagerByAll(request, Invoice.class));
		
		for(Invoice tmp : pager.getResultList()){
			String type = invoiceTypeMap.get(tmp.getInvoice_type());
			if(type != null) tmp.setInvoice_type(type);
			

			String isExemptionTax = isExemptionTaxMap.get(tmp.getIs_exemption_tax());
			if(isExemptionTax != null) tmp.setIs_exemption_tax(isExemptionTax);
			
			
			tmp.setIs_received_payment(isReceivedPaymentMap.get(tmp.getIs_received_payment()));
			
			
		}
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), Invoice.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}	
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "projectincome/invoice_manager_upload";	
	}	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/invoice.xlsx").getPath();		
		downloadBaseUtil.download(  sourceFile,  "发票模板.xlsx" ,response,false);  		
		return null;  
	}  	
		
	

	/**
	 * 导入Excel
	 * @param file
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		
		
		List<String[]> list = null;
		String fileType = null;
		try{
			fileType = FileKit.getFileNameType(file.getOriginalFilename());
			if(!BusinessUtil.EXCEL_TYPE.contains(fileType)) 
				return this.ajaxForwardError(request, "请输入Excel文件！",true);
		}catch(Exception e){
		}
		
		try{			
			list = ExcelRead.readExcel(file.getInputStream(), FileKit.isXlsx(fileType),  Config.startRow);
		}catch(Exception e){
			e.printStackTrace();
			return this.ajaxForwardError(request, "该文件无法解析！",true);
		}
		
		if(list == null || list.size() == 0) return this.ajaxForwardError(request, "该文件内容为空！",true);
		int index = 0;
		for(String[] row : list){
			if(row.length<12) return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			index ++;
		}
		
		List<Invoice> invoices = PubMethod.stringArray2List(list, Invoice.class);
		
		

		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);		

		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		Map<String,Project>  projectMap = new HashMap<String,Project>();
		if(projects.getResultList() != null){
			for(Project project : projects.getResultList()){
				projectMap.put(project.getProject_name(), project);		
				projectMap.put(project.getProject_no(), project);	
			}
		}
		
		
		OtherStaff searchOtherStaff = new OtherStaff();
		searchOtherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		searchOtherStaff.setPosition_type(null);
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
		Map<String,OtherStaff>  otherStaffMap = new HashMap<String,OtherStaff>();
		if(pager.getResultList() != null) {
			for(OtherStaff otherStaff : pager.getResultList()){
				otherStaffMap.put(otherStaff.getStaff_name(), otherStaff);
				otherStaffMap.put(otherStaff.getStaff_no(), otherStaff);
			}
		}
		
		for(Invoice invoice : invoices){
			boolean b = checkInvoice(invoice,projectMap,otherStaffMap,allDicData);
		}
		
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		for(Invoice invoice : invoices){
			if(invoice.getErrorInfo()==null || invoice.getErrorInfo().length() <= 0){
				try{

					invoice.setInvoice_id(IDKit.generateId());
					invoice.setBuild_datetime(PubMethod.getCurrentDate());
					invoice.setBuild_userid(sessionUser.getUser_id());
					invoice.setBuild_username(sessionUser.getUser_name());								
					
					int count = invoiceService.addInvoice(invoice);
					
					if(count == 0){
						invoice.setErrorInfo("发票号重复");
						isAllOK = false;
						continue;
					}

					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.INVOICE.name(), invoice.getInvoice_id(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
					
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key_2")!=-1 || e.getMessage().indexOf("key 2")!=-1) 
						invoice.setErrorInfo("发票号重复");
					else invoice.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, invoices);
			request.setAttribute("forwardUrl", request.getContextPath()+"/InvoiceManageAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的发票信息中有些问题！ ");
		}
		
	}	
	
	

	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<Invoice> list = (List<Invoice>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "projectincome/invoice_manager_excel_list";
	}

	private boolean checkInvoice(Invoice invoice,
			Map<String,Project>  projectMap,
			Map<String,OtherStaff>  otherStaffMap,
			Map<String, Map<String, DicData>> allDicData){
		boolean b = true;
		
		if(invoice.getProject_name() == null ||  invoice.getProject_name().isEmpty()){
			invoice.setErrorInfo(invoice.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(invoice.getProject_name().trim());
			if(project == null ){
				invoice.setErrorInfo(invoice.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				invoice.setProject_id(project.getProject_id());
				invoice.setProject_name(project.getProject_name());
				invoice.setProject_no(project.getProject_no());
				
				if(invoice.getInvoice_title() == null || invoice.getInvoice_title().isEmpty())
					invoice.setInvoice_title(project.getProject_client_name());
			}
		}
		
		
		if(invoice.getInvoice_content_name() != null && invoice.getInvoice_content_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.INVOICE_CONTENT.name(),invoice.getInvoice_content_name(),allDicData);
			if(dicData != null){
				invoice.setInvoice_content(dicData.getId());
			}else {
				invoice.setErrorInfo(invoice.getErrorInfo() + "发票内容错误;");
				b = false;
			}
		}


		if(invoice.getInvoice_no() == null || invoice.getInvoice_no().isEmpty()){
			invoice.setErrorInfo(invoice.getErrorInfo() + "发票编号不能为空;");
			b = false;
		}
		if(invoice.getInvoiceno_amount() == 0){
			invoice.setErrorInfo(invoice.getErrorInfo() + "发票金额不能为空;");
			b = false;
		}

		
		if(invoice.getInvoice_type() != null && invoice.getInvoice_type().length() >0){
			String invoicietype = invoiceTypeMap.get(invoice.getInvoice_type());
			if(invoicietype == null) {
				invoice.setErrorInfo(invoice.getErrorInfo() + "发票类型错误;");
				b = false;
			}else {
				invoice.setInvoice_type(invoicietype);
			}
		}

		if(invoice.getIs_exemption_tax() != null && invoice.getIs_exemption_tax().length() >0){

			String is_exemption_tax = isExemptionTaxMap.get(invoice.getIs_exemption_tax());
			if(is_exemption_tax == null) {
				invoice.setErrorInfo(invoice.getErrorInfo() + "是否免税错误;");
				b = false;
			}else {
				invoice.setIs_exemption_tax(is_exemption_tax);
			}
		}
		
		
		if(invoice.getInvoice_staff_name() != null && invoice.getInvoice_staff_name().length() > 0){
			OtherStaff otherStaff = otherStaffMap.get(invoice.getInvoice_staff_name());
			if(otherStaff == null){
				invoice.setErrorInfo(invoice.getErrorInfo() + "开票人员错误;");
				b = false;
			}else {
				invoice.setInvoice_staff_id(otherStaff.getStaff_id());
				invoice.setInvoice_staff_name(otherStaff.getStaff_name());
			}
		}
		
		if(invoice.getInvoice_receive_name() != null && invoice.getInvoice_receive_name().length() >0){
			OtherStaff otherStaff = otherStaffMap.get(invoice.getInvoice_receive_name());
			if(otherStaff == null){
				invoice.setErrorInfo(invoice.getErrorInfo() + "发票接收人员错误;");
				b = false;
			}else {
				invoice.setInvoice_receive(otherStaff.getStaff_id());
				invoice.setInvoice_receive_name(otherStaff.getStaff_name());
			}
		}
		
		
		
		if(invoice.getErrorInfo() != null && !invoice.getErrorInfo().isEmpty())
			b = false;
		
		return b;
	}
	
	
	
	
	
	
	

	@RequestMapping(params = "method=lookup")
	public void lookup(Invoice invoice,HttpServletResponse res,HttpServletRequest request){				
		
		paramprocess(request,invoice);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.INVOICEVIEW.getId());
		
		invoice.setIs_received_payment("0");

		request.setAttribute("invoice", invoice);
		
		Pager<Invoice> pager = invoiceService.queryInvoice(invoice, userPermit, PubMethod.getPagerByAll(request, Invoice.class));
		PubMethod.setRequestPager(request, pager,Invoice.class);	
		if(pager.getResultList() == null) pager.setResultList(new ArrayList<Invoice>());		
		this.writeResJson(res, pager.getResultList());
	}	

	@RequestMapping(params = "method=list")
	public String list(Invoice invoice,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.INVOICEVIEW.getId());

		paramprocess(request,invoice);
		
		Pager<Invoice> pager = invoiceService.queryInvoice(invoice, userPermit, PubMethod.getPager(request, Invoice.class));
		PubMethod.setRequestPager(request, pager,Invoice.class);	
		
		request.setAttribute("invoice", invoice);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INVOICEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INVOICEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INVOICEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());	
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INVOICECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "projectincome/invoice_manager_list";
	}		
	
	

	@RequestMapping(params = "method=search")
	public String search(Invoice invoice,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.INVOICEVIEW.getId());

		invoice.setIs_received_payment("0");
		
		paramprocess(request,invoice);
		
		Pager<Invoice> pager = invoiceService.queryInvoice(invoice, userPermit, PubMethod.getPager(request, Invoice.class));
		PubMethod.setRequestPager(request, pager,Invoice.class);	
		
		request.setAttribute("invoice", invoice);

		
		return "projectincome/invoice_manager_search";
	}			
	

	private void paramprocess(HttpServletRequest request,Invoice invoice){		
		

		invoice.setDept_id(request.getParameter("dept.dept_id"));
		invoice.setDept_name(request.getParameter("dept.dept_name"));
		
		invoice.setProject_id(request.getParameter("project.project_id"));	
		
		if(invoice.getProject_name() == null || invoice.getProject_name().isEmpty())
		invoice.setProject_name(request.getParameter("project.project_name"));	
		
		invoice.setProject_no(request.getParameter("project.project_no"));

		invoice.setInvoice_staff_id(request.getParameter("otherstaff.staff_id"));
		invoice.setInvoice_staff_name(request.getParameter("otherstaff.staff_name"));		

		invoice.setInvoice_receive(request.getParameter("receive_staff.staff_id"));
		invoice.setInvoice_receive_name(request.getParameter("receive_staff.staff_name"));


		invoice.setInvoice_content(request.getParameter("content_dic.id"));
		invoice.setInvoice_content_name(request.getParameter("content_dic.dic_data_name"));
		
		
		invoice.setContract_id(request.getParameter("contract.id"));
		invoice.setContract_no(request.getParameter("contract.contract_no"));
		
		
		invoice.setInvoice_title(request.getParameter("project.project_client_name"));

		invoice.setMonthly_statement_name(request.getParameter("monthlyStatement.monthly_statement_name"));
		invoice.setMonthly_statement_id(request.getParameter("monthlyStatement.monthly_statement_id"));
		if(invoice.getMonthly_statement_id() != null && invoice.getMonthly_statement_id().isEmpty()){
			invoice.setMonthly_statement_id(null);
		}
		
	}	
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(Invoice searchInvoice,HttpServletResponse res,HttpServletRequest request){
		Invoice invoice = null;
		if(searchInvoice != null && searchInvoice.getInvoice_id()!=null){
			request.setAttribute("next_operation", "updateInvoice");
			invoice = invoiceService.getInvoice(searchInvoice.getInvoice_id());	
			if(invoice.getVerify_userid() != null && invoice.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}			
			
			if(invoice.getMonthly_statement_id() != null){
				MonthlyStatement monthlyStatement = new MonthlyStatement();
				monthlyStatement.setMonthly_statement_id(invoice.getMonthly_statement_id());
				monthlyStatement = monthlyStatementService.getMonthlyStatement(monthlyStatement);
				if(monthlyStatement != null && monthlyStatement.getProject_id() != null){
					invoice.setMonthly_statement_name(monthlyStatement.getProject_name()+"("+monthlyStatement.getStatement_month()+"");
					invoice.setStatement_type(monthlyStatement.getStatement_type());
				}
			}
			
		}else {
			Params params = new Params();
			double rate = 0.06 ;
			params.setParam_key("invoice.default.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			try{
			if(paramList != null && paramList.size() > 0){
				rate = Double.parseDouble(paramList.get(0).getParam_value());
			}
			}catch(Exception e){}
			request.setAttribute("next_operation", "addInvoice");			
			User sessionUser = PubMethod.getUser(request);
			invoice = new Invoice();	
			invoice.setTax_rate(rate*100);
			invoice.setBuild_userid(sessionUser.getUser_id());
			invoice.setBuild_username(sessionUser.getUser_name());
			invoice.setBuild_datetime(PubMethod.getCurrentDate());
			
		}

		request.setAttribute("invoice1", invoice);
		return "projectincome/invoice_manager_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(Invoice searchInvoice,HttpServletResponse res,HttpServletRequest request){
		
		Invoice invoice = invoiceService.getInvoice(searchInvoice.getInvoice_id());
		
		if(invoice.getMonthly_statement_id() != null){
			MonthlyStatement monthlyStatement = new MonthlyStatement();
			monthlyStatement.setMonthly_statement_id(invoice.getMonthly_statement_id());
			monthlyStatement = monthlyStatementService.getMonthlyStatement(monthlyStatement);
			if(monthlyStatement != null && monthlyStatement.getProject_id() != null){
				invoice.setMonthly_statement_name(monthlyStatement.getProject_name()+"("+monthlyStatement.getStatement_month()+"");
				invoice.setStatement_type(monthlyStatement.getStatement_type());
			}
		}
		
		
		request.setAttribute("invoice1", invoice);


		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INVOICECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INVOICEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject(invoice.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.INVOICE.name(), invoice.getInvoice_id());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.INVOICE.name(), invoice.getInvoice_id());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", invoice.getVerify_userid());
		request.setAttribute("data_id", invoice.getInvoice_id());
		request.setAttribute("data_type", EnumEntityType.INVOICE.name());
		
		return "projectincome/invoice_manager_view";
		
	}	
	

	@RequestMapping(params = "method=addInvoice")
	public String addInvoice(Invoice addInvoice,HttpServletResponse res,HttpServletRequest request){
		
		Invoice invoice = addInvoice;	
		paramprocess(request,invoice);

		User sessionUser = PubMethod.getUser(request);
		invoice.setInvoice_id(IDKit.generateId());
		invoice.setBuild_datetime(PubMethod.getCurrentDate());
		invoice.setBuild_userid(sessionUser.getUser_id());
		invoice.setBuild_username(sessionUser.getUser_name());	
		
		
		int count = 0;
		try{
			ThreadLocalUser.setUser(sessionUser);
			count = invoiceService.addInvoice(invoice);		
			ThreadLocalUser.setUser(null);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.INVOICE.name(), invoice.getInvoice_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
		
	}
	

	

	@RequestMapping(params = "method=updateInvoice")
	public String updateInvoice(Invoice updateInvoice,HttpServletResponse res,HttpServletRequest request){
		
		Invoice invoice = updateInvoice;	
		paramprocess(request,invoice);		

		User sessionUser = PubMethod.getUser(request);

		int count = 0;
		try{
			ThreadLocalUser.setUser(sessionUser);
			count = invoiceService.updateInvoice(invoice);			
			ThreadLocalUser.setUser(null);	
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
		
	}	
	
	

	@RequestMapping(params = "method=batchVerifyInvoice")
	public String batchVerifyInvoice(HttpServletResponse res,HttpServletRequest request){

		String[] invoice_ids = request.getParameterValues("ids");
		if(invoice_ids == null || invoice_ids.length == 0){
			return this.ajaxForwardError(request, "请先选择单据！", false);
		}
		
		User sessionUser = PubMethod.getUser(request);

		try{
			ThreadLocalUser.setUser(sessionUser);
			for(String id : invoice_ids){
				Invoice invoice = invoiceService.getInvoice(id);
				invoice.setVerify_datetime(PubMethod.getCurrentDate());
				invoice.setVerify_userid(sessionUser.getUser_id());
				invoice.setVerify_username(sessionUser.getUser_name());
				//invoice.setInvoice_id(id);
				invoiceService.verifyInvoice(invoice);
	
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.INVOICE.name(), invoice.getInvoice_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}

	@RequestMapping(params = "method=verifyInvoice")
	public String verifyInvoice(Invoice invoice,HttpServletResponse res,HttpServletRequest request){
		invoice = invoiceService.getInvoice(invoice.getInvoice_id());
		User sessionUser = PubMethod.getUser(request);
		invoice.setVerify_datetime(PubMethod.getCurrentDate());
		invoice.setVerify_userid(sessionUser.getUser_id());
		invoice.setVerify_username(sessionUser.getUser_name());
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			invoiceService.verifyInvoice(invoice);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.INVOICE.name(), invoice.getInvoice_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		return this.ajaxForwardSuccess(request, rel, true);
	}

	@RequestMapping(params = "method=deleteInvoice")
	public String deleteInvoice(HttpServletResponse res,HttpServletRequest request){
		
		
		String[] invoice_ids = request.getParameterValues("ids");
		if(invoice_ids != null && invoice_ids.length > 0){
			Invoice[] invoices = new Invoice[invoice_ids.length];
			int index = 0;
			for(String invoice_id : invoice_ids){
				Invoice invoice = new Invoice();
				invoice.setInvoice_id(invoice_id);
				invoices[index] = invoice;
				index ++ ;
			}
			
			if(invoices != null && invoices.length > 0)
			invoiceService.deleteInvoice(invoices);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}		
	
	
}
