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
    private String val;



    private EnumDataRange(String code, String val) {
        this.code = code;
        this.val = val;
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
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
}



