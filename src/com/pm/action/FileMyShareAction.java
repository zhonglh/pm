package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.enums.EnumYesNo;
import com.common.utils.file.FileKit;
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
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;

/**
 * 我的分享文件
 * @author zhonglihong
 * @date 2017年6月13日 下午6:56:35
 */
@Controller
@RequestMapping("FileMyShareAction.do")
public class FileMyShareAction extends BaseAction {

	private static final String sessionAttr = "FileInfos";

	private static final String rel = "rel72";

	
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
	 * 
	 * @param fileInfo
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=list")
	public String list(FileInfo fileInfo,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.FILEINFOMYSHARE.getId());

		paramprocess(request,fileInfo);
		
		fileInfo.setIs_share(EnumYesNo.Yes.getCode());
		fileInfo.setBuild_userid(sessionUser.getUser_id());
		Pager<FileInfo> pager = fileInfoService.queryFileShareInfo(fileInfo, userPermit, PubMethod.getPager(request, FileInfo.class));
		
		
		for(FileInfo file : pager.getResultList()){
			file.setShow_size(FileKit.getFileFormatSize(file.getFile_size()));
		}
		
		PubMethod.setRequestPager(request, pager,FileInfo.class);	
		
		

		return "shareinfo/fileinfo_myshare_list";
	}


	private void paramprocess(HttpServletRequest request,FileInfo fileInfo){
	}
	
	
	@RequestMapping(params = "method=toShareInfo")
	public String toShareInfo(String file_id,HttpServletResponse res,HttpServletRequest request){
		List<ShareToUser> toUsers = null;
		ShareInfo shareInfo1 = shareInfoService.getShareInfo(file_id);	
		if(shareInfo1.getIs_share_all().equals(EnumYesNo.No.getCode())){
			toUsers = shareToUserService.getShareToUserByShare(file_id);
			
		}
		request.setAttribute("shareInfo1", shareInfo1);
		request.setAttribute("toUsers", toUsers);
		return "shareinfo/fileinfo_myshare_shareinfo";
	}
	


	
	/**
	 * 取消分享
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=cancelShare")
	public String cancelShare(HttpServletResponse res,HttpServletRequest request){
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
			fileInfoService.cancelShareFileInfo(fileInfos);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	





}