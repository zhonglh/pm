package com.common.interceptor;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerInterceptor{

    Logger logger = Logger.getLogger(TimerInterceptor.class);


    @Around("execution(* com.pm..*.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String method = joinPoint.getSignature().toString();
        //logger.debug("\r\n"+method+" start:"+start);

        Object result = joinPoint.proceed(joinPoint.getArgs());

        long end = System.currentTimeMillis();
        //logger.debug("\r\n"+method+" End:"+end);
        logger.info("\r\n"+method+" Used:"+(end-start));
        System.out.println("\r\n"+method+" Used:"+(end-start));

        return result;
        
    }
    
}