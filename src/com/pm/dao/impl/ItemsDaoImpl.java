package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IItemsDao;
import com.pm.domain.business.Client;
import com.pm.domain.business.ReceivableAccountsItem;
import com.pm.domain.business.ReimburseItem;


@Component
public class ItemsDaoImpl extends BatisDao implements IItemsDao{

	@Override
	public int addClient(Client client) {
		String sql = "ItemsMapping.addClient";
		return this.insert(sql, client);		
	}
	

	@Override
	public boolean isExistClient(Client client) {
		String sql = "ItemsMapping.isExistClient"; 
		List<Client> list = this.query(sql, Client.class, client);
		if(list == null || list.isEmpty()) return false;
		else return true;
	}	

	@Override
	public void deleteClient(Client[] clients) {
		if(clients == null) return ;
		String sql = "ItemsMapping.deleteClient";
		for(Client client : clients){
			this.update(sql, client);
		}		
	}

	@Override
	public int updateClient(Client client) {
		String sql = "ItemsMapping.updateClient";
		return this.update(sql, client);		
	}

	@Override
	public List<Client> queryClient(Client client) {
		String sql = "ItemsMapping.queryClient";
		return this.query(sql, Client.class, client);
	}
	

	
	
	
	
	
	@Override
	public void addReimburseItem(ReimburseItem reimburseItem) {
		String sql = "ItemsMapping.addReimburseItem";
		this.insert(sql, reimburseItem);			
	}

	@Override
	public void deleteReimburseItem(ReimburseItem[] reimburseItems) {
		if(reimburseItems == null) return ;
		String sql = "ItemsMapping.deleteReimburseItem";
		for(ReimburseItem reimburseItem : reimburseItems){
			this.update(sql, reimburseItem);
		}		
	}

	@Override
	public void updateReimburseItem(ReimburseItem reimburseItem) {
		String sql = "ItemsMapping.updateReimburseItem";
		this.update(sql, reimburseItem);	
		
	}

	@Override
	public List<ReimburseItem> queryReimburseItem(ReimburseItem reimburseItem) {
		String sql = "ItemsMapping.queryReimburseItem";
		return this.query(sql, ReimburseItem.class, reimburseItem);		
	}

	@Override
	public boolean isExistReimburseItem(ReimburseItem reimburseItem) {
		String sql = "ItemsMapping.isExistReimburseItem";
		List<ReimburseItem> list =  this.query(sql, ReimburseItem.class, reimburseItem);
		if(list == null || list.isEmpty()) return false;
		else return true;
	}




	
	
	
	
	
	
	
	
	

	
	
	@Override
	public void addReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) {
		String sql = "ItemsMapping.addReceivableAccountsItem";
		this.insert(sql, receivableAccountsItem);			
	}

	@Override
	public void deleteReceivableAccountsItem(ReceivableAccountsItem[] receivableAccountsItems) {
		String sql = "ItemsMapping.deleteReceivableAccountsItem";
		for(ReceivableAccountsItem receivableAccountsItem : receivableAccountsItems){
			this.update(sql, receivableAccountsItem);
		}		
	}

	@Override
	public void updateReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) {
		String sql = "ItemsMapping.updateReceivableAccountsItem";
		this.update(sql, receivableAccountsItem);	
		
	}

	@Override
	public List<ReceivableAccountsItem> queryReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) {
		String sql = "ItemsMapping.queryReceivableAccountsItem";
		return this.query(sql, ReceivableAccountsItem.class, receivableAccountsItem);		
	}

	@Override
	public boolean isExistReceivableAccountsItem(ReceivableAccountsItem receivableAccountsItem) {
		String sql = "ItemsMapping.isExistReceivableAccountsItem";
		List<ReceivableAccountsItem> list =  this.query(sql, ReceivableAccountsItem.class, receivableAccountsItem);
		if(list == null || list.isEmpty()) return false;
		else return true;
	}





}
