package com.pm.util.excel;

/**
 * @author Administrator
 */

public enum EnumCellFormat {


    red("red" , "红色") ,

    bold("B" , "粗体") ,

    percent("%" , "百分比") ,

    ;
    private String code;
    private String label;

    EnumCellFormat(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
