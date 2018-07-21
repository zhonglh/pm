package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffCostDao;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffCostAnalysis;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.vo.ConditionStaffCost;
import com.pm.vo.UserPermit;


@Component
public class StaffCostDaoImpl extends BatisDao implements IStaffCostDao {



	@Override
	public int addStaffCost(StaffCost staffCost) {
		String sql = "StaffCostMapping.addStaffCost";
		return this.insert(sql, staffCost);
	}

	@Override
	public int updateStaffCost(StaffCost staffCost) {
		String sql = "StaffCostMapping.updateStaffCost";
		return this.update(sql, staffCost);
	}
	

	public int updateStaffCostSecurtyUnit(StaffCost staffCost) {
		String sql = "StaffCostMapping.updateStaffCostSecurtyUnit";
		return this.update(sql, staffCost);
	}
	
	public int updateStaffQustomerQuotes(StaffCost staffCost) {
		String sql = "StaffCostMapping.updateStaffQustomerQuotes";
		return this.update(sql, staffCost);
	}
	
	

	public void updateWorkinfLife() {
		String sql = "StaffCostMapping.updateWorkinfLife";
		this.update(sql);
	}
	

	public int updateStaffFirstquotes(StaffCost staffCost) {
		String sql = "StaffCostMapping.updateStaffFirstquotes";
		return this.update(sql, staffCost);
	}

	@Override
	public void deleteStaffCost(StaffCost staffCost) {
		String sql = "StaffCostMapping.deleteStaffCost";
		this.update(sql, staffCost);
	}

	

	@Override
	public StaffCost getStaffCost(String staff_id) {
		StaffCost staffCost = new StaffCost();
		staffCost.setStaff_id(staff_id);
		Map<String,StaffCost> map = new HashMap<String,StaffCost>();
		map.put(StaffCost.class.getSimpleName(), staffCost);
		String sql = "StaffCostMapping.getStaffCost";
		List<StaffCost> list = this.query(sql, StaffCost.class ,map);
		if(list == null || list.size() == 0) return null;
		return list.get(0);
	}
	

	@Override
	public StaffCost getStaffCostByName(String staff_name) {		
		StaffCost staffCost = new StaffCost();
		staffCost.setStaff_name(staff_name);		
		String sql = "StaffCostMapping.getStaffCostByName";
		List<StaffCost> list = this.query(sql, StaffCost.class ,staffCost);
		if(list == null || list.size() == 0) return null;
		return list.get(0);
		
	}
	

	@Override
	public boolean isExist(StaffCost staffCost){
		String sql = "StaffCostMapping.isExist";
		List<StaffCost> list = this.query(sql, StaffCost.class ,staffCost);
		return (list == null || list.isEmpty()) ? false : true;
	}
	

	@Override
	public List<StaffCost> getAllStaff(){
		String sql = "StaffCostMapping.getAllStaff";
		return  this.query(sql, StaffCost.class);
	}
	
	

	@Override
	public StaffCost getStaffCostNum(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit){

		String sql = "StaffCostMapping.getStaffCostNum";

		Map<String,Object>  map = new HashMap<String,Object>();
		if(staffCost != null) map.put(staffCost.getClass().getSimpleName(), staffCost);
		if(staffCostCondition != null) map.put(staffCostCondition.getClass().getSimpleName(), staffCostCondition);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);		
		List<StaffCost> list = this.query(sql, StaffCost.class ,map);
		return list.get(0);
	}
	
	

	@Override
	public List<StaffCost> getAllStaffBySearch(StaffCost staffCost) {		
		String sql = "StaffCostMapping.getAllStaffBySearch";
		return  this.query(sql, StaffCost.class,staffCost);
	}

	@Override
	public Pager<StaffCost> queryStaffCost(StaffCost staffCost,ConditionStaffCost staffCostCondition , 
			UserPermit userPermit, Pager<StaffCost> pager) {
		String sql = "StaffCostMapping.queryStaffCost";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StaffCost.class, staffCost,staffCostCondition,userPermit);
	}
	

	@Override
	public Pager<StaffCost> queryAllStaffCost(StaffCost staffCost,ConditionStaffCost staffCostCondition , 
			UserPermit userPermit, Pager<StaffCost> pager) {
		String sql = "StaffCostMapping.queryAllStaffCost";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StaffCost.class, staffCost,staffCostCondition,userPermit);
	}
	

	@Override
	public Pager<StaffCostAnalysis> queryProfitAnalysis(StaffCost staffCost,ConditionStaffCost staffCostCondition , 
			UserPermit userPermit,Pager<StaffCostAnalysis> pager){
		String sql = "StaffCostAnalysisMapping.queryProfitAnalysis";
		Pager<StaffCostAnalysis> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StaffCostAnalysis.class, staffCost,staffCostCondition,userPermit);
	
		/*sql = "StaffCostAnalysisMapping.queryProfitAnalysisTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(staffCost != null) map.put(staffCost.getClass().getSimpleName(), staffCost);
		if(userPermit != null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}*/
		return pager1;		
	}
	
	public Pager<StatisticsDetail> queryAnalysisDetails(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager){
		
		String sql = "StaffCostAnalysisMapping.queryProfitAnalysisDetailIncome";
		if(statistics.getX().equals("cost")){
			sql = "StaffCostAnalysisMapping.queryProfitAnalysisDetailCost";
		}
		
		return  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics ,userPermit);
	
	}

	
	

}
