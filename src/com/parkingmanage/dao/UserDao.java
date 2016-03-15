package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Date;
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
		String sql1="SELECT * FROM tb_park_user WHERE add_flag='1'";
		String sql2="SELECT role_name FROM tb_role WHERE user_type=?";
		System.out.println(sql1);	
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql1);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> userMap =  it.next();
				UserDomain user = new UserDomain();
				String rolename = jdbcTemplate.queryForObject(sql2,new Object[]{(Integer)userMap.get("user_type")},String.class);
				user.setUserId( (String)userMap.get("user_id") );
				user.setUserPwd( (String)userMap.get("user_pwd"));
				user.setUserName( (String)userMap.get("user_name") );
				user.setUserTel( (String)userMap.get("user_tel") );
				user.setUserType( (Integer)userMap.get("user_type") ); 
				user.setName( (String)userMap.get("name") );
				user.setUserSex( (Integer)userMap.get("user_sex") );
				user.setBornDate( (Date)userMap.get("born_date") );
				user.setUserAddress( (String)userMap.get("user_address") );
				user.setIdNumber( (String)userMap.get("id_number") );
				user.setNation( (String)userMap.get("nation") );
				user.setNativePlace( (String)userMap.get("native_place") );
				user.setMarriage( (String)userMap.get("marriage") );
				user.setEducation( (String)userMap.get("education") );
				user.setEmergContact( (String)userMap.get("emerg_contact") );
				user.setEmergTel( (String)userMap.get("emerg_tel") );
				user.setAddFlag( (Integer)userMap.get("add_flag") );
				user.setOnlineFlag( (Integer)userMap.get("online_flag") );
				user.setRoleName(rolename);
				list.add(user);
			}
		} catch(DataAccessException e){
			System.out.println("web用户信息查询数据库出错--->listall");
		}
		return list;
	}

	/**
	 * 通过userId查找用户
	 * @param userId
	 * @return
	 */
	public List<UserDomain> queryById(String userId){
		List<UserDomain> list = new ArrayList<UserDomain>();
		String sql1="SELECT * FROM tb_park_user WHERE user_id like ? AND add_flag='1'";
		String sql2="SELECT role_name FROM tb_role WHERE user_type=?";
		System.out.println(sql1);
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql1,new Object[]{userId});
//			System.out.println(rows);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> userMap =  it.next();
				UserDomain user = new UserDomain();
				String rolename = jdbcTemplate.queryForObject(sql2,new Object[]{(Integer)userMap.get("user_type")},String.class);
				user.setUserId( (String)userMap.get("user_id") );
				user.setUserPwd( (String)userMap.get("user_pwd"));
				user.setUserName( (String)userMap.get("user_name") );
				user.setUserTel( (String)userMap.get("user_tel") );
				user.setUserType( (Integer)userMap.get("user_type") ); 
				user.setName( (String)userMap.get("name") );
				user.setUserSex( (Integer)userMap.get("user_sex") );
				user.setBornDate( (Date)userMap.get("born_date") );
				user.setUserAddress( (String)userMap.get("user_address") );
				user.setIdNumber( (String)userMap.get("id_number") );
				user.setNation( (String)userMap.get("nation") );
				user.setNativePlace( (String)userMap.get("native_place") );
				user.setMarriage( (String)userMap.get("marriage") );
				user.setEducation( (String)userMap.get("education") );
				user.setEmergContact( (String)userMap.get("emerg_contact") );
				user.setEmergTel( (String)userMap.get("emerg_tel") );
				user.setAddFlag( (Integer)userMap.get("add_flag") );
				user.setOnlineFlag( (Integer)userMap.get("online_flag") );
				user.setRoleName(rolename);
				list.add(user);
			}
		} catch(DataAccessException e){
			System.out.println("web用户信息查询数据库出错--->queryById");
		}
		return list;
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
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean update(UserDomain user){
		String sql = "UPDATE tb_park_user SET user_tel=?, name=?, user_sex=?, born_date=?, user_address=?, id_number=?, nation=?, native_place=?, marriage=?, education=?, emerg_contact=?, emerg_tel=? WHERE user_id=?";
		try {
			jdbcTemplate.update(sql, new Object[]{ 
					user.getUserTel(),
					user.getName(),
					user.getUserSex(),
					user.getBornDate(),					
					user.getUserAddress(),
					user.getIdNumber(),
					user.getNation(),
					user.getNativePlace(),
					user.getMarriage(),
					user.getEducation(),
					user.getEmergContact(),
					user.getEmergTel(),
					user.getUserId()
					});
		} catch (DataAccessException e) {
			System.out.println("web用户信息查询数据库出错--->update");
			return false;
		}
		return true;
	}
	
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
//	public boolean insert(UserDomain user){
//		String sql = "INSERT INTO tb_park_user (user_id,user_pwd,user_name,user_tel,user_type,name,user_age,user_sex,user_address) VALUES (?,?,?,?,?,?,?,?,?)";
//		try {
//			jdbcTemplate.update(sql, new Object[]{user.getUserId(),
//					user.getUserId(),
//					user.getUserPwd(),
//					user.getUserName(),
//					user.getUserTel(),
//					user.getUserType(),
//					user.getName(),
//					user.getUserAge(),
//					user.getUserSex(),
//					user.getUserAddress()
//					});
//		} catch (DataAccessException e) {
//			System.out.println("web用户信息查询数据库出错--->insert");
//			return false;
//		}
//		return true;
//	}
//	

//	
//	public boolean deleteByIds(String userIds){
//		String[] ids = userIds.split(",");
//		int len = ids.length;
//		for(int i=0; i<len; i++){
//			if(!this.deleteById(ids[i])){
//				return false;
//			}
//		}
//		return true;
//	}
//	

	

	
		
}
