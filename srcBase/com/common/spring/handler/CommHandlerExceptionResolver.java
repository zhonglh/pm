package com.common.spring.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.ComKit;
import com.common.utils.ErrorDeal;
import com.common.utils.StringFormatKit;
import com.common.utils.json.Jsonkit;

public class CommHandlerExceptionResolver implements HandlerExceptionResolver {

    protected static Logger logger = LoggerFactory.getLogger (CommHandlerExceptionResolver.class);

    public CommHandlerExceptionResolver() {
        super ();
    }

    public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
        logger.error ("Common Exception Handler Catch Unresolved Error: ", ex);
        Map<String, String> map = new HashMap<String, String> ();
        map.put ("flag", "1");
        map.put ("msg", "success");
        String errorCode = "";
        String errorDesc = "";

        if(ex instanceof PMException){
        	PMException excepton = (PMException)ex;
    		errorCode = excepton.getErrcode();
    		errorDesc = excepton.getErrmsg();
        }else {
			errorCode = CommonErrorConstants.e222001.getCode ();
			errorDesc = ErrorDeal.getErrorMessage (ex);
        }
    
        
        map.put ("statusCode", "300");
        map.put ("flag", errorCode);
		map.put ("message", errorDesc);
		map.put ("detail", ErrorDeal.getStack (ex));
        
		writeResJson (response, map);
		return null;
	  
    }

    protected void writeResJson(HttpServletResponse res,Object obj){
        res.setContentType ("application/json; charset=utf-8");
        res.setCharacterEncoding ("utf-8");
        PrintWriter pw = null;
        try {
            pw = res.getWriter ();
            // String str = JSONUtil.getJSONString (obj);
            if (obj instanceof String) {
                pw.write (obj.toString ());
            } else {
                String str = Jsonkit.object2JsonString (obj, true);
                // logger.debug ("\n" + Jsonkit.formatJson (str, Jsonkit.TAB));
                // str = toCamelCase (str);
                logger.debug ("\n" + StringFormatKit.formatJson (str, StringFormatKit.TAB));
                pw.write (str);
            }
        } catch (IOException e) {
            logger.error (e.getMessage (), e);
        } finally {
            if (ComKit.isNotNull (pw)) {
                pw.flush ();
                pw.close ();
            }
        }
    }
}