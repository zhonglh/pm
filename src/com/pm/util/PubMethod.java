package com.pm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.RefKit;
import com.common.utils.file.FileKit;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.Permit;
import com.pm.domain.system.User;
import com.pm.service.impl.SalaryMailServiceImpl;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.Column;
import com.pm.util.log.EntityAnnotation;
import com.pm.vo.DepartStatisticsItem;

/**
 * @author Administrator
 */
public class PubMethod {
	
	
	public static void main(String[] args){
		double d = PubMethod.getNumberFormatByDouble(-2478753.74795407);
		System.out.println(d);
	}
	

	private static DecimalFormat   decimalFormat   =   new   DecimalFormat("#####0.00");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
	
	public static String getString4Object(Column column, Object t){

		try {

			column.getField().setAccessible(true);
			Object value = column.getField().get(t);
			if(value == null) {
				return "";
			}
			
			if(column.getField().getType().equals(String.class)){
				return String.valueOf(value);
			}else if(column.getField().getType().equals(java.sql.Timestamp.class)){
				return dateFormat.format((java.sql.Timestamp)value);
			}else if(column.getField().getType().equals(java.sql.Date.class)){
				return dateFormat.format((java.sql.Date)value);
			}else if(column.getField().getType().equals(int.class)){
				return String.valueOf((int)value);
			}else if(column.getField().getType().equals(double.class)){
				return String.valueOf( (double)value);						
			}else if(column.getField().getType().equals(long.class)){
				return String.valueOf( (long)value);						
			}else {						
				return value.toString();		
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "";
		
	
	}
	

	public static String getString4Object(Field f,Object t){

		try {

			f.setAccessible(true);
			Object value = f.get(t);
			if(value == null) {
				return "";
			}
			
			if(f.getType().equals(String.class)){
				return String.valueOf(value);
			}else if(f.getType().equals(java.sql.Timestamp.class)){
				return dateFormat.format((java.sql.Timestamp)value);
			}else if(f.getType().equals(java.sql.Date.class)){
				return dateFormat.format((java.sql.Date)value);
			}else if(f.getType().equals(int.class)){
				if(((int)value) == 0) {
					return "";
				}
				return String.valueOf((int)value);
			}else if(f.getType().equals(double.class)){
				if(((double)value) == 0) {
					return "";
				}
				return decimalFormat.format(value);					
			}else if(f.getType().equals(long.class)){
				if(((long)value) == 0) {
					return "";
				}
				return String.valueOf( (long)value);						
			}else {						
				return value.toString();		
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "";
		
	
	}
	
	/**
	 * 处理Excel导入的数字格式的字符串， 去掉小数点及后面的
	 * @param values
	 * @return
	 */
	public static String fromatExcelDecimal(String values){
		if(values == null || values.isEmpty()) {
			return "";
		}
		String temp[] = values.trim().split("\\.");
		if(temp.length>1) {
			return temp[0];
		}
		else {
			return values.trim();
		}
	}
	
	public static String getSalaryMailTemplate(){
		try{
			String fielPath = SalaryMailServiceImpl.class.getClassLoader().getResource("/salary_mail.html").getPath();
			return FileKit.getString4File(fielPath, "utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request,name);
		if(cookie != null) {
			return cookie.getValue();
		}
		else {
			return null;
		}
	}
	
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			return null;
		}
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(name)) {
				return cookie;
			}
		}		
		return null;		
	}
	
	public static User getUser(HttpServletRequest request){
		try{
			return (com.pm.domain.system.User)request.getSession().getAttribute(BusinessUtil.SESSION_USER);
		}catch(Exception e){
			return null;
		}
	}
	
	public static java.sql.Timestamp getCurrentDate(){
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	
	public static int getCurrentYear(){
		return getCurrentDate().getYear() + 1900;
	}
	
	public static boolean isDelete(String delete_flag){
		if(BusinessUtil.DELETEED.equals(delete_flag)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String getAllWord(){
		String ALL  = "全部";
		return ALL;
	}
	
	

	public static  <T> void setRequestPager(HttpServletRequest request , Pager<T> pager ,Class<T> clz){
		request.setAttribute("pageNum", pager.getPageNo());
		request.setAttribute("pageSize", pager.getPageSize());
		request.setAttribute("totalPages", pager.getTotalPages());
		request.setAttribute("totalRows", pager.getTotalRows());
		request.setAttribute("total_amount", pager.getTotal_amount());
		request.setAttribute("list", pager.getResultList());		
		if(pager.getResultObj() != null){
			request.setAttribute("resultObj", PubMethod.entity2String(pager.getResultObj(), "<br>"));	
		}
	}
	
	/**
	public static String getEntity(String className){
		for(String key : entityMap.keySet()){
			if(className.indexOf(key) != -1){
				return key;
			}
		}
		return null;
	}
	*/
	

	/**
	 * 根据实体类型，获取实体类名
	 * @param entityName
	 * @return
	 */
	/**
	public static String getLogService(String entityName){
		if(entityName == null) return null;
		String classname = entityName;
		
		String[] names = classname.split("_");
		StringBuffer sb = new StringBuffer("");
		for(String name : names){
			sb.append(letterToUpper(name));
		}
		
		classname = letterToUpper(classname);
		classname = "com.pm.util.log."+classname+"Log";
		return classname;
	}
	*/	
	
	public static <T> List<T> getList( T[] arrays, Class<T> clazz){
		if(arrays == null || arrays.length == 0) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for(T t : arrays){
			list.add(t);
		}
		return list;
	}
	
	
	public static void printParam(HttpServletRequest request){
		
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()){		
			System.out.println(entry.getKey()+ "=" +entry.getValue()[0]);		
		}
	}
	
	
	public static String letterToUpper(String str){
		if(str == null) {
			return null;
		}
	      Character c = Character.toUpperCase(str.charAt(0));
	      if(str.length()>1)  {
	      	return c.toString().concat(str.substring(1));
		  }
	      else {
	      	return c.toString();
		  }
	}
	
	
	/**
	 * 实体转为字符串
	 * @param obj
	 * @param sign
	 * @return
	 */
	public static String entity2String(Object obj , String sign){
		if(obj == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		Field[] fs = getFields(obj.getClass());
		
		for(Field f : fs){
			if(f.isAnnotationPresent(EntityAnnotation.class)){
				EntityAnnotation annotation = f.getAnnotation(EntityAnnotation.class);								
				String val = getString4Object(f,obj);
				if(StringUtils.isNotEmpty(val)){
					sb.append(annotation.item_name()).append(" : ").append(val).append(sign);
				}
			}
		}
		return sb.toString();
	}
	
	public static String obj2String(Object obj){
		if(obj == null) {
			return null;
		}
		if(obj instanceof java.sql.Timestamp){
			java.sql.Timestamp date = (java.sql.Timestamp)obj;
			return DateKit.fmtDateToYMDHM(date);
		}else if(obj instanceof java.sql.Date){
			java.sql.Date date = (java.sql.Date)obj;
			return DateKit.fmtDateToYMD(date);
		}else if(obj instanceof java.util.Date){
			java.util.Date date = (java.util.Date)obj;
			return DateKit.fmtDateToYMD(date);
		}
		return obj.toString();
		
	}
	

	public static double obj2Double(Object obj){
		if(obj == null) {
			return 0;
		}
		if(obj instanceof java.math.BigDecimal){
			return ((BigDecimal)obj).doubleValue();
		}else if(obj instanceof Double){			
			return (Double)obj;
		}else if(obj instanceof Integer){			
			return new Double((Integer)obj);
		}else {			
			return new Double(obj.toString());
		}
		
	}
	
	
	public static <T> List<LogDetail> getLogDetails(Log log, Class<T> clz,Object object1,Object object2){
		List<LogDetail> list = new ArrayList<LogDetail>();
		if(object1 == null && object2 == null) {
			return list;
		}
		Field[] fs = getFields(clz);
		
		for(Field f : fs){
			if(f.isAnnotationPresent(EntityAnnotation.class)){
				try {
					
					f.setAccessible(true);
					Object preValue = object1==null?null:f.get(object1);
					Object nextValue = object2==null?null:f.get(object2);
					
					LogDetail logDetail = new LogDetail();
					logDetail.setLog_id(log.getLog_id());
					logDetail.setData_item_code(f.getName());
					logDetail.setData_item_i18n(f.getAnnotation(EntityAnnotation.class).item_name());
					
					if(preValue != null){
						if(preValue.getClass() == double.class || preValue.getClass() == Double.class){
							if(Double.parseDouble(preValue.toString()) == 0) {
								preValue = null;
							}
						}else if(preValue.getClass() == int.class || preValue.getClass() == Integer.class){
							if(Integer.parseInt(preValue.toString()) == 0) {
								preValue = null;
							}
						}else if(preValue.getClass() == String.class){
							if(preValue.toString().isEmpty()) {
								preValue = null;
							}
						}
					}
					if(nextValue != null){
						if(nextValue.getClass() == double.class || nextValue.getClass() == Double.class){
							if(Double.parseDouble(nextValue.toString()) == 0) {
								nextValue = null;
							}
						}else if(nextValue.getClass() == int.class || nextValue.getClass() == Integer.class){
							if(Integer.parseInt(nextValue.toString()) == 0) {
								nextValue = null;
							}
						}else if(nextValue.getClass() == String.class){
							if(nextValue.toString().isEmpty()) {
								nextValue = null;
							}
						}
					}
					

					if(preValue == nextValue){
						continue;
					}else if(preValue == null || nextValue == null){						
						logDetail.setOperation_before(preValue==null?"":obj2String(preValue));
						logDetail.setOperation_after(nextValue==null?"":obj2String(nextValue));
						logDetail.setLog_detail_id(IDKit.generateId());
						list.add(logDetail);
					}
					else if(!preValue.equals(nextValue)){
						logDetail.setOperation_before(preValue==null?"":obj2String(preValue));
						logDetail.setOperation_after(nextValue==null?"":obj2String(nextValue));
						logDetail.setLog_detail_id(IDKit.generateId());
						list.add(logDetail);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
						
			}
		}
		return list;
	}

	public static <T> Field[] getFields(Class<T> clz) {
		return RefKit.getField(clz);
	}
	

	/**
	 * 两个 集合相减, 返回 (secSets - firSets)
	 * @param firSets
	 * @param secSets
	 * @return
	 */
	public static List<String> getDifferenceSets(List<String> firSets, List<String> secSets){
		if(firSets == null && secSets == null) {
			return null;
		}
		if(firSets == null) {
			return secSets;
		}
		if(secSets == null) {
			return null;
		}
		HashSet<String> hs = new HashSet<String>();
		for(String sec : secSets) {
			hs.add(sec);
		}
		for(String fir : firSets){
			hs.remove(fir);
		}
		if(hs.size() ==0 ) {
			return null;
		}
		else {
			List<String> array1 = new ArrayList<String>();
			for(String diff : hs) {
				array1.add(diff);
			}
			return array1; 
		}
	}
	
	public static boolean isAjax(HttpServletRequest request) {
		if (request != null
				&& "XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With"))) {
			return true;
		}
		return false;
	}
	
	public static Permit getPermit(List<Permit> list, String permit_id){
		if(list == null) {
			return null;
		}
		for(Permit permit : list){
			if(permit.getPermit_id().equals(permit_id)){
				return permit;
			}
		}
		return null;
	}
	
	
    public static double getNumberFormatByDouble(double s){
        BigDecimal number = new BigDecimal (s);
        number = number.setScale (2, BigDecimal.ROUND_HALF_UP);
        return number.doubleValue ();
    }
	


	public static <T> void List2Array(List<T> list,T[] objArray, Class<T> clz) {
		if (list == null || list.size() == 0)
			return ;
		else {			
			int len = objArray.length;
			for (int index = 0; index < len; index++) {
				objArray[index] = list.get(index);
			}
		}
	}
	
	
	public static <T> List<T> stringArray2List(List<String[]> list,Class<T> clz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if(list == null || list.isEmpty()) {
			return null;
		}


		List<T> ts = new ArrayList<T>();
		List<Column> columns = BusinessExcel.getImportColumn(clz);

		int position = 0;
		//position = list.get(0).length - columns.size();
		//if( position < 0) position = 0;

		
		for(String[] strArray : list){
			T t = clz.newInstance();
			int index = position;
			int len = strArray.length;
			for(Column column : columns){
				column.getField().setAccessible(true);
				if(index >= len) {
					break;
				}
				Object obj = null;
				try{
					if(strArray[index] != null && !strArray[index].isEmpty()) {
						obj = getObject(column, strArray[index]);
					}
				}catch(Exception e){
					e.printStackTrace();
					String errorInfo = (String)clz.getMethod("getErrorInfo").invoke(t);
					if(errorInfo == null) {
						errorInfo = "";
					}
					clz.getMethod("setErrorInfo",String.class).invoke(t, errorInfo + "" + column.getName() + " 数据格式错误; ");
				}
				
				if(obj != null) {
					column.getField().set(t, obj);
				}
				
				if(strArray[index]!=null &&strArray[index].length()>0){
					int columnLength = column.getLength();
					if(columnLength >0 && columnLength < strArray[index].length()){
						String errorInfo = (String)clz.getMethod("getErrorInfo").invoke(t);
						if(errorInfo == null) {
							errorInfo = "";
						}
						clz.getMethod("setErrorInfo",String.class).invoke(t, errorInfo + "" + column.getName() + "  内容太长; ");
					}
					 
				}

				
				index ++;
			}
			ts.add(t);
		}
		
		return ts;
	}
	
	public static Object getObject(Column column, String val){
		if(val == null || val.trim().isEmpty()) {
			return null;
		}
		val = val.trim();
		
		Field field = column.getField();
		
		if(field.getType().equals(String.class)){
			return val;
		}else if(field.getType().equals(java.sql.Timestamp.class)){
			return new Timestamp(DateKit.fmtStrToDate(val).getTime());
		}else if(field.getType().equals(java.sql.Date.class)){
			return new java.sql.Date(DateKit.fmtStrToDate(val).getTime());
		}else if(field.getType().equals(java.util.Date.class)){
			return new java.util.Date(DateKit.fmtStrToDate(val).getTime());
		}else if(field.getType().equals(int.class) || field.getType().equals(Integer.class)){
			if(val.indexOf(".")>0){
				val = val.substring(0,val.indexOf("."));
			}
			return Integer.parseInt(val);
		}else if(field.getType().equals(double.class) || field.getType().equals(Double.class)){
			return Double.parseDouble(val);	
		}else {
			throw new RuntimeException(column.getName()+"类型转换错误");
		}
	}
	
	public static String changeQuarterView(String quarter){
		if(quarter == null) {
			return null;
		}
		if(quarter.length() != 6) {
			return quarter;
		}
		StringBuffer sb = new StringBuffer(quarter.substring(0,4));
		sb.append("年第").append(quarter.charAt(5));
		sb.append("季度");
		return sb.toString();
	}
	
	public static String getMonthChinseStr(int month){
		String monthStr = String.valueOf(month);
		if(monthStr.length() != 6) {
			return "";
		}
		return monthStr.substring(0,4)+"年"+monthStr.substring(4)+"月";
	}

	public static String[] List2Array(List<String> list) {
		if (list == null || list.size() == 0) {
			return null;
		}else {
			String[] objArray = new String[list.size()];
			int len = objArray.length;
			for (int index = 0; index < len; index++) {
				String cellVal = list.get(index);
				if(cellVal != null){
					cellVal = cellVal.trim();
				}
				objArray[index] = cellVal ;
			}
			return objArray;
		}
	}	

	public static <T> Pager<T> getPager(HttpServletRequest request , Class<T> t){
		Pager<T> pager = new Pager<T>();
		pager.setPageNo(getPageNum(request));
		pager.setPageSize(getPageSize(request));
		return pager;
	}
	


	public static <T> Pager<T> getPagerByAll( Class<T> t){
		Pager<T> pager = new Pager<T>();
		pager.setPageNo(1);
		pager.setPageSize(BusinessUtil.MAX_SIZE);
		return pager;
	}	
	public static <T> Pager<T> getPagerByAll(HttpServletRequest request , Class<T> t){
		Pager<T> pager = new Pager<T>();
		pager.setPageNo(1);
		pager.setPageSize(BusinessUtil.MAX_SIZE);
		return pager;
	}	
	

	/**
	 * 获取当前查询页码
	 * @param request
	 * @return
	 */
	public static int getPageNum(HttpServletRequest request){
		int pageNo = 1;
		if(null != request.getParameter("page") && !"".equals(request.getParameter("page"))) {
				pageNo = Integer.parseInt(request.getParameter("page"));
		}
		if(null != request.getParameter("pageNum") && !"".equals(request.getParameter("pageNum"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNum"));
	}
		return pageNo;
	}
	
	/**
	 * 当前分页查询数量
	 * @param request
	 * @return
	 */
	public static int getPageSize(HttpServletRequest request){
		
		 int pageSize =  (request.getSession().getAttribute("PAGE_SIZE") != null) ?
				 (int)(request.getSession().getAttribute("PAGE_SIZE")) :
				 BusinessUtil.PAGE_SIZE;
				 
		 if(null != request.getParameter("rp") && !"".equals(request.getParameter("rp"))) {
			 pageSize = Integer.parseInt(request.getParameter("rp"));
		 }else  if(null != request.getParameter("pageSize") && !"".equals(request.getParameter("pageSize"))) {
			 pageSize = Integer.parseInt(request.getParameter("pageSize"));
		 }else if(null != request.getParameter("numPerPage") && !"".equals(request.getParameter("numPerPage"))) {
			 pageSize = Integer.parseInt(request.getParameter("numPerPage"));
		 }
		 

		 if(request.getSession().getAttribute("PAGE_SIZE") != null){
			 int sessoinPageSize = (int)request.getSession().getAttribute("PAGE_SIZE");
			 if(sessoinPageSize != pageSize) {
			 	request.getSession().setAttribute("PAGE_SIZE", pageSize);
			 }
		 }else {
			 request.getSession().setAttribute("PAGE_SIZE", pageSize);
		 }
		 
		 
		 return pageSize;
	}	
	
	/**
	 * 两个日期转换为字符串
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String twoDate2Str(java.util.Date date1, java.util.Date date2){
		if(date1 == null && date2 == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer("");
		if(date1 != null) {
			sb = sb.append(DateKit.fmtDateToYMD(date1));
		}
		sb.append(BusinessUtil.SPLIT_DATE);
		if(date2 != null) {
			sb = sb.append(DateKit.fmtDateToYMD(date2));
		}
		return sb.toString();
	}
	
	/**
	 * 字符串转换为两个日期
	 * @param str
	 * @return
	 */
	public static java.util.Date[] str2TtwoDate(String str){
		java.util.Date[] ds = new java.util.Date[2]; 
		if(str == null || str.isEmpty()) {
			return  ds;
		}
		
		String[] dateStr = str.split(BusinessUtil.SPLIT_DATE);
		if(dateStr.length > 2) {
			throw new RuntimeException("数据错误");
		}
		
		if(dateStr != null){
			for(int i=0;i<dateStr.length;i++){
				if(dateStr[i] == null || dateStr[i].isEmpty()) {
					continue;
				}
				java.util.Date date1 = DateKit.fmtStrToDate(dateStr[i], "yyyy-MM-dd");
				ds[i] = date1;
			}
		}
			
		return ds;
	}
	
	public static String twoNumber2Str(Double d1, Double d2){
		if(d1 == null && d2 == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer("");
		if(d1 != null) {
			sb = sb.append(d1.toString());
		}
		if(d2 != null){
			sb = sb.append(BusinessUtil.SPLIT_DATE);
			sb = sb.append(d2.toString());		
		}
		return sb.toString();
	}
	
	public static Double[] str2TtwoNumber(String str){
		
		str = str.replaceAll(",", "");
		String[] numberStr = str.split(BusinessUtil.SPLIT_DATE);
		if(numberStr.length != 2 && numberStr.length != 1) {
			throw new RuntimeException("数据错误");
		}
		
		Double[] ds = new Double[2];
		
		for(int i=0;i<numberStr.length;i++){
			ds[i] = Double.parseDouble(numberStr[i]);
		}
		
		return ds;
		
	}
	
	
	
	
	public static <T> T getObj4Map(String type,String name,Map<String, Map<String, T>> map){
		if(map == null || map.isEmpty()) {
			return null;
		}
		
		Map<String, T> temp = map.get(type);
		if(temp == null || temp.isEmpty()) {
			return null;
		}
		
		return temp.get(name);
	}
	
	
	
	
	/**
	 * List转Map
	 * @param list
	 * @return
	 */
	public static Map<String, DepartStatisticsItem> list2Map(List<DepartStatisticsItem> list){
		Map<String, DepartStatisticsItem> map = new HashMap<String, DepartStatisticsItem>();
		if(list == null || list.isEmpty()) {
			return map;
		}
		for(DepartStatisticsItem departStatisticsItem : list){
			map.put(departStatisticsItem.getDeptId(),departStatisticsItem);
		}
		return map;
	}
	public static Map<String, Map<String, DepartStatisticsItem>> list2Map2(List<DepartStatisticsItem> list){
		Map<String, Map<String, DepartStatisticsItem>> map = new HashMap<String, Map<String, DepartStatisticsItem>>();
		if(list == null || list.isEmpty()) {
			return map;
		}
		for(DepartStatisticsItem departStatisticsItem : list){
			Map<String, DepartStatisticsItem> temp = map.get(departStatisticsItem.getItemId());
			if(temp == null ) {
				temp = new HashMap<String, DepartStatisticsItem>();
				map.put(departStatisticsItem.getItemId(), temp);
			}
			temp.put(departStatisticsItem.getDeptId(), departStatisticsItem);
		}
		return map;
	}
	
	
	
	
	/**
	 * 验证字符串
	 * @param regex		验证规则
	 * @param str		验证字符串
	 * @return			是否验证通过
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}



	public static boolean isContains(List<String> arrs , String s){
		if(arrs == null || arrs.isEmpty()){
			return false;
		}
		return arrs.contains(s);
	}

	public static boolean isContains(String[] arrs , String s){
		if(arrs == null || arrs.length == 0){
			return false;
		}

		for(String arr : arrs){
			if(arr.equals(s)){
				return true;
			}
		}

		return false;
	}
	
	

}
