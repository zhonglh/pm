package com.pm.domain.business;


/**
 * 字典表类型
 * @author zhonglihong
 * @date 2016年5月12日 上午12:56:37
 */
public class DicType extends IdEntity{
	
	private String dic_type_name;

	public String getDic_type_name() {
		return dic_type_name;
	}
	public void setDic_type_name(String dic_type_name) {
		this.dic_type_name = dic_type_name;
	}
	
	@Override
	public String toString() {
		return dic_type_name;
	}

}
