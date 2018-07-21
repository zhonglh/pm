package com.pm.engine;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Sender implements  ApplicationContextAware {
	
	private ApplicationContext ctx = null;
	
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;		
	}
	
	public void send(String eventType , String staff_id){		
		ComputeEvent event = new ComputeEvent(eventType,staff_id);
		ctx.publishEvent(event);		
	}

}
