package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.MailSender;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IMailSenderService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_MAILSENDER)
	public int addMailSender(MailSender mailSender) ;  
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_MAILSENDER)
	public int updateMailSender(MailSender mailSender) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_MAILSENDER)
	public void deleteMailSender(MailSender[] mailSenders) ; 
	

	public MailSender getMailSender(String mailSender_id) ;
	
	
	public void updateStatus(MailSender[] mailSenders) ;


	public Pager<MailSender> queryMailSender(MailSender mailSender, UserPermit userPermit,Pager<MailSender> pager);	
}
