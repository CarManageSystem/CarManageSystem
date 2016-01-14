package com.manage.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppCarDao {
	@Autowired
	
	private JdbcTemplate jdbcTemplate;

	public boolean addCarInfo(String phoneString,String carLicese,int isOwner,
			String carBrand,String carType) {
		Map<String, String> kvMap = new HashMap<String, String>();
		kvMap.put("car_license", carLicese);
		kvMap.put("user_phone", phoneString);
		
		if (AppShareDao.isExist(jdbcTemplate, "tb_app_car", kvMap)) {
			return false;
		}
		
		
		
		String sqlString = "insert into tb_app_car (car_license,user_phone,car_is_owner,car_brand,car_type)"
				+ " values ('"+carLicese+"','"+phoneString+"','"+isOwner+"','"+carBrand+"','"+carType+"')";
		return AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString);
	}
}
