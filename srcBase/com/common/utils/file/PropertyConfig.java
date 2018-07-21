package com.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;


/**
 * PropertyConfig: read properties file file path: ../WEB-INFO/ or classpath
 */
public class PropertyConfig {

    private ConcurrentMap<String, Object> properties = new ConcurrentHashMap<String, Object> ();

    private static class PropertyConfigHolder {

        static PropertyConfig instance = new PropertyConfig ();
    }

    public static PropertyConfig me(){
        return PropertyConfigHolder.instance;
    }

    public PropertyConfig() {}

    public void loadPropertyFile(String file){
        Properties property = new Properties ();
        if (StringUtils.isBlank (file)) throw new IllegalArgumentException ("Parameter of file can not be blank");
        if (file.contains ("..")) throw new IllegalArgumentException ("Parameter of file can not contains \"..\"");

        InputStream inputStream = null;
        String fullFile; // String fullFile = PathUtil.getWebRootPath() + file;
        if (file.startsWith (File.separator)) fullFile = PathKit.getRootClassPath ()  + file;
        else fullFile = PathKit.getRootClassPath () + File.separator  + file;

        try {
            inputStream = new FileInputStream (new File (fullFile));
            property.load (inputStream);
        } catch (Exception eOne) {
            try {
                ClassLoader loader = Thread.currentThread ().getContextClassLoader ();
                property.load (loader.getResourceAsStream (file));
            } catch (IOException eTwo) {
                throw new IllegalArgumentException ("Properties file loading failed: " + eTwo.getMessage ());
            }
        } finally {
            try {
                if (inputStream != null) inputStream.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        if (property != null) {
            for ( Entry<Object, Object> entry : property.entrySet () ) {
                this.properties.put (entry.getKey ().toString (), entry.getValue ());
            }
        }
    }

    public String getProperty(String key){
        if (this.properties.containsKey (key)) { 
               String val=properties.get (key).toString ();
               try {
                return new String (val.getBytes ("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
          }
        return null;
    }

    public String getProperty(String key,String defaultValue){
        if (this.properties.containsKey (key)) { 
            String val=properties.get (key).toString ();
            try {
            	return new String (val.getBytes ("ISO8859-1"),"UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
        }
        return defaultValue;
    }

    public Integer getPropertyToInt(String key){
        Integer resultInt = null;
        String resultStr = this.getProperty (key);
        if (resultStr != null) resultInt = Integer.parseInt (resultStr);
        return resultInt;
    }

    public Integer getPropertyToInt(String key,Integer defaultValue){
        Integer result = getPropertyToInt (key);
        return result != null ? result : defaultValue;
    }

    public Long getPropertyToLong(String key){
        Long resultInt = null;
        String resultStr = this.getProperty (key);
        if (resultStr != null) resultInt = Long.parseLong (resultStr);
        return resultInt;
    }

    public Long getPropertyToLong(String key,Long defaultValue){
        Long result = getPropertyToLong (key);
        return result != null ? result : defaultValue;
    }

    public Boolean getPropertyToBoolean(String key){
        String resultStr = this.getProperty (key);
        Boolean resultBool = null;
        if (resultStr != null) {
            if (resultStr.trim ().equalsIgnoreCase ("true")) resultBool = true;
            else if (resultStr.trim ().equalsIgnoreCase ("false")) resultBool = false;
        }
        return resultBool;
    }

    public Boolean getPropertyToBoolean(String key,boolean defaultValue){
        Boolean result = getPropertyToBoolean (key);
        return result != null ? result : defaultValue;
    }
}