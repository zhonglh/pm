package com.apache.common;

import java.sql.Timestamp;

import org.apache.commons.beanutils.converters.DateTimeConverter;

public class MySqlTimestampConverter extends DateTimeConverter{

	public MySqlTimestampConverter() {  
    }  
  
    public MySqlTimestampConverter(Object defaultValue) {  
        super(defaultValue);  
    }  
  
    /* (non-Javadoc) 
     * @see org.apache.commons.beanutils.converters.AbstractConverter#getDefaultType() 
     */  
    @SuppressWarnings("rawtypes")  
    protected Class getDefaultType() {  
        return Timestamp.class;  
    }  
  
    /* 
     * (non-Javadoc) 
     * @see org.apache.commons.beanutils.converters.DateTimeConverter#convertToType(java.lang.Class, java.lang.Object) 
     */  
    @SuppressWarnings("rawtypes")  
    @Override  
    protected Object convertToType(Class arg0, Object arg1) throws Exception {  
        if (arg1 == null) {  
            return null;  
        }  
        String value = arg1.toString().trim();  
        if (value.length() == 0) {  
            return null;  
        }  
        return super.convertToType(arg0, arg1);  
    }  

}
