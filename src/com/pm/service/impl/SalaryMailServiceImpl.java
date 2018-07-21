package com.pm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.mail.MailSenderInfo;
import com.common.utils.ThreadPoolService;
import com.common.utils.file.FileKit;
import com.pm.dao.IParamsDao;
import com.pm.dao.ISalaryMailDao;
import com.pm.domain.business.MailSender;
import com.pm.domain.business.Params;
import com.pm.domain.business.SalaryMail;
import com.pm.domain.business.SalaryMailDetail;
import com.pm.service.IMailSenderService;
import com.pm.service.ISalaryMailService;
import com.pm.service.IStaffCostService;
import com.pm.task.SendMailTask;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.vo.UserPermit;


@Component
public class SalaryMailServiceImpl implements ISalaryMailService {
	
	Logger logger = Logger.getLogger(this.getClass());
	

	@Autowired ISalaryMailDao salaryMailDao;

	@Autowired  IParamsDao paramsDao;
	

	@Autowired  private IStaffCostService staffCostService;
	

	@Autowired
	private IMailSenderService mailSenderService;


	@Autowired ThreadPoolService threadPoolService;	
	
	
	
	
	private void queryMailSender(List<MailSenderInfo>  mailSenderInfos ){
		
		
		MailSender mailSender = new MailSender();
		mailSender.setStatus("1");
		Pager<MailSender> pager = mailSenderService.queryMailSender(mailSender, null, PubMethod.getPagerByAll( MailSender.class));
		
		if(pager.getResultList() == null || pager.getResultList().isEmpty()) return ;
		
		
		
		for(MailSender mailSender1 : pager.getResultList()){
			
			
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(mailSender1.getMail_server_host());
			mailInfo.setMailServerPort(mailSender1.getMail_server_port());
			mailInfo.setValidate(true); 
			
			//mailInfo.setFromAddress(paramNN.getParam_value()+"<"+paramUN.getParam_value()+">");
			mailInfo.setFromAddress(mailSender1.getFrom_username());
		    
			mailInfo.setUserName(mailSender1.getFrom_username());
			mailInfo.setPassword(mailSender1.getFrom_password());
			
			mailSenderInfos.add(mailInfo);
		}
		
		
		
		
	}
	
	private int getMailInfo(int size,int index){
		return index%size;
	}
	

	@Override
	public Pager<SalaryMail> querySalaryMail(SalaryMail salaryMail,
			UserPermit userPermit, Pager<SalaryMail> pager) {
		return salaryMailDao.querySalaryMail(salaryMail, userPermit, pager);
	}

	@Override
	public synchronized  void  sendMail(String[] mails, String userId) {
		
		
		logger.info("start send mail ............................");
		
		if(mails == null || mails.length == 0) return ;

		List<MailSenderInfo>  mailSenderInfos = new ArrayList<MailSenderInfo>();

		queryMailSender(mailSenderInfos );
		if(mailSenderInfos==null || mailSenderInfos.isEmpty())
			throw new RuntimeException("没有设置启用的发件邮箱参数！");
		
		List<SalaryMail> sends = new ArrayList<SalaryMail>();
		for(String salary_id : mails){
			SalaryMail salaryMail = salaryMailDao.getSalaryMail(salary_id);
			if(salaryMail == null) continue;
			if(salaryMail.getSend_status() == null || salaryMail.getSend_status().isEmpty()){
				//表示没有发送过邮件
				salaryMail.setSend_status(BusinessUtil.SEND_SALARY_MAIL_WAIT);
				salaryMailDao.addSalaryMail(salaryMail);
			}else if(salaryMail.getSend_status().equals(BusinessUtil.SEND_SALARY_MAIL_WAIT)){
				//表示要发送  正在发送的邮件， 直接取消
				continue;
			}else {
				salaryMail.setSend_status(BusinessUtil.SEND_SALARY_MAIL_WAIT);
				salaryMailDao.updateSalaryMail(salaryMail);
			}
			
			salaryMail.setSend_userId(userId);
			sends.add(salaryMail);
		}
		
		logger.debug(sends);
		int size = mailSenderInfos.size();
		if(!sends.isEmpty()) {
			int index = 0;
			for(SalaryMail mail : sends){				
				SendMailTask task = new SendMailTask(this,staffCostService,mail, mailSenderInfos.get(getMailInfo(size,index)));
				index ++;
				Thread t = new Thread(task);
				t.start();
				//threadPoolService.execute()
				
			}
		}
	}
	
	

	@Override
	public void cancelSend(){
		this.salaryMailDao.cancelSend();
	}
	

	@Override
	public SalaryMail getSalaryMail(String salary_id) {
		return salaryMailDao.getSalaryMail(salary_id);
	}

	@Override
	public void doAfterSend(SalaryMail mail) {
		if(mail.getDetails() == null) return ;
		SalaryMailDetail detail = mail.getDetails().get(0);
		if(detail == null) return ;
		
		mail.setMax_detail_id(detail.getDetail_id());
		mail.setSend_status(BusinessUtil.SEND_SALARY_MAIL_COMPLETE);	
		salaryMailDao.addSalaryMailDetail(detail);
		salaryMailDao.updateSalaryMail(mail);			
	}

	@Override
	public List<SalaryMailDetail> querySalaryMailDetail(SalaryMail mail) {
		return salaryMailDao.querySalaryMailDetail(mail);
	}

}
