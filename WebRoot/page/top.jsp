<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
    <title>top.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <style>
    .home-nav {color:#ffffff;border-radius:10px;}
    .user-nav {color:#ffffff;border-radius:10px;}
    .parking-nav {color:#ffffff;border-radius:10px;}
    .car-nav {color:#ffffff;border-radius:10px;}
    .charge-nav {color:#ffffff;border-radius:10px;}
    .finance-nav {color:#ffffff;border-radius:10px;}
    </style>
    
    
  </head>
  
  <body>
  <div class="top" style="background-image:url(/CarManageSystem/images/background.jpg);color:#ffffff">
    <div class="container-fluid">
      <div class="row">
        <div class="col-xs-4" style="padding:25px;text-align:center;font-size:1.5em;" >
          <span>北京邮电大学车辆管理系统V1.0.1</span>
        </div>
        <div class="col-xs-8">
          <div class="top" style="padding:10px;">
            <span><%=session.getAttribute("Name")%> | <%=session.getAttribute("Role")%></span>
            <div style="float:right;">
              <span><a href="#" style="color:#ffffff;text-decoration:underline">未处理(3)</a></span>
              <span><a href="#" style="color:#ffffff;text-decoration:underline;margin-left:10px">发起未结(3)</a></span>
              <button type="button" style="color:#ffffff;margin-left:10px;background:#770077;border-width:1px" onclick="logout()">退出</button>
            </div>
          </div>
          <div class="bottom" style="height:30px;margin-bottom:20px;">
            <nav class="navbar">
              <div class="navbar-collapse">
                <ul class="nav navbar-nav" style="font-size:1.3em;height:10px;">
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a  class="home-nav" href="#" onclick="house()">首页</a></li>
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a class="user-nav" href="#" onclick="user()">账户管理</a></li>
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a class="parking-nav" href="#" onclick="parking()">车场管理</a></li>
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a class="car-nav" href="#" onclick="car()">车辆管理</a></li>
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a class="car-nav" href="#" onclick="card()">办卡管理</a></li>
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a class="charge-nav" href="#" onclick="charge()">收费标准</a></li>
                    <li style="width:110px;text-align:center;background:url(/CarManageSystem/images/line.gif) no-repeat 0 15px;margin-left:-1px;"><a class="finance-nav" href="#">财务统计</a></li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
	<script >
	 function logout(){
	 	top.location = "logout.action";
	 	return false;       
	 }
	 
	 function house(){
		 	top.location = "house.action";
		 	return false;       
	 }
	 
	 function user(){
		 	top.location = "user_list.action";
		 	return false;       
	 }
	 
	 function car(){
		 	top.location = "car_records.action";
		 	return false;       
	 }
	 
	 function card() {
		 top.location = "newcard_info.action";
		 return false;
	 }
	 
	 function charge(){
		    top.location = "charge_standard.action";
		    return false;
	 }
	 
	 function parking() {
		 top.location = "display_map.action";
		 return false;
	 }
	</script>
  </body>
</html>
