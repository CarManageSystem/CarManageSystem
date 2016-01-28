package com.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.manage.tools.XLogger;

@Repository
public class AppCarDao {
	@Autowired
	
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = XLogger.getLog();
	

	public String addCarInfo(String phoneString,String carLicese,boolean isOwner,
			String carBrand,String engineNum) {
		//-1主键car_license、user_phone对应数据已存在，0失败，1成功
		if (isExistWith(phoneString, carLicese)) {
			return "-1";
		}
		String sqlString = "insert into tb_app_car (user_phone,car_license,car_is_owner,vehicle_brand,engine_num)"
				+ " values ('"+phoneString+"','"+carLicese+"',"+(isOwner ? 1 : 0)+",'"+carBrand+"','"+engineNum+"')";
		return String.valueOf(AppShareDao.insertOrUpdateWith(jdbcTemplate, sqlString));
	}
	
	public Object fetchCarInfo(String phoneString) {
		String sql = "select car_license,car_is_owner,vehicle_brand,is_pay from tb_app_car "
				+ "where user_phone = "+phoneString+"";
		try {
			List<Map<String, Object>> lists = jdbcTemplate.queryForList(sql);
			logger.info(lists);
			return lists;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("dep查询数据库出错--->fetchCarInfo");
			logger.error(e);
		}
		return null;
	}
	
	public boolean switchPayValue(String phoneString,String carLicense,boolean is_pay) {
		Logger logger = XLogger.getLog();
		try {
			String sql = "";
			sql = "update tb_app_car set is_pay = "+is_pay+" where car_license = "+carLicense+" and"
					+ " user_phone="+phoneString+"";
			logger.info(sql);
			jdbcTemplate.execute(sql);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("dep查询数据库出错--->switchPayValue");
			logger.error(e);
		}
		return false;
	}

	private boolean isExistWith(String phoneString,String carLicese) {
		logger.info("here");
		String sqlString = "select count(*) from tb_app_car where user_phone = '"+phoneString+"'"
				+ " and car_license = '"+carLicese+"'";
		try {
			int num = jdbcTemplate.queryForInt(sqlString);
			logger.info(num + " here " + sqlString);
			return num > 0;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("dep查询数据库出错--->isExistWith");
			logger.error(e);
		}
		return false;
	}
}
