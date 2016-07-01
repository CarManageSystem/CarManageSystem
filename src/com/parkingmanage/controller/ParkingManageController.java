package com.parkingmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.CarportDomain;
import com.parkingmanage.model.UserDomain;
import com.parkingmanage.service.CarportService;


@Controller
public class ParkingManageController {

	@Autowired
	private CarportService carportService;
	
	//车场管理页面
	@RequestMapping(value="/display_map.action")
	public ModelAndView ParkingIndex(){
		List<CarportDomain> list= carportService.listAll();;
		ModelAndView mv=new ModelAndView();
		mv.setViewName("parking_manage/DisplayMap");
		mv.addObject("list", list);
		return mv;
	}
	
	//点击地图中车位时返回信息
		@RequestMapping(value="getcarportinfo.action")
		@ResponseBody
		public CarportDomain getCarportInfo(String spaceId){	
		
			return carportService.queryById(spaceId, 2).get(0);
		}
}
