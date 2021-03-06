package com.parkingmanage.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.ChargeRecordDomain;
import com.parkingmanage.model.ChargeRuleDomain;
import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2016年1月11日
 */

@Repository
public class ChargeRecordDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(ChargeRecordDao.class.getName());
	
	/**
	 * 获取收费记录列表
	 * @return
	 */
	
	public List<ChargeRecordDomain> listAll(){
		List<ChargeRecordDomain> list = new ArrayList<ChargeRecordDomain>();
		String sql="SELECT * FROM tb_charge_record";
		System.out.println(sql);	
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> chargeMap =  it.next();
				ChargeRecordDomain charge = new ChargeRecordDomain();
				charge.setChargeId( (String)chargeMap.get("charge_id") );
				charge.setPayTime( (Date)chargeMap.get("pay_time") );
				charge.setConfirmTime( (Date)chargeMap.get("confirm_time") );
				charge.setActualMoney( (Float)chargeMap.get("actual_money") );
				charge.setPayType( (String)chargeMap.get("pay_type") );
				list.add(charge);
			}
		} catch(DataAccessException e){
			System.out.println("charge查询数据库出错--->listAll");
		}
		return list;
	}
	
	
	/*
	 * 停车收费rule,ChargeRule
	 * @return 
	 * */
	public List<ChargeRuleDomain> chargerule(){
		List<ChargeRuleDomain> list = new ArrayList<ChargeRuleDomain>();
		String sql1 = "SELECT * FROM tb_charge_rule ";
		System.out.println(sql1);
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql1);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> ruleMap =  it.next();
				ChargeRuleDomain rule = new ChargeRuleDomain();
				rule.setFreeTime( (int)ruleMap.get("free_time") );
				rule.setDayUnit( (int)ruleMap.get("day_unit"));
				rule.setNightUnit( (int)ruleMap.get("night_unit"));
				rule.setDayStart( (String)ruleMap.get("day_start") );
				rule.setDayEnd( (String)ruleMap.get("day_end") );
				rule.setBwiDayFee( (float)ruleMap.get("bwi_day_fee") );
				rule.setBwoDayFee( (float)ruleMap.get("bwo_day_fee") );
				rule.setBwiNightFee( (float)ruleMap.get("bwi_night_fee") );
				rule.setBwoNightFee( (float)ruleMap.get("bwo_night_fee") );
				rule.setBriDayFee( (float)ruleMap.get("bri_day_fee") );
				rule.setBroDayFee( (float)ruleMap.get("bro_day_fee") );
				rule.setBriNightFee( (float)ruleMap.get("bri_night_fee") );
				rule.setBroNightFee( (float)ruleMap.get("bro_night_fee") );		
				rule.setSwiDayFee( (float)ruleMap.get("swi_day_fee") );
				rule.setSwoDayFee( (float)ruleMap.get("swo_day_fee") );
				rule.setSwiNightFee( (float)ruleMap.get("swi_night_fee") );
				rule.setSwoNightFee( (float)ruleMap.get("swo_night_fee") );
				rule.setSriDayFee( (float)ruleMap.get("sri_day_fee") );
				rule.setSroDayFee( (float)ruleMap.get("sro_day_fee") );
				rule.setSriNightFee( (float)ruleMap.get("sri_night_fee") );
				rule.setSroNightFee( (float)ruleMap.get("sro_night_fee") );	
				list.add(rule);
			}
		} catch(DataAccessException e){
			System.out.println("web收费规则数据库出错--->chargerule");
		}
		return list;
	}
	
	
	
	
	
	/*
	 * 有免费时长，计算停车费用：返回金额
	 * @return
	 * */
	public float calfreetime(List<ChargeRuleDomain> rule,String cartype,Date timein,Date timeout) throws Exception{
		float fee = 0;//停车费
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String start = df.format(timein).substring(0,17)+"00";
		String end = df.format(timeout).substring(0,17)+"00";
		System.out.println("in_time is:"+start);
		System.out.println("out_time is:"+end);
		
		//判断在场时间是否大于免费时长
		int freetime=rule.get(0).getFreeTime();
		int inpark = (int)(Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())/1000/60;
		System.out.println("parktime is:"+inpark);
		if(inpark < freetime){//在场小于免费时长，不收费
			fee = 0;
			System.out.println("parktime less than freetime,no charging");
			return fee;
		}else{
			System.out.println("over freetime,start charging");
			start = new Timestamp(Timestamp.valueOf(start).getTime()+ freetime*60*1000).toString().substring(0,19);//加上免费时长后继续计费
		}
		
		if((int)(Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())/1000/60/60 <= 24){//在场时间不超过24小时
			System.out.println("no over 24 hours");
			if(isWeekend(start)==isWeekend(end)){//不跨休息日/工作日
				System.out.println("no over workday/weekend,不跨休息日/工作日");
				fee = calfee1(rule,start,end,cartype);
			}else{//跨休息日/工作日
				System.out.println("over workday/weekend,跨休息日/工作日");
				String border = end.substring(0,11) + "00:00:00";
				float fee1 = calfee1(rule,start,border,cartype);
				float fee2 = calfee1(rule,border,end,cartype);
				fee = fee1+fee2;	
			}			
		}else{//在场时间超过24小时
			System.out.println("over 24 hours");		
		    String border1 = addDay(start).substring(0,11) + "00:00:00";
		    float fee1 = calfee1(rule,start,border1,cartype);
		    String border2 = end.substring(0,11) + "00:00:00";
		    float fee2 = calfee1(rule,border2,end,cartype);
		    fee = fee1+fee2;
		    while(!border1.equals(border2)){
		    	String start1 = border1;
		    	String end1 = addDay(border1);
		    	fee = fee + calfee1(rule,start1,end1,cartype);
		    	border1 = addDay(border1);
		    }
		}	
		System.out.println("the parking fee is :"+fee);
		return fee;	
	}
	
	
	/*
	 * 无免费时长，计算停车费用：返回金额
	 * @return
	 */
	public float calfirsthour(List<ChargeRuleDomain> rule,String cartype,Date timein,Date timeout) throws Exception{
		float fee = 0;//停车费
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String start = df.format(timein).substring(0,17)+"00";
		String end = df.format(timeout).substring(0,17)+"00";
		System.out.println("in_time is:"+start);
		System.out.println("out_time is:"+end);
		
		//判断在场时间是否大于1小时
		int inpark = (int)(Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())/1000/60;
		System.out.println("parktime is:"+inpark);
		if(inpark < 60){//在场小于1小时
			System.out.println("parktime less than 1 hour.");
			if(isWeekend(start)==isWeekend(end)){//不跨休息日/工作日
				System.out.println("no over workday/weekend,不跨休息日/工作日");
				fee = calfee1(rule,start,end,cartype);
			}else{//跨休息日/工作日
				System.out.println("over workday/weekend,跨休息日/工作日");
				String border = end.substring(0,11) + "00:00:00";
				float fee1 = calfee1(rule,start,border,cartype);
				float fee2 = calfee1(rule,border,end,cartype);
				fee = fee1+fee2;	
			}				
		}else{//在场时间超过1小时
			System.out.println("over 1 hour.");
			String end1 = new Timestamp(Timestamp.valueOf(start).getTime()+ 60*60*1000).toString().substring(0,19);//加上1小时后继续计费
			if(isWeekend(start)==isWeekend(end1)){//不跨休息日/工作日
				System.out.println("no over workday/weekend,不跨休息日/工作日");
				fee = calfee1(rule,start,end1,cartype);
			}else{//跨休息日/工作日
				System.out.println("over workday/weekend,跨休息日/工作日");
				String border = end1.substring(0,11) + "00:00:00";
				float fee1 = calfee1(rule,start,border,cartype);
				float fee2 = calfee1(rule,border,end1,cartype);
				fee = fee1+fee2;	
			}	
			//算首小时后费用
			String start1 = new Timestamp(Timestamp.valueOf(start).getTime()+ 60*60*1000).toString().substring(0,19);
			if((int)(Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start1).getTime())/1000/60/60 <= 24){//在场时间不超过24小时
				System.out.println("no over 24 hours");
				if(isWeekend(start1)==isWeekend(end)){//不跨休息日/工作日
					System.out.println("no over workday/weekend,不跨休息日/工作日");
					fee = fee + calfee2(rule,start1,end,cartype);
				}else{//跨休息日/工作日
					System.out.println("over workday/weekend,跨休息日/工作日");
					String border = end.substring(0,11) + "00:00:00";
					float fee1 = calfee2(rule,start1,border,cartype);
					float fee2 = calfee2(rule,border,end,cartype);
					fee = fee + fee1 + fee2;	
				}			
			}else{//在场时间超过24小时
				System.out.println("over 24 hours");		
			    String border1 = addDay(start1).substring(0,11) + "00:00:00";
			    float fee1 = calfee2(rule,start1,border1,cartype);
			    String border2 = end.substring(0,11) + "00:00:00";
			    float fee2 = calfee2(rule,border2,end,cartype);
			    fee = fee + fee1 + fee2;
			    while(!border1.equals(border2)){
			    	String start2 = border1;
			    	String end2 = addDay(border1);
			    	fee = fee + calfee2(rule,start2,end2,cartype);
			    	border1 = addDay(border1);
			    }
			}	
		}
		System.out.println("the parking fee is :"+fee);
		return fee;	
	}
	
	
	/*
	 * 日期往后推一天
	 */
	private String addDay(String date) throws Exception{
		String add = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar c =  Calendar.getInstance();   
	    c.setTime(sf.parse(date)); 
	    c.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动  
	    add = sf.format(c.getTime());
		return add;
	}
	
	/*
	 * 有免费时长，计算停车费，不超过24小时的部分，fee取i_fee
	 * @param rule
	 * @param in_time
	 * @param out_time
	 * @param car_type
	 * @return
	 * */
	private float calfee1(List<ChargeRuleDomain> rule,String start,String end,String cartype){
		float fee = 0;

		String day_start = rule.get(0).getDayStart();
		String day_end = rule.get(0).getDayEnd();
		int day_unit = rule.get(0).getDayUnit();
		int night_unit = rule.get(0).getNightUnit();
		float day_fee = 0 , night_fee = 0;
		
		System.out.println("start time is:"+start);
		System.out.println("end time is:"+end);
		
		
		if(isWeekend(start)) {//判断是否为休息日
			if(cartype.equals("b")){
				day_fee = rule.get(0).getBriDayFee();
	    	    night_fee = rule.get(0).getBriNightFee();
	    	    System.out.println("大车休息日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}else{
	    		day_fee = rule.get(0).getSriDayFee();
	    	    night_fee = rule.get(0).getSriNightFee();
	    	    System.out.println("小车休息日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}
	    }else{
	    	if(cartype.equals("b")){
				day_fee = rule.get(0).getBwiDayFee();
	    	    night_fee = rule.get(0).getBwiNightFee();
	    	    System.out.println("大车工作日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}else{
	    		day_fee = rule.get(0).getSwiDayFee();
	    	    night_fee = rule.get(0).getSwiNightFee();
	    	    System.out.println("小车工作日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}
	    }
		
		fee = calculate(day_start,day_end,day_unit,night_unit,day_fee,night_fee,start,end,cartype);
		return fee;    
	}	
	
	/*
	 * fee取o_fee
	 */
	private float calfee2(List<ChargeRuleDomain> rule,String start,String end,String cartype){
		float fee = 0;

		String day_start = rule.get(0).getDayStart();
		String day_end = rule.get(0).getDayEnd();
		int day_unit = rule.get(0).getDayUnit();
		int night_unit = rule.get(0).getNightUnit();
		float day_fee = 0 , night_fee = 0;
		
		System.out.println("start time is:"+start);
		System.out.println("end time is:"+end);
		
		
		if(isWeekend(start)) {//判断是否为休息日
			if(cartype.equals("b")){
				day_fee = rule.get(0).getBroDayFee();
	    	    night_fee = rule.get(0).getBroNightFee();
	    	    System.out.println("大车休息日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}else{
	    		day_fee = rule.get(0).getSroDayFee();
	    	    night_fee = rule.get(0).getSroNightFee();
	    	    System.out.println("小车休息日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}
	    }else{
	    	if(cartype.equals("b")){
				day_fee = rule.get(0).getBwoDayFee();
	    	    night_fee = rule.get(0).getBwoNightFee();
	    	    System.out.println("大车工作日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}else{
	    		day_fee = rule.get(0).getSwoDayFee();
	    	    night_fee = rule.get(0).getSwoNightFee();
	    	    System.out.println("小车工作日,day_fee:"+day_fee+",night_fee:"+night_fee);
	    	}
	    }	
		fee = calculate(day_start,day_end,day_unit,night_unit,day_fee,night_fee,start,end,cartype);
		return fee;    
	}
		
	/*
	 * 计算费用
	 * @param day_start,day_end,day_unit,night_unit,day_fee,night_fee,start,end,cartype
	 * @return fee
	 */
	private float calculate(String day_start,String day_end,int day_unit,int night_unit,float day_fee,float night_fee,String start,String end,String cartype){
		float fee = 0;
		
		String start_flag , end_flag ;//所处时间段标志，1白天段，2晚上段
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd k:mm:ss");
		Date d_start = null,d_end = null;
		try{
			if(isBetween("00:00:00",day_start,start)){
				d_start = sdf.parse("1970-01-02 "+start.substring(11)); 
			}else{
				d_start = sdf.parse("1970-01-01 "+start.substring(11)); 
			}  
			if(isBetween("00:00:00",day_start,end)){
				d_end = sdf.parse("1970-01-02 "+end.substring(11)); 	
			}else{
		    	d_end = sdf.parse("1970-01-01 "+end.substring(11)); 
		    }
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}	
		if(isBetween(day_start,day_end,start)){
			start_flag = "1";
		    //System.out.println("start_flag:"+"1"+" , in_time in day section");
		}else{
			start_flag = "2";
			//System.out.println("start_flag:"+"2"+" , in_time in night section");
		}
		if(isBetween(day_start,day_end,end)){
			end_flag = "1";
			//System.out.println("end_flag:"+"1"+" , out_time in day section");
		}else{
			end_flag = "2";
			//System.out.println("end_flag:"+"2"+" , out_time in night section");
		}
		if(start_flag.equals(end_flag)){
			if(d_start.before(d_end)){//不跨段
				System.out.println("no over section");
				if(start_flag.equals("1")){
				    System.out.println("parktime in day section");
					fee = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())/1000/60/(float)day_unit))*day_fee;
				}else{
				    System.out.println("parktime in night section");
					fee = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())/1000/60/(float)night_unit))*night_fee;
				}
			}else{//跨段两次
				System.out.println("over section twice");
				if(start_flag.equals("1")){//先跨day_end，后跨day_start
					System.out.println("first over "+day_end+" then over "+day_start);
					float fee11 = (float)((Timestamp.valueOf(start.substring(0,11)+day_end).getTime() - Timestamp.valueOf(start).getTime())/1000/60/day_unit)*day_fee;
					float fee12 = (float)(((Timestamp.valueOf(start.substring(0,11)+day_end).getTime() - Timestamp.valueOf(start).getTime())/1000/60)%day_unit)*day_fee/day_unit;
					float fee21 = (float)((Timestamp.valueOf(end.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start.substring(0,11)+day_end).getTime())/1000/60/night_unit)*night_fee;
					float fee22 = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(end.substring(0,11)+day_start).getTime())/1000/60/(float)day_unit))*day_fee;
					fee = fee11+fee12+fee21+fee22;
					System.out.println("第一次跨段前整数个计价单位:"+fee11+"第一次跨段前按分钟算:"+fee12+day_end+"到"+day_start+"计费:"+fee21+"第二次跨段后计费:"+fee22);
				}else{//先跨day_start，后跨day_end
					System.out.println("first over "+day_start+" then over "+day_end);
					if(isBetween("00:00:00",day_start,start)){
						float fee11 = (float)((Timestamp.valueOf(start.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start).getTime())/1000/60/night_unit)*night_fee;
						float fee12 = (float)(((Timestamp.valueOf(start.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start).getTime())/1000/60)%night_unit)*night_fee/night_unit;
						float fee21 = (float)((Timestamp.valueOf(start.substring(0,11)+day_end).getTime() - Timestamp.valueOf(start.substring(0,11)+day_start).getTime())/1000/60/day_unit)*day_fee;
						float fee22 = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start.substring(0,11)+day_end).getTime())/1000/60/(float)night_unit))*night_fee;
						fee = fee11+fee12+fee21+fee22;
					    System.out.println("第一次跨段前整数个计价单位:"+fee11+"第一次跨段前按分钟算:"+fee12+day_start+"到"+day_end+"计费:"+fee21+"第二次跨段后计费:"+fee22);
					}else{
						float fee11 = (float)((Timestamp.valueOf(end.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start).getTime())/1000/60/night_unit)*night_fee;
						float fee12 = (float)(((Timestamp.valueOf(end.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start).getTime())/1000/60)%night_unit)*night_fee/night_unit;
						float fee21 = (float)((Timestamp.valueOf(end.substring(0,11)+day_end).getTime() - Timestamp.valueOf(end.substring(0,11)+day_start).getTime())/1000/60/day_unit)*day_fee;
					    float fee22 = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(end.substring(0,11)+day_end).getTime())/1000/60/(float)night_unit))*night_fee;
						fee = fee11+fee12+fee21+fee22;
						System.out.println("第一次跨段前整数个计价单位:"+fee11+"第一次跨段前按分钟算:"+fee12+day_start+"到"+day_end+"计费:"+fee21+"第二次跨段后计费:"+fee22);						
					}						
				}
					
			}
		}else{//跨段一次
			System.out.println("over section once");
			if(start_flag.equals("1")){//跨day_end
				System.out.println("over "+day_end);
				float fee11 = (float)((Timestamp.valueOf(start.substring(0,11)+day_end).getTime() - Timestamp.valueOf(start).getTime())/1000/60/day_unit)*day_fee;
				float fee12 = (float)(((Timestamp.valueOf(start.substring(0,11)+day_end).getTime() - Timestamp.valueOf(start).getTime())/1000/60)%day_unit)*day_fee/day_unit;
				float fee2 = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start.substring(0,11)+day_end).getTime())/1000/60/(float)night_unit))*night_fee;
				System.out.println("跨段前整数个计价单位:"+fee11+"跨段前按分钟算:"+fee12+"跨段后:"+fee2);
				fee = fee11+fee12+fee2;
			}else{//跨day_start
			    System.out.println("over "+day_start);
			    float fee11 = (float)((Timestamp.valueOf(end.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start).getTime())/1000/60/night_unit)*night_fee;
				float fee12 = (float)((Timestamp.valueOf(end.substring(0,11)+day_start).getTime() - Timestamp.valueOf(start).getTime())/1000/60%night_unit)*night_fee/night_unit;
				float fee2 = (float)Math.ceil(((Timestamp.valueOf(end).getTime() - Timestamp.valueOf(end.substring(0,11)+day_start).getTime())/1000/60/(float)day_unit))*day_fee;
				System.out.println("跨段前整数个计价单位:"+fee11+"跨段前按分钟算:"+fee12+"跨段后:"+fee2);
				fee = fee11+fee12+fee2;
			}
		}
		return fee;
	}
	
	
	/*
	 * 查询当前时间的费率
	 * @param timenow ,cartype
	 */
	public String CheckChargeRate(String timeIn,String timeOut,String cartype){
		String chargeRate = "";
		List<ChargeRuleDomain> rule = chargerule();
		int freetime = rule.get(0).getFreeTime();
		String overh = new Timestamp(Timestamp.valueOf(timeIn).getTime()+ 60*60*1000).toString().substring(0,19);
		String dayStart = timeOut.substring(0, 11)+rule.get(0).getDayStart();
		String dayEnd = timeOut.substring(0, 11)+rule.get(0).getDayEnd();
		String dayUnit = ""+rule.get(0).getDayUnit();
		String nightUnit = ""+rule.get(0).getNightUnit();
		if(cartype.equals("s")){//小型车
			if(isWeekend(timeOut)){//休息日
				if(isBetween0(dayStart, dayEnd, timeOut)){//白天
					if(freetime==0){//有首小时
						if(isBetween0(timeIn,overh,timeOut)){//首小时内
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
				if(isBetween0(dayStart, dayEnd, timeOut)){//白天
					if(freetime==0){//有首小时
						if(isBetween0(timeIn,overh,timeOut)){//首小时内
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
				if(isBetween0(dayStart, dayEnd, timeOut)){//白天
					if(freetime==0){//有首小时
						if(isBetween0(timeIn,overh,timeOut)){//首小时内
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
				if(isBetween0(dayStart, dayEnd, timeOut)){//白天
					if(freetime==0){//有首小时
						if(isBetween0(timeIn,overh,timeOut)){//首小时内
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
		return chargeRate;
		
	}
	
	/*
	 *判断date是否是休息日 
	 */
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
	
	/*
	 * 判断是否在某个时间段内   day_start<=in_time<d_end
	 * @param day_start
	 * @param day_end
	 * @param in_time
	 * @return
	 */
	private boolean isBetween( String day_start, String day_end, String in_time ){
		boolean flag = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd k:mm:ss");
		
		
		try {
			Date d_start = df.parse(in_time.substring(0,11)+day_start);
			//System.out.println(d_start.toString());
			Date d_end = df.parse(in_time.substring(0,11)+day_end);
			//System.out.println(d_end.toString());
			Date d_in = df.parse(in_time);	
			//System.out.println(d_in.toString());
			if( !d_in.before(d_start) && d_in.before(d_end) ){
				flag = true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
	 * 判断是否在某个时间段内   day_start<=in_time<d_end
	 * @param day_start
	 * @param day_end
	 * @param in_time
	 * @return
	 */
	private boolean isBetween0( String start, String end, String in_time ){
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
	
	/*
	 * 更新收费标准
	 * @return
	 */
	public boolean update(String day_start,String day_end,int day_unit,int night_unit,int freetime,
			float bwi_day_fee,float bwo_day_fee,float bwi_night_fee,float swi_day_fee,float swo_day_fee,float swi_night_fee,
			float bri_day_fee,float bro_day_fee,float bri_night_fee,float sri_day_fee,float sro_day_fee,float sri_night_fee) {
		String sql = "UPDATE tb_charge_rule SET free_time=?, day_unit=?, night_unit=?, day_start=?, day_end=?, "
				+ "bwi_day_fee=?, bwo_day_fee=?, bwi_night_fee=?, bwo_night_fee=?,swi_day_fee=?, swo_day_fee=?, swi_night_fee=?, swo_night_fee=?,"
				+ "bri_day_fee=?, bro_day_fee=?, bri_night_fee=?, bro_night_fee=?,sri_day_fee=?, sro_day_fee=?, sri_night_fee=?, sro_night_fee=?";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new Object[]{
					freetime,
					day_unit,
					night_unit,
					day_start,
					day_end,
					bwi_day_fee, 
					bwo_day_fee, 
					bwi_night_fee,
					bwi_night_fee,
					swi_day_fee, 
					swo_day_fee, 
					swi_night_fee, 
					swi_night_fee,
					bri_day_fee, 
					bro_day_fee, 
					bri_night_fee, 
					bri_night_fee,
					sri_day_fee, 
					sro_day_fee, 
					sri_night_fee, 
					sri_night_fee
			});
		} catch (DataAccessException e) {
			System.out.println("web用户信息查询数据库出错--->update");
			return false;
		}
		return true;
	}
	
	
	/**
	 * 添加收费记录
	 * @param 
	 * @return
	 */
	public boolean insert(ChargeRecordDomain charge){
		String sql = "INSERT INTO tb_charge_record (charge_id,pay_time,confirm_time,actual_money,pay_type)"
					 +"values(?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{charge.getChargeId(),charge.getPayTime(),charge.getConfirmTime(),charge.getActualMoney(),charge.getPayType()});
		} catch (DataAccessException e) {
			logger.error("chargerecord查询数据库出错--->insert");
			logger.error(e);
			return false;
		}
		return true;
	}
	
}
