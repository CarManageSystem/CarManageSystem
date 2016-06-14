<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发出的红包</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
   
    <!-- Bootstrap -->
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">

  </head>
  
  <body style="margin:0px">
    <div class="container-fluid" >
      <div class="row" style="height:320px;text-align:center;background:#f5f5f5">
        <div id="year" align="right" style="margin-right:20px;">
          <select style="margin-top:20px;color:#BB5500;border: solid 1px #f5f5f5;appearance:none;-moz-appearance:none;-webkit-appearance:none;background: url('http://ourjs.github.io/static/2015/arrow.png') no-repeat scroll center bottom transparent; padding-bottom: 10px;">
            <option>2016年</option>
            <option>2015年</option>
            <option>2014年</option>
          </select>
        </div>
        <div id="icon">
          <img src="images/icon.png" style="width:100px;height:100px">
        </div>
        <br>
        <span>英仔共发出</span><br>
        <span style="font-size:2.5em">116.73</span>元
        <div style="margin-top:30px">
          <span style="color:#d3d3d3">发出红包总数</span>
          <span style="color:#BB5500">62</span>
          <span style="color:#d3d3d3">个</span>
        </div>
      </div>
      
      <div class="row" id="record_1" style="margin-top:15px;height:50px;border-bottom:1px solid #ebebeb">
        <div class="col-xs-6" style="font-size:1.1em;text-align:left">
          <span>普通红包</span>
        </div>
        <div class="col-xs-6" style="font-size:1.1em;text-align:right">
          <span>4.00元</span>
        </div>
        <div class="col-xs-6" style="text-align:left;color:#d3d3d3;">
          <span>06-05</span>
        </div>
        <div class="col-xs-6" style="text-align:right;color:#d3d3d3;">
          <span>1/1个</span>
        </div>
      </div>
    
      <div class="row" id="record_2" style="margin-top:15px;height:50px;border-bottom:1px solid #ebebeb">
        <div class="col-xs-6" style="font-size:1.1em;text-align:left">
          <span>普通红包</span>
        </div>
        <div class="col-xs-6" style="font-size:1.1em;text-align:right">
          <span>4.00元</span>
        </div>
        <div class="col-xs-6" style="text-align:left;color:#d3d3d3;">
          <span>06-05</span>
        </div>
        <div class="col-xs-6" style="text-align:right;color:#d3d3d3;">
          <span>1/1个</span>
        </div>
      </div>
    </div>
  </body>
</html>
