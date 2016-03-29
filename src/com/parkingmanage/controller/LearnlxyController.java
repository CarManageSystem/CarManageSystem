package com.parkingmanage.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.service.LearnlxyService;

public class LearnlxyController {
    
	@Autowired
	private LearnlxyService  LearnlxyService;
	//查找：按carLicense车牌号
			@RequestMapping(value="/lxy_search.action", method=RequestMethod.POST)
			public ModelAndView CarSearch(String carLicense){
				System.out.print("car_search");
				List<ParkIoDomain> cars= LearnlxyService.querybyCarLicense(carLicense);
				ModelAndView mv=new ModelAndView();
				mv.setViewName("car_manage/query");
				mv.addObject("cars", cars);
				return mv;
			}
			@RequestMapping(value="/lxy_test.action")
			public void test(){
				System.out.print("lxytesting");
			}
		
}
