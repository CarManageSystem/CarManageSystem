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
	<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    
    
  </head>
  
  <body>
  
  <iframe src='page/top.jsp' width=100% height=13% scrolling=no></iframe>
 
  <div class="container" style="height: 545px;">
    <div class="row">
      <div class="col-xs-6 left" style="background:#ffffbb;height:100%;margin-top:10px;text-align:center;padding:10px;font-size:1.5em">
                    待处理
        <hr style="margin-top:2px">
        <div>
          <div style="float:left;margin-left:10px"><a href="#">新增用户：施琅</a></div>
          <div style="float:right">09:05:33 3/6/2016</div>
        </div>
      </div>
      <div class="col-xs-6 right" style="height:100%;margin-top:10px;text-align:center;font-size:1.5em">
        <div class="top" style="background:#ffffbb;margin-bottom:10px;height:50%;padding:10px;">
          <button type="button" style="float:left;background:#ffffbb;border:0px;font-size:0.8em;margin-top:6px">发起</button><span>已发出</span>
          <hr style="margin-top:2px">
        </div>
        <div class="bottom" style="background:#ffffbb;height:48%;padding:10px;">
                        <button type="button" style="float:left;background:#ffffbb;border:0px;font-size:0.8em;margin-top:6px">发起</button><span>已完成</span>
          <hr style="margin-top:2px">
        </div>
      </div>
    </div>
  </div>
  <br>
  <iframe src='page/down.jsp' width=100% height=3% scrolling=no frameborder=0></iframe>

  
  </body>
</html>
