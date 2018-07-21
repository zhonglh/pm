package com.pm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.enums.EnumYesNo;
import com.common.utils.file.FileStoreHelper;
import com.pm.dao.IFileInfoDao;
import com.pm.dao.IShareInfoDao;
import com.pm.dao.IShareToUserDao;
import com.pm.domain.business.FileInfo;
import com.pm.domain.business.ShareInfo;
import com.pm.domain.business.ShareToUser;
import com.pm.service.IFileInfoService;
import com.pm.vo.UserPermit;

@Component
public class FileInfoServiceImpl implements  IFileInfoService {

	@Autowired IFileInfoDao fileInfoDao;

	@Autowired IShareInfoDao shareInfoDao;

	@Autowired IShareToUserDao shareToUserDao;
	
	

	@Override
	public String getParentPath(String parent_id){
		StringBuffer sb = new StringBuffer("");
		String parentId = parent_id;
		while(StringUtils.isNotEmpty(parentId)){
			FileInfo parentFile = getFileInfo(parentId);				
			parentId = parentFile.getParent_id();
			sb.insert(0, "/"+parentFile.getFile_name());
		}
		return sb.toString();
	}
	
	
	public String getSelfPath(String id){
		if(id == null || id.isEmpty()) return "";
		FileInfo fileInfo = getFileInfo(id);
		String parentPath = getParentPath(fileInfo.getParent_id());
		return parentPath + "/" + fileInfo.getFile_name();
	}
	
	

	public List<FileInfo> getChilds(String id , FileInfo otherSearchInfo){
		FileInfo fileInfo = this.getFileInfo(id);
		return getChilds(fileInfo,otherSearchInfo);		
	}
	

	public List<FileInfo> getChilds(FileInfo fileInfo , FileInfo otherSearchInfo){
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		if(fileInfo.getIs_folder().equals(EnumYesNo.No.getCode())) return fileInfos;
		else {
			getChild(fileInfo,otherSearchInfo,fileInfos);
			return fileInfos;
		}
		
	}
	

	public void getChild(FileInfo fileInfo , FileInfo otherSearchInfo , List<FileInfo> childs){
		
		
		FileInfo searchFileInfo = new FileInfo();
		searchFileInfo.setParent_id(fileInfo.getId());
		searchFileInfo.setBuild_userid(fileInfo.getBuild_userid());
		searchFileInfo.setIs_folder(fileInfo.getIs_folder());
		searchFileInfo.setIs_share(fileInfo.getIs_share());
		
		List<FileInfo> list = getAll(searchFileInfo);
		if(list == null || list.isEmpty()) return ;
		else {
			childs.addAll(list);
			for(FileInfo temp : list){
				if(temp.getIs_folder().equals(EnumYesNo.No.getCode())) continue ;
				else getChild(temp,otherSearchInfo,childs);
			}
		}
		
	}
	
	
	@Override
	public int addFileInfo(FileInfo fileInfo) {
		return fileInfoDao.addFileInfo(fileInfo);
	}

	@Override
	public int updateFileInfo(FileInfo fileInfo) {
		return fileInfoDao.updateFileInfo(fileInfo);
	}
	
	

	@Override
	public int updateFileInfoParentId(FileInfo fileInfo) {

		return fileInfoDao.updateFileInfoParentId(fileInfo);
	}

	@Override
	public void deleteFileInfo(FileInfo[] fileInfos) {
		for(FileInfo fileInfo : fileInfos){
			fileInfo = fileInfoDao.getFileInfo(fileInfo.getId());
			String parentPath = getParentPath(fileInfo.getParent_id());
	        String model = fileInfo.getBuild_userid()+parentPath;
			String key = fileInfo.getFile_name();
			FileStoreHelper.deleteFile(model, key);
			
			
			if(fileInfo.getIs_share().equals(EnumYesNo.Yes.getCode())){
				ShareInfo shareInfo = new ShareInfo();
				shareInfo.setId(fileInfo.getId());
				shareInfoDao.deleteShareInfo(shareInfo);
				
				ShareToUser shareToUser = new ShareToUser();
				shareToUser.setShare_id(shareInfo.getId());
				shareToUserDao.deleteShareToUserByShare(shareToUser);				
			}
			fileInfoDao.deleteFileInfo(fileInfo);
		}
	}
	

	@Override
	public void cancelShareFileInfo(FileInfo[] fileInfos) {
		for(int i=0;i<fileInfos.length;i++) cancelShareFileInfo(fileInfos[i]);
	}
	

	public void cancelShareFileInfo(FileInfo fileInfo) {
		fileInfo = fileInfoDao.getFileInfo(fileInfo.getId());
		fileInfo.setIs_share(EnumYesNo.No.getCode());
		fileInfoDao.updateFileInfo(fileInfo);
		
		ShareInfo shareInfo = new ShareInfo();
		shareInfo.setId(fileInfo.getId());
		shareInfoDao.deleteShareInfo(shareInfo);
		
		ShareToUser shareToUser = new ShareToUser();
		shareToUser.setShare_id(shareInfo.getId());
		shareToUserDao.deleteShareToUserByShare(shareToUser);
	}


	@Override
	public FileInfo getFileInfo(String id) {
		return fileInfoDao.getFileInfo(id);
	}
	

	@Override
	public List<FileInfo> getAll(FileInfo fileInfo){
		return fileInfoDao.getAll(fileInfo);
	}

	@Override
	public Pager<FileInfo> queryFileInfo(
		FileInfo fileInfo,
		UserPermit userPermit,
		Pager<FileInfo> pager){

		return fileInfoDao.queryFileInfo(fileInfo, userPermit, pager);
	}
	

	public Pager<FileInfo> queryFileShareInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager){

		return fileInfoDao.queryFileShareInfo(fileInfo, userPermit, pager);
	}
	

	public Pager<FileInfo> queryFileShareMyInfo(FileInfo fileInfo,  UserPermit userPermit,Pager<FileInfo> pager){

		return fileInfoDao.queryFileShareMyInfo(fileInfo, userPermit, pager);
	}
	
	

	@Override
	public <T> T get(String id) {
		return (T)getFileInfo(id);
	}


}