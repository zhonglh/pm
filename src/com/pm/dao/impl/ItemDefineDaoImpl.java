package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IItemDefineDao;
import com.pm.domain.system.ItemDefine;

@Component
public class ItemDefineDaoImpl extends BatisDao implements IItemDefineDao  {

	@Override
	public int addItemDefine(ItemDefine itemDefine) {
		String sql = "ItemDefineMapping.addItemDefine";
		return this.insert(sql, itemDefine);
	}

	@Override
	public int updateItemDefine(ItemDefine itemDefine) {
		String sql = "ItemDefineMapping.updateItemDefine";
		return this.update(sql, itemDefine);
	}

	@Override
	public void deleteItemDefine(ItemDefine itemDefine) {
		String sql = "ItemDefineMapping.deleteItemDefine";
		this.delete(sql, itemDefine);
	}


	@Override
	public ItemDefine getItemDefine(String id) {

		String sql = "ItemDefineMapping.getItemDefine"; 
		ItemDefine itemDefine = new ItemDefine(); 
		itemDefine.setId(id); 
		List<ItemDefine> list = this.query(sql, ItemDefine.class, itemDefine); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<ItemDefine> queryItemDefine(
		ItemDefine itemDefine,
		Pager<ItemDefine> pager){

		String sql = "ItemDefineMapping.queryItemDefine"; 
		Pager<ItemDefine> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ItemDefine.class, itemDefine); 
		
		return pager1;
	}


}