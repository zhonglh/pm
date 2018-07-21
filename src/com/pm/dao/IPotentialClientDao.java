package com.pm.dao;

import com.pm.domain.business.PotentialClient;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IPotentialClientDao {
	

	public boolean isExist(PotentialClient potentialClient);

	public int addPotentialClient(PotentialClient potentialClient) ;

	public int updatePotentialClient(PotentialClient potentialClient) ; 

	public void deletePotentialClient(PotentialClient potentialClient) ;
	

	public int doDallotSales(PotentialClient potentialClient);

	public PotentialClient getPotentialClient(String id) ;	

	public Pager<PotentialClient> queryPotentialClient(PotentialClient potentialClient, Pager<PotentialClient> pager);

}