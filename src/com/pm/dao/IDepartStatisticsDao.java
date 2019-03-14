package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;

public interface IDepartStatisticsDao {


	/**
	 * 项目含税收入 结算单
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryMonthlyStatement20(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);
	public Pager<DepartStatisticsItem> queryMonthlyStatement22(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);

	/**
	 * 项目含税收入 回款
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryReceivedPayments10(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);
	public Pager<DepartStatisticsItem> queryReceivedPayments12(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);
	
	/**
	 * 项目人员成本
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryProjectStaffCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目报销成本
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryReimburseCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目报销明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryReimburseCostDetails(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 项目付款
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryProjectExpends40(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);
	public Pager<DepartStatisticsItem> queryProjectExpends41(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);
	public Pager<DepartStatisticsItem> queryProjectExpends42(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);

	/**
	 * 销售费用
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> querySalseCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	
	/**
	 * 销售费用明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> querySalseCostsDetail(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	

	/**
	 * 部门管理费用
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<DepartStatisticsItem> queryDepartCosts(Statistics statistics,UserPermit userPermit,Pager<DepartStatisticsItem> pager);	
	

	/**
	 * 部门管理费用明细
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
	public Pager<StatisticsDetail> queryOtherStaffCostDetail(Statistics statistics,UserPermit userPermit,Pager<StatisticsDetail> pager) ;

}
