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

import com.parkingmanage.model.AuthorityDomain;
import com.parkingmanage.tools.Log;

/**
 * 
 * @author zhangx
 * @date 2016年1月19日
 */

@Repository
public class AuthorityDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger=Log.getLog(AuthorityDao.class.getName());
	
	public List<AuthorityDomain> listAll(){
		List<AuthorityDomain> list = new ArrayList<AuthorityDomain>();
		String sql = "SELECT * FROM tb_authority";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> AuthorityMap =  it.next();
				AuthorityDomain auth = new AuthorityDomain();
				auth.setAuthorityId( (int)AuthorityMap.get("authority_id") );
				auth.setAuthorityRemark( (String)AuthorityMap.get("authority_remark") );
				list.add(auth);
			}
		}catch (DataAccessException e) {
			logger.error("authority查询数据库出错--->listAll");
			logger.error(e);
		}
		return list;
	}
	
	public boolean addPerm(int userType,String authorityId){
		String st[]=authorityId.split(",");
		for (int i=0;i<st.length;i++)
		{   
		  String sql ="insert into role_authority (user_type,authority_id) values (?,?)";
		  int j = Integer.parseInt(st[i]);
		  try {
				jdbcTemplate.update(sql, new Object[]{userType,j});
		  }
		  catch (DataAccessException e) 
		  {
				logger.error("role_authority查询数据库出错--->insert");
				logger.error(e);
				return false;
		  }	      
		}
		return true;
	}
	
	public String getPerm(int userType){
		String sql = "SELECT * FROM role_authority where user_type="+userType;
		System.out.println(sql);
		String arry="";
		int t;
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> AuthorityMap =  it.next();
				t=(int)AuthorityMap.get("authority_id")-1;//为何要－1?
				arry=arry+" "+Integer.toString(t);
				}
		}catch (DataAccessException e) {
			logger.error("role查询数据库出错--->listAll");
			logger.error(e);
		}
		System.out.println("dao"+arry);
		return arry;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
