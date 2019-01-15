package com.pm.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.enums.EnumYesNo;
import com.common.utils.IDKit;
import com.common.utils.Ztree;
import com.common.utils.file.FileKit;
import com.common.utils.file.FileStoreHelper;
import com.common.utils.file.MultipartFileDataSource;
import com.common.utils.file.StoreResult;
import com.common.utils.file.ZipKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.FileInfo;
import com.pm.domain.business.ShareInfo;
import com.pm.domain.business.ShareToUser;
import com.pm.domain.system.User;
import com.pm.service.IFileInfoService;
import com.pm.service.IRoleService;
import com.pm.service.IShareInfoService;
import com.pm.service.IShareToUserService;
import com.pm.service.IUserService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("FileInfoAction.do")
public class FileInfoAction extends BaseAction {

	private static final String sessionAttr = "FileInfos";

	private static final String rel = "rel71";

	
	@Autowired
	private IFileInfoService fileInfoService;
	
	@Autowired
	private IShareInfoService shareInfoService;

	@Autowired
	private IShareToUserService shareToUserService;


	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	/**
	 * 目录
	 * @param fileInfo
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=list")
	public String list(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.FILEINFOMYFILE.getId());

		paramprocess(request,fileInfo);
		
		String parentId = fileInfo.getParent_id();
		if(StringUtils.isNotEmpty(parentId)){
			List<FileInfo> parents = new ArrayList<FileInfo>();
			while(StringUtils.isNotEmpty(parentId)){
				FileInfo parentFile = fileInfoService.get(parentId);
				parents.add(0,parentFile);
				parentId = parentFile.getParent_id();
			}
			request.setAttribute("parents", parents);
		}
		
		

		fileInfo.setBuild_userid(sessionUser.getUser_id());
		Pager<FileInfo> pager = fileInfoService.queryFileInfo(fileInfo, userPermit, PubMethod.getPager(request, FileInfo.class));
		
		
		for(FileInfo file : pager.getResultList()){
			file.setShow_size(FileKit.getFileFormatSize(file.getFile_size()));
		}
		
		PubMethod.setRequestPager(request, pager,FileInfo.class);	
		request.setAttribute("fileInfo1", fileInfo);
		
		//UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.FILEINFOADD.getId());
		//request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());		
		//userPermit1 = this.getUserPermit(request, roleService, EnumPermit.FILEINFODELETE.getId());
		//request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());		
		//userPermit1 = this.getUserPermit(request, roleService, EnumPermit.FILEINFOSHARE.getId());
		//request.setAttribute(EnumOperationType.SHARE.getKey(), userPermit1.getPermit_id());		
		//userPermit1 = this.getUserPermit(request, roleService, EnumPermit.FILEFOLDERADD.getId());
		//request.setAttribute(EnumOperationType.CREATEFOLDER.getKey(), userPermit1.getPermit_id());
		
		

		return "shareinfo/fileinfo_list";
	}


	private void paramprocess(HttpServletRequest request,FileInfo fileInfo){
	}
	
	
	


	@RequestMapping(params = "method=toAddFolder")
	public String toAddFolder(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){
		FileInfo fileInfo1 = fileInfo;
		request.setAttribute("fileInfo1", fileInfo1);
		return "shareinfo/fileinfo_folder_add";
	}

	

	@RequestMapping(params = "method=doAddFolder")
	public String doAddFolder(FileInfo addFileInfo,HttpServletResponse res,HttpServletRequest request){
		int count = 0;
		String key = IDKit.generateId();
		String model = null;
		try{
			User sessionUser = PubMethod.getUser(request);
			String parent_id = addFileInfo.getParent_id();
			
			
	        model = sessionUser.getUser_id()+fileInfoService.getParentPath(parent_id);
			FileStoreHelper.saveStore(model+"/"+key);
			
			FileInfo fileInfo = new FileInfo();	
			fileInfo.setExt("dir");
			fileInfo.setFile_name(key);
			fileInfo.setFile_size(0);
			fileInfo.setIs_folder(EnumYesNo.Yes.getCode());
			fileInfo.setIs_share(EnumYesNo.No.getCode());
			if(parent_id == null || parent_id.isEmpty()) {
				parent_id = null;
			}
			fileInfo.setParent_id(parent_id);
			fileInfo.setShow_name(addFileInfo.getShow_name());
			
			fileInfo.setId(IDKit.generateId());
			fileInfo.setBuild_datetime(PubMethod.getCurrentDate());
			fileInfo.setBuild_userid(sessionUser.getUser_id());
			fileInfo.setBuild_username(sessionUser.getUser_name());
			count = fileInfoService.addFileInfo(fileInfo);
		}catch(Exception e){
			if(model != null) {
				FileStoreHelper.deleteFile(model,key);
			}
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}else {
			return this.ajaxForwardError(request, "该文件夹已经存在！", true);
		}
	}	
	
	

	@RequestMapping(params = "method=toReName")
	public String toReName(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){
		FileInfo fileInfo1 = fileInfoService.getFileInfo(fileInfo.getId());
		request.setAttribute("fileInfo1", fileInfo1);
		return "shareinfo/fileinfo_reName";
	}



	@RequestMapping(params = "method=doReName")
	public String doReName(FileInfo fileInfo,String newName,HttpServletResponse res,HttpServletRequest request){
		FileInfo fileInfo1 = fileInfoService.getFileInfo(fileInfo.getId());
		fileInfo1.setShow_name(newName);
		int count = 0;
		try{
			count = fileInfoService.updateFileInfo(fileInfo1);
		}catch(Exception e){
			return this.ajaxForwardError(request, "该文件名称已经存在！", true);
		}		
		return this.ajaxForwardSuccess(request, rel, true);
	}
	





	@RequestMapping(params = "method=toMove")
	public String toMove(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){	
		request.setAttribute("fileInfo1", fileInfo);		
		return "shareinfo/fileinfo_move";
	}
	
	

	@RequestMapping(params = "method=folderZtree")
	public String folderZtree(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){	
		FileInfo fileInfo1 = fileInfoService.getFileInfo(fileInfo.getId());
		String parentFilter = "全部";
		if(fileInfo1.getParent_id() !=null ){
			FileInfo parent = fileInfoService.getFileInfo(fileInfo1.getParent_id());
			if(parent != null) {
				parentFilter = parent.getFile_name();
			}
		}
		
		request.setAttribute("parentFilter", parentFilter);	
		request.setAttribute("fileInfo1", fileInfo1);		
		
		return "shareinfo/folderZtree";
	}
	

	@RequestMapping(params = "method=getFolder")
	@ResponseBody
	public List<Ztree> getFolder(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){
				
		FileInfo fileInfo1 = fileInfoService.getFileInfo(fileInfo.getId());
		User sessionUser = PubMethod.getUser(request);
		
		

		FileInfo searchSileInfo = new FileInfo();
  		searchSileInfo.setBuild_userid(fileInfo1.getBuild_userid());
  		searchSileInfo.setIs_folder(EnumYesNo.Yes.getCode());
		

		List<FileInfo> childsFileInfo = fileInfoService.getChilds(fileInfo1,searchSileInfo);
		childsFileInfo.add(fileInfo1);
		List<String> childs = new ArrayList<String>();
		for(FileInfo child : childsFileInfo){
			childs.add(child.getId());
		}
		
		
		List<FileInfo> fileInfos = fileInfoService.getAll(searchSileInfo);
		
		List<Ztree> list = new ArrayList<Ztree>();	
		
		if(fileInfos != null){
			for(FileInfo temp : fileInfos ){
				
				if(childs.contains(temp.getId())) {
					continue;
				}
				
				Ztree ztree = new Ztree();
				ztree.setId(temp.getId());
				ztree.setPid(temp.getParent_id());
				ztree.setName(temp.getShow_name());	
				ztree.setNo(temp.getFile_name());		
				
				list.add(ztree);
			}
		}
		
		Ztree ztree = new Ztree();
		ztree.setName("全部");
		ztree.setNo("全部");
		ztree.setOpen(true);
		list.add(ztree);
		
		return list;
	}



	@RequestMapping(params = "method=doMove")
	public String doMove(FileInfo fileInfo, String destFolderId,HttpServletResponse res,HttpServletRequest request){		
		User sessionUser = PubMethod.getUser(request);
		FileInfo fileInfo1 = fileInfoService.getFileInfo(fileInfo.getId());
		boolean isSame = false;
		if(destFolderId == null || destFolderId.isEmpty() || destFolderId.equals("null")) {
			destFolderId = null;
		}
		
		if(destFolderId == null && fileInfo1.getParent_id() == null) {
			isSame = true;
		}
		else if(destFolderId != null && fileInfo1.getParent_id() != null && destFolderId.equals(fileInfo1.getParent_id())) {
			isSame = true;
		}
		
		if(isSame) {
			return this.ajaxForwardSuccess(request, rel, true);
		}
		
		
		String destPath = fileInfo1.getBuild_userid()+fileInfoService.getSelfPath(destFolderId)+File.separator + fileInfo1.getFile_name();
		String srcPath = fileInfo1.getBuild_userid()+fileInfoService.getSelfPath(fileInfo1.getId());
		
		try{
			fileInfo1.setParent_id(destFolderId);
			fileInfoService.updateFileInfoParentId(fileInfo1);
			FileStoreHelper.moveFile(srcPath, destPath);
		
		}catch(Exception e){
			return this.ajaxForwardError(request, "目标文件夹中已经有该文件名！", true);
		}
		
		//request.setAttribute("fileInfo1", fileInfo1);
		return this.ajaxForwardSuccess(request, rel, true);
	}



	@RequestMapping(params = "method=toAdd")
	public String toAdd(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){		
		User sessionUser = PubMethod.getUser(request);
		FileInfo fileInfo1 = fileInfo;
		request.setAttribute("fileInfo1", fileInfo1);
		return "shareinfo/fileinfo_edit";
	}
	


	@RequestMapping(params = "method=toUpload")
	public String toUpload(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){		
		User sessionUser = PubMethod.getUser(request);
		FileInfo fileInfo1 = fileInfo;
		request.setAttribute("fileInfo1", fileInfo1);
		return "shareinfo/disk_upload";
	}
	
	
	@RequestMapping(params = "method=saveFile")
    @ResponseBody
    public String saveFile(@RequestParam("file") MultipartFile file,
            @RequestParam("parent_id") String parent_id,
            HttpServletResponse res,HttpServletRequest request) throws Exception {
		
		
		User sessionUser = PubMethod.getUser(request);
		String model = null;
		String key = null;
		int count = 0;
		try{
			
			DataSource dataSource = new MultipartFileDataSource(file);
	        String show_name = file.getOriginalFilename();
	        if(show_name != null && show_name.length() > 200) {
	        	return "文件名称太长";
			}
	        long file_size = file.getSize();
	        model = sessionUser.getUser_id()+fileInfoService.getParentPath(parent_id);	
	        StoreResult result = FileStoreHelper.saveStore(model, dataSource);	        
	        key = result.getKey();
			
			FileInfo fileInfo = new FileInfo();	
			fileInfo.setExt(FileKit.getFileExt(show_name));
			fileInfo.setFile_name(key);
			fileInfo.setFile_size(file_size);
			fileInfo.setIs_folder("0");
			fileInfo.setIs_share("0");
			if(parent_id == null || parent_id.isEmpty()) {
				parent_id = null;
			}
			fileInfo.setParent_id(parent_id);
			fileInfo.setShow_name(show_name);
			
			fileInfo.setId(IDKit.generateId());
			fileInfo.setBuild_datetime(PubMethod.getCurrentDate());
			fileInfo.setBuild_userid(sessionUser.getUser_id());
			fileInfo.setBuild_username(sessionUser.getUser_name());
		
			count = fileInfoService.addFileInfo(fileInfo);
		}catch(Exception e){
			if(key != null && model != null) {
				FileStoreHelper.deleteFile(model, key);
			}
			throw e;
		}
		if(count == 1) {
			return "true";
		}
		else {
			return "false";
		}
		
    }
	
	/**
	 * 共享选择界面
	 * @param file_id
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toShareSelect")
	public String toShareSelect(String file_id,HttpServletResponse res,HttpServletRequest request){
		ShareInfo shareInfo1 = shareInfoService.getShareInfo(file_id);	
		if(shareInfo1 == null){
			shareInfo1 = new ShareInfo();
			shareInfo1.setFile_id(file_id);
			shareInfo1.setIs_share_all(EnumYesNo.Yes.getCode());
		}
		request.setAttribute("shareInfo1", shareInfo1);
		return "shareinfo/share_select";
	}
	
	@RequestMapping(params = "method=toZtree")
	public String toZtree(String file_id,HttpServletResponse res,HttpServletRequest request){	
		ShareInfo shareInfo1 = shareInfoService.getShareInfo(file_id);	
		if(shareInfo1 == null){
			shareInfo1 = new ShareInfo();
			shareInfo1.setFile_id(file_id);
			shareInfo1.setIs_share_all(EnumYesNo.Yes.getCode());
		}
		request.setAttribute("shareInfo1", shareInfo1);
		return "shareinfo/ztree";
	}
	
	
	


	@RequestMapping(params = "method=ztree",method = RequestMethod.GET, produces =  "json;charset=UTF-8")
	@ResponseBody
	public List<Ztree> ztree(String file_id ,HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);
		Map<String, String> map = new HashMap<String, String>();
		ShareInfo shareInfo1 = shareInfoService.getShareInfo(file_id);	
		if(shareInfo1 == null){
			shareInfo1 = new ShareInfo();
			shareInfo1.setFile_id(file_id);
			shareInfo1.setIs_share_all(EnumYesNo.Yes.getCode());
		}
		request.setAttribute("shareInfo1", shareInfo1);
		
		if(shareInfo1.getIs_share_all().equals(EnumYesNo.No.getCode())){
			List<ShareToUser> toUsers = shareToUserService.getShareToUserByShare(file_id);
			if(toUsers != null && !toUsers.isEmpty()){
				for(ShareToUser toUser : toUsers){
					map.put(toUser.getTo_user_id(), toUser.getTo_user_id());
				}
			}
		}
		
		
		List<Ztree> list = new ArrayList<Ztree>();		

		User user = new User();
		user.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
		Pager<User> pager = userService.queryUser(user, userPermit, PubMethod.getPagerByAll( User.class));
		
		if(pager.getResultList() != null){
			for(User user1 : pager.getResultList() ){
				
				if(sessionUser.getUser_id().equals(user1.getUser_id())) continue;
				
				Ztree ztree = new Ztree();
				ztree.setId(user1.getUser_id());
				ztree.setName(user1.getUser_name());
				ztree.setNo(user1.getUser_loginname());					
				ztree.setDrag(false);
				if(map.containsKey(user1.getUser_id())){
					ztree.setChecked(true);
				}
				list.add(ztree);
			}
		}
		
		Ztree ztree = new Ztree();
		ztree.setName("全部");
		ztree.setNo("");
		ztree.setOpen(true);
		ztree.setDrag(false);
		ztree.setNocheck(true);
		list.add(ztree);
		return list;
		
	}
	


	@RequestMapping(params = "method=saveShareInfo")
	public String saveShareInfo(ShareInfo shareInfo, String info ,HttpServletResponse res,HttpServletRequest request){
		int count = 0;
		try{
			User sessionUser = PubMethod.getUser(request);
			String is_share = EnumYesNo.Yes.getCode();
			
			FileInfo fileInfo = fileInfoService.getFileInfo(shareInfo.getFile_id());
			
			
			
			ShareInfo shareInfo1 = shareInfoService.getShareInfo(shareInfo.getFile_id());
			List<ShareToUser> toUsers = new ArrayList<ShareToUser>();
			
			String shareAll = shareInfo.getIs_share_all();
			if(shareAll!=null && (shareAll.equals("on") || shareAll.equals("1")))  {
				shareAll = EnumYesNo.Yes.getCode();
			}
			else {
				shareAll = EnumYesNo.No.getCode();
			}
			
			if(shareAll.equals(EnumYesNo.No.getCode())){
				List<Ztree> ztrees = null;			
				if(info!=null && info.length()>0) {
					ztrees = JSON.parseArray(info, Ztree.class);
				}
				if(ztrees != null && !ztrees.isEmpty()){
					for(Ztree ztree : ztrees){
						ShareToUser shareToUser = new ShareToUser();
						shareToUser.setId(IDKit.generateUUID());
						shareToUser.setShare_id(shareInfo.getFile_id());
						shareToUser.setTo_user_id(ztree.getId());
						toUsers.add(shareToUser);
					}
				}else {
					is_share = EnumYesNo.No.getCode();
				}
					
			}
			
			if(shareInfo1 == null ) {
				shareInfo1 = new ShareInfo();
			}
			
			shareInfo1.setIs_share_all(shareAll);			
			shareInfo1.setFile_id(shareInfo.getFile_id());			
			
			
			fileInfo.setIs_share(is_share);
			fileInfoService.updateFileInfo(fileInfo);
			
			if(StringUtils.isEmpty(shareInfo1.getId())){
				shareInfo1.setId(shareInfo1.getFile_id());
				shareInfo1.setBuild_datetime(PubMethod.getCurrentDate());
				shareInfo1.setBuild_userid(sessionUser.getUser_id());
				shareInfo1.setBuild_username(sessionUser.getUser_name());
				shareInfo1.setBegin_time(new java.util.Date());
				shareInfo1.setEnd_time(new java.util.Date());
				shareInfo1.setShare_user_id(sessionUser.getBuild_userid());
				count = shareInfoService.addShareInfo(shareInfo1, toUsers);
			}else {
				count = shareInfoService.updateShareInfo(shareInfo1, toUsers);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "操作失败！", true);
		}
	}	



	
	
	
	/**
     * 下载.
     */
	@RequestMapping(params = "method=download")
    public void download(@RequestParam("id") String id,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		FileInfo fileInfo = fileInfoService.getFileInfo(id);
		String patentPath = fileInfoService.getParentPath(fileInfo.getParent_id());
		String model = fileInfo.getBuild_userid()+patentPath;

        InputStream is = null;
		
		  try {
			  	StoreResult storeResult = FileStoreHelper.getStore(model, fileInfo.getFile_name()) ;
			  	
			  	if(storeResult.isFolder()){
			  		FileInfo searchSileInfo = new FileInfo();
			  		searchSileInfo.setBuild_userid(fileInfo.getBuild_userid());
					List<FileInfo> list = fileInfoService.getAll(searchSileInfo);
					Map<String,String> ctx = null;
					if(list != null && list.size() >0){
						ctx = new HashMap<String,String>();
						for(FileInfo temp : list){
							ctx.put(temp.getFile_name(), temp.getShow_name());
						}
						
					}
			  		
				  	DownloadBaseUtil.setFileDownloadHeader(request, response, fileInfo.getShow_name()+".zip");
			  		FileDataSource fds = (FileDataSource)storeResult.getDataSource();
			  		ZipKit.doZip(fds.getFile().getAbsolutePath(), response.getOutputStream(), ctx);
			  	}else {
				  	DownloadBaseUtil.setFileDownloadHeader(request, response, fileInfo.getShow_name());
			  		is = storeResult.getDataSource().getInputStream();
		            FileKit.copyStream(is, response.getOutputStream());
			  	}
	        } finally {
	            if (is != null) {
	                is.close();
	            }
	        }
    }







	@RequestMapping(params = "method=deleteFileInfo")
	public String deleteFileInfo(HttpServletResponse res,HttpServletRequest request){
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			FileInfo[] fileInfos = new FileInfo[ids.length];
			int index = 0;
			for(String id : ids){
				FileInfo fileInfo = new FileInfo();
				fileInfo.setId(id);
				fileInfos[index] = fileInfo;
				index ++ ;
			}
			if(fileInfos != null && fileInfos.length > 0)
			fileInfoService.deleteFileInfo(fileInfos);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	





}