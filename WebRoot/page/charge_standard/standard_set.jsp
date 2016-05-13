<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设置收费标准</title>

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
            <li class="active"><a>临时车辆</a></li>
            <li><a>年度全时车辆</a></li>
            <li><a>年度分时车辆</a></li>
            <li><a>季度全时车辆</a></li>
            <li><a>月度全时车辆</a></li>
            <li><a>临时定期车辆</a></li>
            <li><a>A类企业车辆</a></li>
            <li><a>B类商户车辆</a></li>
          </ul>
        </div>                                            
      </div>
 
     <div class="col-xs-9 display">  
       <div class="col-xs-8 col-xs-offset-2">
       <h3 align="center">临时车辆收费标准</h3>
       
       <form action="/CarManageSystem/ChargeRule_update.action" method="post" id="ChargeRuleUpdate" onsubmit="return save()">
       <div class="row" style="width:600px;margin-top:20px">
         <div class="col-xs-6">
           <span>跨段分界起点：</span>
           <input type="text" name="day_start" id="day_start" style="width:100px;border:none" placeholder="07:00" onchange="TimeVal(this)"/>
         </div>
         <div class="col-xs-6">
           <span>跨段分界终点：</span>
           <input type="text" name="day_end" id="day_end" style="width:100px;border:none" placeholder="21:00" onchange="TimeVal(this)"/>
         </div>
       </div>
       <div class="row" style="width:600px;margin-top:15px">
         <div class="col-xs-6">
           <span>白天计费单位：</span>
           <input type="text" name="day_unit" id="day_unit" style="width:100px;border:none" onchange="dayunit(this)"/>
           <span>min</span>
         </div>
         <div class="col-xs-6">
           <span>晚上计费单位：</span>
           <input type="text" name="night_unit" id="night_unit" style="width:100px;border:none" onchange="nightunit(this)"/>
           <span>min</span>
         </div>
       </div>
       <div class="row" style="width:600px;margin-top:15px">
         <div class="col-xs-6">
           <input type="radio" name="rule" id="free" value="free" checked="checked" onclick="FreeTime()"/>有免费时长
         </div>
         <div class="col-xs-6">
           <input type="radio" name="rule" id="first" value="firsthour" onclick="FirstHour()"/>有首小时
         </div>
       </div>
       <!-- 有免费时长的表格 -->
       <div class="row" id="tb_freetime" style="width:550px;margin-top:15px;" >
         <div style="margin-bottom:15px;margin-left:10px;">
           <span style="margin-left:10px;">免费时长：</span>
           <input type="text" name="freetime" style="width:100px;border:none"/>
           <span>min</span>
         </div>
         <table class="table table-bordered" >      
             <tr align="center">
               <td>收费期间</td>
               <td>车型</td>
               <td align="center">白天(元/<span id="unit1">0</span>min)</td>
               <td>晚上(元/<span id="unit2">0</span>min)</td>     
             </tr>
             <tr align="center">
               <td rowspan="2" style="line-height:60px">工作日</td>
               <td>大型车</td>
               <td><input type="text" name="bwi_day_fee" style="width:100px;border:none"/></td>
               <input type="hidden" name="bwo_day_fee" value="0.0"/>
               <td><input type="text" name="bwi_night_fee" style="width:100px;border:none"/></td>
             </tr>
             <tr align="center">
               <td>小型车</td>
               <td><input type="text" name="swi_day_fee" style="width:100px;border:none"/></td>
               <input type="hidden" name="swo_day_fee" value="0.0"/>
               <td><input type="text" name="swi_night_fee" style="width:100px;border:none"/></td>
             </tr>
             <tr align="center">
               <td rowspan="2" align="center" style="line-height:60px">休息日</td>
               <td>大型车</td>
               <td><input type="text" name="bri_day_fee" style="width:100px;border:none"/></td>
               <input type="hidden" name="bro_day_fee" value="0.0"/>
               <td><input type="text" name="bri_night_fee" style="width:100px;border:none"/></td>
             </tr>
             <tr align="center">
               <td>小型车</td>
               <td><input type="text" name="sri_day_fee" style="width:100px;border:none"/></td>
               <input type="hidden" name="sro_day_fee" value="0.0"/>
               <td><input type="text" name="sri_night_fee" style="width:100px;border:none"/></td>
             </tr>
         </table>
       </div>
       <!-- 有首小时的表格 -->
       <div class="row" id="tb_firsthour" style="width:550px;margin-top:15px;display:none;" >
         <input type="hidden" name="freetime" value="0" disabled="disabled"/>
         <table class="table table-bordered" >      
             <tr align="center">
               <td rowspan="2" style="line-height:60px">收费期间</td>
               <td rowspan="2" style="line-height:60px">车型</td>
               <td colspan="2" align="center">白天(元/<span id="unit3">0</span>min)</td>
               <td rowspan="2" style="line-height:60px">晚上(元/<span id="unit4">0</span>min)</td>     
             </tr>
             <tr align="center">
               <td>首小时内</td>
               <td>首小时后</td>
             </tr>
             <tr align="center">
               <td rowspan="2" style="line-height:60px">工作日</td>
               <td>大型车</td>
               <td><input type="text" name="bwi_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="bwo_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="bwi_night_fee" style="width:100px;border:none" disabled="disabled"/></td>
             </tr>
             <tr align="center">
               <td>小型车</td>
               <td><input type="text" name="swi_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="swo_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="swi_night_fee" style="width:100px;border:none" disabled="disabled"/></td>
             </tr>
             <tr align="center">
               <td rowspan="2" align="center" style="line-height:60px">休息日</td>
               <td>大型车</td>
               <td><input type="text" name="bri_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="bro_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="bri_night_fee" style="width:100px;border:none" disabled="disabled"/></td>
             </tr>
             <tr align="center">
               <td>小型车</td>
               <td><input type="text" name="sri_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="sro_day_fee" style="width:100px;border:none" disabled="disabled"/></td>
               <td><input type="text" name="sri_night_fee" style="width:100px;border:none" disabled="disabled"/></td>
             </tr>
         </table> 
       </div>
       <div align="center">
           <input type="submit" value="保存" style="margin-left:40px;margin-top:20px;width:90px;background:#337ab7;color:#ffffff;border:none;height:30px;border-radius:3px" />
           <input type="button" value="返回" style="margin-left:80px;margin-top:20px;width:90px;background:#337ab7;color:#ffffff;border:none;height:30px;border-radius:3px" onclick="back()"/>
       </div>
       
       </form>
       </div>
     </div>
     
    </div>  
  </div>
    
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>  
  
  <script type="text/javascript">
  function FreeTime() {
	  var tbfree = document.getElementById("tb_freetime");
	  var tbfirst = document.getElementById("tb_firsthour");
	  tbfree.style.display = "block";
	  tbfirst.style.display = "none";   
	  var inputs1 = tbfirst.getElementsByTagName("INPUT"); 
	  for(var i=0;i<inputs1.length;i++) {   
		  inputs1[i].setAttribute("disabled","true"); 
      }
	  var inputs2 = tbfree.getElementsByTagName("INPUT"); 
	  for(var i=0;i<inputs2.length;i++) {   
		  inputs2[i].removeAttribute("disabled"); 
      }
  }
  
  function FirstHour() { 
	  var tbfree = document.getElementById("tb_freetime");
	  var tbfirst = document.getElementById("tb_firsthour");
	  tbfree.style.display = "none";
	  tbfirst.style.display = "block";  
	  var inputs1 = tbfree.getElementsByTagName("INPUT"); 
	  for(var i=0;i<inputs1.length;i++) {   
		  inputs1[i].setAttribute("disabled","true"); 
      }
	  var inputs2 = tbfirst.getElementsByTagName("INPUT"); 
	  for(var i=0;i<inputs2.length;i++) {   
		  inputs2[i].removeAttribute("disabled"); 
      }
  }
  
  function dayunit(e) {
	  var unit1 = document.getElementById("unit1");
	  var unit2 = document.getElementById("unit3");
	  unit1.innerHTML = e.value;
	  unit2.innerHTML = e.value;
  }
  function nightunit(e) {
	  var unit1 = document.getElementById("unit2");
	  var unit2 = document.getElementById("unit4");
	  unit1.innerHTML = e.value;
	  unit2.innerHTML = e.value;  
  }
  
  function TimeVal(e) {
	  
	  var reg = /([01]\d|2[0-3])(:[0-5]\d)/;
	  var result = e.value.match(reg);
	  e.setAttribute("style","width:100px;border:none");
	  if (result == null) {
		  alert("请正确填写，格式为：HH:mm(00:00~23:59)");
	      e.setAttribute("style","width:100px;border:1px solid red");
	      e.focus();
	  }
  }
  
  function save() {
	  var free = document.getElementById("free");
	  var flag = 0;
	  var inputs1 = new Array(4);
	  inputs1[0] = document.getElementById("day_start");
	  inputs1[1] = document.getElementById("day_end");
	  inputs1[2] = document.getElementById("day_unit");
	  inputs1[3] = document.getElementById("night_unit");
	  for(var i=0;i<4;i++) {
		  inputs1[i].setAttribute("style","width:100px;border:none");
		  if(inputs1[i].value == "") {  
			  inputs1[i].setAttribute("style","width:100px;border:1px solid red");
			  flag=1;
			  
		  }
	  }
	  if(free.checked) {
		  var tbfree = document.getElementById("tb_freetime");
		  var inputs2 = tbfree.getElementsByTagName("INPUT"); 
		  for(var i=0;i<inputs2.length;i++) {   
			  inputs2[i].setAttribute("style","width:100px;border:none");
			  if(inputs2[i].value == ""){
				  inputs2[i].setAttribute("style","width:100px;border:1px solid red");
			      flag = 1;      
			  }
	      }
	  } else {
		  var tbfirst = document.getElementById("tb_firsthour");
		  var inputs2 = tbfirst.getElementsByTagName("INPUT"); 
		  for(var i=0;i<inputs2.length;i++) {   
			  inputs2[i].setAttribute("style","width:100px;border:none");
			  if(inputs2[i].value == ""){
				  inputs2[i].setAttribute("style","width:100px;border:1px solid red");
			      flag = 1;      
			  }
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
