package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.*;
import com.pm.domain.system.User;
import com.pm.service.*;
import com.pm.util.PubMethod;
import com.pm.util.constant.*;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("PayContractAction.do")
public class PayContractAction extends BaseAction {

	private static final String sessionAttr = "PayContracts";

	private static final String rel = "rel19";

	@Autowired
	private IProjectService projectService;
	@Autowired
	private IPayContractService payContractService;


	@Autowired
	private IOtherStaffService otherStaffService;

	@Autowired
	private IApplyApproveService applyApproveService;	


	@Autowired
	private IRoleService roleService;


	@RequestMapping(params = "method=isExist")
	public String isExist(PayContract payContract,HttpServletResponse res,HttpServletRequest request){

		String error = null;

		boolean b  = payContractService.isNoExist(payContract);
		if(!b) {
			error = "该合同编号已经存在";
		}



		if(b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}


	@RequestMapping(params = "method=list")
	public String list(PayContract payContract,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PAYCONTRACTVIEW.getId());

		paramprocess(request,payContract);

		Pager<PayContract> pager = payContractService.queryPayContract(payContract, userPermit, PubMethod.getPager(request, PayContract.class));
		PubMethod.setRequestPager(request, pager,PayContract.class);	

		request.setAttribute("payContract", payContract);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PAYCONTRACTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PAYCONTRACTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PAYCONTRACTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		return "basicdata/paycontract_list";
	}


	private void paramprocess(HttpServletRequest request,PayContract payContract){


		payContract.setEffectivedate(PubMethod.twoDate2Str(payContract.getValidity_date1(), payContract.getValidity_date2()));


		if(payContract.getProject_id() == null || payContract.getProject_id().isEmpty()) {
			payContract.setProject_id(request.getParameter("project.project_id"));
		}
		if(payContract.getProject_name() == null || payContract.getProject_name().isEmpty()) {
			payContract.setProject_name(request.getParameter("project.project_name"));
		}

		if(payContract.getProject_no() == null || payContract.getProject_no().isEmpty()) {
			payContract.setProject_no(request.getParameter("project.project_no"));
		}


		if(payContract.getCompany_name() == null || payContract.getCompany_name().isEmpty()) {
			payContract.setCompany_name(request.getParameter("project.project_client_name"));
		}


		payContract.setManager_userid(request.getParameter("manager.user_id"));
		payContract.setManager_username(request.getParameter("manager.user_name"));
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(PayContract searchPayContract,HttpServletResponse res,HttpServletRequest request){
		PayContract payContract = null;
		if(searchPayContract != null && searchPayContract.getId()!=null){
			request.setAttribute("next_operation", "updatePayContract");
			payContract = payContractService.getPayContract(searchPayContract.getId());	

		}else {
			request.setAttribute("next_operation", "addPayContract");		
			User sessionUser = PubMethod.getUser(request);
			payContract = new PayContract();	
			payContract.setBuild_userid(sessionUser.getUser_id());
			payContract.setBuild_username(sessionUser.getUser_name());
			payContract.setBuild_datetime(PubMethod.getCurrentDate());
			String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");
		}
		request.setAttribute("payContract1", payContract);
		return "basicdata/paycontract_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PayContract searchPayContract,HttpServletResponse res,HttpServletRequest request){
		PayContract payContract = payContractService.getPayContract(searchPayContract.getId());
		request.setAttribute("payContract1", payContract);

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( payContract.getProject_id());

		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("data_id", payContract.getId());
		request.setAttribute("data_type", EnumEntityType.PAYCONTRACT.name());
		return "basicdata/paycontract_view";
	}


	@RequestMapping(params = "method=addPayContract")
	public String addPayContract(PayContract addPayContract,HttpServletResponse res,HttpServletRequest request){
		PayContract payContract = addPayContract;	
		paramprocess(request,payContract);
		User sessionUser = PubMethod.getUser(request);
		payContract.setId(IDKit.generateId());
		payContract.setBuild_datetime(PubMethod.getCurrentDate());
		payContract.setBuild_userid(sessionUser.getUser_id());
		payContract.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = payContractService.addPayContract(payContract);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PAYCONTRACT.name(), payContract.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}


	@RequestMapping(params = "method=updatePayContract")
	public String updatePayContract(PayContract updatePayContract,HttpServletResponse res,HttpServletRequest request){
		PayContract payContract = updatePayContract;	
		paramprocess(request,payContract);	
		int count = 0;
		try{
			count = payContractService.updatePayContract(payContract);	
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}	

	@RequestMapping(params = "method=deletePayContract")
	public String deletePayContract(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		Timestamp deleteDate = PubMethod.getCurrentDate();
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			PayContract[] payContracts = new PayContract[ids.length];
			int index = 0;
			for(String id : ids){
				PayContract payContract = new PayContract();
				payContract.setId(id);
				payContracts[index] = payContract;
				index ++ ;
			}
			if(payContracts != null && payContracts.length > 0) {
				payContractService.deletePayContract(payContracts);
			}
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/paycontract.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "付款合同.xlsx" ,response,false);
		return null;  
	}  	


	@RequestMapping(params = "method=export")
	public void export(PayContract payContract,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEVIEW.getId());		
		Pager<PayContract> pager = payContractService.queryPayContract(payContract, userPermit, PubMethod.getPagerByAll(request, PayContract.class));
		try{
			BusinessExcel.export(res, null, pager.getResultList(), PayContract.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	


	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "basicdata/paycontract_upload";
	}	


	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<String[]> list = getExcel(file,10,res,request);
		List<PayContract> payContracts = PubMethod.stringArray2List(list, PayContract.class);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
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


		Pager<OtherStaff> otherStaffs = otherStaffService.queryOtherStaff(new OtherStaff(), userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
		Map<String,OtherStaff>  otherStaffMap = new HashMap<String,OtherStaff>();
		if(otherStaffs.getResultList() != null){
			for(OtherStaff otherStaff : otherStaffs.getResultList()){
				otherStaffMap.put(otherStaff.getStaff_name(), otherStaff);
				otherStaffMap.put(otherStaff.getStaff_no(), otherStaff);
			}
		}

		for(PayContract payContract : payContracts){

			Date[] twoDate = PubMethod.str2TtwoDate(payContract.getEffectivedate());
			payContract.setValidity_date1(twoDate[0]);
			payContract.setValidity_date2(twoDate[1]);

			checkPayContract(payContract,projectMap,otherStaffMap);
		}
		User sessionUser = PubMethod.getUser(request);
		boolean isAllOK = true;
		for(PayContract payContract : payContracts){
			if(payContract.getErrorInfo()==null || payContract.getErrorInfo().length() <= 0){
				try{
					payContract.setId(IDKit.generateId());
					payContract.setBuild_datetime(PubMethod.getCurrentDate());
					payContract.setBuild_userid(sessionUser.getUser_id());
					payContract.setBuild_username(sessionUser.getUser_name());
					int count = payContractService.addPayContract(payContract);
					if(count == 0){
						payContract.setErrorInfo("已经有此记录");
						isAllOK = false;
					}
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key")!=-1 || e.getMessage().indexOf("key")!=-1) {
						payContract.setErrorInfo("已经有此记录");
					}else {
						payContract.setErrorInfo(e.getMessage());
					}
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, payContracts);
			request.setAttribute("forwardUrl", request.getContextPath()+"/PayContractAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
	}	


	private boolean checkPayContract(PayContract payContract,	Map<String,Project>  projectMap , Map<String,OtherStaff>  otherStaffMap){
		boolean b = true;
		if(payContract.getProject_name() == null ||  payContract.getProject_name().isEmpty()){
			payContract.setErrorInfo(payContract.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(payContract.getProject_name().trim());
			if(project == null ){
				payContract.setErrorInfo(payContract.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				payContract.setProject_id(project.getProject_id());
				payContract.setProject_name(project.getProject_name());
				payContract.setProject_no(project.getProject_no());
				payContract.setCompany_name(project.getProject_client_name());
			}
		}


		if(payContract.getManager_username() != null && payContract.getManager_username().length() > 0){
			OtherStaff otherStaff = otherStaffMap.get(payContract.getManager_username());
			if(otherStaff == null) {
				payContract.setErrorInfo(payContract.getErrorInfo() + "负责人错误;");
				b = false;
			}else {
				payContract.setManager_userid(otherStaff.getStaff_id());
				payContract.setManager_username(otherStaff.getStaff_name());
			}
		}


		if(payContract.getContract_no() == null ||  payContract.getContract_no().isEmpty()){
			payContract.setErrorInfo(payContract.getErrorInfo() + "合同编号不能为空;");
			b = false;
		}else {
			boolean isNoExist = payContractService.isNoExist(payContract);
			if(!isNoExist) {
				payContract.setErrorInfo(payContract.getErrorInfo() + "该合同编号已经存在;");
				b = false;
			}
		}





		if(payContract.getErrorInfo() != null && !payContract.getErrorInfo().isEmpty()) {
			b = false;
		}
		return b;
	}


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<PayContract> list = (List<PayContract>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "basicdata/paycontract_excel_list";
	}



}