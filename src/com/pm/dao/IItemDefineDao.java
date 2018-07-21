package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.system.ItemDefine;

public interface IItemDefineDao {

	public int addItemDefine(ItemDefine itemDefine) ;

	public int updateItemDefine(ItemDefine itemDefine) ; 

	public void deleteItemDefine(ItemDefine itemDefine) ;

	public ItemDefine getItemDefine(String id) ;	

	public Pager<ItemDefine> queryItemDefine(ItemDefine itemDefine, Pager<ItemDefine> pager);

}