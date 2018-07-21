package com.pm.domain.system;

import com.pm.domain.business.IdEntity;
import com.pm.util.log.EntityAnnotation;

/**
 * 费用项定义
 * @author zhonglihong
 * @date 2016年12月4日 下午3:44:08
 */
@SuppressWarnings("serial")
public class ItemDefine extends IdEntity implements java.io.Serializable{
	

	@EntityAnnotation(item_name="项目名称", item_sort=1)
	private String item_name;

	@EntityAnnotation(item_name="项目顺序", item_sort=2)
	private int item_order;
	
	private String item_direction;	

	@EntityAnnotation(item_name="计算公式", item_sort=4)
	private String computational_formula_text ;
	
	
	private String computational_formula;

	private String enable_state;

	@EntityAnnotation(item_name="备注", item_sort=6)
	private String description;
	
	////////////////////////////
	@EntityAnnotation(item_name="使用方向", item_sort=3)
	private String item_direction_name;	
	

	@EntityAnnotation(item_name="启用状态", item_sort=5)
	private String enable_state_name;
	
	

	public String getItem_name() {
		return item_name;
	}



	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}



	public int getItem_order() {
		return item_order;
	}



	public void setItem_order(int item_order) {
		this.item_order = item_order;
	}



	public String getItem_direction() {
		return item_direction;
	}



	public void setItem_direction(String item_direction) {
		this.item_direction = item_direction;
	}



	public String getComputational_formula() {
		return computational_formula;
	}



	public void setComputational_formula(String computational_formula) {
		this.computational_formula = computational_formula;
	}



	public String getEnable_state() {
		return enable_state;
	}



	public void setEnable_state(String enable_state) {
		this.enable_state = enable_state;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getItem_direction_name() {
		return item_direction_name;
	}



	public void setItem_direction_name(String item_direction_name) {
		this.item_direction_name = item_direction_name;
	}



	public String getEnable_state_name() {
		return enable_state_name;
	}



	public void setEnable_state_name(String enable_state_name) {
		this.enable_state_name = enable_state_name;
	}
	
		
	@Override
	public String toString() {
		return  item_name;
	}



	public String getComputational_formula_text() {
		return computational_formula_text;
	}



	public void setComputational_formula_text(String computational_formula_text) {
		this.computational_formula_text = computational_formula_text;
	}


}
