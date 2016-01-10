package com.manage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.manage.tools.XDateTime;
import com.manage.tools.XLogger;

@Repository
public class AppLoginDao {
	@Autowired
	
	private JdbcTemplate jdbcTemplate;

	public boolean loginWithMesage(String phoneString) {
		String funcNameString = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(funcNameString);
		
		String dateString = XDateTime.stringValueWithCurrent();
		boolean isEXist = AppShareDao.isExistIn(jdbcTemplate, "tb_app_user", "user_phone", phoneString);

		AppShareDao.getDataWith(jdbcTemplate, 
				"select user_pwd from tb_app_user where user_phone = 11");
		AppShareDao.getDataWith(jdbcTemplate, 
				"select user_pwd,sign_up_date,sign_in_date from tb_app_user where user_phone = 11");
		
		String sqlString = (!isEXist ? 
				"insert into tb_app_user (user_phone,sign_up_date,sign_in_date) values ('"+phoneString+"','"+dateString+"','"+dateString+"')" : 
				"update tb_app_user set sign_in_date = '"+dateString+"' where user_phone = '"+phoneString+"'");
		return AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString);
	}
	
	public String loginWithPassword(String phoneString, String pwd) {
		Logger logger = XLogger.getLog();
		//-2 异常，-1不存在，0失败，1成功,2未设定密码
		String sql = "select user_pwd from tb_app_user where user_phone = "+phoneString+"";
		String result = "-1";
		try {
			List<String> list = jdbcTemplate.queryForList(sql, String.class);
			System.out.print(list + ">>>>>>list--count:" + list.size() + "first:" + list.get(0) +"\n");
			if (list.size() == 1) {
				if (list.get(0) == null) {
					result = "2";
					logger.info("index of 0 is null");
					return result;
				}
				result = String.valueOf(list.get(0).equals(pwd));
				if (result.equals("1")) {
					String sqlString = "update tb_app_user set "
							+ "sign_in_date="+XDateTime.stringValueWithCurrent()+""
							+ "where user_phone = "+phoneString+"";
					logger.info(AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString));
				}
			}else {
				result = "-2";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(">>>>>>" + e + ">>>>\n");
			logger.error(e);
		}
		System.out.println(result + "---password check <<<<");
		return result;
	}
	
	public boolean deleteAll() {
		String sql = "truncate table tb_app_user";
		System.out.println("deleteid>>>>>>ts>>>>>>>>>>>ts>>>>>>>>>>>ts>>>>>");
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("testDao.se>>>>>>>ts>>>>>>> error" + e + ">>>>>>>>>>>>>>>>>>>>>");
		}
				
		return true;
	}

}
