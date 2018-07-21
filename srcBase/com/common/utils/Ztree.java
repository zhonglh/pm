package com.common.utils;

@SuppressWarnings("serial")
public class Ztree implements java.io.Serializable{

	private String id;
	private String pid;
	private String no;
	private String name;
	
	private boolean open;	
	boolean dropRoot;
	boolean drag = true ;
	boolean checked;
	boolean nocheck ;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public boolean isDropRoot() {
		return dropRoot;
	}
	public void setDropRoot(boolean dropRoot) {
		this.dropRoot = dropRoot;
	}
	public boolean isDrag() {
		return drag;
	}
	public void setDrag(boolean drag) {
		this.drag = drag;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	
	
	
	
}
