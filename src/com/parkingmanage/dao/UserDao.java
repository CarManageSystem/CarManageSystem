package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.UserDomain;


/**
 * @author zhangx
 * @date 2015年12月25日
 */

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public List<UserDomain> listAll(){
		List<UserDomain> list = new ArrayList<UserDomain>();
		String sql="SELECT * FROM tb_park_user WHERE add_flag='1'";
		System.out.println(sql);	
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> userMap =  it.next();
				UserDomain user = new UserDomain();
				user.setUserId( (String)userMap.get("user_id") );
				user.setUserPwd( (String)userMap.get("user_pwd"));
				user.setUserName( (String)userMap.get("user_name") );
				user.setUserTel( (String)userMap.get("user_tel") );
				user.setUserType( (Integer)userMap.get("user_type") ); 
				user.setName( (String)userMap.get("name") );
				user.setUserAge( (Integer)userMap.get("user_age") );
				user.setUserSex( (Integer)userMap.get("user_sex") );
				user.setUserAddress( (String)userMap.get("user_address") );
				list.add(user);
			}
		} catch(DataAccessException e){
			System.out.println("web用户信息查询数据库出错--->listAll");
		}
		return list;
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean insert(UserDomain user){
		String sql = "INSERT INTO tb_park_user (user_id,user_pwd,user_name,user_tel,user_type,name,user_age,user_sex,user_address) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{user.getUserId(),
					user.getUserId(),
					user.getUserPwd(),
					user.getUserName(),
					user.getUserTel(),
					user.getUserType(),
					user.getName(),
					user.getUserAge(),
					user.getUserSex(),
					user.getUserAddress()
					});
		} catch (DataAccessException e) {
			System.out.println("web用户信息查询数据库出错--->insert");
			return false;
		}
		return true;
	}
	
	/**
	 * 通过userId删除用户
	 * @param userId
	 * @return
	 */
	public boolean deleteById(String userId){
		String sql = "DELETE FROM tb_park_user WHERE user_id=?";
		try {
			jdbcTemplate.update(sql, new Object[]{userId});
		} catch (DataAccessException e) {
			System.out.println("web用户信息查询数据库出错--->deleteById");
			return false;
		}
		return true;
	}
	
	public boolean deleteByIds(String userIds){
		String[] ids = userIds.split(",");
		int len = ids.length;
		for(int i=0; i<len; i++){
			if(!this.deleteById(ids[i])){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean update(UserDomain user){
		String sql = "UPDATE tb_park_user SET user_pwd=?, user_name=?, user_tel=?, user_type=?, name=?, user_age=?, user_sex=?, user_address=? WHERE user_id=?";
		try {
			jdbcTemplate.update(sql, new Object[]{ 
					user.getUserPwd(),
					user.getUserName(),
					user.getUserTel(),
					user.getUserType(),
					user.getName(),
					user.getUserAge(),
					user.getUserSex(),
					user.getUserAddress(),
					user.getUserId()
					});
		} catch (DataAccessException e) {
			System.out.println("web用户信息查询数据库出错--->update");
			return false;
		}
		return true;
	}
	
	/**
	 * 通过userId查找用户
	 * @param userId
	 * @return
	 */
	public List<UserDomain> queryById(String userId){
		List<UserDomain> list = new ArrayList<UserDomain>();
		String sql = "SELECT * FROM tb_park_user WHERE user_id like '%"+userId+"%'";
		System.out.println(sql);	
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> userMap =  it.next();
				UserDomain user = new UserDomain();
				user.setUserPwd((String)userMap.get("user_pwd"));
				user.setUserName( (String)userMap.get("user_name") );
				user.setUserTel( (String)userMap.get("user_tel") );
				user.setUserType( (Integer)userMap.get("user_type") ); 
				user.setName( (String)userMap.get("name") );
				user.setUserAge( (Integer)userMap.get("user_age") );
				user.setUserSex( (Integer)userMap.get("user_sex") );
				user.setUserAddress( (String)userMap.get("user_address") );					
				list.add(user);
			}
		} catch(DataAccessException e){
			System.out.println("web用户信息查询数据库出错--->queryById");
		}
		return list;
	}

	
		
}
