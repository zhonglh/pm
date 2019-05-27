package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IDicDataDao;
import com.pm.domain.business.DicData;
import com.pm.domain.business.DicType;
import com.pm.vo.UserPermit;

@Component
public class DicDataDaoImpl extends BatisDao implements IDicDataDao  {
	
	


	@Override
	public int getMaxDicDataByType(DicData dicdata){

		String sql = "DicDataMapping.getMaxDicDataByType"; 
		return this.query4Int(sql, dicdata);
	}
	@Override
	public DicData getDicDataByPre(DicData dicdata) {
		String sql = "DicDataMapping.getDicDataByPre"; 
		List<DicData> list = this.query(sql, DicData.class, dicdata);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}
	@Override
	public DicData getDicDataByNext(DicData dicdata) {

		String sql = "DicDataMapping.getDicDataByNext"; 
		List<DicData> list = this.query(sql, DicData.class, dicdata);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}
	

	@Override
	public List<DicType> getAllDicType(){
		String sql = "DicDataMapping.getAllDicType"; 
		return this.query(sql, DicType.class); 
	}
	

	@Override
	public DicType getDicType(String id){
		String sql = "DicDataMapping.getDicType"; 
		DicType dicType = new DicType();
		dicType.setId(id);
		List<DicType> list =  this.query(sql, DicType.class,dicType); 
		if(list != null && list.size() >0) return list.get(0);
		else return null;
	}

	@Override
	public int addDicData(DicData dicdata) {
		String sql = "DicDataMapping.addDicData";
		return this.insert(sql, dicdata);
	}

	@Override
	public int updateDicData(DicData dicdata) {
		String sql = "DicDataMapping.updateDicData";
		return this.update(sql, dicdata);
	}
	

	@Override
	public int updateDicDataSort(DicData dicdata) {
		String sql = "DicDataMapping.updateDicDataSort";
		return this.update(sql, dicdata);		
	}

	@Override
	public void deleteDicData(DicData dicdata) {
		String sql = "DicDataMapping.deleteDicData";
		this.update(sql, dicdata);
	}

	@Override
	public void recoverDicData(DicData dicdata) {
		String sql = "DicDataMapping.recoverDicData";
		this.update(sql, dicdata);
	}

	@Override
	public DicData getDicData(String id) {

		String sql = "DicDataMapping.getDicData"; 
		DicData dicdata = new DicData(); 
		dicdata.setId(id); 
		List<DicData> list = this.query(sql, DicData.class, dicdata); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}
	

	@Override
	public List<DicData> getDicDataByType(DicData dicdata){
		String sql = "DicDataMapping.getDicDataByType"; 
		return this.query(sql, DicData.class, dicdata); 
	}


	@Override
	public List<DicData> getAllDicDataByType(DicData dicdata){

		String sql = "DicDataMapping.getAllDicDataByType";
		return this.query(sql, DicData.class, dicdata);
	}

	@Override
	public Pager<DicData> queryDicData(
		DicData dicdata,
		Pager<DicData> pager){
		String sql = "DicDataMapping.queryDicData"; 
		Pager<DicData> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,DicData.class, dicdata); 
		return pager1;
	}


}