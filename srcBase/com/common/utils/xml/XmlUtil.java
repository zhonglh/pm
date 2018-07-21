package com.common.utils.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.common.utils.ComKit;
//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XmlUtil {

    private static final Logger logger = LoggerFactory.getLogger (XmlUtil.class);

    public static String toXml(Object obj){
        return replaceEncod (getXstream ().toXML (obj));
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> xml2List(String xml,Class<T> classes){
        XStream xs = getXstream ();
        xs.alias (StringUtils.uncapitalize (classes.getSimpleName ()), classes);
        return (List<T>) xs.fromXML (xml);
    }

    public static File toXmlFile(Object obj,String fileName) throws IOException{
        return toXmlFile (obj, fileName, "UTF-8");
    }

    public static File toXmlFile(Object obj,String fileName,String charSetName) throws IOException{
        File file = new File (fileName);
        if (!file.exists ()) {
            File dir = file.getParentFile ();
            if (!dir.exists ()) if (!dir.mkdirs ()) throw new IOException ("create directory '" + dir.getPath () + "' failed!");
            if (!file.createNewFile ()) throw new IOException ("create file '" + fileName + "' failed!");
        }
        OutputStreamWriter out = new OutputStreamWriter (new FileOutputStream (fileName),charSetName);
        getXstream ().toXML (obj, out);
        return new File (fileName);
    }

    public static Object toOjbect(String xml){
        return getXstream ().fromXML (xml);
    }

    public static Object toOjbect(File file) throws FileNotFoundException{
        return getXstream ().fromXML (new FileReader (file));
    }

    /**
     * 获取XML解析器，（注：考虑多线程中，静态方法可能存在的问题）
     * 
     * @return
     */
    public static XStream getXstream(){
        QNameMap qmap = new QNameMap ();
        StaxDriver staxDriver = new StaxDriver (qmap);
        XStream xstream = new XStream (staxDriver);
        xstream.autodetectAnnotations (true);
        return xstream;
    }

    public static Map<?, ?> parse(InputStream is){
        Map<?, ?> map = new HashMap<Object, Object> ();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
            factory.setValidating (false);
            DocumentBuilder builder = factory.newDocumentBuilder ();
            Document document = builder.parse (is);
            if (document != null) map = getNodeBean (document.getFirstChild ());
            return map;
        } catch (ParserConfigurationException e) {
            logger.error ("", e);
        } catch (SAXException e) {
            logger.error ("", e);
        } catch (IOException e) {
            logger.error ("", e);
        }

        return map;
    }

    private static Map<?, ?> getNodeBean(Node parent){
        Map<Object, Object> rtn = new HashMap<Object, Object> ();

        if (parent != null) {
            Map<Object, Object> attrMap = new HashMap<Object, Object> ();
            if (parent.hasAttributes ()) {
                NamedNodeMap attrs = parent.getAttributes ();
                for ( int j = 0 ; j < attrs.getLength () ; j++ ) {
                    Node attr = attrs.item (j);
                    attr.getNodeName ();
                    attr.getNodeValue ();
                    attrMap.put (attr.getNodeName (), attr.getNodeValue ());
                }
            }

            rtn.put ("tagName", parent.getNodeName ());
            rtn.put ("attr", attrMap);

            NodeList nodeList = parent.getChildNodes ();
            if (nodeList != null) {
                List<Object> children = new ArrayList<Object> ();
                for ( int i = 0 ; i < nodeList.getLength () ; i++ ) {
                    Node child = nodeList.item (i);
                    if (child.getNodeType () == Node.ELEMENT_NODE) children.add (getNodeBean (child));
                }

                rtn.put ("children", children);
            }
        }

        return rtn;
    }

    public static String replaceEncod(String str){
        if (StringUtils.isNotBlank (str)) {
            String xml = str.replace ("<?xml version=\"1.0\" ?>", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            return xml.replace ("__", "_");
        }
        return null;
    }

    public static String object2XML(Object obj,Class<?> classes){
        XStream xs = getXstream ();
        xs.alias (getAlias (classes), classes);
        return replaceEncod (xs.toXML (obj));

    }

    public static String getAlias(Class<?> classes){

        String alias = "";

        try {
            final XStreamAlias aliasAnnotation = classes.getAnnotation (XStreamAlias.class);
            if (aliasAnnotation != null) {
                alias = aliasAnnotation.value ();
                return alias;
            }
        } catch (Exception e) {
            logger.error (e.getMessage (), e);
        }

        try {
            alias = getFirstChar2Lower (classes.getSimpleName ());

            return alias;
        } catch (Exception e) {
            logger.error (e.getMessage (), e);
        }

        return null;
    }

    public static String getFirstChar2Lower(String str){
        if (ComKit.isNotNull (str)) return str.substring (0, 1).toLowerCase () + str.substring (1, str.length ());
        return str;
    }

    /**
     * 格式化xml
     * 
     * @param unformattedXml
     * @return
     */
    public static String format(String unformattedXml){
        try {
            return unformattedXml.toString ();
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T xml2Object(String xml,List<Class<T>> classes){
        XStream xs = getXstream ();
        for ( Class<T> class1 : classes )
            xs.alias (getAlias (class1), class1);
        return (T) xs.fromXML (xml);
    }

    @SuppressWarnings("unchecked")
    public static <T> T xml2Object(String xml,Class<T> classes){
        XStream xs = getXstream ();

        xs.alias (getAlias (classes), classes);
        return (T) xs.fromXML (xml);
    }

}
