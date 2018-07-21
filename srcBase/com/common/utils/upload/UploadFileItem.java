package com.common.utils.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

/**
 * @ClassName: ComputeUtil
 * @Title:
 * @Description:XCAT主机模版文件上传工具类
 * @Author:Hongli
 * @Since:2013-9-23下午4:15:59
 * @Version:1.0
 */
public class UploadFileItem {
	//private static final Logger logger = LoggerFactory.getLogger (UploadFileItem.class);

    /**
     * 从请求中获取上传的licence文件
     * 
     * @param request
     * @return
     * @throws FileUploadException
     */
    public static FileItem getUploadFileItem(HttpServletRequest request) throws FileUploadException{
        FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker (request.getSession ().getServletContext ());
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory ();
        diskFileItemFactory.setFileCleaningTracker (fileCleaningTracker);
        ServletFileUpload servletFileUpload = new ServletFileUpload (diskFileItemFactory);
        servletFileUpload.setHeaderEncoding ("utf-8");
        List<?> list = servletFileUpload.parseRequest (request);
        for ( int i = 0 ; i < list.size () ; i++ ) {
            FileItem fileItem = (FileItem) list.get (i);
            if (!fileItem.isFormField () && fileItem.getName ().length () > 0) { return fileItem; }
        }
        return null;
    }
}
