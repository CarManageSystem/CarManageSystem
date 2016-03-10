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
		
//	@RequestMapping(value="getToken")
//	public @ResponseBody   String  getToken(){
//		System.out.println(userService.listAll().get(0).toString());
//		System.out.println("zzzzzzzzzzzzzzzzz");
//		return userService.listAll().get(0).toString();
//	}
	
	@RequestMapping(value="/user_list.action")
	public @ResponseBody String userList(){
		System.out.println(userService.listAll());
		return userService.listAll().toString();
	}
//	
//	@RequestMapping(value="/user_getUsers.action")
//	public @ResponseBody Object getUsers(){
//		return userService.listAll();
//	}
//	
//	@RequestMapping(value="/user_addInput.action")
//	public String userAddInput(){
//		return "person_manage/user/user_add_input";
//	}
//	
//	@RequestMapping(value="/user_add.action")
//	public @ResponseBody Object userAdd(UserDomain user){
//		//System.out.println(user);
//		int depId = depService.getIdByName(user.getDepName());
//		int roleId = roleService.getIdByName(user.getRoleName());
//		
//		user.setDepId(depId);
//		user.setRoleId(roleId);
//		
//		//pwd 加密
//		String newPwd = MyUtils.codeString( user.getUserPwd() );
//		user.setUserPwd( newPwd );
//		System.out.println(user);
//		
//		Map<String,String> res = new HashMap<String,String>();
//		if(userService.add(user)){
//			res.put("success", "true");
//			res.put("msg", "用户添加成功");
//		}else{
//			res.put("success", "false");
//			res.put("msg", "用户添加失败");
//		}
//		return res;
//	}
//	
//	@RequestMapping(value="/user_delete.action")
//	public String userDelete(@RequestParam String userId){
//		System.out.println("delete userId--->"+userId);
//		userService.delete(userId);
//		return "person_manage/user/user_list";
//	}
//	
//	@RequestMapping(value="user_deleteById.action")
//	public @ResponseBody Object userDeleteById(String userId){
//		System.out.println("delete depId--->"+userId);
//		Map<String,String> res = new HashMap<String,String>();
//		if(userService.delete(userId)){
//			res.put("success", "true");
//			res.put("msg", "用户删除成功");
//		}else{
//			res.put("success", "false");
//			res.put("msg", "用户删除失败");
//		}
//		return res;
//	}
//	
//	@RequestMapping(value="user_deleteByIds.action")
//	public String userDeleteByIds(String userIds){
//		System.out.println("delete userIds--->"+userIds);
//		
//		userService.deleteByIds(userIds);
//			
//		return "person_manage/user/user_list";
//	}
//	
//	@RequestMapping(value="/user_update.action")
//	public @ResponseBody Object userUpdate(UserDomain user){
//		//System.out.println(user);
//		int depId = depService.getIdByName(user.getDepName());
//		int roleId = roleService.getIdByName(user.getRoleName());
//		
//		user.setDepId(depId);
//		user.setRoleId(roleId);
//		
//		//pwd 加密
//		String newPwd = MyUtils.codeString( user.getUserPwd() );
//		user.setUserPwd( newPwd );
//		System.out.println(user);
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
//	
//	@RequestMapping(value="/user_search.action")
//	public @ResponseBody Object userSearch(String userId){
//		System.out.println("search user--->"+userId);
//		return userService.query(userId);
//	}
//	
//	@RequestMapping(value="/user_getUserById.action")
//	public void  getUserById(HttpServletResponse response,String userId){
//		response.setContentType("text/html; charset=utf-8");
//		List<UserDomain> list = userService.query(userId);
//		try {
//			response.getWriter().write( MyUtils.list2Json(list).toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@RequestMapping(value="user_updateInput.action")
//	public String userUpdateInput(String userId,HttpServletRequest request){
//		System.out.println("update userId--->"+userId);
//		UserDomain user = userService.query(userId).get(0);
//		System.out.println(user);
//		request.setAttribute("userId", user.getUserId());
//		request.setAttribute("userNum", user.getUserNum());
//		request.setAttribute("userPwd", MyUtils.decodeString(user.getUserPwd()) );
//		request.setAttribute("userName", user.getUserName());
//		request.setAttribute("userTel", user.getUserTel());
//		request.setAttribute("depName", user.getDepName());
//		request.setAttribute("roleName", user.getRoleName());
//		return "person_manage/user/user_update_input";
//	}

}