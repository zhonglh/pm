package com.pm.util.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntityAnnotation {
	
	/**
	 * 属性名称
	 * @return
	 */
	public String item_name() ;
	
	
	/**
	 * 序号
	 * @return
	 */
	public int item_sort() default 0;

	/**
	 * 属性字符长度
	 * @return
	 */
	public int length() default 0;
	
	
	/**
	 * 业务名称,用于区分一个JavaBean用于多个业务
	 * @return
	 */
	public String business_name() default "";
	
}
