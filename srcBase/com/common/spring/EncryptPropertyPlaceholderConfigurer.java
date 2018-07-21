package com.common.spring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.common.utils.SystemKit;
import com.common.utils.encrypt.DESKit;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Logger logger           = LoggerFactory.getLogger (EncryptPropertyPlaceholderConfigurer.class);
    private String[]      encryptPropNames = { "jdbc.username.sun", "jdbc.password.sun", "jdbc.username.ibm", "jdbc.password.ibm" };

    protected Properties  props;

    @Override
    protected void convertProperties(Properties props){
        this.props = props;
        super.convertProperties (props);
        for ( String encryptpropertyName : encryptPropNames ) {
            props.remove (encryptpropertyName);
        }
    }

    @Override
    protected String convertProperty(String propertyName,String propertyValue){
        if (isEncryptProp (propertyName)) {
            logger.debug ("ciphertext is " + propertyName + "=" + propertyValue);
            if (SystemKit.isIBMJDKVendor ()) {
                if (propertyName.endsWith ("ibm")) props.put (propertyName.substring (0, propertyName.length () - 4), DESKit.decrypt (propertyValue));
            } else {
                if (propertyName.endsWith ("sun")) props.put (propertyName.substring (0, propertyName.length () - 4), DESKit.decrypt (propertyValue));
            }
        }
        return propertyValue;
    }

    /**
     * 判断是否是加密的属性
     * 
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName){
        for ( String encryptpropertyName : encryptPropNames ) {
            if (propertyName.indexOf (encryptpropertyName) > -1) return true;
        }
        return false;
    }
}
