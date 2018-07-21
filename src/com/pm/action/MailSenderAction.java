package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.domain.business.MailSender;
import com.pm.domain.system.User;
import com.pm.service.IMailSenderService;
import com.pm.util.PubMethod;


@Controller
@RequestMapping(value = "MailSenderAction.do")
public class MailSenderAction extends BaseAction {

	
	@Autowired
	private IMailSenderService mailSenderService;

	private static final String rel = "rel100";
	

	@RequestMapping(params = "method=list")
	public String list(HttpServletResponse res,HttpServletRequest request){
		
		Pager<MailSender> pager= mailSenderService.queryMailSender(new MailSender(), null, PubMethod.getPager(request, MailSender.class));
		
		
		PubMethod.setRequestPager(request, pager,MailSender.class);

		
		return "projectcosts/mail_sender_list";
		
	}	
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(MailSender searchMailSender,HttpServletResponse res,HttpServletRequest request){
		MailSender mailSender = null;
		
		if(searchMailSender.getSender_id() != null){
			mailSender = mailSenderService.getMailSender(searchMailSender.getSender_id());
		}else {
			mailSender = new MailSender();
		}
		
		request.setAttribute("mailSender1", mailSender);
		
		return "projectcosts/mail_sender_edit";
	}			


	@RequestMapping(params = "method=saveMailSender")
	public String saveMailSender(MailSender mailSender,HttpServletResponse res,HttpServletRequest request){
		

		User sessionUser = PubMethod.getUser(request);
		
		mailSender.setBuild_datetime(PubMethod.getCurrentDate());
		mailSender.setBuild_userid(sessionUser.getUser_id());
		mailSender.setBuild_username(sessionUser.getUser_name());
		
		if(mailSender.getSender_id() == null || mailSender.getSender_id().isEmpty()){
			mailSender.setSender_id(IDKit.getUUID());
			mailSenderService.addMailSender(mailSender);
		}else {
			mailSenderService.updateMailSender(mailSender);
		}
		return this.ajaxForwardSuccess(request, rel, true);
	}			
	
	
	

	@RequestMapping(params = "method=updateStatus")
	public String updateStatus(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		int size = ids.length;
		String status = request.getParameter("status");
		
		MailSender[] mailSenderArray = new MailSender[size];
		
		for(int i=0;i<size;i++){
			MailSender mailSender = new MailSender();
			mailSender.setSender_id(ids[i]);
			mailSender.setStatus(status);
			mailSenderArray[i] = mailSender;
		}
		
		mailSenderService.updateStatus(mailSenderArray);

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}

	@RequestMapping(params = "method=deleteMailSender")
	public String deleteMailSender(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		int size = ids.length;
		
		MailSender[] mailSenderArray = new MailSender[size];
		
		for(int i=0;i<size;i++){
			MailSender mailSender = new MailSender();
			mailSender.setSender_id(ids[i]);
			mailSenderArray[i] = mailSender;
		}
		
		mailSenderService.deleteMailSender(mailSenderArray);

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
	
	
	
	
	
	
}
