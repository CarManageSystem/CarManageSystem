<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>下拉菜单</title>
    
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
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="btn-group" role="group" aria-label="...">
      <div class="btn-group" role="group">
        <button type="button" class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   账户管理
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" >
          <li><a href="#">Dropdown link</a></li>
          <li><a href="#">Dropdown link</a></li>
        </ul>
      </div>
    </div>
    <div class="btn-group" role="group">
      <button type="button" class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              出入场 
        <span class="caret"></span>
      </button>
      <ul class="dropdown-menu">
        <li><a href="#">Dropdown link</a></li>
        <li><a href="#">Dropdown link</a></li>
      </ul>
    </div>
    <div class="btn-group" role="group">
      <button type="button" class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              车辆管理
        <span class="caret"></span>
      </button>
      <ul class="dropdown-menu">
        <li><a href="#">Dropdown link</a></li>
        <li><a href="#">Dropdown link</a></li>
      </ul>
    </div>
    <form role="form">
      <div class="form-group">
        <label>用户名</label>
        <input type="date" class="form-control" placeholder="user">
      </div>
    </form>
  </body>
</html>
    