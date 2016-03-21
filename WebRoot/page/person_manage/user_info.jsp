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
    
    <title>账户信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
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
  
  <div style="height:20px;background:#ffffff;">
    <ol class="breadcrumb">
      <li style="margin-left:60px"><a href="house.action"><b>首页</b></a></li>
      <li><a href="user_list.action"><b>账户管理</b></a></li>
      <li class="active"><b>账户详情</b></li>
    </ol>
  </div>
  <center>
  <div class="container">
    <div style="margin-top:15px;height:490px;overflow:auto;">
    <table class="table table-bordered" style="font-size:1em;width:800px;background:#f8f8f8;text-align:center">
      <tbody>  	
      	<c:forEach items="${user}" var="user">
        <tr><td colspan=7 style="font-size:1.3em;text-align:left;background:#ebebeb;border:2px solid #a9a9a9"><b>个人基本信息</b></td></tr>
        <tr>
          <td ><b>姓名</b></td>
          <td >${user.name}</td>
          <td ><b>性别</b></td>         
          <c:if test="${user.userSex == 1}">
   			<td>男</td>
		  </c:if>
		  <c:if test="${user.userSex == 0}">
   			<td>女</td>
		  </c:if>
          <td ><b>身份证号码</b></td>
          <td >${user.idNumber}</td>
          <td rowspan=4><img style="width:100px;height:135px" src="/CarManageSystem/files/${user.userId}/${user.photoPath}"></td>
        </tr>
        <tr>
          <td ><b>出生日期</b></td>
          <td >${user.bornDate}</td>
          <td ><b>民族</b></td>
          <td >${user.nation}</td>
          <td ><b>籍贯</b></td>
          <td >${user.nativePlace}</td>        
        </tr>
        <tr> 
          <td ><b>婚姻状况</b></td>
          <c:if test="${user.marriage == 1}">
   			<td>已婚</td>
		  </c:if>
		  <c:if test="${user.marriage == 0}">
   			<td>未婚</td>
		  </c:if>
          <td ><b>学历</b></td>
          <td colspan=2>${user.education}</td>         
        </tr>     
        <tr>
          <td ><b>通讯住址</b></td>
          <td colspan=3 >${user.userAddress}</td>
          <td ><b>移动电话</b></td>
          <td colspan=1 >${user.userTel}</td>     
        </tr>
       
        <tr>
          <td ><b>紧急联系人</b></td>
          <td colspan=3 >${user.emergContact}</td>
          <td ><b>联系电话</b></td>
          <td colspan=2 >${user.emergTel}</td>     
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
  
  <button class="btn btn-default" style="background-color:#D5ADD8;" onclick="change(${user.userId})"><b>修 改</b></button>
  <button class="btn btn-default" data-toggle="modal" data-target="#myModal" style="background-color:#D5ADD8;" ><b>删 除</b></button>
  <button class="btn btn-default" style="background-color:#D5ADD8;" onclick="back()"><b>返 回</b></button>
  
  <!-- 创建模态框 -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:150px">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="text-align:right">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel" style="text-align:left">
              <b>删除账户</b>
            </h4>
         </div>
         <div class="modal-body">
                       确定删除该账户：<span>${user.name}</span>
         </div>
         <div class="modal-footer" style="text-align:center">
            <button type="button" class="btn btn-primary" onclick="delet(${user.userId})" style="line-height:15px;">
                              确定
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal" style="line-height:15px;">
                              取消
            </button>        
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>
  
  </c:forEach>
  </center>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
	
  <script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
	<script >
	 function change(a){		   
		 top.location = "user_updateInput.action?userId="+a;
		 return false; 
	  }
	 
	 function delet(a){
		 top.location = "user_delete.action?userId="+a;
		 return false;    
	  }
	 
	 function back(){
		 top.location = window.history.back(); 
		 return false;
	  }
	</script>
  
  </body>
</html>