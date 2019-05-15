package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IInsuranceDao;
import com.pm.domain.business.Insurance;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class InsuranceDaoImpl extends BatisDao implements IInsuranceDao  {

	@Override
	public int addInsurance(Insurance insurance) {
		String sql = "InsuranceMapping.addInsurance";
		return this.insert(sql, insurance);
	}

	@Override
	public int updateInsurance(Insurance insurance) {
		String sql = "InsuranceMapping.updateInsurance";
		return this.update(sql, insurance);
	}

	@Override
	public void deleteInsurance(Insurance insurance) {
		String sql = "InsuranceMapping.deleteInsurance";
		this.delete(sql, insurance);
	}

	@Override
	public void verifyInsurance(Insurance insurance) {
		String sql = "InsuranceMapping.verifyInsurance";
		this.update(sql, insurance);
	}

	@Override
	public void unVerifyInsurance(Insurance insurance) {
		String sql = "InsuranceMapping.unVerifyInsurance";
		this.update(sql, insurance);
	}

	@Override
	public Insurance getInsurance(String id) {

		String sql = "InsuranceMapping.getInsurance"; 
		Insurance insurance = new Insurance(); 
		insurance.setId(id); 
		List<Insurance> list = this.query(sql, Insurance.class, insurance); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}



	@Override
	public int getNotCheckNumByWorkAttendance(Insurance insurance){
		String sql = "InsuranceMapping.getNotCheckNumByWorkAttendance";
		return this.query4Int(sql , insurance);
	}
	

	@Override
	public List<Insurance> queryInsuranceByWorkAttendance(Insurance insurance){
		String sql = "InsuranceMapping.queryInsuranceByWorkAttendance"; 
		return this.query(sql, Insurance.class, insurance); 
	}



	@Override
	public Insurance queryInsuranceSum(Insurance insurance,  UserPermit userPermit) {
		String sql = "InsuranceMapping.queryInsuranceSum"; 
		Map<String , Object>  map = new HashMap<String , Object>();
		if(insurance !=null) map.put(insurance.getClass().getSimpleName(), insurance);
		if(userPermit !=null) map.put(userPermit.getClass().getSimpleName(), userPermit);
		return this.query(sql, Insurance.class, map).get(0); 
	}
	

	@Override
	public Pager<Insurance> queryInsurance(
		Insurance insurance,
		UserPermit userPermit,
		Pager<Insurance> pager){

		String sql = "InsuranceMapping.queryInsurance"; 
		Pager<Insurance> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,Insurance.class, insurance,userPermit); 
		
		return pager1;
	}


}