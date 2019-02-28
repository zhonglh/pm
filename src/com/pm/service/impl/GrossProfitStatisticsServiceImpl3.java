package com.pm.service.impl;

import com.pm.dao.IGrossProfitStatisticsDao;
import com.pm.service.IGrossProfitStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("grossProfitStatisticsServiceImpl3")
public class GrossProfitStatisticsServiceImpl3 extends
		GrossProfitStatisticsServiceImpl implements
		IGrossProfitStatisticsService {


	@Autowired
	@Qualifier("grossProfitStatisticsDaoImpl3")
	IGrossProfitStatisticsDao grossProfitStatisticsDao;

	@Override
	public String toString() {
		return String.format("GrossProfitStatisticsServiceImpl3 [333333333333]");
	}

	@Override
	public IGrossProfitStatisticsDao getGrossProfitStatisticsDao() {
		return grossProfitStatisticsDao;
	}


}
