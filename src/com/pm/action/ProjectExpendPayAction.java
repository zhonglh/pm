package com.pm.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.pm.domain.business.ProjectExpend;
import com.pm.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.Project;
import com.pm.domain.system.User;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.UserPermit;


import com.pm.domain.business.ProjectExpendPay;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("ProjectExpendPayAction.do")
public class ProjectExpendPayAction extends BaseAction {

	private static final String sessionAttr = "ProjectExpendPays";

	private static final String rel = "rel_119";

	@Autowired
	private IProjectService projectService;
	@Autowired
	private IProjectExpendPayService projectExpendpayService;


	@Autowired
	private IProjectExpendService projectExpendService;


	@Autowired
	private IApplyApproveService applyApproveService;	


	@Autowired
	private IRoleService roleService;


	@RequestMapping(params = "method=list")
	public String list(ProjectExpendPay projectExpendpay,HttpServletResponse res,HttpServletRequest request){

		ProjectExpend projectExpend = projectExpendService.getProjectExpend(projectExpendpay.getProject_expend_id());

		UserPermit userPermit = this.getAllPermit();

		paramprocess(request,projectExpendpay);

		Pager<ProjectExpendPay> pager = projectExpendpayService.queryProjectExpendPay(projectExpendpay, userPermit, PubMethod.getPager(request, ProjectExpendPay.class));
		PubMethod.setRequestPager(request, pager,ProjectExpendPay.class);	

		request.setAttribute("projectExpend", projectExpend);
		request.setAttribute("projectExpendpay", projectExpendpay);

		userPermit = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDVIEW.getId());
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		return "projectcosts/project_expend_pay_list";
	}


	private void paramprocess(HttpServletRequest request,ProjectExpendPay projectExpendpay){

	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(ProjectExpendPay searchProjectExpendPay,HttpServletResponse res,HttpServletRequest request){
		ProjectExpendPay projectExpendpay = null;
		String project_expend_id = null;
		if(searchProjectExpendPay != null && StringUtils.isNotEmpty(searchProjectExpendPay.getId())){
			request.setAttribute("next_operation", "updateProjectExpendPay");
			projectExpendpay = projectExpendpayService.getProjectExpendPay(searchProjectExpendPay.getId());	
			if(projectExpendpay.getVerify_userid() != null && projectExpendpay.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}

			project_expend_id = projectExpendpay.getProject_expend_id();
		}else {
			project_expend_id = searchProjectExpendPay.getProject_expend_id();
			request.setAttribute("next_operation", "addProjectExpendPay");		
			User sessionUser = PubMethod.getUser(request);
			projectExpendpay = new ProjectExpendPay();	
			projectExpendpay.setBuild_userid(sessionUser.getUser_id());
			projectExpendpay.setBuild_username(sessionUser.getUser_name());
			projectExpendpay.setBuild_datetime(PubMethod.getCurrentDate());
		}
		request.setAttribute("projectExpendpay1", projectExpendpay);
		request.setAttribute("projectExpend1", projectExpendService.getProjectExpend(project_expend_id));
		return "projectcosts/project_expend_pay_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(ProjectExpendPay searchProjectExpendPay,HttpServletResponse res,HttpServletRequest request){
		ProjectExpendPay projectExpendpay = projectExpendpayService.getProjectExpendPay(searchProjectExpendPay.getId());
		request.setAttribute("projectExpendpay1", projectExpendpay);

		request.setAttribute("projectExpend1", projectExpendService.getProjectExpend(projectExpendpay.getProject_expend_id()));

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.PROJECTEXPENDUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( projectExpendpay.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.PROJECT_EXPEND_PAY.name(), projectExpendpay.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.PROJECT_EXPEND_PAY.name(),  projectExpendpay.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", projectExpendpay.getVerify_userid());
		request.setAttribute("data_id", projectExpendpay.getId());
		request.setAttribute("data_type", EnumEntityType.PROJECT_EXPEND_PAY.name());
		return "projectcosts/project_expend_pay_view";
	}


	@RequestMapping(params = "method=addProjectExpendPay")
	public String addProjectExpendPay(ProjectExpendPay addProjectExpendPay,HttpServletResponse res,HttpServletRequest request){
		ProjectExpendPay projectExpendpay = addProjectExpendPay;	
		paramprocess(request,projectExpendpay);

		ProjectExpend projectExpend = projectExpendService.getProjectExpend(addProjectExpendPay.getProject_expend_id());

		projectExpendpay.setProject_id(projectExpend.getProject_id());
		projectExpendpay.setProject_name(projectExpend.getProject_name());
		projectExpendpay.setProject_no(projectExpend.getProject_no());
		projectExpendpay.setSub_contractor_name(projectExpend.getSub_contractor_name());
		projectExpendpay.setUse_month(projectExpend.getUse_month());

		User sessionUser = PubMethod.getUser(request);
		projectExpendpay.setId(IDKit.generateId());
		projectExpendpay.setBuild_datetime(PubMethod.getCurrentDate());
		projectExpendpay.setBuild_userid(sessionUser.getUser_id());
		projectExpendpay.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = projectExpendpayService.addProjectExpendPay(projectExpendpay);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PROJECT_EXPEND_PAY.name(), projectExpendpay.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}


	@RequestMapping(params = "method=updateProjectExpendPay")
	public String updateProjectExpendPay(ProjectExpendPay updateProjectExpendPay,HttpServletResponse res,HttpServletRequest request){
		ProjectExpendPay projectExpendpay = updateProjectExpendPay;	
		paramprocess(request,projectExpendpay);	
		int count = 0;
		try{
			count = projectExpendpayService.updateProjectExpendPay(projectExpendpay);	
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}	


	@RequestMapping(params = "method=verifyProjectExpendPay")
	public String verifyProjectExpendPay(ProjectExpendPay projectExpendpay,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		projectExpendpay.setVerify_datetime(PubMethod.getCurrentDate());
		projectExpendpay.setVerify_userid(sessionUser.getUser_id());
		projectExpendpay.setVerify_username(sessionUser.getUser_name());
		projectExpendpayService.verifyProjectExpendPay(projectExpendpay);
		ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.PROJECT_EXPEND_PAY.name(), projectExpendpay.getId(), sessionUser);
		applyApproveService.addApplyApprove(applyApprove);
		return this.ajaxForwardSuccess(request, rel, true);
	}


	@RequestMapping(params = "method=batchVerifyProjectExpendPay")
	public String batchVerifyProjectExpendPay(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0){
			this.ajaxForwardError(request, "请先选择单据！", false);
		}
		for(String id : ids){
			ProjectExpendPay projectExpendpay = new ProjectExpendPay();
			projectExpendpay.setVerify_datetime(PubMethod.getCurrentDate());
			projectExpendpay.setVerify_userid(sessionUser.getUser_id());
			projectExpendpay.setVerify_username(sessionUser.getUser_name());
			projectExpendpay.setId(id);
			projectExpendpayService.verifyProjectExpendPay(projectExpendpay);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.PROJECT_EXPEND_PAY.name(), projectExpendpay.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}


	@RequestMapping(params = "method=deleteProjectExpendPay")
	public String deleteProjectExpendPay(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		Timestamp deleteDate = PubMethod.getCurrentDate();
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			ProjectExpendPay[] projectExpendpays = new ProjectExpendPay[ids.length];
			int index = 0;
			for(String id : ids){
				ProjectExpendPay projectExpendpay = new ProjectExpendPay();
				projectExpendpay.setId(id);
				projectExpendpays[index] = projectExpendpay;
				index ++ ;
			}
			if(projectExpendpays != null && projectExpendpays.length > 0) {
				projectExpendpayService.deleteProjectExpendPay(projectExpendpays);
			}
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@RequestMapping(params = "method=export")
	public void export(ProjectExpendPay projectExpendpay,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEVIEW.getId());		
		Pager<ProjectExpendPay> pager = projectExpendpayService.queryProjectExpendPay(projectExpendpay, userPermit, PubMethod.getPagerByAll(request, ProjectExpendPay.class));
		try{
			BusinessExcel.export(res, null, pager.getResultList(), ProjectExpendPay.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	




}