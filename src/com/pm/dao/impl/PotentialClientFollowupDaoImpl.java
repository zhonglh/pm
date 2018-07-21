package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IPotentialClientFollowupDao;
import com.pm.domain.business.PotentialClientFollowup;

@Component
public class PotentialClientFollowupDaoImpl extends BatisDao implements IPotentialClientFollowupDao  {

	@Override
	public int addPotentialClientFollowup(PotentialClientFollowup potentialClientFollowup) {
		String sql = "PotentialClientFollowupMapping.addPotentialClientFollowup";
		return this.insert(sql, potentialClientFollowup);
	}

	@Override
	public int updatePotentialClientFollowup(PotentialClientFollowup potentialClientFollowup) {
		String sql = "PotentialClientFollowupMapping.updatePotentialClientFollowup";
		return this.update(sql, potentialClientFollowup);
	}

	@Override
	public void deletePotentialClientFollowup(PotentialClientFollowup potentialClientFollowup) {
		String sql = "PotentialClientFollowupMapping.deletePotentialClientFollowup";
		this.delete(sql, potentialClientFollowup);
	}


	@Override
	public PotentialClientFollowup getPotentialClientFollowup(String id) {

		String sql = "PotentialClientFollowupMapping.getPotentialClientFollowup"; 
		PotentialClientFollowup potentialClientFollowup = new PotentialClientFollowup(); 
		potentialClientFollowup.setId(id); 
		List<PotentialClientFollowup> list = this.query(sql, PotentialClientFollowup.class, potentialClientFollowup); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}
	
	

	@Override
	public List<PotentialClientFollowup> getPotentialClientFollowupByClient(String potential_client_id){
		String sql = "PotentialClientFollowupMapping.getPotentialClientFollowupByClient"; 
		PotentialClientFollowup potentialClientFollowup = new PotentialClientFollowup(); 
		potentialClientFollowup.setPotential_client_id(potential_client_id);
		return  this.query(sql, PotentialClientFollowup.class, potentialClientFollowup); 
	}

	@Override
	public Pager<PotentialClientFollowup> queryPotentialClientFollowup(
			PotentialClientFollowup potentialClientFollowup,
			Pager<PotentialClientFollowup> pager){
		
		String sql = "PotentialClientFollowupMapping.queryPotentialClientFollowup"; 
		Pager<PotentialClientFollowup> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PotentialClientFollowup.class, potentialClientFollowup); 
		return pager1;
	}


}