package com.cache;

import com.pm.domain.system.MarketSets;

public class CacheManager {
	
	
	public static double getTaxRate(){
		return Cache.taxRate;
	}
	

	public static double setTaxRate(double taxRate){
		return Cache.taxRate = taxRate;
	}
	
	
	public static MarketSets getMarketSets(){
		return Cache.marketSets;
	}
	
	
	public static MarketSets setMarketSets(MarketSets marketSets){
		return Cache.marketSets = marketSets;
	}
	
	

}
