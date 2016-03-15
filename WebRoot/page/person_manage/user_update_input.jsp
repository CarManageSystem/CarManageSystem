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
    div{height:25px;margin-top:5px;margin-left:20px}
    </style>
  </head>
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div class="container" >
 
  <form class="form-horizontal">
    <div class="row">
      <div class="col-xs-8 col-xs-offset-2" style="border:2px solid #aaaaaa;border-radius:10px;margin-top:10px;margin-bottom:10px;height:540px;background:#ebebeb">
        <div class="row">
          <div class="col-xs-8">
  
            <div class="form-group">
              <label class="col-xs-3 control-label" style="margin-top:10px">姓名：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="name">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">性别：</label>
              <div class="col-xs-8">  
                <label class="radio-inline">
                  <input type="radio" name="sex1" value="male">
                                              男
                </label>  
                <label class="radio-inline">
                  <input type="radio" name="sex0" value="female">
                                              女
                </label>
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">身份证号：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="idnumber">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">出生日期：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="borndate">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">民族：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="nation">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">籍贯：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="nativeplace">
              </div>
            </div> 
      
            <div class="form-group">
              <label class="col-xs-3 control-label">婚姻状况：</label>
              <div class="col-xs-8">  
                <label class="radio-inline">
                  <input type="radio" name="marriage0" value="unmarry">
                                             未婚
                </label>  
                <label class="radio-inline">
                  <input type="radio" name="marriage1" value="marry">
                                             已婚
                </label>
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">学历：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="education">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">通讯地址：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="useraddress">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">移动电话：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="usertel">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">紧急联系人：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="emergcontact">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-3 control-label">联系电话：</label>
              <div class="col-xs-8">
                <input type="text" class="form-control" name="emergtel">
              </div>
            </div>

            <div class="form-group" style="margin-top:20px;">
              <button type="submit" class="btn btn-default col-xs-2 col-xs-offset-6" style="line-height:15px;border:2px solid #a9a9a9;border-radius:5px;width:80px;height:30px;"><b>提 交</b></button>
              <button class="btn btn-default" style="margin-left:40px;line-height:15px;border:2px solid #a9a9a9;border-radius:5px;width:80px;height:30px;"><b>返 回</b></button>
            </div>
          </div>
    
          <div class="col-xs-1 " style="margin-top:10px;">    
            <div>
              <img src="images/car1.jpg" style="width:120px;height:150px">
              <input type="file" style="margin-top:10px">
            </div>
    
          </div>
        </div>
      </div>
    </div>
  
  </form>

  </div>
  
  <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>

  <script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
  <script type="text/javascript">
  	$("input[name='name']").val('<%=request.getAttribute("name")%>');
  	if(<%=request.getAttribute("userSex")%> == 0){
  		$("input[name='sex0']").prop("checked", true);
  	}else{
  		$("input[name='sex1']").prop("checked", true);
  	}
  	$("input[name='idnumber']").val('<%=request.getAttribute("idNumber")%>');
  	$("input[name='borndate']").val('<%=request.getAttribute("bornDate")%>');
  	$("input[name='nation']").val('<%=request.getAttribute("nation")%>');
  	$("input[name='nativeplace']").val('<%=request.getAttribute("nativePlace")%>');
  	if(<%=request.getAttribute("marriage")%> == 0){
  		$("input[name='marriage0']").prop("checked", true);
  	}else{
  		$("input[name='marriage1']").prop("checked", true);
  	}
  	$("input[name='education']").val('<%=request.getAttribute("education")%>');
  	$("input[name='useraddress']").val('<%=request.getAttribute("userAddress")%>');
  	$("input[name='usertel']").val('<%=request.getAttribute("userTel")%>');
  	$("input[name='emergcontact']").val('<%=request.getAttribute("emergContact")%>');
  	$("input[name='emergtel']").val('<%=request.getAttribute("emergTel")%>');
  </script>

  </body>
</html>