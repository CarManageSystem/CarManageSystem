<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账户信息修改</title>
    
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
   
    tr {border:2px solid #dcdcdc}
    button {border:2px solid #a9a9a9;border-radius:5px;width:80px;height:30px;margin-top:15px;margin-left:20px;margin-right:20px}
    </style>
  </head>
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <center>
  <div class="container">
    <div style="margin-top:20px;height:500px;overflow:auto;">
    <table class="table table-bordered" style="font-size:1em;width:800px;background:#f8f8f8;text-align:center">
      <tbody>
        <tr><td colspan=7 style="font-size:1.3em;text-align:left;background:#ebebeb;border:2px solid #a9a9a9"><b>个人基本信息</b></td></tr>
        <tr>
          <td ><b>姓名</b></td>
          <td >陈永华</td>
          <td ><b>性别</b></td>
          <td >男</td>
          <td ><b>身份证号码</b></td>
          <td >350583198011111111</td>
          <td rowspan=4><img style="width:90px;height:120px" src="images/car1.jpg"></td>
        </tr>
        <tr>
          <td ><b>出生日期</b></td>
          <td >1980-11-11</td>
          <td ><b>民族</b></td>
          <td >汉</td>
          <td ><b>籍贯</b></td>
          <td >山东</td>        
        </tr>
        <tr>
          <td ><b>政治面貌</b></td>
          <td >群众</td>
          <td ><b>婚姻状况</b></td>
          <td >已婚</td>
          <td ><b>学历</b></td>
          <td >大专</td>         
        </tr>     
        <tr>
          <td ><b>通讯住址</b></td>
          <td colspan=3 >北京市海淀区北京邮电大学</td>
          <td ><b>移动电话</b></td>
          <td colspan=1 >13806666666</td>     
        </tr>
        <tr>
          <td ><b>户口所在地</b></td>
          <td colspan=3 >山东省济南市</td>
          <td ><b>户口性质</b></td>
          <td colspan=2 >农业</td>     
        </tr>
        <tr>
          <td ><b>紧急联系人</b></td>
          <td colspan=3 >陈某某</td>
          <td ><b>联系电话</b></td>
          <td colspan=2 >13806666666</td>     
        </tr>
        <tr><td colspan=7 style="font-size:1.3em;text-align:left;background:#ebebeb;border:2px solid #a9a9a9"><b>工作基本信息</b></td></tr>
        <tr>
          <td ><b>岗位</b></td>
          <td colspan=3 >出入口管理员</td>       
          <td ><b>权限</b></td>
          <td colspan=2 >可干啥干啥</td>     
        </tr>
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>     
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
        <tr>
          <td ><b>位置</b></td>
          <td colspan=3 >万达地下停车场A出口</td>         
          <td ><b>排班</b></td>
          <td colspan=2 >周一--周五 早班</td>     
        </tr>   
      </tbody>
    </table>
    </div>
  </div>
  
  <button><b>修 改</b></button>
  <button><b>删 除</b></button>
  <button><b>返 回</b></button>
  
  </center>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>

  
  </body>
</html>