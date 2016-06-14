<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资料提交</title>
    
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
    .menu-left{height:530px;width:20%;border:1px solid #ebebeb;border-right:thick double #D3D3D3;background:#f5f5f5;margin-top:15px;box-shadow:-3px 3px 1px #d3d3d3;}
    .form-group{height:25px;margin-top:5px;}
    .ImgFile{max-width:130px;max-height:100;margin-left:-7px;margin-top:5px;}
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
      <div class="col-xs-3 menu-left">
        <div style="margin-top:25px;font-family:FangSong;font-size:1.1em;">
          <ul class="nav nav-pills nav-stacked" >
            <li class="active"><a style="font-weight:700;border:1px solid #337ab7;">办理新卡</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">办卡统计</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">办卡标准与流程说明</a></li>
          </ul>
        </div>
      </div>
      <div class="col-xs-7 middle">
        <div style="margin-left:30px;margin-top:10px;font-size:1.2em;margin-bottom:15px" align="center">
          <span><b>提交资料</b></span>
        </div>
        
        <form id="uploadForm" action="/CarManageSystem/upload_files.action" method="post" enctype="multipart/form-data">
        <div class="row">
          <input type="hidden" name="cardnum" value="<%=request.getAttribute("CardNum")%>">
          <div class="col-xs-6" id="preview" style="margin-left:20px;width:300px;height:225px;border:1px solid #ededed">
            <div class="row" style="background:#ebebeb">
              <div class="col-xs-6">
                <h5>产权证主页</h5>
              </div>
              <div class="col-xs-5">
                <input name="file1" id="file1" type="file" multiple=true style="margin-top:5px;width:130px;height:22px" onclick="ImgType1()"/>
              </div>
            </div>
            <div class="row">
              <div id="ImgType1_0" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;" align="center">
              </div>
              <div id="ImgType1_1"  class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
            <div class="row">
              <div id="ImgType1_2" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
              <div id="ImgType1_3" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
          </div>
          
          <div class="col-xs-6" id="preview" style="margin-left:20px;width:300px;height:225px;border:1px solid #ededed">
            <div class="row" style="background:#ebebeb">
              <div class="col-xs-6">
                <h5>租赁合同</h5>
              </div>
              <div class="col-xs-5">
                <input name="file2" id="file2" type="file" multiple=true style="margin-top:5px;width:130px;height:22px" onclick="ImgType2()"/>
              </div>
            </div>
            <div class="row">
              <div id="ImgType2_0" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;" align="center">
              </div>
              <div id="ImgType2_1" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
            <div class="row">
              <div id="ImgType2_2" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
              <div id="ImgType2_3" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
          </div>
        </div>
        
        
        <div class="row">
          
          <div class="col-xs-6" id="preview" style="margin-left:20px;width:300px;height:225px;border:1px solid #ededed">
            <div class="row" style="background:#ebebeb">
              <div class="col-xs-6">
                <h5>身份证</h5>
              </div>
              <div class="col-xs-5">
                <input name="file3" id="file3" type="file" multiple=true style="margin-top:5px;width:130px;height:22px" onclick="ImgType3()"/>
              </div>
            </div>
            <div class="row">
              <div id="ImgType3_0" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;" align="center">
              </div>
              <div id="ImgType3_1" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
            <div class="row">
              <div id="ImgType3_2" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
              <div id="ImgType3_3" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
          </div>
          
          <div class="col-xs-6" id="preview" style="margin-left:20px;width:300px;height:225px;border:1px solid #ededed">
            <div class="row" style="background:#ebebeb">
              <div class="col-xs-6">
                <h5>车辆行驶证与驾照</h5>
              </div>
              <div class="col-xs-6">
                <input name="file4" id="file4" type="file" multiple=true style="margin-top:5px;width:130px;height:22px" onclick="ImgType4()"/>
              </div>
            </div>
            <div class="row">
              <div id="ImgType4_0" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;" align="center">
              </div>
              <div id="ImgType4_1" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
            <div class="row">
              <div id="ImgType4_2" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
              <div id="ImgType4_3" class="col-xs-6" style="width:149px;height:95px;border:1px solid #ebebeb;">
              </div>
            </div>
          </div>
        </div>
        <div align="center">
          <button type="submit" style="width:80px;height:30px;line-height:15px;margin-top:10px;margin-bottom:5px;margin-left:30px;background:#337ab7;color:#ffffff;border-radius:5px;border:none">上传</button>   
        </div>
        </form>
               
      </div>
      
      <div class="col-xs-2 right">
        <div style="margin-top:40px;margin-left:50px;font-family:FangSong;font-size:1.1em;text-align:center">
          <ul class="nav nav-pills nav-stacked" >
            <li><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">新卡信息</a></li>
            <li style="margin-top:0px"><a style="color:#000000;font-weight:700;border:1px solid #337ab7;">车主车辆</a></li>
            <li class="active" style="margin-top:0px"><a style="font-weight:700;border:1px solid #337ab7;">提交资料</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  
  <iframe src='page/down.jsp' width=100% style="bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
  
  <script src="/CarManageSystem/js/jquery.form.js"></script> 
  <script src="/CarManageSystem/js/zxxFile.js"></script>
  <script type="text/javascript">
 
//绑定表单提交事件处理器 ,jquery.form.js
	$("#uploadForm").submit(function(){
      var options = {
              success: function (data) {
              	alert("资料已上传，等待审核！");
              }
          };
     $(this).ajaxSubmit(options); 
   // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false 
   	return false; 
  });
  
  function ImgType1() {
	  var params = {
				fileInput: $("#file1").get(0),
				onSelect: function(files) {
					for(var k = 0 ; k < 4 ; k++) {
						var id = "#ImgType1_"+k;
						$(id).html("");	
					}
					var html = " " ,i = 0;
					var funAppendImage = function() {
						file = files[i];
						if (file) {
							var reader = new FileReader()
							reader.onload = function(e) {
								var id = "#ImgType1_"+i;
								$(id).html("");								
								html = '<img src="' + e.target.result + '" class="ImgFile" />';								
								$(id).html(html);				
								i++;
								funAppendImage();
							};
							reader.readAsDataURL(file);
						} 
					};
					funAppendImage();		
				},
				
			};
			ZXXFILE = $.extend(ZXXFILE, params);
			ZXXFILE.init();		
  }
  
  function ImgType2() {
	  var params = {
				fileInput: $("#file2").get(0),
				onSelect: function(files) {
					for(var k = 0 ; k < 4 ; k++) {
						var id = "#ImgType2_"+k;
						$(id).html("");	
					}
					var html = " " ,i = 0;
					var funAppendImage = function() {
						file = files[i];
						if (file) {
							var reader = new FileReader()
							reader.onload = function(e) {
								var id = "#ImgType2_"+i;
								$(id).html("");								
								html = '<img src="' + e.target.result + '" class="ImgFile" />';								
								$(id).html(html);				
								i++;
								funAppendImage();
							};
							reader.readAsDataURL(file);
						} 
					};
					funAppendImage();		
				},
				
			};
			ZXXFILE = $.extend(ZXXFILE, params);
			ZXXFILE.init();	
  }
  
  function ImgType3() {
	  var params = {
				fileInput: $("#file3").get(0),
				onSelect: function(files) {
					for(var k = 0 ; k < 4 ; k++) {
						var id = "#ImgType3_"+k;
						$(id).html("");	
					}
					var html = " " ,i = 0;
					var funAppendImage = function() {
						file = files[i];
						if (file) {
							var reader = new FileReader()
							reader.onload = function(e) {
								var id = "#ImgType3_"+i;
								$(id).html("");								
								html = '<img src="' + e.target.result + '" class="ImgFile" />';								
								$(id).html(html);				
								i++;
								funAppendImage();
							};
							reader.readAsDataURL(file);
						} 
					};
					funAppendImage();		
				},
				
			};
			ZXXFILE = $.extend(ZXXFILE, params);
			ZXXFILE.init();	
  }
  
  function ImgType4() {
	  var params = {
				fileInput: $("#file4").get(0),
				onSelect: function(files) {
					for(var k = 0 ; k < 4 ; k++) {
						var id = "#ImgType4_"+k;
						$(id).html("");	
					}
					var html = " " ,i = 0;
					var funAppendImage = function() {
						file = files[i];
						if (file) {
							var reader = new FileReader()
							reader.onload = function(e) {
								var id = "#ImgType4_"+i;
								$(id).html("");								
								html = '<img src="' + e.target.result + '" class="ImgFile" />';								
								$(id).html(html);				
								i++;
								funAppendImage();
							};
							reader.readAsDataURL(file);
						} 
					};
					funAppendImage();		
				},
				
			};
			ZXXFILE = $.extend(ZXXFILE, params);
			ZXXFILE.init();	
  }
  </script>  
  
  </body>
</html>
