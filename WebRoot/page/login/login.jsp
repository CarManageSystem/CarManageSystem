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

	<link href="/CarManageSystem/css/bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="/CarManageSystem/css/login.css" rel="stylesheet" type="text/css" />
	
  </head>
  
<body>
    <div class="container" id="login">
		 <h1 style="margin-left:200px">北京邮电大学车辆管理系统</h1>
		 <form action="/CarManageSystem/login.action" method="post" id="LoginForm" class="form-horizontal" role="form">
		 	  <div class="row" id="center">
		 	  
		        <div class="form-group col-md-7" id="center_left"></div>
		        
			  	<div class="form-group col-md-5" id="center_right">
			  		<div id="right_top">
			  			系统登录
			  		</div>
			  		<div id="right_bottom">
			       		<div class="form-group" id="user">
			       	 		<label class="loginlabel">帐户</label>
			       	 		<div style="float:left;padding-left:10px;width:160px;">
			       	 			<input type="text" class="form-control" name="username" />
			       	 		</div>			    
			       		</div>
			       			       
				   		<div class="form-group" id="password">
				   	 		<label class="loginlabel">密码</label>
				   	 		<div style="float:left;padding-left:10px;width:160px;">
			       	 			<input type="password" class="form-control" name="password" />
			       	 		</div>	
			       	 		<a  id="forget" href="">忘记密码</a>				 
				   		</div>	
				   		
				   		
				   					   			   
				   		<div class="form-group" id="btn">
				   			<div class="loginbtn">
				   				<button type="submit" class="btn btn-default"  onclick="login()">登录</button>
				   			</div>
				   		</div>
				
				    </div>
				    
				</div>				   
			  </div>	 
		 </form>
		 
		 <div class="row" id="down">
		      <div id="down_left">
			      <div id="inf">
                                       
					   <span class="copyright">北京牧诚科技有限公司© V1.0.1</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>		 
	</div>

	
	<script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
	<script >
	 function login(){
	     	    $("#LoginForm").submit();       
	  }
	</script>
</body>
</html>
