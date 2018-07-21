package com.pm.service;

import com.pm.domain.system.MarketSets;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IMarketSetsService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_MARKETSETS)
	public int addMarketSets(MarketSets marketSets) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_MARKETSETS)
	public int updateMarketSets(MarketSets marketSets) ; 

	public MarketSets getMarketSets(String id) ;	

}