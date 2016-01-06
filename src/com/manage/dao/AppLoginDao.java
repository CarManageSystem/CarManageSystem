package com.manage.dao;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppLoginDao {
	@Autowired
	
	private JdbcTemplate jdbcTemplate;
	

	public boolean login(String phoneString) {
		System.out.println("testDao.se>>>>>>>ts>>>>>>> error>>>>>>>>>>>>>>>>>>>>>");
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		String dateString = dateFormat.format(new java.util.Date());
		java.util.Date date = new java.util.Date();
		System.out.println(dateString + ">>>>>>");
		System.out.println(date);
		String sqlString = "insert into tb_app_user (user_phone,login_date) values ('"+phoneString+"','"+dateString+"')";
		//String updateString = "update test1 set date = '"+dateString+"' where id = '"+phoneNumber+"'";
		try {
			System.out.println(sqlString);
			int b = jdbcTemplate.update(sqlString);
			System.out.print(b);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("testDao.se>>>>>>>ts>>>>>>> error" + e + ">>>>>>>>>>>>>>>>>>>>>");
		}
		return false;
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
