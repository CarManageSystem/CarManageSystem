package com.parkingmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingmanage.dao.CardManageDao;
import com.parkingmanage.model.CarDomain;
import com.parkingmanage.model.CardManageDomain;



@Service
public class CardManageService {

	@Autowired
	private CardManageDao cardmanageDao;
	
	public boolean insert(CardManageDomain card){
		return cardmanageDao.insert(card);
	}
	
	public List<CardManageDomain> queryByCardNum(String cardNum){
		return cardmanageDao.queryByCardNum(cardNum);
	} 
}
