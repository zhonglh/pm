package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IUserService;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;



@Controller
@RequestMapping(value = "ApplyApproveAction.do")
public class ApplyApproveAction extends BaseAction {
	

	private static final String rel = "rel30";

	@Autowired
	private IApplyApproveService applyApproveService;
	

	@Autowired
	private IMonthlyStatementService monthlyStatementService;
	
	

	@Autowired
	private IUserService userService;
	
	
	
	

	@RequestMapping(params = "method=addApplyApprove")
	public String addApplyApprove(ApplyApprove applyApprove,HttpServletResponse res,HttpServletRequest request){
		
		ApplyApprove tmp =applyApproveService.needHandle(applyApprove.getData_type(), applyApprove.getData_id());
		if(EnumApplyApproveType.APPLY.getKey().equals(applyApprove.getOperation_type())){
			if(tmp != null) return this.ajaxForwardError(request, "取消核单已经申请了！");
		}else if(EnumApplyApproveType.APPROVE.getKey().equals(applyApprove.getOperation_type())){
			if(tmp == null) return this.ajaxForwardError(request, "已经批复过了!");
		}
		
		

		if(EnumApplyApproveType.APPLY.getKey().equals(applyApprove.getOperation_type())){
			applyApprove.setNeed_approve("1");
		}else if(EnumApplyApproveType.APPROVE.getKey().equals(applyApprove.getOperation_type())){
			applyApprove.setNeed_approve("0");			
		}
		
		User sessionUser = PubMethod.getUser(request);

		applyApprove.setId(IDKit.generateId());
		applyApprove.setUser_id(sessionUser.getUser_id());
		applyApprove.setUser_name(sessionUser.getUser_name());
		applyApprove.setOperatioin_time(PubMethod.getCurrentDate());		
		applyApprove.setDelete_flag(BusinessUtil.NOT_DELETEED);
		
						
		try{
			ThreadLocalUser.setUser(sessionUser);
			applyApproveService.doApprove(tmp,applyApprove);
		}finally{
			ThreadLocalUser.setUser(null);
		}
		
		return this.ajaxForwardSuccess(request, rel, true);
		
	}
	


	@RequestMapping(params = "method=toApplyApprove")
	public String toApplyApprove(ApplyApprove applyApprove1,HttpServletResponse res,HttpServletRequest request){
		
		ApplyApprove applyApprove = applyApprove1;
		
		if(EnumApplyApproveType.APPLY.getKey().equals(applyApprove.getOperation_type())){
			applyApprove.setNeed_approve("1");
		}else if(EnumApplyApproveType.APPROVE.getKey().equals(applyApprove.getOperation_type())){
			applyApprove.setNeed_approve("0");			
		}
		return "common/inputdescription";
	}
	
	


	@RequestMapping(params = "method=cancelApplyApprove")
	public String cancelApplyApprove(ApplyApprove applyApprove,HttpServletResponse res,HttpServletRequest request){
		
		ApplyApprove tmp =applyApproveService.needHandle(applyApprove.getData_type(), applyApprove.getData_id());
		if(tmp == null ) return this.ajaxForwardError(request, "没有申请可以撤销了！");

		applyApproveService.deleteApplyApprove(tmp);
		return this.ajaxForwardSuccess(request, rel, true);
	}
	

}
