package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.ISalaryMailDao;
import com.pm.domain.business.SalaryMail;
import com.pm.domain.business.SalaryMailDetail;
import com.pm.vo.UserPermit;


@Component
public class SalaryMailDaoImpl extends BatisDao implements ISalaryMailDao {


	@Override
	public Pager<SalaryMail> querySalaryMail(SalaryMail salaryMail,
			UserPermit userPermit, Pager<SalaryMail> pager) {

		String sql = "SalaryMailMapping.querySalaryMail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, SalaryMail.class, salaryMail,userPermit);
		
	}

	@Override
	public SalaryMail getSalaryMail(String salary_id) {

		String sql = "SalaryMailMapping.getSalaryMail";
		SalaryMail salaryMail = new SalaryMail();
		salaryMail.setSalary_id(salary_id);
		List<SalaryMail> list = this.query(sql, SalaryMail.class, salaryMail);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
		
	}

	@Override
	public void addSalaryMail(SalaryMail mail) {
		String sql = "SalaryMailMapping.addSalaryMail";
		this.insert(sql, mail);
	}

	@Override
	public void updateSalaryMail(SalaryMail mail) {
		String sql = "SalaryMailMapping.updateSalaryMail";
		this.update(sql, mail);

	}
	
	
	

	public void cancelSend(){
		String sql = "SalaryMailMapping.cancelSend";
		this.delete(sql);
	}


	@Override
	public void addSalaryMailDetail(SalaryMailDetail detail) {
		String sql = "SalaryMailMapping.addSalaryMailDetail";
		this.insert(sql, detail);
	}
	
	
	

	@Override
	public List<SalaryMailDetail> querySalaryMailDetail(SalaryMail mail){

		String sql = "SalaryMailMapping.querySalaryMailDetail";
		return this.query(sql, SalaryMailDetail.class, mail);
	}

}
