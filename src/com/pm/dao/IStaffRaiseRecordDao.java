package com.pm.dao;

import com.pm.domain.business.StaffRaiseRecord;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IStaffRaiseRecordDao {
	

	public int addRaiseRecord(StaffRaiseRecord raiserecord) ;

	public int updateRaiseRecord(StaffRaiseRecord raiserecord) ; 

	public void deleteRaiseRecord(StaffRaiseRecord raiserecord) ;

	public StaffRaiseRecord getRaiseRecord(String id) ;

	public Pager<StaffRaiseRecord> queryRaiseRecord(StaffRaiseRecord raiserecord,  Pager<StaffRaiseRecord> pager);

}