package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.Invoice;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IInvoiceService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class InvoiceLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IInvoiceService invoiceService = SpringContextUtil.getApplicationContext().getBean(IInvoiceService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			Invoice[] invoices = (Invoice[])invocation.getArguments()[0];
			if(invoices == null || invoices.length == 0) return null;
			for(Invoice invoice : invoices){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				Invoice preInvoice = invoiceService.getInvoice(invoice.getInvoice_id());
				if(preInvoice == null) preInvoice = new Invoice();
				log.setEntity_id(invoice.getInvoice_id());
				log.setEntity_name(invoice.getInvoice_no()==null?preInvoice.getInvoice_no():invoice.getInvoice_no());
				log.setProject_id(invoice.getProject_id()==null?preInvoice.getProject_id() : invoice.getProject_id());
				List<LogDetail>  details = PubMethod.getLogDetails(log,Invoice.class, preInvoice,invoice);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				Invoice invoice = (Invoice)invocation.getArguments()[0];
				Invoice preInvoice = new Invoice();				
				log.setEntity_id(invoice.getInvoice_id());
				log.setEntity_name(invoice.getInvoice_no()==null?preInvoice.getInvoice_no():invoice.getInvoice_no());
				log.setProject_id(invoice.getProject_id()==null?preInvoice.getProject_id() : invoice.getProject_id());
				details = PubMethod.getLogDetails(log,Invoice.class, preInvoice,invoice);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				Invoice invoice = (Invoice)invocation.getArguments()[0];
				Invoice preInvoice = invoiceService.getInvoice(invoice.getInvoice_id());				
				log.setEntity_id(invoice.getInvoice_id());
				log.setEntity_name(invoice.getInvoice_no()==null?preInvoice.getInvoice_no():invoice.getInvoice_no());
				log.setProject_id(invoice.getProject_id()==null?preInvoice.getProject_id() : invoice.getProject_id());
				details = PubMethod.getLogDetails(log,Invoice.class, preInvoice,invoice);				
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				Invoice invoice = (Invoice)invocation.getArguments()[0];
				Invoice preInvoice = invoiceService.getInvoice(invoice.getInvoice_id());				
				log.setEntity_id(invoice.getInvoice_id());
				log.setEntity_name(invoice.getInvoice_no()==null?preInvoice.getInvoice_no():invoice.getInvoice_no());
				log.setProject_id(invoice.getProject_id()==null?preInvoice.getProject_id() : invoice.getProject_id());
				//details = PubMethod.getLogDetails(log,Invoice.class, preInvoice,invoice);				
				//log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	

	private void special(List<LogDetail> details){
		if(details == null || details.isEmpty()) return ;
		for(LogDetail detail : details){
			if(detail.getData_item_code().equals("is_exemption_tax")){
				if(detail.getOperation_after() != null && !detail.getOperation_after().isEmpty()){
					detail.setOperation_after(SpringContextUtil.getApplicationContext ().getMessage ("boolean."+detail.getOperation_after(), null, Locale.CHINA));
				}
				if(detail.getOperation_before() != null && !detail.getOperation_before().isEmpty()){
					detail.setOperation_before(SpringContextUtil.getApplicationContext ().getMessage ("boolean."+detail.getOperation_before(), null, Locale.CHINA));
				}
				
				
			}
			

			if(detail.getData_item_code().equals("invoice_type")){
				if(detail.getOperation_after() != null && !detail.getOperation_after().isEmpty()){
					detail.setOperation_after(SpringContextUtil.getApplicationContext ().getMessage ("invoice.type."+detail.getOperation_after(), null, Locale.CHINA));
				}
				if(detail.getOperation_before() != null && !detail.getOperation_before().isEmpty()){
					detail.setOperation_before(SpringContextUtil.getApplicationContext ().getMessage ("invoice.type."+detail.getOperation_before(), null, Locale.CHINA));
				}
				
				
			}
					
		}
	}
	
	

}