package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.AuthorityDao;
import com.parkingmanage.model.AuthorityDomain;

@Service
public class AuthorityService {
	@Autowired
	private AuthorityDao authorityDao;
	
	public List<AuthorityDomain> listAll(){
		return authorityDao.listAll();
	}
	
	public boolean addPerm(int userType,String authorityId){
		return authorityDao.addPerm(userType, authorityId);
	}
	
	public String getPerm(int userType){
		return authorityDao.getPerm(userType);
	}
}