package com.pm.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomDoubleEditor extends PropertyEditorSupport {

	public CustomDoubleEditor() {
	}

	public CustomDoubleEditor(Object source) {
		super(source);
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(text == null || text.trim().length() == 0){
			setValue(0.00);
		}
		else if (text != null && text.trim().length() > 0) {
			try{
				Double val = new Double(text.trim());
				this.setValue(val.doubleValue());
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
		if(getValue() == null || getValue().toString().trim().isEmpty()) return "0.00";
		else {
			Double value = (Double) getValue();
			return (value != null ? value.toString() : "0.00");
		}
	}	

}
