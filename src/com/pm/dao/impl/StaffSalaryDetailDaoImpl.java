package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IItemDefineDao;
import com.pm.dao.IStaffSalaryDetailDao;
import com.pm.domain.business.StaffSalaryDetail;
import com.pm.domain.system.ItemDefine;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumEnable;

@Component
public class StaffSalaryDetailDaoImpl extends BatisDao implements IStaffSalaryDetailDao  {
	
	

	@Autowired IItemDefineDao itemDefineDao;
	

	public List<StaffSalaryDetail> getStaffSalaryDetail(StaffSalaryDetail staffSalaryDetail) {
		String sql = "StaffSalaryDetailMapping.queryStaffSalaryDetail";
		return this.query(sql, StaffSalaryDetail.class, staffSalaryDetail);
	}
	
	
	

	public List<StaffSalaryDetail> queryVirtualStaffSalaryDetail(String item_id , String computational_formula){
		Map<String, String> map = new HashMap<String, String>();
		map.put("item_id", item_id);
		map.put("computational_formula", computational_formula);
		String sql = "StaffSalaryDetailMapping.queryVirtualStaffSalaryDetail";
		return this.query(sql, StaffSalaryDetail.class, map);
		
	}

	public void doProcessStaffSalaryDetailByStaff(String staff_id) {

		
		Map<String,String> map = new HashMap<String,String>();
		if(staff_id != null && staff_id.length() >0) map.put("staff_id", staff_id) ;
		
		ItemDefine itemDefine = new ItemDefine();
		itemDefine.setEnable_state(EnumEnable.Enable.getCode());
		Pager<ItemDefine> pager = itemDefineDao.queryItemDefine(itemDefine, PubMethod.getPagerByAll(ItemDefine.class));

		if(pager.getResultList() != null && pager.getResultList().size() > 0){
			StringBuilder sb = new StringBuilder("");
			for(ItemDefine temp : pager.getResultList()){
				sb = sb.append(" when tid.id = '"+temp.getId()+"' then "+temp.getComputational_formula());
			}
			map.put("case_statement", sb.toString()) ;
		}
		

		deleteStaffSalaryDetail(map);
		
		if(pager.getResultList() != null && pager.getResultList().size() > 0){			
			addStaffSalaryDetail(map);
		}
		
	
		
	}
	


	public void doProcessStaffSalaryDetailByItem(String item_id){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("item_id", item_id);
		
		ItemDefine itemDefine = itemDefineDao.getItemDefine(item_id);

		if(itemDefine != null && EnumEnable.Enable.getCode().equals(itemDefine.getEnable_state())){
			StringBuilder sb = new StringBuilder("");
			sb = sb.append(" when tid.id = '"+itemDefine.getId()+"' then "+itemDefine.getComputational_formula());
			map.put("case_statement", sb.toString()) ;
		}		

		deleteStaffSalaryDetail(map);

		if(itemDefine != null &&  EnumEnable.Enable.getCode().equals(itemDefine.getEnable_state())){		
			addStaffSalaryDetail(map);
		}
		
	}
	
	

	private void addStaffSalaryDetail(Map<String,String> map){
		try{
		String sql = "StaffSalaryDetailMapping.addStaffSalaryDetail";
		this.insert(sql,map);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void deleteStaffSalaryDetail(Map<String,String> map) {
		try{
			String sql = "StaffSalaryDetailMapping.deleteStaffSalaryDetail";
			this.delete(sql , map);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}


}