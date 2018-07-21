package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IMailSenderDao;
import com.pm.domain.business.MailSender;
import com.pm.domain.system.Dept;
import com.pm.vo.UserPermit;

@Component
public class MailSenderDaoImpl extends BatisDao implements IMailSenderDao {


	@Override
	public int addMailSender(MailSender mailSender) {
		String sql = "SalaryMailMapping.addMailSender";
		return this.insert(sql, mailSender);
	}

	@Override
	public int updateMailSender(MailSender mailSender) {
		String sql = "SalaryMailMapping.updateMailSender";
		return this.update(sql, mailSender);
	}

	@Override
	public void deleteMailSender(MailSender[] mailSenders) {
		if(mailSenders == null) return ;
		
		String sql = "SalaryMailMapping.deleteMailSender";
		for(MailSender mailSender : mailSenders){
			this.delete(sql, mailSender);
		}

	}

	@Override
	public MailSender getMailSender(String mailSender_id) {
		String sql = "SalaryMailMapping.getMailSender";
		MailSender mailSender = new MailSender();
		mailSender.setSender_id(mailSender_id);
		List<MailSender> list = this.query(sql, MailSender.class, mailSender);
		if(list != null && !list.isEmpty()) return list.get(0);
		else return null;
	}
	
	

	@Override
	public void updateStatus(MailSender mailSender) {
		String sql = "SalaryMailMapping.updateStatus";
		this.update(sql, mailSender);
	}

	@Override
	public Pager<MailSender> queryMailSender(MailSender mailSender,
			UserPermit userPermit, Pager<MailSender> pager) {
		String sql = "SalaryMailMapping.queryMailSender";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, MailSender.class, mailSender,userPermit);
	}

}
