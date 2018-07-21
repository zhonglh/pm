package com.common.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileSystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.common.actions.BaseAction;
import com.common.utils.file.FileKit;
import com.common.utils.upload.IoUtil;
import com.common.utils.upload.Range;
import com.common.utils.upload.TokenUtil;

/**
 * @ClassName: SoftBundleAction
 * @Title:
 * @Description:软件包管理ACTION
 * @Author:Hongli
 * @Since:2014年3月19日上午9:45:16
 * @Version:1.0
 */
@Controller
@RequestMapping(value = "/FileUpLoadAction.do")
public class FileUpLoadAction extends BaseAction {

    private static Logger      logger               = LoggerFactory.getLogger (FileUpLoadAction.class);
    static final String        FILE_NAME_FIELD      = "name";
    static final String        FILE_SIZE_FIELD      = "size";
    static final String        TOKEN_FIELD          = "token";
    static final String        SERVER_FIELD         = "server";
    static final String        SUCCESS              = "success";
    static final String        MESSAGE              = "message";

    /** mark it whether cross domain for uploading */
    static final String        CROSS                = "CROSS";
    static final String        SERVER               = "SERVER";
    boolean                    cross                = false;
    String                     server               = null;

    static final String        CROSS_ORIGIN         = "CROSS_ORIGIN";
    /** the other class program should not modify it */
    public static boolean      DELETE_FINISH        = false;
    // private String origins = "*";
    /** when the has increased to 10kb, then flush it to the hard-disk. */
    static final int           BUFFER_LENGTH        = 10240;
    static final String        START_FIELD          = "start";
    public static final String CONTENT_RANGE_HEADER = "content-range";
    static final String        FILE_FIELD           = "file";
    /** when the has read to 10M, then flush it to the hard-disk. */
    public static final int    FLASH_BUFFER_LENGTH  = 1024 * 1024 * 10;
    static final int           FLASH_MAX_FILE_SIZE  = 1024 * 1024 * 100;

    @RequestMapping(params = "method=token")
    public void getToken(HttpServletResponse res,HttpServletRequest req){
        String name = req.getParameter (FILE_NAME_FIELD);
        String size = req.getParameter (FILE_SIZE_FIELD);
        res.setHeader ("Access-Control-Allow-Origin", "*");

        res.setContentType ("text/html;charset=UTF-8");
        try {
            String token = TokenUtil.generateToken (name, size);
            JSONObject json = new JSONObject ();
            json.put (TOKEN_FIELD, token);
            if (cross) json.put (SERVER_FIELD, server);
            json.put (SUCCESS, true);
            json.put (MESSAGE, "");
            writeResJson (res, json);
        } catch (Exception e) {
            logger.error (e.getMessage (), e);
        }
        /** : save the token. */
    }

    @RequestMapping(params = "method=lookup")
    public void lookup(HttpServletResponse res,HttpServletRequest req){
        res.setContentType ("application/json");
        res.setHeader ("Access-Control-Allow-Origin", "*");

        res.setContentType ("text/html;charset=UTF-8");
        res.setHeader ("Access-Control-Allow-Headers", "Content-Range,Content-Type");

        res.setHeader ("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        final String token = req.getParameter (TOKEN_FIELD);
        final String size = req.getParameter (FILE_SIZE_FIELD);
        final String fileName = FileKit.getFileName (req.getParameter (FILE_NAME_FIELD));
        /** : validate your token. */
        JSONObject json = new JSONObject ();
        long start = 0;
        boolean success = true;
        String message = "";
        try {
            File f = IoUtil.getTokenedFile (token);
            start = f.length ();
            /** file size is 0 bytes. */
            if (token.endsWith ("_0") && "0".equals (size) && 0 == start) f.renameTo (IoUtil.getFile (fileName));
        } catch (Exception fne) {
            message = "Error: " + fne.getMessage ();
            success = false;
            logger.error (fne.getMessage (), fne);
        } finally {
            try {
                if (success) json.put (START_FIELD, start);
                json.put (SUCCESS, success);
                json.put (MESSAGE, message);
            } catch (Exception e) {
                logger.error (e.getMessage (), e);
            }
            writeResJson (res, json);
        }
    }

    @RequestMapping(params = "method=upload")
    public void upload(HttpServletResponse res,HttpServletRequest req) throws IOException{
        res.setContentType ("application/json");
        res.setHeader ("Access-Control-Allow-Origin", "*");

        res.setContentType ("text/html;charset=UTF-8");
        res.setHeader ("Access-Control-Allow-Headers", "Content-Range,Content-Type");

        res.setHeader ("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

        final String token = req.getParameter (TOKEN_FIELD);
        final String fileName = FileKit.getFileName (new String(req.getParameter (FILE_NAME_FIELD).getBytes ("ISO8859-1"),"UTF-8"));
        Range range = IoUtil.parseRange (req);
        if (null == range) {
            lookup (res, req);
            return;
        }
        OutputStream out = null;
        InputStream content = null;
        long start = 0;
        boolean success = true;
        String message = "";
        JSONObject json = new JSONObject ();
        /** : validate your token. */

        File f = IoUtil.getTokenedFile (token);
        try {
            if (FileSystemUtils.freeSpaceKb ()*1024 < range.getSize () - f.length ()) {
                throw new IOException ("There is insufficient space on the disk.");
            }
            if (f.length () != range.getFrom ()) throw new IOException ("File from position error!");

            out = new FileOutputStream (f,true);
            content = req.getInputStream ();
            int read = 0;
            final byte[] bytes = new byte[BUFFER_LENGTH];
            while ((read = content.read (bytes)) != -1)
                out.write (bytes, 0, read);
            start = f.length ();
        } catch (Exception io) {
            message = "IO Error: " + io.getMessage ();
            success = false;
            logger.error (io.getMessage (), io);
        } finally {
            IoUtil.close (out);
            IoUtil.close (content);
            try {
                /** rename the file */
                if (range.getSize () == start) {
                    /** fix the `renameTo` bug */
                    File dst = IoUtil.getFile (fileName);
                    dst.delete ();
                    f.renameTo (dst);
                    logger.debug ("文件上传成功TK: `" + token + "`, NE: `" + fileName + "`");
                    /** if `DELETE_FINISH`, then delete it. */
                    if (DELETE_FINISH) {
                        dst.delete ();
                    }
                }
                if (success) json.put (START_FIELD, start);
                json.put (SUCCESS, success);
                json.put (MESSAGE, message);
            } catch (Exception e) {
                logger.error (e.getMessage (), e);
            }
            writeResJson (res, json);
        }
    }

    @RequestMapping(params = "method=fupload")
    public void fupload(HttpServletResponse res,HttpServletRequest req) throws IOException{
        res.setContentType ("application/json");

        res.setHeader ("Access-Control-Allow-Origin", "*");
        res.setContentType ("text/html;charset=UTF-8");
        res.setHeader ("Access-Control-Allow-Headers", "Content-Range,Content-Type");

        res.setHeader ("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        /** flash @ windows bug */
        req.setCharacterEncoding ("utf8");

        final PrintWriter writer = res.getWriter ();
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent (req);
        if (!isMultipart) {
            writer.println ("ERROR: It's not Multipart form.");
            return;
        }
        JSONObject json = new JSONObject ();
        long start = 0;
        boolean success = true;
        String message = "";

        ServletFileUpload upload = new ServletFileUpload ();
        InputStream in = null;
        String token = null;
        try {
            FileItemIterator iter = upload.getItemIterator (req);
            while (iter.hasNext ()) {
                FileItemStream item = iter.next ();
                String name = FileKit.getFileName (item.getFieldName ());
                in = item.openStream ();
                if (item.isFormField ()) {
                    String value = Streams.asString (in);
                    if (TOKEN_FIELD.equals (name)) {
                        token = value;
                        /** : validate your token. */
                    }
                    
                } else {
                    String fileName = item.getName ();
                    start = IoUtil.streaming (in, token, fileName);
                }
            }
        } catch (FileUploadException fne) {
            success = false;
            message = "Error: " + fne.getLocalizedMessage ();
            logger.error (fne.getMessage (), fne);
        } finally {
            try {
                if (success) json.put (START_FIELD, start);
                json.put (SUCCESS, success);
                json.put (MESSAGE, message);
            } catch (Exception e) {
                logger.error (e.getMessage (), e);
            }
            writer.write (json.toString ());
            IoUtil.close (in);
            IoUtil.close (writer);
        }
    }
}
