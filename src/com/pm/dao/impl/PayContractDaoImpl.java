package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IPayContractDao;
import com.pm.domain.business.PayContract;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class PayContractDaoImpl extends BatisDao implements IPayContractDao  {


	@Override
	public  boolean isNoExist(PayContract payContract) {

		String sql = "PayContractMapping.isNoExist";


		List<PayContract> list = this.query(sql, PayContract.class, payContract);
		if(list == null || list.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int addPayContract(PayContract payContract) {
		String sql = "PayContractMapping.addPayContract";
		return this.insert(sql, payContract);
	}

	@Override
	public int updatePayContract(PayContract payContract) {
		String sql = "PayContractMapping.updatePayContract";
		return this.update(sql, payContract);
	}

	@Override
	public void deletePayContract(PayContract payContract) {
		String sql = "PayContractMapping.deletePayContract";
		this.delete(sql, payContract);
	}

	@Override
	public void verifyPayContract(PayContract payContract) {
		String sql = "PayContractMapping.verifyPayContract";
		this.update(sql, payContract);
	}

	@Override
	public void unVerifyPayContract(PayContract payContract) {
		String sql = "PayContractMapping.unVerifyPayContract";
		this.update(sql, payContract);
	}

	@Override
	public PayContract getPayContract(String id) {

		String sql = "PayContractMapping.getPayContract"; 
		PayContract payContract = new PayContract(); 
		payContract.setId(id); 
		List<PayContract> list = this.query(sql, PayContract.class, payContract); 
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public Pager<PayContract> queryPayContract(
		PayContract payContract,
		UserPermit userPermit,
		Pager<PayContract> pager){

		String sql = "PayContractMapping.queryPayContract"; 
		Pager<PayContract> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,PayContract.class, payContract,userPermit); 
		sql = "PayContractMapping.queryPayContractTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(payContract != null) {
			map.put(payContract.getClass().getSimpleName(), payContract);
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