package com.parkingmanage.dao;

import java.text.SimpleDateFormat;
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

import com.parkingmanage.model.ChargeRecordDomain;
import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2016年1月11日
 */

@Repository
public class ChargeRecordDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(ChargeRecordDao.class.getName());
	
	/**
	 * 获取收费记录列表
	 * @return
	 */
	
	public List<ChargeRecordDomain> listAll(){
		List<ChargeRecordDomain> list = new ArrayList<ChargeRecordDomain>();
		String sql="SELECT * FROM tb_charge_record";
		System.out.println(sql);	
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> chargeMap =  it.next();
				ChargeRecordDomain charge = new ChargeRecordDomain();
				charge.setChargeId( (String)chargeMap.get("charge_id") );
				charge.setPayTime( (Date)chargeMap.get("pay_time") );
				charge.setConfirmTime( (Date)chargeMap.get("confirm_time") );
				charge.setActualMoney( (Float)chargeMap.get("actual_money") );
				charge.setPayType( (String)chargeMap.get("pay_type") );
				list.add(charge);
			}
		} catch(DataAccessException e){
			System.out.println("charge查询数据库出错--->listAll");
		}
		return list;
	}
	
	/**
	 * 计算在场时间：返回小时
	 * @return
	 */
	public float parktime(String parkioId){
		String sql1 = "SELECT time_in FROM tb_park_io_record WHERE park_io_id=?";
		String sql2 = "SELECT time_out FROM tb_park_io_record WHERE park_io_id=?";
		
		Date timein;
		Date timeout;
		try {
			timein = jdbcTemplate.queryForObject(sql1,new Object[]{parkioId},Date.class);
			timeout = jdbcTemplate.queryForObject(sql2,new Object[]{parkioId},Date.class);
		} catch (DataAccessException e) {
			logger.error("error--->parktime");
			logger.error(e);
			return 0;//没有找到
		}
		float diff = timeout.getTime() - timein.getTime();	
		float hours = diff / (1000 * 60 * 60);
		return hours;
	}
	
	/**
	 * 根据收费标准表计算停车一天的费用：返回金额
	 * @return
	 */
	public float dayfee(){
		String sql = "SELECT SUM(per_fee) FROM tb_charge_standard";
		float fee;
		try {
			fee = jdbcTemplate.queryForObject(sql,Float.class);
		} catch (DataAccessException e) {
			logger.error("error--->dayfee");
			logger.error(e);
			return 0;//没有找到
		}
		return fee;
	}
	
	/**
	 * 计算24小时内停车费用：返回金额
	 * @return
	 */
	public float calculate(String parkioId){
		//找出起始时间和结束时间
		String sql1 = "SELECT time_in FROM tb_park_io_record WHERE park_io_id=?";
		String sql2 = "SELECT time_out FROM tb_park_io_record WHERE park_io_id=?";
		
		Date timein;
		Date timeout;
		try {
			timein = jdbcTemplate.queryForObject(sql1,new Object[]{parkioId},Date.class);
			timeout = jdbcTemplate.queryForObject(sql2,new Object[]{parkioId},Date.class);
		} catch (DataAccessException e) {
			logger.error("error--->calculate");
			logger.error(e);
			return 0;//没有找到
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time1 = df.format(timein);
		String time2 = df.format(timeout);
		String start = time1.substring(11,16).substring(0, 2)+time1.substring(11,16).substring(3, 5);
		String end = time2.substring(11,16).substring(0, 2)+time2.substring(11,16).substring(3, 5);
		
		System.out.println(start);
		System.out.println(end);
		
		//找出起始时间和结束时间两个头 
		String sql3 = "select standard_id from tb_charge_standard where ? between start_time and end_time";
		String startid;
		startid = jdbcTemplate.queryForObject(sql3,new Object[]{start},String.class);
		System.out.println(startid);
		
		String sql4 = "select standard_id from tb_charge_standard where ? between start_time and end_time";
		String endid;
		endid = jdbcTemplate.queryForObject(sql4,new Object[]{end},String.class);
		System.out.println(endid);
		 
		//计算两个头，之间时间段的费用
		float fee1;
		float fee11;
		float fee12;
		//如果startid<endid
		if(Integer.parseInt(startid) < Integer.parseInt(endid)){
			String sql5 = "SELECT SUM(per_fee) FROM tb_charge_standard where standard_id > ? and standard_id < ?";
			fee1 = jdbcTemplate.queryForObject(sql5,new Object[]{startid,endid},Float.class);
			System.out.println(fee1);
		}
		//如果startid>endid
		else{
			String sql51 = "SELECT MAX(standard_id) FROM tb_charge_standard";
			String max = jdbcTemplate.queryForObject(sql51,String.class);
			System.out.println(max);
			
			String sql52 = "SELECT MIN(standard_id) FROM tb_charge_standard";
			String min = jdbcTemplate.queryForObject(sql52,String.class);
			System.out.println(min);
			
			String sql53 = "SELECT SUM(per_fee) FROM tb_charge_standard where standard_id > ? and standard_id <= ?";
			fee11 = jdbcTemplate.queryForObject(sql53,new Object[]{startid,max},Float.class);
			System.out.println(fee11);
			String sql54 = "SELECT SUM(per_fee) FROM tb_charge_standard where standard_id < ? and standard_id >= ?";
			fee12 = jdbcTemplate.queryForObject(sql54,new Object[]{endid,min},Float.class);
			System.out.println(fee12);
			fee1 = fee11 +fee12;
			System.out.println(fee1);
		}
		
		
		//两个头的时间和不足15分钟，按十五分钟计费；超过十五分钟，按半小时计费。两头收费单价不同时取高的费用。
		String sql6 = "select end_time from tb_charge_standard where ? between start_time and end_time";
		String sql7 = "select per_fee from tb_charge_standard where ? between start_time and end_time";
		String endtime1;
		String perfee1;
		endtime1 = jdbcTemplate.queryForObject(sql6,new Object[]{start},String.class);
		perfee1 = jdbcTemplate.queryForObject(sql7,new Object[]{start},String.class);
//		System.out.println(endtime1);
//		System.out.println(perfee1);
		
		String sql8 = "select start_time from tb_charge_standard where ? between start_time and end_time";
		String sql9 = "select per_fee from tb_charge_standard where ? between start_time and end_time";
		String starttime1;
		String perfee2;
		starttime1 = jdbcTemplate.queryForObject(sql8,new Object[]{end},String.class);
		perfee2 = jdbcTemplate.queryForObject(sql9,new Object[]{end},String.class);
//		System.out.println(starttime1);
//		System.out.println(perfee2);
		
		float maxfee;
		if( Float.parseFloat(perfee1) >= Float.parseFloat(perfee2) ){
			maxfee = Float.parseFloat(perfee1);
		}else{
			maxfee = Float.parseFloat(perfee2);
		}
//		System.out.println(maxfee);
		
		int a = Integer.parseInt(endtime1)-Integer.parseInt(start);
		int b = Integer.parseInt(end)-Integer.parseInt(starttime1);
//		System.out.println(a);
//		System.out.println(b);
		
		float fee2;
		if(a+b<15){
			fee2 = maxfee;
		}else{
			fee2 = 2*maxfee;
		}
		System.out.println(fee2);
		float fee3 = fee1 + fee2;
		System.out.println(fee3);
		return fee3;
	}
	
	/**
	 * 添加收费记录
	 * @param 
	 * @return
	 */
	public boolean insert(ChargeRecordDomain charge){
		String sql = "INSERT INTO tb_charge_record (charge_id,pay_time,confirm_time,actual_money,pay_type)"
					 +"values(?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{charge.getChargeId(),charge.getPayTime(),charge.getConfirmTime(),charge.getActualMoney(),charge.getPayType()});
		} catch (DataAccessException e) {
			logger.error("chargerecord查询数据库出错--->insert");
			logger.error(e);
			return false;
		}
		return true;
	}
	
}
