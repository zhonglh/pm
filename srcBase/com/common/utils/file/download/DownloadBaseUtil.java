package com.common.utils.file.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadBaseUtil {

    /**
     * @param filePath
     *            要下载的文件路径
     * @param returnName
     *            返回的文件名
     * @param response
     *            HttpServletResponse
     * @param delFlag
     *            是否删除文件
     */
    // HttpServletResponse res,HttpServletRequest req
    public static void download(String filePath,String returnName,HttpServletResponse response,boolean delFlag){
        prototypeDownload (new File (filePath), returnName, response, delFlag);
    }

    /**
     * @param file
     *            要下载的文件
     * @param returnName
     *            返回的文件名
     * @param response
     *            HttpServletResponse
     * @param delFlag
     *            是否删除文件
     */
    public static void download(File file,String returnName,HttpServletResponse response,boolean delFlag){
        prototypeDownload (file, returnName, response, delFlag);
    }
    
    
    public static void setFileDownloadHeader(HttpServletRequest request,
            HttpServletResponse response, String fileName)
            throws UnsupportedEncodingException {
        // 中文文件名支持
        String encodedFileName = null;
        // 替换空格，否则firefox下有空格文件名会被截断,其他浏览器会将空格替换成+号
        encodedFileName = fileName.trim().replaceAll(" ", "_");

        String agent = request.getHeader("User-Agent");
        boolean isMSIE = ((agent != null) && (agent.toUpperCase().indexOf("MSIE") != -1));

        if (isMSIE) {
            encodedFileName = URLEncoder.encode(encodedFileName, "UTF-8");
        } else {
            encodedFileName = new String(fileName.getBytes("UTF-8"),
                    "ISO8859-1");
        }

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + encodedFileName + "\"");
    }
    

    /**
     * @param file
     *            要下载的文件
     * @param returnName
     *            返回的文件名
     * @param response
     *            HttpServletResponse
     * @param delFlag
     *            是否删除文件
     */
    public static void prototypeDownload(File file,String returnName,HttpServletResponse response,boolean delFlag){
        // 下载文件
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            if (!file.exists ()) {
                return;
            }
            response.reset ();
            // 设置响应类型 PDF文件为"application/pdf"，WORD文件为："application/msword"， EXCEL文件为："application/vnd.ms-excel"。
            response.setContentType ("application/octet-stream;charset=utf-8");


            // attachment作为附件下载；inline客户端机器有安装匹配程序，则直接打开；注意改变配置，清除缓存，否则可能不能看到效果
            if(returnName == null || returnName.isEmpty()){
                  response.addHeader ("Content-Disposition", "attachment");
            }else{
                  // 设置响应的文件名称,并转换成中文编码
                  // returnName = URLEncoder.encode(returnName,"UTF-8");
                  returnName = response.encodeURL (new String (returnName.getBytes (),"iso8859-1")); // 保存的文件名,必须和页面编码一致,否则乱码
            	response.addHeader ("Content-Disposition", "attachment;filename=" + returnName);
            }

            // 将文件读入响应流
            inputStream = new FileInputStream (file);
            outputStream = response.getOutputStream ();
            int length = 1024;
            int readLength = 0;
            byte buf[] = new byte[1024];
            readLength = inputStream.read (buf, 0, length);
            while (readLength != -1) {
                outputStream.write (buf, 0, readLength);
                readLength = inputStream.read (buf, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
        	if (outputStream != null){
        		try {
                    outputStream.flush ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
                try {
                    outputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
        	}
            if (inputStream !=null){
            	try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
            // 删除原文件
            if (delFlag) {
                file.delete ();
            }
        }
    }

    /**
     * by tony 2013-10-17
     * 
     * @param byteArrayOutputStream
     *            将文件内容写入ByteArrayOutputStream
     * @param response
     *            HttpServletResponse 写入response
     * @param returnName
     *            返回的文件名
     */
    public static void download(ByteArrayOutputStream byteArrayOutputStream,HttpServletResponse response,String returnName) throws IOException{
        response.setContentType ("application/octet-stream;charset=utf-8");
        returnName = response.encodeURL (new String (returnName.getBytes (),"iso8859-1")); // 保存的文件名,必须和页面编码一致,否则乱码
        response.addHeader ("Content-Disposition", "attachment;filename=" + returnName);
        response.setContentLength (byteArrayOutputStream.size ());

        ServletOutputStream outputstream = response.getOutputStream (); // 取得输出流
        byteArrayOutputStream.writeTo (outputstream); // 写到输出流
        byteArrayOutputStream.close (); // 关闭
        outputstream.flush (); // 刷数据
    }
}
