package com.parkingmanage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parkingmanage.dao.LoginDao;
import com.parkingmanage.tools.Log;


/**  
* 2015年12月30日  
* author:zhangx
*/

@Repository
public class LoginDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger=Log.getLog(LoginDao.class.getName());
	
	//根据用户名取用户密码
	public String validate(String userName){
		String sql="select user_pwd from tb_park_user where user_name=?";
		String pwd=null;
		try{
			pwd=jdbcTemplate.queryForObject(sql, String.class, new Object[]{userName});
		}catch(Exception e){
			logger.error("验证用户出错");
			logger.error(e);
		}
		return pwd;
	}
	
	public String checkPass(String userName){
		String sql="select user_pwd from tb_park_user where user_name=?";
		String pwd=null;
		try{
			pwd=jdbcTemplate.queryForObject(sql, String.class, new Object[]{userName});
		}catch(Exception e){
			logger.error("验证用户出错");
			logger.error(e);
		}
		return pwd;
	}
	
	//获取权限
	public List<Integer> getPower(String userName){
		String sql="select authority_role.authority_id from tb_park_user"
				+ " left join authority_role on tb_park_user.user_type=authority_role.user_type"
				+ " where tb_park_user.user_name=?";
		List<Integer> list=null;
		try{
			list=jdbcTemplate.query(sql, new Object[]{userName},new MapRowMapper());
		}catch(Exception e){
			logger.error("获取用户权限出错");
			logger.error(e);
		}
		return list;
	}
	
	private class MapRowMapper implements RowMapper<Integer> {  
	    public Integer mapRow(ResultSet rs, int index) throws SQLException {
	    	Integer i=new Integer(rs.getInt("per_id"));
	    	return i;
	    }  
	}
}
