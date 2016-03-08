<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    
  </head>
  
  <body>
  <div class="top" style="background-image:url(background.jpg);color:#ffffff">
    <div class="container-fluid">
      <div class="row">
        <div class="col-xs-4" style="padding:25px;text-align:center;font-size:1.5em;" >
          <span>北京邮电大学车辆管理系统V1.0.1</span>
        </div>
        <div class="col-xs-8">
          <div class="top" style="padding:10px;">
            <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
            <span>陈永华 | 管理员</span>
            <div style="float:right;margin-right:100px">
              <span><a href="#" style="color:#ffffff;text-decoration:underline">未处理(3)</a></span>
              <span><a href="#" style="color:#ffffff;text-decoration:underline;margin-left:10px">发起未结(3)</a></span>
              <button type="button" style="color:#ffffff;margin-left:10px;background:#770077;border-width:1px">退出</button>
            </div>
            <!--<span style="float:right;margin-right:100px">未处理(3) 发起未结(3) 退出</span>-->
          </div>
          <div class="bottom" style="height:30px;margin-bottom:20px">
            <nav class="navbar">
              <div class="navbar-collapse">
                <ul class="nav navbar-nav" style="font-size:1.3em;height:10px;">
                    <li style="width:130px;text-align:center;background:url(line.gif) no-repeat 0 15px;margin-left:-1px;"><a href="#" style="color:#ffffff;">首页</a></li>
                    <li style="width:130px;text-align:center;background:url(line.gif) no-repeat 0 15px;margin-left:-1px;"><a href="#" style="color:#ffffff">车场管理</a></li>
                    <li style="width:130px;text-align:center;background:url(line.gif) no-repeat 0 15px;margin-left:-1px;"><a href="#" style="color:#ffffff">车辆管理</a></li>
                    <li style="width:130px;text-align:center;background:url(line.gif) no-repeat 0 15px;margin-left:-1px;"><a href="#" style="color:#ffffff">收费管理</a></li>
                    <li style="width:130px;text-align:center;background:url(line.gif) no-repeat 0 15px;margin-left:-1px;"><a href="#" style="color:#ffffff">财务统计</a></li>
                    <li style="width:130px;text-align:center;background:url(line.gif) no-repeat 0 15px;margin-left:-1px;"><a href="#" style="color:#ffffff">联系我们</a></li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-xs-6 left" style="background:#ffffcc;height:100%;margin-top:10px;text-align:center;padding:10px;font-size:1.5em">
                    待处理
        <hr style="margin-top:2px;">
        <div>
          <span>新增用户：施琅</span>
          <span>09:05:33 3/6/2016</span>
        </div>
      </div>
      <div class="col-xs-6 right" style="height:100%;margin-top:10px;text-align:center;font-size:1.5em">
        <div class="top" style="background:#ffffbb;margin-bottom:10px;height:50%;padding:10px;">
                        已发出
          <hr style="margin-top:2px">
        </div>
        <div class="bottom" style="background:#ffffbb;height:48%;padding:10px;">
                         已完成
          <hr style="margin-top:2px">
        </div>
      </div>
    </div>
  </div>
 <div class="footer" style="text-align:center">
   北京牧诚科技有限公司c|联系我们
 </div>
  
  </body>
</html>
