package com.common.filters;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 记录方法的执行时间、方法名、使用的参数
 */
public class MethodLogAdvice implements MethodInterceptor {

    protected static Logger logger = LoggerFactory.getLogger (MethodLogAdvice.class);

    public MethodLogAdvice() {
        super ();
    }

    /**
     * 拦截要执行的目标方法
     */
    @SuppressWarnings("rawtypes")
    public Object invoke(MethodInvocation invocation) throws Throwable{

        // 用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
        StopWatch clock = new StopWatch ();
        clock.start (); // 计时开始
        Object result = invocation.proceed ();
        clock.stop (); // 计时结束
        // 方法参数类型，转换成简单类型
        Class[] params = invocation.getMethod ().getParameterTypes ();
        Object[] object = invocation.getArguments ();

       // Method method = invocation.getMethod ();

        String[] simpleParams = new String[params.length];
        for ( int i = 0 ; i < params.length ; i++ ) {
            if (null != object[i]) simpleParams[i] = params[i].getSimpleName () + ":::";
        }

//        if (method.isAnnotationPresent (OperationDescription.class)) {
//            OperationDescription od = method.getAnnotation (OperationDescription.class);
//        }

        logger.debug ("Takes : " + clock.getTime () + " ms [" + invocation.getThis ().getClass ().getName () + "." + invocation.getMethod ().getName () + "(" + StringUtils.join (simpleParams, ",")
                + ")] " + "");

        return result;
    }
}