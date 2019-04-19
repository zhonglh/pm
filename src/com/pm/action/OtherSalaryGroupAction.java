package com.pm.action;

import com.alibaba.fastjson.JSON;
import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.enums.EnumYesNo;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.calculate.SalaryCalculate;
import com.pm.domain.business.*;
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.service.*;
import com.pm.util.PubMethod;
import com.pm.util.constant.*;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.UserPermit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "OtherSalaryGroupAction.do")
public class OtherSalaryGroupAction extends BaseAction {


	private static final String rel = "rel03";

	@Autowired
	private IOtherSalaryService otherSalaryService;

	@Autowired
	private IOtherWorkAttendanceService workAttendanceService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IDeptService deptService;



	@Autowired
	private IApplyApproveService applyApproveService;



	@Autowired
	@Qualifier("otherInsuranceServiceImpl")
	protected IInsuranceService insuranceService;

	@Autowired
	protected IStaffPerformanceService staffPerformanceService;


	/**
	 * 导出Excel
	 * @param searchSalary
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(OtherSalary searchSalary,HttpServletResponse res,HttpServletRequest request){


		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchSalary.setDept_id(dept_id);
		}

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALARYVIEW.getId());

		Pager<OtherSalary> pager = otherSalaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));


		List<List<OtherSalary>> lists = new ArrayList<List<OtherSalary>>();
		if(pager.getResultList() != null && pager.getResultList().size() >0){
			List<OtherSalary> salarys = pager.getResultList();
			List<OtherSalary> salarySameMonth = null;
			int salaryMonth = 0;
			String dept_name = null;
			for(OtherSalary salary : salarys){
				if(salary.getSalary_month() != salaryMonth){
					salarySameMonth = new ArrayList<OtherSalary>();
					lists.add(salarySameMonth);
					salaryMonth = salary.getSalary_month();
					dept_name = null;
				}
				if(!salary.getDept_name().equals(dept_name)){
					OtherSalary projectnameSalary = new OtherSalary();
					projectnameSalary.setSalary_month(salary.getSalary_month());
					projectnameSalary.setDept_name(salary.getDept_name());
					projectnameSalary.setShould_work_days(salary.getShould_work_days());
					salarySameMonth.add(projectnameSalary);
					dept_name = salary.getDept_name();
				}
				salarySameMonth.add(salary);
			}
		}

		try{
			BusinessExcel.exportSalary(res,  lists, OtherSalary.class);
		}catch(Exception e){
			e.printStackTrace();
		}

	}


	@RequestMapping(params = "method=isExist")
	public String isExist(OtherSalary searchSalary,HttpServletResponse res,HttpServletRequest request){
		paramprocess(request,searchSalary);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setAttendance_month(searchSalary.getSalary_month());
		workAttendance.setDept_id(searchSalary.getDept_id());
		workAttendance.setDept_name(searchSalary.getDept_name());
		workAttendance.setVerify_flag("1");

		Pager<OtherWorkAttendance> waPager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPager(request, OtherWorkAttendance.class));
		if(waPager.getResultList() == null || waPager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, workAttendance.getDept_name() + "在 "+workAttendance.getAttendance_month()+ " 的总部人员考勤单还没有制作完成！");
		}

		Pager<OtherSalary> pager= otherSalaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPager(request, OtherSalary.class));
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, searchSalary.getDept_name() + "在 "+searchSalary.getSalary_month()+ " 的总部人员工资记录已经制作，如果继续将会覆盖之前的工资记录！");
		}


		boolean b = staffPerformanceService.isExistNotCheckByWorkAttendance(searchSalary.getDept_id(), searchSalary.getSalary_month());
		if(b){
			return this.ajaxForwardConfirm(request, "该部门的绩效数据有些还没有核实，是否确定要继续？");
		}

		return this.ajaxForwardSuccess(request);
	}

	@RequestMapping(params = "method=list")
	public String list(OtherSalary searchSalary ,HttpServletResponse res,HttpServletRequest request){



		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchSalary.setDept_id(dept_id);
		}

		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchSalary.setDept_name(dept_name);
		}


		request.setAttribute("salary1", searchSalary);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYVIEW.getId());
		Pager<OtherSalary> pager= otherSalaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPager(request, OtherSalary.class));
		PubMethod.setRequestPager(request, pager,OtherSalary.class);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());


		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());




		return "headquarters/other_salary_group_list";

	}


	/**
	 * 跳转到选择月份的界面
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toAutoAddSalaryGroup")
	public String toAutoAddSalaryGroup(HttpServletResponse res,HttpServletRequest request){

		String salary_month_str = DateKit.fmtDateToStr(DateKit.addMonth(new Date(), -1),"yyyyMM");
		salary_month_str = salary_month_str.intern();
		int salary_month = Integer.parseInt(salary_month_str);
		request.setAttribute("salary_month" , salary_month);
		return "headquarters/other_salary_group_selectmonth";
	}


	/**
	 * 自动生成所有上个月的工资
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=autoAddSalaryGroup")
	public String autoAddSalaryGroup(int salary_month,HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		OtherSalary searchSalary = new OtherSalary();
		searchSalary.setSalary_month(salary_month);
		Pager<OtherSalary> pager= otherSalaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPager(request, OtherSalary.class));
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, searchSalary.getSalary_month()+ " 的工资记录已经制作，如果继续将会覆盖之前的工资记录！");
		}

		//检查上个月的人员绩效是否有未审核的
		if(staffPerformanceService.isExistNotCheckByWorkAttendance(null, salary_month)){
			return this.ajaxForwardError(request, "操作错误，该月份的人员绩效还有未审核的！",true);
		}

		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setAttendance_month(searchSalary.getSalary_month());
		workAttendance.setVerify_flag("1");

		Pager<OtherWorkAttendance> waPager = workAttendanceService.queryWorkAttendanceGroup(workAttendance, userPermit, PubMethod.getPagerByAll(OtherWorkAttendance.class));
		if(waPager == null || waPager.getResultList() == null || waPager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "操作错误，该月份的考勤没有已审核通过的！",true);
		}


		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		Dept searchDept = new Dept();
		searchDept.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Dept> depts = deptService.queryDept(searchDept, userPermit, PubMethod.getPagerByAll(request, Dept.class));

		Map<String,Dept>  deptMap = new HashMap<String,Dept>();
		for(Dept dept : depts.getResultList()){
			deptMap.put(dept.getDept_id(), dept);
		}

		List<List<OtherSalary>> lists = new ArrayList<List<OtherSalary>>();

		for(OtherWorkAttendance wa : waPager.getResultList()){

			OtherSalary salary1 = new OtherSalary();
			salary1.setDept_id(wa.getDept_id());
			salary1.setSalary_month(wa.getAttendance_month());

			//增加单个部门的工资信息
			List<OtherSalary> list = this.getSalarysByWorkAttendance(salary1);
			if(list != null && list.size() >0 ) {lists.add(list);}
			salary1 = null;
		}

		for(List<OtherSalary> list : lists){
			for(OtherSalary salary : list){

				Dept dept = deptMap.get(salary.getDept_id());
				if(dept != null){
					salary.setDept_name(dept.getDept_name());
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

			pager= otherSalaryService.querySalaryGroup(searchSalary, userPermit,PubMethod.getPagerByAll( OtherSalary.class));
			if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
				return this.ajaxForwardError(request, searchSalary.getSalary_month()+ " 的总部人员工资记录已经制作，继续将会覆盖之前的工资记录！");
			}

			for(List<OtherSalary> list : lists){
				otherSalaryService.addSalary(list);

				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(),
						EnumEntityType.OTHER_SALARY.name(), list.get(0).getSalary_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}

		}

		return this.ajaxForwardSuccess(request, rel, true);
	}



	@RequestMapping(params = "method=addSalaryGroup")
	public String addSalaryGroup(OtherSalary addSalary,HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("id");

		if(ids == null || ids.length ==0){
			return this.ajaxForwardError(request, "没有添加任何工资记录！",true);
		}

		Map<String, ParamExtend> paramExtMap = getParamExtendMap();


		List<OtherSalary> list = new ArrayList<OtherSalary>();
		for(String index : ids){
			OtherSalary salary = new OtherSalary();
			salary.setSalary_month(addSalary.getSalary_month());
			salary.setDept_id(addSalary.getDept_id());
			salary.setDept_name(addSalary.getDept_name());


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
			salary.setWaiting_post_days(Double.parseDouble(request.getParameter("waiting_post_days"+index)));
			salary.setMaternity_leave_days(Double.parseDouble(request.getParameter("maternity_leave_days"+index)));


			salary.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			salary.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			salary.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));
			salary.setSick_leave_salary(Double.parseDouble(request.getParameter("sick_leave_salary"+index)));
			salary.setWaiting_post_salary(Double.parseDouble(request.getParameter("waiting_post_salary"+index)));
			salary.setMaternity_leave_salary(Double.parseDouble(request.getParameter("maternity_leave_salary"+index)));

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


			list.add(salary);
		}



		Date date1 = DateKit.fmtStrToDate(String.valueOf(addSalary.getSalary_month()*100+1),"yyyyMMdd");
		computeBeforeInfo(date1 ,list);
		for(OtherSalary temp : list){
			SalaryCalculate.getInstance().calculate(temp, null, paramExtMap);
		}


		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		if(list != null && list.size() > 0 ) {
			synchronized (this) {
				Pager<OtherSalary> pager= otherSalaryService.querySalaryGroup(addSalary, userPermit,PubMethod.getPager(request, OtherSalary.class));
				if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
					return this.ajaxForwardError(request, addSalary.getDept_name() + "在 "+addSalary.getSalary_month()+ " 的工资记录已经制作，如果继续将会覆盖之前的工资记录！");
				}
				otherSalaryService.addSalary(list);
			}
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.OTHER_SALARY.name(), list.get(0).getSalary_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, true);

	}



	@RequestMapping(params = "method=updateSalaryGroup4Datagrid")
	public String updateSalaryGroup4Datagrid(String info,HttpServletResponse res,HttpServletRequest request){
		List<OtherSalary> salarys = null;
		if(info!=null && info.length()>0) {
			salarys = JSON.parseArray(info, OtherSalary.class);
		}
		if(salarys!=null && salarys.size()>0) {
			List<OtherSalary> updateSalarys = new ArrayList<OtherSalary>();
			List<OtherSalary> delSalarys = new ArrayList<OtherSalary>();
			for(OtherSalary salary : salarys){

				if(salary.getVerify_userid() != null && !salary.getVerify_userid().isEmpty()) {
					continue;
				}else {
					if("2".equals(salary.getId())){
						OtherSalary temp = new OtherSalary();
						temp.setSalary_id(salary.getSalary_id());
						delSalarys.add(temp);
					}else {
						updateSalarys.add(salary);
					}
				}
			}

			if(updateSalarys != null && !updateSalarys.isEmpty()) {

				Date date1 = DateKit.fmtStrToDate(String.valueOf(updateSalarys.get(0).getSalary_month()*100+1),"yyyyMMdd");
				computeBeforeInfo(date1 ,updateSalarys);
				Map<String, ParamExtend> paramExtMap = getParamExtendMap();
				for(OtherSalary temp : updateSalarys){
					SalaryCalculate.getInstance().calculate(temp, null, paramExtMap);
				}
				otherSalaryService.updateSalary(updateSalarys);
			}


			if(delSalarys != null && !delSalarys.isEmpty()) {
				otherSalaryService.deleteSalary(delSalarys.toArray(new OtherSalary[delSalarys.size()]));
			}

		}
		return this.ajaxForwardSuccess(request, rel, true);

	}

	@RequestMapping(params = "method=updateSalaryGroup")
	public String updateSalaryGroup(OtherSalary updateSalary,HttpServletResponse res,HttpServletRequest request){


		String[] salary_ids = request.getParameterValues("salary_id");
		List<String> salaryList = new ArrayList<String>();
		List<OtherSalary> delSalarys= new ArrayList<OtherSalary>();

		if(salary_ids != null && salary_ids.length > 0){
			Collections.addAll(salaryList, salary_ids);
		}

		String[] ids = request.getParameterValues("id");

		if(ids == null || ids.length ==0){
			return this.ajaxForwardSuccess(request);
		}


		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		List<OtherSalary> list = new ArrayList<OtherSalary>();
		for(String index : ids){

			if(index.equals("0")) {continue;}

			OtherSalary salary = new OtherSalary();
			salary.setSalary_id(request.getParameter("salary_id"+index));
			if(!salaryList.contains(salary.getSalary_id())){
				delSalarys.add(salary);
				continue;
			}


			salary.setSalary_month(updateSalary.getSalary_month());
			salary.setDept_id(updateSalary.getDept_id());
			salary.setDept_name(updateSalary.getDept_name());

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
			salary.setWaiting_post_days(Double.parseDouble(request.getParameter("waiting_post_days"+index)));
			salary.setMaternity_leave_days(Double.parseDouble(request.getParameter("maternity_leave_days"+index)));

			salary.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			salary.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			salary.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));
			salary.setSick_leave_salary(Double.parseDouble(request.getParameter("sick_leave_salary"+index)));
			salary.setWaiting_post_salary(Double.parseDouble(request.getParameter("waiting_post_salary"+index)));
			salary.setMaternity_leave_salary(Double.parseDouble(request.getParameter("maternity_leave_salary"+index)));

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


			list.add(salary);
		}



		Date date1 = DateKit.fmtStrToDate(String.valueOf(updateSalary.getSalary_month()*100+1),"yyyyMMdd");
		computeBeforeInfo(date1 ,list);
		for(OtherSalary temp : list){
			SalaryCalculate.getInstance().calculate(temp, null, paramExtMap);
		}


		if(!list.isEmpty()) {
			otherSalaryService.updateSalary(list);
		}

		if(!delSalarys.isEmpty()){
			OtherSalary[] salarys = new OtherSalary[delSalarys.size()];
			int index = 0;
			for(OtherSalary salary : delSalarys){
				salarys[index] = salary;
				index ++;
			}
			otherSalaryService.deleteSalary(salarys);
		}

		return this.ajaxForwardSuccess(request, rel, true);
	}



	private void paramprocess(HttpServletRequest request,OtherSalary salary){
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)) {
			salary.setDept_id(dept_id);
		}

		if(salary.getDept_name() == null || salary.getDept_name().isEmpty()) {
			salary.setDept_name(request.getParameter("dept.dept_name"));
		}
	}


	@RequestMapping(params = "method=toEditFirst")
	public String toEditFirst(HttpServletResponse res,HttpServletRequest request){

		String salary_month = DateKit.fmtDateToStr(DateKit.addMonth(new Date(), -1),"yyyyMM");

		request.setAttribute("salary_month", salary_month);

		return "headquarters/other_salary_group_edit1";
	}


	@RequestMapping(params = "method=toEditNext")
	public String toEditNext(OtherSalary salary,HttpServletResponse res,HttpServletRequest request){

		OtherSalary salary1 = salary;
		paramprocess(request,salary1);
		User sessionUser = PubMethod.getUser(request);


		Map<String, ParamExtend> paramExtMap = getParamExtendMap();

		List<OtherSalary> list = getSalarysByWorkAttendance(salary1);

		for(OtherSalary temp : list){
			SalaryCalculate.getInstance().calculate(temp, null, paramExtMap);
		}


		salary1.setBuild_username(sessionUser.getUser_name());
		salary1.setBuild_datetime(PubMethod.getCurrentDate());

		request.setAttribute("list", list);
		request.setAttribute("salary1", salary1);
		request.setAttribute("next_operation", "addSalaryGroup");

		return "headquarters/other_salary_group_edit2";
	}

	/**
	 * 根据考勤和人员绩效，计算出工资初始信息
	 * @param salary1
	 * @return
	 */
	private List<OtherSalary> getSalarysByWorkAttendance(OtherSalary salary1) {

		Date date1 = DateKit.fmtStrToDate(String.valueOf(salary1.getSalary_month()*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary1.setDate1(date1);
		salary1.setDate2(date2);

		List<OtherSalary> list = otherSalaryService.getSalaryByDeptMonth(salary1);
		if(list == null) {
			return new ArrayList<OtherSalary>();
		}

		Insurance searchInsurance = new Insurance();
		searchInsurance.setDept_id(salary1.getDept_id());
		searchInsurance.setSalary_month(salary1.getSalary_month());
		List<Insurance> insurances = insuranceService.queryInsuranceByWorkAttendance(searchInsurance);

		Map<String,Insurance> insuranceMap = new HashMap<String,Insurance>();
		if(insurances != null && insurances.size() > 0 ){
			for(Insurance insurance : insurances){
				insuranceMap.put(insurance.getStaff_id(), insurance);
			}
		}

		//查询出人员绩效
		StaffPerformance searchStaffPerformance = new StaffPerformance();
		searchStaffPerformance.setDept_id(salary1.getDept_id());
		searchStaffPerformance.setThe_month(salary1.getSalary_month());
		searchStaffPerformance.setVerify_flag(EnumYesNo.Yes.getCode());
		List<StaffPerformance> staffPerformances = staffPerformanceService.getStaffPerformanceList(searchStaffPerformance);
		Map<String,StaffPerformance> staffPerformanceMap = new HashMap<String,StaffPerformance>();
		if(staffPerformances != null){
			for(StaffPerformance staffPerformance : staffPerformances){
				staffPerformanceMap.put(staffPerformance.getStaff_id(), staffPerformance);
			}
		}


		//查询出人事月报
		List<PersonnelMonthlySalary> personnelMonthlySalarys = otherSalaryService.getCurrSalaryByWorkAttendance(salary1);
		Map<String,PersonnelMonthlySalary> personnelMonthlyMap = new HashMap<String,PersonnelMonthlySalary>();
		if(personnelMonthlySalarys != null){
			for(PersonnelMonthlySalary personnelMonthlySalary : personnelMonthlySalarys){
				personnelMonthlyMap.put(personnelMonthlySalary.getStaff_id(), personnelMonthlySalary);
			}
		}


		for(OtherSalary tempSalary : list){


			StaffPerformance staffPerformance = staffPerformanceMap.get(tempSalary.getStaff_id());
			if (staffPerformance != null) {
				tempSalary.setPerformance_allowances(staffPerformance.getPerformance_salary());
			}else {
				PersonnelMonthlySalary personnelMonthlySalary = personnelMonthlyMap.get(tempSalary.getStaff_id());
				if (personnelMonthlySalary != null) {
					double performance_allowances = personnelMonthlySalary.getCurr_salary() - tempSalary.getBasic_salary() - tempSalary.getPost_salary();
					performance_allowances = PubMethod.getNumberFormatByDouble(performance_allowances);
					tempSalary.setPerformance_allowances(performance_allowances);

					if (StringUtils.isNotEmpty(personnelMonthlySalary.getDescription())) {
						if (StringUtils.isEmpty(tempSalary.getDescription())) {
							tempSalary.setDescription(personnelMonthlySalary.getDescription());
						} else {
							tempSalary.setDescription(tempSalary.getDescription() + " " + personnelMonthlySalary.getDescription());
						}
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


		computeBeforeInfo(date1, list);


		return list;
	}

	/**
	 * 计算本年份之前的累计数据， 工资月份从12月到下一年的11月为一年
	 * @param date1
	 * @param list
	 */
	private void computeBeforeInfo(Date date1, List<OtherSalary> list) {
		if(list == null || list.isEmpty()){
			return ;
		}

		int month = DateKit.getMonth(date1);
		if(month != 12) {
			//计算之前的累计
			Date startDate = DateKit.getLastMonthStart(DateKit.getYearStart(date1));
			Date endDate = DateKit.getLastMonthStart(date1);
			int startSalaryMonth = Integer.parseInt(DateKit.fmtDateToStr(startDate,"yyyyMM"));
			int endSalaryMonth = Integer.parseInt(DateKit.fmtDateToStr(endDate,"yyyyMM"));
			List<String> staffCostIds = new ArrayList<String>();
			for (OtherSalary salary : list) {
				staffCostIds.add(salary.getStaff_id());
			}
			List<OtherSalary> accumulatedSalaryInfos = otherSalaryService.getAccumulatedSalary(startSalaryMonth, endSalaryMonth, staffCostIds);
			Map<String, OtherSalary> accumulatedSalaryMap = new HashMap<String, OtherSalary>();
			if(accumulatedSalaryInfos!=null && !accumulatedSalaryInfos.isEmpty()) {
				for (OtherSalary salary : accumulatedSalaryInfos) {
					accumulatedSalaryMap.put(salary.getStaff_id(), salary);
				}
			}
			for (OtherSalary salary : list) {
				OtherSalary accumulatedSalary = accumulatedSalaryMap.get(salary.getStaff_id());
				if (accumulatedSalary != null) {
					salary.setBefore_accumulated_pretax_income(accumulatedSalary.getBefore_accumulated_pretax_income());
					salary.setBefore_accumulated_tax_deduction(accumulatedSalary.getBefore_accumulated_tax_deduction());
					salary.setBefore_accumulated_children_education(accumulatedSalary.getBefore_accumulated_children_education());
					salary.setBefore_accumulated_continuing_education(accumulatedSalary.getBefore_accumulated_continuing_education());
					salary.setBefore_accumulated_housing_loans(accumulatedSalary.getBefore_accumulated_housing_loans());
					salary.setBefore_accumulated_housing_rent(accumulatedSalary.getBefore_accumulated_housing_rent());
					salary.setBefore_accumulated_support_elderly(accumulatedSalary.getBefore_accumulated_support_elderly());
					salary.setBefore_accumulated_deductions_cost(accumulatedSalary.getBefore_accumulated_deductions_cost());
					salary.setAccumulated_prepaid_tax(accumulatedSalary.getAccumulated_prepaid_tax());
				}else {
					salary.setBefore_accumulated_tax_deduction(5000);
				}

			}
		}
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(HttpServletResponse res,HttpServletRequest request){

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);

		OtherSalary salary = new OtherSalary();
		salary.setDept_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));


		Date date1 = DateKit.fmtStrToDate(String.valueOf(Integer.parseInt(array[0])*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		Pager<OtherSalary> pager = otherSalaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}

		this.computeBeforeInfo(date1 , pager.getResultList());

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("salary1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateSalaryGroup");

		return "headquarters/other_salary_group_edit";

	}




	@RequestMapping(params = "method=toEdit4Easyui")
	public String toEdit4Easyui(HttpServletResponse res,HttpServletRequest request){

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);

		OtherSalary salary = new OtherSalary();
		salary.setDept_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));

		Date date1 = DateKit.fmtStrToDate(String.valueOf(Integer.parseInt(array[0])*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		Pager<OtherSalary> pager = otherSalaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}

		this.computeBeforeInfo(date1 , pager.getResultList());

		request.setAttribute("id", id);
		//request.setAttribute("list", pager.getResultList());
		request.setAttribute("salary1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateSalaryGroup");

		return "headquarters/other_salary_group_edit_easyui";

	}


	@RequestMapping(params = "method=toEdit4DG")
	public String toEdit4DG(String full,HttpServletResponse res,HttpServletRequest request){
		request.setAttribute("full", full);

		String id= request.getParameter("id");
		request.setAttribute("id", id);


		String[] array = id.split(BusinessUtil.SPLITSTRING);

		OtherSalary salary = new OtherSalary();
		salary.setDept_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));

		Date date1 = DateKit.fmtStrToDate(String.valueOf(salary.getSalary_month()*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		Pager<OtherSalary> pager = otherSalaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}else {
			request.setAttribute("salary1", pager.getResultList().get(0));
		}


		this.computeBeforeInfo(date1 , pager.getResultList());



		ParamExtend paramExtend = new ParamExtend();
		paramExtend.setGroup1(BusinessUtil.PARAM_GROUP_SALARY);
		List<ParamExtend> paramExtends = paramExtendService.queryAllParamExtend(paramExtend);
		request.setAttribute("paramExtends", paramExtends);


		return "headquarters/other_salary_group_edit_dg";
	}


	@SuppressWarnings({  "rawtypes", "unchecked" })
	@RequestMapping(params = "method=edit4EasyuiDatagrid")
	public void edit4EasyuiDatagrid(HttpServletResponse res,HttpServletRequest request){

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);

		OtherSalary salary = new OtherSalary();
		salary.setDept_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));


		Date date1 = DateKit.fmtStrToDate(String.valueOf(salary.getSalary_month()*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		Pager<OtherSalary> pager = otherSalaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));
		if(pager.getResultList() == null) {
			pager.setResultList(new ArrayList());
		}

		this.computeBeforeInfo(date1 , pager.getResultList());

		EasyUIDatagrid datagrid = new EasyUIDatagrid(pager.getResultList().size(),pager.getResultList());

		this.writeResJson(res, datagrid);


	}


	@RequestMapping(params = "method=toView")
	public String toView(HttpServletResponse res,HttpServletRequest request){

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);

		OtherSalary salary = new OtherSalary();
		salary.setDept_id(array[1]);
		salary.setSalary_month(Integer.parseInt(array[0]));


		Date date1 = DateKit.fmtStrToDate(String.valueOf(Integer.parseInt(array[0])*100+1),"yyyyMMdd");
		Date date2 = DateKit.getLastDayOfMonth(date1);
		salary.setDate1(date1);
		salary.setDate2(date2);


		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYVIEW.getId());

		Pager<OtherSalary> pager = otherSalaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何工资记录！",true);
		}

		salary = pager.getResultList().get(0);

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("salary1", salary);
		request.setAttribute("next_operation", "updateSalaryGroup");


		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYCHECK.getId());
		if(EnumDataRange.SELF.getCode().equals(userPermit1.getRange())){
			userPermit1.setPermit_id("");
		}
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSALARYUNCHECK.getId());
		if(EnumDataRange.SELF.getCode().equals(userPermit1.getRange())){
			userPermit1.setPermit_id("");
		}
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());

		User sessionUser = PubMethod.getUser(request);
		Dept dept = deptService.getDept( salary.getDept_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.OTHER_SALARY.name(), salary.getSalary_id());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.OTHER_SALARY.name(),  salary.getSalary_id());

		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("dept", dept);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", salary.getVerify_userid());
		request.setAttribute("data_id", salary.getSalary_id());
		request.setAttribute("data_type", EnumEntityType.OTHER_SALARY.name());


		return "headquarters/other_salary_group_view";
	}


	@RequestMapping(params = "method=deleteSalaryGroup")
	public String deleteSalaryGroup(HttpServletResponse res,HttpServletRequest request){

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);
		}

		OtherSalary[] salaryArray = new OtherSalary[ids.length];
		int index = 0;
		for(String id : ids){
			OtherSalary salary = new OtherSalary();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			salary.setSalary_month(Integer.parseInt(array[0]));
			salary.setDept_id(array[1]);
			salaryArray[index] = salary;
			index ++ ;
		}

		if(salaryArray != null && salaryArray.length > 0 ) {
			otherSalaryService.deleteSalary(salaryArray);
		}

		return this.ajaxForwardSuccess(request, rel, false);

	}



	@RequestMapping(params = "method=batchVerifySalary")
	public String batchVerifySalary(HttpServletResponse res,HttpServletRequest request){

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "请先选择工资单！",false);
		}

		OtherSalary[] salaryArray = new OtherSalary[ids.length];
		int index = 0;
		for(String id : ids){
			OtherSalary salary = new OtherSalary();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			salary.setSalary_month(Integer.parseInt(array[0]));
			salary.setDept_id(array[1]);
			salaryArray[index] = salary;
			index ++ ;
		}

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		User sessionUser = PubMethod.getUser(request);

		synchronized(this){

			for(OtherSalary salary : salaryArray){
				List<OtherSalary> list = new ArrayList<OtherSalary>();
				Pager<OtherSalary> pager = otherSalaryService.querySalary(salary, userPermit, PubMethod.getPagerByAll(request, OtherSalary.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}

				for(OtherSalary tmp : pager.getResultList() ){
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
						otherSalaryService.verifySalary( list.toArray(new OtherSalary[list.size()]) );

						ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.OTHER_SALARY.name(), list.get(0).getSalary_id(), sessionUser);
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

			List<OtherSalary> salarys = new ArrayList<OtherSalary>();
			for(String salary_id : ids){
				OtherSalary salary = otherSalaryService.getSalary(salary_id);
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
				otherSalaryService.verifySalary( salarys.toArray(new OtherSalary[salarys.size()]) );

				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.OTHER_SALARY.name(), salarys.get(0).getSalary_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}

		}

		return this.ajaxForwardSuccess(request, rel, true);

	}


}
