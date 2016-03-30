package com.parkingmanage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//type:0代表全选，1代表临时，2代表长期；
	//state：0代表全选，1代表场内，2代表出场，3代表预约；
	@RequestMapping(value="/io_query.action")
	public @ResponseBody String parkioQuery(){
		System.out.println(parkioService.query(1,2,"A","2016-01-07 12:02:38","2016-01-12 16:02:38"));
		return parkioService.query(1,2,"A","2016-01-07 12:02:38","2016-01-12 16:02:38").toString();
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
