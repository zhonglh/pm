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
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectExpend;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IProjectExpendService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.UserPermit;

@Controller
@RequestMapping("ProjectExpendAction.do")
public class ProjectExpendAction extends BaseAction {
	

	private static final String sessionAttr = "ProjectExpends";
	

	private static final String rel = "rel23";
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IProjectExpendService projectExpendService;
	

	@Autowired
	private IApplyApproveService applyApproveService;	
	
	@Autowired
	private IRoleService roleService;
	
	
	private static Map<String,String> ObjMap = new HashMap<String,String>();

	
	static{
		ObjMap.put("公司","1");
		ObjMap.put("个人","2");
		ObjMap.put("1","公司");
		ObjMap.put("2","个人");
	}

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "projectcosts/project_expend_upload";		
	}	
	
	
	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/projectexpend.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "付款信息模板.xlsx" ,response,false);
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
		
		
		List<String[]> list = getExcel(file,8,res,request);
		
		List<ProjectExpend> projectExpends = PubMethod.stringArray2List(list, ProjectExpend.class);
		
		
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
		

		
		for(ProjectExpend projectExpend : projectExpends){
			 checkProjectExpend(projectExpend,projectMap);
		}
		
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		for(ProjectExpend projectExpend : projectExpends){
			if(projectExpend.getErrorInfo()==null || projectExpend.getErrorInfo().length() <= 0){
				try{
					projectExpend.setProject_expend_id(IDKit.generateId());

					projectExpend.setBuild_datetime(PubMethod.getCurrentDate());
					projectExpend.setBuild_userid(sessionUser.getUser_id());
					projectExpend.setBuild_username(sessionUser.getUser_name());
					projectExpend.setDelete_flag(BusinessUtil.NOT_DELETEED);								
					
					int count = projectExpendService.addProjectExpend(projectExpend);
					if(count == 0){
						projectExpend.setErrorInfo("已经有此付款信息记录");
						isAllOK = false;
						continue;
					}
					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PROJECT_EXPEND.name(), projectExpend.getProject_expend_id(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key_2")!=-1 || e.getMessage().indexOf("key 2")!=-1) 
						projectExpend.setErrorInfo("已经有此付款信息记录");
					else projectExpend.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, projectExpends);
			request.setAttribute("forwardUrl", request.getContextPath()+"/ProjectExpendAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的付款信息中有些问题！ ");
		}
		
	}	
	
	

	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<ProjectExpend> list = (List<ProjectExpend>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "projectcosts/project_expend_excel_list";
	}

	private boolean checkProjectExpend(ProjectExpend projectExpend,
			Map<String,Project>  projectMap){
		boolean b = true;
		
		Date date1 = null;
		if(projectExpend.getStr_month() == null ||  projectExpend.getStr_month().isEmpty()){
			projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "应付月份不能为空;");
			b = false;
		}else {
			if(projectExpend.getStr_month().length() != 6) {
				projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "应付月份格式错误;");
				b = false;
			}else{
				date1 = DateKit.fmtStrToDate(projectExpend.getStr_month()+"01","yyyyMMdd");
				if(date1 == null){
					projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "应付月份格式错误;");
					b = false;
				}else {
					projectExpend.setUse_month(Integer.parseInt(projectExpend.getStr_month()));
				}
			}
		}
		
		if(projectExpend.getPay_date() == null){
			//projectExpend.setPay_date(new Timestamp(date1.getTime()));
		}
		
		if(projectExpend.getAmount() <= 0){
			projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "应付金额不能为空;");
			b = false;
		}
		
		if(projectExpend.getProject_name() == null ||  projectExpend.getProject_name().isEmpty()){
			projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(projectExpend.getProject_name().trim());
			if(project == null ){
				projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				projectExpend.setProject_id(project.getProject_id());
				projectExpend.setProject_name(project.getProject_name());
				projectExpend.setProject_no(project.getProject_no());
			}
		}
		
		
		if(projectExpend.getExpend_object() == null || projectExpend.getExpend_object().isEmpty()){
			projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "付款信息对象不能为空;");
			b = false;
		}else {
			String object = ObjMap.get(projectExpend.getExpend_object());
			if(object == null){
				projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "付款信息对象错误;");
				b = false;
			}else {
				projectExpend.setExpend_object(object);
			}
		}
		
		
		if(projectExpend.getSub_contractor_name() == null || projectExpend.getSub_contractor_name().isEmpty()){
			projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "分包商名称不能为空;");
			b = false;
		}

		
		if(projectExpend.getSub_contractor_no() == null || projectExpend.getSub_contractor_no().isEmpty()){
			projectExpend.setErrorInfo(projectExpend.getErrorInfo() + "分包合同编号不能为空;");
			b = false;
		}
		
		
		if(projectExpend.getErrorInfo() != null && !projectExpend.getErrorInfo().isEmpty())
			b = false;
		
		return b;
	}
	
	
	/**
	 * 导出Excel(普通方式导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(ProjectExpend projectExpend,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEVIEW.getId());	
		

		paramprocess(request,projectExpend);

		Pager<ProjectExpend> pager = projectExpendService.queryProjectExpend(projectExpend, userPermit, PubMethod.getPagerByAll(request, ProjectExpend.class));
				

		if(pager.getResultList() != null){
			for(ProjectExpend temp : pager.getResultList()){
				temp.setStr_month(String.valueOf(temp.getUse_month()));
				if("1".equals(temp.getExpend_object())) temp.setExpend_object("公司");
				else if("2".equals(temp.getExpend_object())) temp.setExpend_object("个人");
			}
		}		
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), ProjectExpend.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	

	@RequestMapping(params = "method=list")
	public String list(ProjectExpend projectExpend,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDVIEW.getId());

		paramprocess(request,projectExpend);
		
		Pager<ProjectExpend> pager = projectExpendService.queryProjectExpend(projectExpend, userPermit, PubMethod.getPager(request, ProjectExpend.class));
		PubMethod.setRequestPager(request, pager,ProjectExpend.class);	
		
		request.setAttribute("projectExpend", projectExpend);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());	
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "projectcosts/project_expend_list";
	}		
	

	private void paramprocess(HttpServletRequest request,ProjectExpend projectExpend){		
		projectExpend.setProject_id(request.getParameter("project.project_id"));
		
		if(projectExpend.getProject_name() == null || projectExpend.getProject_name().isEmpty())
		projectExpend.setProject_name(request.getParameter("project.project_name"));
		

		if(projectExpend.getProject_no() == null || projectExpend.getProject_no().isEmpty())
		projectExpend.setProject_no(request.getParameter("project.project_no"));
		
		
		

		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			projectExpend.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			projectExpend.setDept_name(dept_name);
		}
	}	
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(ProjectExpend searchProjectExpend,HttpServletResponse res,HttpServletRequest request){
		ProjectExpend projectExpend = null;
		if(searchProjectExpend != null && searchProjectExpend.getProject_expend_id()!=null){
			request.setAttribute("next_operation", "updateProjectExpend");
			projectExpend = projectExpendService.getProjectExpend(searchProjectExpend.getProject_expend_id());	
			if(projectExpend.getVerify_userid() != null && projectExpend.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			
			
		}else {
			request.setAttribute("next_operation", "addProjectExpend");		

			User sessionUser = PubMethod.getUser(request);
			projectExpend = new ProjectExpend();	
			projectExpend.setBuild_userid(sessionUser.getUser_id());
			projectExpend.setBuild_username(sessionUser.getUser_name());
			projectExpend.setBuild_datetime(PubMethod.getCurrentDate());

			String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");
			projectExpend.setUse_month(Integer.parseInt(month));
			
		}

		request.setAttribute("projectExpend1", projectExpend);
		return "projectcosts/project_expend_edit";
		
	}
	

	

	@RequestMapping(params = "method=toPay")
	public String toPay(ProjectExpend searchProjectExpend,HttpServletResponse res,HttpServletRequest request){
		
		ProjectExpend projectExpend = null;
		request.setAttribute("next_operation", "doPay");
		projectExpend = projectExpendService.getProjectExpend(searchProjectExpend.getProject_expend_id());	
		
		request.setAttribute("projectExpend1", projectExpend);
		return "projectcosts/project_expend_pay";
		
	}

	
	
	@RequestMapping(params = "method=toView")
	public String toView(ProjectExpend searchProjectExpend,HttpServletResponse res,HttpServletRequest request){
		
		ProjectExpend projectExpend = projectExpendService.getProjectExpend(searchProjectExpend.getProject_expend_id());
		request.setAttribute("projectExpend1", projectExpend);

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( projectExpend.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PROJECT_EXPEND.name(), projectExpend.getProject_expend_id());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PROJECT_EXPEND.name(),  projectExpend.getProject_expend_id());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", projectExpend.getVerify_userid());
		request.setAttribute("data_id", projectExpend.getProject_expend_id());
		request.setAttribute("data_type", EnumEntityType.PROJECT_EXPEND.name());		
		
		return "projectcosts/project_expend_view";
		
	}	
	

	@RequestMapping(params = "method=addProjectExpend")
	public String addProjectExpend(ProjectExpend addProjectExpend,HttpServletResponse res,HttpServletRequest request){
		
		ProjectExpend projectExpend = addProjectExpend;	
		paramprocess(request,projectExpend);

		User sessionUser = PubMethod.getUser(request);
		projectExpend.setProject_expend_id(IDKit.generateId());
		projectExpend.setBuild_datetime(PubMethod.getCurrentDate());
		projectExpend.setBuild_userid(sessionUser.getUser_id());
		projectExpend.setBuild_username(sessionUser.getUser_name());
		projectExpend.setDelete_flag(BusinessUtil.NOT_DELETEED);	
		int count = 0;
		try{
			count = projectExpendService.addProjectExpend(projectExpend);	

			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PROJECT_EXPEND.name(), projectExpend.getProject_expend_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
		
	}
	

	

	@RequestMapping(params = "method=updateProjectExpend")
	public String updateProjectExpend(ProjectExpend updateProjectExpend,HttpServletResponse res,HttpServletRequest request){
		
		ProjectExpend projectExpend = updateProjectExpend;	
		paramprocess(request,projectExpend);			
		
		int count = 0;
		try{
			count = projectExpendService.updateProjectExpend(projectExpend);		
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
		
		
	}	
	
	

	@RequestMapping(params = "method=doPay")
	public String doPay(ProjectExpend updateProjectExpend,HttpServletResponse res,HttpServletRequest request){
		
		ProjectExpend projectExpend = projectExpendService.getProjectExpend(updateProjectExpend.getProject_expend_id());	
		
		
		paramprocess(request,projectExpend);	
		
		projectExpend.setInvoiceno(updateProjectExpend.getInvoiceno());
		projectExpend.setPay_amount(updateProjectExpend.getPay_amount());
		projectExpend.setPay_date(updateProjectExpend.getPay_date());
		
		int count = 0;
		try{
			count = projectExpendService.doPay(projectExpend);		
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
		
		
	}		
	
	
	

	@RequestMapping(params = "method=batchVerifyProjectExpend")
	public String batchVerifyProjectExpend(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
			
		
		String[] projectExpend_ids = request.getParameterValues("ids");
		if(projectExpend_ids == null || projectExpend_ids.length == 0){
			this.ajaxForwardError(request, "请先选择单据！", false);
		}
			
		for(String id : projectExpend_ids){
			ProjectExpend projectExpend = new ProjectExpend();
			projectExpend.setVerify_datetime(PubMethod.getCurrentDate());
			projectExpend.setVerify_userid(sessionUser.getUser_id());
			projectExpend.setVerify_username(sessionUser.getUser_name());
			projectExpend.setProject_expend_id(id);
			projectExpendService.verifyProjectExpend(projectExpend);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.PROJECT_EXPEND.name(), projectExpend.getProject_expend_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}

	@RequestMapping(params = "method=verifyProjectExpend")
	public String verifyProjectExpend(ProjectExpend projectExpend,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		projectExpend.setVerify_datetime(PubMethod.getCurrentDate());
		projectExpend.setVerify_userid(sessionUser.getUser_id());
		projectExpend.setVerify_username(sessionUser.getUser_name());
		projectExpendService.verifyProjectExpend(projectExpend);
		ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.PROJECT_EXPEND.name(), projectExpend.getProject_expend_id(), sessionUser);
		applyApproveService.addApplyApprove(applyApprove);
		return this.ajaxForwardSuccess(request, rel, true);
	}

	@RequestMapping(params = "method=deleteProjectExpend")
	public String deleteProjectExpend(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();		
		
		String[] projectExpend_ids = request.getParameterValues("ids");
		if(projectExpend_ids != null && projectExpend_ids.length > 0){
			ProjectExpend[] projectExpends = new ProjectExpend[projectExpend_ids.length];
			int index = 0;
			for(String projectExpend_id : projectExpend_ids){
				ProjectExpend projectExpend = new ProjectExpend();
				projectExpend.setProject_expend_id(projectExpend_id);
				projectExpend.setDelete_userid(sessionUser.getUser_id());
				projectExpend.setDelete_username(sessionUser.getUser_name());
				projectExpend.setDelete_datetime(deleteDate);
				projectExpends[index] = projectExpend;
				index ++ ;
			}
			
			if(projectExpends != null && projectExpends.length > 0)
			projectExpendService.deleteProjectExpend(projectExpends);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}		
	
	
	

}
