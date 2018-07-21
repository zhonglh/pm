package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.SalaryMail;
import com.pm.domain.business.SalaryMailDetail;
import com.pm.vo.UserPermit;

public interface ISalaryMailDao {
	
	

	public Pager<SalaryMail> querySalaryMail(SalaryMail salaryMail,  UserPermit userPermit,Pager<SalaryMail> pager);

	public SalaryMail getSalaryMail(String salary_id);	
	
	public void addSalaryMail(SalaryMail mail);		

	public void updateSalaryMail(SalaryMail mail);	
	
	

	public void cancelSend();
	
	

	public void addSalaryMailDetail(SalaryMailDetail detail);
	
	public List<SalaryMailDetail> querySalaryMailDetail(SalaryMail mail);
	

}
