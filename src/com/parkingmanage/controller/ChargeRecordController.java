package com.parkingmanage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.ChargeRuleDomain;
import com.parkingmanage.model.ParkIoDomain;
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
	
	//charge_standard页面显示
	@RequestMapping(value="/charge_standard.action")
	public ModelAndView recordlist(){
		List<ChargeRuleDomain> rule= chargerecordService.chargerule();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("charge_standard/charge_standard");
		mv.addObject("rule", rule);
		return mv;
	}
	
	//临时车辆收费标准
	@RequestMapping(value="/temp")
	public @ResponseBody void temp(HttpServletResponse response){
		List<ChargeRuleDomain> rulelist= chargerecordService.chargerule();
		ChargeRuleDomain rule = rulelist.get(0);
		JSONObject result = new JSONObject();
		result.put("freetime", rule.getFreeTime());
		result.put("day_unit", rule.getDayUnit());
		result.put("night_unit", rule.getNightUnit());
		result.put("day_start", rule.getDayStart());
		result.put("day_end", rule.getDayEnd());
		result.put("bwi_day_fee", rule.getBwiDayFee());
		result.put("bwo_day_fee", rule.getBwoDayFee());
		result.put("bwi_night_fee", rule.getBwiNightFee());
		result.put("bwo_night_fee", rule.getBwoNightFee());
		result.put("bri_day_fee", rule.getBriDayFee());
		result.put("bro_day_fee", rule.getBroDayFee());
		result.put("bri_night_fee", rule.getBriNightFee());
		result.put("bro_night_fee", rule.getBroNightFee());		
		result.put("swi_day_fee", rule.getSwiDayFee());
		result.put("swo_day_fee", rule.getSwoDayFee());
		result.put("swi_night_fee", rule.getSwiNightFee());
		result.put("swo_night_fee", rule.getSwoNightFee());
		result.put("sri_day_fee", rule.getSriDayFee());
		result.put("sro_day_fee", rule.getSroDayFee());
		result.put("sri_night_fee", rule.getSriNightFee());
		result.put("sro_night_fee", rule.getSroNightFee());
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

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
	
	//计算有免费时长的停车费
//	@RequestMapping(value="calfreetime.action", method=RequestMethod.GET)
//	public @ResponseBody Object CalFreeTime(String parkioId){
//		float fee = chargerecordService.calfreetime(parkioId);
//		return fee;
//	}
	
	//计算停车费用
	@RequestMapping(value="calcharge.action", method=RequestMethod.GET)
	public @ResponseBody Object CalCharge(String parkioId) throws Exception{
		float fee=0;
		List<ChargeRuleDomain> rule = chargerecordService.chargerule();
		int freetime = rule.get(0).getFreeTime();
		if(freetime!=0){
			fee = chargerecordService.calfreetime(rule,parkioId);
		}
		else{
			fee = chargerecordService.calfirsthour(rule,parkioId);
		}
		return fee;
	}
}
	
	//计算停车费
//	@RequestMapping(value="calculate.action", method=RequestMethod.GET)
//	public @ResponseBody Object Calculate(String parkioId){
//		//计算在场时长
//		float hours = chargerecordService.parktime(parkioId);
////		System.out.println(hours);
//		//是否超过一天
//		float days = hours/24;
////		System.out.println(days);
//		//几个全天？
//		int day = (int)days;
////		System.out.println(day);
//		//计算几个全天的停车费
//		float onedayfee = chargerecordService.dayfee();
//		float daysfee = onedayfee*day;
////		System.out.println("daysfee:"+daysfee);
//		
//		//计算24小时内的部分
//		float inadayfee = chargerecordService.calculate(parkioId);
////		System.out.println("inadayfee:"+inadayfee);
//		float sumfee = daysfee + inadayfee;
////		System.out.println("sumfee:"+sumfee);
//		return sumfee;
//	}
//}