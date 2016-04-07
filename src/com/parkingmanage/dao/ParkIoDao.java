package com.parkingmanage.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
	
	/**
	 * 通过carLicense查找用户
	 * @param carLicense
	 * @return
	 */
	public List<ParkIoDomain> querybyCarLicense(String carLicense){
		List<ParkIoDomain> list = new ArrayList<ParkIoDomain>();
		String sql1="SELECT * FROM tb_park_io_record WHERE car_license like ? ";
		System.out.println(sql1);
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql1,new Object[]{carLicense});
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carMap =  it.next();
				ParkIoDomain car = new ParkIoDomain();
				car.setCarLicense( (String)carMap.get("car_license") );
				car.setTimeIn( (Date)carMap.get("time_in"));
				car.setTimeOut( (Date)carMap.get("time_out") );
				car.setCarportId( (String)carMap.get("carport_id") );
				car.setExitTypeString( (String)carMap.get("exit_type") );
				list.add(car);
			}
		} catch(DataAccessException e){
			System.out.println("web用户信息查询数据库出错--->queryByCarLicense");
		}
		return list;
	}
	
	
	
	/*
	 * * 获取当前车场车辆状态
	 * @return
	*/
	public Map<String,Integer> parkstate(){
		Map<String,Integer> parkmap = new HashMap<String,Integer>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式	
		String timenow = df.format(new Date());// new Date()为获取当前系统时间
		String timestart = timenow.substring(0,10)+"00:00:00";
		//统计进场
		String sq1 = "SELECT COUNT(*) FROM tb_park_io_record WHERE time_in > STR_TO_DATE('"+timestart+"','%Y-%m-%d %H:%i:%s') and time_in < STR_TO_DATE('"+timenow+"','%Y-%m-%d %H:%i:%s')";
		int into = jdbcTemplate.queryForInt(sq1);
		//System.out.println("进场"+into);
		parkmap.put("into", into);
		//统计出场
		String sq2 = "SELECT COUNT(*) FROM tb_park_io_record WHERE time_out > STR_TO_DATE('"+timestart+"','%Y-%m-%d %H:%i:%s') and time_out < STR_TO_DATE('"+timenow+"','%Y-%m-%d %H:%i:%s')";
		int out = jdbcTemplate.queryForInt(sq2);
		//System.out.println("出场"+out);
		parkmap.put("out", out);
		//统计预约
		String sq3 = "SELECT COUNT(*) FROM tb_park_io_record WHERE order_flag='1' and time_in > STR_TO_DATE('"+timestart+"','%Y-%m-%d %H:%i:%s') and time_in < STR_TO_DATE('"+timenow+"','%Y-%m-%d %H:%i:%s')";
		int order = jdbcTemplate.queryForInt(sq3);
		//System.out.println("预约："+order);
		parkmap.put("order", order);
		//统计场内
		String sq4 = "SELECT COUNT(*) FROM tb_park_io_record WHERE time_out is null";
		int inside = jdbcTemplate.queryForInt(sq4);
		//System.out.println("场内："+inside);
		parkmap.put("inside", inside);
		//统计场内长期车辆
		String sq5 = "SELECT COUNT(*) FROM tb_park_io_record WHERE car_license IN (SELECT car_license FROM tb_park_io_record WHERE time_out is null) and car_license IN (SELECT car_license FROM tb_carport_car)";
		int longterm = jdbcTemplate.queryForInt(sq5);
		//System.out.println("场内长期："+longterm);
		parkmap.put("longterm", longterm);
		//统计场内临时车辆
		String sq6 = "SELECT COUNT(*) FROM tb_park_io_record WHERE car_license IN (SELECT car_license FROM tb_park_io_record WHERE time_out is null) and car_license NOT IN (SELECT car_license FROM tb_carport_car)";
		int temp = jdbcTemplate.queryForInt(sq6);
		//System.out.println("场内临时："+temp);
		parkmap.put("temp", temp);
		//统计出场自动放行
		String sq7 = "SELECT COUNT(*) FROM tb_park_io_record WHERE car_license IN (SELECT car_license FROM tb_park_io_record WHERE time_out > STR_TO_DATE('"+timestart+"','%Y-%m-%d %H:%i:%s') and time_out < STR_TO_DATE('"+timenow+"','%Y-%m-%d %H:%i:%s') and pass_type='1')";
		int auto = jdbcTemplate.queryForInt(sq7);
		//System.out.println("出场自动："+auto);
		parkmap.put("auto", auto);
		//统计场内现金放行
		String sq8 = "SELECT COUNT(*) FROM tb_park_io_record WHERE car_license IN (SELECT car_license FROM tb_park_io_record WHERE time_out > STR_TO_DATE('"+timestart+"','%Y-%m-%d %H:%i:%s') and time_out < STR_TO_DATE('"+timenow+"','%Y-%m-%d %H:%i:%s') and pass_type='2')";
		int  cash= jdbcTemplate.queryForInt(sq8);
		//System.out.println("出场自动："+cash);
		parkmap.put("cash", cash);
		//统计场内强制放行
		String sq9 = "SELECT COUNT(*) FROM tb_park_io_record WHERE car_license IN (SELECT car_license FROM tb_park_io_record WHERE time_out > STR_TO_DATE('"+timestart+"','%Y-%m-%d %H:%i:%s') and time_out < STR_TO_DATE('"+timenow+"','%Y-%m-%d %H:%i:%s') and pass_type='3')";
		int force = jdbcTemplate.queryForInt(sq9);
		//System.out.println("出场强制："+force);
		parkmap.put("force", force);
		return parkmap;
	}

	//type:0代表全选，1代表临时，2代表长期；
	//state：0代表全选，1代表场内，2代表出场，3代表预约；
	public List<ParkIoDomain> query(Integer Type,Integer State,String Passtype,String Exittype,String Starttime,String Endtime){
		
		List<ParkIoDomain> parkiolist = new ArrayList<ParkIoDomain>();
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		
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
		case 3:
			sql1="SELECT park_io_id FROM tb_park_io_record WHERE order_flag = '1' AND car_license IN ("+sb.toString()+")";
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
		
		switch (Passtype) {
		case "1":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE pass_type = '1' AND park_io_id IN ("+sb1.toString()+")";
			break;
		case "2":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE pass_type = '2' AND park_io_id IN ("+sb1.toString()+")";
			break;
		case "3":
			sql2="SELECT park_io_id FROM tb_park_io_record WHERE pass_type = '3' AND park_io_id IN ("+sb1.toString()+")";
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
		
		switch (Exittype) {
		case "A":
			sql3="SELECT park_io_id FROM tb_park_io_record WHERE exit_type = 'A' AND park_io_id IN ("+sb2.toString()+")";
			break;
		case "B":
			sql3="SELECT park_io_id FROM tb_park_io_record WHERE exit_type = 'B' AND park_io_id IN ("+sb2.toString()+")";
			break;
		case "C":
			sql3="SELECT park_io_id FROM tb_park_io_record WHERE exit_type = 'C' AND park_io_id IN ("+sb2.toString()+")";
			break;
		case "all":
			sql3="SELECT park_io_id FROM tb_park_io_record WHERE park_io_id IN ("+sb2.toString()+")";
			break;
		default:
			break;
		}
		System.out.println("222222222222222222");
		System.out.println(sql3);
		List<String> list3 = new ArrayList<String>();
		list3 = jdbcTemplate.queryForList(sql3, String.class);
		
		
		StringBuilder sb3 = new StringBuilder();
		
		for(int i=0;i<list3.size();i++){
			sb3.append("'"+ list3.get(i) + "'");
			if(i < list3.size()-1){
				sb3.append(",");
			}
		}
		//
		System.out.println(sb3.toString());
		
		
		
		
		if ("".equals(Starttime)||"".equals(Endtime)){
			sql4="SELECT * FROM tb_park_io_record WHERE park_io_id IN ("+sb3.toString()+")";
		}
		else{
			sql4="SELECT * FROM tb_park_io_record WHERE park_io_id IN ("+sb3.toString()+") AND time_in > STR_TO_DATE('"+Starttime+"','%Y-%m-%d %H:%i:%s') and time_out < STR_TO_DATE('"+Endtime+"','%Y-%m-%d %H:%i:%s')";
		}
		System.out.println("33333333333333333333333");
		System.out.println(sql4);
		
	 	try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql4);
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
