package com.pm.dao;

import com.pm.domain.business.ProjectExpendPay;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IProjectExpendPayDao {

	public int addProjectExpendPay(ProjectExpendPay projectExpendpay) ;

	public int updateProjectExpendPay(ProjectExpendPay projectExpendpay) ; 

	public void deleteProjectExpendPay(ProjectExpendPay projectExpendpay) ;

	public void verifyProjectExpendPay(ProjectExpendPay projectExpendpay) ;	

	public void unVerifyProjectExpendPay(ProjectExpendPay projectExpendpay) ;

	public ProjectExpendPay getProjectExpendPay(String id) ;

	public ProjectExpendPay getSumByProjectExpend(ProjectExpendPay projectExpendpay)  ;

	public Pager<ProjectExpendPay> queryProjectExpendPay(ProjectExpendPay projectExpendpay, UserPermit userPermit, Pager<ProjectExpendPay> pager);

}