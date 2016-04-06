package com.parkingmanage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.ParkIoDomain;
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
				if(parkiolist.get(i).getTimeIn()!=null){
				      result.put("TimeIn", parkiolist.get(i).getTimeIn().toString().substring(0, 19));
				}else{
					  result.put("TimeIn","no record");
				}
				if(parkiolist.get(i).getTimeOut()!=null){
				      result.put("TimeOut", parkiolist.get(i).getTimeOut().toString().substring(0, 19));
				}else{
					  result.put("TimeOut","no record");
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
