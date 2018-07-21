package com.pm.util.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 * @author zhonglh
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnnotation {
	
	
	/**
	 * 操作类型
	 * @return
	 */
	public String operation_type() ;
	
	
	/**
	 * 实体类型
	 * @return
	 */
	public String entity_type() ;
	
}
