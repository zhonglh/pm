package com.pm.dao;

import com.pm.domain.business.FileInfo;
import com.pm.vo.UserPermit;

import java.util.List;

import com.common.beans.Pager;

public interface IFileInfoDao {

	public int addFileInfo(FileInfo fileInfo) ;

	public int updateFileInfo(FileInfo fileInfo) ; 
	


	public int updateFileInfoParentId(FileInfo fileInfo) ; 

	public void deleteFileInfo(FileInfo fileInfo) ;

	public FileInfo getFileInfo(String id) ;	
	

	public List<FileInfo> getAll(FileInfo fileInfo);

	public Pager<FileInfo> queryFileInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager);
	

	public Pager<FileInfo> queryFileShareInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager) ;
	

	public Pager<FileInfo> queryFileShareMyInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager) ;

}