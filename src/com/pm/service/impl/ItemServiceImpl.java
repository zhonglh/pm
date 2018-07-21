package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IItemsDao;
import com.pm.domain.business.Client;
import com.pm.domain.business.ReceivableAccountsItem;
import com.pm.domain.business.ReimburseItem;
import com.pm.service.IItemsService;


@Component
public class ItemServiceImpl implements IItemsService {
	
	@Autowired private IItemsDao itemDao;

	@Override
	public int addClient(Client client) {		
		return itemDao.addClient(client);
	}

	@Override
	public boolean isExistClient(Client client) {
		return itemDao.isExistClient(client);
	}

	@Override
	public void deleteClient(Client[] clients) {
		itemDao.deleteClient(clients);		
	}

	@Override
	public int updateClient(Client client) {
		return itemDao.updateClient(client);		
	}

	@Override
	public List<Client> queryClient(Client client) {
		return itemDao.queryClient(client);
	}



}
