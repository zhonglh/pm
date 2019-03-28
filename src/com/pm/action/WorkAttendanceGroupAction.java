package com.pm.action;

import java.util.ArrayList;
import java.util.Collections;
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
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.common.utils.sorts.SortComparator;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.Project;
import com.pm.domain.business.ReimburseCosts;
import com.pm.domain.business.Salary;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.WorkAttendance;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.service.ISalaryService;
import com.pm.service.IStaffCostService;
import com.pm.service.IWorkAttendanceService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.constant.EnumProjectType;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.util.excel.imports.ExcelRead;
import com.pm.vo.UserPermit;


/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "WorkAttendanceGroupAction.do")
public class WorkAttendanceGroupAction extends BaseAction {
	

	private static final String sessionAttr = "WorkAttendances";


	private static final String rel = "rel20";
	
	@Autowired
	private IWorkAttendanceService workAttendanceService;
	

	@Autowired
	private IStaffCostService staffCostService;
	

	@Autowired
	IMonthlyStatementService monthlyStatementService;

	@Autowired
	IApplyApproveService applyApproveService;

	@Autowired
	IProjectService projectService;
	
	

	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private IRoleService roleService;
	

	@RequestMapping(params = "method=isExist")
	public String isExist(WorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){
		paramprocess(request,searchWorkAttendance);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<WorkAttendance> pager= workAttendanceService.queryWorkAttendanceGroup(searchWorkAttendance, userPermit,PubMethod.getPager(request, WorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, searchWorkAttendance.getProject_name() + "在 "+searchWorkAttendance.getAttendance_month()+ " 的考勤记录已经制作，如果继续将会覆盖之前的考勤记录！");
		}
	}

	@RequestMapping(params = "method=list")
	public String list(WorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){
		
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchWorkAttendance.setDept_id(dept_id);
		}
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchWorkAttendance.setDept_name(dept_name);
		}
		

		

		request.setAttribute("workAttendance1", searchWorkAttendance);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEVIEW.getId());	
		Pager<WorkAttendance> pager= workAttendanceService.queryWorkAttendanceGroup(searchWorkAttendance, userPermit,PubMethod.getPager(request, WorkAttendance.class));
		PubMethod.setRequestPager(request, pager,WorkAttendance.class);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "projectcosts/work_attendance_group_list";		
		
	}
	
	

	@RequestMapping(params = "method=export")
	public void export(WorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchWorkAttendance.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchWorkAttendance.setDept_name(dept_name);
		}


		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCEVIEW.getId());		
		Pager<WorkAttendance> pager= workAttendanceService.queryWorkAttendance(searchWorkAttendance, userPermit,PubMethod.getPagerByAll(request, WorkAttendance.class));


		if(pager.getResultList() != null){
			for(WorkAttendance temp : pager.getResultList()){
				temp.setStr_month(String.valueOf(temp.getAttendance_month()));
			}
		}	
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), WorkAttendance.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}	
	
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "projectcosts/work_attendance_upload";		
	}	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/workAttendance.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "考勤模板.xlsx" ,response,false);
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
			if(!BusinessUtil.EXCEL_TYPE.contains(fileType)) {
				return this.ajaxForwardError(request, "请输入Excel文件！", true);
			}
		}catch(Exception e){			
		}
		
		try{			
			list = ExcelRead.readExcel(file.getInputStream(), FileKit.isXlsx(fileType),  Config.startRow);
		}catch(Exception e){
			e.printStackTrace();
			return this.ajaxForwardError(request, "该文件无法解析！",true);
		}
		
		if(list == null || list.size() == 0) {
			return this.ajaxForwardError(request, "该文件内容为空！",true);
		}
		
		int index = 0;
		for(String[] row : list){
			if(row.length<18) {
				return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			}
			index ++;
		}
		
		List<WorkAttendance> workAttendances = PubMethod.stringArray2List(list, WorkAttendance.class);
		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);		


		//查找所有项目
		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		Map<String,Project>  projectMap = new HashMap<String,Project>();
		for(Project project : projects.getResultList()){
			projectMap.put(project.getProject_name(), project);		
		}
		

		//查找所有人员
		Pager<StaffCost> staffCosts = staffCostService.queryStaffCost(new StaffCost(), null, userPermit, PubMethod.getPagerByAll(request, StaffCost.class));
		Map<String,StaffCost>  staffCostMap = new HashMap<String,StaffCost>();
		if(staffCosts.getResultList() != null) {
			for(StaffCost staffCost : staffCosts.getResultList()){
				staffCostMap.put(staffCost.getStaff_no(), staffCost);
			}
		}
		

		//查找已核单过的项目+月份
		List<WorkAttendance> checkedWorkAttendances = workAttendanceService.getCheckedProjectMonth();
		Map<String,WorkAttendance>  checkedWorkAttendanceMap = new HashMap<String,WorkAttendance>();
		if(checkedWorkAttendances != null){
			for(WorkAttendance  workAttendance : checkedWorkAttendances){
				checkedWorkAttendanceMap.put(workAttendance.getProject_id()+String.valueOf(workAttendance.getAttendance_month()), workAttendance);		
			}
		}

		Map<String,WorkAttendance> monthProjectMap = new HashMap<String,WorkAttendance>();	
		if(workAttendances != null) {
			for(WorkAttendance workAttendance : workAttendances){
				boolean b = checkWorkAttendance(workAttendance,projectMap,staffCostMap,checkedWorkAttendanceMap);
				if(b) {
					monthProjectMap.put(workAttendance.getProject_id()+String.valueOf(workAttendance.getAttendance_month()), workAttendance);
				}
			}
		}
		
		//查找对应的技术费用,分月份 和 项目查
		List<WorkAttendance> was = new ArrayList<WorkAttendance>();
		if(!monthProjectMap.isEmpty()){
			for(String  keys : monthProjectMap.keySet()){
				String project_id = keys.substring(0,keys.length()-6);
				int month = Integer.parseInt(keys.substring(keys.length()-6));
				Date date1 = DateKit.fmtStrToDate(month+"01","yyyyMMdd");
				Date date2 = DateKit.addMonth(date1, 1);
				WorkAttendance workAttendance1 = new WorkAttendance();
				workAttendance1.setAttendance_month(month);
				workAttendance1.setProject_id(project_id);
				workAttendance1.setAttendance_day1(DateKit.fmtDateToStr(date1, BusinessUtil.DT_FORMAT));
				workAttendance1.setAttendance_day2(DateKit.fmtDateToStr(date2, BusinessUtil.DT_FORMAT));
				
				List<WorkAttendance> technical_cost_list = workAttendanceService.getWorkAttendanceByProjectMonth(workAttendance1);
				if(technical_cost_list != null && technical_cost_list.size() >0 ) {
					was.addAll(technical_cost_list);
				}
			}
		}
		
		Map<String, WorkAttendance> keyMap = new HashMap<String, WorkAttendance>();
		for(WorkAttendance workAttendance : was){
			keyMap.put(workAttendance.getBusinessKey(), workAttendance);
		}
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		index = 0;
		for(WorkAttendance workAttendance : workAttendances){
			if(workAttendance.getErrorInfo()==null || workAttendance.getErrorInfo().length() <= 0){
				try{
					
					workAttendance.setAttendance_id(IDKit.generateId());	
					workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
					workAttendance.setBuild_userid(sessionUser.getUser_id());
					workAttendance.setBuild_username(sessionUser.getUser_name()); 
					workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);	
					
					WorkAttendance tmp = keyMap.get(workAttendance.getBusinessKey());
					if(tmp == null) {
						workAttendance.setErrorInfo("请先检查该人员是否在此项目中 ");
						isAllOK = false;
						continue;
					}else {
						workAttendance.setTechnical_cost(tmp.getTechnical_cost());
					}
	
					int count = workAttendanceService.addWorkAttendance(workAttendance);
					
					if(count == 0){
						workAttendance.setErrorInfo("已经有此考勤记录");
						isAllOK = false;
					}
					
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key_2")!=-1 || e.getMessage().indexOf("key 2")!=-1) {
						workAttendance.setErrorInfo("已经有此考勤记录");
					}
					else {
						workAttendance.setErrorInfo(e.getMessage());
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
			request.getSession().setAttribute(sessionAttr, workAttendances);
			request.setAttribute("forwardUrl", request.getContextPath()+"/WorkAttendanceGroupAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的考勤信息中有些问题！ ");
		}
		
	}	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<ReimburseCosts> list = (List<ReimburseCosts>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "projectcosts/work_attendance_excel_list";
	}


	private boolean checkWorkAttendance(WorkAttendance workAttendance,
			Map<String,Project>  projectMap,
			Map<String,StaffCost>  staffCostMap,
			Map<String,WorkAttendance>  checkedWorkAttendanceMap){
		boolean b = true;
		if(workAttendance.getStr_month() == null ||  workAttendance.getStr_month().isEmpty()){
			workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "考勤月份不能为空;");
			b = false;
		}else {
			if(workAttendance.getStr_month().length() != 6) {
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "考勤月份格式错误;");
				b = false;
			}else{
				Date date1 = DateKit.fmtStrToDate(workAttendance.getStr_month()+"01","yyyyMMdd");
				if(date1 == null){
					workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "考勤月份格式错误;");
					b = false;
				}else {
					workAttendance.setAttendance_month(Integer.parseInt(workAttendance.getStr_month()));
				}
			}
		}
		
		
		if(workAttendance.getProject_name() == null ||  workAttendance.getProject_name().isEmpty()){
			workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "项目名称不能为空;");
			b = false;
		}else {
			Project project = projectMap.get(workAttendance.getProject_name().trim());
			if(project == null ){
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				workAttendance.setProject_id(project.getProject_id());
				workAttendance.setProject_name(project.getProject_name());
				workAttendance.setProject_no(project.getProject_no());
			}
		}
		

		
		if(workAttendance.getStaff_no() == null || workAttendance.getStaff_no().length() == 0){
			workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "人员工号不能为空;");
			b = false;
			
		}else {
			StaffCost staffCost = staffCostMap.get(workAttendance.getStaff_no());
			if(staffCost == null){
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "人员工号错误;");
				b = false;
			}else {
				workAttendance.setStaff_id(staffCost.getStaff_id());
				workAttendance.setStaff_no(staffCost.getStaff_no());
				workAttendance.setStaff_name(staffCost.getStaff_name());
			}
		}
		
		

		if(b){
			String key = workAttendance.getProject_id() + String.valueOf(workAttendance.getAttendance_month());
			if(checkedWorkAttendanceMap.containsKey(key)) {
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "该项目该月份的考勤已完成核单，不能够再导入了;");
				b = false;
			}
		}
		
		if(workAttendance.getErrorInfo() != null && !workAttendance.getErrorInfo().isEmpty())
			b = false;		
		return b;
	}
	



	@RequestMapping(params = "method=batchVerifyWorkAttendance")
	public String batchVerifyWorkAttendance(HttpServletResponse res,HttpServletRequest request){

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);		
		}
		

		User sessionUser = PubMethod.getUser(request);
		
		WorkAttendance[] workAttendanceArray = new WorkAttendance[ids.length];
		int index = 0;
		for(String id : ids){
			WorkAttendance workAttendance = new WorkAttendance();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			workAttendance.setAttendance_month(Integer.parseInt(array[0]));
			workAttendance.setProject_id(array[1]);
			workAttendanceArray[index] = workAttendance;
			index ++ ;
		}
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		

		synchronized(this){
			
			for(WorkAttendance workAttendance : workAttendanceArray){
				Pager<WorkAttendance> pager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, WorkAttendance.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				WorkAttendance workAttendance1 = pager.getResultList().get(0);
				if(workAttendance1.getVerify_userid() != null && workAttendance1.getVerify_userid().length() > 0){
					continue;
				}
				String[] workAttendanceIds = new String[pager.getResultList().size()];
				int i = 0;
				for(WorkAttendance tmp : pager.getResultList()){
					workAttendanceIds[i] = tmp.getAttendance_id();
					i++;
				}
				try{
					verifyWorkAttendance(sessionUser, workAttendanceIds, workAttendance1);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		
		}
		

		return this.ajaxForwardSuccess(request, rel, false);
		
		
		
	}

	@RequestMapping(params = "method=verifyWorkAttendance")
	public String verifyWorkAttendance(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("attendance_id");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "考勤记录已经核实过！",true);
		}
		
		synchronized(this){
		

			WorkAttendance workAttendance1 = workAttendanceService.getWorkAttendance(ids[0]);
			if(workAttendance1.getVerify_userid() != null && workAttendance1.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "考勤记录已经核实过！",true);
			}
	
			
			verifyWorkAttendance(sessionUser, ids, workAttendance1);
		}
	
		return this.ajaxForwardSuccess(request, rel, true);
	}

	private void verifyWorkAttendance(User sessionUser, String[] ids, WorkAttendance workAttendance1) {
		
		List<WorkAttendance> workAttendances = new ArrayList<WorkAttendance>();			
		for(String attendance_id : ids){
			WorkAttendance workAttendance= new WorkAttendance();
			workAttendance.setAttendance_id(attendance_id);
			workAttendance.setVerify_datetime(PubMethod.getCurrentDate());
			workAttendance.setVerify_userid(sessionUser.getUser_id());
			workAttendance.setVerify_username(sessionUser.getUser_name());
			workAttendances.add(workAttendance);
			
		}

		if(workAttendances != null && workAttendances.size() > 0){
			workAttendanceService.verifyWorkAttendance( workAttendances );
			
			Project project = projectService.getProject(workAttendance1.getProject_id());
			
			if(EnumProjectType.STAFF.getKey().equals(project.getProject_type())){
				try{
					ThreadLocalUser.setUser(sessionUser);
					MonthlyStatement monthlyStatement = monthlyStatementService.autoAddMonthlyStatement(workAttendance1.getProject_id(), workAttendance1.getAttendance_month(), sessionUser);
					monthlyStatement.setProject_name(project.getProject_name());
					monthlyStatement.setProject_no(project.getProject_no());
					monthlyStatement.setProject_type(project.getProject_type());
					monthlyStatementService.addMonthlyStatement(monthlyStatement, monthlyStatement.getDetails());					
					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement.getMonthly_statement_id(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
				}finally{
					ThreadLocalUser.setUser(null);
				}
			}
		}
	}

	@RequestMapping(params = "method=addWorkAttendanceGroup")
	public String addWorkAttendanceGroup(WorkAttendance addWorkAttendance,HttpServletResponse res,HttpServletRequest request){	
		
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("id");
		
		if(ids == null || ids.length ==0){
			return this.ajaxForwardError(request, "没有添加任何考勤记录！",true);
		}
		
		List<WorkAttendance> list = new ArrayList<WorkAttendance>();
		for(String index : ids){			
			WorkAttendance workAttendance = new WorkAttendance();
			workAttendance.setAttendance_month(addWorkAttendance.getAttendance_month());
			workAttendance.setProject_id(addWorkAttendance.getProject_id());

			workAttendance.setStaff_name(request.getParameter("staff_name"+index));
			workAttendance.setStaff_id(request.getParameter("staff_id"+index));
			
			workAttendance.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
			workAttendance.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
			workAttendance.setLegal_holidays(Double.parseDouble(request.getParameter("legal_holidays"+index)));
			workAttendance.setSwopped_holidays(Double.parseDouble(request.getParameter("swopped_holidays"+index)));
			workAttendance.setAnnual_leave_days(Double.parseDouble(request.getParameter("annual_leave_days"+index)));
			workAttendance.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
			workAttendance.setPersonal_leave_days(Double.parseDouble(request.getParameter("personal_leave_days"+index)));
			workAttendance.setSick_leave_days(Double.parseDouble(request.getParameter("sick_leave_days"+index)));

			workAttendance.setWaiting_post_days(Double.parseDouble(request.getParameter("waiting_post_days"+index)));
			workAttendance.setMaternity_leave_days(Double.parseDouble(request.getParameter("maternity_leave_days"+index)));
			//workAttendance.setMedical_days(Double.parseDouble(request.getParameter("medical_days"+index)));


			workAttendance.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			workAttendance.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			workAttendance.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));
			workAttendance.setTechnical_cost(Double.parseDouble(request.getParameter("technical_cost"+index)));
			workAttendance.setClient_dept(request.getParameter("client_dept"+index));

			workAttendance.setEvery_overtime(Double.parseDouble(request.getParameter("every_overtime"+index)));
			workAttendance.setRemark(request.getParameter("remark"+index));
			
			workAttendance.setAttendance_id(IDKit.generateId());
			workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
			workAttendance.setBuild_userid(sessionUser.getUser_id());
			workAttendance.setBuild_username(sessionUser.getUser_name());
			workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);			
			list.add(workAttendance);
		}
		

		if(list != null && !list.isEmpty()) 
			workAttendanceService.addWorkAttendance(list);
		return this.ajaxForwardSuccess(request, rel, true);
		
	}
	

	@RequestMapping(params = "method=updateWorkAttendanceGroup")
	public String updateWorkAttendanceGroup(WorkAttendance updateWorkAttendance,HttpServletResponse res,HttpServletRequest request){	

		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("id");
		
		if(ids == null || ids.length ==0){
			return this.ajaxForwardSuccess(request);
		}
		
		Salary searchSalary = new Salary();
		searchSalary.setProject_id(updateWorkAttendance.getProject_id());
		searchSalary.setSalary_month(updateWorkAttendance.getAttendance_month());
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		Pager<Salary> salarys = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(salarys.getResultList() != null && !salarys.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "该考勤已经生成工资单， 需要删除工资单后才能够修改！",true);
		}

		List<WorkAttendance> list = new ArrayList<WorkAttendance>();

		List<WorkAttendance> delList = new ArrayList<WorkAttendance>();
		
		for(String index : ids){			
			
			if(index.equals("0")) continue;
			
			
			
			
			WorkAttendance workAttendance = new WorkAttendance();
			workAttendance.setAttendance_id(request.getParameter("attendance_id"+index));	
			workAttendance.setOperationType(request.getParameter("operationType"+index));	
			
			if(EnumOperationType.DELETE.getValue().equals(workAttendance.getOperationType())){
				delList.add(workAttendance);
				continue;
			}
			
			
			workAttendance.setAttendance_month(updateWorkAttendance.getAttendance_month());
			workAttendance.setProject_id(updateWorkAttendance.getProject_id());
			
			workAttendance.setStaff_name(request.getParameter("staff_name"+index));
			workAttendance.setStaff_id(request.getParameter("staff_id"+index));
			
			workAttendance.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
			workAttendance.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
			workAttendance.setLegal_holidays(Double.parseDouble(request.getParameter("legal_holidays"+index)));
			workAttendance.setSwopped_holidays(Double.parseDouble(request.getParameter("swopped_holidays"+index)));
			workAttendance.setAnnual_leave_days(Double.parseDouble(request.getParameter("annual_leave_days"+index)));
			workAttendance.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
			workAttendance.setPersonal_leave_days(Double.parseDouble(request.getParameter("personal_leave_days"+index)));
			workAttendance.setSick_leave_days(Double.parseDouble(request.getParameter("sick_leave_days"+index)));

			workAttendance.setWaiting_post_days(Double.parseDouble(request.getParameter("waiting_post_days"+index)));
			workAttendance.setMaternity_leave_days(Double.parseDouble(request.getParameter("maternity_leave_days"+index)));
			//workAttendance.setMedical_days(Double.parseDouble(request.getParameter("medical_days"+index)));

			workAttendance.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			workAttendance.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			workAttendance.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));
			workAttendance.setTechnical_cost(Double.parseDouble(request.getParameter("technical_cost"+index)));
			workAttendance.setClient_dept(request.getParameter("client_dept"+index));
			

			workAttendance.setEvery_overtime(Double.parseDouble(request.getParameter("every_overtime"+index)));
			workAttendance.setRemark(request.getParameter("remark"+index));
			
			
			
			if(workAttendance.getAttendance_id() == null || workAttendance.getAttendance_id().isEmpty()){
				workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
				workAttendance.setBuild_userid(sessionUser.getUser_id());
				workAttendance.setBuild_username(sessionUser.getUser_name());
				workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);
			}
			
			workAttendance.setVerify_datetime(PubMethod.getCurrentDate());
			workAttendance.setVerify_userid(sessionUser.getUser_id());
			workAttendance.setVerify_username(sessionUser.getUser_name());
			
			list.add(workAttendance);
		}
		

		if(list.isEmpty() && delList.isEmpty()){
			return this.ajaxForwardError(request, "没有修改过的考勤单！",true);
		}
		
		if(!delList.isEmpty()){
			workAttendanceService.deleteWorkAttendance(delList.toArray(new WorkAttendance[delList.size()]));
		}
		
		workAttendanceService.updateWorkAttendance(list);
		
		
		if("1".equals(request.getParameter("checked"))){


				workAttendanceService.verifyWorkAttendance( list );
				
				Project project = projectService.getProject(updateWorkAttendance.getProject_id());
				MonthlyStatement searchMonthlyStatement = new MonthlyStatement();
				searchMonthlyStatement.setProject_id(updateWorkAttendance.getProject_id());
				searchMonthlyStatement.setStatement_month(updateWorkAttendance.getAttendance_month());
				searchMonthlyStatement.setProject_type(BusinessUtil.STATEMENT_TYPE_2);
				Pager<MonthlyStatement> mss = monthlyStatementService.queryMonthlyStatement(searchMonthlyStatement, userPermit, PubMethod.getPager(request, MonthlyStatement.class));
				
				boolean haveCheckedMonthlyStatement = false;
				if(mss.getResultList()!=null && mss.getResultList().size()>0){
					for(MonthlyStatement ms : mss.getResultList()){
						if(ms.getVerify_userid() != null && ms.getVerify_userid().length()>0){
							haveCheckedMonthlyStatement = true;
						}else {
							monthlyStatementService.deleteMonthlyStatement(new MonthlyStatement[]{ms});
						}
					}
				}
				
				if(!haveCheckedMonthlyStatement){
					if(EnumProjectType.STAFF.getKey().equals(project.getProject_type())){
						
						try{
							ThreadLocalUser.setUser(sessionUser);
							MonthlyStatement monthlyStatement = monthlyStatementService.autoAddMonthlyStatement(updateWorkAttendance.getProject_id(), updateWorkAttendance.getAttendance_month(), sessionUser);
							monthlyStatement.setProject_name(project.getProject_name());
							monthlyStatement.setProject_no(project.getProject_no());
							monthlyStatement.setProject_type(project.getProject_type());
							monthlyStatementService.addMonthlyStatement(monthlyStatement, monthlyStatement.getDetails());		
							ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement.getMonthly_statement_id(), sessionUser);
							applyApproveService.addApplyApprove(applyApprove);
						}finally{
							ThreadLocalUser.setUser(null);
						}
					}
				}
			
		}
		
		return this.ajaxForwardSuccess(request, rel, true);
	}

	

	private void paramprocess(HttpServletRequest request,WorkAttendance workAttendance){	
		workAttendance.setProject_id(request.getParameter("project.project_id"));	
		
		if(workAttendance.getProject_name() == null || workAttendance.getProject_name().isEmpty()) {
			workAttendance.setProject_name(request.getParameter("project.project_name"));
		}
	}

	
	@RequestMapping(params = "method=toEditFirst")
	public String toEditFirst(HttpServletResponse res,HttpServletRequest request){		
		
		String attendance_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setAttendance_month(Integer.parseInt(attendance_month));
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<WorkAttendance> pager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPager(request, WorkAttendance.class));
		if(pager.getResultList() != null && pager.getResultList().size() > 0){
			WorkAttendance workAttendance1 = pager.getResultList().get(0);
			request.setAttribute("should_work_days", workAttendance1.getShould_work_days());
			request.setAttribute("legal_holidays", workAttendance1.getLegal_holidays());
		}
		
		request.setAttribute("attendance_month", attendance_month);		
		
		return "projectcosts/work_attendance_group_edit1";		
	}
	
	
	private List<WorkAttendance> computeWorkAttendance(WorkAttendance workAttendance1){
		
		
		Date date1 = DateKit.fmtStrToDate(workAttendance1.getAttendance_month()+"01","yyyyMMdd");
		Date date2 = DateKit.addMonth(date1, 1);
		workAttendance1.setAttendance_day1(DateKit.fmtDateToStr(date1, BusinessUtil.DT_FORMAT));
		workAttendance1.setAttendance_day2(DateKit.fmtDateToStr(date2, BusinessUtil.DT_FORMAT));
		
		List<WorkAttendance> list = workAttendanceService.getWorkAttendanceByProjectMonth(workAttendance1);
		
		return list;
	}
	

	@RequestMapping(params = "method=toEditNext")
	public String toEditNext(WorkAttendance workAttendance,HttpServletResponse res,HttpServletRequest request){	
		
		WorkAttendance workAttendance1 = workAttendance;
		paramprocess(request,workAttendance1);
		
		User sessionUser = PubMethod.getUser(request);
		workAttendance1.setBuild_username(sessionUser.getUser_name());
		workAttendance1.setBuild_datetime(PubMethod.getCurrentDate());
		
		List<WorkAttendance> list = computeWorkAttendance(workAttendance1);
		
		if(list == null) list = new ArrayList<WorkAttendance>();
		
		for(WorkAttendance workAttendanceTmp : list){
			workAttendanceTmp.setShould_work_days(workAttendance.getShould_work_days());
			workAttendanceTmp.setLegal_holidays(workAttendance.getLegal_holidays());
		}
		
		request.setAttribute("list", list);
		request.setAttribute("workAttendance1", workAttendance1);
		request.setAttribute("next_operation", "addWorkAttendanceGroup");
		request.setAttribute("totayDays", workAttendance.getShould_work_days()+workAttendance.getLegal_holidays());
		
		return "projectcosts/work_attendance_group_edit2";
	}
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(HttpServletResponse res,HttpServletRequest request){
		
		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setProject_id(array[1]);
		workAttendance.setAttendance_month(Integer.parseInt(array[0]));

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<WorkAttendance> pager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, WorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何考勤记录！",true);
		}
		
		Salary searchSalary = new Salary();
		searchSalary.setProject_id(workAttendance.getProject_id());
		searchSalary.setSalary_month(workAttendance.getAttendance_month());
		Pager<Salary> salarys = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(salarys.getResultList() != null && !salarys.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "该考勤已经生成工资单， 需要删除工资单后才能够修改！",true);
		}

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("workAttendance1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateWorkAttendanceGroup");
		
		String checked = pager.getResultList().get(0).getVerify_userid();
		checked = ( checked == null || checked.isEmpty()) ? "0" : "1" ;
		request.setAttribute("checked", checked);

		return "projectcosts/work_attendance_group_edit";
		
	}	
	
	
	
	/**
	 * 根据项目和人员关系，重新计算考勤中项目人员
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toReCompute")
	public String toReCompute(HttpServletResponse res,HttpServletRequest request){
		
		
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setProject_id(request.getParameter("project_id"));
		workAttendance.setAttendance_month(Integer.parseInt(request.getParameter("attendance_month")));

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<WorkAttendance> pager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, WorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何考勤记录！",true);
		}
		List<WorkAttendance> list = pager.getResultList();
		
		Salary searchSalary = new Salary();
		searchSalary.setProject_id(workAttendance.getProject_id());
		searchSalary.setSalary_month(workAttendance.getAttendance_month());
		Pager<Salary> salarys = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(salarys.getResultList() != null && !salarys.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "该考勤已经生成工资单， 需要删除工资单后才能够修改！",true);
		}
		
		

		List<WorkAttendance> newlist = computeWorkAttendance(workAttendance);
		if(newlist == null) {
			newlist = new ArrayList<WorkAttendance>();
		}
		for(WorkAttendance wa : list){
			boolean have = false;
			for(WorkAttendance nwa :  newlist){
				if(wa.getStaff_id().equals(nwa.getStaff_id())) {
					have = true;
					wa.setTechnical_cost(nwa.getTechnical_cost());
					break;
				}
			}
			if(have) wa.setOperationType(EnumOperationType.UPDATE.getValue());
			else wa.setOperationType(EnumOperationType.DELETE.getValue());
		}
		
		List<WorkAttendance> adds = new ArrayList<WorkAttendance>();
		for(WorkAttendance nwa : newlist){
			boolean have = false;
			for(WorkAttendance wa :  list){
				if(wa.getStaff_id().equals(nwa.getStaff_id())) {
					have = true;
					break;
				}
			}
			if(!have) {
				nwa.setShould_work_days(list.get(0).getShould_work_days());
				nwa.setLegal_holidays(list.get(0).getLegal_holidays());
				nwa.setOperationType(EnumOperationType.INSERT.getValue());
				adds.add(nwa);
			}
		}
		
		if(adds.size() > 0) list.addAll(adds);
		Collections.sort(list,new SortComparator());		
		
		

		request.setAttribute("list", list);
		request.setAttribute("workAttendance1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateWorkAttendanceGroup");
		
		String checked = pager.getResultList().get(0).getVerify_userid();
		checked = ( checked == null || checked.isEmpty()) ? "0" : "1" ;
		request.setAttribute("checked", checked);
		request.setAttribute("reCompute", "1");

		return "projectcosts/work_attendance_group_edit";
		
	}		
	


	@RequestMapping(params = "method=toView")
	public String toView(HttpServletResponse res,HttpServletRequest request){
		

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setProject_id(array[1]);
		workAttendance.setAttendance_month(Integer.parseInt(array[0]));

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<WorkAttendance> pager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, WorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何考勤记录！",true);
		}

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("workAttendance1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateWorkAttendanceGroup");	
		
		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "projectcosts/work_attendance_group_view";
		
	}		
	

	@RequestMapping(params = "method=deleteWorkAttendanceGroup")
	public String deleteWorkAttendanceGroup(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);		
		}
		
		WorkAttendance[] workAttendanceArray = new WorkAttendance[ids.length];
		int index = 0;
		for(String id : ids){
			WorkAttendance workAttendance = new WorkAttendance();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			workAttendance.setAttendance_month(Integer.parseInt(array[0]));
			workAttendance.setProject_id(array[1]);
			workAttendanceArray[index] = workAttendance;
			index ++ ;
		}
		
		if(workAttendanceArray != null && workAttendanceArray.length > 0)
		workAttendanceService.deleteWorkAttendance( workAttendanceArray );

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
	
}
