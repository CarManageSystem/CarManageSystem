package com.manage.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.service.CarTypeService;

@Controller
@RequestMapping(value = "/auto")
public class CarTypeController {
	@Autowired
	
	CarTypeService carTypeService;
	
	@RequestMapping(value = "/brand.first")
	public @ResponseBody void fetchFirstBrand(HttpServletResponse response) throws Exception {
		System.out.println("fetchFirstBrand");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(carTypeService.fetchFirstBrand().toString());
	}

	@RequestMapping(value = "/brand.second")
	public @ResponseBody void fetchSecondBrand(int id,HttpServletResponse response) throws Exception {
		System.out.println("fetchFirstBrand");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(carTypeService.fetchSecondBrand(id).toString());
	}
	
	@RequestMapping(value = "/brand.third")
	public @ResponseBody void fetchThirdBrand(int id,HttpServletResponse response) throws Exception {
		System.out.println("fetchFirstBrand");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(carTypeService.fetchThirdBrand(id).toString());
	}
}
