package com.parkingmanage.service;

import java.util.List;
import java.util.Map;

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

	public List<ParkIoDomain> query(int Type,int State,String Passtype,String Exittype,String Starttime,String Endtime){
		return parkioDao.query(Type,State,Passtype,Exittype,Starttime,Endtime);
	}
	
	public List<ParkIoDomain> querybyCarLicense(String carLicense){
		return parkioDao.querybyCarLicense(carLicense);
	}
	
	public Map<String,Integer> parkstate(){
		return parkioDao.parkstate();
	}

	public ParkIoDomain querybyParkioId(String parkioId) {
		return parkioDao.querybyParkioId(parkioId);
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
