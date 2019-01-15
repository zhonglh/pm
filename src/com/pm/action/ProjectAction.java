package com.pm.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.Base64Kit;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.pm.domain.business.Contract;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectContract;
import com.pm.domain.business.ProjectExpend;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.system.User;
import com.pm.service.IContractService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.constant.EnumProjectType;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "ProjectAction.do")
public class ProjectAction extends BaseAction {

	

	private static final String rel = "rel11";
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IRoleService roleService;
	

	@Autowired
	private IContractService contractService;
	
	

	@RequestMapping(params = "method=isExist")
	public String isExist(HttpServletResponse res,HttpServletRequest request){

		String error = null;
		Project project = new Project();
		project.setProject_name(request.getParameter("project_name"));
		project.setProject_id(request.getParameter("project_id"));		
		
		boolean b = projectService.isExist(project);
		if(!b){
			project.setProject_no(request.getParameter("project_no"));
			project.setProject_name(null);
			b = projectService.isExist(project);
			if(b) error = "该项目编号已经存在";
		}else {
			error = "该项目名称已经存在";
		}
		
		
		if(!b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}	
	

	
	/**
	 * 导出Excel(普通方式导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(Project searchProject,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTVIEW.getId());		

		paramprocess(request,searchProject);

		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		
		Pager<Project> pager = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		
		
		if(pager.getResultList() != null){
			for(Project project : pager.getResultList() ){
				project.setProject_type(this.getMsg("project.type."+project.getProject_type(), request));
			}
		}
	
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), Project.class,true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	
	@RequestMapping(params = "method=lookup")
	public String lookup(Project searchProject,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTVIEW.getId());		

		paramprocess(request,searchProject);
		
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		
		Pager<Project> pager = projectService.queryProject(searchProject, userPermit, PubMethod.getPager(request, Project.class));
		PubMethod.setRequestPager(request, pager,Project.class);
		

		return "basicdata/project_data_search";
	}
	

	@RequestMapping(params = "method=list")
	public String list(Project searchProject,HttpServletResponse res,HttpServletRequest request){
		
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTVIEW.getId());		

		paramprocess(request,searchProject);

		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		
		Pager<Project> pager = projectService.queryProject(searchProject, userPermit, PubMethod.getPager(request, Project.class));
		PubMethod.setRequestPager(request, pager,Project.class);
		

		
		request.setAttribute("project", searchProject);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		return "basicdata/project_data_list";
	}	
	
	private void paramprocess(HttpServletRequest request,Project project){		
		
		project.setProject_client_id(request.getParameter("client.client_id"));
		project.setProject_client_name(request.getParameter("client.client_name"));
		
		

		project.setRecruitment_userid(request.getParameter("recruitment.user_id"));
		project.setRecruitment_username(request.getParameter("recruitment.user_name"));

		project.setSales_userid(request.getParameter("salesuser.user_id"));
		project.setSales_username(request.getParameter("salesuser.user_name"));
		

		project.setInfo_sources_userid(request.getParameter("infosource.user_id"));
		project.setInfo_sources_username(request.getParameter("infosource.user_name"));
		

		project.setManage_userid(request.getParameter("manage.user_id"));
		project.setManage_username(request.getParameter("manage.user_name"));
		

		project.setSales_before_userid(request.getParameter("salesbefore.user_id"));
		project.setSales_before_username(request.getParameter("salesbefore.user_name"));
		
		project.setSales_after_userid(request.getParameter("salesafter.user_id"));
		project.setSales_after_username(request.getParameter("salesafter.user_name"));
		
		

		project.setSales_amount_deptid(request.getParameter("sales.dept_id"));
		project.setSales_amount_deptname(request.getParameter("sales.dept_name"));
		

		project.setExec_amount_deptid(request.getParameter("exec.dept_id"));
		project.setExec_amount_deptname(request.getParameter("exec.dept_name"));

		project.setDeptid(request.getParameter("dept.dept_id"));
		project.setDeptname(request.getParameter("dept.dept_name"));
		
		project.setApprove_user_id(request.getParameter("approve.user_id"));
		project.setApprove_user_name(request.getParameter("approve.user_name"));

	}
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(Project searchProject,HttpServletResponse res,HttpServletRequest request){
		Project project = null;
		if(searchProject != null && searchProject.getProject_id()!=null){
			request.setAttribute("next_operation", "updateProject");
			project = projectService.getProject(searchProject.getProject_id());
			List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(project);
			request.setAttribute("projectStaffs", projectStaffs);
			List<ProjectContract> contractAttachs = projectService.queryProjectContract(project);
			request.setAttribute("contractAttachs", contractAttachs);
		}else {
			request.setAttribute("next_operation", "addProject");
			User sessionUser = PubMethod.getUser(request);
			project = new Project();
			project.setProject_id(IDKit.generateId());
			project.setBuild_userid(sessionUser.getUser_id());
			project.setBuild_username(sessionUser.getUser_name());
			project.setBuild_datetime(PubMethod.getCurrentDate());
			project.setProject_type(EnumProjectType.PROJECT.getKey());
		}

		request.setAttribute("project1", project);
		request.setAttribute("currDate", DateKit.getCurrDate());
		
		return "basicdata/project_data_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(Project searchProject,HttpServletResponse res,HttpServletRequest request){
		
		Project project = projectService.getProject(searchProject.getProject_id());
		List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(project);
		request.setAttribute("projectStaffs", projectStaffs);
		List<ProjectContract> contractAttachs = projectService.queryProjectContract(project);
		request.setAttribute("contractAttachs", contractAttachs);		

		request.setAttribute("project1", project);
		request.setAttribute("currDate", DateKit.getCurrDate());
		
		//处理合同信息
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.CONTRACTVIEW.getId());
		Contract contract = new Contract();
		contract.setProject_id(project.getProject_id());
		Pager<Contract> pager = contractService.queryContract(contract, userPermit, PubMethod.getPagerByAll(Contract.class));
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		request.setAttribute("contracts", pager.getResultList());
		
		return "basicdata/project_data_view";
		
	}
	
	
	/**
	 * 检查在项目人员是否有重复的
	 * @param projectStaffs
	 * @return
	 */
	private boolean checkStaff(ProjectStaff[] projectStaffs){
		boolean b = true;
		if(projectStaffs == null || projectStaffs.length <= 1) return b;
		Map<String,String> map = new HashMap<String,String>();
		
		for(ProjectStaff projectStaff : projectStaffs){
			
			//用于修改项目人员时
			if(StringUtils.isEmpty(projectStaff.getStaff_id())) continue;
			
			if(map.containsKey(projectStaff.getStaff_id()))
				return false;
			else {
				map.put(projectStaff.getStaff_id(), projectStaff.getStaff_id());
			}
		}
		return true;
		
		
	}
	

	@RequestMapping(params = "method=addProject")
	public String addProject(Project addProject,HttpServletResponse res,HttpServletRequest request){
		Project project = addProject;	
		

		paramprocess(request,project);

		User sessionUser = PubMethod.getUser(request);
		project.setBuild_datetime(PubMethod.getCurrentDate());
		project.setBuild_userid(sessionUser.getUser_id());
		project.setBuild_username(sessionUser.getUser_name());
		project.setDelete_flag(BusinessUtil.NOT_DELETEED);		

		ProjectStaff[] projectStaffs = getProjectStaff(request,project,sessionUser);
		
		if(!checkStaff(projectStaffs)){
			return this.ajaxForwardError(request, "项目人员有重复！", true);
		}
		
		ProjectContract[] projectContracts = getProjectContract(request,project,sessionUser);
		
		int count = 0;
		try{
			count = projectService.addProject(project,projectStaffs,projectContracts);		
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	

	

	@RequestMapping(params = "method=updateProject")
	public String updateProject(Project updateProject,HttpServletResponse res,HttpServletRequest request){
		Project project = updateProject;	

		paramprocess(request,project);
		User sessionUser = PubMethod.getUser(request);

		ProjectStaff[] projectStaffs = getProjectStaff(request,project,sessionUser);
		

		if(!checkStaff(projectStaffs)){
			return this.ajaxForwardError(request, "项目人员有重复！", true);
		}

		ProjectContract[] projectContracts = getProjectContract(request,project,sessionUser);
		
		int count = 0;
		try{
			count = projectService.updateProject(project,projectStaffs,projectContracts);		
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}	
	
	
	private ProjectContract[] getProjectContract(HttpServletRequest request,Project project,User sessionUser){

		String[] rowIndex = request.getParameterValues("index_project_contract_attach_table");
		List<ProjectContract> projectContractList = new ArrayList<ProjectContract>();
		if(rowIndex != null && rowIndex.length >0){
			for(String index : rowIndex) {
				ProjectContract projectContract = new ProjectContract();
				projectContract.setProject_id(project.getProject_id());
				projectContract.setAttachment_id(request.getParameter("items["+index+"].attachment.attachment_id"));
				projectContract.setAttachment_name(request.getParameter("items["+index+"].attachment.attachment_name"));
				projectContract.setAttachment_path(request.getParameter("items["+index+"].attachment.attachment_path"));
				
				projectContract.setBuild_datetime(PubMethod.getCurrentDate());
				projectContract.setBuild_userid(sessionUser.getUser_id());
				projectContract.setBuild_username(sessionUser.getUser_name());
				projectContractList.add(projectContract);
			}
		}
		ProjectContract[] projectContracts = null;
		if(projectContractList.size() > 0) {
			projectContracts = new ProjectContract[projectContractList.size()];
			PubMethod.List2Array(projectContractList, projectContracts, ProjectContract.class);
		}
		
		return projectContracts;
	}
	
	
	private ProjectStaff[] getProjectStaff(HttpServletRequest request,Project project,User sessionUser){

		String[] rowIndex = request.getParameterValues("index_project_staff_cost_table");
		
		List<ProjectStaff> projectStaffList = new ArrayList<ProjectStaff>();
		if(rowIndex != null && rowIndex.length >0){
			for(String index : rowIndex) {
				ProjectStaff projectStaff = new ProjectStaff();
				String project_staff_id = request.getParameter("items["+index+"]."+"project_staff_id");
				
				String staff_id = request.getParameter("items["+index+"].staff."+"staff_id");
				String staff_name = request.getParameter("items["+index+"].staff."+"staff_name");
				String join_project_datetime= request.getParameter("items["+index+"]."+"join_project_datetime");
				String leave_project_datetime = request.getParameter("items["+index+"]."+"leave_project_datetime");
				String technical_cost = request.getParameter("items["+index+"].staff."+"technical_cost");
				String client_dept = request.getParameter("items["+index+"]."+"client_dept");
				
				projectStaff.setProject_staff_id(StringUtils.isEmpty(project_staff_id)?null :project_staff_id );
				projectStaff.setStaff_id(staff_id);
				projectStaff.setStaff_name(staff_name);
				projectStaff.setProject_id(project.getProject_id());
				projectStaff.setClient_dept(client_dept);
				
				if(join_project_datetime != null && join_project_datetime.length() > 0){
					projectStaff.setJoin_project_datetime(new Timestamp(DateKit.fmtStrToDate(join_project_datetime).getTime()));
				}
				if(leave_project_datetime != null && leave_project_datetime.length() > 0){
					projectStaff.setLeave_project_datetime(new Timestamp(DateKit.fmtStrToDate(leave_project_datetime).getTime()));
				}
				if(technical_cost != null && technical_cost.length() >0){
					projectStaff.setTechnical_cost(new Double(technical_cost));
				}

				projectStaff.setBuild_datetime(PubMethod.getCurrentDate());
				projectStaff.setBuild_userid(sessionUser.getUser_id());
				projectStaff.setBuild_username(sessionUser.getUser_name());
				projectStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);				
				
				projectStaffList.add(projectStaff);				
			}
		}
		
		ProjectStaff[] projectStaffs = null;
		if(projectStaffList.size() > 0) {
			projectStaffs = new ProjectStaff[projectStaffList.size()];
			PubMethod.List2Array(projectStaffList, projectStaffs, ProjectStaff.class);
		}
		
		return projectStaffs;
	}
	

	@RequestMapping(params = "method=deleteProject")
	public String deleteProject(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();
		
		
		String[] project_ids = request.getParameterValues("ids");
		if(project_ids != null && project_ids.length > 0){
			Project[] projects = new Project[project_ids.length];
			int index = 0;
			for(String project_id : project_ids){
				Project project = new Project();
				project.setProject_id(project_id);
				project.setDelete_userid(sessionUser.getUser_id());
				project.setDelete_username(sessionUser.getUser_name());
				project.setDelete_datetime(deleteDate);
				projects[index] = project;
				index ++ ;
				
				List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(project);
				if(projectStaffs != null && projectStaffs.size() >0){
					for(ProjectStaff projectStaff : projectStaffs){
						if(BusinessUtil.NOT_DELETEED.equals(projectStaff.getDelete_flag())){
							return this.ajaxForwardError(request, "项目不能删除， 还有人员没有离场！",false);
						}
					}
				}
			}

			projectService.deleteProject(projects);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}
	


	@RequestMapping(params = "method=toExchangeProjectStaff")
	public String toExchangeProjectStaff(HttpServletResponse res,HttpServletRequest request){

		ProjectStaff projectStaff = new ProjectStaff();
		projectStaff.setProject_staff_id(request.getParameter("project_staff_id"));		
		projectStaff = projectService.getProjectStaff(projectStaff);
		projectStaff.setLeave_project_datetime(PubMethod.getCurrentDate());
		request.setAttribute("projectStaff", projectStaff);
		
		return "basicdata/project_exchange_staff";
	}
	
	/**
	 * 项目人员离场
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteProjectStaff")
	public String deleteProjectStaff(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		
		ProjectStaff projectStaff = new ProjectStaff();
		projectStaff.setProject_staff_id(request.getParameter("project_staff_id"));		
		projectStaff = projectService.getProjectStaff(projectStaff);
		
		
		String project_id = request.getParameter("project.project_id");
		
		String technical_cost = request.getParameter("technical_cost");			
		
		String join_project_datetime = request.getParameter("join_project_datetime");
		
		String leave_project_datetime = request.getParameter("leave_project_datetime");
		
		String description = request.getParameter("description");
		

		double technical_costDouble = 0 ;
		
		Timestamp join_project_datetimeTime = null;
		

		if(join_project_datetime != null && join_project_datetime.length() > 0){
			join_project_datetimeTime = new Timestamp(DateKit.fmtStrToDate(join_project_datetime).getTime());
		}
		if(leave_project_datetime != null && leave_project_datetime.length() > 0){
			projectStaff.setLeave_project_datetime(new Timestamp(DateKit.fmtStrToDate(leave_project_datetime).getTime()));
		}
		if(technical_cost != null && technical_cost.length() >0){
			technical_costDouble = new Double(technical_cost);
		}
		
		
		projectStaff.setDescription(description);
		projectStaff.setDelete_flag(BusinessUtil.DELETEED);
		projectStaff.setDelete_datetime(PubMethod.getCurrentDate());
		projectStaff.setDelete_userid(sessionUser.getUser_id());
		projectStaff.setDelete_username(sessionUser.getUser_name());
		
		String selectProject = request.getParameter("selectProject");
		if(!"1".equals(selectProject)) project_id = null;
		
		projectService.exchangeProjectStaff(projectStaff, project_id, technical_costDouble, join_project_datetimeTime);
		
		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "project_staff_cost_table");
		return this.ajaxForwardSuccess(request,rel,true);
	}	
	
	
	/**
	 * 删除项目人员
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=removeProjectStaff")
	public String removeProjectStaff(HttpServletResponse res,HttpServletRequest request){
				
		ProjectStaff projectStaff = new ProjectStaff();
		projectStaff.setProject_staff_id(request.getParameter("project_staff_id"));			
		projectStaff = projectService.getProjectStaff(projectStaff);		
		projectService.doRemoveProjectStaff(projectStaff);

		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "project_staff_cost_table");
		return this.ajaxForwardSuccess(request,rel,false);
	}
	

	
	
	
	@RequestMapping(params = "method=toUplodad")
	public String toUplodad(ProjectContract projectContract,HttpServletResponse res,HttpServletRequest request){
		//projectService.deleteProjectContract(projectContract);
		return "basicdata/project_attach_upload";
	}

	@RequestMapping(params = "method=uploadProjectAttach")
	public String uploadProjectAttach(@RequestParam("image") MultipartFile file,ProjectContract projectContract,HttpServletResponse res,HttpServletRequest request) throws Exception{
		String id = IDKit.generateId();
		String folderPath = FileKit.getContractAttachDir(projectContract.getProject_id()).toString();
		
		File folder1 = new File(folderPath);
		if (!folder1.exists()) {
			folder1.mkdirs();  
			folderPath = folder1.getPath();
	      } 
		
		String filePath = folderPath + File.separatorChar + id;
		file.transferTo(new File(filePath));

		projectContract.setAttachment_id(id);
		projectContract.setAttachment_name(file.getOriginalFilename());
		projectContract.setAttachment_path(Base64Kit.encode(filePath.getBytes()) );
		
		request.setAttribute("projectContract1", projectContract);
		
		return "basicdata/project_attach_detail";
	}
	
	@RequestMapping(params = "method=deleteProjectAttach")
	public String deleteProjectAttach(ProjectContract searchprojectContract,HttpServletResponse res,HttpServletRequest request){
		ProjectContract projectContract = projectService.getProjectContract(searchprojectContract);
		if(projectContract != null && projectContract.getAttachment_id() != null){
			try{
				FileKit.deleteFile(new String(Base64Kit.decode(projectContract.getAttachment_path())));
			}catch(Exception e){
				e.printStackTrace();
			}
			projectService.deleteProjectContract(projectContract);
		}
		

		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "project_contract_attach_table");
		
		return this.ajaxForwardSuccess(request,"",false);
	}

	@RequestMapping(params = "method=printAttach")
	public void printAttach(ProjectContract searchprojectContract,HttpServletResponse res,HttpServletRequest request) throws Exception{
		try{
			ProjectContract projectContract = projectService.getProjectContract(searchprojectContract);
			if(projectContract == null) {
				return ;
			}
			if(!projectContract.getProject_id().equals(searchprojectContract.getProject_id())) {
				return ;
			}
			FileKit.pringFile(new String(Base64Kit.decode(projectContract.getAttachment_path())), projectContract.getAttachment_name(), request, res);
		}catch(Exception e){
			
		}
	}
	

}
