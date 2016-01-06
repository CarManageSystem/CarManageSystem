/**  
* 2015年12月30日 
* author:zhangx
*/
package com.parkingmanage.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.parkingmanage.dao.LoginDao;

@Service
public class LoginService {
	@Autowired
	private LoginDao loginDao;
	
	public boolean validate(String userName,String pwd){
		if(pwd!=null&&pwd.equals(loginDao.validate(userName))){	
			System.out.println("3333");
			return true;
		}
		return false;
	}
	
	public List<String> getPower(String userName){
		List<Integer>list=loginDao.getPower(userName);
		List<String> powers=new ArrayList<String>();
		for(Integer i:list){
			switch(i){
//				case 1:powers.add("userManage");break;
//				case 2:powers.add("devManage");break;
//				case 3:powers.add("map");break;
//				case 4:powers.add("historySearch");break;
//				case 5:powers.add("depManage");break;
//				case 6:powers.add("reportForm");break;
//				case 7:powers.add("roleManage");break;
//				case 8:powers.add("loginLog");break;
//				case 9:powers.add("sysLog");break;
//				case 10:powers.add("warnLog");break;
//				case 11:powers.add("areaManage");break;
//				case 12:powers.add("spaceManage");break;
			}
		}
		return powers;
	}

	
	public boolean checkPass(String pwd){
		
		HttpSession session= getSession();
		String username=(String)session.getAttribute("userName");
	    System.out.println(username);
		if(pwd!=null&&pwd.equals(loginDao.checkPass(username))){
			return true;
		}
		return false;
	}
	
	public static HttpSession getSession() { 
		HttpSession session = null; 
		try { 
		    session = getRequest().getSession(); 
		} catch (Exception e) {} 
		    return session; 
	} 
		  
	public static HttpServletRequest getRequest() { 
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		return attrs.getRequest(); 
	} 
}
