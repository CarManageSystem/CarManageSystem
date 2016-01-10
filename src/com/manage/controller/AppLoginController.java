package com.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.service.AppLoginService;
import com.manage.tools.XDateTime;
import com.manage.tools.XSecurityAlgorithm;


@Controller
@RequestMapping(value = "applogin")
public class AppLoginController {
	@Autowired
	
	private AppLoginService loginService;
	
	@RequestMapping(value = "/getmessage")
	public @ResponseBody String loginWithDynamicMessage(HttpServletRequest request,HttpServletResponse response,String p,String m) throws Exception {
		String code = String.valueOf(request.getSession().getAttribute("code"));
		String phone = (String)request.getSession().getAttribute("phone");
		
		java.util.Date date = (java.util.Date)request.getSession().getAttribute("date");
		
		System.out.println(code + ">>>a" + phone + "a>>>>>send time" + date + "current time" + XDateTime.stringValueWithCurrent());
		System.out.println(m + ">>>>>>>>>>>>>>>>" + p);
		
		response.setCharacterEncoding("utf-8");
		
		
		if (code.equals(m) && phone.equals(p)) {
			return String.valueOf(loginService.loginWithMesage(p));
		} else {
			//response.getWriter().write("手机号、动态密码不匹配");
			System.out.println("手机号、动态密码不匹配");
			return "0";
		}
	}
	
	@RequestMapping(value = "/setmessage")
	public @ResponseBody String sendMessageWith(HttpServletRequest request,String p) {
		int verifyCode = (int)((Math.random()*9+1)*100000);
		request.getSession().setAttribute("code", verifyCode);
		request.getSession().setAttribute("phone", p);
		request.getSession().setAttribute("date", new java.util.Date());
		
		return "nihao";//String.valueOf(XMessageVerify.sendMessageWith(p,verifyCode));
	}
	
	@RequestMapping(value = "/password")
	public @ResponseBody String loginWithPassword(String p,String pwd) {
		String md5PwdString = XSecurityAlgorithm.md5EncodeCE(pwd);
		System.out.println("md5PwdString >>:" + md5PwdString);
		
		return loginService.loginWithPwd(p, md5PwdString);
	}

	@RequestMapping(value = "/deleteAll")
	public @ResponseBody String deleteAll() {
		return Boolean.toString(loginService.deleteAll());
	}
	
}
