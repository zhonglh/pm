package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.Salary;
import com.pm.domain.business.SalaryMail;
import com.pm.domain.business.SalaryMailDetail;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.service.ISalaryMailService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "SalaryMailAction.do")
public class SalaryMailAction extends BaseAction {


	private static final String rel = "rel25";
	
	@Autowired
	private ISalaryMailService salaryMailService;

	@Autowired
	private IRoleService roleService;
	
	

	@RequestMapping(params = "method=sendSalaryMail")
	public String sendSalaryMail(Salary addSalary,HttpServletResponse res,HttpServletRequest request){	
		
		String[] ids = request.getParameterValues("ids");
		
		if(ids == null || ids.length ==0){
			return this.ajaxForwardError(request, "没有选择任何记录！",true);
		}

		User sessionUser = PubMethod.getUser(request);
		
		try{
			salaryMailService.sendMail(ids, sessionUser.getUser_id());
			return this.ajaxForwardSuccess(request, rel, false);
		}catch(Exception e){
			return this.ajaxForwardError(request, e.getMessage());
		}
		

		
	}
	
	
	@RequestMapping(params = "method=cancelSend")
	public String cancelSend(Salary addSalary,HttpServletResponse res,HttpServletRequest request){	
		
		User sessionUser = PubMethod.getUser(request);
		
		try{
			salaryMailService.cancelSend();
			return this.ajaxForwardSuccess(request, rel, false);
		}catch(Exception e){
			return this.ajaxForwardError(request, e.getMessage());
		}
		

		
	}	
	

	@RequestMapping(params = "method=list")
	public String list(SalaryMail searchSalary,HttpServletResponse res,HttpServletRequest request){
		
		
		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchSalary.setDept_id(dept_id);
		}
		
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchSalary.setDept_name(dept_name);
		}
		
		if(searchSalary.getSend_status()==null || searchSalary.getSend_status().isEmpty()){
			searchSalary.setSend_status("0");
		}
		

		
		if("3".equals(searchSalary.getSend_status())){
			searchSalary.setSend_status("1");
			searchSalary.setDetail_status("1");
		}else if("4".equals(searchSalary.getSend_status())){
			searchSalary.setSend_status("1");
			searchSalary.setDetail_status("0");
		}
		

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALARYVIEW.getId());	
		Pager<SalaryMail> pager= salaryMailService.querySalaryMail(searchSalary, userPermit, PubMethod.getPager(request, SalaryMail.class));

		request.setAttribute("salary1", searchSalary);
		
		PubMethod.setRequestPager(request, pager,SalaryMail.class);

		
		return "projectcosts/salary_mail_list";
		
	}	
	
	

	@RequestMapping(params = "method=toView")
	public String toView(HttpServletResponse res,HttpServletRequest request){

		String id= request.getParameter("salary_id");
		
		SalaryMail salaryMail = salaryMailService.getSalaryMail(id);
		List<SalaryMailDetail> list = salaryMailService.querySalaryMailDetail(salaryMail);
		
		if(list != null) {
			for (SalaryMailDetail detail : list) {
				String status = "1".equals(detail.getSend_status()) ? "成功" : "<font color=\"red\">失败</font>";
				detail.setSend_status(status);
			}
		}

		request.setAttribute("list", list);
		request.setAttribute("salary1", salaryMail);
		
		return "projectcosts/salary_mail_view";
	}			
	
	
	
	
}
