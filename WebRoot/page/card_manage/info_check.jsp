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
    
    <title>新卡信息审批</title>
    
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
        <div class="col-xs-8 middle">
          <div style="margin-top:15px;margin-left:50px;height:460px;overflow:auto;">
            <table class="table table-bordered" style="font-size:1em;width:670px;background:#f8f8f8;text-align:center">
              <tbody>  	
                <tr><td colspan=4 style="font-size:1.2em;text-align:center;background:#337ab7;color:#ffffff;border:2px solid #a9a9a9"><b>新卡信息</b></td></tr>
                <tr>
                  <td><b>新卡类型</b></td>
                  <c:if test="${card.cardType == 0}">
   			        <td>年度卡</td>
		          </c:if>
		          <c:if test="${card.cardType == 1}">
   			        <td>季度卡</td>
		          </c:if>
		          <c:if test="${card.cardType == 2}">
   			        <td>月度卡</td>
		          </c:if>
                  <td><b>新卡编号</b></td>         
   			      <td>${card.cardNum}</td>
                </tr>
                <tr>
                  <td><b>申请房间号</b></td>
                  <td>${card.applyRoomNum}</td>
                  <td><b>与业主关系</b></td>    
                  <c:if test="${card.relOwner == 0}">
   			        <td>业主</td>
		          </c:if> 
		          <c:if test="${card.relOwner == 1}">
   			        <td>租户</td>
		          </c:if>     
   			      <c:if test="${card.relOwner == 2}">
   			        <td>商场</td>
		          </c:if>
		          <c:if test="${card.relOwner == 3}">
   			        <td>办公</td>
		          </c:if>
                </tr>
                <tr>
                  <td><b>房间已有车辆</b></td>
                  <td>${card.carsHad}</td>
                  <td><b>能否申请</b></td>         
   			      <c:if test="${card.apply == 0}">
   			        <td>能</td>
		          </c:if>
		          <c:if test="${card.apply == 1}">
   			        <td>不能</td>
		          </c:if>
                </tr>
                <tr>
                  <td><b>业主姓名</b></td>
                  <td>${card.nameOwner}</td>
                  <td><b>联系方式</b></td>         
   			      <td>${card.telOwner}</td>
                </tr>
                <tr>
                  <td><b>起始时间</b></td>
                  <td>${card.timeStart}</td>
                  <td><b>终止时间</b></td>         
   			      <td>${card.timeEnd}</td>
                </tr>
                <tr>
                  <td><b>车位类型</b></td>
                  <td>${card.carportType}</td>
                  <td><b>固定车位编号</b></td>         
   			      <td>${card.carportNum}</td>
                </tr>
                <tr>
                  <td><b>支付金额</b></td>
                  <td>${card.payMoney}元</td>
                  <td><b>支付时间</b></td>         
   			      <td>${card.payTime}</td>
                </tr>
                <tr>
                  <td><b>申请人</b></td>
                  <td>${card.nameApply}</td>
                  <td><b>联系方式</b></td>         
   			      <td>${card.telApply}</td>
                </tr>
                <tr>
                  <td><b>是否开具发票</b></td>
                  <c:if test="${card.invoice == 0}">
   			        <td>是</td>
		          </c:if>
		          <c:if test="${card.invoice == 1}">
   			        <td>否</td>
		          </c:if>
                  <td><b>发票抬头</b></td>         
   			      <td>${card.title}</td>
                </tr>
                <tr>
                  <td><b>邮寄地址</b></td>
                  <td colspan=3>${card.address}</td>
                </tr>
                <tr>
                  <td><b>发票是否邮寄</b></td>
                  <c:if test="${card.post == 0}">
   			        <td>是</td>
		          </c:if>
		          <c:if test="${card.post == 1}">
   			        <td>否</td>
		          </c:if>
                  <td><b>运费金额</b></td>         
   			      <td>${card.freight}元</td>
                </tr>
                <tr><td colspan=4 style="font-size:1.2em;text-align:center;background:#337ab7;color:#ffffff;border:2px solid #a9a9a9"><b>车辆信息</b></td></tr>
                <tr>
                  <td><b>车牌号码</b></td>
                  <td>${car.carLicense}</td>
                  <td><b>车主姓名</b></td>         
   			      <td>${car.ownerName}</td>
                </tr>
                <tr>
                  <td><b>车辆类型</b></td>
                  <td>${car.carType}</td>
                  <td><b>使用性质</b></td>         
   			      <td>出租</td>
                </tr>
                <tr>
                  <td><b>发证日期</b></td>
                  <td>${car.carIssueDate}</td>
                  <td><b>注册日期</b></td>         
   			      <td>${car.initialDate}</td>
                </tr>
                <tr>
                  <td><b>发动机号</b></td>
                  <td>${car.engineNumber}</td>
                  <td><b>检验有效期</b></td>         
   			      <td>${car.carValid}</td>
                </tr>
                <tr>
                  <td><b>品牌型号</b></td>
                  <td>${car.carBrand}</td>
                  <td><b>车辆识别代码</b></td>         
   			      <td>${car.carCode}</td>
                </tr>
                <tr><td colspan=4 style="font-size:1.2em;text-align:center;background:#337ab7;color:#ffffff;border:2px solid #a9a9a9"><b>车主信息</b></td></tr>
                <tr>
                  <td><b>车主姓名</b></td>
                  <td>${car.ownerName}</td>
                  <td><b>性别</b></td>         
   			      <c:if test="${car.ownerSex == 1}">
   			        <td>男</td>
		          </c:if>
		          <c:if test="${car.ownerSex == 0}">
   			        <td>女</td>
		          </c:if>
                </tr>
                <tr>
                  <td><b>驾驶证号</b></td>
                  <td>${car.drivingLicense}</td>
                  <td><b>国籍</b></td>         
   			      <td>${car.nation}</td>
                </tr>
                <tr>
                  <td><b>出生日期</b></td>
                  <td>${car.ownerBirthday}</td>
                  <td><b>驾照类型</b></td>         
   			      <td>${car.drivingLicenseType}</td>
                </tr>
                <tr>
                  <td><b>初次领证日期</b></td>
                  <td>${car.licenseIssueDate}</td>
                  <td><b>有效起始日期</b></td>         
   			      <td>${car.validStartDate}</td>
                </tr>
                <tr>
                  <td><b>有效期</b></td>
                  <td>${car.validTerm}</td>
                  <td><b>联系方式</b></td>         
   			      <td>${car.ownerTel}</td>
                </tr>
                <tr>
                  <td><b>驾驶证住址</b></td>
                  <td colspan=3>${car.ownerAddress}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div align="center">
              <input type="submit" style="width:80px;height:30px;line-height:15px;margin-top:30px;margin-left:25px;background:#337ab7;color:#ffffff;border-radius:5px;border:none" value="同意" onclick="save()"/>
              <button style="width:80px;height:30px;line-height:15px;margin-top:30px;margin-left:30px;background:#337ab7;color:#ffffff;border-radius:5px;border:none">不同意</button>
          </div>
        </div>
      </div>
    </div>
    <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
    
  </body>
</html>
