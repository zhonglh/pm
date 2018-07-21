package com.pm.engine;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StaffChildLinstener implements SmartApplicationListener {
	
	@Async
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		
		if( applicationEvent instanceof ComputeEvent){
			ComputeEvent event = (ComputeEvent)applicationEvent;
			if(event.getEventType().indexOf("child") != -1){				
				String staff_id = event.getStaff_id();
				if(staff_id != null && staff_id.length() >0) StaffProcessEngine.processChild(staff_id);
				else StaffProcessEngine.processChild();				
				
			}
			
		}	}

	@Override
	public int getOrder() {
		return 1;
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
