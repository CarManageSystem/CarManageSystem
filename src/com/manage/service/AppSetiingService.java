package com.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.AppSettingDao;

@Service
public class AppSetiingService {
	@Autowired
	
	AppSettingDao settingDao;
	
	public boolean setPassword(String phoneString,String pwdString) {
		return settingDao.setPassword(phoneString, pwdString);
	}
	
	

}
