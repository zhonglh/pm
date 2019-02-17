package com.pm.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
import com.pm.service.IApplyApproveService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
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


import com.pm.domain.business.CommonCost;
import com.pm.service.ICommonCostService;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("CommonCostAction.do")
public class CommonCostAction extends BaseAction {

	private static final String sessionAttr = "CommonCosts";

	private static final String rel = "rel26";

	@Autowired
	private IProjectService projectService;
	@Autowired
	private ICommonCostService commoncostService;


	@Autowired
	private IApplyApproveService applyApproveService;	


	@Autowired
	private IRoleService roleService;


	@RequestMapping(params = "method=list")
	public String list(CommonCost commoncost,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTVIEW.getId());

		paramprocess(request,commoncost);

		Pager<CommonCost> pager = commoncostService.queryCommonCost(commoncost, userPermit, PubMethod.getPager(request, CommonCost.class));
		PubMethod.setRequestPager(request, pager,CommonCost.class);	

		request.setAttribute("commoncost", commoncost);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		return "projectcosts/commoncost_list";
	}


	private void paramprocess(HttpServletRequest request,CommonCost commoncost){

	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(CommonCost searchCommonCost,HttpServletResponse res,HttpServletRequest request){
		CommonCost commoncost = null;
		if(searchCommonCost != null && searchCommonCost.getId()!=null){
			request.setAttribute("next_operation", "updateCommonCost");
			commoncost = commoncostService.getCommonCost(searchCommonCost.getId());	
			if(commoncost.getVerify_userid() != null && commoncost.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
		}else {
			request.setAttribute("next_operation", "addCommonCost");		
			User sessionUser = PubMethod.getUser(request);
			commoncost = new CommonCost();	
			commoncost.setBuild_userid(sessionUser.getUser_id());
			commoncost.setBuild_username(sessionUser.getUser_name());
			commoncost.setBuild_datetime(PubMethod.getCurrentDate());
			String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");
			commoncost.setUse_month(Integer.parseInt(month));
		}
		request.setAttribute("commoncost1", commoncost);
		return "projectcosts/commoncost_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(CommonCost searchCommonCost,HttpServletResponse res,HttpServletRequest request){
		CommonCost commoncost = commoncostService.getCommonCost(searchCommonCost.getId());
		request.setAttribute("commoncost1", commoncost);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.COMMONCOST.name(), commoncost.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.COMMONCOST.name(),  commoncost.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", commoncost.getVerify_userid());
		request.setAttribute("data_id", commoncost.getId());
		request.setAttribute("data_type", EnumEntityType.COMMONCOST.name());
		return "projectcosts/commoncost_view";
	}


	@RequestMapping(params = "method=addCommonCost")
	public String addCommonCost(CommonCost addCommonCost,HttpServletResponse res,HttpServletRequest request){
		CommonCost commoncost = addCommonCost;	
		paramprocess(request,commoncost);
		User sessionUser = PubMethod.getUser(request);
		commoncost.setId(IDKit.generateId());
		commoncost.setBuild_datetime(PubMethod.getCurrentDate());
		commoncost.setBuild_userid(sessionUser.getUser_id());
		commoncost.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = commoncostService.addCommonCost(commoncost);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.COMMONCOST.name(), commoncost.getId(), sessionUser);
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


	@RequestMapping(params = "method=updateCommonCost")
	public String updateCommonCost(CommonCost updateCommonCost,HttpServletResponse res,HttpServletRequest request){
		CommonCost commoncost = updateCommonCost;	
		paramprocess(request,commoncost);	
		int count = 0;
		try{
			count = commoncostService.updateCommonCost(commoncost);	
		}catch(Exception e){
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "该单据已经存在！", true);
		}
	}	


	@RequestMapping(params = "method=verifyCommonCost")
	public String verifyCommonCost(CommonCost commoncost,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		commoncost.setVerify_datetime(PubMethod.getCurrentDate());
		commoncost.setVerify_userid(sessionUser.getUser_id());
		commoncost.setVerify_username(sessionUser.getUser_name());
		commoncostService.verifyCommonCost(commoncost);
		ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.COMMONCOST.name(), commoncost.getId(), sessionUser);
		applyApproveService.addApplyApprove(applyApprove);
		return this.ajaxForwardSuccess(request, rel, true);
	}


	@RequestMapping(params = "method=batchVerifyCommonCost")
	public String batchVerifyCommonCost(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0){
			this.ajaxForwardError(request, "请先选择单据！", false);
		}
		for(String id : ids){
			CommonCost commoncost = new CommonCost();
			commoncost.setVerify_datetime(PubMethod.getCurrentDate());
			commoncost.setVerify_userid(sessionUser.getUser_id());
			commoncost.setVerify_username(sessionUser.getUser_name());
			commoncost.setId(id);
			commoncostService.verifyCommonCost(commoncost);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.COMMONCOST.name(), commoncost.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}


	@RequestMapping(params = "method=deleteCommonCost")
	public String deleteCommonCost(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		Timestamp deleteDate = PubMethod.getCurrentDate();
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			CommonCost[] commoncosts = new CommonCost[ids.length];
			int index = 0;
			for(String id : ids){
				CommonCost commoncost = new CommonCost();
				commoncost.setId(id);
				commoncosts[index] = commoncost;
				index ++ ;
			}
			if(commoncosts != null && commoncosts.length > 0) {
				commoncostService.deleteCommonCost(commoncosts);
			}
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception {
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/commoncost.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "公共费用.xlsx" ,response,false);
		return null;  
	}  	


	@RequestMapping(params = "method=export")
	public void export(CommonCost commoncost,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.REIMBURSEVIEW.getId());		
		Pager<CommonCost> pager = commoncostService.queryCommonCost(commoncost, userPermit, PubMethod.getPagerByAll(request, CommonCost.class));
		try{
			BusinessExcel.export(res, null, pager.getResultList(), CommonCost.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	


	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "commonCost/commoncost_upload";		
	}	


	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<String[]> list = getExcel(file,10,res,request);
		List<CommonCost> commoncosts = PubMethod.stringArray2List(list, CommonCost.class);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		Map<String,Project>  projectMap = new HashMap<String,Project>();
		if(projects.getResultList() != null) {
			for(Project project : projects.getResultList()){
				projectMap.put(project.getProject_name(), project);		
			}
		}
		for(CommonCost commoncost : commoncosts){
			 checkCommonCost(commoncost,projectMap);
		}
		User sessionUser = PubMethod.getUser(request);
		boolean isAllOK = true;
		for(CommonCost commoncost : commoncosts){
			if(commoncost.getErrorInfo()==null || commoncost.getErrorInfo().length() <= 0){
				try{
					commoncost.setId(IDKit.generateId());
					commoncost.setBuild_datetime(PubMethod.getCurrentDate());
					commoncost.setBuild_userid(sessionUser.getUser_id());
					commoncost.setBuild_username(sessionUser.getUser_name());
					int count = commoncostService.addCommonCost(commoncost);
					if(count == 0){
						commoncost.setErrorInfo("已经有此记录");
						isAllOK = false;
					}
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key")!=-1 || e.getMessage().indexOf("key")!=-1) {
						commoncost.setErrorInfo("已经有此记录");
					}
					else {
						commoncost.setErrorInfo(e.getMessage());
					}
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, commoncosts);
			request.setAttribute("forwardUrl", request.getContextPath()+"/CommonCostAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
	}	


	private boolean checkCommonCost(CommonCost commoncost,	Map<String,Project>  projectMap){
		boolean b = true;
		if(commoncost.getErrorInfo() != null && !commoncost.getErrorInfo().isEmpty()) {
			b = false;
		}
		return b;
	}


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<CommonCost> list = (List<CommonCost>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "commonCost/commoncost_excel_list";
	}



}