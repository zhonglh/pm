package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.PotentialClientFollowup;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IPotentialClientFollowupService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_POTENTIALCLIENT_FOLLWUP)
	public int addPotentialClientFollowup(PotentialClientFollowup[] potentialClientFollowups) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_POTENTIALCLIENT_FOLLWUP)
	public int updatePotentialClientFollowup(PotentialClientFollowup[] potentialClientFollowups) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_POTENTIALCLIENT_FOLLWUP)
	public void deletePotentialClientFollowup(PotentialClientFollowup[] potentialClientFollowup) ;


	public PotentialClientFollowup getPotentialClientFollowup(String id) ;	
	

	public List<PotentialClientFollowup> getPotentialClientFollowupByClient(String potential_client_id) ;	

	public Pager<PotentialClientFollowup> queryPotentialClientFollowup(PotentialClientFollowup potentialClientFollowup, Pager<PotentialClientFollowup> pager);

}