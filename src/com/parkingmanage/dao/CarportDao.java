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
	 * 通过carportId 和 parkId查询此车位状态信息
	 * @param carportId,parkId
	 * @return
	 */
	public List<CarportDomain> queryById(int carportId,int parkId){
		List<CarportDomain> list = new ArrayList<CarportDomain>();
		String sql = "SELECT carport_state,carport_license,carport_property FROM tb_carport  WHERE carport_id=? and park_id=?";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{carportId,parkId});
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carportMap =  it.next();
				CarportDomain carport = new CarportDomain();
				carport.setCarportState( (int) carportMap.get("carport_state") );
				carport.setCarLicense( (String)carportMap.get("carport_license") );
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
	public boolean updatestatezero(int carportId,int parkId){
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
	public boolean updatestateone(int carportId,int parkId){
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
	public boolean updatepropertyzero(int carportId,int parkId){
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
	public boolean updatepropertyone(int carportId,int parkId){
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
	 * 通过carportId 和 parkId修改所占车牌号，carport_state＝1（占用）
	 * @param carportId,parkId,carLicense
	 * @return
	 */
	public boolean updatelicense(int carportId,int parkId,String carLicense){
		String sql1 = "SELECT carport_state FROM tb_carport WHERE carport_id=? and park_id=?";
		int state = 0;
		try {
			state = jdbcTemplate.queryForInt(sql1, new Object[]{carportId,parkId});
		} catch (DataAccessException e) {
			logger.error("carport查询数据库出错--->updatelicense");
			logger.error(e);
		}
		if(state==1){
			String sql2 = "UPDATE tb_carport SET car_license=? WHERE carport_id=? and park_id=?";
			System.out.println(sql2);
			try {
				jdbcTemplate.update(sql2, new Object[]{carLicense,carportId,parkId});	
			} catch (DataAccessException e) {
				logger.error("carport查询数据库出错--->updatelicense");
				logger.error(e);
				e.printStackTrace();
				return false;
			}
			return true;
		}else{
			System.out.println("车位未被占用！不能输入车牌号");
			return false;
		}
	}
	
	/**
	 * 通过carportId 和 parkId置空车牌号
	 * @param carportId,parkId
	 * @return
	 */
	public boolean licensenull(int carportId,int parkId){
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
