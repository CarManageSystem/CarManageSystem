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
		String dateString = XDateTime.stringValueWithCurrent();
		setSignInLogs(phoneString, dateString);
		boolean isEXist = AppShareDao.isExistIn(jdbcTemplate, "tb_app_user", "user_phone", phoneString);
		if (!isEXist) {
			String sqlString = "insert into tb_app_user (user_phone,sign_up_date) values ('"+phoneString+"','"+dateString+"')";
			return AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString);
		}
		return true;
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
				result = String.valueOf(list.get(0).equals(pwd) ? 1 : 0);
				if (result.equals("1")) {
					setSignInLogs(phoneString, XDateTime.stringValueWithCurrent());
				}
			}else if(list.size() > 1) {
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
	
	private boolean setSignInLogs(String phoneString,String dateString) {
		String sqlString = "insert into tb_app_log (user_phone,sign_in_date) values "
				+ "('"+phoneString+"','"+dateString+"')";
		return AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString);
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
