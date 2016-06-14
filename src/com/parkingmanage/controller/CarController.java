package com.parkingmanage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.parkingmanage.model.CarDomain;
import com.parkingmanage.service.CarService;


@Controller
public class CarController{
	
	@Autowired
	private CarService carService;
	
	//新卡信息插入数据库表tb_card_info
		@RequestMapping(value="carownerinfo_update.action", method=RequestMethod.POST)
		public String CarOwnerInfoUpdate(String CardNum,String OwnerName,int OwnerSex,String Nation,String OwnerBirthday,String OwnerTel,String OwnerAddress,
				String DrivingLicense,String DrivingLicenseType,String LicenseIssueDate,String ValidStartDate,String ValidTerm,String CarLicense,String CarType,
				String CarBrand,String EngineNumber,String CarIssueDate,String InitialDate,String CarValid,String CarCode,HttpServletRequest request){
			
			CarDomain car = new CarDomain();
			car.setCardNum(CardNum);
			request.setAttribute("CardNum", CardNum);
			car.setOwnerName(OwnerName);
			car.setOwnerSex(OwnerSex);
			car.setNation(Nation);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
			Date date = null;
			try {
				date = sdf.parse(OwnerBirthday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setOwnerBirthday(date);
			car.setOwnerTel(OwnerTel);
			car.setOwnerAddress(OwnerAddress);
			car.setDrivingLicense(DrivingLicense);
			car.setDrivingLicenseType(DrivingLicenseType);
			try {
				date = sdf.parse(LicenseIssueDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setLicenseIssueDate(date);
			try {
				date = sdf.parse(ValidStartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setValidStartDate(date);
			try {
				date = sdf.parse(CarValid);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setCarValid(date);
			car.setCarLicense(CarLicense);
			car.setCarType(CarType);
			car.setCarBrand(CarBrand);
			car.setEngineNumber(EngineNumber);
			try {
				date = sdf.parse(CarIssueDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setCarIssueDate(date);
			try {
				date = sdf.parse(InitialDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setInitialDate(date);
			try {
				date = sdf.parse(CarValid);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			car.setCarValid(date);
			car.setCarCode(CarCode);
			if(carService.carownerupdate(car)){
				System.out.println("carownerinof insert succeed!");
			} else {
				System.out.println("carownerinof insert false!");
			}
			return "card_manage/material_submit";
		}
	
}
