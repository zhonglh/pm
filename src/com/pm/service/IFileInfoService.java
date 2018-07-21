package com.pm.service;

import com.pm.domain.business.FileInfo;
import com.pm.vo.UserPermit;

import java.util.List;

import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IFileInfoService extends IGeneralLogService {
	
	//获取上级路径
	public String getParentPath(String parent_id);
	//获取自己的路径
	public String getSelfPath(String id);
	
	

	//获取下级
	public List<FileInfo> getChilds(String id , FileInfo otherSearchInfo);
	public List<FileInfo> getChilds(FileInfo fileInfo , FileInfo otherSearchInfo);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_FILEINFO)
	public int addFileInfo(FileInfo fileInfo) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_FILEINFO)
	public int updateFileInfo(FileInfo fileInfo) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_FILEINFO)
	public void deleteFileInfo(FileInfo[] fileInfos) ;
	

	public int updateFileInfoParentId(FileInfo fileInfo) ; 
	

	public void cancelShareFileInfo(FileInfo[] fileInfos) ;


	public FileInfo getFileInfo(String id) ;	
	

	public List<FileInfo> getAll(FileInfo fileInfo);

	/**
	 * 我的文件
	 * @param fileInfo
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<FileInfo> queryFileInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager);
	
	/**
	 * 我分享的文件
	 * @param fileInfo
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<FileInfo> queryFileShareInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager);

	/**
	 * 分享给我的文件
	 * @param fileInfo
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<FileInfo> queryFileShareMyInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager);
	
	
	

}