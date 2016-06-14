<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设置全时车辆费率</title>

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
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    
    <style type="text/css">
   body {font-family:Microsoft Yahei}
   .select{height:530px;border:1px solid #ebebeb;border-right:thick double #D3D3D3;background:#f5f5f5;margin-top:15px;box-shadow:-3px 3px 1px #d3d3d3;}
   .display{height:530px;border:1px solid #ebebeb;border-left:thick double #D3D3D3;background:#f5f5f5;margin-top:15px;box-shadow:3px 3px 1px #d3d3d3;}
   .standard{margin-left:80px}
   .standard span{line-height:26px;display:block}
   </style>

  </head>
  
  <body>
    <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div style="margin-top:0px;height:15px;font-size:0.9em;">
    <ol class="breadcrumb" style="padding:0px 15px;background:#ffffff">
      <li  style="margin-left:60px"><a href="house.action"><b>首页</b></a></li>
      <li ><a href="charge_standard.action"><b>收费标准</b></a></li>
      <li class="active"><b>设置收费标准</b></li>
    </ol>
  </div>
  
  <div class="container">
    <div class="row">
      <div class="col-xs-3 select">
        <div style="margin-top:10px;font-size:1.1em">
          <ul class="nav nav-pills nav-stacked">
            <li><a>收费许可</a></li>
            <li><a>临时车辆</a></li>
            <li class="active"><a>年度全时车辆</a></li>
            <li><a>办卡车辆</a></li>
            <li><a>A类企业车辆</a></li>
            <li><a>B类商户车辆</a></li>
          </ul>
        </div>                                            
      </div>
 
      <div class="col-xs-9 display">  
        <div class="col-xs-8 col-xs-offset-2">
          <h3 align="center">全时车辆收费标准</h3>
          <form action="/CarManageSystem/CardFee_update.action" method="post" id="CardFeeUpdate"  onsubmit="return save()">
            <div style="height:70%;padding-left:170px">
              <div class="row" style="width:600px;margin-top:30px"> 
                <span>年度全时费率：</span>
                <input type="text" name="yearall" id="yearall" style="width:100px;border:none"/>
                <span>元</span>
              </div>
              <div class="row" style="width:600px;margin-top:20px">
                <span>季度全时费率：</span>
                <input type="text" name="quarter" id="quarter" style="width:100px;border:none"/>
                <span>元</span>
              </div>  
              <div class="row" style="width:600px;margin-top:20px">
                <span>月度全时费率：</span>
                <input type="text" name="month" id="month" style="width:100px;border:none"/> 
                <span>元</span>
              </div>
            </div>
            <div align="center">
              <input type="submit" value="保存" style="margin-left:40px;margin-top:20px;width:90px;background:#337ab7;color:#ffffff;border:none;height:30px;border-radius:3px"/>
              <input type="button" value="返回" style="margin-left:80px;margin-top:20px;width:90px;background:#337ab7;color:#ffffff;border:none;height:30px;border-radius:3px" onclick="back()"/>
            </div>
          </form>
        </div>
      </div>
    </div>  
  </div>
    
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>  
  
  <script type="text/javascript">
  function save() {
	  var flag = 0;
	  var inputs = new Array(3);
	  inputs[0] = document.getElementById("yearall");
	  inputs[1] = document.getElementById("quarter");
	  inputs[2] = document.getElementById("month");
	  for(var i=0;i<3;i++) {
		  inputs[i].setAttribute("style","width:100px;border:none");
		  if(inputs[i].value == "") {  
			  inputs[i].setAttribute("style","width:100px;border:1px solid red");
			  flag=1;  
		  }
	  }
	  if(flag==1){
		  alert("不可为空！");
		  return false;  
	  }
  }
  
  function back(){
		 top.location = window.history.back(); 
		 return false;
}
  </script>
  </body>
</html>
