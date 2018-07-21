package com.pm.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IStaffChildsDao;
import com.pm.domain.business.StaffChilds;

@Component
public class StaffChildsDaoImpl extends BatisDao implements IStaffChildsDao  {


	@Override
	public void deleteStaffChilds(StaffChilds staffChilds) {
		String sql = "StaffChildsMapping.deleteStaffChilds";
		this.delete(sql, staffChilds);
	}


	@Override
	public void processStaffChilds(String staff_id) {

		String sql = "StaffChildsMapping.processStaffChilds"; 
		Map<String,String> map = new HashMap<String,String>();
		map.put("pro_staff_id", staff_id);
		this.execProc(sql, map);
	}



}