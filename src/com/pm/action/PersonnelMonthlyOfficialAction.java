package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.PersonnelMonthlyOfficial;
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
@RequestMapping("PersonnelMonthlyOfficialAction.do")
public class PersonnelMonthlyOfficialAction extends PersonnelMonthlyBaseAction {

	@RequestMapping(params = "method=toEdit")
	public String toEdit(PersonnelMonthlyOfficial searchPersonnelMonthlyOfficial,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyOfficial personnelMonthlyOfficial = null;
		StaffCost staffCost = null;
		if(searchPersonnelMonthlyOfficial != null && searchPersonnelMonthlyOfficial.getId()!=null){
			request.setAttribute("next_operation", "updatePersonnelMonthlyOfficial");
			personnelMonthlyOfficial = personnelMonthlyOfficialService.getPersonnelMonthlyOfficial(searchPersonnelMonthlyOfficial.getId());	
			if(personnelMonthlyOfficial.getVerify_userid() != null && personnelMonthlyOfficial.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			//staffCost = super.processExist(personnelMonthlyOfficial,request);
		}else {
			request.setAttribute("next_operation", "addPersonnelMonthlyOfficial");		
			User sessionUser = PubMethod.getUser(request);
			personnelMonthlyOfficial = new PersonnelMonthlyOfficial();	
			

			copyEntity(personnelMonthlyOfficial, searchPersonnelMonthlyOfficial);
			
			paramprocess(request,personnelMonthlyOfficial);	
			staffCost = super.processNew(personnelMonthlyOfficial,request);
			

			personnelMonthlyOfficial.setJoin_datetime(staffCost.getJoin_datetime());
			personnelMonthlyOfficial.setConfirmation_date(staffCost.getConfirmation_date());
			personnelMonthlyOfficial.setTryout_salary(staffCost.getTryout_salary());
			personnelMonthlyOfficial.setOfficial_salary(staffCost.getOfficial_salary());
			
			personnelMonthlyOfficial.setBuild_userid(sessionUser.getUser_id());
			personnelMonthlyOfficial.setBuild_username(sessionUser.getUser_name());
			personnelMonthlyOfficial.setBuild_datetime(PubMethod.getCurrentDate());
		}
		request.setAttribute("personnelMonthlyOfficial1", personnelMonthlyOfficial);
		return "personnelmonthly/personnelmonthlyofficial_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PersonnelMonthlyOfficial searchPersonnelMonthlyOfficial,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyOfficial personnelMonthlyOfficial = personnelMonthlyOfficialService.getPersonnelMonthlyOfficial(searchPersonnelMonthlyOfficial.getId());
		request.setAttribute("personnelMonthlyOfficial1", personnelMonthlyOfficial);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( personnelMonthlyOfficial.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyOfficial.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PERSONNELMONTHLYBASE.name(),  personnelMonthlyOfficial.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", personnelMonthlyOfficial.getVerify_userid());
		request.setAttribute("data_id", personnelMonthlyOfficial.getId());
		request.setAttribute("data_type", EnumEntityType.PERSONNELMONTHLYBASE.name());
		return "personnelmonthly/personnelmonthlyofficial_view";
	}


	@RequestMapping(params = "method=addPersonnelMonthlyOfficial")
	public String addPersonnelMonthlyOfficial(PersonnelMonthlyOfficial addPersonnelMonthlyOfficial,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyOfficial personnelMonthlyOfficial = addPersonnelMonthlyOfficial;	
		paramprocess(request,personnelMonthlyOfficial);
		
		

		StaffCost staffCost = processExist(personnelMonthlyOfficial,request);
		personnelMonthlyOfficial.setJoin_datetime(staffCost.getJoin_datetime());
		//personnelMonthlyOfficial.setConfirmation_date(staffCost.getConfirmation_date());
		//personnelMonthlyOfficial.setTryout_salary(staffCost.getTryout_salary());
		//personnelMonthlyOfficial.setOfficial_salary(staffCost.getOfficial_salary());
		
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlyOfficial.setId(IDKit.generateId());
		personnelMonthlyOfficial.setBuild_datetime(PubMethod.getCurrentDate());
		personnelMonthlyOfficial.setBuild_userid(sessionUser.getUser_id());
		personnelMonthlyOfficial.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			if(!validate(personnelMonthlyOfficial)) return this.ajaxForwardError(request, "转正日期和月报月份不符！", true);
			count = personnelMonthlyOfficialService.addPersonnelMonthlyOfficial(personnelMonthlyOfficial);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyOfficial.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePersonnelMonthlyOfficial")
	public String updatePersonnelMonthlyOfficial(PersonnelMonthlyOfficial updatePersonnelMonthlyOfficial,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyOfficial personnelMonthlyOfficial = updatePersonnelMonthlyOfficial;	
		paramprocess(request,personnelMonthlyOfficial);	


		StaffCost staffCost = processExist(personnelMonthlyOfficial,request);
		personnelMonthlyOfficial.setJoin_datetime(staffCost.getJoin_datetime());
		//personnelMonthlyOfficial.setConfirmation_date(staffCost.getConfirmation_date());
		//personnelMonthlyOfficial.setTryout_salary(staffCost.getTryout_salary());
		//personnelMonthlyOfficial.setOfficial_salary(staffCost.getOfficial_salary());
		
		int count = 0;
		try{
			if(!validate(personnelMonthlyOfficial)) return this.ajaxForwardError(request, "转正日期和月报月份不符！", true);
			count = personnelMonthlyOfficialService.updatePersonnelMonthlyOfficial(personnelMonthlyOfficial);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	





}