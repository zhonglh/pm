package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.Client;
import com.pm.domain.business.ReceivableAccountsItem;
import com.pm.domain.business.ReimburseItem;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IItemsService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ItemsLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		List<Log> logs = new ArrayList<Log>();
		IItemsService itemsService = SpringContextUtil.getApplicationContext().getBean(IItemsService.class);
		
		if(invocation.getMethod().getName().indexOf("Client")!=-1){			
			
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			Client[] clients = (Client[])invocation.getArguments()[0];
			if(clients == null || clients.length == 0) return null;
			for(Client client : clients){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				List<Client> list= itemsService.queryClient(client);
				Client preClient = null;
				if(list != null && list.size() >0) preClient = list.get(0);
				if(preClient == null) preClient = new Client();
				
				log.setEntity_id(client.getClient_id());
				log.setEntity_name(client.getClient_name()==null?preClient.getClient_name():client.getClient_name());
				List<LogDetail>  details = PubMethod.getLogDetails(log,Client.class, preClient,client);	
				log.setDetails(details);
				logs.add(log);
			}
			
			}else {
				
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
	
				List<LogDetail> details = null;
				if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
					Client client = (Client)invocation.getArguments()[0];
					Client preClient = new Client();				
					log.setEntity_id(client.getClient_id());
					log.setEntity_name(client.getClient_name()==null?preClient.getClient_name():client.getClient_name());
					details = PubMethod.getLogDetails(log,Client.class, preClient,client);		
					log.setDetails(details);		
					logs.add(log);
				}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
					Client client = (Client)invocation.getArguments()[0];
					List<Client> list= itemsService.queryClient(client);
					Client preClient = null;
					if(list != null && list.size() >0) preClient = list.get(0);
					if(preClient == null) preClient = new Client();
					
					log.setEntity_id(client.getClient_id());
					log.setEntity_name(client.getClient_name()==null?preClient.getClient_name():client.getClient_name());
					details = PubMethod.getLogDetails(log,Client.class, preClient,client);				
					log.setDetails(details);
					logs.add(log);
				}
				
				
			}
			
		}else  if(invocation.getMethod().getName().indexOf("ReimburseItem")!=-1){
			
		}else  if(invocation.getMethod().getName().indexOf("ReceivableAccountsItem")!=-1){}
		
		return logs;
	}
	
	
	
	
	

}
