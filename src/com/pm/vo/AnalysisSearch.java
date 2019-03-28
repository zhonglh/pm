package com.pm.vo;

import com.common.utils.DateKit;
import com.pm.util.PubMethod;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据分析查询条件
 * @author Administrator
 */
public class AnalysisSearch implements Serializable {


    /**
     * 查询起始月
     */
    private int month1;

    /**
     * 查询结束月
     */
    private int month2;

    /**
     * 查询开始日期
     */
    private Date date1;


    /**
     * 查询结束日期
     */
    private Date date2;


    /**
     * 部门
     */
    private String dept_id;



    /**
     * 部门名称
     */
    private String dept_name;


    /**
     * 成本和收入类型
     * 10,20,30 40  50,51,100,101 对应  收款 ，  结算单 ，  报销  ，支出费用  和项目人员成本 , 销售费用， 部门管理费用
     */
    private String x;


    private String groupSelect;

    private String groupBy;

    public int getMonth1() {
        return month1;
    }

    public void setMonth1(int month1) {
        //String strDate1 = String.valueOf(month1)+"01";
        //this.date1 = DateKit.fmtShortYMTStrToDate(strDate1);
        this.month1 = month1;
    }

    public int getMonth2() {
        return month2;
    }

    public void setMonth2(int month2) {
        //String strDate2 = String.valueOf(month2)+"01";
        //this.date2 = DateKit.getLastDayOfMonth(DateKit.fmtShortYMTStrToDate(strDate2));
        this.month2 = month2;
    }

    public Date getDate1() {
        return date1;
    }


    public Date getDate2() {
        return date2;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getGroupSelect() {
        return groupSelect;
    }

    public void setGroupSelect(String groupSelect) {
        this.groupSelect = groupSelect;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
