package com.parkingmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingmanage.service.ChargeRecordService;


/**
 * 
 * @author zhangx
 * @date 2016年1月12日
 */

@Controller
public class ChargeRecordController{
	@Autowired
	private ChargeRecordService chargerecordService;

	//计算在场时长
	@RequestMapping(value="hour_time.action", method=RequestMethod.GET)
	public void hourTime(String parkioId){
		chargerecordService.parktime(parkioId);
	}
	
	//计算一天费用
	@RequestMapping(value="dayfee.action", method=RequestMethod.GET)
	public @ResponseBody Object Fee(){
		return chargerecordService.dayfee();
	}
	
	//计算24小时内费用
	@RequestMapping(value="inadayfee.action", method=RequestMethod.GET)
	public @ResponseBody Object Feeinaday(String parkioId){
		return chargerecordService.calculate(parkioId);
	}
	
	//计算停车费
	@RequestMapping(value="calculate.action", method=RequestMethod.GET)
	public @ResponseBody Object Calculate(String parkioId){
		//计算在场时长
		float hours = chargerecordService.parktime(parkioId);
//		System.out.println(hours);
		//是否超过一天
		float days = hours/24;
//		System.out.println(days);
		//几个全天？
		int day = (int)days;
//		System.out.println(day);
		//计算几个全天的停车费
		float onedayfee = chargerecordService.dayfee();
		float daysfee = onedayfee*day;
//		System.out.println("daysfee:"+daysfee);
		
		//计算24小时内的部分
		float inadayfee = chargerecordService.calculate(parkioId);
//		System.out.println("inadayfee:"+inadayfee);
		float sumfee = daysfee + inadayfee;
//		System.out.println("sumfee:"+sumfee);
		return sumfee;
	}
}