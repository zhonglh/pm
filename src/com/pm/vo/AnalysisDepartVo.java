package com.pm.vo;

/**
 * 部门分析
 * @author Administrator
 */
public class AnalysisDepartVo extends AnalysisVo {

    private String dept_id ;

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
