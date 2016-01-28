package com.manage.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.service.AppCarService;

@Controller
@RequestMapping(value = "/car")
public class AppCarController {
	@Autowired
	
	AppCarService carService;
	
	@RequestMapping(value = "/switch_pay")
	public @ResponseBody String switchPayValue(HttpServletRequest request,String p,String carl,int isp) {
		System.out.println(request.getSession().getAttribute("phone"));
		return String.valueOf(carService.switchPayValue(p, carl,isp > 0));
	}
	
	@RequestMapping(value = "/add_info", method=RequestMethod.POST)
	public @ResponseBody String addCarInfo(String phone,String carLicense,int isOwner,
			String carBrand,String engineNum) {
		return carService.addCarInfo(phone, carLicense, isOwner > 0, carBrand,engineNum);
	}
	
	@RequestMapping(value = "/fetch_info")
	public @ResponseBody String fetchCarInfo(String p) {
		System.out.print(p);
		JSONArray ja = JSONArray.fromObject(carService.fetchCarInfo(p));
		System.out.println(">>>>" + ja);
		return ja.toString();
	}

}
