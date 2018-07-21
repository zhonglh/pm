package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.PotentialClient;
import com.pm.domain.business.Project;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IPotentialClientService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_POTENTIALCLIENT)
	public int addPotentialClient(PotentialClient potentialClient) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_POTENTIALCLIENT)
	public int updatePotentialClient(PotentialClient potentialClient) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_POTENTIALCLIENT)
	public void deletePotentialClient(PotentialClient[] potentialClients) ;
	
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DALLOT,entity_type=LogConstant.ENTITY_POTENTIALCLIENT)
	public int doDallotSales(PotentialClient potentialClient); 

	public PotentialClient getPotentialClient(String id) ;	

	public Pager<PotentialClient> queryPotentialClient(PotentialClient potentialClient,  Pager<PotentialClient> pager);
	

	public boolean isExist(PotentialClient potentialClient);

}