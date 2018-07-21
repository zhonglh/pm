package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pm.dao.IGrossProfitStatisticsDao;
import com.pm.service.IGrossProfitStatisticsService;


@Service("grossProfitStatisticsServiceImpl2")
public class GrossProfitStatisticsServiceImpl2 extends
		GrossProfitStatisticsServiceImpl implements
		IGrossProfitStatisticsService {


	@Autowired
	@Qualifier("grossProfitStatisticsDaoImpl2") 
	IGrossProfitStatisticsDao grossProfitStatisticsDao;

	@Override
	public String toString() {
		return String.format("GrossProfitStatisticsServiceImpl2 [22222222222]");
	}

	public IGrossProfitStatisticsDao getGrossProfitStatisticsDao() {
		return grossProfitStatisticsDao;
	}


}
