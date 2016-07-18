package com.parkingmanage.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.WebSocket.SocketServer;
import com.parkingmanage.model.CarDomain;
import com.parkingmanage.model.ChargeCardDomain;
import com.parkingmanage.model.ChargeRuleDomain;
import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.service.CarService;
import com.parkingmanage.service.CarportService;
import com.parkingmanage.service.ChargeCardService;
import com.parkingmanage.service.ChargeRecordService;
import com.parkingmanage.service.ParkIoService;

/**
 * 
 * @author zhangx
 * @date 2016年1月7日
 */

@Controller
public class ParkIoController{
	@Autowired
	private ParkIoService parkioService;
	@Autowired
	private CarportService carportService;
	@Autowired
	private CarService carService;
	@Autowired
	private ChargeRecordService chargerecordService;
	@Autowired
	private ChargeCardService chargecardService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//car_manage页面显示
		@RequestMapping(value="/car_records.action")
		public ModelAndView recordlist(){
			List<ParkIoDomain> records= parkioService.listAll();
			ModelAndView mv=new ModelAndView();
			mv.setViewName("car_manage/query");
			mv.addObject("records", records);
			return mv;
		}
		
		
	//查找：按carLicense车牌号
		@RequestMapping(value="/car_search.action", method=RequestMethod.POST)
		public ModelAndView CarSearch(String carLicense){
			List<ParkIoDomain> records= parkioService.querybyCarLicense(carLicense);
			ModelAndView mv=new ModelAndView();
			mv.setViewName("car_manage/query");
			mv.addObject("records", records);
			return mv;
		}
		
		
	//显示车场车位状况
		@RequestMapping(value="/park_state")
		public @ResponseBody void parkstate(HttpServletResponse response)
		{
			Map<String,Integer> parkmap = parkioService.parkstate();
			int into = parkmap.get("into");
			int out = parkmap.get("out");
			int order = parkmap.get("order");
			int inside = parkmap.get("inside");
			int longterm = parkmap.get("longterm");
			int temp = parkmap.get("temp");
			int auto = parkmap.get("auto");
			int cash = parkmap.get("cash");
			int force = parkmap.get("force");
			JSONObject result = new JSONObject();
			result.put("into", into);
			result.put("out", out);
			result.put("order", order);
			result.put("inside", inside);
			result.put("longterm", longterm);
			result.put("temp", temp);
			result.put("auto", auto);
			result.put("cash", cash);
			result.put("force", force);
			try {
				response.getWriter().write(result.toString());
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		//type:0代表全选，1代表临时，2代表长期；
		//state：0代表全选，1代表场内，2代表出场，3代表预约；
		@RequestMapping(value="/car_query")
		public @ResponseBody void parkioquery(HttpServletResponse response,String condition)
		{
			Integer Type=0,State=0;
			String Passtype="all",Exittype="all",Starttime="",Endtime="";
			String[] Str = condition.split("/");
			for(int i=0;i<Str.length;i++){
			}
			for(int i=0;i<Str.length;i++){
				if(Str[i].contains("type")){
					Type = Integer.parseInt(Str[i].substring(4));
				}
				if(Str[i].contains("state")){
					State = Integer.parseInt(Str[i].substring(5));
				}
				if(Str[i].contains("pass")){
					Passtype = Str[i].substring(4);					
				}
				if(Str[i].contains("exit")){
					Exittype = Str[i].substring(4);					
				}
				if(Str[i].contains("time")){
					String t = Str[i].substring(4);
					String[] t0 = t.split("—");
					Starttime = t0[0]+":00";
					Endtime = t0[1]+":00";
				}
			}
			List<ParkIoDomain> parkiolist = parkioService.query(Type,State,Passtype,Exittype,Starttime,Endtime);
		
			JSONArray rows = new JSONArray(); 
			for(int i=0;i<parkiolist.size();i++){
				JSONObject result = new JSONObject();
				result.put("ParkioId", parkiolist.get(i).getParkioId());
				result.put("CarLicense", parkiolist.get(i).getCarLicense());
				if(parkioService.cartype(parkiolist.get(i).getCarLicense())){
					result.put("CarType","长期");
					}else{
						result.put("CarType", "临时");
				}
				if(parkiolist.get(i).getTimeIn()!=null){
				      result.put("TimeIn", parkiolist.get(i).getTimeIn().toString().substring(0,19));
				}else{
					  result.put("TimeIn","");
				}
				if(parkiolist.get(i).getTimeOut()!=null){
				      result.put("TimeOut", parkiolist.get(i).getTimeOut().toString().substring(0,19));
				}else{
					  result.put("TimeOut","");
				}
				result.put("CarportId", parkiolist.get(i).getCarportId());
				result.put("ExitType", parkiolist.get(i).getExitTypeString());							
				rows.add(result);			
			}
			try {
				response.getWriter().write(rows.toString());
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	//车辆详情页
	@RequestMapping(value="/car_detail.action")
	public String carDetail(String carLicense,HttpServletRequest request){		
	    request.setAttribute("CarLicense",carLicense);
	    return "car_manage/car_detail";
	}
	//返回停车记录
	@RequestMapping(value="/park_records")
	public @ResponseBody void parkrecords(HttpServletResponse response,String CarLicense){
		List<ParkIoDomain> records= parkioService.querybyCarLicense(CarLicense);
		JSONArray rows = new JSONArray(); 
		for(int i=0;i<records.size();i++){
			JSONObject result = new JSONObject();
			if(records.get(i).getTimeIn()!=null){
			      result.put("TimeIn", records.get(i).getTimeIn().toString().substring(0,19));
			}else{
				  result.put("TimeIn","");
			}
			if(records.get(i).getTimeOut()!=null){
			      result.put("TimeOut", records.get(i).getTimeOut().toString().substring(0,19));
			}else{
				  result.put("TimeOut","");
			}
			result.put("PhotolocIn", records.get(i).getPhotolocIn());
			result.put("PhotolocOut", records.get(i).getPhotolocOut());
			result.put("CarportId", records.get(i).getCarportId());
			result.put("ExitType", records.get(i).getExitTypeString());
			if(records.get(i).getPassType()=="1"){
				result.put("PassType", "自动");
			}else if(records.get(i).getPassType()=="2"){
				result.put("PassType", "现金");
			}else{
				result.put("PassType", "强制");
			}
			
			if(records.get(i).getOrderFlag()=="1"){
				result.put("OrderFlag", "是");
			}else{
				result.put("OrderFlag", "否");
			}
			
			rows.add(result);			
		}
		try {
			response.getWriter().write(rows.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	//判断是否是长期车辆，是的话返回详细信息
	@RequestMapping(value="/longterm_detail")
	public @ResponseBody void longtermdetail(HttpServletResponse response,String CarLicense){		
		JSONObject result = new JSONObject();
		if(parkioService.cartype(CarLicense)){
			result.put("type", 1);
			CarDomain detail = carService.queryByLicense(CarLicense).get(0);
			result.put("CarBrand", detail.getCarBrand());
			result.put("CarType", detail.getCarType());
			result.put("CarColor", detail.getCarColor());
			if(detail.getProductionDate()!=null){
				result.put("ProductionDate", detail.getProductionDate().toString().substring(0,19));
			}else{
				result.put("ProductionDate","");
			}
			result.put("EngineNumber", detail.getEngineNumber());
			result.put("OutputVolume", detail.getOutputVolume());
			result.put("IdentifictionNumber", detail.getIdentifictionNumber());
			result.put("CarDistance", detail.getCarDistance());
			if(detail.getInitialDate()!=null){
				result.put("InitialDate", detail.getInitialDate().toString().substring(0,19));			
			}else{
				result.put("InitialDate", "");
			}			
			result.put("CarPhoto", detail.getCarPhoto());
			result.put("OwnerName", detail.getOwnerName());
			result.put("OwnerAge", detail.getOwnerAge());
			if(detail.getOwnerSex()==1){
				result.put("OwnerSex", "男");
			}else{
				result.put("OwnerSex", "女");
			}	
			result.put("OwnerAddress", detail.getOwnerAddress());
			result.put("OwnerTel", detail.getOwnerTel());		
		}else{
			result.put("type", 0);
		}
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//
	@RequestMapping(value="/io_list.action")
	public @ResponseBody String parkioList(){
		System.out.println(parkioService.listAll());
		return parkioService.listAll().get(0).toString();
	}
	
	@RequestMapping(value = "/inpark", method=RequestMethod.POST) 
	public @ResponseBody void parkingLot_In(HttpServletRequest request, 
			HttpServletResponse response) {
		System.out.println("receive camera message!");
		try {
			InputStream inputStream = request.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			String param0 = buffer.toString();
			String[] param = param0.split(",");
			insertIn(param[0],param[1]);
		}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	
	
			
	
	
	//车辆入场，采集图片和记录牌照ok
	@RequestMapping(value="insert_in.action", method=RequestMethod.GET)
	public void insertIn(String carLicense,String photolocIn){
		//采集图片和记录牌照
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String timenow = df.format(new Date());
		String timeString = timenow.substring(2,16);
		String parkioId = timeString + "-" + carLicense;
		parkioService.insertin(parkioId,carLicense, photolocIn,timenow);
		String carportid;
		//如果为临时车辆安排车位，如果为固定车辆直接放行
		if( !(parkioService.cartype(carLicense)) ){
			carportid = parkioService.arrageport(carLicense);
			//System.out.println(carportid);
			carportService.insertlicense(carportid,2,carLicense);//停车场id:2
			parkioService.insertport(parkioId, carportid);
		}else{
			//String fixedportid = parkioService.findport(carLicense);
			carportid = parkioService.findport(carLicense);
			//System.out.println(fixedportid);
			//parkioService.insertport(parkioId, fixedportid);
			parkioService.insertport(parkioId, carportid);
		}
		//车辆入场，向app端发消息
		JSONObject result =new JSONObject();
		String cartype = "s";
		String parkingLot = "枫蓝国际停车场";//所在停车场
		String parkingPort = carportid;//车位
		String fee = "0.0";//费用,如果数据库中没有出场时间 则按当前时间计费
		String timeIn = timenow;
		List<String> chargeRate = new ArrayList<String>();
		chargeRate.add(chargerecordService.CheckChargeRate(timeIn, timeIn, cartype));
		result.put("parkioId", parkioId);
		result.put("plateNumber", carLicense);
		result.put("parkingLot", parkingLot);
		result.put("extanceTime", timeIn);
		//result.put("OutTime", timeOut);
		result.put("chargeStandard", chargeRate);
		result.put("currentFee", fee);
		result.put("parkingPort", parkingPort);
		String pathUrl = "http://139.129.110.224/CarManageSystem/push/park"; 		
		httpRequest(pathUrl, "POST", result.toString());
		
		
	}
	
	//车辆入场,如果为临时车辆安排车位
	@RequestMapping(value="arrange_port.action", method=RequestMethod.GET)
	public @ResponseBody String arragePort(String carLicense){
		return parkioService.arrageport(carLicense);
	}
	
	//车辆入场,如果为临时车辆安排车位，如果为固定车辆直接放行
	@RequestMapping(value="insert_inrecord.action", method=RequestMethod.GET)
	public void insertInrecord(String carLicense){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String timeString = df.format(new Date()).substring(2,16);
		String parkioId = timeString + "-" + carLicense;
//		parkioService.insertin(parkioId,carLicense,photolocIn,timeString);
	}
	
	@RequestMapping(value="find_port.action", method=RequestMethod.GET)
	public @ResponseBody String findPort(String carLicense){
		return parkioService.findport(carLicense);
	}
	
	@RequestMapping(value = "/outpark", method=RequestMethod.POST) 
	public @ResponseBody boolean parkingLot_Out(HttpServletRequest request, 
			HttpServletResponse response) {
		System.out.println("receive camera message!");
		try {
			InputStream inputStream = request.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			String param0 = buffer.toString();
			String[] param = param0.split(",");
			return insertOut(param[0],param[1]);
		}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
	//车辆出场
	@RequestMapping(value="insert_out.action", method=RequestMethod.GET)
	public @ResponseBody boolean insertOut(String carLicense,String photolocOut) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String timenow = df.format(new Date());
		
		String sql = "select park_io_id from tb_park_io_record where car_license=? and time_out is null and photo_loc_out is null";
		String parkioId;
		parkioId = jdbcTemplate.queryForObject(sql,new Object[]{carLicense},String.class);
		parkioService.insertout(carLicense, photolocOut, timenow);
		fetchAppCheck(parkioId);
		return true;
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
						//System.out.println("freetime,the parking fee is:"+fee);
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
			
			return fee;
			
		}
			
			//app实时查询接口：车牌号、停车场、出入场时间、当前收费标准、实时费用
			@RequestMapping(value = "/appcheck.action")
			public @ResponseBody void fetchAppCheck(String parkioId) throws Exception {
				JSONObject result =new JSONObject();
				//response.setCharacterEncoding("utf-8");,HttpServletResponse response; 
				ParkIoDomain park = parkioService.querybyParkioId(parkioId);
				String plateNumber = park.getCarLicense();//车牌号
				String timeIn = park.getTimeIn().toString().substring(0,19); //入场时间
				String timeOut;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (park.getTimeOut() == null) {
					Date date = new Date();
					
					timeOut = (sdf.format(date)).toString().substring(0, 19);
				} else {
					timeOut = park.getTimeOut().toString().substring(0,19); //出场时间
				}
				String cartype = "s";
				String parkingLot = "枫蓝国际停车场";//所在停车场
				String parkingPort = park.getCarportId();//车位
				//String fee = String.format("%.1f", (float)CalCharge(parkioId));//费用,如果数据库中没有出场时间 则按当前时间计费
				float fee0 = (float)CalCharge(parkioId);
				String fee1 = String.format("%.2f",fee0);
				Float a = (Float)fee0*100;
				String fee = ""+a.intValue();
				List<String> chargeRate = new ArrayList<String>();
				chargeRate.add(chargerecordService.CheckChargeRate(timeIn, timeOut, cartype));
				result.put("parkioId", parkioId);
				result.put("plateNumber", plateNumber);
				result.put("parkingLot", parkingLot);
				result.put("extanceTime", timeIn);
				result.put("OutTime", timeOut);
				result.put("chargeStandard", chargeRate);
				result.put("currentFee", fee);
				result.put("parkingPort", parkingPort);
				//将收费记录插入tb_charge_record
				String sql = "INSERT INTO tb_charge_record VALUES(?,?,?,?,?,?)";
				jdbcTemplate.update(sql, new Object[]{timeOut.substring(2, 16)+"-"+plateNumber+"-"+fee1,timeOut,timeOut,fee1,"0",parkioId});
				String pathUrl = "http://139.129.110.224/CarManageSystem/push/park"; 		
				httpRequest(pathUrl, "POST", result.toString());
				//车辆出场，主动推送出场车辆信息至出入口处
				JSONObject json =new JSONObject();
				json.put("carLicense", plateNumber);
				json.put("timeIn", timeIn);
				json.put("timeOut", timeOut);
				String parkTime = ""+((sdf.parse(timeOut).getTime()-sdf.parse(timeIn).getTime())/1000/60);
				json.put("parkTime", parkTime);
				json.put("fee", fee1);
				json.put("payType", 0);
				SocketServer.sendByIp("1", json.toString());
			}
			
			//利用HttpURLConnection发送数据output给url
			public String httpRequest(String requestUrl, String requestMethod, String output) {
				try{
					URL url = new URL(requestUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.setUseCaches(false);
					connection.setRequestMethod(requestMethod);
					if (null != output) {
						OutputStream outputStream = connection.getOutputStream();
						outputStream.write(output.getBytes("UTF-8"));
						outputStream.close();
					}
					// 从输入流读取返回内容
					InputStream inputStream = connection.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String str = null;
					StringBuffer buffer = new StringBuffer();
					while ((str = bufferedReader.readLine()) != null) {
						buffer.append(str);
					}
					bufferedReader.close();
					inputStreamReader.close();
					inputStream.close();
					inputStream = null;
					connection.disconnect();
					return buffer.toString();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return "";
			}
	
	
}
