package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.domain.system.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.PersonnelMonthlyBonus;
import com.pm.domain.business.Project;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("OtherPersonnelMonthlyBonusAction.do")
public class OtherPersonnelMonthlyBonusAction extends OtherPersonnelMonthlyBaseAction {



	@RequestMapping(params = "method=toEdit")
	public String toEdit(PersonnelMonthlyBonus searchPersonnelMonthlyBonus,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyBonus personnelMonthlyBonus = null;
		if(searchPersonnelMonthlyBonus != null && searchPersonnelMonthlyBonus.getId()!=null){
			request.setAttribute("next_operation", "updatePersonnelMonthlyBonus");
			personnelMonthlyBonus = personnelMonthlyBonusService.getPersonnelMonthlyBonus(searchPersonnelMonthlyBonus.getId());	
			if(personnelMonthlyBonus.getVerify_userid() != null && personnelMonthlyBonus.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			//super.processExist(personnelMonthlyBonus,request);
			
		}else {
			request.setAttribute("next_operation", "addPersonnelMonthlyBonus");		
			User sessionUser = PubMethod.getUser(request);
			personnelMonthlyBonus = new PersonnelMonthlyBonus();	
			
				copyEntity(personnelMonthlyBonus, searchPersonnelMonthlyBonus);
			
			paramprocess(request,personnelMonthlyBonus);			

			OtherStaff staffCost = super.processNew(personnelMonthlyBonus,request);
			personnelMonthlyBonus.setJoin_datetime(new java.sql.Date(staffCost.getJoin_datetime().getTime()));
			
			personnelMonthlyBonus.setBuild_userid(sessionUser.getUser_id());
			personnelMonthlyBonus.setBuild_username(sessionUser.getUser_name());
			personnelMonthlyBonus.setBuild_datetime(PubMethod.getCurrentDate());
		}
		request.setAttribute("personnelMonthlyBonus1", personnelMonthlyBonus);
		

		
		return "headquarters/other_personnelmonthlybonus_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PersonnelMonthlyBonus searchPersonnelMonthlyBonus,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyBonus personnelMonthlyBonus = personnelMonthlyBonusService.getPersonnelMonthlyBonus(searchPersonnelMonthlyBonus.getId());
		//super.processExist(personnelMonthlyBonus,request);
		request.setAttribute("personnelMonthlyBonus1", personnelMonthlyBonus);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Dept dept = deptService.getDept( personnelMonthlyBonus.getDept_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.OTHERPERSONNELMONTHLYBASE.name(), personnelMonthlyBonus.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.OTHERPERSONNELMONTHLYBASE.name(),  personnelMonthlyBonus.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("dept", dept);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", personnelMonthlyBonus.getVerify_userid());
		request.setAttribute("data_id", personnelMonthlyBonus.getId());
		request.setAttribute("data_type", EnumEntityType.OTHERPERSONNELMONTHLYBASE.name());
		return "headquarters/other_personnelmonthlybonus_view";
	}


	@RequestMapping(params = "method=addPersonnelMonthlyBonus")
	public String addPersonnelMonthlyBonus(PersonnelMonthlyBonus addPersonnelMonthlyBonus,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyBonus personnelMonthlyBonus = addPersonnelMonthlyBonus;	
		paramprocess(request,personnelMonthlyBonus);

		OtherStaff staffCost = processExist(personnelMonthlyBonus,request);
		personnelMonthlyBonus.setJoin_datetime(staffCost.getJoin_datetime());
		
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlyBonus.setId(IDKit.generateId());
		personnelMonthlyBonus.setBuild_datetime(PubMethod.getCurrentDate());
		personnelMonthlyBonus.setBuild_userid(sessionUser.getUser_id());
		personnelMonthlyBonus.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			if(!validate(personnelMonthlyBonus)) return this.ajaxForwardError(request, "奖惩时间和月报月份不符！", true);
			count = personnelMonthlyBonusService.addPersonnelMonthlyBonus(personnelMonthlyBonus);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.OTHERPERSONNELMONTHLYBASE.name(), personnelMonthlyBonus.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePersonnelMonthlyBonus")
	public String updatePersonnelMonthlyBonus(PersonnelMonthlyBonus updatePersonnelMonthlyBonus,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyBonus personnelMonthlyBonus = updatePersonnelMonthlyBonus;	
		paramprocess(request,personnelMonthlyBonus);			

		OtherStaff staffCost = processExist(personnelMonthlyBonus,request);
		personnelMonthlyBonus.setJoin_datetime(staffCost.getJoin_datetime());
		
		int count = 0;
		try{
			if(!validate(personnelMonthlyBonus)) return this.ajaxForwardError(request, "奖惩时间和月报月份不符！", true);
			count = personnelMonthlyBonusService.updatePersonnelMonthlyBonus(personnelMonthlyBonus);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	





}