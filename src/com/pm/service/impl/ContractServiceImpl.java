package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.Contract;
import com.pm.dao.IContractDao;
import com.pm.service.IContractService;
import com.pm.util.PubMethod;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

@Component
public class ContractServiceImpl implements  IContractService {

	@Autowired IContractDao contractDao;
	


	@Override
	public boolean isContractNoExist(Contract contract) {
		return contractDao.isContractNoExist(contract);
	}
	
	@Override
	public int addContract(Contract contract) {
		return contractDao.addContract(contract);
	}

	@Override
	public int updateContract(Contract contract) {
		return contractDao.updateContract(contract);
	}

	@Override
	public void deleteContract(Contract[] contracts) {
		for(Contract contract : contracts){
			contractDao.deleteContract(contract);
		}
	}


	@Override
	public Contract getContract(String id) {
		Contract contract = contractDao.getContract(id);
		contract.setEffectivedate(PubMethod.twoDate2Str(contract.getValidity_date1(), contract.getValidity_date2()));
		return contract;
	}
	
	@Override
	public <T> T get(String id) {
		return (T)getContract(id);
	}

	@Override
	public Pager<Contract> queryContract(
		Contract contract,
		UserPermit userPermit,
		Pager<Contract> pager){

		Pager<Contract> pager1 =  contractDao.queryContract(contract, userPermit, pager);
		
		if(pager1.getResultList() != null){
			for(Contract contract1 : pager1.getResultList()){
				contract1.setEffectivedate(PubMethod.twoDate2Str(contract1.getValidity_date1(), contract1.getValidity_date2()));
			}
		}
		
		return pager1;
	}


}