package com.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.service.AppSetiingService;
import com.manage.tools.XSecurityAlgorithm;

@Controller
@RequestMapping(value = "setting")
public class AppSettingController {
	@Autowired
	
	AppSetiingService setiingService;
	
	@RequestMapping(value = "/setpwd")
	public @ResponseBody String setPassword(String p,String pwd) {
		String md5PWD = XSecurityAlgorithm.md5EncodeCE(pwd);
		return String.valueOf(setiingService.setPassword(p, md5PWD));
	}

}
