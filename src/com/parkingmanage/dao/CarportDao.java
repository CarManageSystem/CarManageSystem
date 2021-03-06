package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.CarportDomain;
import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2016年1月4日
 */

@Repository
public class CarportDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(CarportDao.class.getName());
	
	/**
	 * 获取车位列表
	 * @return
	 */
	public List<CarportDomain> listAll(){
		List<CarportDomain> list = new ArrayList<CarportDomain>();
		String sql = "SELECT * FROM tb_carport";
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carportMap =  it.next();
				CarportDomain carport = new CarportDomain();
				carport.setCarportId( (String)carportMap.get("carport_id") );
				carport.setParkId( (int) carportMap.get("park_id"));
				carport.setCarportState( (int) carportMap.get("carport_state") );
				carport.setCarLicense( (String)carportMap.get("car_license") );
				carport.setCarportProperty( (int) carportMap.get("carport_property"));
				list.add(carport);
			}
		}catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->listAll");
			logger.error(e);
		}
		return list;
	}
	
	/**
	 * 通过carportId 和 parkId查询此车位状态信息
	 * @param carportId,parkId
	 * @return
	 */
	public List<CarportDomain> queryById(String carportId,int parkId){
		List<CarportDomain> list = new ArrayList<CarportDomain>();
		String sql = "SELECT carport_state,car_license,carport_property FROM tb_carport  WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{carportId,parkId});
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carportMap =  it.next();
				CarportDomain carport = new CarportDomain();
				carport.setCarportState( (int) carportMap.get("carport_state") );
				carport.setCarLicense( (String)carportMap.get("car_license") );
				carport.setCarportProperty( (int) carportMap.get("carport_property"));
				list.add(carport);
			}
		}catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->queryById");
			logger.error(e);
		}
		return list;
	}
	
	/**
	 * 通过carportId 和 parkId修改carportState为0（空闲）
	 * @param carportId,parkId
	 * @return
	 */
	public boolean updatestatezero(String carportId,int parkId){
		String sql = "UPDATE tb_carport SET carport_state=0 WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carportId,parkId});	
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->updatestate0");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 通过carportId 和 parkId修改carportState为1（占用）
	 * @param carportId,parkId
	 * @return
	 */
	public boolean updatestateone(String carportId,int parkId){
		String sql = "UPDATE tb_carport SET carport_state=1 WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carportId,parkId});
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->updatestate1");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 通过carportId 和 parkId修改carportProperty为0（固定）
	 * @param carportId,parkId
	 * @return
	 */
	public boolean updatepropertyzero(String carportId,int parkId){
		String sql = "UPDATE tb_carport SET carport_property=0 WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carportId,parkId});	
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->updateproperty0");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 通过carportId 和 parkId修改carportProperty为1（临时）
	 * @param carportId,parkId
	 * @return
	 */
	public boolean updatepropertyone(String carportId,int parkId){
		String sql = "UPDATE tb_carport SET carport_property=1 WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carportId,parkId});	
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->updateproperty1");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 通过carportId 和 parkId插入车牌号
	 * @param carportId,parkId,carLicense
	 * @return
	 */
	public boolean insertlicense(String carportId,int parkId,String carLicense){
		String sql = "UPDATE tb_carport SET car_license=? WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carLicense,carportId,parkId});	
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->insertlicense");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 通过carportId 和 parkId修改所占车牌号
	 * @param carportId,parkId,carLicense
	 * @return
	 */
	public boolean updatelicense(String carportId,int parkId,String carLicense){
		String sql = "UPDATE tb_carport SET car_license=? WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carLicense,carportId,parkId});	
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->updatelicense");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 通过carportId 和 parkId置空车牌号
	 * @param carportId,parkId
	 * @return
	 */
	public boolean licensenull(String carportId,int parkId){
		String sql = "UPDATE tb_carport SET car_license = null WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{carportId,parkId});	
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->licensenull");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
}
