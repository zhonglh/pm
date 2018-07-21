package com.pm.dao;

import java.util.List;

import com.pm.domain.business.Client;
import com.pm.domain.business.ReceivableAccountsItem;
import com.pm.domain.business.ReimburseItem;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IItemsDao {



	
	public int addClient(Client client) ;	

	public boolean isExistClient(Client client) ;
	
	public void deleteClient(Client[] clients) ;


	public int updateClient(Client client) ;
		
	public List<Client> queryClient(Client client) ;
	



	public void addReimburseItem(ReimburseItem reimburseItem) ;
	
	public boolean isExistReimburseItem(ReimburseItem reimburseItem) ;

	public void deleteReimburseItem(ReimburseItem[] reimburseItems) ;


	public void updateReimburseItem(ReimburseItem reimburseItem) ;

	public List<ReimburseItem> queryReimburseItem(ReimburseItem reimburseItem) ;
	
	

	
	public void addReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;
	
	public void deleteReceivableAccountsItem(ReceivableAccountsItem[] receivableAccountsItems) ;
	
	public void updateReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;

	public List<ReceivableAccountsItem> queryReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;
	
	public boolean isExistReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) ;
	
}
