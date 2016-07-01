package com.parkingmanage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingmanage.model.CarportDomain;
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
	public boolean updateStatezero(String carportId,int parkId){
		if(carportService.updatestatezero(carportId, parkId)){
			if(carportService.licensenull(carportId, parkId)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//修改车位状态为占用1
	@RequestMapping(value="update_stateone.action", method=RequestMethod.GET)
	public boolean updateStateone(String carportId,int parkId){
		if(carportService.updatestateone(carportId, parkId)){
			return true;
		}else{
			return false;
		}
	}
	
	//修改车位性质为固定0
	@RequestMapping(value="update_propertyzero.action", method=RequestMethod.GET)
	public boolean updatePropertyzero(String carportId,int parkId){
		if(carportService.updatepropertyzero(carportId, parkId)){
			return true;
		}else{
			return false;
		} 
	}
	
	//修改车位性质为临时1
	@RequestMapping(value="update_propertyone.action", method=RequestMethod.GET)
	public boolean updatePropertyone(String carportId,int parkId){
		if(carportService.updatepropertyone(carportId, parkId)){
			return true;
		}else{
			return false;
		} 
	}
		
	// 修改车位被占情况下的车牌号
	@RequestMapping(value="update_license.action", method=RequestMethod.GET)
	public boolean updateLicense(String carportId,int parkId,String carLicense){
		if(carportService.updatelicense(carportId, parkId, carLicense)){
			return true;
		}else{
			return false;
		}
	}
	
	
	

}
