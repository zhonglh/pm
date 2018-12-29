package com.pm.action;

import com.alibaba.fastjson.JSON;
import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.calculate.SalaryCalculate;
import com.pm.domain.business.*;
import com.pm.domain.system.User;
import com.pm.service.*;
import com.pm.util.PubMethod;
import com.pm.util.constant.*;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.UserPermit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "SalaryGroupAction.do")
public class SalaryGroupAction extends BaseAction {


	private static final String rel = "rel21";
	
	@Autowired
	private ISalaryService salaryService;

	@Autowired
	private IWorkAttendanceService workAttendanceService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IProjectService projectService;
		

	@Autowired
	private IApplyApproveService applyApproveService;	

	

	@Autowired
	protected IPersonnelMonthlyBaseService personnelMonthlyBaseService;
	

	@Autowired
	protected IInsuranceService insuranceService;
	
	
	/**
	 * 导出Excel
	 * @param searchSalary
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(Salary searchSalary,HttpServletResponse res,HttpServletRequest request){
		
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchSalary.setDept_id(dept_id);
		}
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALARYVIEW.getId());		
		
		Pager<Salary> pager = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		
		
		List<List<Salary>> lists = new ArrayList<List<Salary>>();
		if(pager.getResultList() != null && pager.getResultList().size() >0){
			List<Salary> salarys = pager.getResultList();
			List<Salary> salarySameMonth = null;
			int salaryMonth = 0;
			String project_name = null;
			for(Salary salary : salarys){
				if(salary.getSalary_month() != salaryMonth){
					salarySameMonth = new ArrayList<Salary>();
					lists.add(salarySameMonth);
					salaryMonth = salary.getSalary_month();
					project_name = null;
				}
				if(!salary.getProject_name().equals(project_name)){
					Salary projectnameSalary = new Salary();
					projectnameSalary.setSalary_month(salary.getSalary_month());
					projectnameSalary.setProject_name(salary.getProject_name());
					projectnameSalary.setShould_work_days(salary.getShould_work_days());
					salarySameMonth.add(projectnameSalary);
					project_name = salary.getProject_name();
				}
				salarySameMonth.add(salary);
			}
		}
		
		try{
			BusinessExcel.exportSalary(res,  lists, Salary.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	@RequestMapping(params = "method=isExist")
	public String isExist(Salary searchSalary,HttpServletResponse res,HttpServletRequest request){
		paramprocess(request,searchSalary);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setAttendance_month(searchSalary.getSalary_month());
		workAttendance.setProject_id(searchSalary.getProject_id());
		workAttendance.setProject_name(searchSalary.getProject_name());
		workAttendance.setVerify_flag("1");
		
		Pager<WorkAttendance> waPager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPager(request, WorkAttendance.class));
		if(waPager.getResultList() == null || waPager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, workAttendance.getProject_name() + "在 "+workAttendance.getAttendance_month()+ " 的考勤单还没有制作完成！");
		}
		
		Pager<Salary> pager= salaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPager(request, Salary.class));
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, searchSalary.getProject_name() + "在 "+searchSalary.getSalary_month()+ " 的工资记录已经制作，如果继续将会覆盖之前的工资记录！");
		}
		
		
		boolean b = personnelMonthlyBaseService.isExistNotCheckByWorkAttendance(searchSalary.getProject_id(), searchSalary.getSalary_month());
		if(b){			
			return this.ajaxForwardConfirm(request, "该项目成员的人事月报数据有些还没有核实，是否确定要继续？");
		}
		
		return this.ajaxForwardSuccess(request);
	}
	
	@RequestMapping(params = "method=list")
	public String list(Salary searchSalary ,HttpServletResponse res,HttpServletRequest request){
		
		
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchSalary.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchSalary.setDept_name(dept_name);
		}
		

		request.setAttribute("salary1", searchSalary);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALARYVIEW.getId());	
		Pager<Salary> pager= salaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPager(request, Salary.class));
		PubMethod.setRequestPager(request, pager,Salary.class);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());	
		


		
		return "projectcosts/salary_group_list";
		
	}
	
	
	/**
	 * 自动生成所有上个月的工资
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=autoAddSalaryGroup")
	public String autoAddSalaryGroup(HttpServletResponse res,HttpServletRequest request){	

		User sessionUser = PubMethod.getUser(request);
		String salary_month_str = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
		salary_month_str = salary_month_str.intern();
		int salary_month = Integer.parseInt(salary_month_str); 
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		Salary searchSalary = new Salary();
		searchSalary.setSalary_month(salary_month);
		Pager<Salary> pager= salaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPager(request, Salary.class));
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, searchSalary.getSalary_month()+ " 的工资记录已经制作，如果继续将会覆盖之前的工资记录！");
		}
				
		//检查上个月的人事月报是否有未审核的
		if(personnelMonthlyBaseService.isExistNotCheckByWorkAttendance(null, salary_month)){
			return this.ajaxForwardError(request, "操作错误，上个月的人事月报还有未审核的！",true);
		}
		
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setAttendance_month(searchSalary.getSalary_month());
		workAttendance.setVerify_flag("1");
		
		Pager<WorkAttendance> waPager = workAttendanceService.queryWorkAttendanceGroup(workAttendance, userPermit, PubMethod.getPagerByAll(WorkAttendance.class));
		if(waPager == null || waPager.getResultList() == null || waPager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "操作错误，上个月的考勤没有已审核通过的！",true);
		}


		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		Map<String,Project>  projectMap = new HashMap<String,Project>();
		for(Project project : projects.getResultList()){
			projectMap.put(project.getProject_id(), project);		
		}
		
		List<List<Salary>> lists = new ArrayList<List<Salary>>();
		
		for(WorkAttendance wa : waPager.getResultList()){

			Salary salary1 = new Salary();
			salary1.setProject_id(wa.getProject_id());
			salary1.setSalary_month(wa.getAttendance_month());

			//增加单个项目的工资信息
			List<Salary> list = this.getSalarysByWorkAttendance(salary1);
			if(list != null && list.size() >0 ) {lists.add(list);}
			salary1 = null;
		}
		
		for(List<Salary> list : lists){
			for(Salary salary : list){

				Project project = projectMap.get(salary.getProject_id());
				if(project != null){
					salary.setProject_no(project.getProject_no());
					salary.setProject_name(project.getProject_name());
				}
				
				SalaryCalculate.getInstance().calculate(salary, null, paramExtMap);
				
				salary.setSalary_month(salary_month);
				salary.setSalary_id(IDKit.generateId());
				salary.setBuild_datetime(PubMethod.getCurrentDate());
				salary.setBuild_userid(sessionUser.getUser_id());
				salary.setBuild_username(sessionUser.getUser_name());
				salary.setDelete_flag(BusinessUtil.NOT_DELETEED);			
			}
		}
		
		synchronized (this) {
			
			pager= salaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPagerByAll( Salary.class));
			if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
				return this.ajaxForwardError(request, searchSalary.getSalary_month()+ " 的工资记录已经制作，如果继续将会覆盖之前的工资记录！");
			}
			
			for(List<Salary> list : lists){
				salaryService.addSalary(list);
			
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), 
						EnumEntityType.SALARY.name(), list.get(0).getSalary_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}			
			
		}

		return this.ajaxForwardSuccess(request, rel, false);
	}
	
	

	@RequestMapping(params = "method=addSalaryGroup")
	public String addSalaryGroup(Salary addSalary,HttpServletResponse res,HttpServletRequest request){	
		
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("id");
		
		if(ids == null || ids.length ==0){
			return this.ajaxForwardError(request, "没有添加任何工资记录！",true);
		}

		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		
		List<Salary> list = new ArrayList<Salary>();
		for(String index : ids){
			Salary salary = new Salary();
			salary.setSalary_month(addSalary.getSalary_month());
			salary.setProject_id(addSalary.getProject_id());
			salary.setProject_no(addSalary.getProject_no());
			salary.setProject_name(addSalary.getProject_name());
			
			
			salary.setStaff_id(request.getParameter("staff_id"+index));
			salary.setStaff_name(request.getParameter("staff_name"+index));
			
			salary.setBasic_salary(Double.parseDouble(request.getParameter("basic_salary"+index)));
			salary.setPost_salary(Double.parseDouble(request.getParameter("post_salary"+index)));
			salary.setPerformance_allowances(Double.parseDouble(request.getParameter("performance_allowances"+index)));
			salary.setTravel_allowance(Double.parseDouble(request.getParameter("travel_allowance"+index)));
			salary.setTax_bonus(Double.parseDouble(request.getParameter("tax_bonus"+index)));
			salary.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
			salary.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
			salary.setPaid_leave_days(Double.parseDouble(request.getParameter("paid_leave_days"+index)));
			salary.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
			salary.setPersonal_leave_days(Double.parseDouble(request.getParameter("personal_leave_days"+index)));
			salary.setSick_leave_days(Double.parseDouble(request.getParameter("sick_leave_days"+index)));
			salary.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			salary.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			salary.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));
			salary.setSick_leave_salary(Double.parseDouble(request.getParameter("sick_leave_salary"+index)));
			salary.setNeglect_work_salary(Double.parseDouble(request.getParameter("neglect_work_salary"+index)));
			salary.setLate_salary(Double.parseDouble(request.getParameter("late_salary"+index)));
			salary.setActual_travel_allowance(Double.parseDouble(request.getParameter("actual_travel_allowance"+index)));
			salary.setActual_computer_allowance(Double.parseDouble(request.getParameter("actual_computer_allowance"+index)));
			salary.setActual_extra_allowance(Double.parseDouble(request.getParameter("actual_extra_allowance"+index)));
			salary.setOvertime_allowance(Double.parseDouble(request.getParameter("overtime_allowance"+index)));
			salary.setMeals_allowance(Double.parseDouble(request.getParameter("meals_allowance"+index)));
			salary.setActual_tax_bonus(Double.parseDouble(request.getParameter("actual_tax_bonus"+index)));
			salary.setShould_salary(Double.parseDouble(request.getParameter("should_salary"+index)));
			salary.setPension_insurance(Double.parseDouble(request.getParameter("pension_insurance"+index)));
			salary.setUnemployment_insurance(Double.parseDouble(request.getParameter("unemployment_insurance"+index)));
			salary.setMedical_Insurance(Double.parseDouble(request.getParameter("medical_Insurance"+index)));
			salary.setAccumulation_fund(Double.parseDouble(request.getParameter("accumulation_fund"+index)));
			

			salary.setEndowment_insurance_bycompany(Double.parseDouble(request.getParameter("endowment_insurance_bycompany"+index)));
			salary.setMedical_insurance_bycompany(Double.parseDouble(request.getParameter("medical_insurance_bycompany"+index)));
			salary.setLosejob_insurance_bycompany(Double.parseDouble(request.getParameter("losejob_insurance_bycompany"+index)));
			salary.setMaternity_insurance_bycompany(Double.parseDouble(request.getParameter("maternity_insurance_bycompany"+index)));
			salary.setJobharm_insurance_bycompany(Double.parseDouble(request.getParameter("jobharm_insurance_bycompany"+index)));
			salary.setReservefund_bypcompany(Double.parseDouble(request.getParameter("reservefund_bypcompany"+index)));


			salary.setChildren_education(Double.parseDouble(request.getParameter("children_education"+index)));
			salary.setContinuing_education(Double.parseDouble(request.getParameter("continuing_education"+index)));
			salary.setHousing_loans(Double.parseDouble(request.getParameter("housing_loans"+index)));
			salary.setHousing_rent(Double.parseDouble(request.getParameter("housing_rent"+index)));
			salary.setSupport_elderly(Double.parseDouble(request.getParameter("support_elderly"+index)));
			
			salary.setDeductions_cost(Double.parseDouble(request.getParameter("deductions_cost"+index)));
			salary.setTaxable_income(Double.parseDouble(request.getParameter("taxable_income"+index)));
			salary.setPersonal_income_tax(Double.parseDouble(request.getParameter("personal_income_tax"+index)));
			salary.setActual_bonus(Double.parseDouble(request.getParameter("actual_bonus"+index)));
			salary.setOverdue_tax_salary(Double.parseDouble(request.getParameter("overdue_tax_salary"+index)));
			salary.setActual_salary(Double.parseDouble(request.getParameter("actual_salary"+index)));
			salary.setDescription(request.getParameter("description"+index));
			salary.setComputer_allowance(Double.parseDouble(request.getParameter("computer_allowance"+index)));
			salary.setMeal_allowance(Double.parseDouble(request.getParameter("meal_allowance"+index)));
			
			salary.setSalary_id(IDKit.generateId());
			salary.setBuild_datetime(PubMethod.getCurrentDate());
			salary.setBuild_userid(sessionUser.getUser_id());
			salary.setBuild_username(sessionUser.getUser_name());
			salary.setDelete_flag(BusinessUtil.NOT_DELETEED);


			SalaryCalculate.getInstance().calculate(salary, null, paramExtMap);

			list.add(salary);
		}
		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		if(list != null && list.size() > 0 ) {
			synchronized (this) {
				Pager<Salary> pager= salaryService.querySalaryGroup(addSalary, userPermit,PubMethod.getPager(request, Salary.class));
				if(pager.getResultList() != null && !pager.getResultList().isEmpty()){					
					return this.ajaxForwardError(request, addSalary.getProject_name() + "在 "+addSalary.getSalary_month()+ " 的工资记录已经制作，如果继续将会覆盖之前的工资记录！");
				}
				salaryService.addSalary(list);
			}
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.SALARY.name(), list.get(0).getSalary_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, true);
		
	}



	@RequestMapping(params = "method=updateSalaryGroup4Datagrid")
	public String updateSalaryGroup4Datagrid(String info,HttpServletResponse res,HttpServletRequest request){	
		List<Salary> salarys = null;
		if(info!=null && info.length()>0) {
			salarys = JSON.parseArray(info, Salary.class);
		}
		if(salarys!=null && salarys.size()>0) {
			List<Salary> updateSalarys = new ArrayList<Salary>();
			List<Salary> delSalarys = new ArrayList<Salary>();
			for(Salary salary : salarys){

				if(salary.getVerify_userid() != null && !salary.getVerify_userid().isEmpty()) {
					continue;
				}else {
					if("2".equals(salary.getId())){
						Salary temp = new Salary();
						temp.setSalary_id(salary.getSalary_id());
						delSalarys.add(temp);
					}else {
						updateSalarys.add(salary);
					}
				}
			}
			
			if(updateSalarys != null && !updateSalarys.isEmpty()) {

				Map<String, ParamExtend> paramExtMap = getParamExtendMap();
				for(Salary salary : updateSalarys){
					SalaryCalculate.getInstance().calculate(salary, null, paramExtMap);
				}
				salaryService.updateSalary(updateSalarys);
			}
			

			if(delSalarys != null && !delSalarys.isEmpty()) {
				salaryService.deleteSalary(delSalarys.toArray(new Salary[delSalarys.size()]));
			}
				
		}
		return this.ajaxForwardSuccess(request, rel, true);
		
	}

	@RequestMapping(params = "method=updateSalaryGroup")
	public String updateSalaryGroup(Salary updateSalary,HttpServletResponse res,HttpServletRequest request){		

		
		String[] salary_ids = request.getParameterValues("salary_id");
		List<String> salaryList = new ArrayList<String>();
		List<Salary> delSalarys= new ArrayList<Salary>();
		
		if(salary_ids != null && salary_ids.length > 0){
			Collections.addAll(salaryList, salary_ids);
		}
		
		String[] ids = request.getParameterValues("id");
		
		if(ids == null || ids.length ==0){
			return this.ajaxForwardSuccess(request);
		}


		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		List<Salary> list = new ArrayList<Salary>();
		for(String index : ids){

			if(index.equals("0")) {continue;}
			
			Salary salary = new Salary();
			salary.setSalary_id(request.getParameter("salary_id"+index));
			if(!salaryList.contains(salary.getSalary_id())){
				delSalarys.add(salary);
				continue;
			}
			
			
			salary.setSalary_month(updateSalary.getSalary_month());
			salary.setProject_id(updateSalary.getProject_id());
			salary.setProject_no(updateSalary.getProject_no());
			salary.setProject_name(updateSalary.getProject_name());
			
			salary.setStaff_name(request.getParameter("staff_name"+index));
			
			salary.setBasic_salary(Double.parseDouble(request.getParameter("basic_salary"+index)));
			salary.setPost_salary(Double.parseDouble(request.getParameter("post_salary"+index)));
			salary.setPerformance_allowances(Double.parseDouble(request.getParameter("performance_allowances"+index)));
			salary.setTravel_allowance(Double.parseDouble(request.getParameter("travel_allowance"+index)));
			salary.setTax_bonus(Double.parseDouble(request.getParameter("tax_bonus"+index)));
			salary.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
			salary.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
			salary.setPaid_leave_days(Double.parseDouble(request.getParameter("paid_leave_days"+index)));
			salary.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
			salary.setPersonal_leave_days(Double.parseDouble(request.getParameter("personal_leave_days"+index)));
			salary.setSick_leave_days(Double.parseDouble(request.getParameter("sick_leave_days"+index)));
			salary.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			salary.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			salary.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));
			salary.setSick_leave_salary(Double.parseDouble(request.getParameter("sick_leave_salary"+index)));
			salary.setNeglect_work_salary(Double.parseDouble(request.getParameter("neglect_work_salary"+index)));
			salary.setLate_salary(Double.parseDouble(request.getParameter("late_salary"+index)));
			salary.setActual_travel_allowance(Double.parseDouble(request.getParameter("actual_travel_allowance"+index)));
			salary.setActual_computer_allowance(Double.parseDouble(request.getParameter("actual_computer_allowance"+index)));
			salary.setActual_extra_allowance(Double.parseDouble(request.getParameter("actual_extra_allowance"+index)));
			salary.setOvertime_allowance(Double.parseDouble(request.getParameter("overtime_allowance"+index)));
			salary.setMeals_allowance(Double.parseDouble(request.getParameter("meals_allowance"+index)));
			salary.setActual_tax_bonus(Double.parseDouble(request.getParameter("actual_tax_bonus"+index)));
			salary.setShould_salary(Double.parseDouble(request.getParameter("should_salary"+index)));
			salary.setPension_insurance(Double.parseDouble(request.getParameter("pension_insurance"+index)));
			salary.setUnemployment_insurance(Double.parseDouble(request.getParameter("unemployment_insurance"+index)));
			salary.setMedical_Insurance(Double.parseDouble(request.getParameter("medical_Insurance"+index)));
			salary.setAccumulation_fund(Double.parseDouble(request.getParameter("accumulation_fund"+index)));
			
			salary.setEndowment_insurance_bycompany(Double.parseDouble(request.getParameter("endowment_insurance_bycompany"+index)));
			salary.setMedical_insurance_bycompany(Double.parseDouble(request.getParameter("medical_insurance_bycompany"+index)));
			salary.setLosejob_insurance_bycompany(Double.parseDouble(request.getParameter("losejob_insurance_bycompany"+index)));
			salary.setMaternity_insurance_bycompany(Double.parseDouble(request.getParameter("maternity_insurance_bycompany"+index)));
			salary.setJobharm_insurance_bycompany(Double.parseDouble(request.getParameter("jobharm_insurance_bycompany"+index)));
			salary.setReservefund_bypcompany(Double.parseDouble(request.getParameter("reservefund_bypcompany"+index)));

			salary.setChildren_education(Double.parseDouble(request.getParameter("children_education"+index)));
			salary.setContinuing_education(Double.parseDouble(request.getParameter("continuing_education"+index)));
			salary.setHousing_loans(Double.parseDouble(request.getParameter("housing_loans"+index)));
			salary.setHousing_rent(Double.parseDouble(request.getParameter("housing_rent"+index)));
			salary.setSupport_elderly(Double.parseDouble(request.getParameter("support_elderly"+index)));
			
			salary.setDeductions_cost(Double.parseDouble(request.getParameter("deductions_cost"+index)));
			salary.setTaxable_income(Double.parseDouble(request.getParameter("taxable_income"+index)));
			salary.setPersonal_income_tax(Double.parseDouble(request.getParameter("personal_income_tax"+index)));
			salary.setActual_bonus(Double.parseDouble(request.getParameter("actual_bonus"+index)));
			salary.setOverdue_tax_salary(Double.parseDouble(request.getParameter("overdue_tax_salary"+index)));
			salary.setActual_salary(Double.parseDouble(request.getParameter("actual_salary"+index)));
			salary.setDescription(request.getParameter("description"+index));
			salary.setComputer_allowance(Double.parseDouble(request.getParameter("computer_allowance"+index)));
			salary.setMeal_allowance(Double.parseDouble(request.getParameter("meal_allowance"+index)));

			SalaryCalculate.getInstance().calculate(salary, null, paramExtMap);

			list.add(salary);
		}
		
		if(!list.isEmpty()) {
			salaryService.updateSalary(list);
		}
		
		if(!delSalarys.isEmpty()){
			Salary[] salarys = new Salary[delSalarys.size()];
			int index = 0;
			for(Salary salary : delSalarys){
				salarys[index] = salary;
				index ++;
			}
			salaryService.deleteSalary(salarys);
		}
		
		return this.ajaxForwardSuccess(request, rel, true);
	}

	

	private void paramprocess(HttpServletRequest request,Salary salary){	
		salary.setProject_id(request.getParameter("project.project_id"));	
		
		if(salary.getProject_no() == null || salary.getProject_no().isEmpty()) {
			salary.setProject_no(request.getParameter("project.project_no"));
		}
		
		if(salary.getProject_name() == null || salary.getProject_name().isEmpty()) {
			salary.setProject_name(request.getParameter("project.project_name"));
		}
	}

	
	@RequestMapping(params = "method=toEditFirst")
	public String toEditFirst(HttpServletResponse res,HttpServletRequest request){		
		
		String salary_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");

		request.setAttribute("salary_month", salary_month);		
		
		return "projectcosts/salary_group_edit1";
	}
	

	@RequestMapping(params = "method=toEditNext")
	public String toEditNext(Salary salary,HttpServletResponse res,HttpServletRequest request){	
		
		Salary salary1 = salary;
		paramprocess(request,salary1);
		User sessionUser = PubMethod.getUser(request);


		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		List<Salary> list = getSalarysByWorkAttendance(salary1);
		
		for(Salary temp : list){			
			SalaryCalculate.getInstance().calculate(temp, null, paramExtMap);					
		}


		salary1.setBuild_username(sessionUser.getUser_name());
		salary1.setBuild_datetime(PubMethod.getCurrentDate());
		
		request.setAttribute("list", list);
		request.setAttribute("salary1", salary1);
		request.setAttribute("next_operation", "addSalaryGroup");
		
		return "projectcosts/salary_group_edit2";
	}

	/**
	 * 根据考勤和人事月报，计算出工资初始信息
	 * @param salary1
	 * @return
	 */
	private List<Salary> getSalarysByWorkAttendance(Salary salary1) {
		
		Date date1 = DateKit.fmtStrToDate(String.valueOf(salary1.getSalary_month()*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary1.setDate1(date1);
		salary1.setDate2(date2);
		
		List<Salary> list = salaryService.getSalaryByProjectMonth(salary1);
		if(list == null) {
			list = new ArrayList<Salary>();
		}
		
		Insurance searchInsurance = new Insurance();
		searchInsurance.setProject_id(salary1.getProject_id());
		searchInsurance.setSalary_month(salary1.getSalary_month());
		List<Insurance> insurances = insuranceService.queryInsuranceByWorkAttendance(searchInsurance);
		
		Map<String,Insurance> insuranceMap = new HashMap<String,Insurance>();
		if(insurances != null && insurances.size() > 0 ){
			for(Insurance insurance : insurances){
				insuranceMap.put(insurance.getStaff_id(), insurance);
			}
		}
		
		//查询出人事月报
		List<PersonnelMonthlySalary> personnelMonthlySalarys = salaryService.getCurrSalaryByWorkAttendance(salary1);
		Map<String,PersonnelMonthlySalary> map = new HashMap<String,PersonnelMonthlySalary>();
		if(personnelMonthlySalarys != null){
			for(PersonnelMonthlySalary personnelMonthlySalary : personnelMonthlySalarys){
				map.put(personnelMonthlySalary.getStaff_id(), personnelMonthlySalary);
			}
		}
		
				
		for(Salary tempSalary : list){
			PersonnelMonthlySalary personnelMonthlySalary = map.get(tempSalary.getStaff_id());
			if(personnelMonthlySalary != null) {			
				double performance_allowances =  personnelMonthlySalary.getCurr_salary() - tempSalary.getBasic_salary() - tempSalary.getPost_salary();
				performance_allowances = PubMethod.getNumberFormatByDouble(performance_allowances);
				tempSalary.setPerformance_allowances(performance_allowances);
				
				if(StringUtils.isNotEmpty(personnelMonthlySalary.getDescription())){
					if(StringUtils.isEmpty(tempSalary.getDescription())){
						tempSalary.setDescription(personnelMonthlySalary.getDescription());
					}else {
						tempSalary.setDescription(tempSalary.getDescription()+" "+personnelMonthlySalary.getDescription());
					}
				}			
			}
			
			Insurance insurance = insuranceMap.get(tempSalary.getStaff_id());
			if(insurance != null){				
				tempSalary.setPension_insurance(insurance.getEndowment_insurance_bypersonal());
				tempSalary.setUnemployment_insurance(insurance.getLosejob_insurance_bypersonal());
				tempSalary.setMedical_Insurance(insurance.getMedical_insurance_bypersonal());				
				tempSalary.setAccumulation_fund(insurance.getReservefund_bypersonal());	
				
				tempSalary.setEndowment_insurance_bycompany(insurance.getEndowment_insurance_bycompany());
				tempSalary.setMedical_insurance_bycompany(insurance.getMedical_insurance_bycompany());				
				tempSalary.setLosejob_insurance_bycompany(insurance.getLosejob_insurance_bycompany());				
				tempSalary.setMaternity_insurance_bycompany(insurance.getMaternity_insurance_bycompany());				
				tempSalary.setJobharm_insurance_bycompany(insurance.getJobharm_insurance_bycompany());
				tempSalary.setReservefund_bypcompany(insurance.getReservefund_bypcompany());				
			}else {
				tempSalary.setPension_insurance(0);
				tempSalary.setUnemployment_insurance(0);
				tempSalary.setMedical_Insurance(0);			
				tempSalary.setAccumulation_fund(0);	
				
				tempSalary.setEndowment_insurance_bycompany(0);
				tempSalary.setMedical_insurance_bycompany(0);			
				tempSalary.setLosejob_insurance_bycompany(0);			
				tempSalary.setMaternity_insurance_bycompany(0);			
				tempSalary.setJobharm_insurance_bycompany(0);
				tempSalary.setReservefund_bypcompany(0);
			}
		}
	
		
		
		
		
		return list;
	}
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(HttpServletResponse res,HttpServletRequest request){
		
		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		Salary salary = new Salary();
		salary.setProject_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));
		
		
		Date date1 = DateKit.fmtStrToDate(String.valueOf(Integer.parseInt(array[0])*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<Salary> pager = salaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("salary1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateSalaryGroup");

		return "projectcosts/salary_group_edit";
		
	}	
	
	


	@RequestMapping(params = "method=toEdit4Easyui")
	public String toEdit4Easyui(HttpServletResponse res,HttpServletRequest request){
		
		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		Salary salary = new Salary();
		salary.setProject_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));

		Date date1 = DateKit.fmtStrToDate(String.valueOf(Integer.parseInt(array[0])*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<Salary> pager = salaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}

		request.setAttribute("id", id);
		//request.setAttribute("list", pager.getResultList());
		request.setAttribute("salary1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateSalaryGroup");

		return "projectcosts/salary_group_edit_easyui";
		
	}		
	

	@RequestMapping(params = "method=toEdit4DG")
	public String toEdit4DG(String full,HttpServletResponse res,HttpServletRequest request){
		request.setAttribute("full", full);

		String id= request.getParameter("id");
		request.setAttribute("id", id);
		
	
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		Salary salary = new Salary();
		salary.setProject_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));

		Date date1 = DateKit.fmtStrToDate(String.valueOf(salary.getSalary_month()*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<Salary> pager = salaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}else {				
			request.setAttribute("salary1", pager.getResultList().get(0));				
		}

			
		
		

		ParamExtend paramExtend = new ParamExtend();
		paramExtend.setGroup1(BusinessUtil.PARAM_GROUP_SALARY);	
		List<ParamExtend> paramExtends = paramExtendService.queryAllParamExtend(paramExtend);
		request.setAttribute("paramExtends", paramExtends);	
		
		
		return "projectcosts/salary_group_edit_dg";		
	}		


	@SuppressWarnings({  "rawtypes", "unchecked" })
	@RequestMapping(params = "method=edit4EasyuiDatagrid")
	public void edit4EasyuiDatagrid(HttpServletResponse res,HttpServletRequest request){
		
		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		Salary salary = new Salary();
		salary.setProject_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));
		

		Date date1 = DateKit.fmtStrToDate(String.valueOf(salary.getSalary_month()*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<Salary> pager = salaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(pager.getResultList() == null) {
			pager.setResultList(new ArrayList());
		}
		
		EasyUIDatagrid datagrid = new EasyUIDatagrid(pager.getResultList().size(),pager.getResultList());
		
		this.writeResJson(res, datagrid);
		
		
	}		
	

	@RequestMapping(params = "method=toView")
	public String toView(HttpServletResponse res,HttpServletRequest request){

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		Salary salary = new Salary();
		salary.setProject_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));
		

		Date date1 = DateKit.fmtStrToDate(String.valueOf(Integer.parseInt(array[0])*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<Salary> pager = salaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}

		salary = pager.getResultList().get(0);
		
		request.setAttribute("list", pager.getResultList());
		request.setAttribute("salary1", salary);
		request.setAttribute("next_operation", "updateSalaryGroup");
		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( salary.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.SALARY.name(), salary.getSalary_id());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.SALARY.name(),  salary.getSalary_id());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", salary.getVerify_userid());
		request.setAttribute("data_id", salary.getSalary_id());
		request.setAttribute("data_type", EnumEntityType.SALARY.name());	
		

		return "projectcosts/salary_group_view";
	}		
	

	@RequestMapping(params = "method=deleteSalaryGroup")
	public String deleteSalaryGroup(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);		
		}
		
		Salary[] salaryArray = new Salary[ids.length];
		int index = 0;
		for(String id : ids){
			Salary salary = new Salary();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			salary.setSalary_month(Integer.parseInt(array[0]));
			salary.setProject_id(array[1]);
			salaryArray[index] = salary;
			index ++ ;
		}
		
		if(salaryArray != null && salaryArray.length > 0 ) 
			salaryService.deleteSalary( salaryArray );

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
	

	@RequestMapping(params = "method=batchVerifySalary")
	public String batchVerifySalary(HttpServletResponse res,HttpServletRequest request){

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "请先选择工资单！",false);
		}
		
		Salary[] salaryArray = new Salary[ids.length];
		int index = 0;
		for(String id : ids){
			Salary salary = new Salary();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			salary.setSalary_month(Integer.parseInt(array[0]));
			salary.setProject_id(array[1]);
			salaryArray[index] = salary;
			index ++ ;
		}

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		User sessionUser = PubMethod.getUser(request);

		synchronized(this){		
			
			for(Salary salary : salaryArray){
				List<Salary> list = new ArrayList<Salary>();
				Pager<Salary> pager = salaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				
				for(Salary tmp : pager.getResultList() ){
					if(tmp.getVerify_userid() != null && tmp.getVerify_userid().length() > 0) {
						continue;
					}

					tmp.setVerify_datetime(PubMethod.getCurrentDate());
					tmp.setVerify_userid(sessionUser.getUser_id());
					tmp.setVerify_username(sessionUser.getUser_name());
					list.add(tmp);
					
				}
				
				try{
					if(!list.isEmpty())  {
						salaryService.verifySalary( list.toArray(new Salary[list.size()]) );

						ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.SALARY.name(), list.get(0).getSalary_id(), sessionUser);
						applyApproveService.addApplyApprove(applyApprove);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
		}
		

		return this.ajaxForwardSuccess(request, rel, false);

	}

	@RequestMapping(params = "method=verifySalary")
	public String verifySalary(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("salary_id");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "没有选择工资记录！",true);
		}

		User sessionUser = PubMethod.getUser(request);
		

		synchronized(this){
		
			List<Salary> salarys = new ArrayList<Salary>();
			for(String salary_id : ids){
				Salary salary = salaryService.getSalary(salary_id);
				if(salary == null) {continue;}
				if(salary.getVerify_userid() != null && salary.getVerify_userid().length() > 0) {
					continue;
				}
				
				salary.setVerify_datetime(PubMethod.getCurrentDate());
				salary.setVerify_userid(sessionUser.getUser_id());
				salary.setVerify_username(sessionUser.getUser_name());
				salarys.add(salary);
			}
	
			if(!salarys.isEmpty()) {
				salaryService.verifySalary( salarys.toArray(new Salary[salarys.size()]) );
				
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.SALARY.name(), salarys.get(0).getSalary_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}
		
		}

		return this.ajaxForwardSuccess(request, rel, true);		
		
	}	
	
	
}
