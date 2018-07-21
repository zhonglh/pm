package com.common.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	
	private String encrypt = "0";

	protected String convertProperty(String propertyName, String propertyValue) {
		try{
			if("1".equals(encrypt)){
				if("jdbc.password".equals(propertyName)){
					try {
						return new String(Base64Kit.decode(propertyValue));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			;
		}
		return propertyValue;
	}

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

}
