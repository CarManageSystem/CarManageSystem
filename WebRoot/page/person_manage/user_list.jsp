<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    
    
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
    <thead>
      <tr style="height:25px">
        <th>在线状态</th>
        <th>工号</th>
        <th>姓名</th>
        <th>岗位</th>
        <th>手机</th>       
      </tr>
     </thead>
     <tbody>
	 	<c:forEach items="${users}" var="user">
     		<tr>
        		
        		<c:if test="${user.onlineFlag == 1}">
   					<td><span class="glyphicon glyphicon-user" style="color:green"></span></td>
				</c:if>
				<c:if test="${user.onlineFlag == 0}">
   					<td><span class="glyphicon glyphicon-user" ></span></td>
				</c:if>
        		<td>${user.userName}</td>
        		<td>${user.name}</td>
        		<td>${user.roleName}</td>
        		<td>${user.userTel}</td>
      		</tr>
     	</c:forEach>
     </tbody>
    </table>
  </div>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  </body>
  
  <script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
  <script type="text/javascript">

  </script>
</html>