package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IPotentialClientDao;
import com.pm.domain.business.PotentialClient;
import com.pm.service.IPotentialClientService;

@Component
public class PotentialClientServiceImpl implements  IPotentialClientService {

	@Autowired IPotentialClientDao potentialClientDao;
	
	

	@Override
	public boolean isExist(PotentialClient potentialClient){
		return potentialClientDao.isExist(potentialClient);
	}
	
	@Override
	public int addPotentialClient(PotentialClient potentialClient) {
		return potentialClientDao.addPotentialClient(potentialClient);
	}

	@Override
	public int updatePotentialClient(PotentialClient potentialClient) {
		return potentialClientDao.updatePotentialClient(potentialClient);
	}

	@Override
	public void deletePotentialClient(PotentialClient[] potentialClients) {
		for(PotentialClient potentialClient : potentialClients){
			potentialClientDao.deletePotentialClient(potentialClient);
		}
	}
	
	public int doDallotSales(PotentialClient potentialClient){
		return potentialClientDao.doDallotSales(potentialClient);
	}


	@Override
	public PotentialClient getPotentialClient(String id) {
		return potentialClientDao.getPotentialClient(id);
	}

	@Override
	public Pager<PotentialClient> queryPotentialClient(
		PotentialClient potentialClient,
		Pager<PotentialClient> pager){

		return potentialClientDao.queryPotentialClient(potentialClient,  pager);
	}

	@Override
	public <T> T get(String id) {
		return (T)getPotentialClient(id);
	}


}