package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.PersonnelMonthlySalarySupply;
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
@RequestMapping("PersonnelMonthlySalarySupplyAction.do")
public class PersonnelMonthlySalarySupplyAction extends PersonnelMonthlyBaseAction {

	@RequestMapping(params = "method=toEdit")
	public String toEdit(PersonnelMonthlySalarySupply searchPersonnelMonthlySalarySupply,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply = null;
		StaffCost staffCost = null;
		if(searchPersonnelMonthlySalarySupply != null && searchPersonnelMonthlySalarySupply.getId()!=null){
			request.setAttribute("next_operation", "updatePersonnelMonthlySalarySupply");
			personnelMonthlySalarySupply = personnelMonthlySalarySupplyService.getPersonnelMonthlySalarySupply(searchPersonnelMonthlySalarySupply.getId());	
			if(personnelMonthlySalarySupply.getVerify_userid() != null && personnelMonthlySalarySupply.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
		}else {
			request.setAttribute("next_operation", "addPersonnelMonthlySalarySupply");		
			User sessionUser = PubMethod.getUser(request);
			personnelMonthlySalarySupply = new PersonnelMonthlySalarySupply();	
			
				copyEntity(personnelMonthlySalarySupply, searchPersonnelMonthlySalarySupply);
			
			paramprocess(request,personnelMonthlySalarySupply);	

			staffCost = super.processNew(personnelMonthlySalarySupply,request);
			personnelMonthlySalarySupply.setBuild_userid(sessionUser.getUser_id());
			personnelMonthlySalarySupply.setBuild_username(sessionUser.getUser_name());
			personnelMonthlySalarySupply.setBuild_datetime(PubMethod.getCurrentDate());
		}
		request.setAttribute("personnelMonthlySalarySupply1", personnelMonthlySalarySupply);
		return "personnelmonthly/personnelmonthlysalarysupply_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PersonnelMonthlySalarySupply searchPersonnelMonthlySalarySupply,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply = personnelMonthlySalarySupplyService.getPersonnelMonthlySalarySupply(searchPersonnelMonthlySalarySupply.getId());
		request.setAttribute("personnelMonthlySalarySupply1", personnelMonthlySalarySupply);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( personnelMonthlySalarySupply.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlySalarySupply.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PERSONNELMONTHLYBASE.name(),  personnelMonthlySalarySupply.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", personnelMonthlySalarySupply.getVerify_userid());
		request.setAttribute("data_id", personnelMonthlySalarySupply.getId());
		request.setAttribute("data_type", EnumEntityType.PERSONNELMONTHLYBASE.name());
		return "personnelmonthly/personnelmonthlysalarysupply_view";
	}


	@RequestMapping(params = "method=addPersonnelMonthlySalarySupply")
	public String addPersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply addPersonnelMonthlySalarySupply,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply = addPersonnelMonthlySalarySupply;	
		paramprocess(request,personnelMonthlySalarySupply);

		StaffCost staffCost = processExist(personnelMonthlySalarySupply,request);	
		
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlySalarySupply.setId(IDKit.generateId());
		personnelMonthlySalarySupply.setBuild_datetime(PubMethod.getCurrentDate());
		personnelMonthlySalarySupply.setBuild_userid(sessionUser.getUser_id());
		personnelMonthlySalarySupply.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			if(!validate(personnelMonthlySalarySupply)) return this.ajaxForwardError(request, "工资补充日期和月报月份不符！", true);
			count = personnelMonthlySalarySupplyService.addPersonnelMonthlySalarySupply(personnelMonthlySalarySupply);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlySalarySupply.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePersonnelMonthlySalarySupply")
	public String updatePersonnelMonthlySalarySupply(PersonnelMonthlySalarySupply updatePersonnelMonthlySalarySupply,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlySalarySupply personnelMonthlySalarySupply = updatePersonnelMonthlySalarySupply;	
		paramprocess(request,personnelMonthlySalarySupply);	

		StaffCost staffCost = processExist(personnelMonthlySalarySupply,request);	
		
		int count = 0;
		try{
			if(!validate(personnelMonthlySalarySupply)) return this.ajaxForwardError(request, "工资补充日期和月报月份不符！", true);
			count = personnelMonthlySalarySupplyService.updatePersonnelMonthlySalarySupply(personnelMonthlySalarySupply);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	




}