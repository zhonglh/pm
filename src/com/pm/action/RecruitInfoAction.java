package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.pm.domain.business.RecruitInfo;
import com.pm.domain.system.User;
import com.pm.service.IRecruitInfoService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumAjaxDone;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("RecruitInfoAction.do")
public class RecruitInfoAction extends BaseAction {


	private static final String rel = "rel17";

	@Autowired
	private IRecruitInfoService recruitInfoService;




	@Autowired
	private IRoleService roleService;
	
	
	private void  paramprocess(HttpServletRequest request,RecruitInfo recruitInfo){		
		recruitInfo.setResume_no(request.getParameter("resume_no"));
		
		if(recruitInfo.getResume_no() != null)
			recruitInfo.setResume_no(recruitInfo.getResume_no().trim());
		
		recruitInfo.setResume_source(request.getParameter("rs.id"));
		recruitInfo.setResume_source_name(request.getParameter("rs.dic_data_name"));
	}


	@RequestMapping(params = "method=list")
	public String list(RecruitInfo recruitInfo,HttpServletResponse res,HttpServletRequest request){

		paramprocess(request,recruitInfo);
		request.setAttribute("recruitInfo1", recruitInfo);	
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECRUITINFOVIEW.getId());
		Pager<RecruitInfo> pager = recruitInfoService.queryRecruitInfo(new RecruitInfo(), userPermit, PubMethod.getPager(request, RecruitInfo.class));
		PubMethod.setRequestPager(request, pager,RecruitInfo.class);	
	
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.RECRUITINFOREVIEW.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());

		return "basicdata/recruitinfo_list";
	}



	/**
	 * 保存简历
	 * @param recruitInfo
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=reviewRecruitInfo")
	public String reviewRecruitInfo(RecruitInfo recruitInfo,HttpServletResponse res,HttpServletRequest request){


		
		
		
		paramprocess(request,recruitInfo);		
		
		request.setAttribute("recruitInfo1", recruitInfo);	
		
		User sessionUser = PubMethod.getUser(request);
		
		RecruitInfo recruitInfoByNoSource = recruitInfoService.getRecruitInfoByNoSource(recruitInfo);
		
		String opera = null;
		if(recruitInfoByNoSource == null) 		{
			opera = this.ajaxForwardSuccess(request, rel, false);
			request.setAttribute(EnumAjaxDone.message.name(), "简历编号:  <b>"+recruitInfo.getResume_no()+"</b><br>简历来源: <b> "+recruitInfo.getResume_source_name()+"</b><br><br>不存在，可以保存！");
			return opera;
		}else {
			String message = "该简历已经存在！ <br><br>"+recruitInfoByNoSource.getBuild_username()+ "于" + DateKit.fmtDateToYMDHMS(recruitInfoByNoSource.getBuild_datetime()) + "录入！";
			opera =  this.ajaxForwardError(request, message , false);
			request.setAttribute(EnumAjaxDone.message.name(), message);
			return opera;
		}
	}
	

	/**
	 * 保存简历
	 * @param recruitInfo
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=addRecruitInfo")
	public String addRecruitInfo(RecruitInfo recruitInfo,HttpServletResponse res,HttpServletRequest request){


		paramprocess(request,recruitInfo);		
		
		request.setAttribute("recruitInfo1", recruitInfo);	
		
		User sessionUser = PubMethod.getUser(request);
		recruitInfo.setId(IDKit.generateId());
		recruitInfo.setBuild_datetime(PubMethod.getCurrentDate());
		recruitInfo.setBuild_userid(sessionUser.getUser_id());
		recruitInfo.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = recruitInfoService.addRecruitInfo(recruitInfo);
		}catch(Exception e){
			return this.ajaxForwardError(request, "该简历已经存在！", true);
		}
		if(count == 1) 		{
			String opera = this.ajaxForwardSuccess(request, rel, false);
			request.setAttribute(EnumAjaxDone.message.name(), "简历编号:  <b>"+recruitInfo.getResume_no()+"</b><br>简历来源: <b> "+recruitInfo.getResume_source_name()+"</b><br><br>最新简历编号，系统已保存");
			return opera;
		}else {
			return this.ajaxForwardError(request, "该简历已经存在！", false);
		}
	}




}