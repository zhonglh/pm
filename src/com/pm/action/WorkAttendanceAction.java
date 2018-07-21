package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.domain.business.WorkAttendance;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.service.IWorkAttendanceService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "WorkAttendanceAction.do")
public class WorkAttendanceAction extends BaseAction {

	

	private static final String rel = "rel20";
	
	@Autowired
	private IWorkAttendanceService workAttendanceService;
	
	@Autowired
	private IRoleService roleService;
	
	

	@RequestMapping(params = "method=list")
	public String list(WorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){

		WorkAttendance workAttendance = new WorkAttendance();
		
		workAttendance.setStr_month(searchWorkAttendance.getStr_month());
		workAttendance.setAttendance_month(searchWorkAttendance.getAttendance_month());
		workAttendance.setProject_name(searchWorkAttendance.getProject_name());

		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			workAttendance.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			workAttendance.setDept_name(dept_name);
		}
		
		workAttendance.setAttendance_month1(searchWorkAttendance.getAttendance_month1());
		workAttendance.setAttendance_month2(searchWorkAttendance.getAttendance_month2());
		

		request.setAttribute("workAttendance1", workAttendance);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEVIEW.getId());		
		Pager<WorkAttendance> pager= workAttendanceService.queryWorkAttendance(workAttendance, userPermit,PubMethod.getPager(request, WorkAttendance.class));
		PubMethod.setRequestPager(request, pager,WorkAttendance.class);	

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());		
		
		
		return "system/work_attendance_list";		
		
	}
	

	@RequestMapping(params = "method=addWorkAttendance")
	public String addWorkAttendance(WorkAttendance workAttendance,HttpServletResponse res,HttpServletRequest request){	
		
		User sessionUser = PubMethod.getUser(request);
		if(workAttendance == null) workAttendance = new WorkAttendance();
		workAttendance.setAttendance_id(IDKit.generateId());
		workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
		workAttendance.setBuild_userid(sessionUser.getUser_id());
		workAttendance.setBuild_username(sessionUser.getUser_name());
		workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		workAttendanceService.addWorkAttendance(workAttendance);
		return this.ajaxForwardSuccess(request, rel, true);
	}
	

	@RequestMapping(params = "method=updateWorkAttendance")
	public String updateWorkAttendance(WorkAttendance workAttendance,HttpServletResponse res,HttpServletRequest request){		
		workAttendanceService.updateWorkAttendance(workAttendance);		
		return this.ajaxForwardSuccess(request, rel, true);
	}
	
	




	@RequestMapping(params = "method=toEdit")
	public String toEdit(WorkAttendance workAttendance,HttpServletResponse res,HttpServletRequest request){
		WorkAttendance workAttendance1 = null;
		if(workAttendance != null && workAttendance.getAttendance_id() !=null){
			workAttendance1 = workAttendanceService.getWorkAttendance(workAttendance.getAttendance_id());
			if(workAttendance1.getVerify_userid() != null && workAttendance1.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			request.setAttribute("next_operation", "updateWorkAttendance");
		}else {
			request.setAttribute("next_operation", "addWorkAttendance");
		}
		
		
		
		if(workAttendance1 == null) workAttendance1 = new WorkAttendance();
		
		request.setAttribute("workAttendance1", workAttendance1);		
		
		return "system/work_attendance_edit";
		
	}	
	


	@RequestMapping(params = "method=toView")
	public String toView(WorkAttendance workAttendance,HttpServletResponse res,HttpServletRequest request){
		
		WorkAttendance  workAttendance1 = workAttendanceService.getWorkAttendance(workAttendance.getAttendance_id());		
		
		if(workAttendance1 == null) workAttendance1 = new WorkAttendance();
		
		request.setAttribute("workAttendance1", workAttendance1);		
		
		return "system/work_attendance_view";
		
	}		
	

	@RequestMapping(params = "method=deleteWorkAttendance")
	public String deleteWorkAttendance(HttpServletResponse res,HttpServletRequest request){
		
		String[] attendance_ids = request.getParameterValues("ids");
		if(attendance_ids == null || attendance_ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);		
		}
		
		WorkAttendance[] workAttendanceArray = new WorkAttendance[attendance_ids.length];
		int index = 0;
		for(String attendance_id : attendance_ids){
			WorkAttendance workAttendance = new WorkAttendance();
			workAttendance.setAttendance_id(attendance_id);
			workAttendanceArray[index] = workAttendance;
			index ++ ;
		}
		
		if(workAttendanceArray != null && workAttendanceArray.length > 0)
		workAttendanceService.deleteWorkAttendance( workAttendanceArray );

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
}
