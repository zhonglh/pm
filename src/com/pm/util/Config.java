package com.pm.util;

import java.io.File;

public class Config {

	
	public static String upload_path;
	

	public static final int startRow = 2;
	
	static{		
		upload_path = new File(System.getProperty("user.dir")).getParentFile().getAbsolutePath();
	}

}
