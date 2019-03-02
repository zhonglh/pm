package com.pm.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分析结果表
 * @author Administrator
 */
public class AnalysisResultTable implements Serializable {

    private String label ;

    private List<AnalysisResult> result;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<AnalysisResult> getResult() {
        return result;
    }

    public void setResult(List<AnalysisResult> result) {
        this.result = result;
    }
}
