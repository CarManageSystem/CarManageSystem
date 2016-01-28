package com.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.manage.tools.XLogger;

@Repository
public class AppShareDao {
	
	static Logger logger = XLogger.getLog();
	
	public static boolean insertOrUpdateWith(JdbcTemplate jdbcTemplate,String sqlString) {
		try {
			jdbcTemplate.update(sqlString);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("dep查询数据库出错--->" + sqlString);
			logger.error(e);
			return false;
		}
		return true;
	}
	
	//查询某表是否存在column=value的数据
	public static boolean isExistIn(JdbcTemplate jdbcTemplate,String tbName,String column,String value) {
		String sql = "select count(*) from "+tbName+" where "+column+"="+value+"";
		try {
			int num = jdbcTemplate.queryForInt(sql);
			System.out.println("number of num " + sql + ">>>>>num:" + num);
			if (num == 1) {
				System.out.println(">>>>>>>>1ge");
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("dep查询数据库出错--->" + sql);
			logger.error(e);
			return false;
		}
		return false;
	}
	
	public static void fetchDataWith(JdbcTemplate jdbcTemplate,String sqlString) {
		System.out.println(">>>>>>>>getDataWith");
		Logger logger = XLogger.getLog();
		try {
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlString);
			System.out.println(">>>>>>>>" + list);
			
			List<String> lista = jdbcTemplate.queryForList(sqlString, String.class);
			
			System.out.println("lista>>>>>>>>" + lista);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("dep查询数据库出错--->" + sqlString);
			logger.error(e);
		}
	}
	
	public static boolean deleteAllData(JdbcTemplate jdbcTemplate,String tbName) {
		Logger logger = XLogger.getLog();
		String sql = "truncate table "+tbName+"";
		System.out.println("delete>>>>>>sql>>>>>>>>>>>ts>>>>>>>>>>>ts>>>>>");
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("testDao.se>>>>>>>ts>>>>>>> error" + e + ">>>>>>>>>>>>>>>>>>>>>");
			logger.error("dep查询数据库出错--->truncate");
			logger.error(e);
			return false;
		}
		return true;
	}
}


