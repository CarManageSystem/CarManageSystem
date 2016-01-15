package com.parkingmanage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingmanage.service.CarportService;

/**
 * 
 * @author zhangx
 * @date 2016年1月4日
 */

@Controller
public class CarportController{
	@Autowired
	private CarportService carportService;

	//修改车位状态为空闲0
	@RequestMapping(value="update_statezero.action", method=RequestMethod.GET)
	public @ResponseBody Object updateStatezero(String carportId,int parkId){
		Map<String,String> res = new HashMap<String,String>();
		if(carportService.updatestatezero(carportId, parkId)){
			if(carportService.licensenull(carportId, parkId)){
				res.put("success", "true");
				res.put("msg", "修改成功");
			}else{
				res.put("success", "false");
				res.put("msg", "修改失败");
			}
		}else{
			res.put("success", "false");
			res.put("msg", "修改失败");
		}
		return res; 
	}
	
	//修改车位状态为占用1
	@RequestMapping(value="update_stateone.action", method=RequestMethod.GET)
	public @ResponseBody Object updateStateone(String carportId,int parkId){
		Map<String,String> res = new HashMap<String,String>();
		if(carportService.updatestateone(carportId, parkId)){
			res.put("success", "true");
			res.put("msg", "修改成功");
		}else{
			res.put("success", "false");
			res.put("msg", "修改失败");
		}
		return "index"; 
	}
	
	//修改车位性质为固定0
	@RequestMapping(value="update_propertyzero.action", method=RequestMethod.GET)
	public @ResponseBody Object updatePropertyzero(String carportId,int parkId){
		Map<String,String> res = new HashMap<String,String>();
		if(carportService.updatepropertyzero(carportId, parkId)){
			res.put("success", "true");
			res.put("msg", "修改成功");
		}else{
			res.put("success", "false");
			res.put("msg", "修改失败");
		}
		return "index"; 
	}
	
	//修改车位性质为临时1
	@RequestMapping(value="update_propertyone.action", method=RequestMethod.GET)
	public @ResponseBody Object updatePropertyone(String carportId,int parkId){
		Map<String,String> res = new HashMap<String,String>();
		if(carportService.updatepropertyone(carportId, parkId)){
			res.put("success", "true");
			res.put("msg", "修改成功");
		}else{
			res.put("success", "false");
			res.put("msg", "修改失败");
		}
		return "index"; 
	}
		
	// 修改车位被占情况下的车牌号
	@RequestMapping(value="update_license.action", method=RequestMethod.GET)
	public @ResponseBody Object updateLicense(String carportId,int parkId,String carLicense){
		Map<String,String> res = new HashMap<String,String>();
		if(carportService.updatelicense(carportId, parkId, carLicense)){
			res.put("success", "true");
			res.put("msg", "修改成功");
		}else{
			res.put("success", "false");
			res.put("msg", "修改失败");
		}
		return "index"; 
	}

}
