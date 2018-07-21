package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.MailSender;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IMailSenderService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class MailSenderLog extends BasicLog {


	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IMailSenderService mailSenderService = SpringContextUtil.getApplicationContext().getBean(IMailSenderService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			MailSender[] mailSenders = (MailSender[])invocation.getArguments()[0];
			if(mailSenders == null || mailSenders.length == 0) return null;
			for(MailSender mailSender1 : mailSenders){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				MailSender mailSender = mailSenderService.getMailSender(mailSender1.getSender_id());
				if(mailSender == null) mailSender = new MailSender();
				log.setEntity_id(mailSender1.getSender_id());
				log.setEntity_name(mailSender.getFrom_username()==null?mailSender.getFrom_username():mailSender1.getFrom_username());
				List<LogDetail>  details = PubMethod.getLogDetails(log,MailSender.class, mailSender,mailSender1);	
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				MailSender mailSender = (MailSender)invocation.getArguments()[0];
				MailSender premailSender = new MailSender();				
				log.setEntity_id(mailSender.getSender_id());
				log.setEntity_name(mailSender.getFrom_username()==null?mailSender.getFrom_username():premailSender.getFrom_username());
				details = PubMethod.getLogDetails(log,MailSender.class, premailSender,mailSender);	
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				MailSender mailSender = (MailSender)invocation.getArguments()[0];
				MailSender premailSender = mailSenderService.getMailSender(mailSender.getSender_id());				
				log.setEntity_id(mailSender.getSender_id());
				log.setEntity_name(premailSender.getFrom_username()==null?premailSender.getFrom_username():mailSender.getFrom_username());
				details = PubMethod.getLogDetails(log,MailSender.class, premailSender,mailSender);				
				log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	

}
