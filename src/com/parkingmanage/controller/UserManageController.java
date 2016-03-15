package com.parkingmanage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//查找
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
		System.out.println(user);
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
		return "person_manage/user_update_input";
	}
	
//	@RequestMapping(value="/user_update.action")
//	public @ResponseBody Object userUpdate(UserDomain user){
//		Map<String,String> res = new HashMap<String,String>();
//		if(userService.update(user)){
//			res.put("success", "true");
//			res.put("msg", "用户信息修改成功");
//		}else{
//			res.put("success", "false");
//			res.put("msg", "用户信息修改失败");
//		}
//		return res;	
//	}
	
	//增加用户
	


}