package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IPotentialClientFollowupDao;
import com.pm.domain.business.PotentialClientFollowup;
import com.pm.service.IPotentialClientFollowupService;

@Component
public class PotentialClientFollowupServiceImpl implements  IPotentialClientFollowupService {

	@Autowired IPotentialClientFollowupDao potentialClientFollowupDao;
	@Override
	public int addPotentialClientFollowup(PotentialClientFollowup[] potentialClientFollowups) {
		for(PotentialClientFollowup potentialClientFollowup : potentialClientFollowups){
			potentialClientFollowup.setId(IDKit.generateId());
			potentialClientFollowupDao.addPotentialClientFollowup(potentialClientFollowup);
		}
		return 0;
	}

	@Override
	public int updatePotentialClientFollowup(PotentialClientFollowup[] potentialClientFollowups) {
		for(PotentialClientFollowup potentialClientFollowup : potentialClientFollowups){
			if(potentialClientFollowup.getId() == null || potentialClientFollowup.getId().isEmpty()){
				potentialClientFollowup.setId(IDKit.generateId());
				potentialClientFollowupDao.addPotentialClientFollowup(potentialClientFollowup);
			}else {				
				potentialClientFollowupDao.updatePotentialClientFollowup(potentialClientFollowup);
			}
		}
		return 0;
	}

	@Override
	public void deletePotentialClientFollowup(PotentialClientFollowup[] potentialClientFollowups) {
		for(PotentialClientFollowup potentialClientFollowup : potentialClientFollowups){
			potentialClientFollowupDao.deletePotentialClientFollowup(potentialClientFollowup);
		}
	}

	@Override
	public PotentialClientFollowup getPotentialClientFollowup(String id) {
		return potentialClientFollowupDao.getPotentialClientFollowup(id);
	}
	

	public List<PotentialClientFollowup> getPotentialClientFollowupByClient(String potential_client_id) {
		return potentialClientFollowupDao.getPotentialClientFollowupByClient(potential_client_id);
	}

	@Override
	public Pager<PotentialClientFollowup> queryPotentialClientFollowup(
		PotentialClientFollowup potentialClientFollowup,
		Pager<PotentialClientFollowup> pager){

		return potentialClientFollowupDao.queryPotentialClientFollowup(potentialClientFollowup,  pager);
	}

	@Override
	public <T> T get(String id) {
		return (T)getPotentialClientFollowup(id);
	}


}