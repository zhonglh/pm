package com.common.http.apache.bean;

import java.util.HashMap;
import java.util.Map;

import com.common.utils.json.Jsonkit;

/**
 * @ClassName: HeaderBean
 * @Title:
 * @Description:
 * @Author:liweixi
 * @Since:2014年3月25日下午4:03:21
 * @Version:1.0
 */
public class Headers {

    private Map<String, String> headers;

    public Headers() {
        headers = new HashMap<String, String> ();
    }

    public Map<String, String> getHeaders(){
        return headers;
    }

    public void addHeaders(Map<String, String> headers){
        headers.putAll (headers);
    }

    public void addHeader(String key,String value){
        headers.put (key, value);
    }

    @Override
    public String toString(){
        return Jsonkit.object2JsonString (headers);
    }
}
