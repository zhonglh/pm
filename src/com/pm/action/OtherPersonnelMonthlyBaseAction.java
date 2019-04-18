package com.pm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.domain.business.*;
import com.pm.excel.otherpersoonnelmonthly.*;
import com.pm.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.AjaxJson;
import com.common.utils.DateKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.system.User;
import com.pm.excel.persoonnelmonthly.ExcelProcessBonus;
import com.pm.excel.persoonnelmonthly.ExcelProcessInsurance;
import com.pm.excel.persoonnelmonthly.ExcelProcessOfficial;
import com.pm.excel.persoonnelmonthly.ExcelProcessReserveFund;
import com.pm.excel.persoonnelmonthly.ExcelProcessSalary;
import com.pm.excel.persoonnelmonthly.ExcelProcessSalarySupply;
import com.pm.util.PersonnelMonthlyUtil;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.constant.EnumPersonnelMonthlyType;
import com.pm.util.excel.exports.BusinessExExcel;
import com.pm.vo.PersonnelMonthlyStaffCost;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("OtherPersonnelMonthlyBaseAction.do")
public class OtherPersonnelMonthlyBaseAction extends BaseAction {

	protected static final String sessionAttr = "OtherPersonnelMonthlyBases";

	protected static final String rel = "rel90";

	@Autowired
	protected IProjectService projectService;
	
	@Autowired
	protected IOtherPersonnelMonthlyBaseService personnelMonthlyBaseService;

	@Autowired
	protected IOtherPersonnelMonthlyOfficialService personnelMonthlyOfficialService;
	@Autowired
	protected IOtherPersonnelMonthlySalaryService personnelMonthlySalaryService;
	@Autowired
	protected IOtherPersonnelMonthlyInsuranceService personnelMonthlyInsuranceService;
	@Autowired
	protected IOtherPersonnelMonthlyReserveFundService personnelMonthlyReserveFundService;	
	@Autowired
	protected IOtherPersonnelMonthlyBonusService personnelMonthlyBonusService;
	@Autowired
	protected IOtherPersonnelMonthlySalarySupplyService personnelMonthlySalarySupplyService;


	@Autowired
	protected IInsuranceGradeService insuranceGradeService;
	
	@Autowired
	protected IOtherStaffService otherStaffService;
	@Autowired
	protected IApplyApproveService applyApproveService;	
	

	@Autowired
	private IParamsService paramsService;


	@Autowired
	protected IRoleService roleService;
	
	
	/**
	 * 获取入职人员信息
	 * @param personnelMonthlyBase
	 * @param request
	 */
	public List<PersonnelMonthlyStaffCost> getJoinStaff(PersonnelMonthlyBase personnelMonthlyBase,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEVIEW.getId());		
		
		OtherStaff searchStaffCost = new OtherStaff();
		searchStaffCost.setStaff_id(personnelMonthlyBase.getStaff_id());
		if(personnelMonthlyBase.getThe_month() != 0){
			Date d1 = DateKit.fmtStrToDate(String.valueOf(personnelMonthlyBase.getThe_month()+"01"),"yyyyMMdd");
			Date d2 = DateKit.getLastDayOfMonth(d1);	
			searchStaffCost.setJoin_datetime1(new java.sql.Timestamp(d1.getTime()));
			searchStaffCost.setJoin_datetime2(new java.sql.Timestamp(d2.getTime()));
		}		
		
		List<PersonnelMonthlyStaffCost> scs = new ArrayList<PersonnelMonthlyStaffCost>();
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchStaffCost, userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
				
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			double tax_rate = 0;
			double staff_costs_threshold = 0;
			
			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}	
			request.setAttribute("staff_costs_threshold", staff_costs_threshold);
			for(OtherStaff staffCost : pager.getResultList()){

				PersonnelMonthlyStaffCost sc = buildPersonnelMonthlyStaffCost(
						request, EnumPersonnelMonthlyType.JoinStaff.getId() ,personnelMonthlyBase.getThe_month(), tax_rate, staffCost);

				scs.add(sc);
			}			
			
			if(!scs.isEmpty()) {
				request.setAttribute("joinStaffCosts", scs);
			}
		}	
		
		return scs;
		
	}
	
	

	/**
	 * 获取离职人员信息
	 * @param personnelMonthlyBase
	 * @param request
	 */
	public List<PersonnelMonthlyStaffCost> getLeaveStaff(PersonnelMonthlyBase personnelMonthlyBase,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEVIEW.getId());		
		
		OtherStaff searchStaffCost = new OtherStaff();
		searchStaffCost.setStaff_id(personnelMonthlyBase.getStaff_id());
		if(personnelMonthlyBase.getThe_month() != 0){
			Date d1 = DateKit.fmtStrToDate(String.valueOf(personnelMonthlyBase.getThe_month()+"01"),"yyyyMMdd");
			Date d2 = DateKit.getLastDayOfMonth(d1);	
			searchStaffCost.setLeave_job_datetime1(new java.sql.Timestamp(d1.getTime()));
			searchStaffCost.setLeave_job_datetime2(new java.sql.Timestamp(d2.getTime()));
		}		
		
		List<PersonnelMonthlyStaffCost> scs = new ArrayList<PersonnelMonthlyStaffCost>();
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchStaffCost, userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
				
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			double tax_rate = 0;
			double staff_costs_threshold = 0;
			
			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}	
			request.setAttribute("staff_costs_threshold", staff_costs_threshold);
			for(OtherStaff staffCost : pager.getResultList()){
				PersonnelMonthlyStaffCost sc = buildPersonnelMonthlyStaffCost(
						request, EnumPersonnelMonthlyType.LeaveStaff.getId() ,personnelMonthlyBase.getThe_month(), tax_rate, staffCost);

				scs.add(sc);
			}			
			if(!scs.isEmpty()) {
				request.setAttribute("leaveStaffCosts", scs);
			}
		}		
		
		return scs;
		
	}

	private PersonnelMonthlyStaffCost buildPersonnelMonthlyStaffCost(HttpServletRequest request,String type,int the_month,  double tax_rate, OtherStaff staffCost) {
		//staffCost.setDifference(staffCost.getQustomerquotes() * (1-tax_rate) - staffCost.getTotalcost());
		staffCost.setCan_send_info(this.getMsg("boolean." + (staffCost.getCan_send_info()==null?"":staffCost.getCan_send_info()), request));
		//staffCost.setOutsource_staff(this.getMsg("boolean." + (staffCost.getOutsource_staff()==null?"":staffCost.getOutsource_staff()), request));
		PersonnelMonthlyStaffCost sc = new PersonnelMonthlyStaffCost();
		BeanUtils.copyProperties(staffCost, sc);
		sc.setThe_month(the_month);
		sc.setMonthly_type(type);
		sc.getMonthly_type_name();
		return sc;
	}


	/**
	 * 获取合同到期人员信息
	 * @param personnelMonthlyBase
	 * @param request
	 */
	public List<PersonnelMonthlyStaffCost> getContrctExpirationStaff(PersonnelMonthlyBase personnelMonthlyBase,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEVIEW.getId());

		OtherStaff searchStaffCost = new OtherStaff();
		searchStaffCost.setStaff_id(personnelMonthlyBase.getStaff_id());
		if(personnelMonthlyBase.getThe_month() != 0){
			Date d1 = DateKit.fmtStrToDate(String.valueOf(personnelMonthlyBase.getThe_month()+"01"),"yyyyMMdd");
			Date d2 = DateKit.getLastDayOfMonth(d1);
			searchStaffCost.setContrct_expiration_date1(new java.sql.Timestamp(d1.getTime()));
			searchStaffCost.setContrct_expiration_date2(new java.sql.Timestamp(d2.getTime()));
		}

		List<PersonnelMonthlyStaffCost> scs = new ArrayList<PersonnelMonthlyStaffCost>();
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchStaffCost, userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));

		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			double tax_rate = 0;
			double staff_costs_threshold = 0;

			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}
			request.setAttribute("staff_costs_threshold", staff_costs_threshold);
			for(OtherStaff staffCost : pager.getResultList()){
				PersonnelMonthlyStaffCost sc = buildPersonnelMonthlyStaffCost(
						request, EnumPersonnelMonthlyType.ContrctExpiration.getId() ,personnelMonthlyBase.getThe_month(), tax_rate, staffCost);

				scs.add(sc);
			}
			if(!scs.isEmpty()) {
				request.setAttribute("contrctExpirationStaffCosts", scs);
			}
		}

		return scs;

	}


	/**
	 * 获取试用期到期人员信息
	 * @param personnelMonthlyBase
	 * @param request
	 */
	public List<PersonnelMonthlyStaffCost> getTryoutStaff(PersonnelMonthlyBase personnelMonthlyBase,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEVIEW.getId());

		OtherStaff searchStaffCost = new OtherStaff();
		searchStaffCost.setStaff_id(personnelMonthlyBase.getStaff_id());
		if(personnelMonthlyBase.getThe_month() != 0){
			Date d1 = DateKit.fmtStrToDate(String.valueOf(personnelMonthlyBase.getThe_month()+"01"),"yyyyMMdd");
			Date d2 = DateKit.getLastDayOfMonth(d1);
			searchStaffCost.setTryoutDate1(new java.sql.Timestamp(d1.getTime()));
			searchStaffCost.setTryoutDate2(new java.sql.Timestamp(d2.getTime()));
		}

		List<PersonnelMonthlyStaffCost> scs = new ArrayList<PersonnelMonthlyStaffCost>();
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchStaffCost, userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));

		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
			double tax_rate = 0;
			double staff_costs_threshold = 0;

			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}
			request.setAttribute("staff_costs_threshold", staff_costs_threshold);
			for(OtherStaff staffCost : pager.getResultList()){
				PersonnelMonthlyStaffCost sc = buildPersonnelMonthlyStaffCost(
						request, EnumPersonnelMonthlyType.Tryout.getId() ,personnelMonthlyBase.getThe_month(), tax_rate, staffCost);

				scs.add(sc);
			}
			if(!scs.isEmpty()) {
				request.setAttribute("tryoutStaffCosts", scs);
			}
		}

		return scs;

	}



	@RequestMapping(params = "method=list")
	public String list(PersonnelMonthlyBase personnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEVIEW.getId());
		getList(personnelMonthlyBase,userPermit, request);
		
		request.setAttribute("personnelMonthlyBase", personnelMonthlyBase);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		return "headquarters/other_personnelmonthlybase_list";
	}


	private List<List<?>> getList(PersonnelMonthlyBase personnelMonthlyBase, UserPermit userPermit ,HttpServletRequest request) {
		
		List<List<?>> lists = new ArrayList<List<?>>();

		paramprocess(request,personnelMonthlyBase);
		
		
		String monthly_type = personnelMonthlyBase.getMonthly_type();

		if(
				StringUtils.isEmpty(personnelMonthlyBase.getStaff_id()) && 
				StringUtils.isEmpty(monthly_type) && 
				personnelMonthlyBase.getThe_month() == 0){
			String the_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
			personnelMonthlyBase.setThe_month(Integer.parseInt(the_month));
		}
		
		try{
			
			if(StringUtils.isEmpty(monthly_type) || monthly_type.equals(EnumPersonnelMonthlyType.JoinStaff.getId())){
				List<PersonnelMonthlyStaffCost> list = this.getJoinStaff(personnelMonthlyBase,  request);
				if(list != null && list.size() > 0) {
					lists.add(list);
				}
			}
			
			if(StringUtils.isEmpty(monthly_type) || monthly_type.equals(EnumPersonnelMonthlyType.LeaveStaff.getId())){
				List<PersonnelMonthlyStaffCost> list = this.getLeaveStaff(personnelMonthlyBase,  request);
				if(list != null && list.size() > 0) {
					lists.add(list);
				}
			}

			if(StringUtils.isEmpty(monthly_type) || monthly_type.equals(EnumPersonnelMonthlyType.ContrctExpiration.getId())){
				List<PersonnelMonthlyStaffCost> list = this.getContrctExpirationStaff(personnelMonthlyBase,  request);
				if(list != null && list.size() > 0) {
					lists.add(list);
				}
			}
			if(StringUtils.isEmpty(monthly_type) || monthly_type.equals(EnumPersonnelMonthlyType.Tryout.getId())){
				List<PersonnelMonthlyStaffCost> list = this.getTryoutStaff(personnelMonthlyBase,  request);
				if(list != null && list.size() > 0) {
					lists.add(list);
				}
			}





			if(StringUtils.isEmpty(monthly_type) || monthly_type.equals(EnumPersonnelMonthlyType.Official.getId())){
				//转正类型
				PersonnelMonthlyOfficial official = new PersonnelMonthlyOfficial();
				copyEntity(official, personnelMonthlyBase);		
				Pager<PersonnelMonthlyOfficial> pager1 = personnelMonthlyOfficialService.queryPersonnelMonthlyOfficial(official, userPermit, PubMethod.getPagerByAll(PersonnelMonthlyOfficial.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("officials", pager1.getResultList());	
					lists.add(pager1.getResultList());
				}
			}
			

			if(
					StringUtils.isEmpty(monthly_type) || 
					monthly_type.equals(EnumPersonnelMonthlyType.AddSalary.getId())){
				//加薪减薪类型
				PersonnelMonthlySalary salary = new PersonnelMonthlySalary();
				copyEntity(salary, personnelMonthlyBase);	
				salary.setMonthly_type(EnumPersonnelMonthlyType.AddSalary.getId());
				Pager<PersonnelMonthlySalary> pager1 = personnelMonthlySalaryService.queryPersonnelMonthlySalary(salary, userPermit, PubMethod.getPagerByAll(PersonnelMonthlySalary.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("addsalarys", pager1.getResultList());		
					lists.add(pager1.getResultList());
				}
			}

			if(
					StringUtils.isEmpty(monthly_type) || 
					monthly_type.equals(EnumPersonnelMonthlyType.DecrSalary.getId())){
				//加薪减薪类型
				PersonnelMonthlySalary salary = new PersonnelMonthlySalary();
				copyEntity(salary, personnelMonthlyBase);	
				salary.setMonthly_type(EnumPersonnelMonthlyType.DecrSalary.getId());
				Pager<PersonnelMonthlySalary> pager1 = personnelMonthlySalaryService.queryPersonnelMonthlySalary(salary, userPermit, PubMethod.getPagerByAll(PersonnelMonthlySalary.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("decrsalarys", pager1.getResultList());
					lists.add(pager1.getResultList());
				}
			}
			

			if(
					StringUtils.isEmpty(monthly_type) || 
					monthly_type.equals(EnumPersonnelMonthlyType.AddInsurance.getId())){
				//社保增员减员
				PersonnelMonthlyInsurance insurance = new PersonnelMonthlyInsurance();
				copyEntity(insurance, personnelMonthlyBase);	
				insurance.setMonthly_type(EnumPersonnelMonthlyType.AddInsurance.getId());
				Pager<PersonnelMonthlyInsurance> pager1 = personnelMonthlyInsuranceService.queryPersonnelMonthlyInsurance(insurance, userPermit, PubMethod.getPagerByAll(				PersonnelMonthlyInsurance.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("addinsurances", pager1.getResultList());	
					lists.add(pager1.getResultList());
				}
			}

			if(
					StringUtils.isEmpty(monthly_type) ||  
					monthly_type.equals(EnumPersonnelMonthlyType.DecrInsurance.getId())){
				//社保增员减员
				PersonnelMonthlyInsurance insurance = new PersonnelMonthlyInsurance();
				copyEntity(insurance, personnelMonthlyBase);	
				insurance.setMonthly_type(EnumPersonnelMonthlyType.DecrInsurance.getId());
				Pager<PersonnelMonthlyInsurance> pager1 = personnelMonthlyInsuranceService.queryPersonnelMonthlyInsurance(insurance, userPermit, PubMethod.getPagerByAll(				PersonnelMonthlyInsurance.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("decrinsurances", pager1.getResultList());	
					lists.add(pager1.getResultList());
				}
			}
			
			

			if(
					StringUtils.isEmpty(monthly_type) || 
					monthly_type.equals(EnumPersonnelMonthlyType.AddReserveFund.getId())){
				//公积金增员减员
				PersonnelMonthlyReserveFund reserveFund = new PersonnelMonthlyReserveFund();
				copyEntity(reserveFund, personnelMonthlyBase);	
				reserveFund.setMonthly_type(EnumPersonnelMonthlyType.AddReserveFund.getId());
				Pager<PersonnelMonthlyReserveFund> pager1 = personnelMonthlyReserveFundService.queryPersonnelMonthlyReserveFund(reserveFund, userPermit, PubMethod.getPagerByAll(PersonnelMonthlyReserveFund.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("addreserveFunds", pager1.getResultList());	
					lists.add(pager1.getResultList());
				}
			}

			if(
					StringUtils.isEmpty(monthly_type) ||
					monthly_type.equals(EnumPersonnelMonthlyType.DecrReserveFund.getId())){
				//公积金增员减员
				PersonnelMonthlyReserveFund reserveFund = new PersonnelMonthlyReserveFund();
				copyEntity(reserveFund, personnelMonthlyBase);	
				reserveFund.setMonthly_type(EnumPersonnelMonthlyType.DecrReserveFund.getId());
				Pager<PersonnelMonthlyReserveFund> pager1 = personnelMonthlyReserveFundService.queryPersonnelMonthlyReserveFund(reserveFund, userPermit, PubMethod.getPagerByAll(PersonnelMonthlyReserveFund.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("decrreserveFunds", pager1.getResultList());	
					lists.add(pager1.getResultList());
				}
			}


			if(
					StringUtils.isEmpty(monthly_type) || 
					monthly_type.equals(EnumPersonnelMonthlyType.Bonus.getId()) ){
				//奖金
				PersonnelMonthlyBonus bouns = new PersonnelMonthlyBonus();
				copyEntity(bouns, personnelMonthlyBase);	
				Pager<PersonnelMonthlyBonus> pager1 = personnelMonthlyBonusService.queryPersonnelMonthlyBonus(bouns, userPermit, PubMethod.getPagerByAll(PersonnelMonthlyBonus.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("bounss", pager1.getResultList());		
					lists.add(pager1.getResultList());
				}
			}
			


			if(
					StringUtils.isEmpty(monthly_type) || 
					monthly_type.equals(EnumPersonnelMonthlyType.SalarySupply.getId()) ){
				//工资变动
				PersonnelMonthlySalarySupply salarySupply = new PersonnelMonthlySalarySupply();
				copyEntity(salarySupply, personnelMonthlyBase);	
				Pager<PersonnelMonthlySalarySupply> pager1 = personnelMonthlySalarySupplyService.queryPersonnelMonthlySalarySupply(salarySupply, userPermit, PubMethod.getPagerByAll(PersonnelMonthlySalarySupply.class));
				if(pager1.getResultList() != null && pager1.getResultList().size() > 0){
					request.setAttribute("salarySupplys", pager1.getResultList());
					lists.add(pager1.getResultList());
				}
			}
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return lists;

	}


	public void paramprocess(HttpServletRequest request,PersonnelMonthlyBase personnelMonthlyBase){
		personnelMonthlyBase.setStaff_id(request.getParameter("staff.staff_id"));
		personnelMonthlyBase.setStaff_no(request.getParameter("staff.staff_no"));
		personnelMonthlyBase.setStaff_name(request.getParameter("staff.staff_name"));
	}

	/**
	 * 处理存在业务的数据
	 * @param personnelMonthlyBase
	 * @return
	 */
	public OtherStaff processExist(PersonnelMonthlyBase personnelMonthlyBase,HttpServletRequest request){
		OtherStaff staffCost = otherStaffService.getOtherStaff(personnelMonthlyBase.getStaff_id());
		personnelMonthlyBase.setStaff_no(staffCost.getStaff_no());
		personnelMonthlyBase.setStaff_name(staffCost.getStaff_name());
		personnelMonthlyBase.setDept_name(staffCost.getDept_name());
		personnelMonthlyBase.setDept_id(staffCost.getDept_id());
		personnelMonthlyBase.setMonthly_type_name(this.getMsg("personnel.monthly."+personnelMonthlyBase.getMonthly_type(), request));
		
		return staffCost;
	}
	
	/**
	 * 处理还不存在的业务的数据
	 * @param personnelMonthlyBase
	 * @return
	 */
	public OtherStaff processNew(PersonnelMonthlyBase personnelMonthlyBase,HttpServletRequest request){
		OtherStaff staffCost = otherStaffService.getOtherStaff(personnelMonthlyBase.getStaff_id());
		personnelMonthlyBase.setStaff_no(staffCost.getStaff_no());
		personnelMonthlyBase.setStaff_name(staffCost.getStaff_name());

	
		personnelMonthlyBase.setDept_name(staffCost.getDept_name());		
		personnelMonthlyBase.setDept_id(staffCost.getDept_id());	
		
		personnelMonthlyBase.setMonthly_type_name(this.getMsg("personnel.monthly."+personnelMonthlyBase.getMonthly_type(), request));
		return staffCost;
	}


	@RequestMapping(params = "method=toFirst")
	public String toFirst(PersonnelMonthlyBase personnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){

		String the_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
		request.setAttribute("personnelMonthlyBase1", personnelMonthlyBase);
		request.setAttribute("the_month", the_month);
		return "headquarters/other_personnelmonthlybase_editfirst";
	}
	


	@RequestMapping(params = "method=toEditNext")
	public String toEditNext(PersonnelMonthlyBase personnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){
		if(EnumPersonnelMonthlyType.Bonus.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyBonusAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.AddInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyInsuranceAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyInsuranceAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.AddReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyReserveFundAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.DecrReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyReserveFundAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.Official.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyOfficialAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.AddSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlySalaryAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.DecrSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlySalaryAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.Bonus.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlyBonusAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.SalarySupply.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return"forward:/OtherPersonnelMonthlySalarySupplyAction.do?method=toEdit";			
		}
		return null;
	}
	
	@RequestMapping(params = "method=toEditBase")
	public String toEditBase(PersonnelMonthlyBase searchPersonnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyBase personnelMonthlyBase = personnelMonthlyBaseService.getPersonnelMonthlyBase(searchPersonnelMonthlyBase.getId());
		if(personnelMonthlyBase == null){
			return "forward:/StaffCostAction.do?method=toEdit&staff_id="+searchPersonnelMonthlyBase.getId();	
		}
		
		if(EnumPersonnelMonthlyType.Bonus.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyBonusAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.AddInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyInsuranceAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyInsuranceAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.AddReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyReserveFundAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.DecrReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyReserveFundAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.Official.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyOfficialAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.AddSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlySalaryAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.DecrSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlySalaryAction.do?method=toEdit";
		}else if(EnumPersonnelMonthlyType.SalarySupply.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlySalarySupplyAction.do?method=toEdit";			
		}
		return null;
	}


	@RequestMapping(params = "method=toViewBase")
	public String toViewBase(PersonnelMonthlyBase searchPersonnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyBase personnelMonthlyBase = personnelMonthlyBaseService.getPersonnelMonthlyBase(searchPersonnelMonthlyBase.getId());
		if(personnelMonthlyBase == null){
			return "forward:/StaffCostAction.do?method=toView&staff_id="+searchPersonnelMonthlyBase.getId();	
		}
		
		if(EnumPersonnelMonthlyType.Bonus.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyBonusAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.AddInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyInsuranceAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.DecrInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyInsuranceAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.AddReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyReserveFundAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.DecrReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyReserveFundAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.Official.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlyOfficialAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.AddSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlySalaryAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.DecrSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlySalaryAction.do?method=toView";
		}else if(EnumPersonnelMonthlyType.SalarySupply.getId().equals(personnelMonthlyBase.getMonthly_type())){
			return "forward:/OtherPersonnelMonthlySalarySupplyAction.do?method=toView";			
		}
		return null;
	}




	@RequestMapping(params = "method=verifyPersonnelMonthlyBase")
	public String verifyPersonnelMonthlyBase(PersonnelMonthlyBase personnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlyBase.setVerify_datetime(PubMethod.getCurrentDate());
		personnelMonthlyBase.setVerify_userid(sessionUser.getUser_id());
		personnelMonthlyBase.setVerify_username(sessionUser.getUser_name());

		synchronized(this){
			personnelMonthlyBaseService.verifyPersonnelMonthlyBase(personnelMonthlyBase);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyBase.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, true);
	}


	@RequestMapping(params = "method=batchVerifyPersonnelMonthlyBase")
	public String batchVerifyPersonnelMonthlyBase(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0){
			this.ajaxForwardError(request, "请先选择单据！", false);
		}
		synchronized(this){
			for(String id : ids){
				PersonnelMonthlyBase personnelMonthlyBase = new PersonnelMonthlyBase();
				personnelMonthlyBase.setVerify_datetime(PubMethod.getCurrentDate());
				personnelMonthlyBase.setVerify_userid(sessionUser.getUser_id());
				personnelMonthlyBase.setVerify_username(sessionUser.getUser_name());
				personnelMonthlyBase.setId(id);
				personnelMonthlyBaseService.verifyPersonnelMonthlyBase(personnelMonthlyBase);
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyBase.getId(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}


	@RequestMapping(params = "method=deletePersonnelMonthlyBase")
	public String deletePersonnelMonthlyBase(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			for(String id : ids){
				PersonnelMonthlyBase personnelMonthlyBase = personnelMonthlyBaseService.getPersonnelMonthlyBase(id);
				if(EnumPersonnelMonthlyType.Bonus.getId().equals(personnelMonthlyBase.getMonthly_type())){
					PersonnelMonthlyBonus bonus = new PersonnelMonthlyBonus();
					bonus.setId(id);
					PersonnelMonthlyBonus[] bonuss = new PersonnelMonthlyBonus[]{bonus};
					personnelMonthlyBonusService.deletePersonnelMonthlyBonus(bonuss);
				}else if(
						EnumPersonnelMonthlyType.AddInsurance.getId().equals(personnelMonthlyBase.getMonthly_type()) ||
						EnumPersonnelMonthlyType.DecrInsurance.getId().equals(personnelMonthlyBase.getMonthly_type())){
					PersonnelMonthlyInsurance insurance = new PersonnelMonthlyInsurance();
					insurance.setId(id);
					PersonnelMonthlyInsurance[] insurances = new PersonnelMonthlyInsurance[]{insurance};
					personnelMonthlyInsuranceService.deletePersonnelMonthlyInsurance(insurances);
				}else if(
						EnumPersonnelMonthlyType.AddReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type()) ||
						EnumPersonnelMonthlyType.DecrReserveFund.getId().equals(personnelMonthlyBase.getMonthly_type())){
					PersonnelMonthlyReserveFund reservefund = new PersonnelMonthlyReserveFund();
					reservefund.setId(id);
					PersonnelMonthlyReserveFund[] reservefunds = new PersonnelMonthlyReserveFund[]{reservefund};
					personnelMonthlyReserveFundService.deletePersonnelMonthlyReserveFund(reservefunds);
				}else if(EnumPersonnelMonthlyType.Official.getId().equals(personnelMonthlyBase.getMonthly_type())){
					PersonnelMonthlyOfficial official = new PersonnelMonthlyOfficial();
					official.setId(id);
					PersonnelMonthlyOfficial[] officials = new PersonnelMonthlyOfficial[]{official};
					personnelMonthlyOfficialService.deletePersonnelMonthlyOfficial(officials);
				}else if(
						EnumPersonnelMonthlyType.AddSalary.getId().equals(personnelMonthlyBase.getMonthly_type()) ||
						EnumPersonnelMonthlyType.DecrSalary.getId().equals(personnelMonthlyBase.getMonthly_type())){
					
					PersonnelMonthlySalary salary = new PersonnelMonthlySalary();
					salary.setId(id);
					PersonnelMonthlySalary[] salarys = new PersonnelMonthlySalary[]{salary};
					personnelMonthlySalaryService.deletePersonnelMonthlySalary(salarys);
				}else if(EnumPersonnelMonthlyType.SalarySupply.getId().equals(personnelMonthlyBase.getMonthly_type())){
					PersonnelMonthlySalarySupply ss = new PersonnelMonthlySalarySupply();
					ss.setId(id);
					PersonnelMonthlySalarySupply[] sss = new PersonnelMonthlySalarySupply[]{ss};
					personnelMonthlySalarySupplyService.deletePersonnelMonthlySalarySupply(sss);
				}
				
			}
			
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@SuppressWarnings("static-access")
	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception {
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/personnelmonthlybase.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "人事月报模板.xlsx" ,response,false);
		return null;  
	}  	


	@RequestMapping(params = "method=export")
	public void export(PersonnelMonthlyBase personnelMonthlyBase,HttpServletResponse res,HttpServletRequest request){


		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEVIEW.getId());
		List<List<?>> lists = getList(personnelMonthlyBase,userPermit, request);
		
		for(List<?> list : lists){
			for(Object obj : list){
				if(obj instanceof PersonnelMonthlyBase){
					((PersonnelMonthlyBase) obj).getMonthly_type_name();
				}
			}
		}
		
		try{
			String header = " 华路时代人事月表";
			if(personnelMonthlyBase.getThe_month() > 0) {
				String the_month =  String.valueOf(personnelMonthlyBase.getThe_month());
				StringBuilder sb = new StringBuilder();
				the_month = sb.append(the_month.substring(0, 4)).append("年").append(Integer.parseInt(the_month.substring(4))).append("月").toString();
				header = the_month +  header;
			}
			List<String> headers = new ArrayList<String>(1);
			headers.add(header);
			BusinessExExcel.export(res, headers , lists,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	


	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		String the_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
		request.setAttribute("the_month", the_month);
		return "headquarters/other_personnelmonthlybase_upload";
	}	


	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,String the_month,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		int ithe_month = Integer.parseInt(the_month);
		
		/*
		PersonnelMonthlyBase personnelMonthlyBase = new PersonnelMonthlyBase();
		personnelMonthlyBase.setThe_month(ithe_month);
		personnelMonthlyBase.setVerify_flag("1");
		Pager<PersonnelMonthlyBase> pages = personnelMonthlyBaseService.queryPersonnelMonthlyBase(personnelMonthlyBase, userPermit, PubMethod.getPager(request, PersonnelMonthlyBase.class));
		if(pages.getResultList() != null && pages.getResultList().size() > 0){
			return this.ajaxForwardError(request, "该月份的人事月报已经导入过了，而且有的已经核实过了！ ");
		}
		*/
		
		
		Map<String,List<String[]>> map = getExcelAllSheet(file,res,request);
		

		User sessionUser = PubMethod.getUser(request);

		Pager<OtherStaff> staffCosts = otherStaffService.queryOtherStaff(new OtherStaff(),  userPermit, PubMethod.getPagerByAll(OtherStaff.class));
		Map<String,OtherStaff>  staffCostNoMap = new HashMap<String,OtherStaff>();
		Map<String,List<OtherStaff>>  staffCostNameMap = new HashMap<String,List<OtherStaff>>();
		if(staffCosts.getResultList() != null) {
			for(OtherStaff staffCost : staffCosts.getResultList()){
				staffCostNoMap.put(staffCost.getStaff_no(), staffCost);	
				if(staffCostNameMap.containsKey(staffCost.getStaff_name())){
					List<OtherStaff> scs = staffCostNameMap.get(staffCost.getStaff_name());
					scs.add(staffCost);
				}else {
					List<OtherStaff> scs = new ArrayList<OtherStaff>();
					scs.add(staffCost);
					staffCostNameMap.put(staffCost.getStaff_name(), scs);	
				}
			}
		}
		
		InsuranceGrade searchInsuranceGrade = new InsuranceGrade();
		searchInsuranceGrade.setDelete_flag(BusinessUtil.NOT_DELETEED);
		List<InsuranceGrade>  insuranceGradelist = insuranceGradeService.queryInsuranceGrade(searchInsuranceGrade);
		Map<String, InsuranceGrade> insuranceGradeMap = new HashMap<String, InsuranceGrade>();
		if(insuranceGradelist != null && insuranceGradelist.size() >0){
			for(InsuranceGrade insuranceGrade : insuranceGradelist){
				insuranceGradeMap.put(insuranceGrade.getInsurance_radix(), insuranceGrade);
			}
		}
		

		AjaxJson ajaxJson = new AjaxJson();
		
		List<PersonnelMonthlyOfficial> officials = (new OtherExcelProcessOfficial(applyApproveService,personnelMonthlyOfficialService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.Official, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);
		
		List<PersonnelMonthlySalary>  addSalarys = (new OtherExcelProcessSalary(applyApproveService,personnelMonthlySalaryService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.AddSalary, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);

		List<PersonnelMonthlySalary>  decrSalarys = (new OtherExcelProcessSalary(applyApproveService,personnelMonthlySalaryService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.DecrSalary, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);

		List<PersonnelMonthlyInsurance>  addInsurances = (new OtherExcelProcessInsurance(applyApproveService,personnelMonthlyInsuranceService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.AddInsurance, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);

		List<PersonnelMonthlyInsurance>  decrInsurances = (new OtherExcelProcessInsurance(applyApproveService,personnelMonthlyInsuranceService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.DecrInsurance, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);	

		List<PersonnelMonthlyReserveFund>  addReserveFunds = (new OtherExcelProcessReserveFund(applyApproveService,personnelMonthlyReserveFundService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.AddReserveFund, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);		

		List<PersonnelMonthlyReserveFund>  decrReserveFunds = (new OtherExcelProcessReserveFund(applyApproveService,personnelMonthlyReserveFundService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.DecrReserveFund, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);

		List<PersonnelMonthlyBonus>  bonuss = (new OtherExcelProcessBonus(applyApproveService,personnelMonthlyBonusService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.Bonus, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);

		List<PersonnelMonthlySalarySupply>  salarySupplys = (new OtherExcelProcessSalarySupply(applyApproveService,personnelMonthlySalarySupplyService)).savePersonnelMonthly(map, ithe_month,
				EnumPersonnelMonthlyType.SalarySupply, insuranceGradeMap, staffCostNoMap, staffCostNameMap,sessionUser,ajaxJson);
		
		int count = 0 ;
		count += officials.size();
		count += addSalarys.size();
		count += decrSalarys.size();
		count += addInsurances.size();
		count += decrInsurances.size();
		count += addReserveFunds.size();
		count += decrReserveFunds.size();
		count += bonuss.size();
		count += salarySupplys.size();
		if(count == 0)  {
			return this.ajaxForwardError(request, "导入的信息中没有正式数据！ ");
		}
		
		
		if(ajaxJson.isOk()){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr+"1", officials);
			request.getSession().setAttribute(sessionAttr+"2", addSalarys);
			request.getSession().setAttribute(sessionAttr+"3", decrSalarys);
			request.getSession().setAttribute(sessionAttr+"4", addInsurances);
			request.getSession().setAttribute(sessionAttr+"5", decrInsurances);
			request.getSession().setAttribute(sessionAttr+"6", addReserveFunds);
			request.getSession().setAttribute(sessionAttr+"7", decrReserveFunds);
			request.getSession().setAttribute(sessionAttr+"8", bonuss);
			request.getSession().setAttribute(sessionAttr+"9", salarySupplys);
			request.setAttribute("forwardUrl", request.getContextPath()+"/OtherPersonnelMonthlyBaseAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
	}


	
	

	@SuppressWarnings({"rawtypes"})
	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		
		for(int i=1 ; i<= 9 ; i++){			
			List list = (List)request.getSession().getAttribute(sessionAttr+i);
			request.getSession().removeAttribute(sessionAttr+i);
			
			if(list != null && list.size() >0) {
				request.setAttribute("list" + i, list);
			}
		}
		
		return "headquarters/other_personnelmonthlybase_excel_list";
	}
	
	
	/**
	 * 检查日期是否和月份匹配
	 * @param personnelMonthlyBase
	 * @return
	 */
	public boolean validate(PersonnelMonthlyBase personnelMonthlyBase){
		return PersonnelMonthlyUtil.validate(personnelMonthlyBase);
	}
	
	
	/**
	 * 对象赋值
	 * @param dest
	 * @param orig
	 */
	public void copyEntity(PersonnelMonthlyBase dest , PersonnelMonthlyBase orig){
		
		dest.setId(orig.getId());
		
		dest.setMonthly_type(orig.getMonthly_type());
		dest.setMonthly_type_name(orig.getMonthly_type_name());
		
		dest.setProject_id(orig.getProject_id());
		dest.setProject_name(orig.getProject_name());
		dest.setStaff_id(orig.getStaff_id());
		dest.setStaff_no(orig.getStaff_no());
		dest.setStaff_name(orig.getStaff_name());
		dest.setDept_id(orig.getDept_id());
		dest.setDept_name(orig.getDept_name());
		
		dest.setThe_month(orig.getThe_month());
		
	}
	
	



}