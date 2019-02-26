package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IContractDao;
import com.pm.domain.business.Contract;
import com.pm.vo.UserPermit;

@Component
public class ContractDaoImpl extends BatisDao implements IContractDao  {
	
	

	@Override
	public boolean isContractNoExist(Contract contract) {

		String sql = "ContractMapping.getContractNoExist"; 
		List<Contract> list = this.query(sql, Contract.class, contract); 
		if(list != null && list.size() > 0 ) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int addContract(Contract contract) {
		String sql = "ContractMapping.addContract";
		return this.insert(sql, contract);
	}

	@Override
	public int updateContract(Contract contract) {
		String sql = "ContractMapping.updateContract";
		return this.update(sql, contract);
	}

	@Override
	public void deleteContract(Contract contract) {
		String sql = "ContractMapping.deleteContract";
		this.delete(sql, contract);
	}

	@Override
	public Contract getContract(String id) {
		String sql = "ContractMapping.getContract"; 
		Contract contract = new Contract(); 
		contract.setId(id); 
		List<Contract> list = this.query(sql, Contract.class, contract); 
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public Pager<Contract> queryContract(
		Contract contract,
		UserPermit userPermit,
		Pager<Contract> pager){
		String sql = "ContractMapping.queryContract"; 
		Pager<Contract> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,Contract.class, contract,userPermit);



		sql = "ContractMapping.queryContractTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(contract != null) {
			map.put(contract.getClass().getSimpleName(), contract);
		}
		if(userPermit != null) {
			map.put(userPermit.getClass().getSimpleName(), userPermit);
		}
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}

		return pager1;
	}


}