package com.manage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.manage.tools.XLogger;

@Repository
public class CarTypeDao {
	@Autowired
	
	JdbcTemplate jdbcTemplate;

	static Logger logger = XLogger.getLog();

	public List<String> fetchAllBrand() {
		String sql1 = "select distinct make_name from tb_vehicle_brand"; //MAKE_NAME
		return jdbcTemplate.queryForList(sql1, String.class);
	}
	
	public Object fetchFirstBrand() {
		String sql = "select id,first_letter,make_name,logo from tb_vehicle_brand where ID in"
				+ "(select min(ID) from tb_vehicle_brand group by make_name) order by first_letter";
		try {
			List<Map<String, Object>> kvMap = jdbcTemplate.queryForList(sql);
			System.out.println(kvMap);
			
			//封装数据
			List<Object> brandsList = new ArrayList<Object>();
			Map<String, Object> brandMap = new HashMap<String, Object>();
			List<Object> bList = new ArrayList<Object>();
			String letterString = "A";

			Iterator<Map<String, Object>> it = kvMap.iterator();
			while(it.hasNext()){
				
				Map<String, Object> map =  new HashMap<String,Object>();
				map = it.next();
				if (letterString.equals(map.get("first_letter"))) {
					map.remove("first_letter");
					bList.add(map);
				} else {
					brandMap.put(letterString, bList);
					brandsList.add(brandMap);
					brandMap = new HashMap<String, Object>();
					bList = new ArrayList<Object>();
					
					letterString = map.get("first_letter").toString();
					map.remove("first_letter");
					bList.add(map);
				}
			}
			brandMap.put(letterString, bList);
			brandsList.add(brandMap);
			logger.info("infonihao");
			System.out.println("printlnnihao");
			List<Object> dataList = new ArrayList<Object>();
			dataList.add(fetchAllLetter());
			dataList.add(brandsList);
			logger.info(dataList);
			return JSONArray.fromObject(dataList);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("dep查询数据库出错--->fetchUnduplicate");
			logger.error(e);
		}
		return null;
	}
	
	public Object fetchSecondBrand(int id) {
		try {
			String sqlString = "select make_name from tb_vehicle_brand where id = "+id+"";
			String brandString = jdbcTemplate.queryForObject(sqlString, String.class);
			logger.info(brandString);
			String sql = "select id,model_name from tb_vehicle_brand where id in "
+ "(select min(id) from tb_vehicle_brand where make_name='"+brandString+"' group by model_name)";
			List<Map<String, Object>> kvMap = jdbcTemplate.queryForList(sql);
			System.out.println(kvMap);
			return JSONArray.fromObject(kvMap);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("dep查询数据库出错--->fetchUnduplicate");
			logger.error(e);
		}
		return null;
	}
	
	public Object fetchThirdBrand(int id) {
		try {
			String sqlString = "select model_name from tb_vehicle_brand where id = "+id+"";
			String model = jdbcTemplate.queryForObject(sqlString, String.class);
			logger.info(model);
			String sql = "select id,type_series,type_name from tb_vehicle_brand where id in "
+ "(select id from tb_vehicle_brand where model_name='"+model+"')";
			List<Map<String, Object>> kvMap = jdbcTemplate.queryForList(sql);
			System.out.println(kvMap);
			return JSONArray.fromObject(kvMap);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("dep查询数据库出错--->fetchUnduplicate");
			logger.error(e);
		}
		return null;
	}
	
	private List<String> fetchAllLetter() throws Exception {
		logger.info("fetchAllLetter");
		String sql = "select distinct first_letter from tb_vehicle_brand";
		return jdbcTemplate.queryForList(sql,String.class);
	}
}
