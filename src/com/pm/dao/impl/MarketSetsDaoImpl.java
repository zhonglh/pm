package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IMarketSetsDao;
import com.pm.domain.system.MarketSets;

@Component
public class MarketSetsDaoImpl extends BatisDao implements IMarketSetsDao  {

	@Override
	public int addMarketSets(MarketSets marketSets) {
		String sql = "MarketSetsMapping.addMarketSets";
		return this.insert(sql, marketSets);
	}

	@Override
	public int updateMarketSets(MarketSets marketSets) {
		String sql = "MarketSetsMapping.updateMarketSets";
		return this.update(sql, marketSets);
	}
	
	@Override
	public MarketSets getMarketSets(String id) {

		String sql = "MarketSetsMapping.getMarketSets"; 
		MarketSets marketSets = new MarketSets(); 
		marketSets.setId(id); 
		List<MarketSets> list = this.query(sql, MarketSets.class, marketSets); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}


}