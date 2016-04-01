package com.parkingmanage.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.parkingmanage.service.LearnlxyService;
import com.parkingmanage.service.ParkIoService;

@Controller
//@RequestMapping(value="CarManageSystem")
public class LearnlxyController {
    
	@Autowired
	private ParkIoService parkioService;
	@Autowired
	private CarportService carportService;
	
	@Autowired
	private LearnlxyService  LearnlxyService;
	//car_manage页面显示
	@RequestMapping(value="/lxy_car.action")
	public ModelAndView recordlist(){
		List<ParkIoDomain> records= LearnlxyService.listAll();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("car_manage/query");
		mv.addObject("records", records);
		return mv;
	}
	//查找：按carLicense车牌号
			@RequestMapping(value="/lxy_search.action", method=RequestMethod.POST)
			public ModelAndView CarSearch(String carLicense){
				List<ParkIoDomain> records= LearnlxyService.querybyCarLicense(carLicense);
				ModelAndView mv=new ModelAndView();
				mv.setViewName("car_manage/query");
				mv.addObject("records", records);
				return mv;
			}
			
			
			//type:0代表全选，1代表临时，2代表长期；
			//state：0代表全选，1代表场内，2代表出场，3代表预约；
			@RequestMapping(value="/lxy_query")
			public @ResponseBody void parkioquery(HttpServletResponse response,String condition)
			{
				Integer Type=0,State=0,Opentype=0;
				String Exittype="all",Starttime="",Endtime="";
				String[] Str = condition.split("/");
				for(int i=0;i<Str.length;i++){
				}
				for(int i=0;i<Str.length;i++){
					if(Str[i].contains("type")){
						Type = Integer.parseInt(Str[i].substring(4));
						//System.out.println("车辆种类为："+Type);
					}
					if(Str[i].contains("state")){
						State = Integer.parseInt(Str[i].substring(5));
						//System.out.println("车辆状态为："+State);
					}
					if(Str[i].contains("open")){
						Opentype = Integer.parseInt(Str[i].substring(4));
						//System.out.println("开放标准为："+Opentype);
					}
					if(Str[i].contains("exit")){
						Exittype = Str[i].substring(4);
						//System.out.println("出口类型为："+Exittype);
					}
					if(Str[i].contains("time")){
						String t = Str[i].substring(4);
						String[] t0 = t.split("—");
						Starttime = t0[0]+":00";
						Endtime = t0[1]+":00";
						//System.out.println("Starttime为："+Starttime);
						//System.out.println("Endtime为："+Endtime);
					}
				}
				List<ParkIoDomain> parkiolist = parkioService.query(Type,State,Exittype,Starttime,Endtime);
				
				JSONArray rows = new JSONArray(); 
				for(int i=0;i<parkiolist.size();i++){
					JSONObject result = new JSONObject();
					result.put("ParkioId", parkiolist.get(i).getParkioId());
					result.put("CarLicense", parkiolist.get(i).getCarLicense());
					if(parkiolist.get(i).getTimeIn()!=null){
					      result.put("TimeIn", parkiolist.get(i).getTimeIn().toString());
					}else{
						  result.put("TimeIn","no record");
					}
					if(parkiolist.get(i).getTimeOut()!=null){
					      result.put("TimeOut", parkiolist.get(i).getTimeOut().toString());
					}else{
						  result.put("TimeOut","no record");
					}
					//result.put("PhotolocIn", parkiolist.get(i).getPhotolocIn());
					//result.put("PhotolocOut", parkiolist.get(i).getPhotolocOut());
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

		
}
