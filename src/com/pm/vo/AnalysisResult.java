package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;

/**
 * 数据分析结果
 * @author Administrator
 */
public class AnalysisResult implements Serializable {




    @EntityAnnotation(item_name="分析类型",item_sort=2)
    private String analysis_type;


    private String item_id;

    @EntityAnnotation(item_name="类别",item_sort=10)
    private String item_name;








    @EntityAnnotation(item_name="统计金额",item_sort=100)
    private double curr_statistics_amount;


    @EntityAnnotation(item_name="同比金额",item_sort=110)
    private double pre_statistics_amount;



    @EntityAnnotation(item_name="增减额",item_sort=120)
    private double increase_or_decrease;


    @EntityAnnotation(item_name="变动比例",item_sort=130)
    private double change_ratio;

    public String getAnalysis_type() {
        return analysis_type;
    }

    public void setAnalysis_type(String analysis_type) {
        this.analysis_type = analysis_type;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getCurr_statistics_amount() {
        return curr_statistics_amount;
    }

    public void setCurr_statistics_amount(double curr_statistics_amount) {
        this.curr_statistics_amount = curr_statistics_amount;
    }

    public double getPre_statistics_amount() {
        return pre_statistics_amount;
    }

    public void setPre_statistics_amount(double pre_statistics_amount) {
        this.pre_statistics_amount = pre_statistics_amount;
    }

    public double getIncrease_or_decrease() {
        return increase_or_decrease;
    }

    public void setIncrease_or_decrease(double increase_or_decrease) {
        this.increase_or_decrease = increase_or_decrease;
    }

    public double getChange_ratio() {
        return change_ratio;
    }

    public void setChange_ratio(double change_ratio) {
        this.change_ratio = change_ratio;
    }





}
