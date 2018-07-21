package com.common.el;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.simpleEL.dialect.tiny.TinyELEvalService;

/**
 * Created by Administrator on 2016/11/24.
 */
public class ElUtil {

    TinyELEvalService elService = null;
    Map<String,Object> ctx = null;

    public ElUtil(Map<String,Object> ctx){
        elService = new TinyELEvalService();
        this.ctx = ctx;
        init();
    }
    private void init(){


        if(ctx != null && ctx.size() > 0 ){
            for(String key : ctx.keySet()) {
                Object val = ctx.get(key) ;
                if(val != null) {
                    //SimpleEL中没有处理 double float
                    if(val.getClass().isArray())
                        elService.regsiterVariant(val.getClass(), key);
                    else elService.regsiterVariant(BigDecimal.class, key);
                }else {
                    elService.regsiterVariant(BigDecimal.class, key);
                }
            }
        }
    }

    public double eval(String exp , String key){
    	
        if(exp == null || exp.isEmpty()) {
            ctx.put(key,0.00);
        	return 0.00;
        }
        elService.regsiterVariant(BigDecimal.class, "temp","temp1","temp2","temp3","temp4");
        elService.regsiterVariant(BigDecimal.class, "临时变量","临时变量1","临时变量2","临时变量3","临时变量4");
        ctx.put("temp",0);
        ctx.put("temp1",0);
        ctx.put("temp2",0);
        ctx.put("temp3",0);
        ctx.put("temp4",0);
        ctx.put("临时变量",0);
        ctx.put("临时变量1",0);
        ctx.put("临时变量2",0);
        ctx.put("临时变量3",0);
        ctx.put("临时变量4",0);

        elService.regsiterVariant(BigDecimal.class, key);

        if(exp.indexOf(";")!=-1 || exp.indexOf("return ")!=-1) {
            //开启多表达式模式
            elService.setAllowMultiStatement(true);
        }else {
            elService.setAllowMultiStatement(false);
        }

        Object result = elService.eval(ctx, exp);
        
        double dr = 0;
        if(result == null) return 0;
        else if(result instanceof  java.math.BigDecimal){
        	dr = ((java.math.BigDecimal)result).doubleValue();
        }else  if(result instanceof  Double){
        	dr = (Double)result;
        }else if(result instanceof  String){
        	dr = Double.parseDouble((String)result);
        }else {
        	dr = new Double(result.toString());
        }
        
        dr = (new java.math.BigDecimal(dr)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 

        ctx.put(key,dr);
        return dr;
        
    }

    /**
     * 执行表达式
     * @param ctx
     * @param exp
     * @return
     */    
    public static double exec(Map<String, Object> ctx , String exp){
    	
        if(exp == null || exp.isEmpty()) {
        	return 0.00;
        }
        
        TinyELEvalService service = new TinyELEvalService();

        service.regsiterVariant(BigDecimal.class, "temp","temp1","temp2","temp3","temp4");
        service.regsiterVariant(BigDecimal.class, "临时变量","临时变量1","临时变量2","临时变量3","临时变量4");
        ctx.put("temp",0);
        ctx.put("temp1",0);
        ctx.put("temp2",0);
        ctx.put("temp3",0);
        ctx.put("temp4",0);
        ctx.put("临时变量",0);
        ctx.put("临时变量1",0);
        ctx.put("临时变量2",0);
        ctx.put("临时变量3",0);
        ctx.put("临时变量4",0);

        if(exp.indexOf(";")!=-1 || exp.indexOf("return ")!=-1) {
            //开启多表达式模式
            service.setAllowMultiStatement(true);
        }else {
            service.setAllowMultiStatement(false);
        }
        if(ctx != null && ctx.size() > 0 ){
            for(String key : ctx.keySet()) {
                Object val = ctx.get(key) ;
                if(val != null) {
                    //SimpleEL中没有处理 double float
                    if(val.getClass().isArray())
                        service.regsiterVariant(val.getClass(), key);
                    else service.regsiterVariant(BigDecimal.class, key);
                }else {
                    service.regsiterVariant(BigDecimal.class, key);
                }
            }
        }
        Object result = service.eval(ctx, exp);
        

        
        if(result == null) return 0;
        else if(result instanceof  java.math.BigDecimal){
            return ((java.math.BigDecimal)result).doubleValue();
        }else  if(result instanceof  Double){
            return (Double)result;
        }else if(result instanceof  String){
            return Double.parseDouble((String)result);
        }else {
            return new Double(result.toString());
        }
    }


    public static void main(String[] args){

        Integer[] bbb = {51,52,53,54};
        Integer[] ccc = {1,2,3,4};

        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("bbb",bbb);
        ctx.put("ccc",ccc);
/*
        ctx.put("aa",abc);
        ctx.put("t",0);
        ElUtil elUtil = new ElUtil(ctx);
        ElUtil.eval("for(int i=0;i<4;i++) t+=abc[1]; return t;","d");
        elUtil.eval("return aa[1]*9+_sum();","f");*/


        ctx.put("资金成本率",3.12);
        ctx.put("运营成本率",3.13);

        ctx.put("违约概率",3.13);
        ctx.put("违约损失率",3.13);
        ctx.put("经济资本占用率",3.13);
        ctx.put("股东回报率",3.13);
        ctx.put("产品回报要求",3.13);
        ctx.put("所得税率",0.13);
        ctx.put("增值税率",0.3);
        ctx.put("期限",0.3);
        ctx.put("贷款金额",0.3);

        ctx.put("现有业务个数",0);

        ElUtil ElUtil = new ElUtil(ctx);
        ElUtil.eval("0+SUM(bbb,ccc)","t1");
        /*ElUtil.eval("for(int i=0;i<10;i++) {a=a+i;} return a","t");
        ElUtil.eval("_sum(_sum(1.2,3),_sum(1.99,_sum(11,3)))","ss");
        //ElUtil.eval("if (a > b) {  temp1++; temp1 = temp1 + a*b; return temp1;} else {double tt=0; tt=a+b;tt++;return tt; }","c");
        ElUtil.eval("for(int i=0;i<10;i++) a = a+1; return a;","d");
        ElUtil.eval("if(_sum(1,3) > 3) {return 1;} else {return 0;}","e");
        ElUtil.eval("return abc[1]*9.111","f");*/

        System.out.print(ctx);

/*
        double d1 = 3.21;
        double d2 = 4.11;
        System.out.println(d2-d1);

        BigDecimal b1 = new BigDecimal(3.21);
        BigDecimal b2 = new BigDecimal(4.11);
        System.out.println(b2.subtract(b1));


        Map<String,Object> ctx = new HashMap<String,Object>();
        ctx.put("股东回报要求",-90.12);
        ctx.put("期望要求",8.12);
        Object obj = ElUtil.exec(ctx, "if(股东回报要求 < 期望要求) {return 股东回报要求*期望要求;}else {return 0;}");
        System.out.println(obj);
        ctx.put("a", d1);
        ctx.put("b", d2);
        Object obj1 = ElUtil.exec(ctx, "if (a > b) { return a - b; } else {return b - a; }");
        System.out.println(obj1);*/

    }


}
