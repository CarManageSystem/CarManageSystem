package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.RoleDomain;
import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2016年1月18日  
 */
@Repository
public class RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(RoleDao.class.getName());

	/**
	 * 获取角色列表
	 * @return
	 */
	public List<RoleDomain> listAll(){
		List<RoleDomain> list = new ArrayList<RoleDomain>();
		String sql = "SELECT * FROM tb_role";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> roleMap =  it.next();
				RoleDomain role = new RoleDomain();
				role.setUserType( (int) roleMap.get("user_type") );
				role.setRoleName( (String)roleMap.get("role_name") );
				list.add(role);
			}
		}catch (DataAccessException e) {
			logger.error("role查询数据库出错--->listAll");
			logger.error(e);
		}
		return list;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public boolean insert(String roleName){
		String sql = "INSERT INTO tb_role (role_name) VALUE (?)";
		
		   String sql0 = "SELECT * FROM tb_role";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql0);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> roleMap =  it.next();
				if(roleName.equals((String)roleMap.get("role_name") ))
				{
					return false;
				}
			  }
		try {
			jdbcTemplate.update(sql, new Object[]{roleName});
		} catch (DataAccessException e) {
			logger.error("role查询数据库出错--->insert");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	public boolean update(String roleName,int userType){
		String sql = "update tb_role set role_name="+"'"+roleName+"'"+" where role_id="+userType;
		System.out.println(sql);
	   	try {
			jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			logger.error("role查询数据库出错--->update");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 通过userType删除角色
	 * @param userType
	 * @return
	 */
	public boolean deleteByType(int userType){
		String sql = "DELETE FROM tb_role WHERE user_type in ("+userType+")";
		try {
			jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			logger.error("role查询数据库出错--->deleteByType");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	public boolean deletePerm(int userType){
		String sql = "DELETE FROM authority_role WHERE user_type in ("+userType+")";
		try {
			jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			logger.error("authority_role删除数据出错--->deletePerm");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 通过roleId获取角色列表
	 * @param roleId
	 * @return
	 */
	public List<RoleDomain> queryByType(int userType){
		List<RoleDomain> list = new ArrayList<RoleDomain>();
		String sql = "SELECT * FROM tb_role WHERE role_id like '%"+userType+"%'";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> roleMap =  it.next();
				RoleDomain role = new RoleDomain();
				role.setUserType( (int) roleMap.get("user_type") );
				role.setRoleName( (String)roleMap.get("role_name") );
				list.add(role);
			}
		}catch (DataAccessException e) {
			logger.error("role查询数据库出错--->queryByType");
			logger.error(e);
		}
		return list;
	}
	
	/**
	 * 通过roleName获取userType
	 * @param roleName
	 * @return
	 */
	public int getTypeByName(String roleName){
		String sql = "SELECT user_type FROM tb_role WHERE role_name=?";
		int res = 0;
		try {
			res = jdbcTemplate.queryForInt(sql,new Object[]{roleName});
		} catch (DataAccessException e) {
			logger.error("role查询数据库出错--->getTypeByName");
			logger.error(e);
		}
		return res;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
