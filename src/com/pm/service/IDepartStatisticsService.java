package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.DepartCosts;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;

/**
 * 部门费用统计
 * @author zhonglihong
 * @date 2016年6月2日 下午8:50:23
 */
public interface IDepartStatisticsService {



	
	/**
	 * 项目含税收入统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryReceivedPayments(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目人员成本统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryProjectStaffCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目报销成本统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryReimburseCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目报销明细统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryReimburseCostDetails(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目付款统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryProjectExpends(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 销售费用统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> querySalseCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 销售费用明细统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> querySalseCostsDetail(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	

	/**
	 * 部门管理费用统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryDepartCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	

	/**
	 * 部门管理费用明细统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryDepartCostsDetail(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	
	/**
	 * 成本明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryCostsDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);
	
	/**
	 * 部门费用明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryDepartDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);


	/**
	 * 总部人员成本统计
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryOtherStaffCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);



	/**
	 * 总部人员成本明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryOtherStaffCostDetail(Statistics statistics,UserPermit userPermit,Pager<StatisticsDetail> pager);
}
