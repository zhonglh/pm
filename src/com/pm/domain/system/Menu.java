package com.pm.domain.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Menu implements Serializable {
	
	private String menu_id;	
	private String menu_name;
	private String menu_i18n_name;
	private int menulevel;
	private String pmenu_id;	
	private String menu_url;	
	private int order_no;	
	private String menu_type;
	private String leaf_flag;
	
	private List<Menu> childs ;
	
	
	///////////////////////////
	//////扩展
	///////////////////////////
	
	//外部地址
	private boolean external=false;
	
	
	
	
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenulevel() {
		return menulevel;
	}
	public void setMenulevel(int menulevel) {
		this.menulevel = menulevel;
	}
	public String getPmenu_id() {
		return pmenu_id;
	}
	public void setPmenu_id(String pmenu_id) {
		this.pmenu_id = pmenu_id;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getLeaf_flag() {
		return leaf_flag;
	}
	public void setLeaf_flag(String leaf_flag) {
		this.leaf_flag = leaf_flag;
	}
	public String getMenu_i18n_name() {
		return menu_i18n_name;
	}
	public void setMenu_i18n_name(String menu_i18n_name) {
		this.menu_i18n_name = menu_i18n_name;
	}
	public List<Menu> getChilds() {
		if(childs == null ) childs = new ArrayList<Menu>();
		return childs;
	}
	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}



}
