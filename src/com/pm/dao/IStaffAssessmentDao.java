package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.StaffAssessment;

public interface IStaffAssessmentDao {

	public int addStaffAssessment(StaffAssessment staffAssessment) ;

	public int updateStaffAssessment(StaffAssessment staffAssessment) ; 

	public void deleteStaffAssessment(StaffAssessment staffAssessment) ;
	
	public StaffAssessment getStaffAssessment(String id) ;	

	public Pager<StaffAssessment> queryStaffAssessment(StaffAssessment staffAssessment,  Pager<StaffAssessment> pager);

}