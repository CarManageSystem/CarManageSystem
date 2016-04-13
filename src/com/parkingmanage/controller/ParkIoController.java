package com.parkingmanage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.CarDomain;
import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.service.CarService;
import com.parkingmanage.service.CarportService;
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
	
	//车辆入场，采集图片和记录牌照ok
	@RequestMapping(value="insert_in.action", method=RequestMethod.GET)
	public void insertIn(String carLicense,String photolocIn){
		//采集图片和记录牌照
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String timenow = df.format(new Date());
		String timeString = timenow.substring(2,16);
		String parkioId = timeString + "-" + carLicense;
		parkioService.insertin(parkioId,carLicense, photolocIn,timenow);
		//如果为临时车辆安排车位，如果为固定车辆直接放行
		if( !(parkioService.cartype(carLicense)) ){
			String carportid = parkioService.arrageport(carLicense);
			System.out.println(carportid);
			carportService.insertlicense(carportid,2,carLicense);//停车场id:2
			parkioService.insertport(parkioId, carportid);
		}else{
			String fixedportid = parkioService.findport(carLicense);
			System.out.println(fixedportid);
			parkioService.insertport(parkioId, fixedportid);
		}
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
	
	//车辆出场
	@RequestMapping(value="insert_out.action", method=RequestMethod.GET)
	public @ResponseBody boolean insertOut(String carLicense,String photolocOut){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String timenow = df.format(new Date());
		return parkioService.insertout(carLicense, photolocOut, timenow);
	}
}
