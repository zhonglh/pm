package com.common.http.token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.http.client.RestApiConstants;
import com.common.http.client.RestClient;
import com.common.http.entity.HeaderEntity;
import com.common.http.entity.RemoteTokenInfo;
import com.common.utils.DateKit;
import com.common.utils.ErrorDeal;
import com.common.utils.StringKit;
import com.common.utils.json.Jsonkit;

public class TokenCache {

    private static Logger logger = LoggerFactory.getLogger (TokenCache.class);

    private static class TokenCacheHolder {

        static TokenCache instance = new TokenCache ();
    }

    public static TokenCache me(){
        return TokenCacheHolder.instance;
    }

    private RemoteTokenInfo remoteToken;

    public TokenCache() {
        remoteToken = new RemoteTokenInfo ();
    }

    private static final String TOKEN_STR   = "id";

    private static final String EXPIRES_STR = "expires";

    public String getOne() throws Exception{
        String user = RestApiConstants.python_token_user.getValue ();
        String pwd = RestApiConstants.python_token_pwd.getValue ();
        String url = RestApiConstants.python_get_token.getUrl ();
        logger.info ("user:" + user + "password:" + pwd);
        String token = null;
        int expireSec = 0;
        synchronized (remoteToken) {
            try {
                HeaderEntity header = new HeaderEntity ();
                RestClient client = new RestClient (url,header,false);
                Map<String, String> map = new HashMap<String, String> ();
                map.put ("userName", user);
                map.put ("password", pwd);
                String tokenInfo = client.post (Jsonkit.object2JsonString (map));
                JSONObject obj = JSONObject.parseObject (tokenInfo);
                JSONObject _token = obj.getJSONObject ("token");
                token = _token.getString (TOKEN_STR);
                expireSec = _token.getIntValue (EXPIRES_STR);
                remoteToken.setToken (token);
                remoteToken.setExpireDate (DateKit.nSecAfterNowDate (expireSec));
            } catch (PMException e) {
                logger.error (e.getMessage (), e);
                throw e;
            } catch (Exception e) {
                logger.error (e.getMessage (), e);
                throw new PMException (CommonErrorConstants.e999999.getMsg (),ErrorDeal.getErrorMessage (e),e);
            }
        }
        return token;
    }

    public boolean checkToken(){
        boolean res = false;
        synchronized (remoteToken) {
            if (!StringKit.isBlank (remoteToken.getToken ())) if (DateKit.nMsBetweenTwoDate (remoteToken.getExpireDate (), new Date ()) < 0) {
                remoteToken.setExpireDate (null);
                remoteToken.setToken (null);
            } else {
                res = true;
            }
        }
        return res;
    }

    public RemoteTokenInfo getRemoteToken(){
        return remoteToken;
    }

}
