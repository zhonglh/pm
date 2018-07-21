package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IPotentialClientDao;
import com.pm.domain.business.PotentialClient;
import com.pm.domain.business.Project;

@Component
public class PotentialClientDaoImpl extends BatisDao implements IPotentialClientDao  {
	

	public boolean isExist(PotentialClient potentialClient){

		String sql = "PotentialClientMapping.isExist";
		List<PotentialClient> list = this.query(sql, PotentialClient.class ,potentialClient);
		return (list == null || list.isEmpty()) ? false : true; 
	}

	@Override
	public int addPotentialClient(PotentialClient potentialClient) {
		String sql = "PotentialClientMapping.addPotentialClient";
		return this.insert(sql, potentialClient);
	}

	@Override
	public int updatePotentialClient(PotentialClient potentialClient) {
		String sql = "PotentialClientMapping.updatePotentialClient";
		return this.update(sql, potentialClient);
	}

	@Override
	public void deletePotentialClient(PotentialClient potentialClient) {
		String sql = "PotentialClientMapping.deletePotentialClient";
		this.delete(sql, potentialClient);
	}
	

	public int doDallotSales(PotentialClient potentialClient){
		String sql = "PotentialClientMapping.doDallotSales";
		return this.update(sql, potentialClient);		
	}


	@Override
	public PotentialClient getPotentialClient(String id) {

		String sql = "PotentialClientMapping.getPotentialClient"; 
		PotentialClient potentialClient = new PotentialClient(); 
		potentialClient.setId(id); 
		List<PotentialClient> list = this.query(sql, PotentialClient.class, potentialClient); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<PotentialClient> queryPotentialClient(
		PotentialClient potentialClient,
		Pager<PotentialClient> pager){

		String sql = "PotentialClientMapping.queryPotentialClient"; 
		Pager<PotentialClient> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PotentialClient.class, potentialClient); 
		
		return pager1;
	}


}