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
    <style>
    .checkleft{padding-left:30px;font-size:0.8em}
    .checkright{text-align:right;padding-right:30px;font-size:0.8em}
    </style>
    
  </head>
  
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
 
  <div class="container" style="height: 545px;">
    <div class="row">
      <div class="col-xs-6 left" style="background:#f5f5ef;height:100%;margin-top:10px;padding:10px;">
        <div style="text-align:center;font-size:1.3em">待处理</div>
        <hr style="margin-top:2px">
        <div class="row">
          <div class="col-xs-8 checkleft"><a href="#">新增用户：施琅</a></div>
          <div class="col-xs-4 checkright">09:05:33 3/8/2016</div>
        </div>
        <div class="row">
          <div class="col-xs-8 checkleft"><a href="#">结账 出入口A1 金额：1036.0</a></div>
          <div class="col-xs-4 checkright">09:05:33 3/6/2016</div>
        </div>
      </div>
      <div class="col-xs-6 right" style="height:100%;margin-top:10px;">
        <div class="top" style="background:#f5f5ef;margin-bottom:10px;height:50%;padding:10px;">
          <button type="button" style="float:left;background:#f5f5ef;border:0px;font-size:0.8em;">发起</button>
          <div style="text-align:center;font-size:1.3em">已发出</div>
          <hr style="margin-top:2px">
          <div class="row">
          <div class="col-xs-8 checkleft"><a href="#">新增用户：施琅</a></div>
          <div class="col-xs-4 checkright">09:05:33 3/8/2016</div>
        </div>
        <div class="row">
          <div class="col-xs-8 checkleft"><a href="#">结账 出入口A1 金额：1036.0</a></div>
          <div class="col-xs-4 checkright">09:05:33 3/6/2016</div>
        </div>
        </div>
        <div class="bottom" style="background:#f5f5ef;height:48%;padding:10px;">
          <button type="button" style="float:left;background:#f5f5ef;border:0px;font-size:0.8em;">发起</button>
          <div style="text-align:center;font-size:1.3em">已完成</div>
          <hr style="margin-top:2px">
          <div class="row">
          <div class="col-xs-8 checkleft"><a href="#">新增用户：施琅</a></div>
          <div class="col-xs-4 checkright">09:05:33 3/8/2016</div>
        </div>
        <div class="row">
          <div class="col-xs-8 checkleft"><a href="#">结账 出入口A1 金额：1036.0</a></div>
          <div class="col-xs-4 checkright">09:05:33 3/6/2016</div>
        </div>
        </div>
      </div>
    </div>
  </div>
  <br>
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>

  
  </body>
</html>
