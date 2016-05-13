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

import com.parkingmanage.model.ChargeCardDomain;
import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.tools.Log;

@Repository
public class ChargeCardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(ChargeCardDao.class.getName());
	
	/*
	 * 根据carlicense获取card的信息
	 * @param carLicense
	 * @return
	 */
	public ChargeCardDomain querybyCarLicense(String carLicense){
		
		ChargeCardDomain card = new ChargeCardDomain();
		String sql1="SELECT * FROM tb_card WHERE car_license like ? ";
		System.out.println(sql1);
		try{
			Map<String, Object> cardMap = jdbcTemplate.queryForMap(sql1,new Object[]{carLicense});
            card.setCarLicense( (String)cardMap.get("car_license") );
			card.setCardType( (String)cardMap.get("car_type") );
			card.setStartTime( (Date)cardMap.get("start_time") );
			card.setEndTime( (Date)cardMap.get("end_time") );	
		}catch(DataAccessException e){
			System.out.println("card信息查询数据库出错--->queryByCarLicense");
		}
		return card;
	
	}
	
	/*
	 * 判断是否有停车卡
	 * @param carlicense
	 * @return
	 */
	public boolean HasCard(String carLicense){
		String sql = "select count(*) from tb_card where car_license=?";
		int count = 0;
		try {
			count = jdbcTemplate.queryForInt(sql, new Object[]{carLicense});
			if(count>0){
				return true;//办卡
			}else{
				return false;
			}
		} catch (DataAccessException e) {
			logger.error("card查询数据库出错--->HasCard");
			logger.error(e);
			return false;
		}
	}
	
	public float cardcharge(String parkioId){
		float fee = 0;//停车费
		//找出起始时间和结束时间
	    String sql1 = "SELECT time_in FROM tb_park_io_record WHERE park_io_id=?";
		String sql2 = "SELECT time_out FROM tb_park_io_record WHERE park_io_id=?";	
		String sql3 = "SELECT car_license FROM tb_park_io_record WHERE park_io_id=?";
	    Date timein , timeout;
	    String carLicense;
		try {
			timein = jdbcTemplate.queryForObject(sql1,new Object[]{parkioId},Date.class);
			timeout = jdbcTemplate.queryForObject(sql2,new Object[]{parkioId},Date.class);
			carLicense = jdbcTemplate.queryForObject(sql3,new Object[]{parkioId},String.class);	
		} catch (DataAccessException e) {
			logger.error("error--->cardcharge");
			logger.error(e);
			return 0;//没有找到
		}
		
		String sql4="SELECT * FROM tb_card WHERE car_license like ? ";
		Map<String, Object> cardMap = null;
		try{
			cardMap = jdbcTemplate.queryForMap(sql4, new Object[]{carLicense});
		}catch(DataAccessException e){
			System.out.println("card信息查询数据库出错--->queryByCarLicense");
		}
		
		Date startTime = (Date)cardMap.get("start_time");
		Date endTime = (Date)cardMap.get("end_time");
		if(startTime.before(startTime)&&endTime.after(endTime)) {
			fee = 0;
			System.out.println("办卡用户，免停车费！");
		} else {
			
		}
		
		return fee;
	}

}
