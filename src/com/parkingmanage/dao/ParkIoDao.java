package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Arrays;
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
				io.setExitTypeString( (String)ioMap.get("exit_type") );
				list.add(io);
			}
		} catch(DataAccessException e){
			System.out.println("io查询数据库出错--->listAll");
		}
		return list;
	}

	//type:0代表全选，1代表临时，2代表长期；
	//state：0代表全选，1代表场内，2代表出场，3代表预约；
	public List<ParkIoDomain> query(Integer Type,Integer State,String Exittype,String Starttime,String Endtime){
		
		List<ParkIoDomain> parkiolist = new ArrayList<ParkIoDomain>();
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		
		switch (Type) {
		case 0:
			sql="SELECT car_license FROM tb_park_io_record";
			break;
		case 1:
			sql="SELECT car_license FROM tb_park_io_record WHERE car_license NOT IN (SELECT car_license FROM tb_carport_car)";
			break;
		case 2:
			sql="SELECT car_license FROM tb_park_io_record WHERE car_license IN (SELECT car_license FROM tb_carport_car)";
			break;
		default:
			break;
		}
		
		List<String> list = new ArrayList<String>();
		list = jdbcTemplate.queryForList(sql, String.class);
		 
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<list.size();i++){
			sb.append("'"+ list.get(i) + "'");
			if(i < list.size()-1){
				sb.append(",");
			}
		}
		
//		System.out.println(sb.toString());

		switch (State) {
		case 0:
			sql1="SELECT park_io_id FROM tb_park_io_record WHERE car_license IN ("+sb.toString()+")";
			break;
		case 1:
			sql1="SELECT park_io_id FROM tb_park_io_record WHERE time_out is null AND car_license IN ("+sb.toString()+")";
			break;
		case 2:
			sql1="SELECT park_io_id FROM tb_park_io_record WHERE time_out is not null AND car_license IN ("+sb.toString()+")";
			break;
		default:
			break;
		}
		
		System.out.println(sql1);
		List<String> list1 = new ArrayList<String>();
		list1 = jdbcTemplate.queryForList(sql1, String.class);
		
		StringBuilder sb1 = new StringBuilder();
		
		for(int i=0;i<list1.size();i++){
			sb1.append("'"+ list1.get(i) + "'");
			if(i < list1.size()-1){
				sb1.append(",");
			}
		}
		
		switch (Exittype) {
		case "A":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE exit_type = 'A' AND park_io_id IN ("+sb1.toString()+")";
			break;
		case "B":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE exit_type = 'B' AND park_io_id IN ("+sb1.toString()+")";
			break;
		case "C":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE exit_type = 'C' AND park_io_id IN ("+sb1.toString()+")";
			break;
		case "all":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE park_io_id IN ("+sb1.toString()+")";
			break;
		default:
			break;
		}
		System.out.println("222222222222222222");
		System.out.println(sql2);
		List<String> list2 = new ArrayList<String>();
		list2 = jdbcTemplate.queryForList(sql2, String.class);
		
		
		StringBuilder sb2 = new StringBuilder();
		
		for(int i=0;i<list2.size();i++){
			sb2.append("'"+ list2.get(i) + "'");
			if(i < list2.size()-1){
				sb2.append(",");
			}
		}
		//
		System.out.println(sb2.toString());
		
		if ("".equals(Starttime)||"".equals(Endtime)){
			sql3="SELECT * FROM tb_park_io_record WHERE park_io_id IN ("+sb2.toString()+")";
		}
		else{
			sql3="SELECT * FROM tb_park_io_record WHERE park_io_id IN ("+sb2.toString()+") AND time_in > STR_TO_DATE('"+Starttime+"','%Y-%m-%d %H:%i:%s') and time_out < STR_TO_DATE('"+Endtime+"','%Y-%m-%d %H:%i:%s')";
		}
		System.out.println("33333333333333333333333");
		System.out.println(sql3);
		
	 	try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql3);
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
				io.setExitTypeString( (String)ioMap.get("exit_type") );
				parkiolist.add(io);
			}
		} catch(DataAccessException e){
			System.out.println("io查询数据库出错--->query");
		}
		return parkiolist;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
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
