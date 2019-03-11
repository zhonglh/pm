package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.OtherStaffCost;
import com.pm.service.*;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.UserPermit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "OtherStaffCostAction.do")
public class OtherStaffCostAction extends BaseAction {

	

	private static final String rel = "rel04";
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IOtherStaffCostService otherStaffCostService;
	
	@Autowired
	private IRoleService roleService;
	
	

	@RequestMapping(params = "method=export")
	public void export(OtherStaffCost otherStaffCost,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFCOSTVIEW.getId());

		paramprocess(request,otherStaffCost);
		
		Pager<OtherStaffCost> pager = otherStaffCostService.queryOtherStaffCost(otherStaffCost, userPermit, PubMethod.getPagerByAll(request, OtherStaffCost.class));


		for(OtherStaffCost otherStaffCost1 : pager.getResultList()){
			otherStaffCost1.setCost_center_name(this.getMsg("cost.center." + (otherStaffCost1.getCost_center()==null?"":otherStaffCost1.getCost_center()), request));
		}
		try{
			BusinessExcel.export(res, null, pager.getResultList(), OtherStaffCost.class);
		}catch(Exception e){
			
		}
		
	}
	

	@RequestMapping(params = "method=list")
	public String list(OtherStaffCost otherStaffCost,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFCOSTVIEW.getId());

		paramprocess(request,otherStaffCost);
		
		Pager<OtherStaffCost> pager = otherStaffCostService.queryOtherStaffCost(otherStaffCost, userPermit, PubMethod.getPager(request, OtherStaffCost.class));
		PubMethod.setRequestPager(request, pager,OtherStaffCost.class);
		

		
		request.setAttribute("otherStaffCost", otherStaffCost);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		
		return "headquarters/other_staff_cost_list";
	}	
	
	private void paramprocess(HttpServletRequest request,OtherStaffCost otherStaffCost){		
		
		//otherStaffCost.setProject_id(request.getParameter("other.other_id"));
		//otherStaffCost.setProject_name(request.getParameter("other.other_name"));
		
		//otherStaffCost.setStaff_id(request.getParameter("staff.staff_id"));
		//otherStaffCost.setStaff_name(request.getParameter("staff.staff_name"));
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			otherStaffCost.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			otherStaffCost.setDept_name(dept_name);
		}
		
	}
	
	

	@RequestMapping(params = "method=toView")
	public String toView(OtherStaffCost otherStaffCost ,HttpServletResponse res,HttpServletRequest request){
		
		OtherStaffCost otherStaffCost1 = otherStaffCostService.getOtherStaffCost(otherStaffCost);
		

		request.setAttribute("otherStaffCost1", otherStaffCost1);
		
		return "headquarters/other_staff_cost_view";
		
	}
	

}
