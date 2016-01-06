package com.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.AppLoginDao;

@Service
public class AppLoginService {
	@Autowired
	
	private AppLoginDao loginDao;
	
	public boolean login(String phoneString) {
		return loginDao.login(phoneString);
	}

	public boolean deleteAll() {
		return loginDao.deleteAll();
	}
}
