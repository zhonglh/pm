package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.vo.UserPermit;

public interface IStatisticsDetailService {
	
	
	/**
	 * 毛利润统计明细(按收款记录)
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryGrossProfit1Detail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);
	

	/**
	 * 毛利润统计明细(按月度结算单记录)
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryGrossProfit2Detail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);	
	

	/**
	 * 合同额统计明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> querySalesDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);
	

	/**
	 * 应收款统计明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryReceivablesDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);

}
