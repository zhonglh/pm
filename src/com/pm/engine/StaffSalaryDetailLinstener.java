package com.pm.engine;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StaffSalaryDetailLinstener implements SmartApplicationListener {
	
	@Async
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		
		if( applicationEvent instanceof ComputeEvent){
			ComputeEvent event = (ComputeEvent)applicationEvent;
			if(event.getEventType().indexOf("detail") != -1){				
				String staff_id = event.getStaff_id();
				if(staff_id != null && staff_id.length() >0) StaffProcessEngine.processSalaryDetail(staff_id);
				else StaffProcessEngine.processSalaryDetail();				
				
			}
			
		}	}

	@Override
	public int getOrder() {
		return 2;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return eventType == ComputeEvent.class;  
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		if(sourceType == String.class){
			return true;
		}else {
			return false;
		}
	}

}
