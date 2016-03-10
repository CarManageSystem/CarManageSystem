package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.tools.Log;


/**
 * @author zhangx
 * @date 2016年1月7日
 */

@Repository
public class ParkIoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(ParkIoDao.class.getName());
	
	/**
	 * 获取出入场记录列表
	 * @return
	 */
	public List<ParkIoDomain> listAll(){
		List<ParkIoDomain> list = new ArrayList<ParkIoDomain>();
		String sql="SELECT * FROM tb_park_io_record";
		System.out.println(sql);	
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> ioMap =  it.next();
				ParkIoDomain io = new ParkIoDomain();
				io.setParkioId( (String)ioMap.get("park_io_id") );
				io.setCarLicense( (String)ioMap.get("car_license") );
				io.setTimeIn( (Date)ioMap.get("time_in") );
				io.setTimeOut( (Date)ioMap.get("time_out") );
				io.setPhotolocIn( (String)ioMap.get("photo_loc_in") );
				io.setPhotolocOut( (String)ioMap.get("photo_loc_out") );
				io.setCarportId( (String)ioMap.get("carport_id") );
				list.add(io);
			}
		} catch(DataAccessException e){
			System.out.println("io查询数据库出错--->listAll");
		}
		return list;
	}

	/**
	 * 插入入场记录:图片采集，记录车牌,入场时间
	 * @return
	 */
	public boolean insertin(String parkioId,String carLicense,String photolocIn,String timeIn){
		String sql = "INSERT INTO tb_park_io_record (park_io_id,car_license,photo_loc_in,time_in) VALUES (?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{parkioId,carLicense,photolocIn,timeIn});
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->insertin");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 插入入场记录:判断是否是长期车辆还是临时车辆
	 * @return
	 */
	public boolean cartype(String carLicense){
		String sql = "select count(*) from tb_carport_car where car_license=?";
		int count = 0;
		try {
			count = jdbcTemplate.queryForInt(sql, new Object[]{carLicense});
			if(count>0){
				return true;//长期车辆
			}else{
				return false;
			}
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->cartype");
			logger.error(e);
			return false;
		}
	}
	
	/**
	 * 插入入场记录:为临时车辆分配车位
	 * @return
	 */
	public String arrageport(String carLicense){
		String sql = "select carport_id from tb_carport where carport_property=1 and carport_state=0 ORDER BY rand() LIMIT 1";
		String idString;
		try {
			idString = jdbcTemplate.queryForObject(sql,String.class);
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->arrageport");
			logger.error(e);
			return "0";//没有空闲的临时车位可以分配
		}
		return idString;
	}
	
	/**
	 * 插入入场记录:找出长期车辆的固定车位
	 * @return
	 */
	public String findport(String carLicense){
		String sql = "select carport_id from tb_carport where carport_property=0 and car_license=?";
		String idString;
		try {
			idString = jdbcTemplate.queryForObject(sql,new Object[]{carLicense},String.class);
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->findport");
			logger.error(e);
			return "0";//没有找到
		}
		return idString;
	}
	
	/**
	 * 插入入场记录:记录车位
	 * @return
	 */
	public boolean insertport(String parkioId,String carportId){
		String sql = "UPDATE tb_park_io_record SET carport_id=? WHERE park_io_id=?";
		try {
			jdbcTemplate.update(sql, new Object[]{carportId,parkioId});
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->insertport");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 插入出场记录:图片采集，出场时间
	 * @return
	 */
	public boolean insertout(String carLicense,String photolocOut,String timeOut){
		String sql1 = "select park_io_id from tb_park_io_record where car_license=? and time_out is null and photo_loc_out is null";
		String ioid;
		try {
			ioid = jdbcTemplate.queryForObject(sql1,new Object[]{carLicense},String.class);
			System.out.print(ioid);
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->insertout-ioid");
			logger.error(e);
			return false;
		}
		
		String sql2 = "UPDATE tb_park_io_record SET photo_loc_out=?,time_out=? WHERE park_io_id=?";
		try {
			jdbcTemplate.update(sql2, new Object[]{photolocOut,timeOut,ioid});
		} catch (DataAccessException e) {
			logger.error("io查询数据库出错--->insertout");
			logger.error(e);
			return false;
		}
		return true;
	}
}
