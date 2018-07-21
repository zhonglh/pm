package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IInvoiceDao;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.ReceivedPayment;
import com.pm.vo.UserPermit;


@Component
public class InvoiceDaoImpl extends BatisDao implements IInvoiceDao {


	@Override
	public int addInvoice(Invoice invoice) {
		String sql = "InvoiceMapping.addInvoice";
		return this.insert(sql,invoice);
	}

	@Override
	public int  updateInvoice(Invoice invoice) {
		String sql = "InvoiceMapping.updateInvoice";
		return this.update(sql,invoice);
	}


	@Override
	public void updateInvoiceReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "InvoiceMapping.updateInvoiceReceivedPayment";
		this.update(sql,receivedPayment);
	}


	@Override
	public void updateInvoiceIsReceivedPayment(ReceivedPayment receivedPayment) {
		String sql = "InvoiceMapping.updateInvoiceIsReceivedPayment";
		this.update(sql,receivedPayment);
	}
	
	
	@Override
	public void deleteInvoice(Invoice invoice) {
		String sql = "InvoiceMapping.deleteInvoice";
		this.delete(sql,invoice);
	}

	@Override
	public int verifyInvoice(Invoice invoice) {
		String sql = "InvoiceMapping.verifyInvoice";
		return this.update(sql,invoice);
	}
	

	@Override
	public int unVerifyInvoice(Invoice invoice){
		String sql = "InvoiceMapping.unVerifyInvoice";
		return this.update(sql,invoice);
	}
	
	

	@Override
	public void deleteMonthlyStatement(String monthly_statement_id){
		Invoice invoice = new Invoice();
		invoice.setMonthly_statement_id(monthly_statement_id);
		String sql = "InvoiceMapping.deleteMonthlyStatement";
		this.update(sql,invoice);
	}

	@Override
	public Invoice getInvoice(String invoice_id) {
		Invoice invoice = new Invoice();
		invoice.setInvoice_id(invoice_id);
		String sql = "InvoiceMapping.getInvoice";
		List<Invoice> list = this.query(sql, Invoice.class, invoice);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}


	public List<Invoice> getInvoiceByMonthly(String monthly_statement_id) {
		Invoice invoice = new Invoice();
		invoice.setMonthly_statement_id(monthly_statement_id);
		String sql = "InvoiceMapping.getInvoiceByMonthly";
		return this.query(sql, Invoice.class, invoice);
	}
	
	@Override
	public Pager<Invoice> queryInvoice(Invoice invoice,
			UserPermit userPermit, Pager<Invoice> pager) {
		String sql = "InvoiceMapping.queryInvoice";
		Pager<Invoice> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Invoice.class, invoice,userPermit);
		
		return pager1;
		
		
	}

}
