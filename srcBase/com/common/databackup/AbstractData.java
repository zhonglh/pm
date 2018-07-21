package com.common.databackup;

import java.util.Properties;

import com.common.cmd.ExecCmd;
import com.common.utils.file.properties.PropertyConfig;

public abstract class AbstractData {
	
	protected String datatype ;
	
	protected String[] cmd ;
	
	public static Properties jdbc = PropertyConfig.getProperties("jdbc.properties");
	
	/**
	 * 备份数据库
	 */
	public synchronized void backup(){
		ExecCmd.Runtime(cmd);
	}

}
