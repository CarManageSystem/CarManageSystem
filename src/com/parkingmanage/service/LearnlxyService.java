
package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.LearnlxyDao;
import com.parkingmanage.model.ParkIoDomain;

@Service
public class LearnlxyService {

	@Autowired
	private LearnlxyDao LearnlxyDao;
	
	public List<ParkIoDomain> listAll(){
		return LearnlxyDao.listAll();
	}
	public List<ParkIoDomain> querybyCarLicense(String carLicense){
		return LearnlxyDao.queryByCarLicense(carLicense);
	}
}