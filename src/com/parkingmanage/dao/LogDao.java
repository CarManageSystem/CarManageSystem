/**  
* 2015年12月30日
* author:zhangx
*/
package com.parkingmanage.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.tools.Log;

@Repository
public class LogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Logger logger=Log.getLog(LogDao.class.getName());
	
	public boolean addloginlog(String inlog_time,String inlog_user,int inlog_type,String inlog_content){
			String sql = "INSERT INTO tb_park_inlog(inlog_time,inlog_user,inlog_type,inlog_content) VALUES(?,?,?,?)";
			
			try {
				jdbcTemplate.update(sql, 
						new Object[]
					    {
						inlog_time,
						inlog_user,
						inlog_type,
						inlog_content,
					    }
				);
			} catch (DataAccessException e) {
				logger.error("数据库出错--->insert");
				logger.error(e);
				return false;
			}
			return true;
	}

}
