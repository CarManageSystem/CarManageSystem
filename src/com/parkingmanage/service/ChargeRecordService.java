package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.ChargeRecordDao;
import com.parkingmanage.model.ChargeRecordDomain;


/**
 * 
 * @author zhangx
 * @date 2015年1月12日
 */

@Service
public class ChargeRecordService {
	@Autowired
	private ChargeRecordDao chargerecordDao;
	
	public List<ChargeRecordDomain> listAll(){
		return chargerecordDao.listAll();
	}
	
	public float parktime(String parkioId){
		return chargerecordDao.parktime(parkioId);
	}
	
	public float fee(String parkioId){
		return chargerecordDao.fee(parkioId);
	}
	
	public float dayfee(){
		return chargerecordDao.dayfee();
	}
	
	public float calculate(String parkioId){
		return chargerecordDao.calculate(parkioId);
	}
}