package com.parkingmanage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parkingmanage.dao.CarDao;
import com.parkingmanage.model.CarDomain;

/**
 * 
 * @author zhangx
 * @date 2015年12月31日
 */

@Service
public class CarService {
	@Autowired
	private CarDao carDao;
	
	public List<CarDomain> listAll(){
		return carDao.listAll();
	}

	public boolean add(CarDomain car){
		return carDao.insert(car);
	}
	
	public boolean delete(String carLicense){
		return carDao.deleteByLicense(carLicense);
	}
	
	public boolean deleteByLicenses(String carLicenses){
		return carDao.deleteByLicenses(carLicenses);
	}
	
	public boolean update(CarDomain car){
		return carDao.update(car);
	}
}
