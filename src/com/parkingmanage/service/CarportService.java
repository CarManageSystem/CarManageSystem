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
	
	public List<CarportDomain> listAll(){
		return carportDao.listAll();
	}
	
	public List<CarportDomain> queryById(String carportId,int parkId){
		return carportDao.queryById(carportId, parkId);
	}
	
	public boolean updatestatezero(String carportId,int parkId){
		return carportDao.updatestatezero(carportId, parkId);
	}
	
	public boolean updatestateone(String carportId,int parkId){
		return carportDao.updatestateone(carportId, parkId);
	}
	
	public boolean updatepropertyzero(String carportId,int parkId){
		return carportDao.updatepropertyzero(carportId, parkId);
	}
	
	public boolean updatepropertyone(String carportId,int parkId){
		return carportDao.updatepropertyone(carportId, parkId);
	}
	
	public boolean updatelicense(String carportId,int parkId,String carLicense){
		return carportDao.updatelicense(carportId, parkId, carLicense);
	}
	
	public boolean licensenull(String carportId,int parkId){
		return carportDao.licensenull(carportId, parkId);
	}
	public boolean insertlicense(String carportId,int parkId,String carLicense){
		return carportDao.insertlicense(carportId, parkId, carLicense);
	}
}
