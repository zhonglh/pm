package com.pm.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.Project;
import com.pm.domain.business.StaffCost;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("PersonnelMonthlySalaryAction.do")
public class PersonnelMonthlySalaryAction extends PersonnelMonthlyBaseAction {

	@RequestMapping(params = "method=toEdit")
	public String toEdit(PersonnelMonthlySalary searchPersonnelMonthlySalary,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalary personnelMonthlySalary = null;
		StaffCost staffCost = null;
		if(searchPersonnelMonthlySalary != null && searchPersonnelMonthlySalary.getId()!=null){
			request.setAttribute("next_operation", "updatePersonnelMonthlySalary");
			personnelMonthlySalary = personnelMonthlySalaryService.getPersonnelMonthlySalary(searchPersonnelMonthlySalary.getId());	
			if(personnelMonthlySalary.getVerify_userid() != null && personnelMonthlySalary.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
		}else {
			request.setAttribute("next_operation", "addPersonnelMonthlySalary");		
			User sessionUser = PubMethod.getUser(request);
			personnelMonthlySalary = new PersonnelMonthlySalary();	
			
				copyEntity(personnelMonthlySalary, searchPersonnelMonthlySalary);
			
			paramprocess(request,personnelMonthlySalary);	
			staffCost = super.processNew(personnelMonthlySalary,request);
			
			Date minDate = DateKit.fmtStrToDate(searchPersonnelMonthlySalary.getThe_month()+""+"01","yyyyMMdd");
			Date maxDate = DateKit.getLastDayOfMonth(minDate);
			personnelMonthlySalary.setJoin_datetime(staffCost.getJoin_datetime());
			if(staffCost.getConfirmation_date() == null){
				personnelMonthlySalary.setOld_salary(staffCost.getOfficial_salary());
			}else if(staffCost.getConfirmation_date().compareTo(maxDate) > 0){
				//表示肯定是试用期
				personnelMonthlySalary.setOld_salary(staffCost.getTryout_salary());
			}else {
				//其它情况默认是正式工资
				personnelMonthlySalary.setOld_salary(staffCost.getOfficial_salary());
			}
			
			personnelMonthlySalary.setBuild_userid(sessionUser.getUser_id());
			personnelMonthlySalary.setBuild_username(sessionUser.getUser_name());
			personnelMonthlySalary.setBuild_datetime(PubMethod.getCurrentDate());
		}
		request.setAttribute("personnelMonthlySalary1", personnelMonthlySalary);
		return "personnelmonthly/personnelmonthlysalary_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PersonnelMonthlySalary searchPersonnelMonthlySalary,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalary personnelMonthlySalary = personnelMonthlySalaryService.getPersonnelMonthlySalary(searchPersonnelMonthlySalary.getId());
		request.setAttribute("personnelMonthlySalary1", personnelMonthlySalary);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( personnelMonthlySalary.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlySalary.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PERSONNELMONTHLYBASE.name(),  personnelMonthlySalary.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", personnelMonthlySalary.getVerify_userid());
		request.setAttribute("data_id", personnelMonthlySalary.getId());
		request.setAttribute("data_type", EnumEntityType.PERSONNELMONTHLYBASE.name());
		return "personnelmonthly/personnelmonthlysalary_view";
	}


	@RequestMapping(params = "method=addPersonnelMonthlySalary")
	public String addPersonnelMonthlySalary(PersonnelMonthlySalary addPersonnelMonthlySalary,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalary personnelMonthlySalary = addPersonnelMonthlySalary;	
		paramprocess(request,personnelMonthlySalary);		


		StaffCost staffCost = processExist(personnelMonthlySalary,request);

		personnelMonthlySalary.setJoin_datetime(staffCost.getJoin_datetime());		
		if(staffCost.getConfirmation_date() == null){
			personnelMonthlySalary.setOld_salary(staffCost.getOfficial_salary());
		}else {
			if(staffCost.getConfirmation_date().compareTo(personnelMonthlySalary.getChange_time()) > 0){
				personnelMonthlySalary.setOld_salary(staffCost.getTryout_salary());
			}else {
				personnelMonthlySalary.setOld_salary(staffCost.getOfficial_salary());
			}
		}
		
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlySalary.setId(IDKit.generateId());
		personnelMonthlySalary.setBuild_datetime(PubMethod.getCurrentDate());
		personnelMonthlySalary.setBuild_userid(sessionUser.getUser_id());
		personnelMonthlySalary.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			if(!validate(personnelMonthlySalary)) return this.ajaxForwardError(request, "调薪日期和月报月份不符！", true);
			count = personnelMonthlySalaryService.addPersonnelMonthlySalary(personnelMonthlySalary);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlySalary.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePersonnelMonthlySalary")
	public String updatePersonnelMonthlySalary(PersonnelMonthlySalary updatePersonnelMonthlySalary,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalary personnelMonthlySalary = updatePersonnelMonthlySalary;	
		paramprocess(request,personnelMonthlySalary);	

		StaffCost staffCost = processExist(personnelMonthlySalary,request);
		personnelMonthlySalary.setJoin_datetime(staffCost.getJoin_datetime());

		if(staffCost.getConfirmation_date() == null){
			personnelMonthlySalary.setOld_salary(staffCost.getOfficial_salary());
		}else {
			if(staffCost.getConfirmation_date().compareTo(personnelMonthlySalary.getChange_time()) > 0){
				personnelMonthlySalary.setOld_salary(staffCost.getTryout_salary());
			}else {
				personnelMonthlySalary.setOld_salary(staffCost.getOfficial_salary());
			}		
		}		
		
		int count = 0;
		try{
			if(!validate(personnelMonthlySalary)) return this.ajaxForwardError(request, "调薪日期和月报月份不符！", true);
			count = personnelMonthlySalaryService.updatePersonnelMonthlySalary(personnelMonthlySalary);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	





}