package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IItemDefineDao;
import com.pm.dao.IStaffSalaryDetailDao;
import com.pm.domain.system.ItemDefine;
import com.pm.service.IItemDefineService;

@Component
public class ItemDefineServiceImpl implements  IItemDefineService {

	@Autowired IItemDefineDao itemDefineDao;
	
	@Autowired IStaffSalaryDetailDao staffSalaryDetailDao ;
	
	@Override
	public int addItemDefine(ItemDefine itemDefine) {
		int count = itemDefineDao.addItemDefine(itemDefine);
		staffSalaryDetailDao.doProcessStaffSalaryDetailByItem(itemDefine.getId());
		return count ;
	}

	@Override
	public int updateItemDefine(ItemDefine itemDefine) {
		int count = itemDefineDao.updateItemDefine(itemDefine);
		staffSalaryDetailDao.doProcessStaffSalaryDetailByItem(itemDefine.getId());
		return count ;
	}

	@Override
	public void deleteItemDefine(ItemDefine[] itemDefines) {
		for(ItemDefine itemDefine : itemDefines){
			itemDefineDao.deleteItemDefine(itemDefine);
			staffSalaryDetailDao.doProcessStaffSalaryDetailByItem(itemDefine.getId());
		}
	}

	@Override
	public ItemDefine getItemDefine(String id) {
		return itemDefineDao.getItemDefine(id);
	}
	

	@Override
	public <T> T get(String id) {		
		return (T)getItemDefine(id);
	}


	@Override
	public Pager<ItemDefine> queryItemDefine(
		ItemDefine itemDefine,
		Pager<ItemDefine> pager){

		return itemDefineDao.queryItemDefine(itemDefine,  pager);
	}


}