package com.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.AppLoginDao;

@Service
public class AppLoginService {
	@Autowired
	
	private AppLoginDao loginDao;
	
	public boolean loginWithMesage(String phoneString) {
		return loginDao.loginWithMesage(phoneString);
	}
	
	public String loginWithPwd(String phoneString,String pwdString) {
		return loginDao.loginWithPassword(phoneString, pwdString);
	}

	public boolean getToken(String phoneString,String tokenString) {
		return loginDao.getDeviceToken(phoneString, tokenString);
	}
	
	public boolean deleteAll() {
		return loginDao.deleteAll();
	}
}
