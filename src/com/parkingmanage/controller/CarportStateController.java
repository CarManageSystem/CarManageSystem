package com.parkingmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="update_now.action", method=RequestMethod.GET)
	public void carportState(int parkId){
		int fusing = carportstateService.fixedUsing(parkId);
		int funused = carportstateService.fixedUnused(parkId);
		int tusing = carportstateService.tempUsing(parkId);
		int tunused = carportstateService.tempUnused(parkId);
		carportstateService.update(fusing, funused, tusing, tunused, parkId);
	}
	
	

}
