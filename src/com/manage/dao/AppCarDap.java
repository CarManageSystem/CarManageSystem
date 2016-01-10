package com.manage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppCarDap {
	@Autowired
	
	private JdbcTemplate jdbcTemplate;

	public boolean addCarInfo(String phoneString,String carLicese,int isOwner,String carBrand) {
		String sqlString = "";
		return false;
	}
}
