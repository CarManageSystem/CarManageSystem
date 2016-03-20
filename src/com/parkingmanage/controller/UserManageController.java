package com.parkingmanage.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingmanage.model.UserDomain;
import com.parkingmanage.tools.MyUtils;
import com.parkingmanage.service.UserService;

/**
 * 
 * @author zhangx
 * @date 2015年12月26日
 */

@Controller
public class UserManageController {
	@Autowired
	private UserService  userService;
	
	//列表
//	@RequestMapping(value="/user_list.action")
//	public String userList(){
//		return "person_manage/user_list";
//	}
//	
//	@RequestMapping(value="/user_getUsers.action")
//	public @ResponseBody Object getUsers(){
//		return userService.listAll();
//	}
	
	@RequestMapping(value="/user_list.action")
	public ModelAndView userList(){
		List<UserDomain> users= userService.listAll();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("person_manage/user_list");
		mv.addObject("users", users);
		return mv;
	}
	
	//查询详情
	@RequestMapping(value="/user_detail.action")
	public ModelAndView userDetail(String userId){
		List<UserDomain> user= userService.query(userId);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("person_manage/user_info");
		mv.addObject("user", user);
		return mv;
	}
	
	//删除
	@RequestMapping(value="/user_delete.action")
	public ModelAndView userDelete(String userId){
		userService.delete(userId);
		List<UserDomain> users= userService.listAll();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("person_manage/user_list");
		mv.addObject("users", users);
		return mv;
	}
	
	//更新
	//更新带数据跳转
	@RequestMapping(value="user_updateInput.action")
	public String userUpdateInput(String userId,HttpServletRequest request){
		System.out.println("update userId--->"+userId);
		UserDomain user = userService.query(userId).get(0);
		request.setAttribute("userTel", user.getUserTel());
		request.setAttribute("name", user.getName());
		request.setAttribute("userSex",user.getUserSex() );
		request.setAttribute("bornDate", user.getBornDate());
		request.setAttribute("userAddress", user.getUserAddress());
		request.setAttribute("idNumber", user.getIdNumber());
		request.setAttribute("nation", user.getNation());
		request.setAttribute("nativePlace", user.getNativePlace());
		request.setAttribute("marriage", user.getMarriage());
		request.setAttribute("education", user.getEducation());
		request.setAttribute("emergContact", user.getEmergContact());
		request.setAttribute("emergTel", user.getEmergTel());
		request.setAttribute("userId", user.getUserId());
		request.setAttribute("photoPath", user.getPhotoPath());
		return "person_manage/user_update_input";
	}
	
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
	 
	@RequestMapping(value="user_update.action", method=RequestMethod.POST)
	public ModelAndView userUpdate(UserDomain user){
		userService.update(user);
		List<UserDomain> users= userService.listAll();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("person_manage/user_list");
		mv.addObject("users", users);
		return mv;
	}
	
	//查找：按userName工号
		@RequestMapping(value="/user_search.action", method=RequestMethod.POST)
		public ModelAndView userSearch(String userName){
			List<UserDomain> users= userService.querybyusername(userName);
			ModelAndView mv=new ModelAndView();
			mv.setViewName("person_manage/user_list");
			mv.addObject("users", users);
			return mv;
		}
	
	//增加用户
	


}