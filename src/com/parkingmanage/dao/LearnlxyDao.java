package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.ParkIoDomain;


@Repository
public class LearnlxyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 通过carLicense查找用户
	 * @param carLicense
	 * @return
	 */
	public List<ParkIoDomain> queryByCarLicense(String carLicense){
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
				//car.setExitType( (Integer)carMap.get("exit_type") ); 
				list.add(car);
			}
		} catch(DataAccessException e){
			System.out.println("web用户信息查询数据库出错--->queryByCarLicense");
		}
		return list;
	}
}
