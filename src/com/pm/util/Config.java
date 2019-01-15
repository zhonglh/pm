package com.pm.util;

import com.common.utils.file.FileKit;

import java.io.File;

public class Config {

	
	public static String upload_path;
	

	public static final int startRow = 2;
	
	static{		
		upload_path = FileKit.getServerDir();
	}

}
