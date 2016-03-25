<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>车辆管理</title>
    
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
    body {font-family:Microsoft Yahei}
    .title {float:left;width:80px;font-weight:bold}
    .condition {clear:both;margin-left:20px;margin-right:20px;padding-top:8px}
    .clear {margin-left:5px;margin-right:10px;padding-top:10px}
    .info-list {margin-left:30px;margin-right:30px;padding-top:5px}
    
    .condition a:hover {background:#7700FF;color:#ffffff;text-decoration:none;}
    .condition a {color:#444444;float:left;margin-right:15px}
    .state {margin-top:15px;margin-left:20px;font-size:1.3em;text-align:center}
    .state2 {margin-top:10px;margin-left:35px;font-size:1.1em;text-align:center;color:#808080}
    .line {margin-left:20px;margin-right:20px;margin-top:8px;margin-bottom:2px;height:1px;border:none;border-top:1px dashed #a9a9a9;}
    .select {float:left;margin-right:5px;border:1px solid #000000;padding-left:5px;padding-right:5px}
    </style>
    
  </head>
  
  <body>
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div style="margin-top:0px;height:15px;font-size:0.9em;">
    <ol class="breadcrumb" style="padding:0px 15px;background:#ffffff">
      <li  style="margin-left:60px"><a href="house.action"><b>首页</b></a></li>
      <li class="active"><b>车辆管理</b></li>
    </ol>
  </div>
  
  <div class="container" style="margin-top:15px">
    <div class="row">
      <div class="col-xs-9" style="height:545px;border:1px solid #ebebeb">
        <div class="query" style="font-size:0.9em">
          <div class="row" style="margin-left:0px;margin-right:0px;margin-top:10px;height:10%;background:f5f5f5">
          <div class="col-xs-9 clear" id="clear">
            <span class="title">已选：</span>     
          </div>
          <div style="padding-top:7px">
              <input type="text" style="float:left;width:120px;height:25px;border-radius:3px;" class="form-control"> 
              <button type="submit" style="float:left;height:25px;margin-left:5px">查询</button>
          </div>
          
          </div>
          
          <div class="condition-list" style="height:27%;background:#f5f5f5;">
          <div class="condition">
            <span class="title">车辆种类：</span>
            <div>
              <a>临时车辆</a>
              <a>长期车辆</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">车辆状态：</span>  
            <div>
              <a>进场</a>
              <a>场内</a>
              <a>出场</a>
              <a>预约</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">放行标准：</span>  
            <div>
              <a>自动</a>
              <a>现金</a>
              <a>强制</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">出口类型：</span>  
            <div>
              <a>A口</a>
              <a>B口</a>
              <a>C口</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">时间：</span>  
            <div>
              <input type="date" name="idate" id="idate" value="">
              <input type="time" name="itime" id="itime" value="">
              <span>——</span>
              <input type="date" name="odate" id="odate" value="">
              <input type="time" name="otime" id="otime" value="" onchange="datetime()">
            </div>
            
          </div>
          </div>
          <div style="text-align:center;height:2%;background:#f5f5f5;">
            <span class="glyphicon glyphicon-menu-up" id="menuup"></span>
            <span class="glyphicon glyphicon-menu-down" id="menudown" style="display:none"></span>
          </div>
        </div>
        <div class="info" id="info" style="height:55%;background:#ebebeb;margin-top:10px;padding-top:10px">
        
          <div class="row">
            <div class="col-xs-8">
              <span style="margin-left:20px;"><b>检索结果</b></span>
            </div>
          </div>
          <hr style="margin-left:10px;margin-right:10px;margin-top:8px;margin-bottom:2px;height:2px;border:none;border-top:1px ridge #185598;">
          <div style="overflow:auto">
          <div class="info-list">
            <span>2016/3/23 21:42：  京A TC8008 出入口A1 现金放行</span>
          </div>
          <hr class="line">
          <div class="info-list">
            <span>2016/3/23 19:42：京B TC8008 出入口A1 免费放行</span>
          </div>
          <hr class="line">
          <div class="info-list">
            <span>2016/3/23 12:42：京A TC8008 出入口A1 现金放行 额额额额额额额</span>
          </div>
          <hr class="line">
          <div class="info-list">
            <span>2016/3/23 9:42：京A TC8008 出入口A1 现金放行 额额额额额额额</span>
          </div>
          <hr class="line">
          <div class="info-list">
            <span>2016/3/23 8:42： 京A TC8008 出入口A1 现金放行 额额额额额额额</span>
          </div>
          <hr class="line">
          </div>
        </div>
      
      </div>
      <div class="col-xs-3" style="height:545px;border:1px solid #ebebeb">
        <div style="height:96%;background:#ebebeb;margin-top:10px;">
          <div style="text-align:center;padding-top:20px;padding-bottom:10px;font-weight:bold">
            <span>2016年3月23日</span>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>场内车辆</span>
            </div>
            <div class="col-xs-6">
              <span>90辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>长期车辆</span>
            </div>
            <div class="col-xs-6">
              <span>45辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>临时车辆</span>
            </div>
            <div class="col-xs-6">
              <span>45辆</span>
            </div>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>进场车辆</span>
            </div>
            <div class="col-xs-6">
              <span>388辆</span>
            </div>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>出场车辆</span>
            </div>
            <div class="col-xs-6">
              <span>303辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>自动放行</span>
            </div>
            <div class="col-xs-6">
              <span>200辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>现金放行</span>
            </div>
            <div class="col-xs-6">
              <span>100辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>强制放行</span>
            </div>
            <div class="col-xs-6">
              <span>3辆</span>
            </div>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>预约车辆</span>
            </div>
            <div class="col-xs-6">
              <span>13辆</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  
  </div>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  
  <script type="text/javascript">
    $("a").click(function(){  	
    	var select=document.createElement("div");
        var span1=document.createElement("span");
        var span2 = document.createElement("span");
    	span1.innerHTML=this.innerHTML;
    	span2.setAttribute("class", "glyphicon glyphicon-remove");
    	span2.setAttribute("width", "0.8em");
    	span2.setAttribute("onclick","delet()");
    	select.appendChild(span1);	
    	select.appendChild(span2);
    	select.setAttribute("class", "select");
        document.getElementById("clear").appendChild(select);	  	
    });
    function datetime(){
    	var select=document.createElement("div");
        var span1=document.createElement("span");
        var span2 = document.createElement("span");
        var a = document.getElementById("idate");
        var b=document.getElementById("itime");
        var c=document.getElementById("odate");
        var d=document.getElementById("otime");
        span1.innerHTML=a.value+" "+b.value+"—"+c.value+" "+d.value;
        span2.setAttribute("class", "glyphicon glyphicon-remove");
    	span2.setAttribute("width", "0.8em");
    	select.appendChild(span1);	
    	select.appendChild(span2);	
    	select.setAttribute("class", "select");
        document.getElementById("clear").appendChild(select);
    }
    
    function delet(){	
    	var par=$(this).parentNode;
    	alert("delet");
    	document.getElementById("clear").removeChild(par);
    }
    
   $("#menuup").click(function(){

    	$(".condition-list").slideUp(100,
    			function(){ 	
    		$("#menuup").hide();
    		$("#menudown").show();
    	});
    	document.getElementById("info").style.height="82%"; 	
    });
    
    $("#menudown").click(function(){ 	
    	$(".condition-list").slideDown(100,
    	function (){
    		
    		$("#menuup").show();
    		$("#menudown").hide();
    	});
    	document.getElementById("info").style.height="55%";  	
    });
    
    /*$(".glyphicon-remove").click(function (){
    	    alert("s1");
    	    //$(this).click(function(){
    		alert("s");
    		//var par=this.parentNode;
        	//document.getElementById("clear").removeChild(par);

    	});
    //});*/
  </script>
  </body>
</html>