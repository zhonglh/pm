package com.common.http.client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.enums.DeployEngineConstants;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.http.domain.Status;
import com.common.http.entity.HeaderEntity;
import com.common.http.entity.ParamEntity;
import com.common.http.token.TokenCache;
import com.common.utils.ErrorDeal;

/**
 * @param <T>
 * @ClassName: Client
 * @Title:
 * @Description: http client
 * @Author:Hongli
 * @Since:2013-6-14下午6:02:03
 * @Version:1.0
 */
abstract public class Client {

    protected String       url;
    protected HeaderEntity header;
    protected ParamEntity  param;
    protected boolean      isToken;

    public Logger          logger = LoggerFactory.getLogger (Client.class.getName ());

    /**
     * REST GET
     * 
     * @Title: get
     * @Description: REST GET
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:41:33
     * @return String
     */
    public abstract String get();

    /**
     * REST DELETE
     * 
     * @Title: delete
     * @Description: REST DELETE
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:41:46
     * @return String
     */
    public abstract String delete();

    /**
     * REST PUT
     * 
     * @Title: put
     * @Description: REST PUT
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:42:01
     * @param obj
     * @return String
     */
    public abstract String put(Object obj);

    /**
     * REST POST
     * 
     * @Title: post
     * @Description: REST POST
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:42:13
     * @param obj
     * @return String
     */
    public abstract String post(Object obj);

    /**
     * HTTP 请求返回码
     * 
     * @Title: responseCode
     * @Description: HTTP 请求返回码
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:42:48
     * @return
     */
    public abstract int responseCode();

    protected HttpURLConnection con    = null;

    /**
     * @Fields status : 状态码,描述,参考RESTLET Status
     */
    protected Status            status = Status.SUCCESS_OK;

    private URL                 rooturl;

    protected Client(String url, boolean isToken) {
        this (url, null, isToken);
    }

    protected Client(String url, HeaderEntity header, boolean isToken) {
        this (url, header, null, isToken);
    }

    protected Client(String url, HeaderEntity header, ParamEntity param, boolean isToken) {
        this.isToken = isToken;
        this.header = header;
        this.param = param;
        this.url = url;
        try {
            String path = setParams (url, param);
            rooturl = new URL (path);
            if ("https".equals (rooturl.getProtocol ())) {
                setDefaultCert ();
                con = (HttpsURLConnection) rooturl.openConnection ();
            } else {
                con = (HttpURLConnection) rooturl.openConnection ();
            }
            if (isToken) {
                String token = "";
                if (TokenCache.me ().checkToken ()) {
                    token = TokenCache.me ().getRemoteToken ().getToken ();
                } else {
                    token = TokenCache.me ().getOne ();
                }
                if (header == null) {
                    header = new HeaderEntity ();
                }
                header.remove (HttpConstants.X_AUTH_USER);
                header.remove (HttpConstants.X_AUTH_PASSWD);
                header.put (HttpConstants.X_AUTH_TOKEN, token);
            }
            setHeader (header);
        } catch (PMException e) {
            if (null != con) con.disconnect ();
            throw e;
        } catch (Exception e) {
            if (null != con) con.disconnect ();
            throw new PMException (CommonErrorConstants.e222001.getCode (),ErrorDeal.getErrorMessage (e),e);
        }
        con.setRequestProperty ("Accept-Charset", "utf-8");
        con.setReadTimeout (HttpConstants.TIMEOUT);
        System.setProperty ("jsse.enableSNIExtension", "false");
    }

    /**
     * @Title: setHeader
     * @Description:设置 请求头
     * @Author: ZhongLiHong
     * @Since: 2013-6-14下午5:07:16
     * @param header
     */
    private void setHeader(HeaderEntity header){
        if (null != con) {
            boolean isContainsXcat = false;
            if (null == header) { return; }
            Iterator<Map.Entry<String, String>> pairs = header.entrySet ().iterator ();
            while (pairs.hasNext ()) {
                Map.Entry<String, String> pair = pairs.next ();
                if (pair.getKey ().toLowerCase ().equalsIgnoreCase (DeployEngineConstants.deploy_engine_ip.getKey ())) isContainsXcat = true;
                con.setRequestProperty (pair.getKey (), pair.getValue ());
            }
            if (isToken && !isContainsXcat) {
                con.setRequestProperty (DeployEngineConstants.deploy_engine_ip.getKey (), DeployEngineConstants.deploy_engine_ip.getValue ());
                con.setRequestProperty (DeployEngineConstants.deploy_engine_user.getKey (), DeployEngineConstants.deploy_engine_user.getValue ());
                con.setRequestProperty (DeployEngineConstants.deploy_engine_paaswd.getKey (), DeployEngineConstants.deploy_engine_paaswd.getValue ());
            }
        } else {
            // throw new PDException (ExceptionConstants.CLIENT_VALIDATION,"Connection is empty");
        }
    }

    private String setParams(String url,ParamEntity params){
        if (null == params || params.size () == 0) { return url; }
        Map<String, String> sorted = new TreeMap<String, String> (String.CASE_INSENSITIVE_ORDER);
        sorted.putAll (params);
        Iterator<Map.Entry<String, String>> pairs = sorted.entrySet ().iterator ();
        while (pairs.hasNext ()) {
            String[] strs = url.split ("\\?");
            Map.Entry<String, String> pair = pairs.next ();
            if (strs.length > 1) {
                if (strs[1].indexOf (pair.getKey () + "=") > -1) {
                    continue;
                }
                url = url + "&" + pair.getKey () + "=" + pair.getValue ();
            } else {
                url = url + "?" + pair.getKey () + "=" + pair.getValue ();
            }
        }
        return url;
    }

    /**
     * @Title: close
     * @Description: 关闭连接
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:46:42
     */
    public void close(){
        if (null != con) {
            con.disconnect ();
        }
    }

    /**
     * @Title: setTimeout
     * @Description:设置超时
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午11:11:20
     */
    public void setTimeout(int timeout){
        if (null != con) {
            if (timeout > HttpConstants.TIMEOUT) con.setReadTimeout (timeout);
            else con.setReadTimeout (HttpConstants.TIMEOUT);
        }
    }

    /**
     * @Title: getStatus
     * @Description: 取执行状态
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:46:49
     * @return
     */
    public Status getStatus(){
        return status;
    }

    private void trustAllHttpsCertificates() throws Exception{
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM ();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance ("SSL");
        sc.init (null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory (sc.getSocketFactory ());

    }

    public class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers(){
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs){
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs){
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs,String authType) throws java.security.cert.CertificateException{
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs,String authType) throws java.security.cert.CertificateException{
            return;
        }
    }

    private void setDefaultCert() throws Exception{
        HostnameVerifier hv = new HostnameVerifier () {

            public boolean verify(String urlHostName,SSLSession session){
                System.out.println ("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost ());
                return true;
            }
        };
        trustAllHttpsCertificates ();
        HttpsURLConnection.setDefaultHostnameVerifier (hv);
    }
}
