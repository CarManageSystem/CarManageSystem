package com.parkingmanage.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2016年1月4日
 */

@Repository
public class CarportStateDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(CarportStateDao.class.getName());
	
	/**
	 * 计算使用中的固定车位:state-1,property-0
	 * @param 
	 * @return
	 */
	public int fixedUsing(int parkId){
		String sql = "SELECT COUNT(*) FROM tb_carport WHERE park_id=? and carport_state=1 and carport_property=0";
		System.out.println(sql);
		int res = 0;
		try {
			res = jdbcTemplate.queryForInt(sql, new Object[]{parkId});
		} catch (DataAccessException e) {
			logger.error("carportstate查询数据库出错--->fixedUsing");
			logger.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 计算空闲中的固定车位:state-0,property-0
	 * @param 
	 * @return
	 */
	public int fixedUnused(int parkId){
		String sql = "SELECT COUNT(*) FROM tb_carport WHERE park_id=? and carport_state=0 and carport_property=0";
		System.out.println(sql);
		int res = 0;
		try {
			res = jdbcTemplate.queryForInt(sql, new Object[]{parkId});
		} catch (DataAccessException e) {
			logger.error("carportstate查询数据库出错--->fixedUnused");
			logger.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 计算使用中的临时车位:state-1,property-1
	 * @param 
	 * @return
	 */
	public int tempUsing(int parkId){
		String sql = "SELECT COUNT(*) FROM tb_carport WHERE park_id=? and carport_state=1 and carport_property=1";
		System.out.println(sql);
		int res = 0;
		try {
			res = jdbcTemplate.queryForInt(sql, new Object[]{parkId});
		} catch (DataAccessException e) {
			logger.error("carportstate查询数据库出错--->tempUsing");
			logger.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 计算空闲中的临时车位:state-0,property-1
	 * @param 
	 * @return
	 */
	public int tempUnused(int parkId){
		String sql = "SELECT COUNT(*) FROM tb_carport WHERE park_id=? and carport_state=0 and carport_property=1";
		System.out.println(sql);
		int res = 0;
		try {
			res = jdbcTemplate.queryForInt(sql, new Object[]{parkId});
		} catch (DataAccessException e) {
			logger.error("carportstate查询数据库出错--->tempUnused");
			logger.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 写入tb_carport_state信息
	 * @param 
	 * @return
	 */
	public boolean update(int fUsing,int fUnused,int tUsing,int tUnused,int parkId){
		String sql = "UPDATE tb_carport_state SET fixed_carport_using=? and fixed_carport_unused=? and temp_carport_using=? and temp_carport_unused=? WHERE park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{fUsing,fUnused,tUsing,tUnused,parkId});
			System.out.println("ok");
		} catch (DataAccessException e) {
			logger.error("carportstate查询数据库出错--->update");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
