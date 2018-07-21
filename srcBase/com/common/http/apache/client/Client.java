package com.common.http.apache.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.exceptions.ExceptionCode;
import com.common.exceptions.UtilException;
import com.common.http.apache.bean.Headers;

/**
 * HttpClient连接服务器获取信息(httpclient4.2.1版本)
 * 
 * @author liweixi
 * @version 1.0
 */
public class Client {

    public static Logger                          logger                = LoggerFactory.getLogger (Client.class);

    private static final Charset                   CHARSET               = Consts.UTF_8;
    private static final String                   CONTENT_TYPE          = HTTP.CONTENT_TYPE;
    private static final String                   X_AUTH_USER           = "X-Auth-User";
    private static final String                   X_AUTH_PASSWD         = "X-Auth-Passwd";
    /**
     * 最大连接超时时间
     */
    public static int                             REQUEST_TIMEOUT       = 60 * 1000*5;

    /**
     * 最大等待数据时间
     */
    public static int                             SO_TIMEOUT            = 60 * 1000*5;

    /**
     * 客户机总并行最大数
     */
    public static int                             MAX_TOTAL_CONNECTIONS = 800;

    /**
     * 路由最大连接数
     */
    public static int                             MAX_ROUTE_CONNECTIONS = 400;

    private static HttpParams                     httpParams;

    private static PoolingClientConnectionManager poolingClientConnectionManager;
    /**
     * 适合多线程的HttpClient,用httpClient4.2.1实现
     */
    static {
        // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
        httpParams = new BasicHttpParams ();
        HttpProtocolParams.setVersion (httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent (httpParams, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue (httpParams, true);
        // 设置连接超时时间
        httpParams.setParameter (CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);
        // 设置等待数据时间
        httpParams.setParameter (CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

        // 设置访问协议
        SchemeRegistry schreg = new SchemeRegistry ();
        schreg.register (new Scheme ("http",80,PlainSocketFactory.getSocketFactory ()));
        schreg.register (new Scheme ("https",443,SSLSocketFactory.getSocketFactory ()));

        // 多连接的线程安全的管理器
        poolingClientConnectionManager = new PoolingClientConnectionManager (schreg);
        // 每个主机的最大并行链接数
        poolingClientConnectionManager.setDefaultMaxPerRoute (MAX_ROUTE_CONNECTIONS);
        // 客户端总并行链接最大数
        poolingClientConnectionManager.setMaxTotal (MAX_TOTAL_CONNECTIONS);

    }

    public static HttpClient getHttpClient(){
        return new DefaultHttpClient (poolingClientConnectionManager,httpParams);
    }

    public static String getToken(String url,String userId,String password) throws Exception{
        try {
            logger.debug ("client url is >> " + url);
            logger.debug ("user is >>" + userId);
            logger.debug ("password is >>" + password);
            /** 创建get请求 */
            HttpGet request = new HttpGet (url);
            request.addHeader (X_AUTH_USER, userId);
            request.addHeader (X_AUTH_PASSWD, password);
            request.addHeader (CONTENT_TYPE, "application/json");
            /** 发送请求 */
            HttpClient client = getHttpClient ();
            HttpResponse response = client.execute (request);
            HttpEntity resEntity = response.getEntity ();
            if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK) {
                return (resEntity == null) ? null : EntityUtils.toString (resEntity, CHARSET);
            } else {
                throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,EntityUtils.toString (resEntity, CHARSET));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_CHARSET_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (ClientProtocolException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_TIMEOUT_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_IO_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (Exception e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,e.getMessage (),e.getCause ());
        }
    }

    public static String get(String url,Headers headers,NameValuePair... params) throws UtilException{
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair> ();
            for ( NameValuePair p : params ) {
                formparams.add (p);
            }
            if (null != formparams && formparams.size () > 0) {
                String param = URLEncodedUtils.format (formparams, CHARSET);
                url += "?" + param;
            }
            logger.debug ("client url is >> " + url);
            logger.debug ("headers is >> " + headers);
            /** 创建get请求 */
            HttpGet request = new HttpGet (url);
            if (headers != null) {
                Map<String, String> headersMap = headers.getHeaders ();
                if (headersMap != null && headersMap.size () > 0) {
                    for ( Map.Entry<String, String> header : headersMap.entrySet () ) {
                        request.addHeader (header.getKey (), header.getValue ());
                    }
                }
            }
            // request.addHeader ("X-Auth-Token", tokenId);
            // request.addHeader("X-Auth-User", "admin");
            // request.addHeader("X-Auth-Passwd", "admin");
            // request.addHeader (CONTENT_TYPE, "application/json");
            /** 发送请求 */
            HttpClient client = getHttpClient ();
            HttpResponse response = client.execute (request);
            HttpEntity resEntity = response.getEntity ();
            if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK) {
                return (resEntity == null) ? null : EntityUtils.toString (resEntity, CHARSET);
            } else if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_UNAUTHORIZED) {
                throw new UtilException (ExceptionCode.PD3_TOKEN_EMPTY,EntityUtils.toString (resEntity, CHARSET));
            } else {
                throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,EntityUtils.toString (resEntity, CHARSET));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_CHARSET_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (ClientProtocolException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_TIMEOUT_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_IO_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (UtilException e) {
            logger.error (e.getMessage (),e);
            throw e;
        } catch (Exception e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,e.getMessage (),e.getCause ());
        }
    }

    public static String post(String url,Headers headers,String params) throws UtilException{
        try {
            logger.debug ("client url is >> " + url);
            logger.debug ("params is >> " + params);
            logger.debug ("headers is >> " + headers);
            /** 创建POST请求 */
            HttpPost request = new HttpPost (url);
            // request.addHeader ("Content-Type", "application/json");
            if (headers != null) {
                Map<String, String> headersMap = headers.getHeaders ();
                if (headersMap != null && headersMap.size () > 0) {
                    for ( Map.Entry<String, String> header : headersMap.entrySet () ) {
                        request.addHeader (header.getKey (), header.getValue ());
                    }
                }
            }
            // request.addHeader ("X-Auth-Token", tokenId);
            // request.addHeader("X-Auth-User", "admin");
            // request.addHeader("X-Auth-Passwd", "admin");
            request.setEntity (new StringEntity (params,CHARSET));
            /** 发送请求 */
            HttpClient client = getHttpClient ();
            HttpResponse response = client.execute (request);
            if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK || response.getStatusLine ().getStatusCode () == HttpStatus.SC_ACCEPTED
                    || response.getStatusLine ().getStatusCode () == HttpStatus.SC_CREATED || response.getStatusLine ().getStatusCode () == HttpStatus.SC_NO_CONTENT) {
                HttpEntity resEntity = response.getEntity ();
                if (resEntity != null) {
                    String entityStr = EntityUtils.toString (resEntity, CHARSET);
                    if (entityStr.length () > 0) {
                        return entityStr;
                    } else {
                        Header head = response.getLastHeader ("Location");
                        if (null != head) { return head.getValue (); }
                    }
                }
            } else if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_UNAUTHORIZED) {
                throw new UtilException (ExceptionCode.PD3_TOKEN_EMPTY,EntityUtils.toString (response.getEntity ()));
            } else {
                logger.error ("status << " + response.getStatusLine ().getStatusCode ());
                String entityString = EntityUtils.toString (response.getEntity ());
                logger.error ("entityString << " + entityString);
                throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,entityString);

            }
        } catch (UnsupportedEncodingException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_CHARSET_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (ClientProtocolException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_TIMEOUT_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_IO_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (UtilException e) {
            logger.error (e.getMessage (),e);
            throw e;
        } catch (Exception e) {
            throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,e.getMessage (),e.getCause ());
        }
        return null;
    }

    public static String put(String url,Headers headers,String params) throws UtilException{
        try {
            logger.debug ("client url is >> " + url);
            logger.debug ("params is >> " + params);
            logger.debug ("headers is >> " + headers);
            /** 创建POST请求 */
            HttpPut request = new HttpPut (url);
            // request.addHeader ("Content-Type", "application/json");
            if (headers != null) {
                Map<String, String> headersMap = headers.getHeaders ();
                if (headersMap != null && headersMap.size () > 0) {
                    for ( Map.Entry<String, String> header : headersMap.entrySet () ) {
                        request.addHeader (header.getKey (), header.getValue ());
                    }
                }
            }
            // request.addHeader ("X-Auth-Token", tokenId);
            // request.addHeader("X-Auth-User", "admin");
            // request.addHeader("X-Auth-Passwd", "admin");
            request.setEntity (new StringEntity (params,CHARSET));
            /** 发送请求 */
            HttpClient client = getHttpClient ();
            HttpResponse response = client.execute (request);
            if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK || response.getStatusLine ().getStatusCode () == HttpStatus.SC_ACCEPTED
                    || response.getStatusLine ().getStatusCode () == HttpStatus.SC_CREATED || response.getStatusLine ().getStatusCode () == HttpStatus.SC_NO_CONTENT) {
                HttpEntity resEntity = response.getEntity ();
                if (resEntity != null) {
                    String entityStr = EntityUtils.toString (resEntity, CHARSET);
                    if (entityStr.length () > 0) {
                        return entityStr;
                    } else {
                        Header head = response.getLastHeader ("Location");
                        if (null != head) { return head.getValue (); }
                    }
                }
            } else if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_UNAUTHORIZED) {
                throw new UtilException (ExceptionCode.PD3_TOKEN_EMPTY,EntityUtils.toString (response.getEntity ()));
            } else {
                logger.error ("status << " + response.getStatusLine ().getStatusCode ());
                String entityString = EntityUtils.toString (response.getEntity ());
                logger.error ("entityString << " + entityString);
                throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,entityString);

            }
        } catch (UnsupportedEncodingException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_CHARSET_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (ClientProtocolException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_TIMEOUT_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_IO_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (UtilException e) {
            logger.error (e.getMessage (),e);
            throw e;
        } catch (Exception e) {
            throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,e.getMessage (),e.getCause ());
        }
        return null;
    }

    public static void delete(String url,Headers headers) throws UtilException{
        try {
            logger.debug ("client url is >> " + url);
            logger.debug ("headers is >> " + headers);
            /** 创建DELETE请求 */
            HttpDelete request = new HttpDelete (url);
            // request.addHeader ("Content-Type", "application/json");
            if (headers != null) {
                Map<String, String> headersMap = headers.getHeaders ();
                if (headersMap != null && headersMap.size () > 0) {
                    for ( Map.Entry<String, String> header : headersMap.entrySet () ) {
                        request.addHeader (header.getKey (), header.getValue ());
                    }
                }
            }
            // request.addHeader ("X-Auth-Token", tokenId);
            // request.addHeader("X-Auth-User", "admin");
            // request.addHeader("X-Auth-Passwd", "admin");
            /** 发送请求 */
            HttpClient client = getHttpClient ();
            HttpResponse response = client.execute (request);
            logger.debug ("delete status is << " + response.getStatusLine ().getStatusCode ());
            if (response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK || response.getStatusLine ().getStatusCode () == HttpStatus.SC_ACCEPTED
                    || response.getStatusLine ().getStatusCode () == HttpStatus.SC_CREATED || response.getStatusLine ().getStatusCode () == HttpStatus.SC_NO_CONTENT) {} else if (response
                    .getStatusLine ().getStatusCode () == HttpStatus.SC_UNAUTHORIZED) {
                throw new UtilException (ExceptionCode.PD3_TOKEN_EMPTY,EntityUtils.toString (response.getEntity ()));
            } else {
                HttpEntity resEntity = response.getEntity ();
                if (resEntity != null) {
                    String exceptionStr = EntityUtils.toString (resEntity, CHARSET);
                    throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,exceptionStr);
                } else {
                    throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,response.getStatusLine ().getStatusCode () + "");
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_CHARSET_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (ClientProtocolException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_TIMEOUT_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
            throw new UtilException (ExceptionCode.PD3_IO_EXCEPTION,e.getMessage (),e.getCause ());
        } catch (UtilException e) {
            logger.error (e.getMessage (),e);
            throw e;
        } catch (Exception e) {
            logger.error ("delete << " + e);
            throw new UtilException (ExceptionCode.PD3_INTERNAL_EXCEPTION,e.getMessage (),e.getCause ());
        }
    }
}
