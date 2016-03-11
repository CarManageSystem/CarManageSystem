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
    </style>
  </head>
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <center>
  <div class="container">
    <table class="table table-bordered" style="margin-top:10px;font-size:1em;width:800px;background:#f8f8f8;text-align:center">
      <tbody>
        <tr><td colspan=7 style="font-size:1.3em;text-align:left;background:#ebebeb;border:2px solid #a9a9a9"><b>个人基本信息</b></td></tr>
        <tr>
          <td ><b>姓名</b></td>
          <td >陈永华</td>
          <td ><b>性别</b></td>
          <td >男</td>
          <td >身份证号码</td>
          <td >350583198011111111</td>
          <td rowspan=4><img style="width:90px;height:120px" src="images/car1.jpg"></td>
        </tr>
        <tr>
          <td >出生日期</td>
          <td >1980-11-11</td>
          <td >民族</td>
          <td >汉</td>
          <td >籍贯</td>
          <td >山东</td>
          
        </tr>
        <tr>
          <td >政治面貌</td>
          <td >党员</td>
          <td >婚姻状况</td>
          <td >已婚</td>
          <td >学历</td>
          <td >本科</td>
          
        </tr>
        <tr>
          <td >家庭住址</td>
          <td colspan=3 >北京市海淀区北京邮电大学</td>
          <td >家庭电话</td>
          <td colspan=1 >010-66666666</td>     
        </tr>
        <tr>
          <td >通讯住址</td>
          <td colspan=3 >北京市海淀区北京邮电大学</td>
          <td >移动电话</td>
          <td colspan=2 >13806666666</td>     
        </tr>
        <tr>
          <td >户口所在地</td>
          <td colspan=3 >山东省济南市</td>
          <td >户口性质</td>
          <td colspan=2 >农业</td>     
        </tr>
        <tr>
          <td >紧急联系人</td>
          <td colspan=3 >陈某某</td>
          <td >联系电话</td>
          <td colspan=2 >13806666666</td>     
        </tr>
        <tr><td colspan=7 style="font-size:1.3em;text-align:left;background:#ebebeb;border:2px solid #a9a9a9"><b>工作基本信息</b></td></tr>
        <tr>
          <td >岗位</td>
          <td colspan=3 ></td>
          <td >学历</td>
          <td colspan=3 >本科</td>
          <td >联系电话</td>
          <td colspan=2 >13806666666</td>     
        </tr>
      </tbody>
    </table>
  </div>
  </center>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>

  
  </body>
</html>