package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffCostAnalysis;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.vo.ConditionStaffCost;
import com.pm.vo.UserPermit;

public interface IStaffCostDao {

	
	
	public int addStaffCost(StaffCost staffCost) ;
	

	
	public int updateStaffCost(StaffCost staffCost) ;

	/**
	 * 修改社保缴纳单位
	 * @param staffCost
	 * @return
	 */
	public int updateStaffCostSecurtyUnit(StaffCost staffCost) ;

	/**
	 * 修改专项附件扣除
	 * @param staffCost
	 * @return
	 */
	public int updateStaffCostAdditionalDeduction(StaffCost staffCost) ;

	

	public void autoUpdateWorkinfLife() ;
	public void autoUpdateSalary();
	
	
	public int updateStaffFirstquotes(StaffCost staffCost) ; 
	
	public int updateStaffQustomerQuotes(StaffCost staffCost) ; 
	
	

	
	public void deleteStaffCost(StaffCost staffCost) ;
	
	
	public StaffCost getStaffCost(String staff_id) ;
	public StaffCost getStaffCostByName(String staff_name) ;
	

	public boolean isExist(StaffCost staffCost);
	

	public List<StaffCost> getAllStaff();
	

	public StaffCost getStaffCostNum(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit);
	

	public List<StaffCost> getAllStaffBySearch(StaffCost staffCost) ;
	

	public Pager<StaffCost> queryStaffCost(StaffCost staffCost,ConditionStaffCost staffCostCondition , UserPermit userPermit,Pager<StaffCost> pager);

	public Pager<StaffCost> queryAllStaffCost(StaffCost staffCost,ConditionStaffCost staffCostCondition , UserPermit userPermit,Pager<StaffCost> pager);

	public Pager<StaffCostAnalysis> queryProfitAnalysis(StaffCost staffCost,ConditionStaffCost staffCostCondition , UserPermit userPermit,Pager<StaffCostAnalysis> pager);

	
	public Pager<StatisticsDetail> queryAnalysisDetails(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager);
	
}
