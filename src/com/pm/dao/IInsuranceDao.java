package com.pm.dao;

import com.pm.domain.business.Insurance;
import com.pm.domain.business.MonthlyStatement;
import com.pm.vo.UserPermit;

import java.util.List;

import com.common.beans.Pager;

public interface IInsuranceDao {

	public int addInsurance(Insurance insurance) ;

	public int updateInsurance(Insurance insurance) ; 

	public void deleteInsurance(Insurance insurance) ;

	public void verifyInsurance(Insurance insurance) ;	

	public void unVerifyInsurance(Insurance insurance) ;

	public Insurance getInsurance(String id) ;


	public int getNotCheckNumByWorkAttendance(Insurance insurance);
	

	public List<Insurance> queryInsuranceByWorkAttendance(Insurance insurance);
	
	

	public Insurance queryInsuranceSum(Insurance insurance,  UserPermit userPermit) ;

	public Pager<Insurance> queryInsurance(Insurance insurance,  UserPermit userPermit,Pager<Insurance> pager);

}