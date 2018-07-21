package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyInsurance;
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
@RequestMapping("PersonnelMonthlyInsuranceAction.do")
public class PersonnelMonthlyInsuranceAction extends PersonnelMonthlyBaseAction {

	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(PersonnelMonthlyInsurance searchPersonnelMonthlyInsurance,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyInsurance personnelMonthlyInsurance = null;
		StaffCost staffCost = null;
		if(searchPersonnelMonthlyInsurance != null && searchPersonnelMonthlyInsurance.getId()!=null){
			request.setAttribute("next_operation", "updatePersonnelMonthlyInsurance");
			personnelMonthlyInsurance = personnelMonthlyInsuranceService.getPersonnelMonthlyInsurance(searchPersonnelMonthlyInsurance.getId());	
			if(personnelMonthlyInsurance.getVerify_userid() != null && personnelMonthlyInsurance.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			//staffCost = super.processExist(personnelMonthlyInsurance,request);
		}else {
			request.setAttribute("next_operation", "addPersonnelMonthlyInsurance");		
			User sessionUser = PubMethod.getUser(request);
			personnelMonthlyInsurance = new PersonnelMonthlyInsurance();	
			
				copyEntity(personnelMonthlyInsurance, searchPersonnelMonthlyInsurance);
			
			paramprocess(request,personnelMonthlyInsurance);

			staffCost = super.processNew(personnelMonthlyInsurance,request);
			
			
			personnelMonthlyInsurance.setBuild_userid(sessionUser.getUser_id());
			personnelMonthlyInsurance.setBuild_username(sessionUser.getUser_name());
			personnelMonthlyInsurance.setBuild_datetime(PubMethod.getCurrentDate());
		}
		processInsuranceGrade(personnelMonthlyInsurance);
		
		
		request.setAttribute("personnelMonthlyInsurance1", personnelMonthlyInsurance);
		return "personnelmonthly/personnelmonthlyinsurance_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(PersonnelMonthlyInsurance searchPersonnelMonthlyInsurance,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyInsurance personnelMonthlyInsurance = personnelMonthlyInsuranceService.getPersonnelMonthlyInsurance(searchPersonnelMonthlyInsurance.getId());
		processInsuranceGrade(personnelMonthlyInsurance);
		request.setAttribute("personnelMonthlyInsurance1", personnelMonthlyInsurance);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PERSONNELMONTHLYBASEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( personnelMonthlyInsurance.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyInsurance.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PERSONNELMONTHLYBASE.name(),  personnelMonthlyInsurance.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", personnelMonthlyInsurance.getVerify_userid());
		request.setAttribute("data_id", personnelMonthlyInsurance.getId());
		request.setAttribute("data_type", EnumEntityType.PERSONNELMONTHLYBASE.name());
		return "personnelmonthly/personnelmonthlyinsurance_view";
	}
	
	private void processInsuranceGrade(PersonnelMonthlyInsurance personnelMonthlyInsurance){
		if(StringUtils.isNotEmpty(personnelMonthlyInsurance.getInsurance_grade_id())){
			InsuranceGrade insuranceGrade = insuranceGradeService.getInsuranceGrade(personnelMonthlyInsurance.getInsurance_grade_id());
			if(insuranceGrade != null) {
				personnelMonthlyInsurance.setInsurance_radix(insuranceGrade.getInsurance_radix());				
				personnelMonthlyInsurance.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
				personnelMonthlyInsurance.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
				personnelMonthlyInsurance.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
				personnelMonthlyInsurance.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
				personnelMonthlyInsurance.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
				personnelMonthlyInsurance.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
				personnelMonthlyInsurance.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
				personnelMonthlyInsurance.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());					
			}		
		}
	}


	@RequestMapping(params = "method=addPersonnelMonthlyInsurance")
	public String addPersonnelMonthlyInsurance(PersonnelMonthlyInsurance addPersonnelMonthlyInsurance,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyInsurance personnelMonthlyInsurance = addPersonnelMonthlyInsurance;	
		paramprocess(request,personnelMonthlyInsurance);
		

		personnelMonthlyInsurance.setInsurance_grade_id(request.getParameter("grade.insurance_grade_id"));
		processInsuranceGrade(personnelMonthlyInsurance);		
		
		StaffCost staffCost = processExist(personnelMonthlyInsurance,request);
		personnelMonthlyInsurance.setOld_insurance_grade_id(staffCost.getInsurance_grade_id());
		personnelMonthlyInsurance.setOld_securty_unit(staffCost.getSecurty_unit());
		
		User sessionUser = PubMethod.getUser(request);
		personnelMonthlyInsurance.setId(IDKit.generateId());
		personnelMonthlyInsurance.setBuild_datetime(PubMethod.getCurrentDate());
		personnelMonthlyInsurance.setBuild_userid(sessionUser.getUser_id());
		personnelMonthlyInsurance.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = personnelMonthlyInsuranceService.addPersonnelMonthlyInsurance(personnelMonthlyInsurance);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), personnelMonthlyInsurance.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePersonnelMonthlyInsurance")
	public String updatePersonnelMonthlyInsurance(PersonnelMonthlyInsurance updatePersonnelMonthlyInsurance,HttpServletResponse res,HttpServletRequest request){
		PersonnelMonthlyInsurance personnelMonthlyInsurance = updatePersonnelMonthlyInsurance;	
		paramprocess(request,personnelMonthlyInsurance);	

		personnelMonthlyInsurance.setInsurance_grade_id(request.getParameter("grade.insurance_grade_id"));
		processInsuranceGrade(personnelMonthlyInsurance);

		StaffCost staffCost = processExist(personnelMonthlyInsurance,request);
		personnelMonthlyInsurance.setOld_insurance_grade_id(staffCost.getInsurance_grade_id());
		personnelMonthlyInsurance.setOld_securty_unit(staffCost.getSecurty_unit());
		
		int count = 0;
		try{
			count = personnelMonthlyInsuranceService.updatePersonnelMonthlyInsurance(personnelMonthlyInsurance);	
		}catch(Exception e){
			e.printStackTrace();
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	





}