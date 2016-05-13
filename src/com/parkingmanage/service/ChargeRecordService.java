package com.parkingmanage.service;

import java.util.Date;
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
	
//	public float parktime(String parkioId){
//		return chargerecordDao.parktime(parkioId);
//	}
	
//	public float dayfee(){
//		return chargerecordDao.dayfee();
//	}
	
	public List<ChargeRuleDomain> chargerule(){
		return chargerecordDao.chargerule();
	 	
	}
	
	public float calfreetime(List<ChargeRuleDomain> rule, String cartype, Date timein, Date timeout) throws Exception{
		return chargerecordDao.calfreetime(rule, cartype, timein, timeout);
	}
	
	public float calfirsthour(List<ChargeRuleDomain> rule,String cartype,Date timein,Date timeout) throws Exception{
		return chargerecordDao.calfirsthour(rule, cartype, timein, timeout);
	}
	
	public boolean update(String day_start,String day_end,int day_unit,int night_unit,int freetime,
			float bwi_day_fee,float bwo_day_fee,float bwi_night_fee,float swi_day_fee,float swo_day_fee,float swi_night_fee,
			float bri_day_fee,float bro_day_fee,float bri_night_fee,float sri_day_fee,float sro_day_fee,float sri_night_fee) {
		
		return chargerecordDao.update(day_start, day_end, day_unit, night_unit, freetime, bwi_day_fee, bwo_day_fee, bwi_night_fee, swi_day_fee, swo_day_fee, swi_night_fee, bri_day_fee, bro_day_fee, bri_night_fee, sri_day_fee, sro_day_fee, sri_night_fee);
	}
//	public float calculate(String parkioId){
//		return chargerecordDao.calculate(parkioId);
//	}
	
	public boolean insert(ChargeRecordDomain charge){
		return chargerecordDao.insert(charge);
	}
}