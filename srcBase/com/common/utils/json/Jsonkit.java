package com.common.utils.json;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.ErrorDeal;
import com.common.utils.StringFormatKit;

public class Jsonkit {

    private static final SerializeConfig     config;
    static {
        config = new SerializeConfig ();
        // config.put (java.util.Date.class, new JSONLibDataFormatSerializer ()); // 使用和json-lib兼容的日期输出格式
        // config.put (java.sql.Date.class, new JSONLibDataFormatSerializer ()); // 使用和json-lib兼容的日期输出格式
    }
    private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
                                                      // SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat
                                                      // SerializerFeature.WriteClassName // 序列化为和JSON-LIB兼容的字符串
                                                      };

    /**
     * @Title: object2JsonString
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: lixin
     * @Since: 2014年6月23日上午10:13:25
     * @param object
     * @param PropertyFilter
     *            需要过滤的属性
     * @param nameFilter
     *            需要重新命名的属性
     * @param isToCamelCase
     *            下滑线转驼峰
     * @param isToBooleanToInt
     *            boolean 转 int
     * @return
     */
    public static String object2JsonString(Object object,final List<String> propertyFilter,final Map<String, String> nameFilter,boolean isToCamelCase,boolean isToBooleanToInt){
        PropertyFilter pf = new PropertyFilter () {

            @Override
            public boolean apply(Object object,String name,Object value){
                  if(name.toLowerCase().indexOf("time")>-1) return false;
                if (propertyFilter != null && propertyFilter.contains (name)) return false;
                return true;
            }
        };
        NameFilter _nameFilter = new NameFilter () {

            @Override
            public String process(Object object,String name,Object value){
                if (nameFilter.containsKey (name)) name = nameFilter.get (name);
                return name;
            }
        };
        NameFilter toCamelCase = new NameFilter () {

            @Override
            public String process(Object object,String name,Object value){
                if (name.indexOf (StringFormatKit.SEPARATOR) > -1) name = StringFormatKit.toCamelCase (name);
                return name;
            }
        };

        ValueFilter toBooleanToInt = new ValueFilter () {

            @Override
            public Object process(Object object,String name,Object value){
                if (value instanceof Boolean) {
                    boolean _value = (Boolean) value;
                    if (_value == Boolean.TRUE) return "1";
                    else return "0";
                }
                return value;
            }
        };
        JSONSerializer serializer = new JSONSerializer (config);

        //if (null != propertyFilter && propertyFilter.size () > 0) 
      	  serializer.getPropertyFilters ().add (pf);// 增加字段过滤
        if (null != nameFilter && nameFilter.size () > 0) serializer.getNameFilters ().add (_nameFilter);// 增加字段转换
        if (isToCamelCase) serializer.getNameFilters ().add (toCamelCase);// 增加下划线转驼峰
        if (isToBooleanToInt) serializer.getValueFilters ().add (toBooleanToInt);// 增加blean转int
        for ( com.alibaba.fastjson.serializer.SerializerFeature feature : features ) {
            serializer.config (feature, true);
        }
        serializer.write (object);
        return serializer.toString ();
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以过滤不需要的属性
     * @Author: Hongli
     * @Since: 2014年5月6日下午2:47:50
     * @param object
     * @param propertyFilter
     *            过滤不需要的属性
     * @return
     */
    public static String object2JsonString(Object object,List<String> propertyFilter){
        return object2JsonString (object, propertyFilter, null, false, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以重命名某些元素
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:06:21
     * @param object
     * @param nameFilter
     *            需要重新命名的属性
     * @return
     */
    public static String object2JsonString(Object object,Map<String, String> nameFilter){
        return object2JsonString (object, null, nameFilter, false, false);
    }

    /**
     * @Title: object2JsonString
     * @Description:转json时下划线可以转驼峰
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:07:56
     * @param object
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object,boolean isToCamelCase){
        return object2JsonString (object, null, null, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以过滤不需要的属性，并重命名某些属性
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:11:33
     * @param object
     * @param propertyFilter
     *            过滤不需要的属性
     * @param nameFilter
     *            重命名某些属性
     * @return
     */
    public static String object2JsonString(Object object,List<String> propertyFilter,final Map<String, String> nameFilter){
        return object2JsonString (object, propertyFilter, nameFilter, false, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以过滤不需要的属性,下划线可以转驼峰
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:13:05
     * @param object
     * @param propertyFilter
     *            可以过滤不需要的属性
     * @param isToCamelCase
     *            true 下划线可以转驼峰
     * @return
     */
    public static String object2JsonString(Object object,List<String> propertyFilter,boolean isToCamelCase){
        return object2JsonString (object, propertyFilter, null, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持重命名某些属性，支持下划线可以转驼峰
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:14:39
     * @param object
     * @param nameFilter
     *            重命名某些属性
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object,Map<String, String> nameFilter,boolean isToCamelCase){
        return object2JsonString (object, null, nameFilter, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰，支持boolean 转true=1，false=0
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:20:17
     * @param object
     * @param isToCamelCase
     *            true 支持下划线转驼峰
     * @param isToBooleanToInt
     *            true 支持boolean转数字
     * @return
     */
    public static String object2JsonString(Object object,boolean isToCamelCase,boolean isToBooleanToInt){
        return object2JsonString (object, null, null, isToCamelCase, isToBooleanToInt);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰，支持属性过滤，支持重命名
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:23:59
     * @param object
     * @param propertyFilter
     *            需要过滤的属性
     * @param nameFilter
     *            需要重命名的属性
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object,final List<String> propertyFilter,final Map<String, String> nameFilter,boolean isToCamelCase){
        return object2JsonString (object, propertyFilter, nameFilter, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰， 支持重命名,支持boolean 转true=1，false=0
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:23:59
     * @param object
     * @param propertyFilter
     *            需要过滤的属性
     * @param isToBooleanToInt
     *            true 支持boolean转int
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object,List<String> propertyFilter,boolean isToCamelCase,boolean isToBooleanToInt){
        return object2JsonString (object, propertyFilter, null, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰， 支持属性过滤,支持boolean 转true=1，false=0
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:23:59
     * @param object
     * @param nameFilter
     *            属性过滤
     * @param isToBooleanToInt
     *            true 支持boolean转int
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object,Map<String, String> nameFilter,boolean isToCamelCase,boolean isToBooleanToInt){
        return object2JsonString (object, null, nameFilter, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: Object 转json
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:33:44
     * @param object
     * @return
     */
    public static String object2JsonString(Object object){
        return JSON.toJSONString (object, config, features);
    }

    /**
     * @Title: jsonString2Object
     * @Description: josn 转OBject
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:34:03
     * @param jsonString
     * @param classes
     * @return
     */
    public static <T> T jsonString2Object(String jsonString,Class<T> classes){
        return jsonString2Object (jsonString, classes, false);
    }

    /**
     * @Title: jsonString2Object
     * @Description: 支持验证JSON格式
     * @Author: Hongli
     * @Since: 2014年7月2日上午9:35:09
     * @param jsonString
     * @param classes
     * @param verify
     *            true 验证
     * @throws PMException
     *             JSON格式不正确时抛出异常
     * @return
     */
    public static <T> T jsonString2Object(String jsonString,Class<T> classes,boolean verify){
        if (verify) {
            if (null == String2JSON (jsonString)) return null;
            return JSON.parseObject (jsonString, classes);
        } else return JSON.parseObject (jsonString, classes);
    }

    /**
     * @Title: jsonString2Array
     * @Description: josn 转List
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:34:21
     * @param jsonString
     * @param classes
     * @return
     */
    public static <T> List<T> jsonString2Array(String jsonString,Class<T> classes){
        return jsonString2Array (jsonString, classes, false);
    }

    /**
     * @Title: jsonString2Array
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: Hongli
     * @Since: 2014年7月2日上午9:49:06
     * @param jsonString
     * @param classes
     * @param verify
     *            true 验证
     * @throws PMException
     *             JSON格式不正确时抛出异常
     * @return
     */
    public static <T> List<T> jsonString2Array(String jsonString,Class<T> classes,boolean verify){
        if (verify) {
            if (null == String2JSON (jsonString)) return null;
            return JSON.parseArray (jsonString, classes);
        } else return JSON.parseArray (jsonString, classes);
    }

    /**
     * @Title: json2Object
     * @Description:JSONObject 转javaObject
     * @Author: Hongli
     * @Since: 2014年6月27日下午9:34:43
     * @param json
     * @param classes
     * @return
     */
    public static <T> T json2Object(JSON json,Class<T> classes){
        return JSON.toJavaObject (json, classes);

    }

    /**
     * bean转JSONObject或jsonArray
     * 
     * @param obj
     * @return
     */
    public static JSON beanToJSONObject(Object obj){
        return (JSON) JSON.toJSON (obj);
    }

    /**
     * @Title: String2JSON
     * @Description: String 转json对像JSONObject ,JSONArray,Integer,String
     * @Author: Hongli
     * @Since: 2014年6月30日下午9:17:45
     * @param json
     * @return
     */
    public static Object String2JSON(String json){
        try {
            return JSON.parse (json);
        } catch (JSONException e) {
            throw new PMException (CommonErrorConstants.e119901,e);
        }

    }

    /**
     * 判断是否是json结构
     */
    public static boolean isJson(String value){
        try {
            JSON.parse (value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        try {
            System.out.println (object2JsonString (1));
            System.out.println (isJson (object2JsonString (1)));
            System.out.println (String2JSON (object2JsonString (1)));
        } catch (Exception e) {
            e.printStackTrace ();
            try {
                System.out.println (ErrorDeal.getErrorMessage (e));
                throw new RuntimeException (e);
            } catch (Exception e2) {
                System.out.println (ErrorDeal.getErrorMessage (e));
            }

        }

    }
}
