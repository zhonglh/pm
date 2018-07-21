package com.pm.domain.business;

import java.sql.Date;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

/**
 * 文件信息
 * @author zhonglihong
 * @date 2016年5月13日 上午10:26:51
 */
@SuppressWarnings("serial")
public class FileInfo extends IdEntity implements java.io.Serializable{
	
	
	private String file_name;
	

	private long file_size ;	

	@EntityAnnotation(item_name="文件名" ,item_sort=1, length=30)
	private String show_name;
	

	@EntityAnnotation(item_name="文件类型" ,item_sort=2, length=30)
	private String ext;	
	
	private String is_folder;
	private String parent_id;
	private String file_md5;
	private String is_share;
	
	
	private Timestamp build_datetime;
	private String build_userid;
	private String build_username;
	
	
	
	/////////////////用于显示////////////////
	
	//文件打下格式化
	private String show_size;
	
	//分享时间
	private Timestamp share_datetime;
	
	//分享人名称
	private Timestamp share_username;
	
	
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getIs_folder() {
		return is_folder;
	}
	public void setIs_folder(String is_folder) {
		this.is_folder = is_folder;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getFile_md5() {
		return file_md5;
	}
	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	public String getIs_share() {
		return is_share;
	}
	public void setIs_share(String is_share) {
		this.is_share = is_share;
	}
	public Timestamp getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	public String getShow_size() {
		return show_size;
	}
	public void setShow_size(String show_size) {
		this.show_size = show_size;
	}
	public Timestamp getShare_datetime() {
		return share_datetime;
	}
	public void setShare_datetime(Timestamp share_datetime) {
		this.share_datetime = share_datetime;
	}
	public Timestamp getShare_username() {
		return share_username;
	}
	public void setShare_username(Timestamp share_username) {
		this.share_username = share_username;
	}
	
	
	
	
	

}
