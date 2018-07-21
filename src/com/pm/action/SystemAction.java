package com.pm.action;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.databackup.AbstractData;
import com.common.databackup.MySql;
import com.common.utils.DateKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.system.User;

@Controller
@RequestMapping(value = "SystemAction.do")
public class SystemAction extends BaseAction {
	
	


	@RequestMapping(params = "method=backupMysql")
	public void backupMysql(User user,HttpServletResponse response,HttpServletRequest request){
		
		try{

			String tempdir = request.getSession().getServletContext().getRealPath("") + File.separator + "temp";
			File f = new File(tempdir);
			if(f != null && !f.exists()) f.mkdirs();
			
			String filename = DateKit.fmtDateToStr(new Date(),"yyyyMMddHHmmss")+".dmp";
			String fullfilename = f.getAbsolutePath() + File.separator + filename;
			AbstractData db  = new MySql(fullfilename,this.getDbBin());
			db.backup();			
			
			DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();	
			downloadBaseUtil.download(  fullfilename,  filename ,response,true);	
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("备份系统数据错误，可能是数据库BIN目录配置错误！");
		}
		
		
	}
	
	

}
