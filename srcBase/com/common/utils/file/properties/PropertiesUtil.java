package com.common.utils.file.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 配置文件工具类
 * 
 * @author Riche's
 * @since 2012-08-09
 */
public class PropertiesUtil {

    private static Logger                        logger = LoggerFactory.getLogger (PropertiesUtil.class);

    private static Hashtable<String, Properties> table  = new Hashtable<String, Properties> ();

    public static String getValue(String fileName,String key){
        Properties prop = null;
        if (fileName != null && !fileName.trim ().equals ("")) {
            if (!table.containsKey (fileName)) {
                prop = new Properties ();
                InputStream is = null;
                try {
                    ClassPathResource cpr = new ClassPathResource (fileName);
                    is = cpr.getInputStream ();
                    prop.load (is);
                } catch (IOException e) {
                    logger.error ("Load Properties " + fileName + " failer!");
                } finally {
                    if (null != is) {
                        try {
                            is.close ();
                        } catch (IOException e) {
                            logger.error ("Close inputStream failer");
                        }
                    }
                }
                table.put (fileName, prop);
            }
            prop = (Properties) table.get (fileName);
            return (String) prop.getProperty (key);
        }
        return "";
    }

    public static Properties    powerdirectorProps;
    // PowerDirector全局配置文件
    private static final String TEBE_FILENAME = "tebe.properties";

    // 加载配置文件
    public static Properties getProperties(String fileName){
        Properties properties = null;
        URL propUrl = null;
        try {
            propUrl = getConfPath (fileName);
            properties = loadProperties (propUrl);

            logger.debug ("init propFile: " + fileName);
        } catch (IOException e) {
            logger.error ("getProperties IOException = " + e);
        }
        return properties;
    }

    // 取得配置文件的路径
    public static URL getConfPath(String file){
        String propUrl = Thread.currentThread ().getContextClassLoader ().getResource ("").getPath ();

        try {
            propUrl = URLDecoder.decode (propUrl, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            logger.error ("getConfPath UnsupportedEncodingException = " + e1);
        }
        String url[] = propUrl.split ("/");
        URL propURL = null;
        String str = "";
        if (url.length < 0) {
            url = propUrl.split ("\\\\");
        }
        for ( int i = 1 ; i < url.length ; i++ ) {
            if (!"WEB-INF".equals (url[i])) {
                str += url[i - 1] + "/";
            } else {
                str += "config/";
                break;
            }
        }

        logger.debug ("PropConf path: " + str);

        try {
            File fil = new File (str);
            if (!fil.isDirectory ()) {
                fil.mkdir ();
                logger.debug ("make directory:  config/ ");
                fil = new File (str + file);
                InputStream demoPath = Thread.currentThread ().getContextClassLoader ().getResource (TEBE_FILENAME).openStream ();
                copyFile (demoPath, fil);
            } else if (!(fil = new File (str + file)).isFile ()) {
                InputStream demoPath = Thread.currentThread ().getContextClassLoader ().getResource (TEBE_FILENAME).openStream ();
                copyFile (demoPath, fil);
            }
            propURL = fil.toURI ().toURL ();
        } catch (MalformedURLException e) {
            logger.error ("getConfPath MalformedURLException = " + e);
        } catch (IOException e) {
            logger.error ("getConfPath IOException = " + e);
        }
        return propURL;
    }

    // 拷贝文件
    public static void copyFile(InputStream in,File out) throws IOException{
        if (null != in) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream (out);
            } catch (FileNotFoundException e) {
                throw new RuntimeException ("copyFile FileNotFoundException = " + e);
            }
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = in.read (buf)) != -1) {
                fos.write (buf, 0, i);
            }
            in.close ();
            if (null != fos) fos.close ();
            logger.debug ("copy file successful !");
        }
    }

    public static Properties loadProperties(URL url) throws IOException{
        Properties newprops = new Properties ();
        InputStream in = url.openStream ();
        newprops.load (in);
        in.close ();
        return newprops;
    }

    public static Properties getPowerDirectorProperties(){
        /**
         * if(powerdirectorProps == null) initPowerDirectorConfiguration(); return powerdirectorProps;
         **/

        return getProperties (TEBE_FILENAME);
    }
}
