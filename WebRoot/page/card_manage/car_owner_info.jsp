<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>车主车辆信息</title>
    
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
          
            <form action="carownerinfo_update.action" method="post" id="CarOwnerInfo" name="CarOwnerInfo" class="form-horizontal" role="form">	
              <div class="row" style="font-size:1.0em;margin-top:10px;">
                <div style="margin-left:30px;font-size:1.2em" align="center">
                  <span><b>车主信息</b></span>
                </div>
                <input type="hidden" name="CardNum" id="CardNum" value="<%=request.getAttribute("CardNum")%>">
              <div class="col-xs-6">
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">车主姓名：</label>
  			      <div class="col-xs-7">
  			        <input type="text" class="form-control" name="OwnerName">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">性别：</label>
  			      <div class="col-xs-7">
  			        <label class="radio-inline">
                      <input checked="checked" type="radio" name="OwnerSex" value="0">
                                                       男
                    </label>  
                    <label class="radio-inline">
                      <input type="radio" name="OwnerSex" value="1">
                                                       女
                    </label>
  			      </div>
  			    </div>
  			    
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">国籍：</label>
  			      <div class="col-xs-7">
  			        <select class="form-control" name="Nation">
                      <option value="0">中国</option>
                      <option value="1">美国</option>
                      <option value="2">法国</option>
                      <option value="3">英国</option>
                    </select>
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">出生日期：</label>
  			      <div class="col-xs-7">
  			        <input type="date" class="form-control" name="OwnerBirthday">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">联系方式：</label>
  			      <div class="col-xs-7">
  			        <input type="text" class="form-control" name="OwnerTel">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">驾驶证住址：</label>
  			      <div class="col-xs-7">
  			        <input type="text" class="form-control" name="OwnerAddress">
  			      </div>
  			    </div>
  			  </div>
  			  <div class="col-xs-6">
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">驾驶证号：</label>
  			      <div class="col-xs-7">
  			        <input type="text" class="form-control" name="DrivingLicense">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">驾照类型：</label>
  			      <div class="col-xs-7">
  			        <input type="text" class="form-control" name="DrivingLicenseType">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">初次领证日期：</label>
  			      <div class="col-xs-7">
  			        <input type="date" class="form-control" name="LicenseIssueDate">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">有效起始日期：</label>
  			      <div class="col-xs-7">
  			        <input type="date" class="form-control" name="ValidStartDate">
  			      </div>
  			    </div>
  			    <div class="form-group">
  			      <label class="col-xs-5 control-label">有效期：</label>
  			      <div class="col-xs-7">
  			        <select class="form-control" name="ValidTerm">
                      <option value="0">6年</option>
                      <option value="1">10年</option>
                      <option value="2">长期</option>
                    </select>
  			      </div>
  			    </div>
  			    
  			    
              </div>
              </div>
              <div class="row">
                <div style="margin-left:30px;margin-top:10px;font-size:1.2em" align="center">
                  <span><b>车辆信息</b></span>
                </div>
                <div class="col-xs-6">
                  <div class="form-group">
  			        <label class="col-xs-5 control-label">车牌号码：</label>
  			        <div class="col-xs-7">
  			          <input type="text" class="form-control" name="CarLicense">
  			        </div>
  			      </div>
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">车辆类型：</label>
  			        <div class="col-xs-7">
  			          <input type="text" class="form-control" name="CarType">
  			        </div>
  			      </div>
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">品牌型号：</label>
  			        <div class="col-xs-7">
  			          <input type="text" class="form-control" name="CarBrand">
  			        </div>
  			      </div>
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">发动机号：</label>
  			        <div class="col-xs-7">
  			          <input type="text" class="form-control" name="EngineNumber">
  			        </div>
  			      </div>
  			      
                </div>
                <div class="col-xs-6">
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">发证日期：</label>
  			        <div class="col-xs-7">
  			          <input type="date" class="form-control" name="CarIssueDate">
  			        </div>
  			      </div>
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">注册日期：</label>
  			        <div class="col-xs-7">
  			          <input type="date" class="form-control" name="InitialDate">
  			        </div>
  			      </div>
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">检验有效期：</label>
  			        <div class="col-xs-7">
  			          <input type="date" class="form-control" name="CarValid">
  			        </div>
  			      </div>
  			      
  			      <div class="form-group">
  			        <label class="col-xs-5 control-label">车辆识别代码：</label>
  			        <div class="col-xs-7">
  			          <input type="text" class="form-control" name="CarCode">
  			        </div>
  			      </div>
                </div>
              </div>
            </form>
            <iframe style="display: none" id="CarOwnerInfo"  name="CarOwnerInfo" src="about:blank"></iframe>
            <div align="center">
              <input type="submit" style="width:80px;height:30px;line-height:15px;margin-bottom:5px;margin-left:30px;background:#337ab7;color:#ffffff;border-radius:5px;border:none" value="保存" onclick="save()"/>
              <button style="width:80px;height:30px;line-height:15px;margin-bottom:5px;margin-left:30px;background:#337ab7;color:#ffffff;border-radius:5px;border:none">返回</button>
            </div>
          
        </div>
        <div class="col-xs-2 right">
          <div style="margin-top:40px;margin-left:50px;font-family:FangSong;font-size:1.1em;text-align:center">
          <ul class="nav nav-pills nav-stacked" >
            <li><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">新卡信息</a></li>
            <li class="active" style="margin-top:0px"><a style="font-weight:700;border:1px solid #337ab7;">车主车辆</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">提交资料</a></li>
          </ul>
        </div>
        </div>
      </div>
    </div>
    
    <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  <script type="text/javascript">
  function save() {  
	  $("#CarOwnerInfo").submit();
	  alert("保存成功");  
  }
  </script>
  
  </body>
</html>
