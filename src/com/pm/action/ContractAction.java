package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.Contract;
import com.pm.domain.business.DicData;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.Project;
import com.pm.domain.system.User;
import com.pm.service.*;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("ContractAction.do")
public class ContractAction extends BaseAction {

	private static final String sessionAttr = "Contracts";

	private static final String rel = "rel15";

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IContractService contractService;

	@Autowired
	private IOtherStaffService otherStaffService;	

	@Autowired
	private IDicDataService dicDataService;


	@Autowired
	private IRoleService roleService;
		

	@RequestMapping(params = "method=isExist")
	public String isExist(Contract contract,HttpServletResponse res,HttpServletRequest request){

		String error = null;
		
		boolean b  = contractService.isContractNoExist(contract);
		if(!b) {
			error = "该合同编号已经存在";
		}
		
		
		
		if(b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}	
	
	
	
	@RequestMapping(params = "method=lookup")
	public String lookup(Contract contract,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.CONTRACTVIEW.getId());
		Pager<Contract> pager = contractService.queryContract(contract, userPermit, PubMethod.getPager(request, Contract.class));
		PubMethod.setRequestPager(request, pager,Contract.class);
		return "basicdata/contract_search";
	}


	@RequestMapping(params = "method=list")
	public String list(Contract contract,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.CONTRACTVIEW.getId());

		Pager<Contract> pager = contractService.queryContract(contract, userPermit, PubMethod.getPager(request, Contract.class));
		PubMethod.setRequestPager(request, pager,Contract.class);	

		request.setAttribute("contract", contract);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CONTRACTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CONTRACTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CONTRACTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		return "basicdata/contract_list";
	}


	private void paramprocess(HttpServletRequest request,Contract contract){
		
		contract.setProject_id(request.getParameter("project.project_id"));
		contract.setProject_name(request.getParameter("project.project_name"));
		contract.setProject_client_name(request.getParameter("project.project_client_name"));
		
		contract.setSales_userid(request.getParameter("salesuser.user_id"));	
		contract.setSales_username(request.getParameter("salesuser.user_name"));
		
		contract.setEffectivedate(PubMethod.twoDate2Str(contract.getValidity_date1(), contract.getValidity_date2()));
		contract.setStorage_addr(request.getParameter("sa.id"));
		contract.setStorage_addr_name(request.getParameter("sa.dic_data_name"));
		
		
	}
	


	@RequestMapping(params = "method=toEdit")
	public String toEdit(Contract searchContract,HttpServletResponse res,HttpServletRequest request){
		Contract contract = null;
		
		if(searchContract != null && searchContract.getId()!=null && searchContract.getId().length() >0){
			request.setAttribute("next_operation", "updateContract");
			contract = contractService.getContract(searchContract.getId());				
		}else {
			request.setAttribute("next_operation", "addContract");		
			User sessionUser = PubMethod.getUser(request);
			contract = new Contract();	
			contract.setBuild_userid(sessionUser.getUser_id());
			contract.setBuild_username(sessionUser.getUser_name());
			contract.setBuild_datetime(PubMethod.getCurrentDate());
		}
		request.setAttribute("contract1", contract);
		return "basicdata/contract_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(Contract searchContract,HttpServletResponse res,HttpServletRequest request){
		Contract contract = contractService.getContract(searchContract.getId());
		request.setAttribute("contract1", contract);
		
		Project project = projectService.getProject( contract.getProject_id());
		request.setAttribute("project", project);
		return "basicdata/contract_view";
	}


	@RequestMapping(params = "method=addContract")
	public String addContract(Contract addContract,HttpServletResponse res,HttpServletRequest request){
		Contract contract = addContract;	
		paramprocess(request,contract);
		User sessionUser = PubMethod.getUser(request);
		contract.setId(IDKit.generateId());
		contract.setBuild_datetime(PubMethod.getCurrentDate());
		contract.setBuild_userid(sessionUser.getUser_id());
		contract.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = contractService.addContract(contract);
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}


	@RequestMapping(params = "method=updateContract")
	public String updateContract(Contract updateContract,HttpServletResponse res,HttpServletRequest request){
		Contract contract = updateContract;	
		paramprocess(request,contract);	
		int count = 0;
		try{
			count = contractService.updateContract(contract);	
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}	




	@RequestMapping(params = "method=deleteContract")
	public String deleteContract(HttpServletResponse res,HttpServletRequest request){
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			Contract[] contracts = new Contract[ids.length];
			int index = 0;
			for(String id : ids){
				Contract contract = new Contract();
				contract.setId(id);
				contracts[index] = contract;
				index ++ ;
			}
			if(contracts != null && contracts.length > 0)
			contractService.deleteContract(contracts);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/contract.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "收款合同模板.xlsx" ,response,false);
		return null;  
	}  	


	@RequestMapping(params = "method=export")
	public void export(Contract contract,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.CONTRACTVIEW.getId());		
		Pager<Contract> pager = contractService.queryContract(contract, userPermit, PubMethod.getPagerByAll(Contract.class));
		if(pager.getResultList() != null){
			for(Contract contract1 : pager.getResultList() ){
				contract1.setMonthly_expenses_str(PubMethod.twoNumber2Str(contract1.getMonthly_expenses(), contract1.getMonthly_expenses2()));
			}
		}
		try{
			BusinessExcel.export(res, null, pager.getResultList(), Contract.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	


	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "basicdata/contract_upload";		
	}	


	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<String[]> list = getExcel(file,13,res,request);
		List<Contract> contracts = PubMethod.stringArray2List(list, Contract.class);
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(Project.class));
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
		

		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		
		
		for(Contract contract : contracts){

			Date[] twoDate = PubMethod.str2TtwoDate(contract.getEffectivedate());
			contract.setValidity_date1(twoDate[0]);
			contract.setValidity_date2(twoDate[1]);	
			
			Double[] ds = PubMethod.str2TtwoNumber(contract.getMonthly_expenses_str());
			contract.setMonthly_expenses(ds[0]);
			contract.setMonthly_expenses2(ds[1]);			
			
			checkContract(contract,projectMap,otherStaffMap,allDicData);
		}
		
		User sessionUser = PubMethod.getUser(request);
		boolean isAllOK = true;
		for(Contract contract : contracts){
			if(contract.getErrorInfo()==null || contract.getErrorInfo().length() <= 0){
				try{
					contract.setId(IDKit.generateId());
					contract.setBuild_datetime(PubMethod.getCurrentDate());
					contract.setBuild_userid(sessionUser.getUser_id());
					contract.setBuild_username(sessionUser.getUser_name());
					int count = contractService.addContract(contract);
					if(count == 0){
						contract.setErrorInfo("已经有此记录");
						isAllOK = false;
					}
				}catch(Exception e){
					contract.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, contracts);
			request.setAttribute("forwardUrl", request.getContextPath()+"/ContractAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
	}	


	private boolean checkContract(
			Contract contract,	
			Map<String,Project>  projectMap , 
			Map<String,OtherStaff>  otherStaffMap,
			Map<String, Map<String, DicData>> allDicData){
		boolean b = true;
		if(contract.getProject_name() == null ||  contract.getProject_name().isEmpty()){
			contract.setErrorInfo(contract.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(contract.getProject_name().trim());
			if(project == null ){
				contract.setErrorInfo(contract.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				contract.setProject_id(project.getProject_id());
				contract.setProject_name(project.getProject_name());
				contract.setProject_no(project.getProject_no());
			}
		}
		
		
		if(contract.getStorage_addr_name() != null && !contract.getStorage_addr_name().isEmpty()){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.CONTRACT_STORAGE_ADDR.name(),contract.getStorage_addr_name(),allDicData);
			if(dicData != null){
				contract.setStorage_addr(dicData.getId());
			}else {
				contract.setErrorInfo(contract.getErrorInfo() + "合同存放地错误;");
				b = false;
			}
		}
		
		if(contract.getSales_username() != null && contract.getSales_username().length() > 0){
			OtherStaff otherStaff = otherStaffMap.get(contract.getSales_username());
			if(otherStaff == null) {
				contract.setErrorInfo(contract.getErrorInfo() + "销售负责人错误;");
				b = false;
			}else {
				contract.setSales_userid(otherStaff.getStaff_id());
				contract.setSales_username(otherStaff.getStaff_name());
			}
		}
		


		if(contract.getContract_no() == null ||  contract.getContract_no().isEmpty()){
			contract.setErrorInfo(contract.getErrorInfo() + "合同编号不能为空;");
			b = false;
		}else {
			boolean isExist = contractService.isContractNoExist(contract);
			if(isExist) {
				contract.setErrorInfo(contract.getErrorInfo() + "该合同编号已经存在;");
				b = false;
			}
		}
		
		if(contract.getErrorInfo() != null && !contract.getErrorInfo().isEmpty()) {
			b = false;
		}
		return b;
	}


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<Contract> list = (List<Contract>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "basicdata/contract_excel_list";
	}



}