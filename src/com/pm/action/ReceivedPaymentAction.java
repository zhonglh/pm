package com.pm.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.DicData;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.Project;
import com.pm.domain.business.ReceivedPayment;
import com.pm.domain.business.ReimburseCosts;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IDicDataService;
import com.pm.service.IInvoiceService;
import com.pm.service.IProjectService;
import com.pm.service.IReceivedPaymentService;
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
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.util.excel.imports.ExcelRead;
import com.pm.vo.UserPermit;

/**
 * 到款记录
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "ReceivedPaymentAction.do")
public class ReceivedPaymentAction extends BaseAction {
	

	private static final String sessionAttr = "ReceivedPaymentActions";

	private static final String rel = "rel32";
	

	@Autowired
	private IApplyApproveService applyApproveService;
	
	@Autowired
	private IReceivedPaymentService receivedPaymentService;
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	IProjectService projectService;	

	@Autowired
	private IDicDataService dicDataService;
	
	@Autowired
	private IInvoiceService invoiceService;
	
	

	@RequestMapping(params = "method=export")
	public void export(ReceivedPayment receivedPayment,HttpServletResponse res,HttpServletRequest request){
		

		paramprocess(request,receivedPayment);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEVIEW.getId());		
		Pager<ReceivedPayment> pager= receivedPaymentService.queryReceivedPayment(receivedPayment, userPermit,PubMethod.getPagerByAll(request, ReceivedPayment.class));
		
		if(pager.getResultList() != null){
			for(ReceivedPayment rp : pager.getResultList()){
				rp.setStr_month(String.valueOf(rp.getReceive_payment_month()));
			}
		}
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), ReceivedPayment.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}	
	
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "projectincome/received_payment_upload";	
	}	
	
	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/receivedpayment.xlsx").getPath();		
		downloadBaseUtil.download(  sourceFile,  "到款记录模板.xlsx" ,response,false);  		
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
			if(row.length<8) return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			index ++;
		}
		
		List<ReceivedPayment> receivedPayments = PubMethod.stringArray2List(list, ReceivedPayment.class);
		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);		


		//查找所有项目
		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		Map<String,Project>  projectMap = new HashMap<String,Project>();
		if(projects.getResultList() != null) {
			for(Project project : projects.getResultList()){
				projectMap.put(project.getProject_name(), project);	
				projectMap.put(project.getProject_no(), project);		
			}
		}
		
		//查找收款方式
		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		
		//查可以使用发票
		Invoice invoice = new Invoice();
		invoice.setIs_received_payment("0");
		Pager<Invoice> invoices = invoiceService.queryInvoice(invoice, userPermit, PubMethod.getPagerByAll(request, Invoice.class));
		Map<String,Invoice>  invoiceMap = new HashMap<String,Invoice>();
		if(invoices.getResultList() != null) {
			for(Invoice invoice1 : invoices.getResultList()){
				invoiceMap.put(invoice1.getInvoice_no(), invoice1);		
			}
		}
			
		for(ReceivedPayment receivedPayment : receivedPayments){
			boolean b = checkReceivedPayment(receivedPayment,projectMap,allDicData,invoiceMap);
			
		}
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		index = 0;
		for(ReceivedPayment receivedPayment : receivedPayments){
			if(receivedPayment.getErrorInfo()==null || receivedPayment.getErrorInfo().length() <= 0){
				try{
					
					receivedPayment.setReceive_payment_id(IDKit.generateId());	
					receivedPayment.setBuild_datetime(PubMethod.getCurrentDate());
					receivedPayment.setBuild_userid(sessionUser.getUser_id());
					receivedPayment.setBuild_username(sessionUser.getUser_name());
					
					
					int count = receivedPaymentService.addReceivedPayment(receivedPayment);
					
					if(count == 0){
						receivedPayment.setErrorInfo("已经有此到款记录");
						isAllOK = false;
					}
					
					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.RECEIVED_PAYMENT.name(), receivedPayment.getReceive_payment_id(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
					
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key_2")!=-1 || e.getMessage().indexOf("key 2")!=-1) 
						receivedPayment.setErrorInfo("已经有此到款记录");
					else receivedPayment.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}		

		
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {
			request.getSession().setAttribute(sessionAttr, receivedPayments);
			request.setAttribute("forwardUrl", request.getContextPath()+"/ReceivedPaymentAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的到款记录中有些问题！ ");
		}
		
	}	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<ReimburseCosts> list = (List<ReimburseCosts>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "projectincome/received_payment_excel_list";
	}


	private boolean checkReceivedPayment(ReceivedPayment receivedPayment ,
			Map<String,Project>  projectMap,
			Map<String, Map<String, DicData>> allDicData,
			Map<String,Invoice>  invoiceMap){
		boolean b = true;
		if(receivedPayment.getStr_month() == null ||  receivedPayment.getStr_month().isEmpty()){
			receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "到款所属月份不能为空;");
			b = false;
		}else {
			if(receivedPayment.getStr_month().length() != 6) {
				receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "到款所属月份格式错误;");
				b = false;
			}else{
				Date date1 = DateKit.fmtStrToDate(receivedPayment.getStr_month()+"01","yyyyMMdd");
				if(date1 == null){
					receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "到款所属月份格式错误;");
					b = false;
				}else {
					receivedPayment.setReceive_payment_month(Integer.parseInt(receivedPayment.getStr_month()));
				}
			}
		}
		
		
		if(receivedPayment.getProject_name() == null ||  receivedPayment.getProject_name().isEmpty()){
			receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(receivedPayment.getProject_name().trim());
			if(project == null ){
				receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				receivedPayment.setProject_id(project.getProject_id());
				receivedPayment.setProject_name(project.getProject_name());
				receivedPayment.setProject_no(project.getProject_no());
			}
		}
		

		if(receivedPayment.getReceive_payment_amount() == 0){
			receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "请输入有效的到款金额 ");
			b = false;
		}

		if(receivedPayment.getReceive_payment_datetime() == null){
			receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "请输入有效的到款日期 ");
			b = false;
		}
		
		if(receivedPayment.getReceivable_accounts_item_name() == null || receivedPayment.getReceivable_accounts_item_name().isEmpty()){
			receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "请输入收款方式 ");
			b = false;
		}else {
			DicData dicData = PubMethod.getObj4Map(EnumDicType.PAYMENT_METHOD.name(),receivedPayment.getReceivable_accounts_item_name(),allDicData);
			if(dicData != null){
				receivedPayment.setReceivable_accounts_item_id(dicData.getId());
				receivedPayment.setReceivable_accounts_item_name(dicData.getDic_data_name());
			}else {
				receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "收款方式错误;");
				b = false;
			}
		}
		
		if(receivedPayment.getInvoice_no() != null && !receivedPayment.getInvoice_no().isEmpty()){
			Invoice invoice = invoiceMap.get(receivedPayment.getInvoice_no());
			if(invoice == null){
				receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "发票号错误 ");
				b = false;
			}else {
				
				if("1".equals(invoice.getIs_received_payment())){
					receivedPayment.setErrorInfo(receivedPayment.getErrorInfo() + "该发票已经到款 ");
					b = false;
				}else {				
					receivedPayment.setInvoice_id(invoice.getInvoice_id());
					receivedPayment.setInvoice_no(invoice.getInvoice_no());
				}
			}
		}
		
		
		if(receivedPayment.getErrorInfo() != null && !receivedPayment.getErrorInfo().isEmpty())
			b = false;		
		return b;
	}	

	@RequestMapping(params = "method=list")
	public String list(ReceivedPayment receivedPayment,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTVIEW.getId());

		paramprocess(request,receivedPayment);
		
		Pager<ReceivedPayment> pager = receivedPaymentService.queryReceivedPayment(receivedPayment, userPermit, PubMethod.getPager(request, ReceivedPayment.class));
		PubMethod.setRequestPager(request, pager,ReceivedPayment.class);	
		
		request.setAttribute("receivedPayment", receivedPayment);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "projectincome/received_payment_list";
	}		
	
	
	

	private void paramprocess(HttpServletRequest request,ReceivedPayment receivedPayment){	
		

		receivedPayment.setDept_id(request.getParameter("dept.dept_id"));
		receivedPayment.setDept_name(request.getParameter("dept.dept_name"));
		
		receivedPayment.setProject_id(request.getParameter("project.project_id"));
		
		if(receivedPayment.getProject_name() == null || receivedPayment.getProject_name().isEmpty())
		receivedPayment.setProject_name(request.getParameter("project.project_name"));

		if(receivedPayment.getProject_no() == null || receivedPayment.getProject_no().isEmpty())
		receivedPayment.setProject_no(request.getParameter("project.project_no"));
		
		
		if(receivedPayment.getPayment_unit() == null || receivedPayment.getPayment_unit().isEmpty())
		receivedPayment.setPayment_unit(request.getParameter("project.project_client_name"));

		if(receivedPayment.getInvoice_id() == null || receivedPayment.getInvoice_id().isEmpty())
		receivedPayment.setInvoice_id(request.getParameter("invoice.invoice_id"));

		if(receivedPayment.getInvoice_no() == null || receivedPayment.getInvoice_no().isEmpty()){
			if(receivedPayment.getInvoice_no() == null || receivedPayment.getInvoice_no().isEmpty())
			receivedPayment.setInvoice_no(request.getParameter("invoice.invoice_no"));
		}

		if(receivedPayment.getReceivable_accounts_item_id() == null || receivedPayment.getReceivable_accounts_item_id().isEmpty())
		receivedPayment.setReceivable_accounts_item_id(request.getParameter("rai.id"));

		if(receivedPayment.getReceivable_accounts_item_name() == null || receivedPayment.getReceivable_accounts_item_name().isEmpty())
		receivedPayment.setReceivable_accounts_item_name(request.getParameter("rai.dic_data_name"));
		
	}	
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(ReceivedPayment searchReceivedPayment,HttpServletResponse res,HttpServletRequest request){
		ReceivedPayment receivedPayment = null;
		if(searchReceivedPayment != null && searchReceivedPayment.getReceive_payment_id()!=null){
			request.setAttribute("next_operation", "updateReceivedPayment");
			receivedPayment = receivedPaymentService.getReceivedPayment(searchReceivedPayment.getReceive_payment_id());	
			if(receivedPayment.getVerify_userid() != null && receivedPayment.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			
		}else {
			request.setAttribute("next_operation", "addReceivedPayment");	
			String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");			
			User sessionUser = PubMethod.getUser(request);
			receivedPayment = new ReceivedPayment();	
			receivedPayment.setReceive_payment_month(Integer.parseInt(month));
			receivedPayment.setBuild_userid(sessionUser.getUser_id());
			receivedPayment.setBuild_username(sessionUser.getUser_name());
			receivedPayment.setBuild_datetime(PubMethod.getCurrentDate());
			
		}

		request.setAttribute("receivedPayment1", receivedPayment);
		return "projectincome/received_payment_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(ReceivedPayment searchReceivedPayment,HttpServletResponse res,HttpServletRequest request){
		
		ReceivedPayment receivedPayment = receivedPaymentService.getReceivedPayment(searchReceivedPayment.getReceive_payment_id());
		request.setAttribute("receivedPayment1", receivedPayment);

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());


		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECEIVEDPAYMENTUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		
		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject(receivedPayment.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.RECEIVED_PAYMENT.name(), receivedPayment.getReceive_payment_id());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.RECEIVED_PAYMENT.name(), receivedPayment.getReceive_payment_id());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", receivedPayment.getVerify_userid());
		request.setAttribute("data_id", receivedPayment.getReceive_payment_id());
		request.setAttribute("data_type", EnumEntityType.RECEIVED_PAYMENT.name());
		
		return "projectincome/received_payment_view";
		
	}	
	

	@RequestMapping(params = "method=addReceivedPayment")
	public String addReceivedPayment(ReceivedPayment addReceivedPayment,HttpServletResponse res,HttpServletRequest request){
		
		ReceivedPayment receivedPayment = addReceivedPayment;	
		paramprocess(request,receivedPayment);

		User sessionUser = PubMethod.getUser(request);
		receivedPayment.setReceive_payment_id(IDKit.generateId());
		receivedPayment.setBuild_datetime(PubMethod.getCurrentDate());
		receivedPayment.setBuild_userid(sessionUser.getUser_id());
		receivedPayment.setBuild_username(sessionUser.getUser_name());

		
		if(StringUtils.isNotEmpty(receivedPayment.getInvoice_id())){
			Invoice invoice = invoiceService.getInvoice(receivedPayment.getInvoice_id());
			receivedPayment.setContract_no(invoice.getContract_no());
			receivedPayment.setContract_id(invoice.getContract_id());
		}
		
		int count = 0;
		try{
			ThreadLocalUser.setUser(sessionUser);
			count = receivedPaymentService.addReceivedPayment(receivedPayment);	
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.RECEIVED_PAYMENT.name(), receivedPayment.getReceive_payment_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "出现错误， 可能是该单据已经存在！", true);
		
	}
	

	

	@RequestMapping(params = "method=updateReceivedPayment")
	public String updateReceivedPayment(ReceivedPayment updateReceivedPayment,HttpServletResponse res,HttpServletRequest request){
		
		ReceivedPayment receivedPayment = updateReceivedPayment;	
		paramprocess(request,receivedPayment);	
		User sessionUser = PubMethod.getUser(request);
		
		if(StringUtils.isNotEmpty(receivedPayment.getInvoice_id())){
			Invoice invoice = invoiceService.getInvoice(receivedPayment.getInvoice_id());
			receivedPayment.setContract_no(invoice.getContract_no());
			receivedPayment.setContract_id(invoice.getContract_id());
		}
		
		int count = 0;
		try{
			ThreadLocalUser.setUser(sessionUser);
			count = receivedPaymentService.updateReceivedPayment(receivedPayment);		
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "出现错误， 可能是该单据已经存在！", true);
		
	}	
	

	@RequestMapping(params = "method=batchVerifyReceivedPayment")
	public String batchVerifyReceivedPayment(HttpServletResponse res,HttpServletRequest request){
		

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "请先选择单据！", false);
		}
		
		User sessionUser = PubMethod.getUser(request);
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			for(String id : ids){
				ReceivedPayment receivedPayment = receivedPaymentService.getReceivedPayment(id);
				receivedPayment.setVerify_datetime(PubMethod.getCurrentDate());
				receivedPayment.setVerify_userid(sessionUser.getUser_id());
				receivedPayment.setVerify_username(sessionUser.getUser_name());
				
				receivedPaymentService.verifyReceivedPayment(receivedPayment);	
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.RECEIVED_PAYMENT.name(), receivedPayment.getReceive_payment_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);				
			}
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}
	

	@RequestMapping(params = "method=verifyReceivedPayment")
	public String verifyReceivedPayment(ReceivedPayment receivedPayment,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		receivedPayment = receivedPaymentService.getReceivedPayment(receivedPayment.getReceive_payment_id());
		receivedPayment.setVerify_datetime(PubMethod.getCurrentDate());
		receivedPayment.setVerify_userid(sessionUser.getUser_id());
		receivedPayment.setVerify_username(sessionUser.getUser_name());
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			receivedPaymentService.verifyReceivedPayment(receivedPayment);
	
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.RECEIVED_PAYMENT.name(), receivedPayment.getReceive_payment_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		return this.ajaxForwardSuccess(request, rel, true);
	}

	@RequestMapping(params = "method=deleteReceivedPayment")
	public String deleteReceivedPayment(HttpServletResponse res,HttpServletRequest request){		
		
		String[] receivedPayment_ids = request.getParameterValues("ids");
		if(receivedPayment_ids != null && receivedPayment_ids.length > 0){
			ReceivedPayment[] receivedPayments = new ReceivedPayment[receivedPayment_ids.length];
			int index = 0;
			for(String receivedPayment_id : receivedPayment_ids){
				ReceivedPayment receivedPayment = new ReceivedPayment();
				receivedPayment.setReceive_payment_id(receivedPayment_id);
				receivedPayments[index] = receivedPayment;
				index ++ ;
			}
			
			if(receivedPayments != null && receivedPayments.length > 0)
			receivedPaymentService.deleteReceivedPayment(receivedPayments);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}		
	
	
}
