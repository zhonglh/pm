package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.SalaryMail;
import com.pm.domain.business.SalaryMailDetail;
import com.pm.vo.UserPermit;

public interface ISalaryMailService {
	

	public Pager<SalaryMail> querySalaryMail(SalaryMail salaryMail,  UserPermit userPermit,Pager<SalaryMail> pager);
	
	public void sendMail(String[] mails, String userId);
	
	
	public void cancelSend();
	
	public void doAfterSend(SalaryMail mail);
	
	public SalaryMail getSalaryMail(String salary_id);
	

	public List<SalaryMailDetail> querySalaryMailDetail(SalaryMail mail);

}
