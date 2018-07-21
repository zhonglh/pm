package com.pm.engine;

import com.common.utils.spring.SpringContextUtil;

public class BusinessSend {
	
	//MarketSets   , StaffExEntity  Params  StaffCost
	
	public static final Sender sender = SpringContextUtil.getBean(Sender.class);
	
	private static final String type_child = "child";
	private static final String type_detail = "detail";
	
	private static String getEventType(String business){
		
		String eventType = null;
		
		switch (business) {
		case "MarketSets":
			eventType = type_child+","+type_detail;
			break;
		case "Params":
			eventType = type_detail;
			break;
		case "StaffCost":
			eventType = type_detail;
			break;
		default:
			eventType = type_child+","+type_detail;
			break;
		}
		
		return eventType;
	}
	

	public static void send(String business){	
		
		try{
			
			sender.send(getEventType(business) , null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void send(String business , String staff_id){	
		
		try{

			sender.send(getEventType(business) , staff_id);
		
		}catch(Exception e){
			e.printStackTrace();
		}		
	
	}

}
