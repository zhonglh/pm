package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyReserveFund;
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
@RequestMapping("PersonnelMonthlyReserveFundAction.do")
public class PersonnelMonthlyReserveFundAction extends PersonnelMonthlyBaseAction {


	@RequestMapping(params = "method=toEdit")
	public String toEdit(PersonnelMonthlyReserveFund searchPersonnelMonthlyReserveFund,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund = null;
		StaffCost staffCost = null;
		if(searchPersonnelMonthlyReserveFund != null && searchPersonnelMonthlyReserveFund.getId()!=null){
			request.setAttribute("next_operation", "updatePersonnelMonthlyReserveFund");
			personnelMonthlyReserveFund = personnelMonthlyReserveFundService.getPersonnelMonthlyReserveFund(searchPersonnelMonthlyReserveFund.getId());	
			if(personnelMonthlyReserveFund.getVerify_userid() != null && personnelMonthlyReserveFund.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			//staffCost = super.processExist(personnelMonthlyReserveFund,request);
		}else {
			request.setAttribute("next_operation", "addPersonnelMonthlyReserveFund");		
			User sessionUser = PubMethod.getUser(request);
			personnelMonthlyReserveFund = new PersonnelMonthlyReserveFund();	
			
				copyEntity(personnelMonthlyReserveFund, searchPersonnelMonthlyReserveFund);
			
			paramprocess(request,personnelMonthlyReserveFund);	
			staffCost = super.processNew(personnelMonthlyReserveFund,request);			
			
			personnelMonthlyReserveFund.setBuild_userid(sessionUser.getUser_id());
			personnelMonthlyReserveFund.setBuild_username(sessionUser.getUser_name());
			personnelMonthlyReserveFund.setBuild_datetime(PubMethod.getCurrentDate());
		}
		processInsuranceGrade(personnelMonthlyReserveFund);
		request.setAttribute("personnelMonthlyReserveFund1", personnelMonthlyReserveFund);
		return "personnelmonthly/personnelmonthlyreservefund_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PersonnelMonthlyReserveFund searchPersonnelMonthlyReserveFund,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund = personnelMonthlyReserveFundService.getPersonnelMonthlyReserveFund(searchPersonnelMonthlyReserveFund.getId());
		processInsuranceGrade(personnelMonthlyReserveFund);
		request.setAttribute("personnelMonthlyReserveFund1", personnelMonthlyReserveFund);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( personnelMonthlyReserveFund.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyReserveFund.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PERSONNELMONTHLYBASE.name(),  personnelMonthlyReserveFund.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", personnelMonthlyReserveFund.getVerify_userid());
		request.setAttribute("data_id", personnelMonthlyReserveFund.getId());
		request.setAttribute("data_type", EnumEntityType.PERSONNELMONTHLYBASE.name());
		return "personnelmonthly/personnelmonthlyreservefund_view";
	}

	private void processInsuranceGrade(PersonnelMonthlyReserveFund personnelMonthlyReserveFund){
		if(StringUtils.isNotEmpty(personnelMonthlyReserveFund.getInsurance_grade_id())){
			InsuranceGrade insuranceGrade = insuranceGradeService.getInsuranceGrade(personnelMonthlyReserveFund.getInsurance_grade_id());
			if(insuranceGrade != null) {
				personnelMonthlyReserveFund.setInsurance_radix(insuranceGrade.getInsurance_radix());
				personnelMonthlyReserveFund.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());
				personnelMonthlyReserveFund.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());
				personnelMonthlyReserveFund.setBase_cardinal(insuranceGrade.getBase_cardinal());
			}		
		}
	}


	@RequestMapping(params = "method=addPersonnelMonthlyReserveFund")
	public String addPersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund addPersonnelMonthlyReserveFund,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund = addPersonnelMonthlyReserveFund;	
		paramprocess(request,personnelMonthlyReserveFund);

		personnelMonthlyReserveFund.setInsurance_grade_id(request.getParameter("grade.insurance_grade_id"));
		processInsuranceGrade(personnelMonthlyReserveFund);	


		StaffCost staffCost = processExist(personnelMonthlyReserveFund,request);
		personnelMonthlyReserveFund.setOld_insurance_grade_id(staffCost.getInsurance_grade_id());
		personnelMonthlyReserveFund.setOld_securty_unit(staffCost.getSecurty_unit());
		
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlyReserveFund.setId(IDKit.generateId());
		personnelMonthlyReserveFund.setBuild_datetime(PubMethod.getCurrentDate());
		personnelMonthlyReserveFund.setBuild_userid(sessionUser.getUser_id());
		personnelMonthlyReserveFund.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = personnelMonthlyReserveFundService.addPersonnelMonthlyReserveFund(personnelMonthlyReserveFund);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyReserveFund.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePersonnelMonthlyReserveFund")
	public String updatePersonnelMonthlyReserveFund(PersonnelMonthlyReserveFund updatePersonnelMonthlyReserveFund,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyReserveFund personnelMonthlyReserveFund = updatePersonnelMonthlyReserveFund;	
		paramprocess(request,personnelMonthlyReserveFund);	

		personnelMonthlyReserveFund.setInsurance_grade_id(request.getParameter("grade.insurance_grade_id"));
		processInsuranceGrade(personnelMonthlyReserveFund);	

		StaffCost staffCost = processExist(personnelMonthlyReserveFund,request);
		personnelMonthlyReserveFund.setOld_insurance_grade_id(staffCost.getInsurance_grade_id());
		personnelMonthlyReserveFund.setOld_securty_unit(staffCost.getSecurty_unit());
		
		int count = 0;
		try{
			count = personnelMonthlyReserveFundService.updatePersonnelMonthlyReserveFund(personnelMonthlyReserveFund);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	





}