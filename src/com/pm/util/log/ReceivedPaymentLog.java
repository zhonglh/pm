package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.ReceivedPayment;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IReceivedPaymentService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ReceivedPaymentLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IReceivedPaymentService receivedPaymentService = SpringContextUtil.getApplicationContext().getBean(IReceivedPaymentService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			ReceivedPayment[] receivedPayments = (ReceivedPayment[])invocation.getArguments()[0];
			if(receivedPayments == null || receivedPayments.length == 0) return null;
			for(ReceivedPayment receivedPayment : receivedPayments){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				ReceivedPayment preReceivedPayment = receivedPaymentService.getReceivedPayment(receivedPayment.getReceive_payment_id());
				if(preReceivedPayment == null) preReceivedPayment = new ReceivedPayment();
				log.setEntity_id(receivedPayment.getReceive_payment_id());


				String entity_name = receivedPayment.getProject_name() == null ?
						(preReceivedPayment.getReceive_payment_month() + ":" +preReceivedPayment.getProject_name()  + ":" + preReceivedPayment.getPayment_unit()):
						(receivedPayment.getReceive_payment_month() + ":" +receivedPayment.getProject_name()  + ":" + receivedPayment.getPayment_unit());
				
				log.setEntity_name(entity_name);
				
				log.setProject_id(receivedPayment.getProject_id()==null?preReceivedPayment.getProject_id():receivedPayment.getProject_id());
				List<LogDetail>  details = PubMethod.getLogDetails(log,ReceivedPayment.class, preReceivedPayment,receivedPayment);	
				
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				ReceivedPayment receivedPayment = (ReceivedPayment)invocation.getArguments()[0];
				ReceivedPayment preReceivedPayment = new ReceivedPayment();				
				log.setEntity_id(receivedPayment.getReceive_payment_id());
				
				String entity_name = receivedPayment.getProject_name() == null ?
						(preReceivedPayment.getReceive_payment_month() + ":" +preReceivedPayment.getProject_name()  + ":" + preReceivedPayment.getPayment_unit()):
						(receivedPayment.getReceive_payment_month() + ":" +receivedPayment.getProject_name()  + ":" + receivedPayment.getPayment_unit());
				
				log.setEntity_name(entity_name);
				log.setProject_id(receivedPayment.getProject_id()==null?preReceivedPayment.getProject_id():receivedPayment.getProject_id());
				
				details = PubMethod.getLogDetails(log,ReceivedPayment.class, preReceivedPayment,receivedPayment);	
				
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				ReceivedPayment receivedPayment = (ReceivedPayment)invocation.getArguments()[0];
				ReceivedPayment preReceivedPayment = receivedPaymentService.getReceivedPayment(receivedPayment.getReceive_payment_id());				
				log.setEntity_id(receivedPayment.getReceive_payment_id());


				String entity_name = receivedPayment.getProject_name() == null ?
						(preReceivedPayment.getReceive_payment_month() + ":" +preReceivedPayment.getProject_name()  + ":" + preReceivedPayment.getPayment_unit()):
						(receivedPayment.getReceive_payment_month() + ":" +receivedPayment.getProject_name()  + ":" + receivedPayment.getPayment_unit());
				
				log.setEntity_name(entity_name);
				
				log.setProject_id(receivedPayment.getProject_id()==null?preReceivedPayment.getProject_id():receivedPayment.getProject_id());
				
				details = PubMethod.getLogDetails(log,ReceivedPayment.class, preReceivedPayment,receivedPayment);				
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				ReceivedPayment receivedPayment = (ReceivedPayment)invocation.getArguments()[0];
				ReceivedPayment preReceivedPayment = receivedPaymentService.getReceivedPayment(receivedPayment.getReceive_payment_id());				
				log.setEntity_id(receivedPayment.getReceive_payment_id());



				String entity_name = receivedPayment.getProject_name() == null ?
						(preReceivedPayment.getReceive_payment_month() + ":" +preReceivedPayment.getProject_name()  + ":" + preReceivedPayment.getPayment_unit()):
						(receivedPayment.getReceive_payment_month() + ":" +receivedPayment.getProject_name()  + ":" + receivedPayment.getPayment_unit());
				
				log.setEntity_name(entity_name);
				
				log.setProject_id(receivedPayment.getProject_id()==null?preReceivedPayment.getProject_id():receivedPayment.getProject_id());
				
				//details = PubMethod.getLogDetails(log,ReceivedPayment.class, preReceivedPayment,receivedPayment);				
				//log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	

	

}
