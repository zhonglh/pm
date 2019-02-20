package com.common.http.client;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.HttpRestException;
import com.common.exceptions.PMException;
import com.common.http.domain.Status;
import com.common.http.entity.HeaderEntity;
import com.common.http.entity.ParamEntity;
import com.common.http.token.TokenCache;
import com.common.utils.ComKit;
import com.common.utils.ErrorDeal;
import com.common.utils.json.Jsonkit;

/**
 * @ClassName: RestClient
 * @Title:
 * @Description:REST client 具体实现
 * @Author:Hongli
 * @Since:2013-6-15上午10:47:59
 * @Version:1.0
 */
public class RestClient extends Client {

    /**
     * @param url
     *            请求URL
     */
    public RestClient(String url, boolean isToken) {
        super (url, isToken);
    }

    /**
     * @param url
     *            请求URL
     * @param header
     *            请求属性
     * @param cre
     */
    public RestClient(String url, HeaderEntity header, boolean isToken) {
        super (url, header, isToken);
    }

    /**
     * @param url
     *            请求URL
     * @param header
     *            请求属性
     */
    public RestClient(String url, HeaderEntity header, ParamEntity param, boolean isToken) {
        super (url, header, param, isToken);
    }

    boolean isRetry = true;

    /**
     * @Title: rest
     * @Description: rest 请求
     * @Author: ZhongLiHong
     * @Since: 2013-6-15上午10:48:34
     * @param parameter
     * @param restMethod
     * @return String
     * @throws MiaoYuanException
     */
    private String rest(String parameter,String restMethod){
        String result = "";
        try {
            con.setRequestMethod (restMethod);
            logger.debug ("Request :" + con.getURL ().toString () + " | Method :" + restMethod);
            Map<String, List<String>> requestMap = con.getRequestProperties ();
            for ( Map.Entry<String, List<String>> entry : requestMap.entrySet () ) {
                logger.debug (entry.getKey () + " ： " + entry.getValue ());
            }

            // 如果请求方法为PUT,POST和DELETE设置DoOutput为真
            if (!HttpConstants.METHOD_GET.equals (restMethod)) {
                con.setUseCaches (false);
                logger.debug ("Parameter: " + parameter);
                con.setDoOutput (true);
                con.setDoInput (true);
                if (!HttpConstants.METHOD_DELETE.equals (restMethod)) { // 请求方法为PUT或POST时执行
                    OutputStream os = null;
                    os = con.getOutputStream ();
                    os.write (parameter.getBytes ("UTF-8"));
                    os.close ();
                }
            }

            status = Status.valueOf (responseCode ());
            InputStream in = null;
            if (status.isSuccess ()) {
                in = con.getInputStream ();
            } else {
                in = con.getErrorStream ();
            }
            String contextType = con.getContentType ().toLowerCase ();
            if ("image/jpg".indexOf (contextType) > -1) {// 图片
                savePic (in);
            } else {
                if (null != in) {
                    BufferedReader br = new BufferedReader (new InputStreamReader (in,getCharSet (contextType)));
                    StringBuffer buffer = new StringBuffer ();
                    String line = "";
                    while ((line = br.readLine ()) != null) {
                        buffer.append (line);
                    }
                    in.close ();
                    result = buffer.toString ();
                }
            }
            if (!this.getStatus ().isSuccess ()) {
                if (getStatus ().getCode () == 401) {
                    try {
                        if (isToken) TokenCache.me ().getOne ();
                    } catch (Exception e) {
                        logger.error ("", e);
                    }
                }
                if (Jsonkit.isJson (result)) {
                    ErrorDeal.checkResult (result);
                }
                String errorDesc = getStatus ().getDescription ();
                if (this.getStatus ().getCode () == -1) {
                    errorDesc = CommonErrorConstants.e999999.getMsg ();
                } else {
                    if (ComKit.isNotNull (result)) errorDesc = result;
                }
                throw new HttpRestException ("http-" + this.getStatus ().getCode (),errorDesc);

            }
        } catch (PMException e1) {
            throw e1;
        } catch (Exception e1) {
            throw new RuntimeException (e1);
        } finally {
            if (null != con) con.disconnect ();
        }
        logger.debug ("Result: " + result);
        return result;
    }

    @Override
    public String get(){
        return rest (null, HttpConstants.METHOD_GET);
    }

    @Override
    public String delete(){
        return rest (null, HttpConstants.METHOD_DELETE);
    }

    @Override
    public String put(Object obj){
        return rest ((null != obj) ? (String) obj : "", HttpConstants.METHOD_PUT);
    }

    @Override
    public String post(Object obj){
        return rest ((null != obj) ? (String) obj : "", HttpConstants.METHOD_POST);
    }

    @Override
    public int responseCode(){
        try {
            return con.getResponseCode ();
        } catch (Exception e) {
            String errmsg = ErrorDeal.getErrorMessage (e);
            status = new Status (Status.CLIENT_ERROR_BAD_REQUEST,e.getCause (),errmsg);
            throw new HttpRestException (CommonErrorConstants.e999999.getMsg (),errmsg,e);

        }
    }

    private String getCharSet(String contextType){
        if (StringUtils.isBlank (contextType)) { return "utf-8"; }
        String[] contextTypes = contextType.split (";");
        if (contextTypes.length > 0) {
            for ( String string : contextTypes ) {
                if (string.trim ().startsWith ("charset")) return string.trim ().split ("=")[1].trim ();
            }
        }
        return "utf-8";
    }

    private String savePic(InputStream inputStream){
        byte[] data = new byte[1024];
        int len = 0;
        String filepath = "g:\\qiyi\\ok.png";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream (filepath);
            while ((len = inputStream.read (data)) != -1) {
                fileOutputStream.write (data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
        return filepath;
    }

    public static void main(String[] args){
        String url = RestApiConstants.python_polling_log.getUrl ();
        // python_polling_log=/c/task/{taskId}/log?startline={startLine}
        url = url.replace ("{taskId}", "0700f403-37a9-47ff-9e45-ccd8f82e704a");
        url = url.replace ("{startLine}", "0");
        Client client = new RestClient (url,true);
        String result = client.get ();
        System.out.println (result);
    }
}
