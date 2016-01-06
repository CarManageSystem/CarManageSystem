package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.CarportDao;
import com.parkingmanage.model.CarportDomain;

/**
 * 
 * @author zhangx
 * @date 2016年1月4日
 */

@Service
public class CarportService {
	@Autowired
	private CarportDao carportDao;
	
	public List<CarportDomain> queryById(int carportId,int parkId){
		return carportDao.queryById(carportId, parkId);
	}
	
	public boolean updatestatezero(int carportId,int parkId){
		return carportDao.updatestatezero(carportId, parkId);
	}
	
	public boolean updatestateone(int carportId,int parkId){
		return carportDao.updatestateone(carportId, parkId);
	}
	
	public boolean updatepropertyzero(int carportId,int parkId){
		return carportDao.updatepropertyzero(carportId, parkId);
	}
	
	public boolean updatepropertyone(int carportId,int parkId){
		return carportDao.updatepropertyone(carportId, parkId);
	}
	
	public boolean updatelicense(int carportId,int parkId,String carLicense){
		return carportDao.updatelicense(carportId, parkId, carLicense);
	}
	
	public boolean licensenull(int carportId,int parkId){
		return carportDao.licensenull(carportId, parkId);
	}
}
