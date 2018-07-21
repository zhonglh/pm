package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IMailSenderDao;
import com.pm.domain.business.MailSender;
import com.pm.service.IMailSenderService;
import com.pm.vo.UserPermit;

@Component
public class MailSenderServiceImpl implements IMailSenderService {

	@Autowired 
	IMailSenderDao mailSenderDao;


	@Override
	public int addMailSender(MailSender mailSender) {
		return mailSenderDao.addMailSender(mailSender);
	}

	@Override
	public int updateMailSender(MailSender mailSender) {
		return mailSenderDao.updateMailSender(mailSender);
	}

	@Override
	public void deleteMailSender(MailSender[] mailSenders) {
		mailSenderDao.deleteMailSender(mailSenders);

	}

	@Override
	public MailSender getMailSender(String mailSender_id) {
		
		return mailSenderDao.getMailSender(mailSender_id);
	}
	

	@Override
	public void updateStatus(MailSender[] mailSenders) {
		for(MailSender mailSender : mailSenders)
		mailSenderDao.updateStatus(mailSender);
	}

	@Override
	public Pager<MailSender> queryMailSender(MailSender mailSender,
			UserPermit userPermit, Pager<MailSender> pager) {

		return mailSenderDao.queryMailSender(mailSender, userPermit, pager);
	}

}
