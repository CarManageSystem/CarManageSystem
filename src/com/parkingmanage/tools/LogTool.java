/**  
* 2015年12月30日
* author:zhangx
*/
package com.parkingmanage.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.parkingmanage.dao.LogDao;

@Component
public class LogTool {
	private static LogDao logDao;
	
	@Autowired
	public void setLogDao(LogDao logDao) {
		LogTool.logDao = logDao;
	}

	public static void addLoginlog(String inlog_time,String inlog_user,int inlog_type,String inlog_content){
		logDao.addloginlog(inlog_time,inlog_user,inlog_type,inlog_content);
	}
}
