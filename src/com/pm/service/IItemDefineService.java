package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.system.ItemDefine;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IItemDefineService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_ITEMDEFINE)
	public int addItemDefine(ItemDefine itemDefine) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_ITEMDEFINE)
	public int updateItemDefine(ItemDefine itemDefine) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_ITEMDEFINE)
	public void deleteItemDefine(ItemDefine[] itemDefines) ;


	public ItemDefine getItemDefine(String id) ;	

	public Pager<ItemDefine> queryItemDefine(ItemDefine itemDefine,  Pager<ItemDefine> pager);

}