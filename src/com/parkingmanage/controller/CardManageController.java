package com.parkingmanage.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.jdbc.core.JdbcTemplate;

import com.parkingmanage.dao.CarportStateDao;
import com.parkingmanage.model.CarDomain;
import com.parkingmanage.model.CardManageDomain;
import com.parkingmanage.model.UserDomain;
import com.parkingmanage.service.CarService;
import com.parkingmanage.service.CardManageService;
import com.parkingmanage.service.CarportService;
import com.parkingmanage.tools.Log;



@Controller
public class CardManageController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger=Log.getLog("CardManageController");
	@Autowired
	private CardManageService cardmanageService;
	@Autowired
	private CarService carService;
	
	
	//跳转到车主车辆信息页面，带数据卡号CardNum
	@RequestMapping(value="/carowner_info.action")
	public String CarOwner(String CardNum,HttpServletRequest request){
		request.setAttribute("CardNum", CardNum);
		System.out.println(CardNum);
		return "card_manage/car_owner_info";
	}
	
	//跳转到新卡信息页面，带数据卡号CardNum
		@RequestMapping(value="/newcard_info.action")
		public String NewCard(){
			return "card_manage/new_card_info";
		}
	
	//读取支付金额
	@RequestMapping(value="/cardtype")
	public @ResponseBody void CardType(HttpServletResponse response,String type)
	{
		String sql = "SELECT price FROM tb_card_standard WHERE card_type=?";
		int price = 0;
		try {
			
			price = jdbcTemplate.queryForInt(sql,new Object[]{type});
			
		} catch (DataAccessException e) {
			logger.error("cardmanage查询数据库出错--->CardType");
			logger.error(e);
		}
		JSONObject result = new JSONObject();
		result.put("price",price);
		
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
	}
	
	//读取房间已有车辆数
	@RequestMapping(value="/carhad")
	public @ResponseBody void CarHad(HttpServletResponse response,String RoomNum)
	{
		String sql = "SELECT COUNT(*) FROM tb_room_car WHERE room_num=?";
		int had = 0;
		try {
			
			had = jdbcTemplate.queryForInt(sql,new Object[]{RoomNum});
			
		} catch (DataAccessException e) {
			logger.error("cardmanage查询数据库出错--->CarHad");
			logger.error(e);
		}
		JSONObject result = new JSONObject();
		result.put("had",had);
		
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	//新卡信息插入数据库表tb_card_info
	@RequestMapping(value="newcardinfo_update.action", method=RequestMethod.POST)
	public String NewCardInfoUpdate(String CardType,String RoomNum,String RelOwner,String CarHad,String Apply,String NameOwner,
			String TelOwner,String TimeStart,String TimeEnd,String CarportType,String CarportNum,String NameApply,String TelApply,
			float PayMoney,String PayTime,String Invoice,String Title,String Post,String Address,HttpServletRequest request){
		//insert
		CardManageDomain card = new CardManageDomain();
		card.setCardType(CardType);
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
		String CardNum = dateFormat.format(today);
		CardNum = CardNum+CardType;
		CardNum = CardNum+(int)(Math.random()*900+100);//cardnum=date+cardtype+random(3)
		System.out.println(CardNum);
		card.setCardNum(CardNum);
		request.setAttribute("CardNum", CardNum);
		card.setApplyRoomNum(RoomNum);
		card.setRelOwner(RelOwner);
		card.setCarsHad(CarHad);
		card.setApply(Apply);
		card.setNameOwner(NameOwner);
		card.setTelOwner(TelOwner);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		Date date=null;
		try {
			date = sdf.parse(TimeStart);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		card.setTimeStart(date);
		TimeEnd = TimeEnd.replace("/", "-");
		try {
			date = sdf.parse(TimeEnd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		card.setTimeEnd(date);
		card.setCarportType(CarportType);
		card.setCarportNum(CarportNum);
		card.setNameApply(NameApply);
		card.setTelApply(TelApply);
		card.setPayMoney(PayMoney);
		card.setPayTime(PayTime);
		card.setInvoice(Invoice);
		card.setTitle(Title);
		card.setPost(Post);
		card.setAddress(Address);
		card.setFreight("10");
		if(cardmanageService.insert(card)){
			System.out.println("cardinfo insert succeed!");
		} else {
			System.out.println("cardinfo insert false!");
		}
		return "card_manage/car_owner_info";	
	}
	
	//新卡审批页面info_check.jsp
	@RequestMapping(value="/info_check.action")
	public ModelAndView InfoCheck(String CardNum){
		CardManageDomain card= cardmanageService.queryByCardNum(CardNum).get(0);
		CarDomain car = carService.queryByCardNum(CardNum).get(0);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("card_manage/info_check");
		mv.addObject("card", card);
		mv.addObject("car", car);
		System.out.println("新卡审批数据读取成功");
		return mv;
	}
}
