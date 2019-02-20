package com.common.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.domain.business.ParamExtend;
import com.pm.service.IParamExtendService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.cache.CacheManager;
import com.common.annotations.FiledBind;
import com.common.beans.MessageJson;
import com.common.exceptions.BaseException;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.ComKit;
import com.common.utils.ErrorDeal;
import com.common.utils.RefKit;
import com.common.utils.StringFormatKit;
import com.common.utils.ehcache.CacheKit;
import com.common.utils.encrypt.JCryKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.Params;
import com.pm.domain.business.Statistics;
import com.pm.domain.system.MarketSets;
import com.pm.domain.system.User;
import com.pm.service.IMarketSetsService;
import com.pm.service.IParamsService;
import com.pm.service.IRoleService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumAjaxDone;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.UserPermit;

/**
 * 日志操作 ，此ACTION为管理日志信息提供．
 * 
 * @author Riche's
 * @since 2012-07-05
 * @version1.0
 */
@Component
public class BaseAction {

    protected static Logger logger;

    public BaseAction() {
        logger = LoggerFactory.getLogger (getClass ());
    }

    private final static String DATA_NAME = "data";
    
    private final static String OPERATION_DONE = "common/ajaxDone"; 
    
    

	@Autowired
    protected IParamsService paramsService;
	
	@Autowired
	private IMarketSetsService marketSetsService;


    @Autowired
    protected IParamExtendService paramExtendService;


    /**
     * 获取扩展参数
     * @return
     */
    protected Map<String, ParamExtend> getParamExtendMap() {
        ParamExtend paramExtend = new ParamExtend();
        paramExtend.setGroup1(BusinessUtil.PARAM_GROUP_SALARY);
        List<ParamExtend> paramExtends = paramExtendService.queryAllParamExtend(paramExtend);
        Map<String, ParamExtend>	paramExtMap = new HashMap<String, ParamExtend>();
        if(paramExtends != null){
            for(ParamExtend temp : paramExtends){
                paramExtMap.put(temp.getGroup2(), temp);
            }
        }
        return paramExtMap;
    }
	
	
	public MarketSets getMarketSets(){
		MarketSets marketSets = CacheManager.getMarketSets();
		if(marketSets == null){
			marketSets = marketSetsService.getMarketSets(BusinessUtil.MARKET_SETS_ID);
			CacheManager.setMarketSets(marketSets);
		}
		return marketSets;
	}
	

	/**
	 * 计算税率
	 */
	public double computeTaxRate() {
		try{
			double taxRate = CacheManager.getTaxRate();
			if(taxRate == 0){
				Params params = new Params();
				params.setParam_key("tax.rate");
				List<Params> paramList = paramsService.queryAllParams(params);
				if(paramList != null && paramList.size() > 0){
					double tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
					CacheManager.setTaxRate(tax_rate);
					return tax_rate;
				}
			}else {
				return taxRate;
			}
		}catch(Exception e){
			
		}
		return 0;
	}	
    
    
	/**
	 * 计算税率
	 * @param searchStatistics
	 */
	public void computeTaxRate(Statistics searchStatistics) {
		try{
				searchStatistics.setTax_rate(computeTaxRate());			
		}catch(Exception e){
			
		}
	}	
	

	/**
	 * 获取数据库bin目录
	 * @return
	 */
	public String getDbBin() {	
			String dir = null;
			Params params = new Params();
			params.setParam_key("db.bin.dir");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				dir =  paramList.get(0).getParam_value();
			}
			System.out.println("数据库bin目录:"+dir);
			if(dir == null || dir.isEmpty())
				throw new RuntimeException("没有设置数据库BIN目录");
			else return dir;
		
	}	
    
    
    
    public List<String[]> getExcel(MultipartFile file,int colCount,HttpServletResponse res,HttpServletRequest request){
    	List<String[]> list = null;
		String fileType = null;
		try{
			
			fileType = FileKit.getFileNameType(file.getOriginalFilename());
			
			if(!BusinessUtil.EXCEL_TYPE.contains(fileType)) {
				throw new BaseException(this.ajaxForwardError(request, "请输入Excel文件！",true),null);
			}
		
			
			try{			
				list = ExcelRead.readExcel(file.getInputStream(), FileKit.isXlsx(fileType),  Config.startRow);
			}catch(Exception e){
				e.printStackTrace();
				throw new BaseException(this.ajaxForwardError(request, "该文件无法解析！！",true),null);
			}
			
			if(list == null || list.size() == 0) throw new BaseException(this.ajaxForwardError(request, "该文件内容为空！",true),null);
			
			int index = 0;
			for(String[] row : list){
				if(row.length<colCount) throw new BaseException(this.ajaxForwardError(request,"第"+(index+Config.startRow)+"行数据不全",true),null);
				index ++;
			}
		
		}catch(BaseException e){
			throw e;
		}
		
		return list;
    }
    
    

    public Map<String,List<String[]>> getExcelAllSheet(MultipartFile file,HttpServletResponse res,HttpServletRequest request){
    	Map<String,List<String[]>> map = null;
		String fileType = null;
		try{
			
			fileType = FileKit.getFileNameType(file.getOriginalFilename());
			
			if(!BusinessUtil.EXCEL_TYPE.contains(fileType)) {
				throw new BaseException(this.ajaxForwardError(request, "请输入Excel文件！",true),null);
			}
		
			
			try{			
				map = ExcelRead.readExcelAllSheet(file.getInputStream(), FileKit.isXlsx(fileType),  Config.startRow);
			}catch(Exception e){
				e.printStackTrace();
				throw new BaseException(this.ajaxForwardError(request, "该文件无法解析！！",true),null);
			}
			
			if(map == null || map.size() == 0) throw new BaseException(this.ajaxForwardError(request, "该文件内容为空！",true),null);
			
		
		}catch(BaseException e){
			throw e;
		}
		
		return map;
    }
    
    
    
    
    public UserPermit getUserPermit(HttpServletRequest request,IRoleService roleService, String permitId){
		User user = new User();
		user.setUser_id(PubMethod.getUser(request).getUser_id());
		user.setPermit_id(permitId);
		List<UserPermit> permitList = roleService.queryPermitsByUser(user);
		if(permitList == null || permitList.isEmpty()) return new UserPermit();
		else return permitList.get(0);
		
    }
    

	private String ajaxForward(HttpServletRequest request,int statusCode,String message,String rel,boolean closeCurrent) {
		request.setAttribute(EnumAjaxDone.statusCode.name(), statusCode);
		request.setAttribute(EnumAjaxDone.message.name(), message);
		request.setAttribute(EnumAjaxDone.rel.name(), rel);
		if(closeCurrent) request.setAttribute(EnumAjaxDone.callbackType.name(), "closeCurrent");
		return OPERATION_DONE;
	}
	

	private String ajaxForward(HttpServletRequest request,int statusCode,String message,String rel) {
		return ajaxForward(request,statusCode,message,rel,true);
	}
	

	private String ajaxForward(HttpServletRequest request,int statusCode,String message) {
		return ajaxForward(request,statusCode,message,"",true);
	}	
	
	

	protected String ajaxForwardConfirm(HttpServletRequest request , String message) {
		return ajaxForward(request,888,message);
	}
	
	
	protected String ajaxForwardSuccess(HttpServletRequest request) {
		return ajaxForward(request,200,"操作成功");
	}
	
	
	protected String ajaxForwardSuccess(HttpServletRequest request,String message,String rel,boolean closeCurrent) {
		return ajaxForward(request,200,message,rel,closeCurrent);
	}

	protected String ajaxForwardSuccess(HttpServletRequest request,String rel,boolean closeCurrent) {
		return ajaxForward(request,200,"操作成功",rel,closeCurrent);
	}
	


	protected String ajaxForwardSuccess(HttpServletRequest request,boolean closeCurrent) {
		return ajaxForward(request,200,"操作成功","",closeCurrent);
	}
	
	

	protected String ajaxForwardError(HttpServletRequest request,String message) {
		return ajaxForwardError(request,message,true);
	}
	


	protected String ajaxForwardError(HttpServletRequest request,String message,boolean closeCurrent) {
		return ajaxForward(request,300,message,"",closeCurrent);
	}	
	
	
	
	public static boolean isAjax(HttpServletRequest request) {
		if (request != null	&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")))
			return true;
		return false;
	}	
	

	
	
	
	
	
	public ModelAndView download(String sourceFile,  String realName,HttpServletRequest request,  
	            HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		downloadBaseUtil.download(  sourceFile,  realName,response,false);  		
		return null;  
	}  
	

	public ModelAndView download(File file,  String realName,HttpServletRequest request,  
	            HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		downloadBaseUtil.download(  file,  realName,response,false);  		
		return null;  
	}  
	
	

	
    /**
     * @Title: getDataflow
     * @Description: 取Rquest中的数据流
     * @Author: ZhongLiHong
     * @Since: 2014年3月18日下午2:34:22
     * @param req
     * @return
     */
    protected String getDataflow(HttpServletRequest req){
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer ("");
        try {
            in = req.getInputStream ();
            br = new BufferedReader (new InputStreamReader (in,getCharSet (req.getContentType ())));
            String line = "";
            while ((line = br.readLine ()) != null) {
                sb.append (line);
            }
        } catch (IOException e) {
            logger.error (e.getMessage (), e);
        } finally {
            if (null != in) try {
                in.close ();
            } catch (IOException e) {
                logger.error (e.getMessage (), e);
            }
            if (null != br) try {
                br.close ();
            } catch (IOException e) {
                logger.error (e.getMessage (), e);
            }
        }
        return sb.toString ();

    }

    /**
     * @Title: writeResJson
     * @Description: 写入JSON到HttpServletResponse
     * @Author: ZhongLiHong
     * @Since: 2014年3月18日下午2:35:54
     * @param req
     * @param res
     * @param obj
     */
    protected void writeResJson(HttpServletResponse res,Object obj){
        res.setContentType ("application/json; charset=utf-8");
        res.setCharacterEncoding ("utf-8");
        PrintWriter pw = null;
        try {
            pw = res.getWriter ();
            if (obj instanceof String) {
                pw.write (obj.toString ());
            } else {
                //String str = Jsonkit.object2JsonString (obj, false);
                String str = JSON.toJSONString(obj);

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

    private String getCharSet(String contextType){
        contextType = contextType.toLowerCase ();
        if (StringUtils.isBlank (contextType)) { return "utf-8"; }
        String[] contextTypes = contextType.split (";");
        if (contextTypes.length > 0) {
            for ( String string : contextTypes ) {
                if (string.trim ().startsWith ("charset")) return string.trim ().split ("=")[1].trim ();
            }
        }
        return "utf-8";
    }

    /**
     * @Title: getJson
     * @Description: 取JOSN 默认参数名为json
     * @Author: ZhongLiHong
     * @Since: 2014年3月18日下午3:29:07
     * @param req
     * @return
     */
    protected String getJson(HttpServletRequest req){
        return getJson (req, DATA_NAME);
    }

    /**
     * @Title: getJson
     * @Description: 取JOSN
     * @Author: ZhongLiHong
     * @Since: 2014年3月18日下午3:29:20
     * @param req
     * @param dataName
     *            参数名
     * @return
     */
    protected String getJson(HttpServletRequest req,String dataName){
        String json = req.getParameter (dataName);
        logger.debug ("\n" + StringFormatKit.formatJson (json, StringFormatKit.TAB));
        return json;
    }

    /**
     * @Title: getLocale
     * @Description: 取国际化语言
     * @Author: ZhongLiHong
     * @Since: 2014年3月18日下午5:29:21
     * @param req
     * @return
     */
    protected Locale getLocale(HttpServletRequest req){
        return RequestContextUtils.getLocale (req);
        // return (Locale) req.getSession ().getAttribute (SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    }

    /**
     * @Title: getMsg
     * @Description: 取国际化
     * @Author: ZhongLiHong
     * @Since: 2014年3月18日下午5:31:54
     * @param key
     * @param request
     * @return
     */

    protected String getMsg(String key,HttpServletRequest request,String... args){
        return SpringContextUtil.getApplicationContext ().getMessage (key, args, getLocale (request));
    }

    /**
     * @Title: converCondition2Bean
     * @Description: 转换前台条件到相应的java bean中
     * @Author: ZhongLiHong
     * @Since: 2014年5月7日上午11:10:50
     * @param condition
     * @param obj
     */
    public void converCondition2Bean(JSONObject condition,Object obj){
        try {
            Field[] fileds = RefKit.getField (obj);
            if (null != fileds) for ( Field field : fileds ) {
                FiledBind fb = field.getAnnotation (FiledBind.class);
                Object conval = null;
                if (ComKit.isNotNull (fb)) {
                    String fieldName = fb.name ();
                    conval = condition.get (fieldName);
                }
                if (ComKit.isNull (conval)) {
                    JSONField jf = field.getAnnotation (JSONField.class);
                    if (ComKit.isNotNull (jf)) {
                        String annotationName = jf.name ();
                        if (condition.containsKey (annotationName)) conval = condition.get (annotationName);
                        else if (annotationName.indexOf ("_") > 0) conval = condition.get (StringFormatKit.toCamelCase (annotationName));// 转成驼峰取值
                    } else conval = condition.get (field.getName ());
                }
                if (ComKit.isNotNull (conval)) RefKit.setFieldValue (obj, field.getName (), conval);
            }
        } catch (Exception e) {
            logger.error (e.getMessage (), e);
        }
    }

    /**
     * 设置cookie
     * 
     * @param response
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     */
    protected void addCookie(HttpServletResponse res,String name,String value,int maxAge){
        Cookie cookie = new Cookie (name,value);
        cookie.setPath ("/");
        if (maxAge > 0) cookie.setMaxAge (maxAge);
        res.addCookie (cookie);
    }

    /**
     * 根据名字获取cookie
     * 
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    protected Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String, Cookie> cookieMap = ReadCookieMap (request);
        if (cookieMap.containsKey (name)) {
            Cookie cookie = (Cookie) cookieMap.get (name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * 
     * @param request
     * @return
     */
    protected Map<String, Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie> ();
        Cookie[] cookies = request.getCookies ();
        if (null != cookies) {
            for ( Cookie cookie : cookies ) {
                cookieMap.put (cookie.getName (), cookie);
            }
        }
        return cookieMap;
    }

   

    /**
     * @Title: setMessageJson
     * @Description: 异常时设置 json
     * @Author: ZhongLiHong
     * @Since: 2014年7月1日下午5:57:52
     * @param mJson
     * @param e
     * @param req
     */
    protected void setMessageJson(MessageJson mJson,Exception e,HttpServletRequest req){
        if (null != e) {
            String errorCode = "";
            String errorDesc = "";
            String errorStack = "";
            if (e instanceof PMException) {
                errorCode = ((PMException) e).getErrcode ();
                if ("999999".equals (errorCode)) errorDesc = e.getMessage ();
                else errorDesc = ((PMException) e).getErrmsg ();
                if (null != e.getCause ()) errorStack = ErrorDeal.getStack (e);
                else {
                    errorStack = errorDesc;
                }
            } else {
                errorCode = CommonErrorConstants.e999999.getCode ();
                errorDesc = ErrorDeal.getErrorMessage (e);
                errorStack = ErrorDeal.getStack (e);
            }
            if (null == mJson) mJson = new MessageJson (MessageJson.ERROR);
            mJson.setFlag (errorCode);
            mJson.setMsg (errorDesc);
            mJson.setDetail (errorStack);
            logger.error (e.getMessage (), e);
        }
    }

    /**
     * @Title: verifyRequestData
     * @Description: 当返回不为NULL时需要 return
     * @Author: ZhongLiHong
     * @Since: 2014年7月2日上午10:26:20
     * @return
     */
    protected MessageJson verifyRequestData(String json,HttpServletRequest req){
        if (ComKit.isNull (json)) {
            MessageJson mJson = new MessageJson (MessageJson.ERROR);
            mJson.setMsg (getMsg ("common.not.found.data", req));
            return mJson;
        }
        return null;
    }

    /**
     * @Title: verifyOperationObject
     * @Description: 当返回不为NULL时需要 return
     * @Author: ZhongLiHong
     * @Since: 2014年7月2日上午10:26:20
     * @return
     */
    protected MessageJson verifyOperationObject(Object obj,HttpServletRequest req){
        if (ComKit.isNull (obj)) {
            MessageJson mJson = new MessageJson (MessageJson.ERROR);
            mJson.setMsg (getMsg ("common.operation.object.not.exist", req));
            return mJson;
        }
        return null;
    }

    public static final Object lock = new Object ();

    protected KeyPair getKeyPair(HttpServletRequest req){
        KeyPair keys = CacheKit.get ("rsaCache", "publicKey");
        if (null == keys) synchronized (lock) {
            keys = CacheKit.get ("rsaCache", "publicKey");
            if (null == keys) {
                keys = JCryKit.generateKeypair (1024);
                CacheKit.put ("rsaCache", "publicKey", keys);
            }
        }
        return keys;
    }

    protected synchronized void removeKyes(HttpServletRequest req){
        req.getSession ().removeAttribute ("RSAKey");
    }

    protected String desPasswd(HttpServletRequest req,String passwd){
        try {
            passwd = JCryKit.decryptPrivateKey (passwd, getKeyPair (req).getPrivate ());
        } catch (Exception e) {
            throw new PMException (CommonErrorConstants.e229903);
        }
        return passwd;
    }
}
