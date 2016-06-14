package com.parkingmanage.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//@RequestMapping(value = "iocharge")
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
		float fee = 0;
		List<ChargeRuleDomain> rule = chargerecordService.chargerule();
		int freetime = rule.get(0).getFreeTime();
		ParkIoDomain park = parkioService.querybyParkioId(parkioId);
		String carLicense = park.getCarLicense();
		Date timein = park.getTimeIn();
		Date timeout;
		if (park.getTimeOut()==null){
			timeout = new Date();
		}else{
			timeout = park.getTimeOut();
		}
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
			}else if(startTime.after(timein)&&endTime.after(timeout)&&startTime.before(timeout)) {
				//t1=startTime,t2=inTime
				if(freetime!=0){
					fee = chargerecordService.calfreetime(rule,cartype,timein,startTime);
				}
				else{
					fee = chargerecordService.calfirsthour(rule,cartype,startTime,timein);
				}
			}else if(endTime.before(timein)||startTime.after(timeout)){//有办卡记录，但相当于没卡
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
		
		//app实时查询接口：车牌号、停车场、出入场时间、当前收费标准、实时费用
		@RequestMapping(value = "/appcheck.action")
		public @ResponseBody void fetchAppCheck(String parkioId,HttpServletResponse response) throws Exception {
			JSONObject result =new JSONObject();
			response.setCharacterEncoding("utf-8");
			ParkIoDomain park = parkioService.querybyParkioId(parkioId);
			String plateNumber = park.getCarLicense();//车牌号
			String timeIn = park.getTimeIn().toString().substring(0,19); //入场时间
			String overh = new Timestamp(Timestamp.valueOf(timeIn).getTime()+ 60*60*1000).toString().substring(0,19);
			String timeOut;
			if (park.getTimeOut() == null) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				timeOut = (sdf.format(date)).toString().substring(0, 19);
			} else {
				timeOut = park.getTimeOut().toString().substring(0,19); //出场时间
			}
			String cartype = "s";
			String parkingLot = "010";//所在停车场
			String fee = String.format("%.1f", (float)CalCharge(parkioId));//费用,如果数据库中没有出场时间 则按当前时间计费
			List<ChargeRuleDomain> rule = chargerecordService.chargerule();
			int freetime = rule.get(0).getFreeTime();
			String dayStart = timeOut.substring(0, 11)+rule.get(0).getDayStart();
			String dayEnd = timeOut.substring(0, 11)+rule.get(0).getDayEnd();
			String dayUnit = ""+rule.get(0).getDayUnit();
			String nightUnit = ""+rule.get(0).getNightUnit();
			String chargeRate = "";
			if(cartype.equals("s")){//小型车
				if(isWeekend(timeOut)){//休息日
					if(isBetween(dayStart, dayEnd, timeOut)){//白天
						if(freetime==0){//有首小时
							if(isBetween(timeIn,overh,timeOut)){//首小时内
								chargeRate = rule.get(0).getSriDayFee()+"元/"+dayUnit+"min";
							}else{//首小时后
								chargeRate = rule.get(0).getSroDayFee()+"元/"+dayUnit+"min";
							}
						}else{//有免费时长
							chargeRate = rule.get(0).getSriDayFee()+"元/"+dayUnit+"min";
						}
					}else{//晚上		
					    chargeRate = rule.get(0).getSriNightFee()+"元/"+nightUnit+"min";		
					}
				}else {//工作日
					if(isBetween(dayStart, dayEnd, timeOut)){//白天
						if(freetime==0){//有首小时
							if(isBetween(timeIn,overh,timeOut)){//首小时内
								chargeRate = rule.get(0).getSwiDayFee()+"元/"+dayUnit+"min";
							}else{//首小时后
								chargeRate = rule.get(0).getSwoDayFee()+"元/"+dayUnit+"min";
							}
						}else{//有免费时长
							chargeRate = rule.get(0).getSwiDayFee()+"元/"+dayUnit+"min";
						}
					}else{//晚上		
					    chargeRate = rule.get(0).getSwiNightFee()+"元/"+nightUnit+"min";		
					}
				}
				
			}else{//大型车
				if(isWeekend(timeOut)){//休息日
					if(isBetween(dayStart, dayEnd, timeOut)){//白天
						if(freetime==0){//有首小时
							if(isBetween(timeIn,overh,timeOut)){//首小时内
								chargeRate = rule.get(0).getBriDayFee()+"元/"+dayUnit+"min";
							}else{//首小时后
								chargeRate = rule.get(0).getBroDayFee()+"元/"+dayUnit+"min";
							}
						}else{//有免费时长
							chargeRate = rule.get(0).getBriDayFee()+"元/"+dayUnit+"min";
						}
					}else{//晚上		
					    chargeRate = rule.get(0).getBriNightFee()+"元/"+nightUnit+"min";		
					}
				}else {//工作日
					if(isBetween(dayStart, dayEnd, timeOut)){//白天
						if(freetime==0){//有首小时
							if(isBetween(timeIn,overh,timeOut)){//首小时内
								chargeRate = rule.get(0).getBwiDayFee()+"元/"+dayUnit+"min";
							}else{//首小时后
								chargeRate = rule.get(0).getBwoDayFee()+"元/"+dayUnit+"min";
							}
						}else{//有免费时长
							chargeRate = rule.get(0).getBwiDayFee()+"元/"+dayUnit+"min";
						}
					}else{//晚上		
					    chargeRate = rule.get(0).getBwiNightFee()+"元/"+nightUnit+"min";		
					}
				}
			}
			result.put("plateNumber", plateNumber);
			result.put("parkingLot", parkingLot);
			result.put("timeIn", timeIn);
			result.put("timeOut", timeOut);
			result.put("chargeRate", chargeRate);
			result.put("charge", fee);
			response.getWriter().write(result.toString());
		}
		
		/*
		 * 判断是否在某个时间段内   day_start<=in_time<d_end
		 * @param day_start
		 * @param day_end
		 * @param in_time
		 * @return
		 */
		private boolean isBetween( String start, String end, String in_time ){
			boolean flag = false;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd k:mm:ss");
			try {
				Date d_start = df.parse(start);
				Date d_end = df.parse(end);
				Date d_in = df.parse(in_time);	
				if( !d_in.before(d_start) && d_in.before(d_end) ){
					flag = true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		
		private boolean isWeekend(String date){
			boolean flag = false;
			//判断是工作日/休息日
			 SimpleDateFormat formatYMD=new SimpleDateFormat("yyyy-MM-dd");//formatYMD表示的是yyyy-MM-dd格式
		     SimpleDateFormat formatD=new SimpleDateFormat("E");//"E"表示"day in week"
		     Date startD=null;
		     String StartWeekDay="";
		     try{
		         startD=formatYMD.parse(date);//将String 转换为符合格式的日期
		         StartWeekDay=formatD.format(startD);
		      }catch (Exception e){
		         e.printStackTrace();
		      }
		     List<String> week=new ArrayList<String>();
		     week.add("星期六");
		     week.add("星期日");
		    if(week.contains(StartWeekDay)) {
		    	flag = true;   	
		    }
			return flag;
		}
}
