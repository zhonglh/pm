package com.pm.service;

import java.util.List;

import com.pm.domain.business.Client;
import com.pm.domain.business.ReceivableAccountsItem;
import com.pm.domain.business.ReimburseItem;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IItemsService {
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_CLIENT)
	public int addClient(Client client) ;

	public boolean isExistClient(Client client) ;
	
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_CLIENT)
	public void deleteClient(Client[] clients) ;
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_CLIENT)
	public int updateClient(Client client) ;
		
	public List<Client> queryClient(Client client) ;
	
	
	/**
	
	
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_REIMBURSE_ITEM)
	public void addReimburseItem(ReimburseItem reimburseItem) ;
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_REIMBURSE_ITEM)
	public void deleteReimburseItem(ReimburseItem[] reimburseItems) ;
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_REIMBURSE_ITEM)
	public void updateReimburseItem(ReimburseItem reimburseItem) ;

	public List<ReimburseItem> queryReimburseItem(ReimburseItem reimburseItem) ;
	
	public boolean isExistReimburseItem(ReimburseItem reimburseItem) ;
	
	
	

	
	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_ACCOUNTS_TYPE)
	public void addReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_ACCOUNTS_TYPE)
	public void deleteReceivableAccountsItem(ReceivableAccountsItem[] receivableAccountsItems) ;
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_ACCOUNTS_TYPE)
	public void updateReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;

	public List<ReceivableAccountsItem> queryReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;
	
	public boolean isExistReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;
	*/

}
