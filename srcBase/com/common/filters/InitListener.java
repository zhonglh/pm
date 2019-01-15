package com.common.filters;

import com.common.utils.file.FileStoreHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try{
			FileStoreHelper.setBaseDir( event.getServletContext().getRealPath("/") );
			

			//ConvertUtils.register(new MyDateConverter(null), java.util.Date.class);
			//ConvertUtils.register(new MySqlTimestampConverter(null),java.sql.Timestamp.class);
			//ConvertUtils.register(new MySqlDateConverter(null), java.sql.Date.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
