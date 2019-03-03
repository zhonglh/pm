package com.pm.vo;

/**
 * @author Administrator
 */
public class AnalysisSalesVo extends AnalysisDepartVo{

    private String sales_id;

    private String sales_name;


    public String getSales_id() {
        return sales_id;
    }

    public void setSales_id(String sales_id) {
        this.sales_id = sales_id;
    }

    public String getSales_name() {
        return sales_name;
    }

    public void setSales_name(String sales_name) {
        this.sales_name = sales_name;
    }
}
