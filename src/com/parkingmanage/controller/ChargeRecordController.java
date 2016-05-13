package com.parkingmanage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.ChargeCardDomain;
import com.parkingmanage.model.ChargeRuleDomain;
import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.service.ChargeCardService;
import com.parkingmanage.service.ChargeRecordService;
import com.parkingmanage.service.ParkIoService;


/**
 * 
 * @author zhangx
 * @date 2016年1月12日
 */

@Controller
public class ChargeRecordController{
	@Autowired
	private ChargeRecordService chargerecordService;
	@Autowired
	private ParkIoService parkioService;
	@Autowired
	private ChargeCardService chargecardService;
	
	//charge_standard页面显示
	@RequestMapping(value="/charge_standard.action")
	public ModelAndView recordlist(){
		List<ChargeRuleDomain> rule= chargerecordService.chargerule();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("charge_standard/charge_standard");
		mv.addObject("rule", rule);
		return mv;
	}
	
	//年度全时收费标准
	@RequestMapping(value="/yearall")
	public @ResponseBody void yearall(HttpServletResponse response){
		JSONObject result = new JSONObject();
		result.put("yearall", 8000);
		result.put("quarter", 2100);
		result.put("month", 800);
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
	
	//跳转到临时车辆收费标准编辑页面
	@RequestMapping(value="/standard_set.action")
	public String standardSet(){
		return "charge_standard/standard_set";
	}
	
	//跳转到全时车辆收费标准编辑页面
	@RequestMapping(value="/cardfee_set.action")
	public String CardFeeSet(){
		return "charge_standard/cardfee_set";
	}
	
	//临时车辆收费标准提交表单
	@RequestMapping(value="ChargeRule_update.action", method=RequestMethod.POST)
	public String userUpdate(String day_start,String day_end,int day_unit,int night_unit,int freetime,
			float bwi_day_fee,float bwo_day_fee,float bwi_night_fee,float swi_day_fee,float swo_day_fee,float swi_night_fee,
			float bri_day_fee,float bro_day_fee,float bri_night_fee,float sri_day_fee,float sro_day_fee,float sri_night_fee){
	    day_start = day_start+":00";
	    day_end = day_end+":00";
		if(bwo_day_fee==0.0){
	    	bwo_day_fee = bwi_day_fee;
		    swo_day_fee = swi_day_fee;
		    bro_day_fee = bri_day_fee;
		    sro_day_fee = sri_day_fee;
	    }
	    chargerecordService.update(day_start, day_end, day_unit, night_unit, freetime, bwi_day_fee, bwo_day_fee, bwi_night_fee, swi_day_fee, swo_day_fee, swi_night_fee, bri_day_fee, bro_day_fee, bri_night_fee, sri_day_fee, sro_day_fee, sri_night_fee);
		return "charge_standard/charge_standard";
	}
	
	//全时车辆收费标准提交表单
	@RequestMapping(value="CardFee_update.action", method=RequestMethod.POST)
	public String cardfeeUpdate(int yearall,int quarter,int month) {
		System.out.println("年度全时："+yearall+"季度全时："+quarter+"月度全时："+month);
		return "charge_standard/charge_standard";
	}

	
	//计算停车费用
	@RequestMapping(value="calcharge.action", method=RequestMethod.GET)
	public @ResponseBody Object CalCharge(String parkioId) throws Exception{
		float fee=0;
		List<ChargeRuleDomain> rule = chargerecordService.chargerule();
		int freetime = rule.get(0).getFreeTime();
		ParkIoDomain park = parkioService.querybyParkioId(parkioId);
		String carLicense = park.getCarLicense();
		Date timein = park.getTimeIn();
		Date timeout = park.getTimeOut();
		String cartype = "s";//改为数据库查询返回结果
		if(chargecardService.HasCard(carLicense)) {//有卡
			ChargeCardDomain card = chargecardService.querybyCarLicense(carLicense);
			Date startTime = card.getStartTime();
			Date endTime = card.getEndTime();
			if(startTime.before(timein)&&endTime.after(timeout)) {
				fee = 0;
			}else if(startTime.before(timein)&&endTime.before(timeout)) {
				//t1=endTime,t2=outTime
				if(freetime!=0){
					fee = chargerecordService.calfreetime(rule,cartype,endTime,timeout);
					System.out.println("freetime,the parking fee is:"+fee);
				}
				else{
					fee = chargerecordService.calfirsthour(rule,cartype,endTime,timeout);
				}
			}else if(startTime.after(timein)&&endTime.after(timeout)) {
				//t1=startTime,t2=inTime
				if(freetime!=0){
					fee = chargerecordService.calfreetime(rule,cartype,startTime,timein);
				}
				else{
					fee = chargerecordService.calfirsthour(rule,cartype,startTime,timein);
				}
			}else if(endTime.before(timein)){//有办卡记录，但相当于没卡
				if(freetime!=0){
					fee = chargerecordService.calfreetime(rule,cartype,timein,timeout);
				}
				else{
					fee = chargerecordService.calfirsthour(rule,cartype,timein,timeout);
				}
			}else{
				//t1=startTime,t2=inTime
				//t3=endTime,t4=outTime
				if(freetime!=0){
					float fee1 = chargerecordService.calfreetime(rule,cartype,startTime,timein);
					float fee2 = chargerecordService.calfreetime(rule,cartype,endTime,timeout);
					fee = fee1+fee2;
				}
				else{
					float fee1 = chargerecordService.calfirsthour(rule,cartype,startTime,timein);
					float fee2 = chargerecordService.calfirsthour(rule,cartype,endTime,timeout);
					fee = fee1+fee2;
				}
			}
			
		}else {//没有卡
			if(freetime!=0){
				fee = chargerecordService.calfreetime(rule,cartype,timein,timeout);
			}
			else{
				fee = chargerecordService.calfirsthour(rule,cartype,timein,timeout);
			}
		}
		//System.out.println("the parking fee is:"+fee);
		return fee;
		
	}
}
