package com.pm.dao;

import com.pm.domain.system.MarketSets;

public interface IMarketSetsDao {

	public int addMarketSets(MarketSets marketSets) ;

	public int updateMarketSets(MarketSets marketSets) ; 

	public MarketSets getMarketSets(String id) ;

}