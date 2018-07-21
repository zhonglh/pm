package com.pm.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomIntEditor extends PropertyEditorSupport {

	public CustomIntEditor() {
	}

	public CustomIntEditor(Object source) {
		super(source);
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(text == null || text.trim().length() == 0){
			setValue(0);
		}
		else if (text != null && text.trim().length() > 0) {
			try{
				Integer val = new Integer(text.trim());
				this.setValue(val.intValue());
			}catch(Exception e){
				throw e;
			}
		}
		else {
			throw new IllegalArgumentException("Could not parse int:" + text);
		}
	}	
	
	@Override
	public String getAsText() {

		if(getValue() == null || getValue().toString().trim().isEmpty()) return "0";
		else {
			Integer value = (Integer) getValue();
			return (value != null ? value.toString() : "0");
		}
	}	

}
