package com.pm.util.constant;

/**
 * 数据范围
 * @author Administrator
 */

public enum EnumDataRange {

    All("1" , "全部"),
    SELF_DEPT("2" , "本部门"),
    SELF("3" , "本人"),

    ;


    private String code;
    private String label;



    private EnumDataRange(String code, String label) {
        this.code = code;
        this.label = label;
    }


    public static EnumDataRange getEnumByCode(String code){
        for(EnumDataRange enum1 : EnumDataRange.values()){
            if(enum1.getCode().equals(code)){
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



