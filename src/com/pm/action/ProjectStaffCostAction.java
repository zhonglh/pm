package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.service.IProjectService;
import com.pm.service.IProjectStaffCostService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "ProjectStaffCostAction.do")
public class ProjectStaffCostAction extends BaseAction {

	

	private static final String rel = "rel24";
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IProjectStaffCostService projectStaffCostService;
	
	@Autowired
	private IRoleService roleService;
	
	

	@RequestMapping(params = "method=export")
	public void export(ProjectStaffCost projectStaffCost,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTSTAFFCOSTVIEW.getId());

		paramprocess(request,projectStaffCost);
		
		Pager<ProjectStaffCost> pager = projectStaffCostService.queryProjectStaffCost(projectStaffCost, userPermit, PubMethod.getPagerByAll(request, ProjectStaffCost.class));
		
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), ProjectStaffCost.class);
		}catch(Exception e){
			
		}
		
	}
	

	@RequestMapping(params = "method=list")
	public String list(ProjectStaffCost projectStaffCost,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTSTAFFCOSTVIEW.getId());

		paramprocess(request,projectStaffCost);
		
		Pager<ProjectStaffCost> pager = projectStaffCostService.queryProjectStaffCost(projectStaffCost, userPermit, PubMethod.getPager(request, ProjectStaffCost.class));
		PubMethod.setRequestPager(request, pager,ProjectStaffCost.class);
		

		
		request.setAttribute("projectStaffCost", projectStaffCost);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		
		return "projectcosts/project_staff_cost_list";
	}	
	
	private void paramprocess(HttpServletRequest request,ProjectStaffCost projectStaffCost){		
		
		//projectStaffCost.setProject_id(request.getParameter("project.project_id"));
		//projectStaffCost.setProject_name(request.getParameter("project.project_name"));
		
		//projectStaffCost.setStaff_id(request.getParameter("staff.staff_id"));
		//projectStaffCost.setStaff_name(request.getParameter("staff.staff_name"));
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			projectStaffCost.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			projectStaffCost.setDept_name(dept_name);
		}
		
	}
	
	

	@RequestMapping(params = "method=toView")
	public String toView(ProjectStaffCost projectStaffCost ,HttpServletResponse res,HttpServletRequest request){
		
		ProjectStaffCost projectStaffCost1 = projectStaffCostService.getProjectStaffCost(projectStaffCost);
		

		request.setAttribute("projectStaffCost1", projectStaffCost1);
		
		return "projectcosts/project_staff_cost_view";
		
	}
	

}
