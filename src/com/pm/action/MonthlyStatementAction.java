package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.MonthlyStatementDetail;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.Project;
import com.pm.domain.business.WorkAttendance;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IInvoiceService;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IOtherStaffService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.service.IWorkAttendanceService;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "MonthlyStatementAction.do")
public class MonthlyStatementAction extends BaseAction {

	private static final String rel = "rel30";
	
	@Autowired
	private IMonthlyStatementService monthlyStatementService;

	@Autowired
	private IApplyApproveService applyApproveService;

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IWorkAttendanceService workAttendanceService;
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private IInvoiceService invoiceService;
	

	@Autowired
	private IOtherStaffService otherStaffService;
	

	@RequestMapping(params = "method=isExist")
	public String isExist(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){
		paramprocess(request,monthlyStatement);	
		boolean b = monthlyStatementService.isExist(monthlyStatement);
		if(!b){
			if(BusinessUtil.STATEMENT_TYPE_2.equals(monthlyStatement.getStatement_type())) {
				UserPermit userPermit = new UserPermit();
				userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
				WorkAttendance workAttendance = new WorkAttendance();
				workAttendance.setAttendance_month(monthlyStatement.getStatement_month());
				workAttendance.setProject_id(monthlyStatement.getProject_id());
				workAttendance.setProject_name(monthlyStatement.getProject_name());
				workAttendance.setVerify_flag("1");
				Pager<WorkAttendance> waPager = workAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPager(request, WorkAttendance.class));
				if(waPager.getResultList() == null || waPager.getResultList().isEmpty()){
					return this.ajaxForwardError(request, workAttendance.getProject_name() + "在 "+workAttendance.getAttendance_month()+ " 的考勤单还没有制作完成！");
				}else {
					return this.ajaxForwardSuccess(request);
				}
			}else {
				return this.ajaxForwardSuccess(request);
			}
		}else {
			return this.ajaxForwardError(request, monthlyStatement.getProject_name() + "在 "+monthlyStatement.getStatement_month()+ " 的月度结算单已经制作！");
		}
	}

	@RequestMapping(params = "method=list")
	public String list(MonthlyStatement searchMonthlyStatement,HttpServletResponse res,HttpServletRequest request){
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchMonthlyStatement.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchMonthlyStatement.setDept_name(dept_name);
		}
		
		request.setAttribute("searchMonthlyStatement1", searchMonthlyStatement);
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTVIEW.getId());	
		Pager<MonthlyStatement> pager= monthlyStatementService.queryMonthlyStatement(searchMonthlyStatement, userPermit,PubMethod.getPager(request, MonthlyStatement.class));
		PubMethod.setRequestPager(request, pager,MonthlyStatement.class);
		

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());	
		

		UserPermit otherPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());	
		List<OtherStaff> saless = otherStaffService.queryOtherStaffByProjectSales(new OtherStaff(),otherPermit);
		request.setAttribute("saless", saless);	
		
		return "projectincome/monthly_statement_list";		
		
	}
	
	
	

	/**
	 * 导出Excel
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(MonthlyStatement searchMonthlyStatement,HttpServletResponse res,HttpServletRequest request){
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchMonthlyStatement.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchMonthlyStatement.setDept_name(dept_name);
		}

		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTVIEW.getId());	
		Pager<MonthlyStatement> pager= monthlyStatementService.queryMonthlyStatement(searchMonthlyStatement, userPermit,PubMethod.getPagerByAll(request, MonthlyStatement.class));
		PubMethod.setRequestPager(request, pager,MonthlyStatement.class);
		
		
		if(pager.getResultList() != null && pager.getResultList().size() >0){
			for(MonthlyStatement monthlyStatement : pager.getResultList()){
				monthlyStatement.setProject_type(this.getMsg("project.type."+monthlyStatement.getProject_type(), request));
				monthlyStatement.setStatement_type(this.getMsg("statement.type."+monthlyStatement.getStatement_type(), request));
			}
			
			
		}
		
		try{
			BusinessExcel.export(res,  pager.getResultList(), MonthlyStatement.class);
		}catch(Exception e){
			
		}
		
	}	
	
	
	

	/**
	 * 导出明细Excel
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportDetail")
	public void exportDetail(MonthlyStatement searchMonthlyStatement,HttpServletResponse res,HttpServletRequest request){


		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchMonthlyStatement.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchMonthlyStatement.setDept_name(dept_name);
		}

		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTVIEW.getId());	
		Pager<MonthlyStatementDetail> pager= monthlyStatementService.queryMonthlyStatementDetail(searchMonthlyStatement, userPermit,PubMethod.getPagerByAll(request, MonthlyStatementDetail.class));
		PubMethod.setRequestPager(request, pager,MonthlyStatementDetail.class);
		
		
		
		try{
			

			List<String> heads = new ArrayList<String>();
			heads.add("费用结算清单");
			BusinessExcel.export(res,  heads,pager.getResultList(), MonthlyStatementDetail.class);
		}catch(Exception e){
			
		}
		
	}	
	

	@RequestMapping(params = "method=search")
	public String search(HttpServletResponse res,HttpServletRequest request){
		
		MonthlyStatement searchMonthlyStatement = new MonthlyStatement();
		searchMonthlyStatement.setProject_name(request.getParameter("project_name"));
		searchMonthlyStatement.setVerify_flag(request.getParameter("verify_flag"));
		String statement_month = request.getParameter("statement_month");
		if(statement_month != null && statement_month.length() > 0){
			try{
			searchMonthlyStatement.setStatement_month(Integer.parseInt(statement_month));
			}catch(Exception e){}
		}
		
		searchMonthlyStatement.setVerify_flag("1");
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTVIEW.getId());	
		Pager<MonthlyStatement> pager= monthlyStatementService.queryMonthlyStatement(searchMonthlyStatement, userPermit,PubMethod.getPager(request, MonthlyStatement.class));
		PubMethod.setRequestPager(request, pager,MonthlyStatement.class);
		
		return "projectincome/monthly_statement_search";		
		
	}	
	
	

	@RequestMapping(params = "method=to")
	public String to(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){		
		
		if(monthlyStatement.getMonthly_statement_id() == null || monthlyStatement.getMonthly_statement_id().isEmpty()){
			if(BusinessUtil.STATEMENT_TYPE_2.equals(monthlyStatement.getStatement_type())){
				return this.toEditNext(monthlyStatement, res, request);
			}else {
				return this.addMonthlyStatement(monthlyStatement, res, request);
			}
		}else {
			if(BusinessUtil.STATEMENT_TYPE_2.equals(monthlyStatement.getStatement_type())){
				return this.toEditNext(monthlyStatement, res, request);
			}else {
				return this.updateMonthlyStatement(monthlyStatement, res, request);
			}
		}
		
	}

	@RequestMapping(params = "method=addMonthlyStatement")
	public String addMonthlyStatement(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){	

		paramprocess(request,monthlyStatement);
		User sessionUser = PubMethod.getUser(request);		
		monthlyStatement.setMonthly_statement_id(IDKit.generateId());
		
		String[] ids = request.getParameterValues("id");		
		List<MonthlyStatementDetail> list = new ArrayList<MonthlyStatementDetail>();
		if(ids != null && ids.length >0) {
			for(String index : ids){
				
				MonthlyStatementDetail monthlyStatementDetail = new MonthlyStatementDetail();
				
				monthlyStatementDetail.setStaff_id(request.getParameter("staff_id"+index));
				monthlyStatementDetail.setStaff_name(request.getParameter("staff_name"+index));
	
				monthlyStatementDetail.setTechnical_cost(Double.parseDouble(request.getParameter("technical_cost"+index)));
				monthlyStatementDetail.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
				monthlyStatementDetail.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
				
				monthlyStatementDetail.setDaily_overtime(Double.parseDouble(request.getParameter("daily_overtime"+index)));
				monthlyStatementDetail.setWeekend_overtime(Double.parseDouble(request.getParameter("weekend_overtime"+index)));
				monthlyStatementDetail.setOvertime_cost(Double.parseDouble(request.getParameter("overtime_cost"+index)));
				monthlyStatementDetail.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
				monthlyStatementDetail.setBusiness_trip_cost(Double.parseDouble(request.getParameter("business_trip_cost"+index)));
				monthlyStatementDetail.setOther_cost(Double.parseDouble(request.getParameter("other_cost"+index)));
				monthlyStatementDetail.setMan_month(Double.parseDouble(request.getParameter("man_month"+index)));
				monthlyStatementDetail.setTotal_cost(Double.parseDouble(request.getParameter("total_cost"+index)));				

				monthlyStatementDetail.setClient_dept(request.getParameter("client_dept"+index));
				monthlyStatementDetail.setDescription(request.getParameter("description"+index));
	
				monthlyStatementDetail.setProject_id(monthlyStatement.getProject_id());
				monthlyStatementDetail.setMonthly_statement_id(monthlyStatement.getMonthly_statement_id());
				monthlyStatementDetail.setMonthly_statement_detail_id(IDKit.generateId());
				monthlyStatementDetail.setBuild_datetime(PubMethod.getCurrentDate());
				monthlyStatementDetail.setBuild_userid(sessionUser.getUser_id());
				monthlyStatementDetail.setBuild_username(sessionUser.getUser_name());	
				list.add(monthlyStatementDetail);
			}
		}

		monthlyStatement.setBuild_datetime(PubMethod.getCurrentDate());
		monthlyStatement.setBuild_userid(sessionUser.getUser_id());
		monthlyStatement.setBuild_username(sessionUser.getUser_name());	
		
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			monthlyStatementService.addMonthlyStatement(monthlyStatement,list);			
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement.getMonthly_statement_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		
		request.setAttribute("continue", false);
		return this.ajaxForwardSuccess(request, rel, true);		
	}
	

	@RequestMapping(params = "method=updateMonthlyStatement")
	public String updateMonthlyStatement(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){	

		paramprocess(request,monthlyStatement);
		
		//判断项目是否更换了， 更换项目提示错误
		MonthlyStatement old = monthlyStatementService.getMonthlyStatement(monthlyStatement);
		if(old != null){
			if(!old.getProject_id().equals(monthlyStatement.getProject_id())){
				return this.ajaxForwardError(request, "项目不能更换！", false);
			}
		}

		String[] ids = request.getParameterValues("id");
		
		List<MonthlyStatementDetail> list = new ArrayList<MonthlyStatementDetail>();
		if(ids != null && ids.length >0) {
			for(String index : ids){
				
				//if("0".equals(index)) continue;
				
				MonthlyStatementDetail monthlyStatementDetail = new MonthlyStatementDetail();
				
				
				monthlyStatementDetail.setStaff_id(request.getParameter("staff_id"+index));
				monthlyStatementDetail.setStaff_name(request.getParameter("staff_name"+index));
	
				monthlyStatementDetail.setTechnical_cost(Double.parseDouble(request.getParameter("technical_cost"+index)));
				monthlyStatementDetail.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
				monthlyStatementDetail.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
				
				monthlyStatementDetail.setDaily_overtime(Double.parseDouble(request.getParameter("daily_overtime"+index)));
				monthlyStatementDetail.setWeekend_overtime(Double.parseDouble(request.getParameter("weekend_overtime"+index)));
				monthlyStatementDetail.setOvertime_cost(Double.parseDouble(request.getParameter("overtime_cost"+index)));
				monthlyStatementDetail.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
				monthlyStatementDetail.setBusiness_trip_cost(Double.parseDouble(request.getParameter("business_trip_cost"+index)));				
				monthlyStatementDetail.setOther_cost(Double.parseDouble(request.getParameter("other_cost"+index)));
				monthlyStatementDetail.setMan_month(Double.parseDouble(request.getParameter("man_month"+index)));
				monthlyStatementDetail.setTotal_cost(Double.parseDouble(request.getParameter("total_cost"+index)));

				monthlyStatementDetail.setClient_dept(request.getParameter("client_dept"+index));
				monthlyStatementDetail.setDescription(request.getParameter("description"+index));
	
				monthlyStatementDetail.setProject_id(monthlyStatement.getProject_id());
				monthlyStatementDetail.setMonthly_statement_id(monthlyStatement.getMonthly_statement_id());
				monthlyStatementDetail.setMonthly_statement_detail_id(request.getParameter("monthly_statement_detail_id"+index));

				list.add(monthlyStatementDetail);
			}
		}
		

		User sessionUser = PubMethod.getUser(request);
		try{
			ThreadLocalUser.setUser(sessionUser);
			monthlyStatementService.updateMonthlyStatement(monthlyStatement,list);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		request.setAttribute("continue", false);
		return this.ajaxForwardSuccess(request, rel, true);
	}

	

	private void paramprocess(HttpServletRequest request,MonthlyStatement monthlyStatement){	
		if(monthlyStatement.getProject_id() == null || monthlyStatement.getProject_id().isEmpty())
		monthlyStatement.setProject_id(request.getParameter("project.project_id"));	

		if(monthlyStatement.getProject_name() == null || monthlyStatement.getProject_name().isEmpty())
		monthlyStatement.setProject_name(request.getParameter("project.project_name"));

		if(monthlyStatement.getProject_no() == null || monthlyStatement.getProject_no().isEmpty())
		monthlyStatement.setProject_no(request.getParameter("project.project_no"));
		
		if(monthlyStatement.getProject_type() == null || monthlyStatement.getProject_type().isEmpty())
		monthlyStatement.setProject_type(request.getParameter("project.project_type"));
	}

	
	@RequestMapping(params = "method=toEditFirst")
	public String toEditFirst(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){	
		
		String monthly_statement_id = request.getParameter("monthly_statement_id");
		if(monthly_statement_id == null || monthly_statement_id.isEmpty()){
			String attendance_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
			monthlyStatement = new MonthlyStatement();
			monthlyStatement.setStatement_month(Integer.parseInt(attendance_month));

			User sessionUser = PubMethod.getUser(request);	
			monthlyStatement.setBuild_datetime(PubMethod.getCurrentDate());
			monthlyStatement.setBuild_userid(sessionUser.getUser_id());
			monthlyStatement.setBuild_username(sessionUser.getUser_name());	
			
			request.setAttribute("next_operation", "addMonthlyStatement");	
		}else {
			monthlyStatement = monthlyStatementService.getMonthlyStatement(monthlyStatement);
			request.setAttribute("next_operation", "updateMonthlyStatement");
			if(monthlyStatement.getVerify_userid() != null && monthlyStatement.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}			
		}
		
		request.setAttribute("monthlyStatement1", monthlyStatement);		
		
		return "projectincome/monthly_statement_edit1";		
	}
	

	@RequestMapping(params = "method=toEditNext")
	public String toEditNext(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){	
		
		List<MonthlyStatementDetail> list = null; 
		
		MonthlyStatement monthlyStatement1 = monthlyStatement;		
		paramprocess(request,monthlyStatement1);
		
		
		if(monthlyStatement1.getMonthly_statement_id() == null || monthlyStatement1.getMonthly_statement_id().isEmpty()){		
			User sessionUser = PubMethod.getUser(request);
			monthlyStatement1.setBuild_username(sessionUser.getUser_name());
			monthlyStatement1.setBuild_datetime(PubMethod.getCurrentDate());		
			request.setAttribute("next_operation", "addMonthlyStatement");	
			list = monthlyStatementService.computeMonthlyStatementDetail(monthlyStatement1);
		}else {
			list = monthlyStatementService.getMonthlyStatementDetail(monthlyStatement1);
			if(list == null || list.size() == 0){
				list = monthlyStatementService.computeMonthlyStatementDetail(monthlyStatement1);				
			}
			request.setAttribute("next_operation", "updateMonthlyStatement");
			MonthlyStatement tmp = monthlyStatementService.getMonthlyStatement(monthlyStatement1);
			if(tmp != null){
				monthlyStatement1.setBuild_datetime(tmp.getBuild_datetime());
				monthlyStatement1.setBuild_username(tmp.getBuild_username());
				monthlyStatement1.setBuild_userid(tmp.getBuild_userid());
				if(!tmp.getProject_id().equals(monthlyStatement1.getProject_id())){
					return this.ajaxForwardError(request, "单据不能够修改项目信息！", false);
				}
			}
		}
		
		if(list == null) list = new ArrayList<MonthlyStatementDetail>();		
		
		request.setAttribute("list", list);
		request.setAttribute("monthlyStatement1", monthlyStatement1);
		
		return "projectincome/monthly_statement_edit2";
	}
	


	@RequestMapping(params = "method=toView")
	public String toView(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){	
		
		List<MonthlyStatementDetail> list = null; 
		
		MonthlyStatement monthlyStatement1 = monthlyStatementService.getMonthlyStatement(monthlyStatement);
		list = monthlyStatementService.getMonthlyStatementDetail(monthlyStatement1);
		if(list == null) list = new ArrayList<MonthlyStatementDetail>();
		
		
		MonthlyStatementDetail totalMonthlyStatementDetail = new MonthlyStatementDetail();
		for(MonthlyStatementDetail monthlyStatementDetail : list){
			totalMonthlyStatementDetail.setTechnical_cost(totalMonthlyStatementDetail.getTechnical_cost()+monthlyStatementDetail.getTechnical_cost());
			totalMonthlyStatementDetail.setTotal_cost(totalMonthlyStatementDetail.getTotal_cost()+monthlyStatementDetail.getTotal_cost());
			totalMonthlyStatementDetail.setDaily_overtime(totalMonthlyStatementDetail.getDaily_overtime()+monthlyStatementDetail.getDaily_overtime());
			totalMonthlyStatementDetail.setMan_month(totalMonthlyStatementDetail.getMan_month()+monthlyStatementDetail.getMan_month());
			totalMonthlyStatementDetail.setOther_cost(totalMonthlyStatementDetail.getOther_cost()+monthlyStatementDetail.getOther_cost());
			totalMonthlyStatementDetail.setOvertime_cost(totalMonthlyStatementDetail.getOvertime_cost()+monthlyStatementDetail.getOvertime_cost());
			totalMonthlyStatementDetail.setShould_work_days(totalMonthlyStatementDetail.getShould_work_days()+monthlyStatementDetail.getShould_work_days());
			totalMonthlyStatementDetail.setWeekend_overtime(totalMonthlyStatementDetail.getWeekend_overtime()+monthlyStatementDetail.getWeekend_overtime());
			totalMonthlyStatementDetail.setWork_days(totalMonthlyStatementDetail.getWork_days()+monthlyStatementDetail.getWork_days());			
			totalMonthlyStatementDetail.setBusiness_trip_cost(totalMonthlyStatementDetail.getBusiness_trip_cost()+monthlyStatementDetail.getBusiness_trip_cost());
			totalMonthlyStatementDetail.setBusiness_trip_days(totalMonthlyStatementDetail.getBusiness_trip_days()+monthlyStatementDetail.getBusiness_trip_days());			
		}
		
		request.setAttribute("list", list);
		request.setAttribute("totalMonthlyStatementDetail", totalMonthlyStatementDetail);
		request.setAttribute("monthlyStatement1", monthlyStatement1);
		request.setAttribute("verify_userid", monthlyStatement1.getVerify_userid());
		request.setAttribute("data_id", monthlyStatement1.getMonthly_statement_id());
		request.setAttribute("data_type", EnumEntityType.MONTHLY_STATEMENT.name());
		
		
		
		List<Invoice> invoices = invoiceService.getInvoiceByMonthly(monthlyStatement.getMonthly_statement_id());
		request.setAttribute("invoices", invoices);
		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.MONTHLYSTATEMENTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject(monthlyStatement1.getProject_id());		
		
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement1.getMonthly_statement_id());
		
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement1.getMonthly_statement_id());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		

		request.setAttribute("verify_userid", monthlyStatement1.getVerify_userid());
		request.setAttribute("data_id", monthlyStatement1.getMonthly_statement_id());
		request.setAttribute("data_type", EnumEntityType.MONTHLY_STATEMENT.name());
		
		return "projectincome/monthly_statement_view";
	}
	


	@RequestMapping(params = "method=batchVerifyMonthlyStatement")
	public String batchVerifyMonthlyStatement(HttpServletResponse res,HttpServletRequest request){


		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "请先选择单据！", false);
		}
		
		User sessionUser = PubMethod.getUser(request);
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			for(String id : ids){				
				MonthlyStatement search = new MonthlyStatement();
				search.setMonthly_statement_id(id);				
				MonthlyStatement monthlyStatement =  this.monthlyStatementService.getMonthlyStatement(search);
				monthlyStatement.setVerify_datetime(PubMethod.getCurrentDate());
				monthlyStatement.setVerify_userid(sessionUser.getUser_id());
				monthlyStatement.setVerify_username(sessionUser.getUser_name());
				monthlyStatementService.verifyMonthlyStatement(monthlyStatement);		
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement.getMonthly_statement_id(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}		
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}

		return this.ajaxForwardSuccess(request, rel, false);		
	}

	@RequestMapping(params = "method=verifyMonthlyStatement")
	public String verifyMonthlyStatement(MonthlyStatement monthlyStatement,HttpServletResponse res,HttpServletRequest request){


		monthlyStatement = this.monthlyStatementService.getMonthlyStatement(monthlyStatement);
		User sessionUser = PubMethod.getUser(request);
		monthlyStatement.setVerify_datetime(PubMethod.getCurrentDate());
		monthlyStatement.setVerify_userid(sessionUser.getUser_id());
		monthlyStatement.setVerify_username(sessionUser.getUser_name());
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			monthlyStatementService.verifyMonthlyStatement(monthlyStatement);
			
	
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.MONTHLY_STATEMENT.name(), monthlyStatement.getMonthly_statement_id(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		return this.ajaxForwardSuccess(request, rel, true);		
	}


	@RequestMapping(params = "method=deleteMonthlyStatement")
	public String deleteMonthlyStatement(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);		
		}
		
		MonthlyStatement[] monthlyStatementArray = new MonthlyStatement[ids.length];
		int index = 0;
		for(String id : ids){
			MonthlyStatement monthlyStatement = new MonthlyStatement();
			monthlyStatement.setMonthly_statement_id(id);
			monthlyStatementArray[index] = monthlyStatement;
			index ++ ;
		}
		
		if(monthlyStatementArray != null && monthlyStatementArray.length > 0)
		monthlyStatementService.deleteMonthlyStatement(monthlyStatementArray);

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}	

}
