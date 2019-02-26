package com.pm.util.constant;

/**
 * 项目合同(附件)类型
 * @author Administrator
 */

public enum EnumProjectContractType {


    PAY("0" , "付款合同"),
    GATHER("1" , "收款合同"),

            ;


    private String code;
    private String label;



    private EnumProjectContractType(String code, String label) {
        this.code = code;
        this.label = label;
    }


    public static EnumProjectContractType getEnumByCode(String code){
        for(EnumProjectContractType enum1 : EnumProjectContractType.values()){
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
