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
    <script src="js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/jquery.dataTables.css">
    
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
          <div class="row" style="margin-left:0px;margin-right:0px;margin-top:10px;height:6%;background:#f5f5f5">
          <div class="col-xs-9 clear" id="clear">
            <span class="title">已选：</span>     
          </div>
          <form action="/CarManageSystem/car_search.action" method="post" id="search" role="form">
          <div style="padding-top:7px">
              <input type="text" style="float:left;width:120px;height:25px;border-radius:3px;" class="form-control" name="carLicense" id="searchbycarLicense" placeholder="请输入车牌号"/> 
              <button type="submit" style="float:left;height:25px;margin-left:5px" onclick="carsearch()">查询</button>
          </div>
          </form>
          
          </div>
          
          <div class="condition-list" style="height:27%;background:#f5f5f5;">
          <div class="condition">
            <span class="title">车辆种类：</span>
            <div>
              <a id="type1">临时车辆</a>
              <a id="type2">长期车辆</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">车辆状态：</span>  
            <div>
              <a id="state1">场内</a>
              <a id="state2">出场</a>
              <a id="state3">预约</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">放行标准：</span>  
            <div>
              <a id="pass1">自动</a>
              <a id="pass2">现金</a>
              <a id="pass3">强制</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">出口类型：</span>  
            <div>
              <a id="exitA">A口</a>
              <a id="exitB">B口</a>
              <a id="exitC">C口</a>
            </div>
          </div>
          <div class="condition">
            <span class="title">时间：</span>  
            <div>
              <input type="date" name="idate" id="idate" value="">
              <input type="time" name="itime" id="itime" value="">
              <span>——</span>
              <input type="date" name="odate" id="odate" value="">
              <input type="time" name="otime" id="otime" value="">
              <button onclick="datetime()">确定</button>
            </div>
          </div>
          </div>
          <div style="text-align:center;height:3%;background:#f5f5f5;">
            <span class="glyphicon glyphicon-menu-up" id="menuup"></span>
            <span class="glyphicon glyphicon-menu-down" id="menudown" style="display:none"></span>
          </div>
        </div>
        <div class="row" style="height:5%;margin-left:0px;margin-right:0px;background:#ebebeb;margin-top:10px;padding-top:3px;border-radius:5px;">             
           <span style="margin-left:20px;"><b>检索结果</b></span> 
        </div>
        <div class="info" id="info" style="height:52%;background:#ebebeb;margin-top:5px;padding-left:20px;padding-right:20px">   
          <table class="table table-hover" style="font-size:0.9em" id="parkiorecord">
          <thead>
            <tr>
              <th>车牌号</th>
              <th>入场时间</th>
              <th>出场时间</th>
              <th>车位号</th>
              <th>出口</th>       
            </tr>
          </thead>
          <tbody id="records">
	 	    <c:forEach items="${records}" var="record">
	 	      <tr>
        		<td>${record.carLicense}</td>
        		<td>${record.timeIn}</td>
        		<td>${record.timeOut}</td>
        		<td>${record.carportId}</td>
        		<td>${record.exitTypeString}</td>
      		  </tr>
     	    </c:forEach>
          </tbody>
        </table>
        
      </div>
      
      </div>
      <div class="col-xs-3" style="height:545px;border:1px solid #ebebeb">
        <div style="height:96%;background:#ebebeb;margin-top:10px;">
          <div style="text-align:center;padding-top:20px;padding-bottom:10px;font-weight:bold">
            <span id="today"></span>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>场内车辆</span>
            </div>
            <div class="col-xs-6">
              <span id="inside">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>长期车辆</span>
            </div>
            <div class="col-xs-6">
              <span id="longterm">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>临时车辆</span>
            </div>
            <div class="col-xs-6">
              <span id="temp">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>进场车辆</span>
            </div>
            <div class="col-xs-6">
              <span id="into">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>出场车辆</span>
            </div>
            <div class="col-xs-6">
              <span id="out">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>自动放行</span>
            </div>
            <div class="col-xs-6">
              <span id="auto">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>现金放行</span>
            </div>
            <div class="col-xs-6">
              <span id="cash">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state2">
            <div class="col-xs-6">
              <span>强制放行</span>
            </div>
            <div class="col-xs-6">
              <span id="force">0</span><span>辆</span>
            </div>
          </div>
          <div class="row state">
            <div class="col-xs-6">
              <span>预约车辆</span>
            </div>
            <div class="col-xs-6">
              <span id="order">0</span><span>辆</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  
  </div>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  
  <script type="text/javascript">
    $(document).ready(function(){
    	$("#parkiorecord").DataTable({
            "bFilter":false,//去掉搜索栏
            "paging":true,
            "dom": 'frtip',
            "sPaginationType": "full_numbers",
            "oLanguage": {
                "sInfo": "共_TOTAL_条数据",
                "sInfoEmpty": "",
                "sInfoFiltered": "(从_MAX_条数据中的查询)",
                "sLengthMenu": "每页显示 _MENU_ 条数据",
                "sSearch":  "查找 _INPUT_ ",
                "sZeroRecords": " ",
                "oPaginate":{
                    "sPrevious":"上一页",
                    "sNext":"下一页",
                    "sLast":"尾页",
                    "sFirst":"首页"
                },
            },
            "iDisplayLength":5,                
        });
    	parkupdate();
    	setInterval("parkupdate()",5000);
    	
    });
    
    function parkupdate(){
    	$.ajax({
     		url:"park_state",//跟@RequestMapping(value="/")一样
     		type:"post",
     		async:true,
     		dataType:"json",
     		data:{},
     		success:function(data){
     			var today = new Date();
     			document.getElementById("today").innerHTML = today.toLocaleDateString();
     			document.getElementById("inside").innerHTML = data.inside;
     			document.getElementById("longterm").innerHTML = data.longterm;
     			document.getElementById("temp").innerHTML = data.temp;
     			document.getElementById("into").innerHTML = data.into;
     			document.getElementById("out").innerHTML = data.out;
     			document.getElementById("auto").innerHTML = data.auto;
     			document.getElementById("cash").innerHTML = data.cash;
     			document.getElementById("force").innerHTML = data.force;
     			document.getElementById("order").innerHTML = data.order;
     		}
    	});
    }
  
    $("a").click(function(){  	
    	var select = document.createElement("div");
        var span1 = document.createElement("span");
        var span2 = document.createElement("span");
        var getid = event.srcElement.id;
    	span1.innerHTML=this.innerHTML;
    	span1.setAttribute("id",getid);
    	span2.setAttribute("class", "glyphicon glyphicon-remove");
    	span2.setAttribute("width", "0.8em");
    	span2.setAttribute("onclick","delet(this)");	
    	select.setAttribute("id","a1");
    	select.appendChild(span1);	
    	select.appendChild(span2);
    	select.setAttribute("class", "select");
        document.getElementById("clear").appendChild(select);	  	     
        query();
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
        var getid = span1.innerHTML;
        span1.setAttribute("id","time"+getid);
        span2.setAttribute("class", "glyphicon glyphicon-remove");
    	span2.setAttribute("width", "0.8em");
    	span2.setAttribute("onclick","delet(this)");
    	select.appendChild(span1);	
    	select.appendChild(span2);	
    	select.setAttribute("class", "select");
        document.getElementById("clear").appendChild(select);  
        query();
    }
    
    function delet(e){	
    	var par=e.parentNode;
    	var c=document.getElementById("clear");
    	c.removeChild(par);
    	query();
    	
    }
    
    function query(){
    	
			
    	//每次选择条件后，检测已选条件，进行查询
    	var query = $(".select");  
        var se = "";
        for (var i=0;i<query.length;i++){
     	  var span3 = query[i].getElementsByTagName("span");
     	  se = se+"/"+span3[0].id;
        }
        $.ajax({
     		url:"car_query",//跟@RequestMapping(value="/")一样
     		type:"post",
     		async:true,
     		dataType:"json",
     		data:{condition:se},
     		success:function(data){
     			var table = $("#parkiorecord").DataTable();
    			table.clear();
    			for(var i=0;i<data.length;i++){		    	 
    		    	 var infolist = document.createElement("tr");
    		    	 var CarLicense = document.createElement("td");
    		    	 var TimeIn = document.createElement("td");
    		    	 var TimeOut = document.createElement("td");
    		    	 var CarportId = document.createElement("td");
    		    	 var ExitType = document.createElement("td");
    		    	 CarLicense.innerHTML = data[i].CarLicense;
    		    	 TimeIn.innerHTML = data[i].TimeIn;
    		    	 TimeOut.innerHTML = data[i].TimeOut;
    		    	 CarportId.innerHTML = data[i].CarportId;
    		    	 ExitType.innerHTML = data[i].ExitType;
    		    	 infolist.appendChild(CarLicense);
    		    	 infolist.appendChild(TimeIn);
    		    	 infolist.appendChild(TimeOut);
    		    	 infolist.appendChild(CarportId);
    		    	 infolist.appendChild(ExitType);
    		    	 table.row.add(infolist).draw();
    		     }	
    			
    			
     		}
        });

        
    }
    
   $("#menuup").click(function(){

    	$(".condition-list").slideUp(100,
    			function(){ 	
    		$("#menuup").hide();
    		$("#menudown").show();
    	});
    	document.getElementById("info").style.height="79%"; 
    	$("#parkiorecord").DataTable().page.len(10).draw();
    });
    
    $("#menudown").click(function(){ 	
    	$(".condition-list").slideDown(100,
    	function (){
    		
    		$("#menuup").show();
    		$("#menudown").hide();
    	});
    	document.getElementById("info").style.height="52%";  
    	$("#parkiorecord").DataTable().page.len(5).draw();
    });
    
    function carsearch(){
	    $("#search").submit(); 
    }
    
  </script>
  </body>
</html>