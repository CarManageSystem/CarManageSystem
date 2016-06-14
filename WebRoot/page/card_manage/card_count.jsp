<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>办卡统计</title>
    
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
  body {font-family:FangSong;}
  .left{height:530px;width:20%;border:1px solid #ebebeb;border-right:thick double #D3D3D3;background:#f5f5f5;margin-top:15px;box-shadow:-3px 3px 1px #d3d3d3;}
  </style>
  </head>
  
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div style="margin-top:0px;height:15px;font-size:0.9em;">
    <ol class="breadcrumb" style="padding:0px 15px;background:#ffffff">
      <li  style="margin-left:60px;font-family:Microsoft YaHei"><a href="house.action"><b>首页</b></a></li>
      <li class="active" style="font-family:Microsoft YaHei"><a href="#"><b>办卡管理</b></a></li>
    </ol>
  </div>
  
  <div class="container" style="height:530px">
    <div class="row">
      <div class="col-xs-3 left">
        <div style="margin-top:25px;font-family:FangSong;font-size:1.1em;">
          <ul class="nav nav-pills nav-stacked" >
            <li><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">办理新卡</a></li>
            <li class="active" style="margin-top:0px"><a style="font-weight:700;border">办卡统计</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">办卡标准与流程说明</a></li>
          </ul>
        </div>
      </div>
      <div class="col-xs-7 middle">
        <h4 style="margin-left:30px;margin-top:50px"><b>办卡统计：</b></h4>
        <div style="margin-left:30px;margin-top:15px;width:450px;">
          <table class="table table-bordered" style="border:1px solid #ebebeb;background:#ebebeb;font-size:0.9em;color:#000000">
            <thead>
              <tr style="height:25px">
                <th style="text-align:center">卡类型</th>
                <th style="text-align:center">车辆数量</th>       
              </tr>
            </thead>
            <tbody>
     		  <tr>
   			    <td>临时卡</td>
   			    <td>11</td>
      		  </tr>
      		  <tr>
   			    <td>年度卡</td>
   			    <td>22</td>
      		  </tr>
      		  <tr>
   			    <td>季度卡</td>
   			    <td>33</td>
      		  </tr>
      		  <tr>
   			    <td>月度卡</td>
   			    <td>11</td>
      		  </tr>
      		  <tr>
   			    <td>临时定期卡</td>
   			    <td>22</td>
      		  </tr>
      		  <tr>
   			    <td>企业办公类-BA卡</td>
   			    <td>33</td>
      		  </tr>
      		  <tr>
   			    <td>商户经营类-SA卡</td>
   			    <td>11</td>
      		  </tr>
      		  <tr>
   			    <td>分享类场管车辆-年卡</td>
   			    <td>22</td>
      		  </tr>
      		  <tr>
   			    <td>分享类场管车辆-季卡</td>
   			    <td>33</td>
      		  </tr>
      		  <tr>
   			    <td>分享类场管车辆-月卡</td>
   			    <td>11</td>
      		  </tr>
      		</tbody>
          </table>
        </div>
      </div>
      <div class="col-xs-2 right">
      </div>
    </div>
  </div>
  
  <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  <script type="text/javascript">
  </script>
  
  </body>
</html>
