package com.pm.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.PMException;
import com.pm.dao.IProjectDao;
import com.pm.dao.IStaffCostDao;
import com.pm.domain.business.StaffAssessment;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffCostAnalysis;
import com.pm.domain.business.StaffPositions;
import com.pm.domain.business.StaffRaiseRecord;
import com.pm.domain.business.StaffRewardPenalty;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.service.IStaffAssessmentService;
import com.pm.service.IStaffCostService;
import com.pm.service.IStaffPositionsService;
import com.pm.service.IStaffRaiseRecordService;
import com.pm.service.IStaffRewardPenaltyService;
import com.pm.vo.ConditionStaffCost;
import com.pm.vo.UserPermit;

@Component
public class StaffCostServiceImpl implements IStaffCostService {

	@Autowired  private IStaffCostDao staffCostDao;
	
	@Autowired  private IProjectDao projectDao;
	

	@Autowired
	private IStaffRaiseRecordService staffRaiseRecordService;
	
	@Autowired
	private IStaffRewardPenaltyService staffRewardPenaltyService;
	
	@Autowired
	private IStaffAssessmentService  staffAssessmentService;
	
	@Autowired
	private IStaffPositionsService staffPositionsService;
	
	
	@Override
	public int addStaffCost(StaffCost staffCost,
			StaffAssessment[] staffAssessments,
			StaffPositions[] staffPositionss,
			StaffRaiseRecord[] staffRaiseRecords,
			StaffRewardPenalty[] staffRewardPenaltys) {
		
		
		if(staffAssessments != null && staffAssessments.length >0){
			for(StaffAssessment staffAssessment : staffAssessments){
				staffAssessmentService.addStaffAssessment(staffAssessment);
			}
		}
		
		if(staffPositionss != null && staffPositionss.length >0){
			for(StaffPositions staffPositions : staffPositionss){
				staffPositionsService.addStaffPositions(staffPositions);
			}
		}
		
		if(staffRaiseRecords != null && staffRaiseRecords.length >0){
			for(StaffRaiseRecord staffRaiseRecord : staffRaiseRecords){
				staffRaiseRecordService.addRaiseRecord(staffRaiseRecord);
			}
		}
		
		if(staffRewardPenaltys != null && staffRewardPenaltys.length >0){
			for(StaffRewardPenalty staffRewardPenalty : staffRewardPenaltys){
				staffRewardPenaltyService.addStaffRewardPenalty(staffRewardPenalty);
			}
		}
		
		return staffCostDao.addStaffCost(staffCost);
	}
	

	@Override
	public int updateStaffCost(StaffCost staffCost,
			StaffAssessment[] staffAssessments,
			StaffPositions[] staffPositionss,
			StaffRaiseRecord[] staffRaiseRecords,
			StaffRewardPenalty[] staffRewardPenaltys) {
		

		if(staffAssessments != null && staffAssessments.length >0){
			for(StaffAssessment staffAssessment : staffAssessments){
				if(StringUtils.isEmpty(staffAssessment.getId())) {
					staffAssessmentService.addStaffAssessment(staffAssessment);
				}else {
					staffAssessmentService.updateStaffAssessment(staffAssessment);
				}
			}
		}
		
		if(staffPositionss != null && staffPositionss.length >0){
			for(StaffPositions staffPositions : staffPositionss){
				if(StringUtils.isEmpty(staffPositions.getId())) {
					staffPositionsService.addStaffPositions(staffPositions);
				}else {
					staffPositionsService.updateStaffPositions(staffPositions);
				}
			}
		}
		
		if(staffRaiseRecords != null && staffRaiseRecords.length >0){
			for(StaffRaiseRecord staffRaiseRecord : staffRaiseRecords){
				if(StringUtils.isEmpty(staffRaiseRecord.getId())) {
					staffRaiseRecordService.addRaiseRecord(staffRaiseRecord);
				}else {
					staffRaiseRecordService.updateRaiseRecord(staffRaiseRecord);
				}
			}
		}
		
		if(staffRewardPenaltys != null && staffRewardPenaltys.length >0){
			for(StaffRewardPenalty staffRewardPenalty : staffRewardPenaltys){
				if(StringUtils.isEmpty(staffRewardPenalty.getId())){
					staffRewardPenaltyService.addStaffRewardPenalty(staffRewardPenalty);
				}else {
					staffRewardPenaltyService.updateStaffRewardPenalty(staffRewardPenalty);
				}
			}
		}
		
		
		int size = staffCostDao.updateStaffCost(staffCost);
		if(size == 0) {
			throw new PMException("111111", "操作错误，已有其他人和你同时修改人员成本信息，请重新操作！");
		}
		else {
			return size;
		}
	}

	@Override
	public int updateStaffCostAdditionalDeduction(StaffCost staffCost) {
		int size = staffCostDao.updateStaffCostAdditionalDeduction(staffCost);
		if(size == 0) {
			throw new PMException("111111", "操作错误，已有其他人和你同时修改人员成本信息，请重新操作！");
		}
		else {
			return size;
		}
	}

	@Override
	public void deleteStaffCost(StaffCost[] staffCosts) {
		for(StaffCost staffCost : staffCosts){			
			staffCostDao.deleteStaffCost(staffCost);			
			projectDao.deleteProjectStaffByStaff(staffCost);
		}
		
	}
	
	

	@Override
	public void autoUpdateWorkinfLife() {
		staffCostDao.autoUpdateWorkinfLife();
	}

	@Override
	public void autoUpdateSalary() {
		staffCostDao.autoUpdateSalary();
	}

	@Override
	public StaffCost getStaffCost(String staff_id) {
		return staffCostDao.getStaffCost(staff_id);
	}
	

	@Override
	public StaffCost getStaffCostByName(String staff_name){
		if(staff_name == null || staff_name.isEmpty()) {
			return null;
		}
		return staffCostDao.getStaffCostByName(staff_name);
	}
	


	@Override
	public boolean isExist(StaffCost staffCost){
		return staffCostDao.isExist(staffCost);
	}
	

	@Override
	public List<StaffCost> getAllStaff(){
		return staffCostDao.getAllStaff();
		
	}
	

	@Override
	public StaffCost getStaffCostNum(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit){
		return staffCostDao.getStaffCostNum(staffCost, staffCostCondition, userPermit);
	}
	

	@Override
	public Pager<StaffCost> queryStaffCost(StaffCost staffCost,ConditionStaffCost staffCostCondition ,
			UserPermit userPermit, Pager<StaffCost> pager) {
		return staffCostDao.queryStaffCost(staffCost, staffCostCondition,userPermit, pager);
	}	
	

	@Override
	public List<StaffCost> getAllStaffBySearch(StaffCost staffCost){
		return staffCostDao.getAllStaffBySearch(staffCost);
	}
	

	@Override
	public Pager<StaffCost> queryAllStaffCost(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit,Pager<StaffCost> pager){
		return staffCostDao.queryAllStaffCost(staffCost, staffCostCondition,userPermit, pager);
	}
	
	
	@Override
	public Pager<StaffCostAnalysis> queryProfitAnalysis(StaffCost staffCost, ConditionStaffCost staffCostCondition ,UserPermit userPermit,Pager<StaffCostAnalysis> pager){
		return staffCostDao.queryProfitAnalysis(staffCost, staffCostCondition,userPermit, pager);
	}
	

	
	@Override
	public Pager<StatisticsDetail> queryAnalysisDetails(Statistics statistics, UserPermit userPermit, Pager<StatisticsDetail> pager){
		return staffCostDao.queryAnalysisDetails(statistics, userPermit, pager);
	}
	
	
	

}
