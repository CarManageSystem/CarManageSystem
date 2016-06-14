package com.parkingmanage.dao;

//import java.sql.Date;
import java.util.Date;
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
				car.setCarBrand( (String)carMap.get("car_brand") );
				car.setCarType( (String)carMap.get("car_type") );
				car.setProductionDate( (Date)carMap.get("production_date") );
				car.setEngineNumber( (String)carMap.get("engine_number") );
				car.setOutputVolume( (String)carMap.get("output_volume") );
				car.setIdentifictionNumber( (String)carMap.get("identifiction_number") );
				car.setCarDistance( (Integer)carMap.get("car_distance") );
				car.setInitialDate( (Date)carMap.get("initial_date") );
				car.setCarPhoto( (String)carMap.get("car_photo") );
				car.setOwnerName( (String)carMap.get("owner_name") );
				car.setOwnerAge( (Integer)carMap.get("owner_age") );
				car.setOwnerSex( (Integer)carMap.get("owner_sex") );
				car.setOwnerAddress( (String)carMap.get("owner_address") );
				car.setOwnerTel( (String)carMap.get("owner_tel") );
				list.add(car);
			}
		}catch (DataAccessException e) {
			logger.error("car查询数据库出错--->listAll");
			logger.error(e);
		}
		return list;
	}

	/**
	 * 通过carLicense查询
	 * @param carLicense
	 * @return
	 */
	public List<CarDomain> queryByLicense(String carLicense){
		List<CarDomain> list = new ArrayList<CarDomain>();
		String sql = "SELECT * FROM tb_carport_car WHERE car_license=?";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{carLicense});
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carMap =  it.next();
				CarDomain car = new CarDomain();
				car.setCarLicense( (String)carMap.get("car_license") );
				car.setCarBrand( (String)carMap.get("car_brand") );
				car.setCarType( (String)carMap.get("car_type") );
				car.setProductionDate( (Date)carMap.get("production_date") );
				car.setEngineNumber( (String)carMap.get("engine_number") );
				car.setOutputVolume( (String)carMap.get("output_volume") );
				car.setIdentifictionNumber( (String)carMap.get("identifiction_number") );
				car.setCarDistance( (Integer)carMap.get("car_distance") );
				car.setInitialDate( (Date)carMap.get("initial_date") );
				car.setCarPhoto( (String)carMap.get("car_photo") );
				car.setOwnerName( (String)carMap.get("owner_name") );
				car.setOwnerAge( (Integer)carMap.get("owner_age") );
				car.setOwnerSex( (Integer)carMap.get("owner_sex") );
				car.setOwnerAddress( (String)carMap.get("owner_address") );
				car.setOwnerTel( (String)carMap.get("owner_tel") );
				list.add(car);
			}
		}catch (DataAccessException e) {
			logger.error("car查询数据库出错--->queryByLicense");
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
			jdbcTemplate.update(sql, new Object[]{car.getCarLicense(),car.getCarBrand(),car.getCarType(),car.getProductionDate(),car.getEngineNumber(),car.getOutputVolume(),car.getIdentifictionNumber(),car.getCarDistance(),car.getInitialDate(),car.getCarPhoto(),car.getOwnerName(),car.getOwnerAge(),car.getOwnerSex(),car.getOwnerAddress(),car.getOwnerTel()});
		} catch (DataAccessException e) {
			logger.error("car查询数据库出错--->insert");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/*
	 * 添加车主车辆信息
	 * @param car
	 * @return
	 */
	public boolean carownerupdate(CarDomain car) {
		String sql = "INSERT INTO tb_carport_car (owner_name,owner_sex,driving_license,nation,owner_birthday,driving_license_type,license_issue_date,valid_start_date,valid_term,owner_tel,owner_address,car_license,car_type,car_issue_date,initial_date,engine_number,car_valid,car_brand,car_code,card_num)"
				     +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{car.getOwnerName(),car.getOwnerSex(),car.getDrivingLicense(),car.getNation(),car.getOwnerBirthday(),car.getDrivingLicenseType(),car.getLicenseIssueDate(),car.getValidStartDate(),car.getValidTerm(),car.getOwnerTel(),car.getOwnerAddress(),car.getCarLicense(),car.getCarType(),car.getCarIssueDate(),car.getInitialDate(),car.getEngineNumber(),car.getCarValid(),car.getCarBrand(),car.getCarCode(),car.getCardNum()});
		} catch (DataAccessException e) {
			logger.error("car查询数据库出错--->carownerupdate");
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
			jdbcTemplate.update(sql, new Object[]{car.getCarBrand(),car.getCarType(),car.getProductionDate(),car.getEngineNumber(),car.getOutputVolume(),car.getIdentifictionNumber(),car.getCarDistance(),car.getInitialDate(),car.getCarPhoto(),car.getOwnerName(),car.getOwnerAge(),car.getOwnerSex(),car.getOwnerAddress(),car.getOwnerTel(),car.getCarLicense()});
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.error("car查询数据库出错--->update");
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 通过cardNum查询
	 * @param cardNum
	 * @return
	 */
	public List<CarDomain> queryByCardNum(String cardNum){
		List<CarDomain> list = new ArrayList<CarDomain>();
		String sql = "SELECT * FROM tb_carport_car WHERE card_num=?";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{cardNum});
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> carMap =  it.next();
				CarDomain car = new CarDomain();
				car.setOwnerName( (String)carMap.get("owner_name") );
				car.setOwnerSex( (Integer)carMap.get("owner_sex") );
				car.setDrivingLicense( (String)carMap.get("driving_license") );
				car.setNation( (String)carMap.get("nation") );
				car.setOwnerBirthday( (Date)carMap.get("owner_birthday") );
				car.setDrivingLicenseType( (String)carMap.get("driving_license_type") );
				car.setLicenseIssueDate( (Date)carMap.get("license_issue_date") );
				car.setValidStartDate( (Date)carMap.get("valid_start_date") );
				car.setValidTerm( (String)carMap.get("valid_term") );
				car.setOwnerTel( (String)carMap.get("owner_tel") );
				car.setOwnerAddress( (String)carMap.get("owner_address") );
				car.setCarLicense( (String)carMap.get("car_license") );
				car.setCarType( (String)carMap.get("car_type") );
				car.setCarIssueDate( (Date)carMap.get("car_issue_date") );
				car.setInitialDate( (Date)carMap.get("initial_date") );
				car.setEngineNumber((String)carMap.get("engine_number"));
				car.setCarValid((Date)carMap.get("car_valid"));
				car.setCarBrand((String)carMap.get("car_brand"));
				car.setCarCode((String)carMap.get("car_code"));
				car.setCardNum((String)carMap.get("card_num"));
				list.add(car);
			}
		}catch (DataAccessException e) {
			logger.error("car查询数据库出错--->queryByCardNum");
			logger.error(e);
		}
		return list;
	}
}
