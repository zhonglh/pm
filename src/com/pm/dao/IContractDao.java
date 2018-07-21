package com.pm.dao;

import com.pm.domain.business.Contract;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IContractDao {



	public boolean isContractNoExist(Contract contract) ;
	
	public int addContract(Contract contract) ;

	public int updateContract(Contract contract) ; 

	public void deleteContract(Contract contract) ;

	public Contract getContract(String id) ;	

	public Pager<Contract> queryContract(Contract contract,  UserPermit userPermit,Pager<Contract> pager);

}