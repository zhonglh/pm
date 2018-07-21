package com.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSONObject;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.HttpRestException;
import com.common.exceptions.PMException;
import com.common.utils.json.Jsonkit;

/**
 * @ClassName: ExceptionDeal
 * @Title:
 * @Description:异常处理
 * @Author:Hongli
 * @Since:2013-6-15上午11:49:49
 * @Version:1.0
 */
public class ErrorDeal {

    /**
     * @Title: getErrorMessage
     * @Description: 取异常描述
     * @Author: Hongli
     * @Since: 2013-6-15上午10:52:25
     * @param e
     * @return
     */
    public static String getErrorMessage(Throwable e){
        if (null == e) return null;
        String errorMessage = e.getMessage ();
        Throwable tw = e.getCause ();
        if (ComKit.isNull (errorMessage) && ComKit.isNotNull (tw)) errorMessage = tw.getMessage ();
        if (ComKit.isNotNull (errorMessage)) errorMessage = getErrorClassName (e).concat (":").concat (errorMessage);
        else errorMessage = getErrorClassName (e);
        return errorMessage;
    }

    /**
     * @Title: getErrorClassName
     * @Description: 取异常class name
     * @Author: Hongli
     * @Since: 2014年6月30日下午10:36:22
     * @param e
     * @return
     */
    public static String getErrorClassName(Throwable e){
        if (null == e) return null;
        String className = "";
        Throwable tw = e.getCause ();
        if (ComKit.isNull (tw)) className = e.getClass ().getName ();
        else {
            className = tw.getClass ().getName ();
        }
        return className;
    }

    /**
     * @Title: getStack
     * @Description: 取异常堆栈
     * @Author: Hongli
     * @Since: 2013-6-15上午10:52:41
     * @param e
     * @return
     */
    public static String getStack(Throwable e){
        if (null == e) return null;
        ByteArrayOutputStream buf = new ByteArrayOutputStream ();
        e.printStackTrace (new PrintWriter (buf,true));
        return buf.toString ();
    }

    public static String getErrorCode(Throwable e){
        if (e instanceof PMException) return ((PMException) e).getErrcode ();
        else return CommonErrorConstants.e999999.getCode ();
    }

    public static String getErrorDesc(Throwable e){
        if (e instanceof PMException) return ((PMException) e).getErrmsg ();
        else return getErrorMessage (e);
    }

    public static void checkResult(String result) throws HttpRestException{
        if (Jsonkit.isJson (result)) {
            Object obj = Jsonkit.String2JSON (result);
            if (obj instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject error = jsonObject.getJSONObject ("error");
                if (ComKit.isNotNull (error)) {
                    int errcode = error.getIntValue ("code");
                    if (errcode != 0) {
                        String errorMsg = error.getString ("message");
                        String errorStack = error.getString ("traceback");
                        if (ComKit.isNotNull (errorStack)) {
                            Throwable tb = new Throwable (errorMsg);
                            StackTraceElement[] stes = new StackTraceElement[] { new StackTraceElement ("","",errorStack,0) };
                            tb.setStackTrace (stes);
                            throw new HttpRestException (String.valueOf (errcode),errorMsg,tb);
                        } else {
                            throw new HttpRestException (String.valueOf (errcode),errorMsg);
                        }
                    }
                }

            }
        }
    }
}
