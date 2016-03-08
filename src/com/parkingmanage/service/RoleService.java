package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.RoleDao;
import com.parkingmanage.model.RoleDomain;

/**
 * 
 * @author zhangx
 * @date 2016年1月19日
 */
@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public List<RoleDomain> listAll(){
		return roleDao.listAll();
	}
	
	public boolean add(String roleName){
		return roleDao.insert(roleName);
	}
	
	public boolean update(String roleName,int userType){
		return roleDao.update(roleName,userType);
	}
	
	public boolean delete(int userType){
		if(roleDao.deletePerm(userType))
		{
		return roleDao.deleteByType(userType);
		}
		else
		{
			return false;
		}
	}
	
	public boolean deletePerm(int userType){
		return (roleDao.deletePerm(userType));
	}
	
	public List<RoleDomain> queryByType(int userType){
		return roleDao.queryByType(userType);
	}
	
	public int getTypeByName(String roleName){
		return roleDao.getTypeByName(roleName);
	}
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
