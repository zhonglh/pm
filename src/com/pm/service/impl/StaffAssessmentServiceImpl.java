package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.StaffAssessment;
import com.pm.dao.IStaffAssessmentDao;
import com.pm.service.IStaffAssessmentService;
import com.pm.vo.UserPermit;
import com.thoughtworks.xstream.core.ReferenceByIdMarshaller.IDGenerator;
import com.common.beans.Pager;
import com.common.utils.IDKit;

@Component
public class StaffAssessmentServiceImpl implements  IStaffAssessmentService {

	@Autowired IStaffAssessmentDao staffAssessmentDao;
	@Override
	public int addStaffAssessment(StaffAssessment staffAssessment) {
		staffAssessment.setId(IDKit.generateId());
		return staffAssessmentDao.addStaffAssessment(staffAssessment);
	}

	@Override
	public int updateStaffAssessment(StaffAssessment staffAssessment) {
		return staffAssessmentDao.updateStaffAssessment(staffAssessment);
	}

	@Override
	public void deleteStaffAssessment(StaffAssessment[] staffAssessments) {
		for(StaffAssessment staffAssessment : staffAssessments){
			staffAssessmentDao.deleteStaffAssessment(staffAssessment);
		}
	}


	@Override
	public StaffAssessment getStaffAssessment(String id) {
		return staffAssessmentDao.getStaffAssessment(id);
	}

	@Override
	public Pager<StaffAssessment> queryStaffAssessment(
		StaffAssessment staffAssessment,
		Pager<StaffAssessment> pager){

		return staffAssessmentDao.queryStaffAssessment(staffAssessment,  pager);
	}
	
	@Override
	public <T> T get(String id) {
		return (T)getStaffAssessment(id);
	}


}