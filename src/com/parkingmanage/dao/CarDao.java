package com.parkingmanage.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.CarDomain;
import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2015年12月31日
 */

@Repository
public class CarDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(CarDao.class.getName());
	
	/**
	 * 获取车辆列表
	 * @return
	 */
	public List<CarDomain> listAll(){
		List<CarDomain> list = new ArrayList<CarDomain>();
		String sql = "SELECT * FROM tb_carport_car";
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carMap =  it.next();
				CarDomain car = new CarDomain();
				car.setCarLicense( (String)carMap.get("car_license") );
				car.setCarBrand( (Integer)carMap.get("car_brand") );
				car.setCarType( (String)carMap.get("car_type") );
				car.setProductionDate( (Date)carMap.get("production_date") );
				car.setEngineNumber( (String)carMap.get("engine_number") );
				car.setOutputVolume( (String)carMap.get("output_volume") );
				car.setIdentifictionNumber( (String)carMap.get("identifiction_number") );
				car.setCarDistance( (Integer)carMap.get("car_distance") );
				car.setInitialDate( (Date)carMap.get("initial_date") );
				car.setCarPhoto( (String)carMap.get("car_photo") );
				car.setOwerName( (String)carMap.get("ower_name") );
				car.setOwerAge( (Integer)carMap.get("ower_age") );
				car.setOwerSex( (Integer)carMap.get("ower_sex") );
				car.setOwerAddress( (String)carMap.get("ower_address") );
				car.setOwerTel( (String)carMap.get("ower_tel") );
				list.add(car);
			}
		}catch (DataAccessException e) {
			logger.error("car查询数据库出错--->listAll");
			logger.error(e);
		}
		return list;
	}

	/**
	 * 添加车辆
	 * @param car
	 * @return
	 */
	public boolean insert(CarDomain car){
		String sql = "INSERT INTO tb_carport_car (car_license,car_brand,car_type,production_date,engine_number,output_volume,identifiction_number,car_distance,initial_date,car_photo,ower_name,ower_age,ower_sex,ower_address,ower_tel)"
					 +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{car.getCarLicense(),car.getCarBrand(),car.getCarType(),car.getProductionDate(),car.getEngineNumber(),car.getOutputVolume(),car.getIdentifictionNumber(),car.getCarDistance(),car.getInitialDate(),car.getCarPhoto(),car.getOwerName(),car.getOwerAge(),car.getOwerSex(),car.getOwerAddress(),car.getOwerTel()});
		} catch (DataAccessException e) {
			logger.error("car查询数据库出错--->insert");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 通过carLicense删除车辆
	 * @param carLicense
	 * @return
	 */
	public boolean deleteByLicense(String carLicense){
		String sql = "DELETE FROM tb_carport_car WHERE car_license=?";
		try {
			jdbcTemplate.update(sql, new Object[]{carLicense});
		} catch (DataAccessException e) {
			logger.error("car查询数据库出错--->deleteByLicense");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 批量删除  carLicenses = carLicense1,carLicense2,carLicense3...
	 * @param carLicenses
	 * @return
	 */
	public boolean deleteByLicenses(String carLicenses){
		String sql = "DELETE FROM tb_carport_car WHERE car_license IN ("+carLicenses+")";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			logger.error("car查询数据库出错--->deleteByLicenses");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 更新车辆信息
	 * @param car
	 * @return
	 */
	public boolean update(CarDomain car){
		String sql = "UPDATE tb_carport_car SET car_brand=?,car_type=?,production_date=?,engine_number=?,output_volume=?,identifiction_number=?,car_distance=?,initial_date=?,car_photo=?,ower_name=?,ower_age=?,ower_sex=?,ower_address=?,ower_tel=? WHERE car_license=?";
		try {
			jdbcTemplate.update(sql, new Object[]{car.getCarBrand(),car.getCarType(),car.getProductionDate(),car.getEngineNumber(),car.getOutputVolume(),car.getIdentifictionNumber(),car.getCarDistance(),car.getInitialDate(),car.getCarPhoto(),car.getOwerName(),car.getOwerAge(),car.getOwerSex(),car.getOwerAddress(),car.getOwerTel(),car.getCarLicense()});
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.error("car查询数据库出错--->update");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
}