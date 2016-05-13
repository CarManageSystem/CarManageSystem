package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.ChargeCardDao;
import com.parkingmanage.model.ChargeCardDomain;


@Service
public class ChargeCardService {
	
	@Autowired
	private ChargeCardDao chargecardDao;
	
	public ChargeCardDomain querybyCarLicense(String carLicense){
		return chargecardDao.querybyCarLicense(carLicense);
	}
	
	public boolean HasCard(String carLicense) {
		return chargecardDao.HasCard(carLicense);
	}

}
