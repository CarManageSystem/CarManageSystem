<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>停车场管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="This is my login page">

	<link href="" rel="stylesheet" type="text/css" />
	
  </head>
  
<body>
    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <form action="/CarManageSystem/login.action" method="post" id="LoginForm">
		 	  <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			       <div id="user">帐 户
			         <input type="text" name="username" />
			       </div>
				   <div id="password">密   码
				     <input type="password" name="password" />
				   </div>				
				   <div id="type">类   别
				     <select name="usertype">
				     	<option value="1" selected>超级管理员</option>
				     	<option value="2">出入口管理员</option>
				     	<option value="3">财务</option>
				     </select>
				   </div>				   
				   <div id="btn"><a href="#" onclick="login()">登录</a><a href="#" onclick="reset()">清空</a></div>				   
			  </div>
			  <div id="center_right"></div>		 
		 	  </div>		 
		 </form>
		 
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">停车场管理系统 2016 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>		 
	</div>
	
	<script src="/CarManageSystem/js/jquery-1.10.2.js"></script> 
	<script >
	 function login(){
	     	    $("#LoginForm").submit();       
	  }
	 
	  function reset(){
	     	    $("#username").val("");
	     	    $("#password").val("");
	     	    $("#username").focus();          
	  }
	</script>
</body>
</html>
