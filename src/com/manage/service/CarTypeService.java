package com.manage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.CarTypeDao;

@Service
public class CarTypeService {
	@Autowired
	
	CarTypeDao carTypeDao;
	
	public Object fetchFirstBrand() {
		return carTypeDao.fetchFirstBrand();
	}
	
	public Object fetchSecondBrand(int id) {
		return carTypeDao.fetchSecondBrand(id);
	}
	
	public Object fetchThirdBrand(int id) {
		return carTypeDao.fetchThirdBrand(id);
	}

}
