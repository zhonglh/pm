
package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IDepartStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.service.IDepartStatisticsService;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;

/**
 * @author zhonglihong
 * @date 2016年6月9日 上午11:25:25
 */
@Component
public class DepartStatisticsServiceImpl implements IDepartStatisticsService {
	
	@Autowired
	private IDepartStatisticsDao departStatisticsDao;



	@Override
	public Pager<DepartStatisticsItem> queryMonthlyStatement20(Statistics statistics, UserPermit userPermit, Pager<DepartStatisticsItem> pager){
		return departStatisticsDao.queryMonthlyStatement20(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryMonthlyStatement22(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager){
		return departStatisticsDao.queryMonthlyStatement22(statistics, userPermit, pager);
	}


	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments10(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReceivedPayments10(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments12(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReceivedPayments12(statistics, userPermit, pager);
	}
	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments13(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReceivedPayments13(statistics, userPermit, pager);
	}
	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments14(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReceivedPayments14(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryProjectStaffCosts(Statistics statistics, UserPermit userPermit,	Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryProjectStaffCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReimburseCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReimburseCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReimburseCostDetails(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReimburseCostDetails(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends40(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryProjectExpends40(statistics, userPermit, pager);
	}
	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends41(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryProjectExpends41(statistics, userPermit, pager);
	}
	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends42(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryProjectExpends42(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> querySalseCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.querySalseCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> querySalseCostsDetail(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.querySalseCostsDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryDepartCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryDepartCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryDepartCostsDetail(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryDepartCostsDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryCostsDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager) {
		return departStatisticsDao.queryCostsDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryDepartDetail(Statistics statistics, UserPermit userPermit, Pager<StatisticsDetail> pager) {
		return departStatisticsDao.queryDepartDetail(statistics, userPermit, pager);
	}


	@Override
	public Pager<DepartStatisticsItem> queryOtherStaffCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryOtherStaffCosts(statistics, userPermit, pager);
	}



	@Override
	public Pager<StatisticsDetail> queryOtherStaffCostDetail(Statistics statistics,UserPermit userPermit,Pager<StatisticsDetail> pager){
		return departStatisticsDao.queryOtherStaffCostDetail(statistics, userPermit, pager);
	}




	@Override
	public Pager<DepartStatisticsItem> querySalesStaffCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.querySalesStaffCosts(statistics, userPermit, pager);
	}



	@Override
	public Pager<StatisticsDetail> querySalesStaffCostDetail(Statistics statistics,UserPermit userPermit,Pager<StatisticsDetail> pager){
		return departStatisticsDao.querySalesStaffCostDetail(statistics, userPermit, pager);
	}




	@Override
	public Pager<DepartStatisticsItem> queryManageStaffCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryManageStaffCosts(statistics, userPermit, pager);
	}



	@Override
	public Pager<StatisticsDetail> queryManageStaffCostDetail(Statistics statistics,UserPermit userPermit,Pager<StatisticsDetail> pager){
		return departStatisticsDao.queryManageStaffCostDetail(statistics, userPermit, pager);
	}
	
}
