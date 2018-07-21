package com.pm.engine;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class ComputeEvent extends ApplicationEvent {
	
	//多个类型用逗号分隔
	private String eventType ;
	
	private String staff_id;
	

	public ComputeEvent( String eventType, String staff_id) {
		super(eventType);
		this.eventType = eventType;
		this.staff_id = staff_id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	
	
	

}
