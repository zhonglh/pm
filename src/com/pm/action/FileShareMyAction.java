package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.file.FileKit;
import com.pm.domain.business.FileInfo;
import com.pm.domain.system.User;
import com.pm.service.IFileInfoService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;

/**
 * 分享给我的文件
 * @author zhonglihong
 * @date 2017年6月13日 下午6:56:35
 */
@Controller
@RequestMapping("FileShareMyAction.do")
public class FileShareMyAction extends BaseAction {

	private static final String sessionAttr = "FileInfos";

	private static final String rel = "rel73";
	
	@Autowired
	private IFileInfoService fileInfoService;
	
	@Autowired
	private IRoleService roleService;

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
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.FILEINFOSHAREMY.getId());

		paramprocess(request,fileInfo);
		
		fileInfo.setBuild_userid(sessionUser.getUser_id());
		Pager<FileInfo> pager = fileInfoService.queryFileShareMyInfo(fileInfo, userPermit, PubMethod.getPager(request, FileInfo.class));
		
		
		for(FileInfo file : pager.getResultList()){
			file.setShow_size(FileKit.getFileFormatSize(file.getFile_size()));
		}
		
		PubMethod.setRequestPager(request, pager,FileInfo.class);	
		
		

		return "shareinfo/fileinfo_sharemy_list";
	}


	private void paramprocess(HttpServletRequest request,FileInfo fileInfo){
	}
	




}