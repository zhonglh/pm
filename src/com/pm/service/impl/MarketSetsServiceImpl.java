package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cache.CacheManager;
import com.common.beans.Pager;
import com.pm.dao.IMarketSetsDao;
import com.pm.domain.system.MarketSets;
import com.pm.service.IMarketSetsService;
import com.pm.vo.UserPermit;

@Component
public class MarketSetsServiceImpl implements  IMarketSetsService {

	@Autowired IMarketSetsDao marketSetsDao;
	@Override
	public int addMarketSets(MarketSets marketSets) {
		return marketSetsDao.addMarketSets(marketSets);
	}

	@Override
	public int updateMarketSets(MarketSets marketSets) {
		return marketSetsDao.updateMarketSets(marketSets);
	}


	@Override
	public MarketSets getMarketSets(String id) {
		return marketSetsDao.getMarketSets(id);
	}

	@Override
	public <T> T get(String id) {
		return (T)getMarketSets(id);
	}


}