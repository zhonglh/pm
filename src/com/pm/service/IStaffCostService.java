package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.StaffAssessment;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffCostAnalysis;
import com.pm.domain.business.StaffPositions;
import com.pm.domain.business.StaffRaiseRecord;
import com.pm.domain.business.StaffRewardPenalty;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.ConditionStaffCost;
import com.pm.vo.UserPermit;

public interface IStaffCostService {
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_STAFF_COST)
	public int addStaffCost(StaffCost staffCost,
			StaffAssessment[] staffAssessments,
			StaffPositions[] staffPositionss,
			StaffRaiseRecord[] staffRaiseRecords,
			StaffRewardPenalty[] staffRewardPenaltys) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_STAFF_COST)
	public int updateStaffCost(StaffCost staffCost,
			StaffAssessment[] staffAssessments,
			StaffPositions[] staffPositionss,
			StaffRaiseRecord[] staffRaiseRecords,
			StaffRewardPenalty[] staffRewardPenaltys) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_STAFF_COST)
	public void deleteStaffCost(StaffCost[] staffCosts) ;
	
	//自动增加工作年限
	public void updateWorkinfLife() ;
	
	
	public StaffCost getStaffCost(String staff_id) ;
	public StaffCost getStaffCostByName(String staff_name) ;
	
	
	public boolean isExist(StaffCost staffCost);
	
	/**
	 * 查询所有人员， 包括人员成本和行政人员
	 * @return
	 */
	public List<StaffCost> getAllStaff();
	
	
	public StaffCost getStaffCostNum(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit);

	public Pager<StaffCost> queryStaffCost(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit,Pager<StaffCost> pager);

	
	/**
	 * 查询人员
	 * @param staffCost
	 * @return
	 */
	public List<StaffCost> getAllStaffBySearch(StaffCost staffCost);
	
	/**
	 *  查询所有人员， 包括人员成本和行政人员
	 * @param staffCost
	 * @param staffCostCondition
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StaffCost> queryAllStaffCost(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit,Pager<StaffCost> pager);
	
	
	/**
	 * 人员利润报表
	 * @param staffCost
	 * @param staffCostCondition
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StaffCostAnalysis> queryProfitAnalysis(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit,Pager<StaffCostAnalysis> pager);
	

	/**
	 * 人员利润分析明细
	 * @param statistics
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StatisticsDetail> queryAnalysisDetails(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);
	
	
}
