package com.common.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.pm.util.CustomDateEditor;
import com.pm.util.CustomDoubleEditor;
import com.pm.util.CustomIntEditor;
import com.pm.util.CustomTimestampEditor;

public class MyBindingInitializer implements WebBindingInitializer {
    public void initBinder(WebDataBinder binder,WebRequest request){
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient (false);
        binder.registerCustomEditor (java.sql.Timestamp.class, new CustomTimestampEditor (timeFormat,false));
        binder.registerCustomEditor (Date.class, new CustomDateEditor (dateFormat,false));
        binder.registerCustomEditor (String.class, new StringTrimmerEditor (false));
        binder.registerCustomEditor (int.class, new CustomIntEditor ());
        binder.registerCustomEditor (double.class, new CustomDoubleEditor ());
        //binder.registerCustomEditor (Integer.class, new CustomIntEditor ());
    }
}