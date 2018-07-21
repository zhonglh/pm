package com.common.utils.file;

import java.io.File;
import java.io.FileOutputStream;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.common.utils.IDKit;
import com.common.utils.encrypt.MacEncImpl;
import com.common.utils.encrypt.MacKit;

public class FileStoreHelper  {
    private static Logger logger = LoggerFactory.getLogger(FileStoreHelper.class);
    private static String baseDir;
    
    private static String firstpath = "uploadFile/default/user/";

    public static StoreResult getStore(String model, String key) throws Exception {
        if (key == null) {
            logger.info("key cannot be null");

            return null;
        }
        

        if (key.indexOf("../") != -1) {
            StoreResult storeResult = new StoreResult();
            storeResult.setModel(model);
            storeResult.setKey(key);

            return storeResult;
        }

        File file = new File(baseDir + "/" + firstpath + model + "/" + key);
        

        StoreResult storeResult = new StoreResult();
        storeResult.setModel(model);
        storeResult.setKey(key);

        if (!file.exists()) {
            logger.info("cannot find : {}", file);
            return null;
        }if(file.isDirectory()){
        	storeResult.setFolder(true);
	        storeResult.setDataSource(new FileDataSource(file));
        }else {
	        storeResult.setDataSource(new FileDataSource(file));	
        }
        return storeResult;
    }
    
    
    public static void moveFile(String srcPath, String destPath){
    	try{
	        File file = new File(baseDir + "/" + firstpath + srcPath);
	        if(file.isDirectory())     FileKit.moveAndReplaceFolder(baseDir + "/" + firstpath + srcPath, baseDir + "/" + firstpath + destPath);
	        else FileKit.moveAndNotReplaceFile(baseDir + "/" + firstpath + srcPath, baseDir + "/" + firstpath + destPath);
    	}catch(Exception e){
    		
    	}
    }
    
    

    public static StoreResult saveStore(String model)
            throws Exception {
        File dir = new File(baseDir + "/"  + firstpath + model );

        if (!dir.exists()) {
            boolean success = dir.mkdirs();

            if (!success) {
                logger.error("cannot create directory : {}", dir);
            }
        }


        StoreResult storeResult = new StoreResult();
        storeResult.setModel(model);
        storeResult.setDataSource(new FileDataSource(dir));

        return storeResult;
    }

   
    public static StoreResult saveStore(String model, DataSource dataSource)
            throws Exception {
        String suffix = FileKit.getSuffix(dataSource.getName());
        String path =  IDKit.generateId() + suffix;
        File dir = new File(baseDir + "/"  + firstpath + model );

        if (!dir.exists()) {
            boolean success = dir.mkdirs();

            if (!success) {
                logger.error("cannot create directory : {}", dir);
            }
        }

        File targetFile = new File(baseDir + "/"  + firstpath + model + "/" + path);
        FileOutputStream fos = new FileOutputStream(targetFile);

        try {
            FileCopyUtils.copy(dataSource.getInputStream(), fos);
            fos.flush();
        } finally {
            fos.close();
        }

        StoreResult storeResult = new StoreResult();
        storeResult.setModel(model);
        storeResult.setKey(path);
        storeResult.setDataSource(new FileDataSource(targetFile));

        return storeResult;
    }

    public static StoreResult saveStore(String model, String key, DataSource dataSource)
            throws Exception {
        String path = key;
        File dir = new File(baseDir + "/"  + firstpath + model);
        dir.mkdirs();

        File targetFile = new File(baseDir + "/"  + firstpath +  model + "/" + path);
        FileOutputStream fos = new FileOutputStream(targetFile);

        try {
            FileCopyUtils.copy(dataSource.getInputStream(), fos);
            fos.flush();
        } finally {
            fos.close();
        }

        StoreResult storeResult = new StoreResult();
        storeResult.setModel(model);
        storeResult.setKey(path);
        storeResult.setDataSource(new FileDataSource(targetFile));

        return storeResult;
    }
    
    

    
    public static void deleteFile(String model){
    	try {
    		
	    	File targetFile = new File(baseDir + "/"  + firstpath + model );
	    	if(!targetFile.exists()) return ;
	    	if(targetFile.isDirectory()){	    		
					FileKit.deleteFolder(baseDir + "/"  + firstpath + model );				
	    	}else {
	    		FileKit.deleteFile(baseDir + "/"  + firstpath + model );
	    	}
	    	
	    } catch (Exception e) {
			
		}
    }
    
    
    public static void deleteFile(String model, String key){
    	try {
    		
	    	File targetFile = new File(baseDir + "/"  + firstpath + model + "/" + key);
	    	if(!targetFile.exists()) return ;
	    	if(targetFile.isDirectory()){
	    		
					FileKit.deleteFolder(baseDir + "/"  + firstpath + model + "/" + key);
				
	    	}else {
	    		FileKit.deleteFile(baseDir + "/"  + firstpath + model + "/" + key);
	    	}
	    	
	    } catch (Exception e) {
			
		}
    }

    

	public static void setBaseDir(String baseDir) {
		FileStoreHelper.baseDir = baseDir;
	}

    
	
    


}
