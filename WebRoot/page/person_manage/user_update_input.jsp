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

	<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    
    <style type="text/css">
    div{height:25px;margin-top:5px;}
    </style>
  </head>
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div style="margin-top:0px;height:20px;background:#ffffff;">
    <ol class="breadcrumb">
      <li  style="margin-left:60px"><a href="house.action"><b>首页</b></a></li>
      <li><a href="user_list.action"><b>账户管理</b></a></li>
      <li><a href="user_detail.action?userId=<%=request.getAttribute("userId")%>"><b>账户详情</b></a></li>
      <li class="active"><b>账户信息修改</b></li>
    </ol>
  </div>
  
  <div class="container" >
 
    <div class="row">
      <div class="col-xs-8 col-xs-offset-2" style="border:2px solid #aaaaaa;border-radius:10px;margin-top:5px;margin-bottom:10px;height:540px;background:#ebebeb">
        <div class="row">
        
          <div class="col-xs-7">
  		  <form action="/CarManageSystem/user_update.action" method="post" id="UserUpdate" style="width:500px" class="form-horizontal" role="form">	
  			<input class="form-control" name="userId" type="hidden" >
  			
            <div class="form-group">
              <label class="col-xs-3 control-label" style="margin-top:10px">姓名：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="name">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">性别：</label>
              <div class="col-xs-7">  
                <label class="radio-inline">
                  <input type="radio" name="userSex" value="1">
                                              男
                </label>  
                <label class="radio-inline">
                  <input type="radio" name="userSex" value="0">
                                              女
                </label>
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">身份证号：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="idNumber">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">出生日期：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="bornDate">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">民族：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="nation">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">籍贯：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="nativePlace">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">婚姻状况：</label>
              <div class="col-xs-7">  
                <label class="radio-inline">
                  <input type="radio" name="marriage" value="0">
                                             未婚
                </label>  
                <label class="radio-inline">
                  <input type="radio" name="marriage" value="1">
                                             已婚
                </label>
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">学历：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="education">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">通讯地址：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="userAddress">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">移动电话：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="userTel">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">紧急联系人：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="emergContact">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">联系电话：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="emergTel">
              </div>
            </div>

            <div class="form-group" style="margin-top:20px;">
              <button type="submit" class="btn btn-default col-xs-2 col-xs-offset-6" style="background-color:#D5ADD8;line-height:15px;border:2px solid #a9a9a9;border-radius:5px;width:80px;height:30px;" onclick="save()"><b>提 交</b></button>
              <button class="btn btn-default"  style="background-color:#D5ADD8;margin-left:40px;line-height:15px;border:2px solid #a9a9a9;border-radius:5px;width:80px;height:30px;" onclick="back()"><b>返 回</b></button>
            </div>
          
          </form>
          </div>
    
          <div class="col-xs-4" style="margin-top:10px;">  
          <form action="/CarManageSystem/upload.action" id="savephoto" method="post" enctype="multipart/form-data" style="width:400px">                
              <img src="" id="photoPath" style="margin-left:70px;margin-top:20px;width:150px;height:180px"> 
              <div class="form-group" style="margin-top:40px">
              	<input class="form-control" name="userId" type="hidden" >
              	<input name="imgFile" id="imgFile" type="file" style="float:left;margin-left:45px;width:150px;height:25px">
              	<button type="submit" style="float:left;margin-left:15px;width:50px;height:22px;line-height:15px;">上传</button>
              </div>          
          </form>
          </div>
          
        </div>
      </div>
    </div>
  
  </form>

  </div>
  
  <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>

  <script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
  <script src="/CarManageSystem/js/jquery.form.js"></script> 
  <script type="text/javascript">
    $("input[name='userId']").val('<%=request.getAttribute("userId")%>');
  	$("input[name='name']").val('<%=request.getAttribute("name")%>');
  	if(<%=request.getAttribute("userSex")%> == 0){
  		$("input[name='userSex']:radio:last").prop("checked", true);
  	}else{
  		$("input[name='userSex']:radio:first").prop("checked", true);
  	}
  	$("input[name='idNumber']").val('<%=request.getAttribute("idNumber")%>');
  	$("input[name='bornDate']").val('<%=request.getAttribute("bornDate")%>');
  	$("input[name='nation']").val('<%=request.getAttribute("nation")%>');
  	$("input[name='nativePlace']").val('<%=request.getAttribute("nativePlace")%>');
  	if(<%=request.getAttribute("marriage")%> == 0){
  		$("input[name='marriage']:radio:first").prop("checked", true);
  	}else{
  		$("input[name='marriage']:radio:last").prop("checked", true);
  	}
  	$("input[name='education']").val('<%=request.getAttribute("education")%>');
  	$("input[name='userAddress']").val('<%=request.getAttribute("userAddress")%>');
  	$("input[name='userTel']").val('<%=request.getAttribute("userTel")%>');
  	$("input[name='emergContact']").val('<%=request.getAttribute("emergContact")%>');
  	$("input[name='emergTel']").val('<%=request.getAttribute("emergTel")%>');
  	
  	$("#photoPath").attr("src","/CarManageSystem/files/<%=request.getAttribute("userId")%>/<%=request.getAttribute("photoPath")%>");

  	
  	function save(){
     	    $("#UserUpdate").submit();       
    }
  	
 // 绑定表单提交事件处理器 ,jquery.form.js
  	$("#savephoto").submit(function(){
        var options = {
                success: function (data) {
                	$("#photoPath").attr("src","/CarManageSystem/files/<%=request.getAttribute("userId")%>/"+data);
                }
            };
        $(this).ajaxSubmit(options); 
     // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false 
     	return false; 
     });
  			 			
 
  	function back(){
		 top.location = window.history.back(); 
		 return false;
	}
  </script>

  </body>
</html>