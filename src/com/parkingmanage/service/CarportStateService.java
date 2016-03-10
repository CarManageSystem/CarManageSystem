package com.parkingmanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parkingmanage.dao.CarportStateDao;

/**
 * 
 * @author zhangx
 * @date 2016年1月6日
 */

@Service
public class CarportStateService {
	@Autowired
	private CarportStateDao carportstateDao;
	
	public int fixedUsing(int parkId){
		return carportstateDao.fixedUsing(parkId);
	}
	
	public int fixedUnused(int parkId){
		return carportstateDao.fixedUnused(parkId);
	}
	
	public int tempUsing(int parkId){
		return carportstateDao.tempUsing(parkId);
	}
	
	public int tempUnused(int parkId){
		return carportstateDao.tempUnused(parkId);
	}
	
	public boolean update(int fUsing,int fUnused,int tUsing,int tUnused,int parkId){
		return carportstateDao.update(fUsing, fUnused, tUsing, tUnused, parkId);
	}
}
