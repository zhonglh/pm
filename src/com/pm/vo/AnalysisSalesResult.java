package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

/**
 * 销售层面分析结果
 * @author Administrator
 */
public class AnalysisSalesResult extends  AnalysisResult {

    private String sales_staff_id ;


    @EntityAnnotation(item_name="销售姓名",item_sort=10)
    private String sales_staff_name;

    public String getSales_staff_id() {
        return sales_staff_id;
    }

    public void setSales_staff_id(String sales_staff_id) {
        this.sales_staff_id = sales_staff_id;
    }

    public String getSales_staff_name() {
        return sales_staff_name;
    }

    public void setSales_staff_name(String sales_staff_name) {
        this.sales_staff_name = sales_staff_name;
    }
}
