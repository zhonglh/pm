package com.pm.service.impl;

import java.util.List;

import com.common.exceptions.PMException;
import com.common.utils.IDKit;
import com.pm.domain.business.*;
import com.pm.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IOtherStaffDao;
import com.pm.dao.IUserDao;
import com.pm.util.constant.EnumStaffType;
import com.pm.vo.UserPermit;

@Component
public class OtherStaffServiceImpl implements IOtherStaffService {

	@Autowired IUserDao userDao;
	@Autowired IOtherStaffDao otherStaffDao;


	@Autowired
	private IStaffRaiseRecordService staffRaiseRecordService;

	@Autowired
	private IStaffRewardPenaltyService staffRewardPenaltyService;

	@Autowired
	private IStaffAssessmentService staffAssessmentService;

	@Autowired
	private IStaffPositionsService staffPositionsService;


	@Autowired
	private IDeptStaffService deptStaffService;
	
	@Override
	public int addOtherStaff(OtherStaff otherStaff,
							 StaffAssessment[] staffAssessments,
							 StaffPositions[] staffPositionss,
							 StaffRaiseRecord[] staffRaiseRecords,
							 StaffRewardPenalty[] staffRewardPenaltys,
							 DeptStaff[] deptStaffs) {

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


		if(deptStaffs != null && deptStaffs.length >0){
			for(DeptStaff deptStaff : deptStaffs){
				deptStaff.setDept_staff_id(IDKit.getUUID());
				deptStaffService.addDeptStaff(deptStaff);
			}
		}

		return otherStaffDao.addOtherStaff(otherStaff);
	}

	@Override
	public int updateOtherStaff(OtherStaff otherStaff,
								StaffAssessment[] staffAssessments,
								StaffPositions[] staffPositionss,
								StaffRaiseRecord[] staffRaiseRecords,
								StaffRewardPenalty[] staffRewardPenaltys,
								DeptStaff[] deptStaffs) {




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


		if(deptStaffs != null && deptStaffs.length >0){
			for(DeptStaff deptStaff : deptStaffs){
				if(StringUtils.isEmpty(deptStaff.getDept_staff_id())) {
					deptStaff.setDept_staff_id(IDKit.getUUID());
					deptStaffService.addDeptStaff(deptStaff);
				}else {
					deptStaffService.updateDeptStaff(deptStaff);
				}
			}
		}

		userDao.updateUser(otherStaff.getStaff_name(), otherStaff.getDept_id(), otherStaff.getStaff_id(), EnumStaffType.AdminStaff.name());
		int size = otherStaffDao.updateOtherStaff(otherStaff);


		if(size == 0) {
			throw new PMException("111111", "操作错误，已有其他人和你同时人员信息，请重新操作！");
		}
		else {
			return size;
		}
	}

	@Override
	public void deleteOtherStaff(OtherStaff[] otherStaffs) {
		for(OtherStaff otherStaff : otherStaffs){	
				
				userDao.deleteUser(
						otherStaff.getStaff_id(),
						EnumStaffType.AdminStaff.name(),
						otherStaff.getDelete_userid(),
						otherStaff.getDelete_username()
				);
				
				otherStaffDao.deleteOtherStaff(otherStaff);
		}
	}

	@Override
	public OtherStaff getOtherStaff(String staff_id) {
		
		return otherStaffDao.getOtherStaff(staff_id);
	}


	@Override
	public List<OtherStaff> getOtherStaffByInsurance(OtherStaff otherStaff){

		return otherStaffDao.getOtherStaffByInsurance(otherStaff);
	}

	@Override
	public boolean isExist(OtherStaff otherStaff) {		
		return otherStaffDao.isExist(otherStaff);
	}

	@Override
	public Pager<OtherStaff> queryOtherStaff(OtherStaff otherStaff, UserPermit userPermit,Pager<OtherStaff> pager){
		return otherStaffDao.queryOtherStaff(otherStaff, userPermit, pager);
	}
	
	

	@Override
	public List<OtherStaff> queryOtherStaffByProjectSales(OtherStaff otherStaff,UserPermit userPermit){
		return otherStaffDao.queryOtherStaffByProjectSales(otherStaff,userPermit);
	}

}
