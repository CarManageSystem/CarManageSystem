package com.parkingmanage.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



import com.parkingmanage.model.ParkIoDomain;
import com.parkingmanage.service.LearnlxyService;

@Controller
public class LearnlxyController {
    
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
		
}
