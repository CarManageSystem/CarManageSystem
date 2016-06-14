package com.parkingmanage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parkingmanage.model.CardManageDomain;
import com.parkingmanage.tools.Log;

@Repository
public class CardManageDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger=Log.getLog(CardManageDao.class.getName());
	
	/*添加新卡，插入tb_card_info
	 * 
	 */
	public boolean insert(CardManageDomain card){
		String sql = "INSERT INTO tb_card_info (card_type,card_num,apply_room_num,rel_owner,cars_had,apply,name_owner,tel_owner,time_start,time_end,carport_type,carport_num,pay_money,pay_time,name_apply,tel_apply,invoice,title,address,post,freight)"
					 +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[]{card.getCardType(),card.getCardNum(),card.getApplyRoomNum(),card.getRelOwner(),card.getCarsHad(),card.getApply(),card.getNameOwner(),card.getTelOwner(),card.getTimeStart(),card.getTimeEnd(),card.getCarportType(),card.getCarportNum(),card.getPayMoney(),card.getPayTime(),card.getNameApply(),card.getTelApply(),card.getInvoice(),card.getTitle(),card.getAddress(),card.getPost(),card.getFreight()});
		} catch (DataAccessException e) {
			logger.error("card插入数据库出错--->insert");
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 通过cardNum查询
	 * @param cardNum
	 * @return
	 */
	public List<CardManageDomain> queryByCardNum(String cardNum){
		List<CardManageDomain> list = new ArrayList<CardManageDomain>();
		String sql = "SELECT * FROM tb_card_info WHERE card_num=?";
		System.out.println(sql);
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{cardNum});
			Iterator<Map<String, Object>> it = rows.iterator();
			while(it.hasNext()){
				Map<String, Object> cardMap =  it.next();
				CardManageDomain card = new CardManageDomain();
				card.setCardType((String)cardMap.get("card_type"));
				card.setCardNum((String)cardMap.get("card_num"));
				card.setApplyRoomNum((String)cardMap.get("apply_room_num"));
				card.setRelOwner((String)cardMap.get("rel_owner"));
				card.setCarsHad((String)cardMap.get("cars_had"));
				card.setApply((String)cardMap.get("apply"));
				card.setNameOwner((String)cardMap.get("name_owner"));
				card.setTelOwner((String)cardMap.get("tel_owner"));
				card.setTimeStart((Date)cardMap.get("time_start"));
				card.setTimeEnd((Date)cardMap.get("time_end"));
				card.setCarportType((String)cardMap.get("carport_type"));
				card.setCarportNum((String)cardMap.get("carport_num"));
				card.setPayMoney((float)cardMap.get("pay_money"));
				card.setPayTime((String)cardMap.get("pay_time"));
				card.setNameApply((String)cardMap.get("name_apply"));
				card.setTelApply((String)cardMap.get("tel_apply"));
				card.setInvoice((String)cardMap.get("invoice"));
				card.setTitle((String)cardMap.get("title"));
				card.setAddress((String)cardMap.get("address"));
				card.setPost((String)cardMap.get("post"));
				card.setFreight((String)cardMap.get("freight"));
				list.add(card);
			}
		}catch (DataAccessException e) {
			logger.error("card查询数据库出错--->queryByCardNum");
			logger.error(e);
		}
		return list;
	}
	
	

}
