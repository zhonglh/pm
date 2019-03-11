package com.pm.util.constant;

/**
 * 总部人员  成本中心类型
 * @author Administrator
 */

public enum EnumCostCenter {

    CC1("1","总部人员成本"),
    CC2("2","销售成本"),
    CC3("3","部门管理成本"),
    ;


    private String code;
    private String label;



    private EnumCostCenter(String code, String label) {
        this.code = code;
        this.label = label;
    }


    public static EnumCostCenter getEnumByCode(String code){
        for(EnumCostCenter enum1 : EnumCostCenter.values()){
            if(enum1.getCode().equals(code)){
                return enum1;
            }
        }
        return null;
    }


    public static EnumCostCenter getEnumByLabel(String label){
        for(EnumCostCenter enum1 : EnumCostCenter.values()){
            if(enum1.getLabel().equals(label)){
                return enum1;
            }
        }
        return null;
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
