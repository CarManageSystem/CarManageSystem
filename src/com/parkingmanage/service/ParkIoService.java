package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.ParkIoDao;
import com.parkingmanage.model.ParkIoDomain;



/**
 * 
 * @author zhangx
 * @date 2016年1月7日
 */

@Service
public class ParkIoService {
	@Autowired
	private ParkIoDao parkioDao;
	
	public List<ParkIoDomain> listAll(){
		return parkioDao.listAll();
	}
	
	public boolean insertin(String parkioId,String carLicense,String photolocIn,String timeIn){
		return parkioDao.insertin(parkioId,carLicense, photolocIn,timeIn);
	}
	
	public boolean cartype(String carLicense){
		return parkioDao.cartype(carLicense);
	}
	
	public String arrageport(String carLicense){
		return parkioDao.arrageport(carLicense);
	}
	
	public String findport(String carLicense){
		return parkioDao.findport(carLicense);
	}
	
	public boolean insertport(String parkioId,String carportId){
		return parkioDao.insertport(parkioId, carportId);
	}
	
	public boolean insertout(String carLicense,String photolocOut,String timeOut){
		return parkioDao.insertout(carLicense, photolocOut, timeOut);
	}
}
