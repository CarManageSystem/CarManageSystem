package com.parkingmanage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.parkingmanage.service.LoginService;
import com.parkingmanage.tools.LogTool;
import com.parkingmanage.tools.MyUtils;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	 
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//登录
	@RequestMapping(value="/login.action", method=RequestMethod.GET)
	public String login(HttpSession session,String username,String password) {
		System.out.println(password);
		password = MyUtils.codeString(password);
		System.out.println(password);
		if(
			loginService.validate(username, password)){
				System.out.println("22222");
				LogTool.addLoginlog(sdf.format(new Date(System.currentTimeMillis())), username, 1, "登录");
				session.setAttribute("userName", username);
				//session.setAttribute("power", loginService.getPower(username));
				System.out.println("1111111");
				return "index"; 
			}
			else
				return "loginError";
	}
	
	//登出
	@RequestMapping(value="/logout.action")
	public String logout(HttpSession session) {
		String userName=(String) session.getAttribute("userName");
		LogTool.addLoginlog(sdf.format(new Date(System.currentTimeMillis())), userName, 1, "登出");
		session.removeAttribute("userName");
		return "/logout";
	}
	
	@RequestMapping(value="/house.action")
	public String house() {
		return "/welcome";
	}

}
