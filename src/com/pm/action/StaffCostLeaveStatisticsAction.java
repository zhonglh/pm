package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.StaffCostStatistics;
import com.pm.service.IRoleService;
import com.pm.service.IStaffCostStatisticsService;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalBusinessNameHolder;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.log.EntityAnnotation;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "StaffCostLeaveStatisticsAction.do")
public class StaffCostLeaveStatisticsAction extends BaseAction {
	
	

	
	@Autowired
	private IStaffCostStatisticsService staffCostStatisticsService;
	

	@Autowired
	private IRoleService roleService;
	
	
	


	/**
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(StaffCostStatistics searchStaffCostStatistics,HttpServletResponse res,HttpServletRequest request){		

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFCOSTLEAVESTATISTICS.getId());		
		paramprocess(request,searchStaffCostStatistics);
		
		Pager<StaffCostStatistics> pager = staffCostStatisticsService.queryLeaveStaffCostStatistics(searchStaffCostStatistics, userPermit, PubMethod.getPagerByAll(StaffCostStatistics.class));
		try{
			ThreadLocalBusinessNameHolder.setBusinessName("LEAVE");
			BusinessExcel.export(res, null, pager.getResultList(), StaffCostStatistics.class);
			ThreadLocalBusinessNameHolder.setBusinessName(null);
		}catch(Exception e){
			
		}
		
	}
	


	@RequestMapping(params = "method=list")
	public String list(StaffCostStatistics searchStaffCostStatistics,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFCOSTLEAVESTATISTICS.getId());		
		paramprocess(request,searchStaffCostStatistics);
		
		Pager<StaffCostStatistics> pager = staffCostStatisticsService.queryLeaveStaffCostStatistics(searchStaffCostStatistics, userPermit, PubMethod.getPager(request, StaffCostStatistics.class));

		
		PubMethod.setRequestPager(request, pager,StaffCostStatistics.class);
				
		request.setAttribute("staffCostStatistics1", searchStaffCostStatistics);		
		return "departstatistics/staff_cost_statisticsleave_list";
	}	
	
	private void paramprocess(HttpServletRequest request,StaffCostStatistics staffCostStatistics){	

		staffCostStatistics.setDept_id(request.getParameter("dept.dept_id"));
		staffCostStatistics.setDept_name(request.getParameter("dept.dept_name"));
	}
	
}
