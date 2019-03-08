package com.pm.action;

import java.sql.Timestamp;
import java.util.Date;
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
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.DicData;
import com.pm.domain.business.Project;
import com.pm.domain.business.ReimburseCosts;
import com.pm.domain.business.StaffCost;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IDeptService;
import com.pm.service.IDicDataService;
import com.pm.service.IProjectService;
import com.pm.service.IReimburseCostsService;
import com.pm.service.IRoleService;
import com.pm.service.IStaffCostService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.imports.ExcelRead;
import com.pm.vo.UserPermit;

/**
 * 报销记录
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "ReimburseCostsAction.do")
public class ReimburseCostsAction extends BaseAction {
	

	private static final String sessionAttr = "ReimburseCostss";

	private static final String rel = "rel22";
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IReimburseCostsService reimburseCostsService;

	@Autowired
	private IApplyApproveService applyApproveService;	
	

	@Autowired
	private IDicDataService dicDataService;

	@Autowired
	IDeptService deptService;
	

	@Autowired
	private IStaffCostService staffCostService;
	
	@Autowired
	private IRoleService roleService;
	

	@RequestMapping(params = "method=list")
	public String list(ReimburseCosts reimburseCosts,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEVIEW.getId());

		paramprocess(request,reimburseCosts);
		

		if(reimburseCosts.getDept_id() == null || reimburseCosts.getDept_id().isEmpty())
		reimburseCosts.setDept_id(request.getParameter("dept.dept_id"));		
		
		if(reimburseCosts.getDept_name() == null || reimburseCosts.getDept_name().isEmpty())
		reimburseCosts.setDept_name(request.getParameter("dept.dept_name"));	
		
		Pager<ReimburseCosts> pager = reimburseCostsService.queryReimburseCosts(reimburseCosts, userPermit, PubMethod.getPager(request, ReimburseCosts.class));
		PubMethod.setRequestPager(request, pager,ReimburseCosts.class);	
		
		request.setAttribute("reimburseCosts", reimburseCosts);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());	
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.REIMBURSECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "projectcosts/reimburse_costs_list";
	}		
	

	private void paramprocess(HttpServletRequest request,ReimburseCosts reimburseCosts){
		reimburseCosts.setProject_id(request.getParameter("project.project_id"));	
		
		if(reimburseCosts.getProject_name() == null || reimburseCosts.getProject_name().isEmpty() )
		reimburseCosts.setProject_name(request.getParameter("project.project_name"));		
		reimburseCosts.setProject_no(request.getParameter("project.project_no"));
		
		reimburseCosts.setStaff_id(request.getParameter("staff.staff_id"));		
		if(reimburseCosts.getStaff_name() == null || reimburseCosts.getStaff_name().isEmpty() )
		reimburseCosts.setStaff_name(request.getParameter("staff.staff_name"));		
		reimburseCosts.setStaff_no(request.getParameter("staff.staff_no"));
		
		if(reimburseCosts.getStaff_id() != null && reimburseCosts.getStaff_id().length()>0) {
			StaffCost staffCost = staffCostService.getStaffCost(reimburseCosts.getStaff_id());
			if (staffCost != null){
				if (staffCost.getStaff_name().equals(reimburseCosts.getStaff_name())) {
					reimburseCosts.setStaff_no(staffCost.getStaff_no());
				} else {
					StaffCost staffCost1 = staffCostService.getStaffCostByName(reimburseCosts.getStaff_name());
					if (staffCost1 == null) {
						reimburseCosts.setStaff_id(reimburseCosts.getStaff_name());
						reimburseCosts.setStaff_no(null);
					} else {
						reimburseCosts.setStaff_id(staffCost1.getStaff_id());
						reimburseCosts.setStaff_no(staffCost1.getStaff_no());
					}
				}
			}else {

			}
		
		}else {
			StaffCost staffCost1 = staffCostService.getStaffCostByName(reimburseCosts.getStaff_name());
			if(staffCost1 == null) {
				reimburseCosts.setStaff_id(reimburseCosts.getStaff_name());	
				reimburseCosts.setStaff_no(null);
			}else {
				reimburseCosts.setStaff_id(staffCost1.getStaff_id());	
				reimburseCosts.setStaff_no(staffCost1.getStaff_no());
			}
		}
		
		
		
		reimburseCosts.setPay_item_id(request.getParameter("rai.id"));
		reimburseCosts.setPay_item_name(request.getParameter("rai.dic_data_name"));
		
			
	}	
	
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "projectcosts/reimburse_costs_upload";		
	}	
	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/reimbursecost.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "报销模板.xlsx" ,response,false);
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
			if(row.length<7) return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			index ++;
		}
		
		List<ReimburseCosts> reimburseCostss = PubMethod.stringArray2List(list, ReimburseCosts.class);
		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);		

		

		//查找报销类别
		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		

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
		


		
		Pager<StaffCost> staffCosts = staffCostService.queryStaffCost(new StaffCost(), null, userPermit, PubMethod.getPagerByAll(StaffCost.class));
		Map<String,StaffCost>  staffCostMap = new HashMap<String,StaffCost>();
		if(staffCosts.getResultList() != null) {
			for(StaffCost staffCost : staffCosts.getResultList()){
				staffCostMap.put(staffCost.getStaff_name(), staffCost);	
				staffCostMap.put(staffCost.getStaff_no(), staffCost);		
			}
		}
		
		for(ReimburseCosts reimburseCosts : reimburseCostss){
			boolean b = checkReimburseCosts(reimburseCosts,allDicData,projectMap,staffCostMap);
		}
		
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		index = 0;
		for(ReimburseCosts reimburseCosts : reimburseCostss){
			if(reimburseCosts.getErrorInfo()==null || reimburseCosts.getErrorInfo().length() <= 0){
				try{
					reimburseCosts.setImport_order(index);
					reimburseCosts.setReimburse_id(IDKit.generateId());

					reimburseCosts.setBuild_datetime(PubMethod.getCurrentDate());
					reimburseCosts.setBuild_userid(sessionUser.getUser_id());
					reimburseCosts.setBuild_username(sessionUser.getUser_name());
					reimburseCosts.setDelete_flag(BusinessUtil.NOT_DELETEED);
					
					reimburseCostsService.addReimburseCosts(reimburseCosts);					

					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.REIMBURSE_COSTS.name(), reimburseCosts.getReimburse_id(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
					
					
					index ++;
				}catch(Exception e){
					reimburseCosts.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, reimburseCostss);
			request.setAttribute("forwardUrl", request.getContextPath()+"/ReimburseCostsAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的报销信息中有些问题！ ");
		}
		
	}	
	
	

	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<ReimburseCosts> list = (List<ReimburseCosts>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "projectcosts/reimburse_costs_excel_list";
	}

	private boolean checkReimburseCosts(ReimburseCosts reimburseCosts,
			Map<String, Map<String, DicData>> allDicData,
			Map<String,Project>  projectMap,
			Map<String,StaffCost>  staffCostMap){
		boolean b = true;
		
		Date date1 = null;
		if(reimburseCosts.getStr_month() == null ||  reimburseCosts.getStr_month().isEmpty()){
			reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "报销月份不能为空;");
			b = false;
		}else {
			if(reimburseCosts.getStr_month().length() != 6) {
				reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "报销月份格式错误;");
				b = false;
			}else{
				date1 = DateKit.fmtStrToDate(reimburseCosts.getStr_month()+"01","yyyyMMdd");
				if(date1 == null){
					reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "报销月份格式错误;");
					b = false;
				}else {
					reimburseCosts.setUse_month(Integer.parseInt(reimburseCosts.getStr_month()));
				}
			}
		}
		

		if(reimburseCosts.getPay_date() == null){
			reimburseCosts.setPay_date(new Timestamp(date1.getTime()));
		}
		
		if(reimburseCosts.getAmount() == 0){
			reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "报销金额不能为空;");
			b = false;
		}
		
		if(reimburseCosts.getProject_name() == null ||  reimburseCosts.getProject_name().isEmpty()){
			reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(reimburseCosts.getProject_name().trim());
			if(project == null ){
				reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				reimburseCosts.setProject_id(project.getProject_id());
				reimburseCosts.setProject_name(project.getProject_name());
				reimburseCosts.setProject_no(project.getProject_no());
				
				reimburseCosts.setDept_id(project.getDeptid());
				reimburseCosts.setDept_name(project.getDeptname());
			}
		}
		
		if(reimburseCosts.getPay_item_name() == null ||  reimburseCosts.getPay_item_name().isEmpty()){
			reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "报销类别不能为空;");
			b = false;
		}else {

			DicData dicData = PubMethod.getObj4Map(EnumDicType.REIMBURSE_ITEM.name(),reimburseCosts.getPay_item_name() ,allDicData);
			if(dicData != null){
				reimburseCosts.setPay_item_id(dicData.getId());
				reimburseCosts.setPay_item_name(dicData.getDic_data_name());
			}else {
				reimburseCosts.setErrorInfo(reimburseCosts.getErrorInfo() + "报销类别错误;");
				b = false;
			}
		
			
		}
		
				
		if(reimburseCosts.getStaff_name() != null && reimburseCosts.getStaff_name().length() > 0){
			StaffCost staffCost = staffCostMap.get(reimburseCosts.getStaff_name());
			if(staffCost == null){
				reimburseCosts.setStaff_id(reimburseCosts.getStaff_name());
				reimburseCosts.setStaff_name(reimburseCosts.getStaff_name());
			}else {
				reimburseCosts.setStaff_id(staffCost.getStaff_id());
				reimburseCosts.setStaff_name(staffCost.getStaff_name());
				reimburseCosts.setStaff_no(staffCost.getStaff_no());
			}
		}
		
		
		
		
		if(reimburseCosts.getErrorInfo() != null && !reimburseCosts.getErrorInfo().isEmpty()) {
			b = false;
		}
		
		return b;
	}
	
	
	/**
	 * 导出Excel(普通方式导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(ReimburseCosts reimburseCosts,HttpServletResponse res,HttpServletRequest request){
		

		paramprocess(request,reimburseCosts);
		

		if(reimburseCosts.getDept_id() == null || reimburseCosts.getDept_id().isEmpty())
		reimburseCosts.setDept_id(request.getParameter("dept.dept_id"));		
		
		if(reimburseCosts.getDept_name() == null || reimburseCosts.getDept_name().isEmpty())
		reimburseCosts.setDept_name(request.getParameter("dept.dept_name"));	
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEVIEW.getId());		

		Pager<ReimburseCosts> pager = reimburseCostsService.queryReimburseCosts(reimburseCosts, userPermit, PubMethod.getPagerByAll(request, ReimburseCosts.class));
				

		
		
		if(pager.getResultList() != null){
			for(ReimburseCosts temp : pager.getResultList()){
				temp.setStr_month(String.valueOf(temp.getUse_month()));
			}
		}		
		
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), ReimburseCosts.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(ReimburseCosts searchReimburseCosts,HttpServletResponse res,HttpServletRequest request){
		ReimburseCosts reimburseCosts = null;
		if(searchReimburseCosts != null && searchReimburseCosts.getReimburse_id()!=null){
			request.setAttribute("next_operation", "updateReimburseCosts");
			reimburseCosts = reimburseCostsService.getReimburseCosts(searchReimburseCosts.getReimburse_id());	
			if(reimburseCosts.getVerify_userid() != null && reimburseCosts.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			
			
		}else {
			request.setAttribute("next_operation", "addReimburseCosts");
			
			User sessionUser = PubMethod.getUser(request);
			reimburseCosts = new ReimburseCosts();	
			reimburseCosts.setBuild_userid(sessionUser.getUser_id());
			reimburseCosts.setBuild_username(sessionUser.getUser_name());
			reimburseCosts.setBuild_datetime(PubMethod.getCurrentDate());

			String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");
			reimburseCosts.setUse_month(Integer.parseInt(month));
			
		}

		request.setAttribute("reimburseCosts1", reimburseCosts);
		return "projectcosts/reimburse_costs_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(ReimburseCosts searchReimburseCosts,HttpServletResponse res,HttpServletRequest request){
		
		ReimburseCosts reimburseCosts = reimburseCostsService.getReimburseCosts(searchReimburseCosts.getReimburse_id());
		request.setAttribute("reimburseCosts1", reimburseCosts);

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.REIMBURSECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( reimburseCosts.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.REIMBURSE_COSTS.name(), reimburseCosts.getReimburse_id());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.REIMBURSE_COSTS.name(),   reimburseCosts.getReimburse_id());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", reimburseCosts.getVerify_userid());
		request.setAttribute("data_id",  reimburseCosts.getReimburse_id());
		request.setAttribute("data_type", EnumEntityType.REIMBURSE_COSTS.name());			
		
		return "projectcosts/reimburse_costs_view";
		
	}	
	

	@RequestMapping(params = "method=addReimburseCosts")
	public String addReimburseCosts(ReimburseCosts addReimburseCosts,HttpServletResponse res,HttpServletRequest request){
		
		ReimburseCosts reimburseCosts = addReimburseCosts;	
		paramprocess(request,reimburseCosts);

		if(reimburseCosts.getDept_id() == null || reimburseCosts.getDept_id().isEmpty())
		reimburseCosts.setDept_id(request.getParameter("project.deptid"));		
		
		if(reimburseCosts.getDept_name() == null || reimburseCosts.getDept_name().isEmpty())
		reimburseCosts.setDept_name(request.getParameter("project.deptname"));	
		
		
		String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");

		User sessionUser = PubMethod.getUser(request);
		reimburseCosts.setReimburse_id(IDKit.generateId());
		reimburseCosts.setBuild_datetime(PubMethod.getCurrentDate());
		reimburseCosts.setBuild_userid(sessionUser.getUser_id());
		reimburseCosts.setBuild_username(sessionUser.getUser_name());
		reimburseCosts.setDelete_flag(BusinessUtil.NOT_DELETEED);	
		

		int count = 0;
		try{
			count = reimburseCostsService.addReimburseCosts(reimburseCosts);		

			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.REIMBURSE_COSTS.name(), reimburseCosts.getReimburse_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	

	

	@RequestMapping(params = "method=updateReimburseCosts")
	public String updateReimburseCosts(ReimburseCosts updateReimburseCosts,HttpServletResponse res,HttpServletRequest request){
		
		ReimburseCosts reimburseCosts = updateReimburseCosts;	
		paramprocess(request,reimburseCosts);		
		

		if(reimburseCosts.getDept_id() == null || reimburseCosts.getDept_id().isEmpty())
		reimburseCosts.setDept_id(request.getParameter("project.deptid"));		
		
		if(reimburseCosts.getDept_name() == null || reimburseCosts.getDept_name().isEmpty())
		reimburseCosts.setDept_name(request.getParameter("project.deptname"));	
		
		int count = 0;
		try{
			count = reimburseCostsService.updateReimburseCosts(reimburseCosts);			
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}	
	
	


	@RequestMapping(params = "method=batchVerifyReimburseCosts")
	public String batchVerifyReimburseCosts(HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);	
		
		String[] reimburseCosts_ids = request.getParameterValues("ids");
		if(reimburseCosts_ids == null || reimburseCosts_ids.length ==0){
			return this.ajaxForwardError(request, "请先选择报销单！", false);
		}
		
		for(String reimburseCosts_id : reimburseCosts_ids){
			ReimburseCosts reimburseCosts = new ReimburseCosts();
			reimburseCosts.setReimburse_id(reimburseCosts_id);
			reimburseCosts.setVerify_datetime(PubMethod.getCurrentDate());
			reimburseCosts.setVerify_userid(sessionUser.getUser_id());
			reimburseCosts.setVerify_username(sessionUser.getUser_name());
			reimburseCostsService.verifyReimburseCosts(reimburseCosts);	
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.REIMBURSE_COSTS.name(), reimburseCosts.getReimburse_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}

		return this.ajaxForwardSuccess(request,rel,false);
		
	}

	@RequestMapping(params = "method=verifyReimburseCosts")
	public String verifyReimburseCosts(ReimburseCosts reimburseCosts,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		reimburseCosts.setVerify_datetime(PubMethod.getCurrentDate());
		reimburseCosts.setVerify_userid(sessionUser.getUser_id());
		reimburseCosts.setVerify_username(sessionUser.getUser_name());
		reimburseCostsService.verifyReimburseCosts(reimburseCosts);	
		ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.REIMBURSE_COSTS.name(), reimburseCosts.getReimburse_id(), sessionUser);
		applyApproveService.addApplyApprove(applyApprove);
		return this.ajaxForwardSuccess(request, rel, true);
	}

	@RequestMapping(params = "method=deleteReimburseCosts")
	public String deleteReimburseCosts(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();		
		
		String[] reimburseCosts_ids = request.getParameterValues("ids");
		if(reimburseCosts_ids != null && reimburseCosts_ids.length > 0){
			ReimburseCosts[] reimburseCostss = new ReimburseCosts[reimburseCosts_ids.length];
			int index = 0;
			for(String reimburseCosts_id : reimburseCosts_ids){
				ReimburseCosts reimburseCosts = new ReimburseCosts();
				reimburseCosts.setReimburse_id(reimburseCosts_id);
				reimburseCosts.setDelete_userid(sessionUser.getUser_id());
				reimburseCosts.setDelete_username(sessionUser.getUser_name());
				reimburseCosts.setDelete_datetime(deleteDate);
				reimburseCostss[index] = reimburseCosts;
				index ++ ;
			}
			
			if(reimburseCostss != null && reimburseCostss.length > 0)
			reimburseCostsService.deleteReimburseCosts(reimburseCostss);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}		
	
	
	

}
