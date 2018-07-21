package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.utils.IDKit;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.system.User;
import com.pm.service.IInsuranceGradeService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;



@Controller
@RequestMapping(value = "InsuranceGradeAction.do")
public class InsuranceGradeAction extends BaseAction{



	private static final String rel = "rel14";
	
	@Autowired
	private IInsuranceGradeService insuranceGradeService;
	
	@Autowired
	private IRoleService roleService;
	


	public String isExist(InsuranceGrade searchInsuranceGrade,HttpServletResponse res,HttpServletRequest request){

		String error = null;		
		boolean b = insuranceGradeService.isExist(searchInsuranceGrade);
		if(!b){
			return null;
		}else {
			error = "该保险档次已经存在";
			return this.ajaxForwardError(request, error);
		}
		
	}
	
	
	@RequestMapping(params = "method=lookup")
	public void lookup(HttpServletResponse res,HttpServletRequest request){
		
		InsuranceGrade searchInsuranceGrade = new InsuranceGrade();
		searchInsuranceGrade.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.INSURANCEGRADEVIEW.getId());			
		List<InsuranceGrade> list= null;
		if(userPermit == null || userPermit.getPermit_id() == null || userPermit.getPermit_id().isEmpty()) 
			list = new ArrayList<InsuranceGrade>();
		else 
			list = insuranceGradeService.queryInsuranceGrade(searchInsuranceGrade);
		
		this.writeResJson(res, list);
		
	}

	@RequestMapping(params = "method=list")
	public String list(HttpServletResponse res,HttpServletRequest request){

		InsuranceGrade searchInsuranceGrade = new InsuranceGrade();
		searchInsuranceGrade.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.INSURANCEGRADEVIEW.getId());			
		List<InsuranceGrade> list= null;
		if(userPermit == null || userPermit.getPermit_id() == null || userPermit.getPermit_id().isEmpty()) 
			list = new ArrayList<InsuranceGrade>();
		else 
			list = insuranceGradeService.queryInsuranceGrade(searchInsuranceGrade);

		request.setAttribute("list", list);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INSURANCEGRADEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INSURANCEGRADEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.INSURANCEGRADEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.SALARYUPDATE.getId());
		request.setAttribute(EnumOperationType.SYNCHRODATA.getKey(), userPermit1.getPermit_id());	
		
		return "basicdata/insurance_grade_list";
		
	}
	

	@RequestMapping(params = "method=addInsuranceGrade")
	public String addInsuranceGrade(InsuranceGrade insuranceGrade,HttpServletResponse res,HttpServletRequest request){
		
		String isExist = isExist(insuranceGrade,res,request);
		if(isExist != null) return isExist;

		User sessionUser = PubMethod.getUser(request);
		if(insuranceGrade == null) insuranceGrade = new InsuranceGrade();
		insuranceGrade.setInsurance_grade_id(IDKit.generateId());
		

		insuranceGrade.setBuild_datetime(PubMethod.getCurrentDate());
		insuranceGrade.setBuild_userid(sessionUser.getUser_id());
		insuranceGrade.setBuild_username(sessionUser.getUser_name());
		insuranceGrade.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		
		int count = 0;
		try{
			count = insuranceGradeService.addInsuranceGrade(insuranceGrade);		
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	

	@RequestMapping(params = "method=updateInsuranceGrade")
	public String updateInsuranceGrade(InsuranceGrade insuranceGrade,HttpServletResponse res,HttpServletRequest request){	

		String isExist = isExist(insuranceGrade,res,request);
		if(isExist != null) return isExist;
		
		int count = 0;
		try{
			count = insuranceGradeService.updateInsuranceGrade(insuranceGrade);			
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(InsuranceGrade insuranceGrade,HttpServletResponse res,HttpServletRequest request){
		
		InsuranceGrade insuranceGrade1 = null;
		if(insuranceGrade != null && insuranceGrade.getInsurance_grade_id() !=null){
			insuranceGrade1 = insuranceGradeService.getInsuranceGrade(insuranceGrade.getInsurance_grade_id());
			request.setAttribute("next_operation", "updateInsuranceGrade");
		}else {
			request.setAttribute("next_operation", "addInsuranceGrade");
		}		
		
		if(insuranceGrade1 == null) insuranceGrade1 = new InsuranceGrade();
		
		request.setAttribute("insuranceGrade1", insuranceGrade1);		
		
		return "basicdata/insurance_grade_edit";
		
	}	
	


	@RequestMapping(params = "method=toView")
	public String toView(InsuranceGrade insuranceGrade,HttpServletResponse res,HttpServletRequest request){
		
		InsuranceGrade  insuranceGrade1 = insuranceGradeService.getInsuranceGrade(insuranceGrade.getInsurance_grade_id());		
		
		if(insuranceGrade1 == null) insuranceGrade1 = new InsuranceGrade();
		
		request.setAttribute("insuranceGrade1", insuranceGrade1);		
		
		return "basicdata/insurance_grade_view";
		
	}		
	

	@RequestMapping(params = "method=deleteInsuranceGrade")
	public String deleteInsuranceGrade(HttpServletResponse res,HttpServletRequest request){
		
		String[] insuranceGrade_ids = request.getParameterValues("ids");
		int size = insuranceGrade_ids.length;
		
		InsuranceGrade[] insuranceGradeArray = new InsuranceGrade[size];

		User sessionUser = PubMethod.getUser(request);
		for(int i=0;i<size;i++){
			InsuranceGrade insuranceGrade = new InsuranceGrade();
			insuranceGrade.setInsurance_grade_id(insuranceGrade_ids[i]);			
			insuranceGrade.setDelete_userid(sessionUser.getUser_id());
			insuranceGrade.setDelete_username(sessionUser.getUser_name());
			insuranceGrade.setDelete_datetime(PubMethod.getCurrentDate());			
			insuranceGradeArray[i] = insuranceGrade;
		}
		
		if(insuranceGradeArray != null && insuranceGradeArray.length >0)
		insuranceGradeService.deleteInsuranceGrade( insuranceGradeArray );

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
}
