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
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div class="container" style="margin-top:20px;width:1140px;border:1px solid #ebebeb;padding:10px;border-radius:5px;background:#ebebeb">
    <div class="row">
      <div class="col-xs-6">
        <span style="font-size:1.3em;margin-left:10px"><b>人员列表</b></span>
      </div>
      <div class="col-xs-6">
        <input type="button" value="查询" style="float:right;margin-right:10px">
        <input type="text" style="float:right;border-radius:3px;margin-right:5px">
        
      </div>
    </div>
  </div>
  <div class="container" style="height:500px;margin-top:20px;overflow:auto" id="user_list">
    <table class="table table-striped" style="border:1px solid #ebebeb;background:#ebebeb">
      <tr style="height:25px">
        <th>在线状态</th>
        <th>工号</th>
        <th>姓名</th>
        <th>岗位</th>
        <th>年龄</th>
        <th>性别</th>
        <th>地址</th>
        <th>手机</th>
        <th>操作</th>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A001</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px;">
        <img src="images/car1.jpg" style="width:25px;height:25px;">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A002</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>
      <tr>
        <td><img src="images/car1.jpg" style="width:25px;height:25px"></td>
        <td>A003</td>
        <td>陈永华</td>
        <td>经理</td>
        <td>40</td>
        <td>男</td>
        <td>海淀区</td>
        <td>10086</td>
        <td>
        <img src="images/car1.jpg" style="width:25px;height:25px">
        <img src="images/car1.jpg" style="width:25px;height:25px">
        </td>
      </tr>

    
    </table>
  </div>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  </body>
</html>