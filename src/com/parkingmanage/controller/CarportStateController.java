package com.parkingmanage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingmanage.model.CarportDomain;
import com.parkingmanage.model.CarportStateDomain;
import com.parkingmanage.service.CarportStateService;

/**
 * 
 * @author zhangx
 * @date 2016年1月6日
 */

@Controller
public class CarportStateController{
	@Autowired
	private CarportStateService carportstateService;

	//统计车位状态
	@RequestMapping(value="fixed_using.action", method=RequestMethod.GET)
	public String carportState(int parkId){
		
		int fusing = carportstateService.fixedUsing(parkId);
		int funused = carportstateService.fixedUnused(parkId);
		int tusing = carportstateService.tempUsing(parkId);
		int tunused = carportstateService.tempUnused(parkId);
		System.out.println(fusing);
		System.out.println(funused);
		System.out.println(tusing);
		System.out.println(tunused);
		carportstateService.update(fusing, funused, tusing, tunused, parkId);
		return "index";	
	}
	
	

}
