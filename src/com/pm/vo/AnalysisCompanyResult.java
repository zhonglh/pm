package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

/**
 * 公司层门分析结果
 * @author Administrator
 */
public class AnalysisCompanyResult extends  AnalysisResult {


    private String item_id;

    @EntityAnnotation(item_name="类别",item_sort=10)
    private String item_name;


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
}
