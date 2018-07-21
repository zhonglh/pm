package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IFileInfoDao;
import com.pm.domain.business.FileInfo;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class FileInfoDaoImpl extends BatisDao implements IFileInfoDao  {

	@Override
	public int addFileInfo(FileInfo fileInfo) {
		String sql = "FileInfoMapping.addFileInfo";
		return this.insert(sql, fileInfo);
	}

	@Override
	public int updateFileInfo(FileInfo fileInfo) {
		String sql = "FileInfoMapping.updateFileInfo";
		return this.update(sql, fileInfo);
	}
	

	@Override
	public int updateFileInfoParentId(FileInfo fileInfo) {
		String sql = "FileInfoMapping.updateFileInfoParentId";
		return this.update(sql, fileInfo);
	}

	@Override
	public void deleteFileInfo(FileInfo fileInfo) {
		String sql = "FileInfoMapping.deleteFileInfo";
		this.delete(sql, fileInfo);
	}


	@Override
	public FileInfo getFileInfo(String id) {

		String sql = "FileInfoMapping.getFileInfo"; 
		FileInfo fileInfo = new FileInfo(); 
		fileInfo.setId(id); 
		List<FileInfo> list = this.query(sql, FileInfo.class, fileInfo); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}
	

	@Override
	public List<FileInfo> getAll(FileInfo fileInfo){
		String sql = "FileInfoMapping.getAll"; 
		return  this.query(sql, FileInfo.class, fileInfo); 
	}

	@Override
	public Pager<FileInfo> queryFileInfo(
		FileInfo fileInfo,
		UserPermit userPermit,
		Pager<FileInfo> pager){

		String sql = "FileInfoMapping.queryFileInfo"; 
		Pager<FileInfo> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,FileInfo.class, fileInfo,userPermit); 
		
		return pager1;
	}
	

	public Pager<FileInfo> queryFileShareInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager) {

		String sql = "FileInfoMapping.queryFileShareInfo"; 
		Pager<FileInfo> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,FileInfo.class, fileInfo,userPermit); 
		
		return pager1;
	}
	

	public Pager<FileInfo> queryFileShareMyInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager) {
		String sql = "FileInfoMapping.queryFileShareMyInfo"; 
		Pager<FileInfo> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,FileInfo.class, fileInfo,userPermit); 
		
		return pager1;
	}


}