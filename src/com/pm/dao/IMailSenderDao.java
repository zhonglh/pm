package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.MailSender;
import com.pm.vo.UserPermit;

public interface IMailSenderDao {

	public int addMailSender(MailSender mailSender) ;  
	

	public int updateMailSender(MailSender mailSender) ; 
	

	public void deleteMailSender(MailSender[] mailSenders) ; 
	

	public MailSender getMailSender(String mailSender_id) ;
	

	public void updateStatus(MailSender mailSender) ;


	public Pager<MailSender> queryMailSender(MailSender mailSender, UserPermit userPermit,Pager<MailSender> pager);
}
