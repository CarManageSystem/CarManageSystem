package com.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.AppCarDao;

@Service
public class AppCarService {
	@Autowired
	
	private AppCarDao carDao;
	
	public boolean switchPayValue(String phoneString,String carLicense,boolean is_pay) {
		return carDao.switchPayValue(phoneString, carLicense,is_pay);
	}
	
	public String addCarInfo(String phoneString,String carLicese,boolean isOwner,
			String carBrand,String engineNum) {
		return carDao.addCarInfo(phoneString, carLicese, isOwner, carBrand, engineNum);
	}
	
	public Object fetchCarInfo(String phoneString) {
		return carDao.fetchCarInfo(phoneString);
	}

}
