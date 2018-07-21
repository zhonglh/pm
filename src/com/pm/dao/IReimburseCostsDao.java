package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.ReimburseCosts;
import com.pm.vo.UserPermit;

public interface IReimburseCostsDao {


	public int addReimburseCosts(ReimburseCosts reimburseCosts) ;
	

	public int updateReimburseCosts(ReimburseCosts reimburseCosts) ; 
	

	public void deleteReimburseCosts(ReimburseCosts reimburseCostss) ;
	
	public int verifyReimburseCosts(ReimburseCosts reimburseCosts);
	
	public int unVerifyReimburseCosts(ReimburseCosts reimburseCosts);
	
	
	public ReimburseCosts getReimburseCosts(String reimburse_id) ;	
	

	public Pager<ReimburseCosts> queryReimburseCosts(ReimburseCosts reimburseCosts,  UserPermit userPermit,Pager<ReimburseCosts> pager);

	
	
}
