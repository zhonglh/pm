package com.pm.dao;

import com.pm.domain.business.PotentialClientFollowup;
import com.pm.vo.UserPermit;

import java.util.List;

import com.common.beans.Pager;

public interface IPotentialClientFollowupDao {

	public int addPotentialClientFollowup(PotentialClientFollowup potentialClientFollowup) ;

	public int updatePotentialClientFollowup(PotentialClientFollowup potentialClientFollowup) ; 

	public void deletePotentialClientFollowup(PotentialClientFollowup potentialClientFollowup) ;

	public PotentialClientFollowup getPotentialClientFollowup(String id) ;	

	public List<PotentialClientFollowup> getPotentialClientFollowupByClient(String potential_client_id) ;

	public Pager<PotentialClientFollowup> queryPotentialClientFollowup(PotentialClientFollowup potentialClientFollowup,  Pager<PotentialClientFollowup> pager);

}