package com.pm.dao;

import com.pm.domain.business.PayContract;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPayContractDao {


	public  boolean isNoExist(PayContract payContract) ;

	public int addPayContract(PayContract payContract) ;

	public int updatePayContract(PayContract payContract) ; 

	public void deletePayContract(PayContract payContract) ;

	public void verifyPayContract(PayContract payContract) ;	

	public void unVerifyPayContract(PayContract payContract) ;

	public PayContract getPayContract(String id) ;	

	public Pager<PayContract> queryPayContract(PayContract payContract, UserPermit userPermit, Pager<PayContract> pager);

}