package com.common.spring;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.common.utils.spring.SpringContextUtil;

public class ApplicationContextRegister implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        SpringContextUtil.setApplicationContext (applicationContext);
    }

}
