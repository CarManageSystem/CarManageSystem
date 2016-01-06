package com.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.service.AppLoginService;
import com.manage.tools.XMessageVerify;
import com.manage.tools.XSecurityAlgorithm;


@Controller
@RequestMapping(value = "applogin")
public class AppLoginController {
	@Autowired
	
	private AppLoginService loginService;
	
	@RequestMapping(value = "/message")
	public @ResponseBody String loginWithDynamicMessage(String p,String m) throws Exception {
		System.out.println(p + ">>>>>>>>>>>>>>>>" + m);
		String ppString = XSecurityAlgorithm.md5Encode(p);
		String mmString = XSecurityAlgorithm.md5Encode(m);
		System.out.println(ppString + ">>>>>>>>>>>>>>>>" + mmString);
		
		return String.valueOf(loginService.login(p));
	}

	@RequestMapping(value = "/deleteAll")
	public @ResponseBody String deleteAll() {
		return Boolean.toString(loginService.deleteAll());
	}
	
	@RequestMapping(value = "/getmessage")
	public @ResponseBody String sendMessageWith(String p) {
		System.out.println("sending");
		int verifyCode = (int)((Math.random()*9+1)*100000);
		return String.valueOf(XMessageVerify.sendMessageWith(p,verifyCode));
	}
	
}
