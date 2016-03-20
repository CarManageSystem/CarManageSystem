package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.UserDao;
import com.parkingmanage.model.UserDomain;

/**
 * 
 * @author zhangx
 * @date 2015年12月26日
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public List<UserDomain> listAll(){
		return userDao.listAll();
	}
	
	public List<UserDomain> query(String userId){
		return userDao.queryById(userId);
	}
	
	public List<UserDomain> querybyusername(String userName){
		return userDao.queryByUsername(userName);
	}
	
	public boolean delete(String userId){
		return userDao.deleteById(userId);
	}
	
	public boolean update(UserDomain user){
		return userDao.update(user);
	}
	
	public boolean updatephoto(String photoPath,String userId){
		return userDao.updatephoto(photoPath, userId);
	}
//	public boolean add(UserDomain user){
//		return userDao.insert(user);
//	}
//	

//	
//	public boolean deleteByIds(String userIds){
//		return userDao.deleteByIds(userIds);
//	}
//	

	
	
}
