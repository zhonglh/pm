package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Invoice;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

/**
 * @author Administrator
 */
public interface IInvoiceService extends IBaseService{
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_INVOICE)
	public int addInvoice(Invoice invoice) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_INVOICE)
	public int updateInvoice(Invoice invoice) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_INVOICE)
	public void deleteInvoice(Invoice[] invoices) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_INVOICE)
	public void verifyInvoice(Invoice invoice) ;
	
	
	
	public Invoice getInvoice(String invoice_id) ;	
	

	public List<Invoice> getInvoiceByMonthly(String monthly_statement_id) ;	
	
	

	public Pager<Invoice> queryInvoice(Invoice invoice,  UserPermit userPermit,Pager<Invoice> pager);
	
	
}
