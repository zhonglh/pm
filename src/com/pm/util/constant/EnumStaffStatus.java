package com.pm.util.constant;

/**
 * 员工状态
 * @author Administrator
 */

public enum EnumStaffStatus {

    Normal("1" , "正常"),
    WaitingPost("2" , "待岗"),
    MaternityLeave("3" , "产假"),
    Medical("4" , "医疗假期"),

    ;

    private String code;
    private String val;



    private EnumStaffStatus(String code, String val) {
        this.code = code;
        this.val = val;
    }


    public static EnumStaffStatus getEnumByCode(String code){
        for(EnumStaffStatus enum1 : EnumStaffStatus.values()){
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
