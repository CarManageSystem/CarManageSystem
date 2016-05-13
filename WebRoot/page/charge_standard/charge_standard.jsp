<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>收费标准</title>

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
      <li class="active"><b>收费标准</b></li>
    </ol>
  </div>
  
  <div class="container">
    <div class="row">
      <div class="col-xs-3 select">
        <div style="margin-top:10px;font-size:1.1em">
          <ul class="nav nav-pills nav-stacked">
            <li class="active"><a onclick="permit(this)">收费许可</a></li>
            <li><a onclick="temp(this)">临时车辆</a></li>
            <li><a onclick="yearall(this)">年度全时车辆</a></li>
            <li><a onclick="yeardiv(this)">年度分时车辆</a></li>
            <li><a onclick="quarter(this)">季度全时车辆</a></li>
            <li><a onclick="month(this)">月度全时车辆</a></li>
            <li><a onclick="tempfixed(this)">临时定期车辆</a></li>
            <li><a onclick="firm(this)">A类企业车辆</a></li>
            <li><a onclick="merchant(this)">B类商户车辆</a></li>
          </ul>
        </div>
        <button class="btn btn-default" type="button" style="width:260px;margin-top:60px;background:#337ab7;color:#ffffff;font-size:1.1em;text-align:left;padding:10px" onclick="add()">+ 添加收费方式</button>
      </div>
      <div class="col-xs-9 display">
      </div>
    </div>
  </div>
  
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>  
  
  <script type="text/javascript">
  function add(){
	  alert("添加收费方式！");
  }
  function permit(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("收费许可!");
  }
  
  function temp(e){
	  $(".display").html("");
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $.ajax({
   		url:"temp",//跟@RequestMapping(value="/")一样
   		type:"post",
   		async:false,
   		dataType:"json",
   		data:{},
   		success:function(data){
   			if(data.freetime!="0"){
   				var div1 = document.createElement("div");
   		        var div2 = document.createElement("div");
   		        div1.setAttribute("style", "height:85%;margin-top:20px;font-size:1.1em;padding-top:10px");
   		        div2.setAttribute("class", "col-xs-10 standard");
   		        div2.setAttribute("style", "margin-left:210px");
   		        var ArraySpan = new Array(16);
   		        for(var i=0;i<16;i++){
   		        	ArraySpan[i] = document.createElement("span");
   		        	}
   		        ArraySpan[0].innerHTML = "免费停车时间：进场后"+data.freetime+"min内离场";
   		        ArraySpan[1].innerHTML = "计费周期：白天"+data.day_unit+"min计费一次，夜间"+data.night_unit+"min计费一次";
   		        ArraySpan[2].innerHTML = "小型车：";
   		        ArraySpan[3].innerHTML = "工作日（周一至周五）：";
   		        ArraySpan[4].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） "+data.swi_day_fee+"元/"+data.day_unit+"min";
   		        ArraySpan[4].setAttribute("style","padding-left:60px");
   		        ArraySpan[5].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.swi_night_fee+"元/"+data.night_unit+"min";
   		        ArraySpan[5].setAttribute("style","padding-left:60px");
   		        ArraySpan[6].innerHTML = "休息日（周六、日）：";
   		        ArraySpan[7].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） "+data.sri_day_fee+"元/"+data.day_unit+"min";
   		        ArraySpan[7].setAttribute("style","padding-left:60px");
   		        ArraySpan[8].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.sri_night_fee+"元/"+data.night_unit+"min";
   		        ArraySpan[8].setAttribute("style","padding-left:60px");
   		        ArraySpan[9].innerHTML = "大型车：";
   		        ArraySpan[10].innerHTML = "工作日（周一至周五）：";
   		        ArraySpan[11].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） "+data.bwi_day_fee+"元/"+data.day_unit+"min";
   		        ArraySpan[11].setAttribute("style","padding-left:60px");
   		        ArraySpan[12].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.bwi_night_fee+"元/"+data.night_unit+"min";
   		        ArraySpan[12].setAttribute("style","padding-left:60px");
   		        ArraySpan[13].innerHTML = "休息日（周六、日）：";
   		        ArraySpan[14].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） "+data.bri_day_fee+"元/"+data.day_unit+"min";
   		        ArraySpan[14].setAttribute("style","padding-left:60px");
   		        ArraySpan[15].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.bri_night_fee+"元/"+data.night_unit+"min";
   		        ArraySpan[15].setAttribute("style","padding-left:60px");
   		        for(var i=0;i<16;i++){
   		        	div2.appendChild(ArraySpan[i]);
   		        	}
   		        div1.appendChild(div2);
   		        $(".display").append(div1);
   		        }else{
   		        	var div1 = document.createElement("div");
   	   		        var div2 = document.createElement("div");
   	   		        div1.setAttribute("style", "height:85%;margin-top:20px;font-size:1.1em;padding-top:10px");
   	   		        div2.setAttribute("class", "col-xs-10 standard");
   	   		        var ArraySpan = new Array(16);
   	   		        for(var i=0;i<16;i++){
   	   		        	ArraySpan[i] = document.createElement("span");
   	   		        	}
   	   		        ArraySpan[0].innerHTML = "免费停车时间：无";
   	   		        ArraySpan[1].innerHTML = "计费周期：白天"+data.day_unit+"min计费一次，夜间"+data.night_unit+"min计费一次";
   	   		        ArraySpan[2].innerHTML = "小型车：";
   	   		        ArraySpan[3].innerHTML = "工作日（周一至周五）：";
   	   		        ArraySpan[4].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） 首小时内"+data.swi_day_fee+"元/"+data.day_unit+"min，首小时后"+data.swo_day_fee+"元/"+data.day_unit+"min";
   	   		        ArraySpan[4].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[5].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.swi_night_fee+"元/"+data.night_unit+"min";
   	   		        ArraySpan[5].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[6].innerHTML = "休息日（周六、日）：";
   	   		        ArraySpan[7].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） 首小时内"+data.sri_day_fee+"元/"+data.day_unit+"min，首小时后"+data.sro_day_fee+"元/"+data.day_unit+"min";
   	   		        ArraySpan[7].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[8].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.sri_night_fee+"元/"+data.night_unit+"min";
   	   		        ArraySpan[8].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[9].innerHTML = "大型车：";
   	   		        ArraySpan[10].innerHTML = "工作日（周一至周五）：";
   	   		        ArraySpan[11].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） 首小时内"+data.bwi_day_fee+"元/"+data.day_unit+"min，首小时后"+data.bwo_day_fee+"元/"+data.day_unit+"min";
   	   		        ArraySpan[11].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[12].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.bwi_night_fee+"元/"+data.night_unit+"min";
   	   		        ArraySpan[12].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[13].innerHTML = "休息日（周六、日）：";
   	   		        ArraySpan[14].innerHTML = "白天（"+data.day_start+"-"+data.day_end+"） 首小时内"+data.bri_day_fee+"元/"+data.day_unit+"min，首小时后"+data.bro_day_fee+"元/"+data.day_unit+"min";
   	   		        ArraySpan[14].setAttribute("style","padding-left:30px");
   	   		        ArraySpan[15].innerHTML = "夜间（"+data.day_end+"-"+data.day_start+"） "+data.bri_night_fee+"元/"+data.night_unit+"min";
   	   		        ArraySpan[15].setAttribute("style","padding-left:30px");
   	   		        for(var i=0;i<16;i++){
   	   		        	div2.appendChild(ArraySpan[i]);
   	   		        	}
   	   		        div1.appendChild(div2);
   	   		        $(".display").append(div1);
   		        	
   				
   		        }
   		}
   		});
	  
	  var div3 = document.createElement("div");
	  div3.setAttribute("class", "col-xs-4 col-xs-offset-4");
	  div3.setAttribute("style", "height:8%;");
	  div3.setAttribute("align", "center");
	  var bt1 = document.createElement("input");
	  var bt2 = document.createElement("input");
	  bt1.type = "button";
	  bt1.value = "编辑";
	  bt1.style.width = "90px";
	  bt1.style.background = "#337ab7";
	  bt1.style.color = "#ffffff";
	  bt1.style.border = "none";
	  bt1.style.height = "30px";
	  bt1.style.float = "left";
	  bt1.style.borderRadius = "3px";
	  bt1.onclick = function edit() {
		  top.location = "standard_set.action";
		  return false;
	  };
      bt2.type = "button";
	  bt2.value = "删除";
	  bt2.style.width = "90px";
	  bt2.style.background = "#337ab7";
	  bt2.style.color = "#ffffff";
	  bt2.style.border = "none";
	  bt2.style.height = "30px";
	  bt2.style.float = "right";
	  bt2.style.borderRadius = "3px";
	  bt2.onclick = function del(){alert("删除");};
	  div3.appendChild(bt1);
	  div3.appendChild(bt2);
	  $(".display").append(div3);  
  }
  
  
  
  function yearall(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  $.ajax({
	   		url:"yearall",//跟@RequestMapping(value="/")一样
	   		type:"post",
	   		async:false,
	   		dataType:"json",
	   		data:{},
	   		success:function(data){
	   			var div1 = document.createElement("div");
   		        var div2 = document.createElement("div");
   		        div1.setAttribute("style", "height:80%;margin-top:20px;font-size:1.2em;padding-top:10px");
   		        div2.setAttribute("class", "col-xs-10 standard");
   		        //div2.setAttribute("style", "margin-left:300px");
   		        //div2.setAttribute("align", "center");
   		        var h3 = document.createElement("h3");
   		        var span1 = document.createElement("span");
   		        var span2 = document.createElement("span");
   		        var span3 = document.createElement("span");
   		        h3.innerHTML = "全时车辆收费标准";
   		        h3.setAttribute("style","margin-bottom:24px;");
   		        span1.innerHTML = "年度全时费率："+data.yearall+"元";
   		        span1.setAttribute("style","padding-top:10px;padding-left:20px;");
   		        span2.innerHTML = "季度全时费率："+data.quarter+"元";
   		        span2.setAttribute("style","padding-top:20px;padding-left:20px;");
   		        span3.innerHTML = "月度全时费率："+data.month+"元";
   		        span3.setAttribute("style","padding-top:20px;padding-left:20px;");
   		        div2.appendChild(h3);
   		        div2.appendChild(span1);
   		        div2.appendChild(span2);
   		        div2.appendChild(span3);
		        div1.appendChild(div2);
		        $(".display").append(div1);
	   		}
	  });
	  var div3 = document.createElement("div");
	  div3.setAttribute("class", "col-xs-4 col-xs-offset-4");
	  div3.setAttribute("style", "height:8%;");
	  div3.setAttribute("align", "center");
	  var bt1 = document.createElement("input");
	  var bt2 = document.createElement("input");
	  bt1.type = "button";
	  bt1.value = "编辑";
	  bt1.style.width = "90px";
	  bt1.style.background = "#337ab7";
	  bt1.style.color = "#ffffff";
	  bt1.style.border = "none";
	  bt1.style.height = "30px";
	  bt1.style.float = "left";
	  bt1.style.borderRadius = "3px";
	  bt1.onclick = function edit() {
		  top.location = "cardfee_set.action";
		  return false;
	  };
      bt2.type = "button";
	  bt2.value = "删除";
	  bt2.style.width = "90px";
	  bt2.style.background = "#337ab7";
	  bt2.style.color = "#ffffff";
	  bt2.style.border = "none";
	  bt2.style.height = "30px";
	  bt2.style.float = "right";
	  bt2.style.borderRadius = "3px";
	  bt2.onclick = function del(){alert("删除");};
	  div3.appendChild(bt1);
	  div3.appendChild(bt2);
	  $(".display").append(div3);  
  }
	  

  
  function yeardiv(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("年度分时车辆！");
  }
  
  function quarter(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("季度全时车辆！");
  }
  
  function month(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("月度全时车辆！");
  }
  
  function tempfixed(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("临时定期车辆");
  }
  
  function firm(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("A类企业车辆");
  }
  
  function merchant(e){
	  var a1 = $(".active");
	  a1.removeClass("active");
	  var a2 = e.parentNode;
	  a2.setAttribute("class","active");
	  $(".display").html("");
	  alert("B类商户车辆！");
  }
  </script>
  
  </body>
</html>
