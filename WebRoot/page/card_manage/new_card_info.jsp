<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加新卡-新卡信息</title>
    
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
  body {font-family:Microsoft YaHei;}
  .left{height:530px;width:20%;border:1px solid #ebebeb;border-right:thick double #D3D3D3;background:#f5f5f5;margin-top:15px;box-shadow:-3px 3px 1px #d3d3d3;}
  .form-group{height:25px;margin-top:5px;}
  </style>

  </head>
  
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div style="margin-top:0px;height:15px;font-size:0.9em;">
    <ol class="breadcrumb" style="padding:0px 15px;background:#ffffff">
      <li  style="margin-left:60px;"><a href="house.action"><b>首页</b></a></li>
      <li class="active"><a href="#"><b>办卡管理</b></a></li>
    </ol>
  </div>
  
  <div class="container" style="height:530px">
    <div class="row">
      <div class="col-xs-3 left">
        <div style="margin-top:25px;font-family:FangSong;font-size:1.1em;">
          <ul class="nav nav-pills nav-stacked" >
            <li class="active"><a style="font-weight:700;border:1px solid #337ab7;">办理新卡</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">办卡统计</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">办卡标准与流程说明</a></li>
          </ul>
        </div>
      </div>
      <div class="col-xs-7 middle">
        <div style="margin-left:30px;margin-top:30px;font-size:1.2em" align="center">
          <span><b>新卡信息</b></span>
        </div>
        <div>
        <div class="row" style="font-size:1.0em;margin-top:20px;">
          <form action="newcardinfo_update.action" method="post" id="CardInfo" name="CardInfo" class="form-horizontal" role="form">	
          <div class="col-xs-6">
  			<div class="form-group">
  			  <label class="col-xs-5 control-label">新卡类型：</label>
  			  <div class="col-xs-7">
  			    <select class="form-control" name="CardType" id="CardType" onchange="cardtype(this)">
                  <option value="0">年度卡</option>
                  <option value="1">季度卡</option>
                  <option value="2">月度卡</option>
                  <option value="3">临时定期卡</option>
                  <option value="4">企业办公类-BA卡</option>
                </select>
  			  </div>
  			</div>
  			<div class="form-group">
  			  <label class="col-xs-5 control-label">申请房间号：</label>
  			  <div class="col-xs-7">
  			    <input type="text" class="form-control" name="RoomNum" onchange="carhad(this)">
  			  </div>
  			</div>
  			<div class="form-group">
  			  <label class="col-xs-5 control-label">与业主关系：</label>
  			  <div class="col-xs-7">
  			    <select class="form-control" name="RelOwner">
                  <option value="0">业主</option>
                  <option value="1">租户</option>
                  <option value="2">商业</option>
                  <option value="3">办公</option>
                </select>
  			  </div>
  			</div>
  			<div class="form-group">
              <label class="col-xs-5 control-label">房间已有车辆：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="CarHad" id="CarHad" readonly="readonly">
              </div>
            </div>
            <div class="form-group">
              <label class="col-xs-5 control-label">能否申请：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="Apply" id="Apply" readonly="readonly">
              </div>
            </div>
            <div class="form-group">
              <label class="col-xs-5 control-label">业主姓名：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="NameOwner">
              </div>
            </div>
            <div class="form-group">
              <label class="col-xs-5 control-label">联系方式：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="TelOwner">
              </div>
            </div>
            
           
      
            <div class="form-group">
              <label class="col-xs-5 control-label">起始时间：</label>
              <div class="col-xs-7">
                <input type="date" class="form-control" name="TimeStart" onchange="starttime(this)">
              </div>
            </div> 
            <div class="form-group">
              <label class="col-xs-5 control-label">终止时间：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="TimeEnd" id="TimeEnd" readonly="readonly">
              </div>
            </div> 
            <div class="form-group">
              <label class="col-xs-5 control-label">车位类型：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="CarportType">
              </div>
            </div> 
            </div>
            
            <div class="col-xs-6">
            <div class="form-group">
              <label class="col-xs-5 control-label">固定车位编号：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="CarportNum">
              </div>
            </div>
             <div class="form-group">
              <label class="col-xs-5 control-label">申请人：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="NameApply">
              </div>
            </div>
      
            <div class="form-group">
              <label class="col-xs-5 control-label">联系方式：</label>
              <div class="col-xs-7">       
                <input type="text" class="form-control" name="TelApply">                           
              </div>
            </div>
            <div class="form-group">
              <label class="col-xs-5 control-label">支付金额：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="PayMoney" id="PayMoney" readonly="readonly" value="8000">
              </div>
            </div> 
            <div class="form-group">
              <label class="col-xs-5 control-label">支付时间：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="PayTime" value="预先支付">
              </div>
            </div> 
            <div class="form-group">
              <label class="col-xs-5 control-label">是否开具发票：</label>
              <div class="col-xs-7">
                <label class="radio-inline">
                  <input type="radio" name="Invoice" value="0">
                                              是
                </label>  
                <label class="radio-inline">
                  <input type="radio" name="Invoice" value="1">
                                              否
                </label>
              </div>
            </div> 
            <div class="form-group">
              <label class="col-xs-5 control-label">发票抬头：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="Title">
              </div>
            </div>  
            
            <div class="form-group">
              <label class="col-xs-5 control-label">发票是否邮寄：</label>
              <div class="col-xs-7">
                <label class="radio-inline">
                  <input type="radio" name="Post" value="0">
                                              是
                </label>  
                <label class="radio-inline">
                  <input type="radio" name="Post" value="1">
                                              否
                </label>
              </div>
            </div>
            <div class="form-group">
              <label class="col-xs-5 control-label">邮寄地址：</label>
              <div class="col-xs-7">
                <input type="text" class="form-control" name="Address">
              </div>
            </div> 
            </div> 
          </form>
          <!-- 提交表单无刷新<iframe style="display: none" id="CardInfo"  name="CardInfo" src="about:blank"> </iframe>-->  
          
          </div>
        <div align="center">
          <input type="submit" style="width:80px;height:30px;line-height:15px;margin-top:15px;margin-left:30px;background:#337ab7;color:#ffffff;border-radius:5px;border:none" value="保存" onclick="save()"/>
          <button style="width:80px;height:30px;line-height:15px;margin-top:15px;margin-left:30px;background:#337ab7;color:#ffffff;border-radius:5px;border:none">返回</button>
        </div>
        </div>
      </div>
      <div class="col-xs-2 right">
        <div style="margin-top:40px;margin-left:50px;font-family:FangSong;font-size:1.1em;text-align:center">
          <ul class="nav nav-pills nav-stacked" >
            <li class="active"><a style="font-weight:700;border:1px solid #337ab7;">新卡信息</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">车主车辆</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">提交资料</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
   
  <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  <script type="text/javascript">
  function carowner() {
	top.location = "carowner_info.action?CardNum="+"test";
	return false;
  }
  
  function cardtype(e) {
	  var type = e.value;
	  $.ajax({
	   		url:"cardtype",
	   		type:"post",
	   		async:false,
	   		dataType:"json",
	   		data:{type:type},
	   		success:function(data){
	   			var pay = document.getElementById("PayMoney");
	   			pay.value = data.price;
	   		}
	  });
  }
  
  function carhad(e) {
	  var roomnum = e.value;
	  $.ajax({
	   		url:"carhad",
	   		type:"post",
	   		async:false,
	   		dataType:"json",
	   		data:{RoomNum:roomnum},
	   		success:function(data){
	   			var had = document.getElementById("CarHad");
	   			var apply = document.getElementById("Apply");
	   			had.value = data.had;
	   			if(data.had <= 2) {
	   				apply.value = "能";
	   			} else {
	   				apply.value = "不能";
	   			}
	   		}
	  });
  }
  
  function starttime(e) {
	  var end = document.getElementById("TimeEnd");
	  var type = document.getElementById("CardType");
	  var date = new Date();
	  var year = e.value.substring(0,4);
	  var month = e.value.substring(5,7);
	  var day = e.value.substring(8,10);
	  date.setFullYear(year, month-1, day);
	  switch(type.value){
	    case "0":
	    	date.setDate(date.getDate()+365);
	    	break;
	    case "1":
	    	date.setDate(date.getDate()+90);
	    	break;
	    case "2":
	    	date.setDate(date.getDate()+30);
	    	break;
	  }
	  end.value = fmt(date);
  }
  
  function fmt(date) {
	  var year = date.getFullYear().toString();
	  var month = (date.getMonth()+1).toString();
	  var day = date.getDate().toString();
	  if(month.length == 1) {
		  if(day.length == 1) {
			  return year+"/0"+month+"/0"+day;
			  
		  }else {
			  return year+"/0"+month+"/"+day;
		  }
	  }else {
		  if(day.length == 1) {
			  return year+"/"+month+"/0"+day;
		  }else {
			  return year+"/"+month+"/"+day;
		  }
	  }
	  
  }
  
  function save() {  
	  //document.forms[0].target="CardInfo";
	  $("#CardInfo").submit();
	  alert("保存成功");
  }
  </script>
  </body>
</html>
