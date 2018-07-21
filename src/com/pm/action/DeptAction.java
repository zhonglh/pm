package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.domain.business.DepartObjective;
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.service.IDepartObjectiveService;
import com.pm.service.IDeptService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "DeptAction.do")
public class DeptAction extends BaseAction {
	

	private static final String rel = "rel52";
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IDepartObjectiveService departObjectiveService;
	
	@Autowired
	private IRoleService roleService;
	


	@RequestMapping(params = "method=isExist")
	public String isExist(HttpServletResponse res,HttpServletRequest request){

		String error = null;
		Dept searchDept = new Dept();
		searchDept.setDept_name(request.getParameter("dept_name"));		
		searchDept.setDept_id(request.getParameter("dept_id"));		
		boolean b = deptService.isExist(searchDept);
		if(!b){
			searchDept.setDept_no(request.getParameter("dept_no"));
			searchDept.setDept_name(null);
			b = deptService.isExist(searchDept);
			if(b) error = "该部门编号已经存在";
		}else {
			error = "该部门名称已经存在";
		}
		
		
		if(!b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}
	
	
	@RequestMapping(params = "method=lookup")
	public void lookup(Dept searchDept,HttpServletResponse res,HttpServletRequest request){
		
		/*Dept searchDept = new Dept();
		searchDept.setDept_name(request.getParameter("dept_name"));
		searchDept.setStatisticsed(request.getParameter("statisticsed"));*/

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPTVIEW.getId());	
		
		String use = request.getParameter("use");
		
		List<Dept> list= deptService.getAllDept(searchDept, userPermit);
		if(list == null) list = new ArrayList<Dept>();
		
		if(use != null && use.equals("search")) {
			Dept dept = new Dept();
			dept.setDept_id("");
			dept.setDept_name(PubMethod.getAllWord());
			list.add(0, dept);
		}
		
		this.writeResJson(res, list);
		
	}

	@RequestMapping(params = "method=list")
	public String list(HttpServletResponse res,HttpServletRequest request){
		
		Dept searchDept = new Dept();
		searchDept.setDept_name(request.getParameter("dept_name"));
		request.setAttribute("curr_years", searchDept.getCurr_years());
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPTVIEW.getId());
		
		Pager<Dept> pager= deptService.queryDept(searchDept, userPermit,PubMethod.getPager(request, Dept.class));
		PubMethod.setRequestPager(request, pager,Dept.class);
		

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		
		
		return "system/dept_list";
		
	}
	
	private void paramprocess(HttpServletRequest request, Dept dept){

		dept.setLead_id(request.getParameter("staff.user_id"));
		dept.setLead_name(request.getParameter("staff.user_name"));		
	}
	
	private DepartObjective doParamprocess(HttpServletRequest request,Dept depart){
		String[] rowIndex = request.getParameterValues("index_dept_target");
		if(rowIndex == null || rowIndex.length == 0 ) return null;
		
		if(rowIndex != null && rowIndex.length >0){
			for(String index : rowIndex) {
				String id = request.getParameter("items["+index+"].id");
				String strYear = request.getParameter("items["+index+"].years");
				if(strYear == null || strYear.trim().isEmpty()) continue;
				int years = Integer.parseInt(strYear);
				if(years != PubMethod.getCurrentYear()) continue;
				double profit_objective = Double.parseDouble(request.getParameter("items["+index+"].profit_objective"));
				
				DepartObjective departObjective = new DepartObjective();
				departObjective.setId(id);
				departObjective.setDepart_id(depart.getDept_id());
				departObjective.setDepart_name(depart.getDept_name());
				departObjective.setProfit_objective(profit_objective);
				departObjective.setYears(years);
				return departObjective;
			}
		}
		
		return null;
	}

	@RequestMapping(params = "method=addDept")
	public String addDept(Dept dept,HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		if(dept == null) dept = new Dept();
		dept.setDept_id(IDKit.generateId());
		dept.setBuild_time(PubMethod.getCurrentDate());
		dept.setBuild_userid(sessionUser.getUser_id());
		dept.setBuild_username(sessionUser.getUser_name());
		dept.setDelete_flag(BusinessUtil.NOT_DELETEED);		

		paramprocess(request,dept);
		

		int count = 0;
		try{
			count = deptService.addDept(dept);
			DepartObjective departObjective = doParamprocess(request,dept);
			if(departObjective != null){
				departObjective.setId(IDKit.generateId());
				departObjectiveService.addDepartObjective(departObjective);
			}
		}catch(Exception e){
			 return this.ajaxForwardError(request, "年度目标重复或太大！", true);
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
	}
	

	@RequestMapping(params = "method=updateDept")
	public String updateDept(Dept dept,HttpServletResponse res,HttpServletRequest request){		
		

		paramprocess(request,dept);

		int count = 0;
		try{
			count = deptService.updateDept(dept);				

			DepartObjective departObjective = doParamprocess(request,dept);
			if(departObjective != null){
				if(departObjective.getId() == null || departObjective.getId().isEmpty()){
					departObjective.setId(IDKit.generateId());
					departObjectiveService.addDepartObjective(departObjective);
				}else{
					departObjectiveService.updateDepartObjective(departObjective);
				}
			}
		}catch(Exception e){
			 return this.ajaxForwardError(request, "年度目标重复或太大！", true);
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(Dept dept,HttpServletResponse res,HttpServletRequest request){
		
		Dept dept1 = null;
		boolean haveCurrYears = false;
		int currYears = PubMethod.getCurrentYear();
		
		if(dept != null && dept.getDept_id() !=null){
			dept1 = deptService.getDept(dept.getDept_id());
			request.setAttribute("next_operation", "updateDept");
			

			DepartObjective departObjective = new DepartObjective();
			departObjective.setDepart_id(dept1.getDept_id());
			List<DepartObjective> departObjectives = departObjectiveService.getDepartObjectiveBySearch(departObjective);
			if(departObjectives != null) {
				for(DepartObjective tmpTarget : departObjectives){
					if( tmpTarget.getYears() == currYears){
						haveCurrYears = true;
						break ;
					}
				}
				request.setAttribute("departObjectives", departObjectives);
			}
			
			
			
		}else {
			request.setAttribute("next_operation", "addDept");
		}
		
		
		if(dept1 == null) dept1 = new Dept();		
		request.setAttribute("dept1", dept1);	
		request.setAttribute("currYears", currYears);	
		request.setAttribute("haveCurrYears", haveCurrYears);		
		
		return "system/dept_edit";
		
	}	
	


	@RequestMapping(params = "method=toView")
	public String toView(Dept dept,HttpServletResponse res,HttpServletRequest request){
		
		Dept  dept1 = deptService.getDept(dept.getDept_id());
		
		if(dept1 == null) dept1 = new Dept();		
		request.setAttribute("dept1", dept1);	
		

		DepartObjective departObjective = new DepartObjective();
		departObjective.setDepart_id(dept1.getDept_id());
		List<DepartObjective> departObjectives = departObjectiveService.getDepartObjectiveBySearch(departObjective);
		if(departObjectives != null) request.setAttribute("departObjectives", departObjectives);
		
		return "system/dept_view";
		
	}		
	

	@RequestMapping(params = "method=deleteDept")
	public String deleteDept(HttpServletResponse res,HttpServletRequest request){
		
		String[] dept_ids = request.getParameterValues("ids");
		int size = dept_ids.length;
		
		Dept[] deptArray = new Dept[size];
		
		for(int i=0;i<size;i++){
			Dept dept = new Dept();
			dept.setDept_id(dept_ids[i]);
			deptArray[i] = dept;
		}
		
		deptService.deleteDept( deptArray );

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
}
