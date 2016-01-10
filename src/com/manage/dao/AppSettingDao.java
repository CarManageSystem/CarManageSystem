package com.manage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppSettingDao {
	@Autowired

	private JdbcTemplate jdbcTemplate;
	
	public boolean setPassword(String phoneString,String pwdString) {
		String sqlString = "update tb_app_user set user_pwd = '"+pwdString+"' where user_phone = '"+phoneString+"'";
		return  AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString);
				
	}

}
