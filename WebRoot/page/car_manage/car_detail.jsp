<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>车辆详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
  
  </head>
  <body>
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  <div style="margin-top:0px;height:15px;font-size:0.9em;">
    <ol class="breadcrumb" style="padding:0px 15px;background:#ffffff">
      <li  style="margin-left:60px;"><a href="house.action"><b>首页</b></a></li>
      <li><a href="car_records.action"><b>车辆管理</b></a></li>
      <li class="active"><b>车辆详情</b></li>
    </ol>
  </div>
  <center>
  
  <div class="container" style="height:500px;overflow:auto;">
  <div class="row" id="carlicense" style="font-size:1.2em;margin-top:15px;margin-bottom:10px;line-height:25px;padding-left:20px;height:30px;width:800px;border:2px solid #a9a9a9;text-align:left;background:#ebebeb;">
               车牌号
  </div>  
    <div class="row" id="longterm">
      <table class="table table-bordered" style="margin-bottom:10px;font-size:1em;width:800px;background:#f8f8f8;text-align:center">
        <tr>
          <td><b>姓名</b></td>
          <td id="ownername"></td>
          <td><b>年龄</b></td>
          <td id="ownerage"></td>
          <td><b>性别</b></td>
          <td id="ownersex"></td>        
          <td rowspan=5><img style="padding-top:15px;width:100px;height:150px" src="/CarManageSystem/images/car1.jpg"></td>
        </tr>
        <tr>
          <td><b>地址</b></td>
          <td id="owneraddress" colspan=3></td>
          <td><b>联系电话</b></td>         
   		  <td id="ownertel"></td>          
        </tr>
        <tr>
          <td><b>汽车品牌</b></td>
          <td id="carbrand">XXX</td>
          <td><b>款式</b></td>         
   		  <td id="cartype">XXX</td>
          <td><b>颜色</b></td>
          <td id="carcolor">白</td>        
        </tr>
        <tr>
          <td><b>出厂日期</b></td>
          <td id="productiondate">XXX</td>
          <td><b>初登日期</b></td>
          <td id="initialdate">XXX</td>    
          <td><b>行驶里程</b></td>
          <td id="cardistance">XXX</td>          
        </tr>
        <tr>
          <td><b>发动机号</b></td>
          <td id="enginenumber">XXX</td>
          <td><b>车架号</b></td>
          <td id="identifictionnumber">XXX</td>  
          <td><b>排量</b></td>
          <td id="outputvolume">XXX</td>              
        </tr>
        
      </table>
    </div>
    <div class="row" >
    <table  class="table table-hover" style="border:2px solid #dcdcdc;font-size:1em;width:800px;background:#f8f8f8;text-align:center">
      <thead>
        <tr>
          <th>入场时间</th>
          <th>出场时间</th>
          <th>入场照片</th>
          <th>出场照片</th>
          <th>车位号</th>
          <th>出口类型</th> 
          <th>预约</th>  
          <th>放行标准</th>     
        </tr>
      </thead>
      <tbody id="records">
        
      </tbody>
    </table>
    </div>
  </div>
  <button class="btn btn-default" style="margin-top:15px;background-color:#D5ADD8;" onclick="back()"><b>返 回</b></button>
  </center>
  
  <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  <script type="text/javascript">
  $("#carlicense").html(<%=request.getAttribute("CarLicense")%>);
  
  $(document).ready(function(){
	  var cl = <%=request.getAttribute("CarLicense")%>;
	//如果是长期车辆返回详细车辆信息，否则隐藏表格
	  $.ajax({
   		url:"longterm_detail",
   		type:"post",
   		async:true,
   		dataType:"json",
   		data:{CarLicense:cl},
   		success:function(data){
   			if(data.type==1){
   				document.getElementById("ownername").innerHTML=data.OwnerName;
   				document.getElementById("ownerage").innerHTML=data.OwnerAge;
   				document.getElementById("ownersex").innerHTML=data.OwnerSex;
   				document.getElementById("owneraddress").innerHTML=data.OwnerAddress;
   				document.getElementById("ownertel").innerHTML=data.OwnerTel;
   				document.getElementById("carbrand").innerHTML=data.CarBrand;
   				document.getElementById("carcolor").innerHTML=data.CarColor;
   				document.getElementById("cartype").innerHTML=data.CarType;
   				document.getElementById("productiondate").innerHTML=data.ProductionDate;
   				document.getElementById("initialdate").innerHTML=data.InitialDate;
   				document.getElementById("cardistance").innerHTML=data.CarDistance;
   				document.getElementById("enginenumber").innerHTML=data.EngineNumber;
   				document.getElementById("identifictionnumber").innerHTML=data.IdentifictionNumber;
   				document.getElementById("outputvolume").innerHTML=data.OutputVolume; 				
   			}else{
   				var e=document.getElementById("longterm");
   		    	var par=e.parentNode;
   		    	par.removeChild(e);
   			}
   			
   		}
	  });
	  //ajax返回停车记录
	  $.ajax({
   		url:"park_records",
   		type:"post",
   		async:true,
   		dataType:"json",
   		data:{CarLicense:cl},
   		success:function(data){
   			for(var i=0;i<data.length;i++){
   			var infolist = document.createElement("tr");
	    	var TimeIn = document.createElement("td");
	    	var TimeOut = document.createElement("td");
	    	var PhotolocIn = document.createElement("td");
			var PhotolocOut = document.createElement("td");
	    	var CarportId = document.createElement("td");
	    	var ExitType = document.createElement("td");
	    	var OrderFlag = document.createElement("td");
			var PassType = document.createElement("td");
	    	TimeIn.innerHTML = data[i].TimeIn;
	    	TimeOut.innerHTML = data[i].TimeOut;
	    	PhotolocIn.innerHTML = data[i].PhotolocIn;
	    	PhotolocOut.innerHTML = data[i].PhotolocOut;
	    	CarportId.innerHTML = data[i].CarportId;
	    	ExitType.innerHTML = data[i].ExitType;
	    	OrderFlag.innerHTML = data[i].OrderFlag;
			PassType.innerHTML = data[i].PassType;        
	    	infolist.appendChild(TimeIn);
	    	infolist.appendChild(TimeOut);
	    	infolist.appendChild(PhotolocIn);
	    	infolist.appendChild(PhotolocOut);
	    	infolist.appendChild(CarportId);
	    	infolist.appendChild(ExitType);
	    	infolist.appendChild(OrderFlag);
	    	infolist.appendChild(PassType);
	    	$("#records").append(infolist);
   			}
   		}
  	});
	  
  });
  
  function back(){
	  top.location = "car_records.action"; 
	  return false;
  }
  </script>
  </body>
  </html>
