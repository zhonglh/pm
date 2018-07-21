package com.common.databackup;

import com.common.cmd.EnumOsPlatform;


/**
 * MySQL备份
 * @author zhonglihong
 * @date 2016年6月11日 下午10:11:03
 */
public class MySql extends AbstractData{	
	
	
	public MySql(String targetFullFile,String dbbindir){
		this.datatype = "mysql";
		
		String dickmark=dbbindir.substring(0,2);
		String dbname = jdbc.get("jdbc.url").toString();
		//从端口开始，计算出dbname
		dbname = dbname.substring(dbname.indexOf("33")+5,dbname.indexOf("?useUnicode"));
		String username = jdbc.get("jdbc.username").toString();
		String password = jdbc.get("jdbc.password").toString();
		String backup = "mysqldump -u"+username+" -p"+password+" -l -FR "+dbname+" > "+targetFullFile;
		String command = "cmd /c cd "+dbbindir +" & "+dickmark+" & "+backup;
		String os = System.getProperty ("os.name").toLowerCase ();
        if (os.indexOf (EnumOsPlatform.Windows.toString().toLowerCase()) == -1) {
        	backup = "./mysqldump -u"+username+" -p"+password+" -l -FRE "+dbname+" > "+targetFullFile;
        	command = "cd "+dbbindir +" & "+backup;
        }
		System.out.println("command: "+command);
		this.cmd = new String[]{command};
	}
	
	


}
