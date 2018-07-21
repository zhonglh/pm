package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IReceivedPaymentDao;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.ReceivedPayment;
import com.pm.vo.UserPermit;


@Component
public class ReceivedPaymentDaoImpl extends BatisDao implements IReceivedPaymentDao {


	@Override
	public int addReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "ReceivedPaymentMapping.addReceivedPayment";
		return this.insert(sql,receivedPayment);
	}

	@Override
	public int updateReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "ReceivedPaymentMapping.updateReceivedPayment";
		return this.update(sql,receivedPayment);
	}

	@Override
	public void deleteReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "ReceivedPaymentMapping.deleteReceivedPayment";
		this.delete(sql,receivedPayment);
	}

	@Override
	public int verifyReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "ReceivedPaymentMapping.verifyReceivedPayment";
		return this.update(sql,receivedPayment);
	}
	

	@Override
	public int unVerifyReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "ReceivedPaymentMapping.unVerifyReceivedPayment";
		return this.update(sql,receivedPayment);
	}
	

	@Override
	public void deleteInvoice(Invoice invoice){
		String sql = "ReceivedPaymentMapping.deleteInvoice";
		this.update(sql,invoice);
	}

	@Override
	public ReceivedPayment getReceivedPayment(String receive_payment_id) {
		ReceivedPayment receivedPayment = new ReceivedPayment();
		receivedPayment.setReceive_payment_id(receive_payment_id);
		String sql = "ReceivedPaymentMapping.getReceivedPayment";
		List<ReceivedPayment> list = this.query(sql, ReceivedPayment.class, receivedPayment);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}

	@Override
	public Pager<ReceivedPayment> queryReceivedPayment(
			ReceivedPayment receivedPayment,
			UserPermit userPermit, 
			Pager<ReceivedPayment> pager) {
		String sql = "ReceivedPaymentMapping.queryReceivedPayment";
		Pager<ReceivedPayment> pager1 = this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, ReceivedPayment.class, receivedPayment,userPermit);
		
		return pager1;
		
		
		
	}

}
