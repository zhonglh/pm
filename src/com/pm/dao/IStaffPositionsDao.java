package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.StaffPositions;

public interface IStaffPositionsDao {

	public int addStaffPositions(StaffPositions staffPositions) ;

	public int updateStaffPositions(StaffPositions staffPositions) ; 

	public void deleteStaffPositions(StaffPositions staffPositions) ;


	public StaffPositions getStaffPositions(String id) ;	

	public Pager<StaffPositions> queryStaffPositions(StaffPositions staffPositions, Pager<StaffPositions> pager);

}