package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.ReceivedPayment;
import com.pm.vo.UserPermit;

public interface IInvoiceDao {


	public int addInvoice(Invoice invoice) ;
	

	public int updateInvoice(Invoice invoice) ; 
	
	/**
	 * 修改到款记录
	 * @param invoice
	 */
	public void updateInvoiceReceivedPayment(ReceivedPayment receivedPayment) ; 
	
	
	/**
	 * 修改是否全部到款
	 * @param receivedPayment
	 */
	public void updateInvoiceIsReceivedPayment(ReceivedPayment receivedPayment) ; 
	

	public void deleteInvoice(Invoice invoices) ;


	public int verifyInvoice(Invoice invoice) ;
	

	public int unVerifyInvoice(Invoice invoice) ;
	
	
	public void deleteMonthlyStatement(String monthly_statement_id);
	
	public Invoice getInvoice(String invoice_id) ;	
	
	public List<Invoice> getInvoiceByMonthly(String monthly_statement_id) ;		
	

	public Pager<Invoice> queryInvoice(Invoice invoice,  UserPermit userPermit,Pager<Invoice> pager);
	
}
