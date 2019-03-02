package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

/**
 * 部门层面分析结果
 * @author Administrator
 */
public class AnalysisDepartResult extends  AnalysisResult {

    private String dept_id;

    @EntityAnnotation(item_name="部门名称",item_sort=10)
    private String dept_name;

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
}
