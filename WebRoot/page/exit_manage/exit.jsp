<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wsPath=request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出口界面</title>
    
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
   body {font-family:Microsoft Yahei;background:#f5f5ef}
  </style>
  </head>
  
  <body>
  
  <div class="top" >
    <div class="container-fluid">
      <div class="row" style="height:25px;text-align:center;background-image:url(/CarManageSystem/images/background.jpg);color:#ffffff">
        <div class="col-xs-4">
          <span>万达集团车辆管理系统</span>
        </div>
        <div class="col-xs-4">
          <span>2016-07-11 17:04:56</span>
        </div>
        <div class="col-xs-4">
          <span>北京牧诚科技有限公司</span>
        </div>
      </div>
      
      <div class="row" style="margin-top:20px;">
        <div class="col-xs-9">
          <div class="row">
            <div class="col-xs-6" style="text-align:center;font-size:1.2em;">
              <span>入  口</span>
              <div style="height:300px;width:90%;margin-left:5%;margin-top:10px;border:2px solid #ebebeb;background:#ffffff">
                <img id="inPicture" style="height:100%;width:100%;">
              </div>
            </div>
            <div>
            </div>
            <div class="col-xs-6" style="text-align:center;font-size:1.2em;">
              <span>出  口</span>
              <div style="height:300px;width:90%;margin-left:5%;margin-top:10px;border:2px solid #ebebeb;background:#ffffff">
                <img id="outPicture" style="height:100%;width:100%;">
              </div>
            </div>
          </div>
          <div class="row" style="margin-top:30px;border-top:2px solid #a9a9a9;width:95%;margin-left:2.5%;">
            <div class="col-xs-6" style="width:45%;margin-left:2.5%;font-size:1.2em;font-weight:bold;line-height:40px">
              <div>
                <span>车牌号：</span><span id="carLicense"></span>
              </div>
              <div>
                <span>进入时间：</span><span id="timeIn"></span>
              </div>
              <div>
                <span>离开时间：</span><span id="timeOut"></span>
              </div>
              <div>
                <span>停车时长：</span><span id="parkTime"></span>
              </div>           
            </div>
            <div class="col-xs-6" style="width:45%;margin-left:7.5%;font-size:1.2em;font-weight:bold;line-height:40px">
              <div>
                <span>停车费用：</span><span id="fee"></span>
              </div>
              <div>
                <span>支付方式：</span><span id="payType"></span>
              </div>
              <div>
                <button style="font-size:1.3em;font-weight:normal;text-align:center;background:#008800;color:#ffffff;height:60px;width:200px;border-radius:5px;">收费抬杆</button>
              </div>        
            </div>
          </div>
        </div>
        <div class="col-xs-3" style="margin-top:20px;">
          <div style="height:540px;width:98%;border:1px solid #a9a9a9">
            此处留作信息展示?
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>  
  
  
  <script type="text/javascript">
  //websocket
	var ws = null,ip="1";
  
	function startWebSocket() { 
		var url="ws://<%=wsPath%>websocket/"+ip;
	    if ('WebSocket' in window)    
	        ws = new WebSocket(url);    
	    else if ('MozWebSocket' in window)    
	        ws = new MozWebSocket(url);    
	    else    
	        alert("not support");    
	        
	        
	    ws.onmessage = function(evt) {
	    	//后台传递错误信息
	    	if(evt.data.indexOf("error") >= 0){
	    		var message=evt.data.split("!");
	    		alert(message[1]);
	    	}
	    	else{		
	    		jsonData = eval('(' + evt.data + ')');
	    		document.getElementById("carLicense").innerHTML = jsonData.carLicense;
	    		document.getElementById("fee").innerHTML = jsonData.fee;
	    		document.getElementById("timeIn").innerHTML = jsonData.timeIn;
	    		document.getElementById("timeOut").innerHTML = jsonData.timeOut;
	    		document.getElementById("parkTime").innerHTML = jsonData.parkTime+"min";
	    		document.getElementById("fee").innerHTML = jsonData.fee+"元";
	    		if(jsonData.payType==0){
	    			document.getElementById("payType").innerHTML = "现金支付";
	    		}else{
	    			document.getElementById("payType").innerHTML = "APP支付";
	    		}
	    		document.getElementById("inPicture").src = "images/car1.jpg";
	    		document.getElementById("outPicture").src = "images/car1.jpg";
	    	}
	    };    
	        
	    ws.onclose = function(evt) {;    
	        //alert("close");    
	    };    
	        
	    ws.onopen = function(evt) {   ; 
	        //alert("open");    
	    };    
	};
	
	
	function sendMsg(msg) {    
	    ws.send(msg);    
	}

	
	window.onload=function(){
		startWebSocket();
	};
  </script>
  </body>
</html>
