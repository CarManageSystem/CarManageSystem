package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.ChargeRecordDao;
import com.parkingmanage.model.ChargeRecordDomain;
import com.parkingmanage.model.ChargeRuleDomain;


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
	
	public float dayfee(){
		return chargerecordDao.dayfee();
	}
	
	public List<ChargeRuleDomain> chargerule(){
		return chargerecordDao.chargerule();
	 	
	}
	
	public float calfreetime(List<ChargeRuleDomain> rule,String parkioId) throws Exception{
		return chargerecordDao.calfreetime(rule,parkioId);
	}
	public float calculate(String parkioId){
		return chargerecordDao.calculate(parkioId);
	}
	
	public boolean insert(ChargeRecordDomain charge){
		return chargerecordDao.insert(charge);
	}
}